package org.isotope.jfp.mpc.weixin.client;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.mpc.weixin.beans.message.WeiXinMessageValueBean;
import org.isotope.jfp.mpc.weixin.service.MyWeixinMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信企业号发送信息
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
public class WeiXinMessageClient {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	MyWeixinMessageService MyWeixinMessageBusiness_;

	/**
	 * 群发微信消息
	 * 
	 * @return
	 */
	public RESTResultBean sendToCompanyIdPOST(String companyId, WeiXinMessageValueBean message) {
		RESTResultBean result = new RESTResultBean();
		result.setCode(MyWeixinMessageBusiness_.sendToCompany(companyId, message));
		return result;
	}

	/**
	 * 微信消息发送
	 * 
	 * @return
	 */
	public RESTResultBean sendToCompanyIdGroupIdPOST(String companyId, String groupId, WeiXinMessageValueBean message) {
		RESTResultBean result = new RESTResultBean();
		result.setCode(MyWeixinMessageBusiness_.sendToCompanyIdGroupId(companyId, groupId, message));
		return result;
	}

	public RESTResultBean sendToCompanyIdGroupIdsPOST(String companyId, String groupIds, WeiXinMessageValueBean message) {
		RESTResultBean result = new RESTResultBean();
		result.setCode(MyWeixinMessageBusiness_.sendToCompanyIdGroupIds(companyId, groupIds, message));
		return result;
	}

	/**
	 * 微信消息发送
	 * 
	 * @return
	 */
	public RESTResultBean sendToCompanyIdUserIdPOST(String companyId, String userId, WeiXinMessageValueBean message) {
		RESTResultBean result = new RESTResultBean();
		result.setCode(MyWeixinMessageBusiness_.sendToCompanyIdUserId(companyId, userId, message));
		return result;
	}

	public RESTResultBean sendToCompanyIdUserIdsPOST(String companyId, String userIds, WeiXinMessageValueBean message) {
		RESTResultBean result = new RESTResultBean();
		result.setCode(MyWeixinMessageBusiness_.sendToCompanyIdUserIds(companyId, userIds, message));
		return result;
	}
}
