package org.isotope.jfp.mpc.weixin.service;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.WeixinService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.mpc.weixin.service.tx.TXWeixinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信对接服务
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 * 
 */
public class MyWeixinCompanyBusiness implements ISFrameworkConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	WeixinService WeixinService_;
	@Resource
	TXWeixinService TXWeixinService_;

	public Object companyIdSync(String companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object companyIdDelete(String companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object companyIdAdd(String companyId) {
		// TODO Auto-generated method stub
		return null;
	}

}
