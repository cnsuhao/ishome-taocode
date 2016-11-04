package org.isotope.jfp.mpc.weixin.controller;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.mpc.weixin.client.WeiXinUserClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 微信企业号用户管理
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
@Controller
@RequestMapping("/weixin/user")
public class WeiXinUserController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	WeiXinUserClient WeiXinUserClient_;

	/**
	 * 增加一个企业用户（数据来源于DB）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add/{companyId}/{userId}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean companyIdUserIdAddPOST(@PathVariable String companyId, @PathVariable String userId) {
		return WeiXinUserClient_.companyIdUserIdAddPOST(companyId, userId);
	}

	/**
	 * 增加一个企业用户组用户（数据来源于DB）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add/{companyId}/{groupId}/{userId}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean companyIdGroupIdUserIdAddPOST(@PathVariable String companyId, @PathVariable String groupId, @PathVariable String userId) {
		return WeiXinUserClient_.companyIdGroupIdUserIdAddPOST(companyId, groupId, userId);
	}

	/**
	 * 删除一个企业用户（数据来源于接口）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete/{companyId}/{userId}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean companyIdUserIdDeletePOST(@PathVariable String companyId, @PathVariable String userId) {
		return WeiXinUserClient_.companyIdUserIdDeletePOST(companyId, userId);
	}

	/**
	 * 同步一个企业用户（数据来源于DB监听）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sync/{companyId}/{userId}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean companyIdUserIdSyncPOST(@PathVariable String companyId, @PathVariable String userId) {
		return WeiXinUserClient_.companyIdUserIdSyncPOST(companyId, userId);
	}
}
