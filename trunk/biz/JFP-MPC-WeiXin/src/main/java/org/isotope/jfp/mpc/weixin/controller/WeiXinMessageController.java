package org.isotope.jfp.mpc.weixin.controller;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.mpc.weixin.beans.message.WeiXinMessageValueBean;
import org.isotope.jfp.mpc.weixin.client.WeiXinMessageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	WeiXinMessageClient WeiXinMessageClient_;

	/**
	 * 群发微信消息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/company/{companyId}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean sendToCompanyIdPOST(@PathVariable String companyId, @RequestBody WeiXinMessageValueBean message) {
		return WeiXinMessageClient_.sendToCompanyIdPOST(companyId, message);
	}

	/**
	 * 微信消息发送
	 * 
	 * @return
	 */
	@RequestMapping(value = "/group/{companyId}/{groupId}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean sendToCompanyIdGroupIdPOST(@PathVariable String companyId, @PathVariable String groupId, @RequestBody WeiXinMessageValueBean message) {
		return WeiXinMessageClient_.sendToCompanyIdGroupIdPOST(companyId, groupId, message);
	}

	@RequestMapping(value = "/groups/{companyId}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean sendToCompanyIdGroupIdsPOST(@PathVariable String companyId, String groupIds, @RequestBody WeiXinMessageValueBean message) {
		return WeiXinMessageClient_.sendToCompanyIdGroupIdsPOST(companyId, groupIds, message);
	}

	/**
	 * 微信消息发送
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/{companyId}/{userId}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean sendToCompanyIdUserIdPOST(@PathVariable String companyId, @PathVariable String userId, @RequestBody WeiXinMessageValueBean message) {
		return WeiXinMessageClient_.sendToCompanyIdUserIdPOST(companyId, userId, message);
	}

	@RequestMapping(value = "/users/{companyId}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean sendToCompanyIdUserIdsPOST(@PathVariable String companyId, String userIds, @RequestBody WeiXinMessageValueBean message) {
		return WeiXinMessageClient_.sendToCompanyIdUserIdsPOST(companyId, userIds, message);
	}
}
