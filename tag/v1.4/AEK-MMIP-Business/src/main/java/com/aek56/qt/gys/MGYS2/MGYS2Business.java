package com.aek56.qt.gys.MGYS2;

import javax.annotation.Resource;

import org.jfpc.framework.helper.PKHelper;
import org.jfpc.framework.support.MyServiceSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.aek56.atm.auditing.CSM2_CSXX.CSM2_CSXXDBO;
import com.aek56.atm.auditing.CSM2_CSXX.CSM2_CSXXService;
import com.aek56.atm.company.MGYS2_GYSTJCSXX.MGYS2_GYSTJCSXXDBO;
import com.aek56.atm.company.MGYS2_GYSTJCSXX.MGYS2_GYSTJCSXXService;
import com.aek56.atm.credentials.MGA8C_YYZZ.MGA8C_YYZZDBO;
import com.aek56.atm.credentials.MGA8C_YYZZ.MGA8C_YYZZService;
import com.aek56.atm.credentials.MGA9C_SCXKZ.MGA9C_SCXKZDBO;
import com.aek56.atm.credentials.MGA9C_SCXKZ.MGA9C_SCXKZService;
import com.aek56.atm.master.MD4_CSXX.MD4_CSXXDBO;
import com.aek56.atm.master.MD4_CSXX.MD4_CSXXService;

/**
 * 供应商维护厂家事务类
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Service
public class MGYS2Business extends MyServiceSupport {
    /**
     * 厂家营业执照
     */
    @Resource
    protected MGA8C_YYZZService MGA8C_YYZZService_;
    
    /**
     * 厂家生产许可证
     */
    @Resource
    protected MGA9C_SCXKZService MGA9C_SCXKZService_;
    
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
     * 厂家主数据
     */
    @Resource
    protected CSM2_CSXXService CSM2_CSXXService_;
    
    /**
     * 保存厂家信息
     * 
     * @param param
     * @Author:zhengxw
     * @Date:2014-11-29
     */
    @Transactional
    public void saveFactory(MGYS2_GYSTJCSXXDBO param) {
    	Object[] paramArray = this.pvoToDbo(param);
    	String cmpid = this.getCompanyId();
    	// 厂家信息入库
    	MD4_CSXXDBO cjParam = (MD4_CSXXDBO) paramArray[0];
    	if (!StringUtils.isEmpty(cjParam.getF01_qyqc())) {
    		String cjpuk = this.gysOptSccjInfo(cjParam);
    		
    		// 厂家如果有名称，还要判断是否有证书
    		// 营业执照入库
    		MGA8C_YYZZDBO cjyyzzParam = (MGA8C_YYZZDBO) paramArray[1];
    		if (!StringUtils.isEmpty(cjyyzzParam.getBbb())) {
    			cjyyzzParam.setK02_sccj_qyid(cjpuk);
    			cjyyzzParam.setK01_gysid(cmpid);
    			if (StringUtils.isEmpty(cjyyzzParam.getPuk())) {
        			this.MGA8C_YYZZService_.doInsert(cjyyzzParam);
        		} else {
        			this.MGA8C_YYZZService_.doUpdate(cjyyzzParam);
        		}
    		}
    		
    		// 生产许可证入库
    		MGA9C_SCXKZDBO cjscxkzParam = (MGA9C_SCXKZDBO) paramArray[2];
    		if (!StringUtils.isEmpty(cjscxkzParam.getBbb())) {
    			cjscxkzParam.setK01_gysid(cmpid);
    			cjscxkzParam.setK02_sccj_qyid(cjpuk);
    			if (StringUtils.isEmpty(cjscxkzParam.getPuk())) {
    				this.MGA9C_SCXKZService_.doInsert(cjscxkzParam);
    			} else {
    				this.MGA9C_SCXKZService_.doUpdate(cjscxkzParam);
    			}
    		}
    	}
    }
    
    /**
     * 供应商操作生产厂家信息
     * @param param
     * @return
     */
    private String gysOptSccjInfo(MD4_CSXXDBO param) {
    	MGYS2_GYSTJCSXXDBO cjInsertParam = new MGYS2_GYSTJCSXXDBO();
    	
    	cjInsertParam.setP01_gysid(this.getCompanyId());
    	// 如果ID不为空，则说明从主数据中获取或者是编辑情况
    	if (!StringUtils.isEmpty(param.getPuk())) {
    		// 根据ID从主数据中获取厂家记录
        	MD4_CSXXDBO cjRs = (MD4_CSXXDBO) this.MD4_CSXXService_.doRead(param);
        	if (null == cjRs || StringUtils.isEmpty(cjRs.getPuk())) {
        	    BeanUtils.copyProperties(param, cjInsertParam);
        	} else {
        	    BeanUtils.copyProperties(cjRs, cjInsertParam);
        	}
        	
        	// 查询供应商添加医院表，判断是修改还是添加
        	MGYS2_GYSTJCSXXDBO mgys2Param = new MGYS2_GYSTJCSXXDBO();
        	mgys2Param.setPuk(param.getPuk());
        	MGYS2_GYSTJCSXXDBO rs = (MGYS2_GYSTJCSXXDBO) this.MGYS2_GYSTJCSXXService_.doRead(mgys2Param);
        	if (null == rs || StringUtils.isEmpty(rs.getPuk())) {
        	    cjInsertParam.setN03_logogjz(cjInsertParam.getF01_qyqc().substring(0, 1));
        	    this.MGYS2_GYSTJCSXXService_.doInsert(cjInsertParam);
        	}
    	} else {// 如果ID为空，则证明是新增主数据中没有的厂家
    	    // 基本表入库
    	    param.setPuk(PKHelper.creatPUKey());
    		BeanUtils.copyProperties(param, cjInsertParam);
    		cjInsertParam.setN03_logogjz(cjInsertParam.getF01_qyqc().substring(0, 1));
    		this.MGYS2_GYSTJCSXXService_.doInsert(cjInsertParam);
    		
    		// 审核表入库
    		CSM2_CSXXDBO csm2Dbo = new CSM2_CSXXDBO();
    		BeanUtils.copyProperties(param, csm2Dbo);
    		csm2Dbo.setN05_qymc(this.getLoginerBean().getCompanyNameCN());
    		csm2Dbo.setN06_sqrxm(this.getLoginerBean().getUserNameCN());
    		this.CSM2_CSXXService_.doInsert(csm2Dbo);
    	}
    	return cjInsertParam.getPuk();
    }
    
    /**
     * 页面对象转化为dbo
     *
     * @param param
     * @return rs[厂商对象，厂商营业执照，厂商生产许可证，
     *            总代对象，总代营业执照，厂商生产许可证]
     * @Author:zhengxw
     * @Date:2014-12-7
     */
    private Object[] pvoToDbo(MGYS2_GYSTJCSXXDBO param) {
        Object[] rs = new Object[6];
        
        // 拆分出厂家基本信息（ID和名称）
        MD4_CSXXDBO cjDbo = new MD4_CSXXDBO();
        cjDbo.setPuk(param.getPuk());
        cjDbo.setF01_qyqc(param.getF01_qyqc());
        cjDbo.setEb1(param.getEb1());
        cjDbo.setEb2(param.getEb2());
        
        // 拆分厂家营业执照
        MGA8C_YYZZDBO cjyyzzDbo = new MGA8C_YYZZDBO();
        cjyyzzDbo.setPuk(param.getFb1());
        cjyyzzDbo.setK02_sccj_qyid(param.getPuk());
        cjyyzzDbo.setF04_yxksrq(param.getF25_qyyyzzksrq());
        cjyyzzDbo.setF05_yxzzrq(param.getF26_qyyyzzzlrq());
        cjyyzzDbo.setBbb(param.getF35_qyyyzz());
        cjyyzzDbo.setBbb(param.getF33_qyjyxkz());
        
        // 拆分厂家经营许可证
        MGA9C_SCXKZDBO cjjyxkzDbo = new MGA9C_SCXKZDBO();
        cjjyxkzDbo.setPuk(param.getFb2());
        cjjyxkzDbo.setK02_sccj_qyid(param.getPuk());
        cjjyxkzDbo.setF04_yxksrq(param.getF21_qyjyxkzksrq());
        cjjyxkzDbo.setF05_yxzzrq(param.getF22_qyjyxkzzlrq());
        cjjyxkzDbo.setBbb(param.getF33_qyjyxkz());
        cjjyxkzDbo.setBbb(param.getF35_qyyyzz());
        
        rs[0] = cjDbo;
        rs[1] = cjyyzzDbo;
        rs[2] = cjjyxkzDbo;
        return rs;
    }
    
    public MGYS2Dao getDao(){
        return getMySqlSession().getMapper(MGYS2Dao.class);
    }
}