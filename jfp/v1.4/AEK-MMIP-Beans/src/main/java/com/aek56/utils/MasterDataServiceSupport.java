package com.aek56.utils;

import org.jfpc.framework.bean.LoginerBean;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.springframework.beans.BeanUtils;

import com.aek56.atm.auditing.CSM0_GYSXX.CSM0_GYSXXDBO;
import com.aek56.atm.auditing.CSM1_YYXX.CSM1_YYXXDBO;
import com.aek56.atm.auditing.CSM2_CSXX.CSM2_CSXXDBO;
import com.aek56.atm.auditing.CSM4_CPXX.CSM4_CPXXDBO;
import com.aek56.atm.auditing.CSM6_ZCZXX.CSM6_ZCZXXDBO;
import com.aek56.atm.company.MCS0_JBXX.MCS0_JBXXDBO;
import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXDBO;
import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZDBO;
import com.aek56.atm.material.MGYS4_GYSTJSP.MGYS4_GYSTJSPDBO;
import com.aek56.atm.material.MYY2_YYTJSPXX.MYY2_YYTJSPXXDBO;

/**
 * 主数据审核类
 * @author Administrator
 *
 */
public class MasterDataServiceSupport extends MyServiceSupport{
	
	/**
	 * 添加数据到审核列表
	 * @param formParamBean 原始数据信息
	 * @param type 1新增2变更
	 */
	public void forAuditing(MyDataBaseObjectSupport formParamBean,String type){
		LoginerBean loginer = getLoginerBean();
		
		//供应商
		if(formParamBean instanceof MGYS0_JBXXDBO){
			CSM0_GYSXXDBO o = new CSM0_GYSXXDBO();
			BeanUtils.copyProperties(formParamBean, o);
			o.makePuk();
			o.setP01_puk(formParamBean.getPuk());
			o.setP02_sjlb(type);
			
			//申请企业名称	
			o.setN05_qymc(loginer.getCompanyNameCN());
			//申请人姓名									
			o.setN06_sqrxm(loginer.getUserNameCN());
			
			super.doInsert(o);
		}
		//医院
		else if(formParamBean instanceof MGYS0_JBXXDBO){
			CSM1_YYXXDBO o = new CSM1_YYXXDBO();
			BeanUtils.copyProperties(formParamBean, o);
			o.makePuk();
			o.setP01_puk(formParamBean.getPuk());
			o.setP02_sjlb(type);
			
			//申请企业名称	
			o.setN05_qymc(loginer.getCompanyNameCN());
			//申请人姓名									
			o.setN06_sqrxm(loginer.getUserNameCN());

			super.doInsert(o);
		} 
		//厂商
		else if(formParamBean instanceof MCS0_JBXXDBO){
			CSM2_CSXXDBO o = new CSM2_CSXXDBO();
			BeanUtils.copyProperties(formParamBean, o);
			o.makePuk();
			o.setP01_puk(formParamBean.getPuk());
			o.setP02_sjlb(type);
			
			//申请企业名称	
			o.setN05_qymc(loginer.getCompanyNameCN());
			//申请人姓名									
			o.setN06_sqrxm(loginer.getUserNameCN());

			super.doInsert(o);
		}
		//注册证信息
		else if(formParamBean instanceof MGAAC_YLQXZCZDBO){
			CSM6_ZCZXXDBO o = new CSM6_ZCZXXDBO();
			BeanUtils.copyProperties(formParamBean, o);
			o.makePuk();
			o.setP01_puk(formParamBean.getPuk());
			o.setP02_sjlb(type);
			
			//申请企业名称	
			o.setN05_qymc(loginer.getCompanyNameCN());
			//申请人姓名									
			o.setN06_sqrxm(loginer.getUserNameCN());

			super.doInsert(o);
		}
		//产品信息
		else if(formParamBean instanceof MGYS4_GYSTJSPDBO){
			CSM4_CPXXDBO o = new CSM4_CPXXDBO();
			BeanUtils.copyProperties(formParamBean, o);
			o.makePuk();
			o.setP01_puk(formParamBean.getPuk());
			o.setP02_sjlb(type);
			
			//申请企业名称	
			o.setN05_qymc(loginer.getCompanyNameCN());
			//申请人姓名									
			o.setN06_sqrxm(loginer.getUserNameCN());
			
			super.doInsert(o);
		}
		else if(formParamBean instanceof MYY2_YYTJSPXXDBO){
			CSM4_CPXXDBO o = new CSM4_CPXXDBO();
			BeanUtils.copyProperties(formParamBean, o);
			o.makePuk();
			o.setP01_puk(formParamBean.getPuk());
			o.setP02_sjlb(type);
			
			//申请企业名称	
			o.setN05_qymc(loginer.getCompanyNameCN());
			//申请人姓名									
			o.setN06_sqrxm(loginer.getUserNameCN());
			
			super.doInsert(o);
		}
//		//品牌信息
//		else if(formParamBean instanceof MGYS0_JBXXDBO){
//			CSM0_GYSXXDBO o = new CSM0_GYSXXDBO();
//			BeanUtils.copyProperties(formParamBean, o);
//			o.makePuk();
//			o.setP01_puk(formParamBean.getPuk());
//			o.setP02_sjlb(type);
//			super.doInsert(o);
//		}
	}

}
