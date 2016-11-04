package org.isotope.jfp.mpc.weixin.client;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.One;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.mpc.weixin.beans.message.WeiXinMessageValueBean;
import org.isotope.jfp.mpc.weixin.service.MyWeixinMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 微信企业号发送信息
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
@Service
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

	public RESTResultBean sendToCompanyIdUserIdsPOST(Long tid,String companyId, String userIds, WeiXinMessageValueBean message) {
		RESTResultBean result = new RESTResultBean();
		try {
			result.setCode(MyWeixinMessageBusiness_.sendToCompanyIdUserIds(tid,companyId, userIds, message));
		} catch (Exception e) {
			result.setMessage("发送成功，其中不存在的用户发送失败或者保存微信信息记录表失败" + e.getMessage());
			result.setCode(Long.toString(1));;
		}
		return result;
	}
}
