package org.isotope.jfp.mpc.weixin.controller;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.mpc.weixin.service.MyWeixinCompanyService;
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
	MyWeixinCompanyService WinxinBusiness_;

	/**
	 * 微信回调地址
	 * 
	 * @return
	 */
	@RequestMapping(value = "/weixin", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean companyIdPOST(@PathVariable String companyId) {
		RESTResultBean result = new RESTResultBean();
		result.setMessage("微信企业号......" + DateHelper.currentTimeMillis2());
		return result;
	}

}
