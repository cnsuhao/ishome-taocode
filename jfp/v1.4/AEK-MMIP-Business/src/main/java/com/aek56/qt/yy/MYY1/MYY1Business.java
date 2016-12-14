package com.aek56.qt.yy.MYY1;

import javax.annotation.Resource;

import org.jfpc.common.sms.SMSService;
import org.jfpc.framework.helper.PKHelper;
import org.jfpc.framework.support.MyServiceSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.aek56.atm.auditing.CSM0_GYSXX.CSM0_GYSXXDBO;
import com.aek56.atm.auditing.CSM0_GYSXX.CSM0_GYSXXService;
import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXDBO;
import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXService;
import com.aek56.atm.company.MGYS3_GYSTJYYXX.MGYS3_GYSTJYYXXDBO;
import com.aek56.atm.company.MGYS3_GYSTJYYXX.MGYS3_GYSTJYYXXService;
import com.aek56.atm.company.MYY0_JBXX.MYY0_JBXXDBO;
import com.aek56.atm.company.MYY0_JBXX.MYY0_JBXXService;
import com.aek56.atm.company.MYY1_YYTJGYSXX.MYY1_YYTJGYSXXDBO;
import com.aek56.atm.company.MYY1_YYTJGYSXX.MYY1_YYTJGYSXXService;
import com.aek56.atm.master.MD2_GYSXX.MD2_GYSXXDBO;
import com.aek56.atm.master.MD2_GYSXX.MD2_GYSXXService;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXDBO;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXService;

@Service
public class MYY1Business extends MyServiceSupport{

	/**
	 * 医院供应商信息
	 */
	@Resource
	private MYY1_YYTJGYSXXService yytjgysxxService;
	
	/**
	 * 供应商医院信息
	 */
	@Resource
	private MGYS3_GYSTJYYXXService gystjyyxxService;
	@Resource
	private MD2_GYSXXService md2_GYSXXService;
	@Resource
	private MD3_YYXXService md3_YYXXService;
	

	@Resource
	private MYY0_JBXXService myy0_JBXXService;
	@Resource
	private MGYS0_JBXXService mgys0_JBXXService;
	/**
	 * 供应商审核
	 */
	@Resource
	private CSM0_GYSXXService csm0_GYSXXService;
    @Resource
    protected SMSService SMSService_;
    @Transactional
    public void addInfo(MYY1_YYTJGYSXXDBO param) throws Exception{
    	//获得供应商ID
    	String cid = param.getPuk();
    	//判断是否已经添加过该供应商
    	param.setP01_yyid(super.getCompanyId());
    	MYY1_YYTJGYSXXDBO ishas = (MYY1_YYTJGYSXXDBO)yytjgysxxService.doRead(param);
    	if(ishas!=null)
    	{
    		//直接修改添加状态
    		param.setF40(ZERO);
    		yytjgysxxService.doUpdate(param);
    	}else
    	{
	    	//医院添加供应商表
	    	{
		    	if(StringUtils.isEmpty(cid)){
		    		cid = PKHelper.creatPUKey();
		    		//预注册
		    		CSM0_GYSXXDBO csm0 = new CSM0_GYSXXDBO();
		    		csm0.setP01_puk(cid);
		    		csm0.setP02_sjlb(ONE);
		    		csm0.setF01_qyqc(param.getF01_qyqc());
		    		csm0.setF30_lxrxm(param.getF30_lxrxm());
		    		csm0.setF32_lxrdh(param.getF32_lxrdh());
		    		csm0.setN03_shzt(TWO);
		    		csm0_GYSXXService.doInsert(csm0);
		    		//插入医院添加供应商
		    		param.setP01_yyid(super.getCompanyId());
		    		param.setF40(ZERO);
		    		yytjgysxxService.doInsert(param);
		    		//发送短信
		    		this.SMSService_.sendMessage("/31022003", param.getF32_lxrdh(), param.getBbb());
		    	}else{
					//从主数据里获取
			    	MD2_GYSXXDBO md2 = new MD2_GYSXXDBO();
			    	md2.setPuk(cid);
			    	MD2_GYSXXDBO remd = (MD2_GYSXXDBO)md2_GYSXXService.doRead(md2);
			    	BeanUtils.copyProperties(remd, param);
			    	//插入医院添加供应商
			    	param.setP01_yyid(super.getCompanyId());
			    	param.setF40(ZERO);
			    	yytjgysxxService.doInsert(param);
			    	//判断供应商是否已经注册。 私有数据，如果没有则发短信
			    	MGYS0_JBXXDBO mgys0 = new MGYS0_JBXXDBO();
			    	mgys0.setPuk(cid);
			    	mgys0=(MGYS0_JBXXDBO)mgys0_JBXXService.doRead(mgys0);
			    	if(mgys0==null){
			    		this.SMSService_.sendMessage("/31022003", param.getF32_lxrdh(), param.getBbb());
			    	}
		    	}
	    	}
	    	//供应商添加医院 表
	    	{
		    		MGYS3_GYSTJYYXXDBO mgys3 = new MGYS3_GYSTJYYXXDBO();
		    		//从主数据获得
		    		MD3_YYXXDBO md3 = new MD3_YYXXDBO();
		    		md3.setPuk(super.getCompanyId());
		    		md3 = (MD3_YYXXDBO) md3_YYXXService.doRead(md3);
		    		if(md3==null){
		    			//从私有数据获得
		        		MYY0_JBXXDBO myy0 = new MYY0_JBXXDBO();
		        		myy0.setPuk(super.getCompanyId());
			    		myy0 = (MYY0_JBXXDBO) myy0_JBXXService.doRead(myy0);
			    		BeanUtils.copyProperties(myy0, mgys3);
		    		}
		    		else{
		    			BeanUtils.copyProperties(md3, mgys3);
		    		}
		        	mgys3.setP01_gysid(cid);
		        	mgys3.setF45(ZERO);
		        	gystjyyxxService.doInsert(mgys3);
	    	}
    	}
    }
	
}
