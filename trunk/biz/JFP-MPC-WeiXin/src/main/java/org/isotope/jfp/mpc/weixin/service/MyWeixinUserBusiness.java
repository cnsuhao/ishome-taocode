package org.isotope.jfp.mpc.weixin.service;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.WeixinService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信数据管理服务<br>
 * 企业用户信息
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 * 
 */
public class MyWeixinUserBusiness implements ISFrameworkConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	WeixinService WeixinService_;
	@Resource
	WeixinService TXWeixinService_;

	public Object companyIdUserIdAdd(String companyId, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object loadCompanyId(String companyId, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object companyIdGroupIdUserIdAdd(String companyId, String groupId, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object companyIdGroupIdUserIdDelete(String companyId, String groupId, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object companyIdUserIdSync(String companyId, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
