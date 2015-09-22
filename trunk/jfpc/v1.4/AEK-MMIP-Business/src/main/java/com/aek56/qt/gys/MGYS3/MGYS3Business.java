package com.aek56.qt.gys.MGYS3;

import java.util.List;

import javax.annotation.Resource;

import org.jfpc.common.sms.SMSService;
import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.helper.PKHelper;
import org.jfpc.framework.support.MyServiceSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.aek56.atm.auditing.CSM1_YYXX.CSM1_YYXXDBO;
import com.aek56.atm.auditing.CSM1_YYXX.CSM1_YYXXService;
import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXDBO;
import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXService;
import com.aek56.atm.company.MGYS3_GYSTJYYXX.MGYS3_GYSTJYYXXDBO;
import com.aek56.atm.company.MGYS3_GYSTJYYXX.MGYS3_GYSTJYYXXService;
import com.aek56.atm.company.MYY1_YYTJGYSXX.MYY1_YYTJGYSXXDBO;
import com.aek56.atm.company.MYY1_YYTJGYSXX.MYY1_YYTJGYSXXService;
import com.aek56.atm.master.MD2_GYSXX.MD2_GYSXXDBO;
import com.aek56.atm.master.MD2_GYSXX.MD2_GYSXXService;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXDBO;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXService;

/**
 * 供应商添加医院事务类
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Service
public class MGYS3Business extends MyServiceSupport {
    /**
     * 供应商添加医院
     */
    @Resource
    protected MGYS3_GYSTJYYXXService MGYS3_GYSTJYYXXService_;
    
    /**
     * 医院添加供应商
     */
    @Resource
    protected MYY1_YYTJGYSXXService MYY1_YYTJGYSXXService_;
    
    /**
     * 医院主数据
     */
    @Resource
    protected MD3_YYXXService MD3_YYXXService_;
    
    /**
     * 供应商主数据
     */
    @Resource
    protected MD2_GYSXXService MD2_GYSXXService_;
    
    /**
     * 厂商审核表
     */
    @Resource
    protected CSM1_YYXXService CSM1_YYXXService_;
    
    /**
     * 供应商私有数据
     */
    @Resource
    protected MGYS0_JBXXService MGYS0_JBXXService_;
    
    @Resource
    protected SMSService SMSService_;
    
	/**
	 * 供应商添加医院
	 *
	 * @param param 主数据医院信息
	 * @return 0 正常返回（用户存在，提示是否推送）1医院用户不存在（邀请用户）
	 * @throws Exception 
	 * @Author:zhengxw
	 * @Date:2014-12-6
	 */
	@Transactional
	public int addHospital(MD3_YYXXDBO param) throws Exception {
	    String yyid = param.getPuk();
	    String gysid = this.getCompanyId();
	    int rsCode = 0;
	    
	    // 获取医院主数据信息
	    MD3_YYXXDBO md3Rs = null;
	    
	    // 如果主数据有，则从主数据中获取
	    if (!StringUtils.isEmpty(yyid)) {
	        MD3_YYXXDBO md3Param = new MD3_YYXXDBO();
	        md3Param.setPuk(yyid);
	        md3Rs = (MD3_YYXXDBO) this.MD3_YYXXService_.doRead(md3Param);
	    } else {// 如果主数据没有，则插入审核库
	        yyid = PKHelper.creatPUKey();
	        md3Rs = param;
	        rsCode = 1;
	        CSM1_YYXXDBO csm1Param = new CSM1_YYXXDBO();
	        BeanUtils.copyProperties(param, csm1Param);
	        csm1Param.setPuk(yyid);
	        csm1Param.setP01_puk(yyid);
	        csm1Param.setN05_qymc(this.getLoginerBean().getCompanyNameCN());
	        csm1Param.setN06_sqrxm(this.getLoginerBean().getUserNameCN());
	        this.CSM1_YYXXService_.doInsert(csm1Param);
	        
	        // 同时发送短信
	        this.SMSService_.sendMessage("/31022003", param.getF32_lxrdh(), "3003003", new String[]{param.getBbb()});
	        //this.SMSService_.sendMessage("/31022003", param.getF32_lxrdh(), param.getBbb());
	        
	    }
	    
	    // 入库供应商添加医院表
	    MGYS3_GYSTJYYXXDBO mgys3QueParam = new MGYS3_GYSTJYYXXDBO();
	    mgys3QueParam.setP01_gysid(gysid);
	    mgys3QueParam.setPuk(yyid);
	    List<FrameworkDataBean> mgys3List = this.MGYS3_GYSTJYYXXService_.doSelectPage(mgys3QueParam, false);
	    if (null == mgys3List || mgys3List.size() < 1) {
	        MGYS3_GYSTJYYXXDBO mgys3Param = new MGYS3_GYSTJYYXXDBO();
	        BeanUtils.copyProperties(md3Rs, mgys3Param);
	        mgys3Param.setP01_gysid(this.getCompanyId());
	        mgys3Param.setF40_zgrs(ZERO);
	        this.MGYS3_GYSTJYYXXService_.doInsert(mgys3Param);
	    } else {
	        MGYS3_GYSTJYYXXDBO mgys3Param = (MGYS3_GYSTJYYXXDBO) mgys3List.get(0);
	        mgys3Param.setF45(ZERO);
	        this.MGYS3_GYSTJYYXXService_.doUpdate(mgys3Param);
	    }
	    
	    // 入库医院添加供应商表
	    // 获取供应商主数据信息
	    MYY1_YYTJGYSXXDBO myy1QueParam = new MYY1_YYTJGYSXXDBO();
	    myy1QueParam.setP01_yyid(yyid);
	    myy1QueParam.setPuk(gysid);
	    List<FrameworkDataBean> myy1List = this.MYY1_YYTJGYSXXService_.doSelectPage(myy1QueParam, false);
	    if (null == myy1List || myy1List.size() < 1) {
	        MD2_GYSXXDBO md2Param = new MD2_GYSXXDBO();
	        md2Param.setPuk(this.getCompanyId());
	        MD2_GYSXXDBO md2Rs = (MD2_GYSXXDBO) this.MD2_GYSXXService_.doRead(md2Param);
	        MYY1_YYTJGYSXXDBO myy1Param = new MYY1_YYTJGYSXXDBO();
	        // 如果供应商主数据里没有，则从私有数据中获取
	        if (null == md2Rs || StringUtils.isEmpty(md2Rs)) {
	            MGYS0_JBXXDBO mgys0Param = new MGYS0_JBXXDBO();
	            mgys0Param.setPuk(gysid);
	            MGYS0_JBXXDBO mgys0Rs = (MGYS0_JBXXDBO) this.MGYS0_JBXXService_.doRead(mgys0Param);
	            BeanUtils.copyProperties(mgys0Rs, myy1Param);
	        } else {
	            BeanUtils.copyProperties(md2Rs, myy1Param);
	        }
	        
	        myy1Param.setP01_yyid(param.getPuk());
	        myy1Param.setF40(TWO);
	        this.MYY1_YYTJGYSXXService_.doInsert(myy1Param);
	    } else {
	        MYY1_YYTJGYSXXDBO myy1Param = (MYY1_YYTJGYSXXDBO) myy1List.get(0);
	        myy1Param.setF40(ZERO);
	        this.MYY1_YYTJGYSXXService_.doUpdate(myy1Param);
	    }
	    return rsCode;
	}
    
    public MGYS3Dao getDao(){
        return getMySqlSession().getMapper(MGYS3Dao.class);
    }
}