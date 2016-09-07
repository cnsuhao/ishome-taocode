package org.isotope.jfp.mpc.weixin.service;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.mpc.weixin.beans.WeiXinMessageValueBean;
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
public class MyWeixinBusiness implements ISFrameworkConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public Object companyIdPOST(String companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object loadCompanyUser(String companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object loadCompanyConfig(String companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	public String sendMessageToCompanyAll(String companyId, WeiXinMessageValueBean message) {
		// TODO Auto-generated method stub
		return null;
	}

	public String sendMessageToCompanyUser(String companyId, String userId, WeiXinMessageValueBean message) {
		// TODO Auto-generated method stub
		return null;
	}

}
