package org.isotope.jfp.mpc.weixin.controller;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinMessageValueBean;
import org.isotope.jfp.mpc.weixin.service.MyWeixinMessageBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 微信企业号发送信息
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
@Controller
@RequestMapping("/weixin/message")
public class WeiXinMessageController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	MyWeixinMessageBusiness MyWeixinMessageBusiness_;

	/**
	 * 群发微信消息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{companyId}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean sendToCompanyIdPOST(@PathVariable String companyId, WeiXinMessageValueBean message) {
		RESTResultBean result = new RESTResultBean();
		result.setCode(MyWeixinMessageBusiness_.sendToCompanyId(companyId, message));
		return result;
	}
	
	/**
	 * 微信消息发送
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{companyId}/{groupId}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean sendToCompanyIdGroupIdPOST(@PathVariable String companyId, @PathVariable String groupId, WeiXinMessageValueBean message) {
		RESTResultBean result = new RESTResultBean();
		result.setCode(MyWeixinMessageBusiness_.sendToCompanyIdGroupId(companyId, groupId, message));
		return result;
	}
	
	/**
	 * 微信消息发送
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{companyId}/{userId}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean sendToCompanyIdUserIdPOST(@PathVariable String companyId, @PathVariable String userId, WeiXinMessageValueBean message) {
		RESTResultBean result = new RESTResultBean();
		result.setCode(MyWeixinMessageBusiness_.sendToCompanyIdUserId(companyId, userId, message));
		return result;
	}
}
