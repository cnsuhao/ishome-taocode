package com.aek56.qt.yy.CGTZ1;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek56.atm.company.cmp.CompanyService;
import com.aek56.atm.credentials.CGTZ_TSJL.CGTZ_TSJLDBO;
import com.aek56.atm.credentials.CGTZ_TSJL.CGTZ_TSJLService;
import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZDBO;
import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZService;
@Service
public class CGTZ1Business extends MyServiceSupport{

	@Resource
	private CGTZ_TSJLService cgtz_TSJLService;
	@Resource
	private CompanyService companyService;
	@Resource
	private MGAAC_YLQXZCZService mgaac_YLQXZCZService;
	/**
	 * 推送審核
	 * @param param
	 */
	@Transactional
	public void tsshenhe(CGTZ_TSJLDBO param)
	{
		//审核通过
		if(ZERO.equals(param.getF13_shzt()))
		{	//调用接收方法
			companyService.auditingCredentialsWithManual(param.getPuk());
		}
		//更新推送记录表
		cgtz_TSJLService.doUpdate(param);
		
	}
	
	/**
	 * 审核时，查看注册证详情
	 */
	public MGAAC_YLQXZCZDBO  m321132105post(MGAAC_YLQXZCZDBO param){
		MGAAC_YLQXZCZDBO dbo =(MGAAC_YLQXZCZDBO)mgaac_YLQXZCZService.doRead(param);
		return dbo;
	}
	

}
