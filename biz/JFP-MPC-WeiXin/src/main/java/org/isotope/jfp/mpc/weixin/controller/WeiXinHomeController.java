package org.isotope.jfp.mpc.weixin.controller;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.mpc.weixin.beans.WeiXinMessageValueBean;
import org.isotope.jfp.mpc.weixin.service.MyWeixinCompanyBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 微信企业号
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
@Controller
public class WeiXinHomeController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	MyWeixinCompanyBusiness WinxinBusiness_;

	/**
	 * 微信回调地址
	 * 
	 * @return
	 */
	@RequestMapping(value = "/weixin", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean companyIdPOST(@PathVariable String companyId) {
		RESTResultBean result = new RESTResultBean();
		result.setMessage("微信企业号......"+DateHelper.currentTimeMillis2());
		return result;
	}
//
//	/**
//	 * 通讯本同步地址
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/{companyId}/user", method = RequestMethod.POST)
//	@ResponseBody
//	public RESTResultBean companyUserPOST(@PathVariable String companyId) {
//		RESTResultBean result = new RESTResultBean();
//		result.setResult(WinxinBusiness_.loadCompanyUser(companyId));
//		return result;
//	}
//
//	/**
//	 * jssdk的验证算法请求
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/{companyId}/config", method = RequestMethod.POST)
//	@ResponseBody
//	public RESTResultBean companyConfigPOST(@PathVariable String companyId) {
//		RESTResultBean result = new RESTResultBean();
//		result.setResult(WinxinBusiness_.loadCompanyConfig(companyId));
//		return result;
//	}
//
//	/**
//	 * 群发微信消息
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/{companyId}/message", method = RequestMethod.POST)
//	@ResponseBody
//	public RESTResultBean messageAllPOST(@PathVariable String companyId, WeiXinMessageValueBean message) {
//		RESTResultBean result = new RESTResultBean();
//		result.setCode(WinxinBusiness_.sendMessageToCompanyAll(companyId, message));
//		return result;
//	}
//
//	/**
//	 * 微信消息发送
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/{companyId}/message/{userId}", method = RequestMethod.POST)
//	@ResponseBody
//	public RESTResultBean messagePOST(@PathVariable String companyId, @PathVariable String userId, WeiXinMessageValueBean message) {
//		RESTResultBean result = new RESTResultBean();
//		result.setCode(WinxinBusiness_.sendMessageToCompanyUser(companyId, userId, message));
//		return result;
//	}
}
