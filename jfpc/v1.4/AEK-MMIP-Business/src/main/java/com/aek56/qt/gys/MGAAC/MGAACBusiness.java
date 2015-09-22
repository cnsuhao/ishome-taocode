package com.aek56.qt.gys.MGAAC;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jfpc.framework.helper.PKHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.aek56.atm.auditing.CSM2_CSXX.CSM2_CSXXDBO;
import com.aek56.atm.auditing.CSM2_CSXX.CSM2_CSXXService;
import com.aek56.atm.auditing.CSM6_ZCZXX.CSM6_ZCZXXDBO;
import com.aek56.atm.auditing.CSM6_ZCZXX.CSM6_ZCZXXService;
import com.aek56.atm.company.MGYS2_GYSTJCSXX.MGYS2_GYSTJCSXXDBO;
import com.aek56.atm.company.MGYS2_GYSTJCSXX.MGYS2_GYSTJCSXXService;
import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZDBO;
import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZService;
import com.aek56.atm.master.MD4_CSXX.MD4_CSXXDBO;
import com.aek56.atm.master.MD4_CSXX.MD4_CSXXService;
import com.aek56.atm.master.MDG_ZCZXX.MDG_ZCZXXDBO;
import com.aek56.atm.master.MDG_ZCZXX.MDG_ZCZXXService;

/**
 * 供应商维护注册证事务类
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Service
public class MGAACBusiness extends MyServiceSupport {
    /**
     * 供应商私有注册证表
     */
    @Resource
    protected MGAAC_YLQXZCZService MGAAC_YLQXZCZService_;

    /**
     * 注册证主数据
     */
    @Resource
    protected MDG_ZCZXXService MDG_ZCZXXService_;

    /**
     * 注册证审核表
     */
    @Resource
    protected CSM6_ZCZXXService CSM6_ZCZXXService_;

    /**
     * 厂家主数据
     */
    @Resource
    protected MD4_CSXXService MD4_CSXXService_;

    /**
     * 供应商添加厂商表
     */
    @Resource
    protected MGYS2_GYSTJCSXXService MGYS2_GYSTJCSXXService_;
    
    /**
     * 厂家信息审核
     */
    @Resource
    protected CSM2_CSXXService CSM2_CSXXService_;

    /**
     * 供应商添加或修改注册证
     * 
     * @param param
     * @Author:zhengxw
     * @Date:2014-12-6
     */
    @Transactional
    public void optZcz(MGAAC_YLQXZCZDBO param, String oldPuk) {
        String optType = param.getP02_zjbs();
        // 如果主键为空，则证明是添加主数据中没有的注册证
        if (StringUtils.isEmpty(param.getPuk())) {
            this.addNewZcz(param, oldPuk);
        } else {// 否则，可能是添加，可能是修改
            // 查询供应商注册证表，判断是添加还是修改
            MGAAC_YLQXZCZDBO rs = (MGAAC_YLQXZCZDBO) this.MGAAC_YLQXZCZService_.doRead(param);
            
            // 如果供应商私有数据中没有记录，则是添加
            if (null == rs || StringUtils.isEmpty(rs.getPuk())) {
                /**
                 * 添加注册证的话需要添加厂商信息
                 */
                // 查询主数据中注册证内容
                MDG_ZCZXXDBO mdgParam = new MDG_ZCZXXDBO();
                mdgParam.setPuk(param.getPuk());
                MDG_ZCZXXDBO mdgRs = (MDG_ZCZXXDBO) this.MDG_ZCZXXService_.doRead(mdgParam);
                
                // 查询供应商添加厂商表
                MGYS2_GYSTJCSXXDBO mgys2Param = new MGYS2_GYSTJCSXXDBO();
                mgys2Param.setPuk(mdgRs.getK04_scqyid());
                MGYS2_GYSTJCSXXDBO alreadyAddRs = (MGYS2_GYSTJCSXXDBO) this.MGYS2_GYSTJCSXXService_.doRead(mgys2Param);
                
                String factoryId = "";
                String factoryName = "";
                
                // 如果供应商添加厂商表内没记录，则新增一条记录
                if (null == alreadyAddRs || StringUtils.isEmpty(alreadyAddRs.getPuk())) {
                    // 根据注册证对象获取供应商添加厂家对象
                    MGYS2_GYSTJCSXXDBO insertTmp = this.getCjInfoByZcz(mdgRs);
                    if (null != insertTmp && !StringUtils.isEmpty(insertTmp.getPuk())) {
                        factoryId = insertTmp.getPuk();
                        factoryName = insertTmp.getF01_qyqc();
                        this.MGYS2_GYSTJCSXXService_.doInsert(insertTmp);
                    }
                } else {// 如果有记录，则获取厂家ID
                    factoryId = alreadyAddRs.getPuk();
                    factoryName = alreadyAddRs.getF01_qyqc();
                }
                
                // 复制主数据中的注册证记录到供应商私有数据
                MGAAC_YLQXZCZDBO zczInsertDbo = new MGAAC_YLQXZCZDBO();
                BeanUtils.copyProperties(mdgRs, zczInsertDbo);
                
                // 完善一些必要字段
                if (StringUtils.isEmpty(optType)) {
                    zczInsertDbo.setP02_zjbs(ONE);
                }
                zczInsertDbo.setP01_gysid(this.getCompanyId());
                zczInsertDbo.setBbb(param.getBbb());// 图片要获取新上传的图片
                
                // 如果没有老的证书号，则证明是添加，否则为换证
                if (StringUtils.isEmpty(oldPuk)) {
                    zczInsertDbo.setK01_zczid(zczInsertDbo.getPuk());
                } else {
                    zczInsertDbo.setK01_zczid(oldPuk);
                }
                zczInsertDbo.setK04_scqyid(factoryId);
                zczInsertDbo.setF32_scqyzwmc(factoryName);
                this.MGAAC_YLQXZCZService_.doInsert(zczInsertDbo);
            } else {// 如果表中有记录，则是修改
                if (StringUtils.isEmpty(optType)) {
                    param.setP02_zjbs(TWO);
                }
                param.setP01_gysid(this.getCompanyId());
                if (StringUtils.isEmpty(oldPuk)) {
                    param.setK01_zczid(param.getPuk());// 业务主键，新增时和主键相同
                } else {
                    param.setK01_zczid(oldPuk);
                }
                this.MGAAC_YLQXZCZService_.doUpdate(param);
            }
        }
    }

    /**
     * 供应商添加主数据中没有的注册证
     * 
     * @param param
     */
    public void addNewZcz(MGAAC_YLQXZCZDBO param, String oldPuk) {
        // 供应商注册证基础数据表入库
        param.setPuk(PKHelper.creatPUKey());
        param.setP01_gysid(this.getCompanyId());
        if (StringUtils.isEmpty(param.getP02_zjbs())) {
            param.setP02_zjbs(ONE);
        }
        if (StringUtils.isEmpty(oldPuk)) {
            param.setK01_zczid(param.getPuk());// 业务主键，新增时和主键相同
        } else {
            param.setK01_zczid(oldPuk);
        }
        this.MGAAC_YLQXZCZService_.doInsert(param);

        // 审核表入库
        this.addIntoZczShenhe(param, ONE);
        
    }

    /**
     * 纠错
     * 
     * @param param
     */
    @Transactional
    public void errorRecoveryOrUpdate(MGAAC_YLQXZCZDBO param) {
        // 获取主数据中的注册证信息
        MDG_ZCZXXDBO mdgParam = new MDG_ZCZXXDBO();
        mdgParam.setPuk(param.getPuk());
        MDG_ZCZXXDBO mdgRs = (MDG_ZCZXXDBO) this.MDG_ZCZXXService_.doRead(mdgParam);
        
        // 复制字段
        MGAAC_YLQXZCZDBO mgaacParam = new MGAAC_YLQXZCZDBO();
        BeanUtils.copyProperties(mdgRs, mgaacParam);
        mgaacParam.setF01_zczzwmc(param.getF01_zczzwmc());
        mgaacParam.setF03_cpzwmc(param.getF03_cpzwmc());
        mgaacParam.setF32_scqyzwmc(param.getF32_scqyzwmc());
        mgaacParam.setF09_yxksrq(param.getF09_yxksrq());
        mgaacParam.setF10_yxzzrq(param.getF10_yxzzrq());
        
        // 修改注册证基础数据表
        mgaacParam.setP01_gysid(this.getCompanyId());
        mgaacParam.setP02_zjbs(TWO);
        MGAAC_YLQXZCZDBO isAdd = (MGAAC_YLQXZCZDBO) this.MGAAC_YLQXZCZService_.doRead(param);
        if (null == isAdd || StringUtils.isEmpty(isAdd.getPuk())) {
            this.MGAAC_YLQXZCZService_.doInsert(mgaacParam);
        } else {
            this.MGAAC_YLQXZCZService_.doUpdate(mgaacParam);
        }

        // 审核表入库
        this.addIntoZczShenhe(mgaacParam, TWO);
    }

    /**
     * 根据注册证获取厂家对象
     * 
     * @param param
     * @return
     * @Author:zhengxw
     * @Date:2014-12-8
     */
    private MGYS2_GYSTJCSXXDBO getCjInfoByZcz(MDG_ZCZXXDBO param) {
        String cjid = param.getK04_scqyid();
        String cjname = param.getF32_scqyzwmc();
        
        if (StringUtils.isEmpty(cjid) && StringUtils.isEmpty(cjname)) {
            return null;
        }
        
        MGYS2_GYSTJCSXXDBO rs = new MGYS2_GYSTJCSXXDBO();

        // 查询主数据中的厂家信息
        MD4_CSXXDBO md4Param = new MD4_CSXXDBO();
        md4Param.setPuk(cjid);
        MD4_CSXXDBO md4Rs = (MD4_CSXXDBO) this.MD4_CSXXService_
                .doRead(md4Param);
        
        // 如果主数据中没有厂家，则插入审核表
        if (null == md4Rs || StringUtils.isEmpty(md4Rs.getPuk())) {
            rs.setPuk(cjid);
            rs.setF01_qyqc(cjname);
            CSM2_CSXXDBO csm2Param = new CSM2_CSXXDBO();
            csm2Param.setP01_puk(cjid);
            csm2Param.setP02_sjlb(ONE);
            csm2Param.setF01_qyqc(cjname);
            csm2Param.setN05_qymc(this.getLoginerBean().getCompanyNameCN());
            csm2Param.setN06_sqrxm(this.getLoginerId());
            this.CSM2_CSXXService_.doInsert(csm2Param);
        } else {
            BeanUtils.copyProperties(md4Rs, rs);
        }
        rs.setP01_gysid(this.getCompanyId());
        rs.setN03_logogjz(rs.getF01_qyqc().substring(0, 1));
        return rs;
    }

    /**
     * 注册证审核表入库
     * 
     * @param param
     * @param optType
     */
    private void addIntoZczShenhe(MGAAC_YLQXZCZDBO param, String optType) {
        CSM6_ZCZXXDBO csm6Param = new CSM6_ZCZXXDBO();
        BeanUtils.copyProperties(param, csm6Param);
        csm6Param.setPuk(PKHelper.creatPUKey());// 审核表主键和基础表主键不同
        csm6Param.setP01_puk(param.getPuk());
        csm6Param.setP02_sjlb(optType);
        csm6Param.setN05_qymc(this.getLoginerBean().getCompanyId());
        csm6Param.setN06_sqrxm(this.getLoginerBean().getUserNameCN());
        this.CSM6_ZCZXXService_.doInsert(csm6Param);
    }
    
    public List<Map<String, String>> doCountAllZs(MyDataBaseObjectSupport formParam) {
        return this.getDao().doCountAllZs(formParam);
    }

    public MGAACDao getDao() {
        return getMySqlSession().getMapper(MGAACDao.class);
    }
}