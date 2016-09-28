package org.isotope.jfp.mpc.weixin.controller;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.mpc.weixin.service.MyWeixinGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 微信企业号用户组管理
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
@Controller
@RequestMapping("/weixin/group")
public class WeiXinGroupController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	MyWeixinGroupService MyWeixinGroupBusiness_;

	/**
	 * 删除一个企业用户组（数据来源于接口）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete/{companyId}/{groupId}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean companyIdGroupIdDeletePOST(@PathVariable String companyId,@PathVariable String groupId) {
		RESTResultBean result = new RESTResultBean();
		result.setResult(MyWeixinGroupBusiness_.companyIdGroupIdDelete(companyId,groupId));
		return result;
	}

	/**
	 * 同步一个企业（数据来源于DB监控）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sync/{companyId}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean companyIdSyncPOST(@PathVariable String companyId) {
		RESTResultBean result = new RESTResultBean();
		result.setResult(MyWeixinGroupBusiness_.companyIdGroupIdSync(companyId));
		return result;
	}
	
	/**
	 * 同步一个企业用户组（数据来源于DB监听）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sync/{companyId}/{groupId}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean companyIdGroupIdSyncPOST(@PathVariable String companyId,@PathVariable String groupId) {
		RESTResultBean result = new RESTResultBean();
		result.setResult(MyWeixinGroupBusiness_.companyGroupIdSync(companyId,groupId));
		return result;
	}
}
