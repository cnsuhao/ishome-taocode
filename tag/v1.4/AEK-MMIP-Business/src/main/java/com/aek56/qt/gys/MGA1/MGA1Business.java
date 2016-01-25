package com.aek56.qt.gys.MGA1;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.aek56.atm.credentials.MGA1_YYZZ.MGA1_YYZZDBO;
import com.aek56.atm.credentials.MGA1_YYZZ.MGA1_YYZZService;
import com.aek56.atm.credentials.MGA2_JYXKZ.MGA2_JYXKZDBO;
import com.aek56.atm.credentials.MGA2_JYXKZ.MGA2_JYXKZService;
import com.aek56.atm.credentials.MGA3_GSSWDJZ.MGA3_GSSWDJZDBO;
import com.aek56.atm.credentials.MGA3_GSSWDJZ.MGA3_GSSWDJZService;

/**
 * 供应商维护基本证件事务类
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Service
public class MGA1Business extends MyServiceSupport {
    /**
     * 营业执照
     */
    @Resource
    protected MGA1_YYZZService MGA1_YYZZService_;
    
    /**
     * 经营许可证
     */
    @Resource
    protected MGA2_JYXKZService MGA2_JYXKZService_;
    
    /**
     * 税务登记证
     */
    @Resource
    protected MGA3_GSSWDJZService MGA3_GSSWDJZService_;
    
    /**
     * 供应商维护基本证件
     * 
     * @param param
     * @Author:zhengxw
     * @Date:2014-11-29
     */
    @Transactional
    public int optGysZj(MGA1_YYZZDBO param) {
        String gysid = this.getCompanyId();
        
        MGA1_YYZZDBO yyzzDbo = new MGA1_YYZZDBO();
        yyzzDbo.setPuk(param.getPuk());
        yyzzDbo.setF04_yxksrq(param.getK01_gysid());
        yyzzDbo.setF05_yxzzrq(param.getK02_zjbh());
        yyzzDbo.setBbb(param.getK03_zjlb());
        yyzzDbo.setK01_gysid(gysid);
        
        MGA2_JYXKZDBO jyxkzDbo = new MGA2_JYXKZDBO();
        jyxkzDbo.setPuk(param.getF01_fzdwmc());
        jyxkzDbo.setF04_yxksrq(param.getF02_fzrq());
        jyxkzDbo.setF05_yxzzrq(param.getF03_yxnx());
        jyxkzDbo.setBbb(param.getF04_yxksrq());
        jyxkzDbo.setK01_gysid(gysid);
        
        MGA3_GSSWDJZDBO swdjzDbo = new MGA3_GSSWDJZDBO();
        swdjzDbo.setPuk(param.getF05_yxzzrq());
        swdjzDbo.setF04_yxksrq(param.getF06_shzt());
        swdjzDbo.setF05_yxzzrq(param.getF07());
        swdjzDbo.setBbb(param.getF08());
        swdjzDbo.setK01_gysid(gysid);
        
        int yyzzNum = 0, jyxkzNum = 0, swdjzNum = 0;
        if (StringUtils.isEmpty(yyzzDbo.getPuk())) {
            yyzzNum = this.MGA1_YYZZService_.doInsert(yyzzDbo);
        } else {
            yyzzNum = this.MGA1_YYZZService_.doUpdate(yyzzDbo);
        }
        if (StringUtils.isEmpty(jyxkzDbo.getPuk())) {
            jyxkzNum = this.MGA2_JYXKZService_.doInsert(jyxkzDbo);
        } else {
            jyxkzNum = this.MGA2_JYXKZService_.doUpdate(jyxkzDbo);
        }
        if (StringUtils.isEmpty(swdjzDbo.getPuk())) {
            swdjzNum = this.MGA3_GSSWDJZService_.doInsert(swdjzDbo);
        } else {
            swdjzNum = this.MGA3_GSSWDJZService_.doUpdate(swdjzDbo);
        }
        return yyzzNum + jyxkzNum + swdjzNum;
    }
    
    public MGA1Dao getDao(){
        return getMySqlSession().getMapper(MGA1Dao.class);
    }
}