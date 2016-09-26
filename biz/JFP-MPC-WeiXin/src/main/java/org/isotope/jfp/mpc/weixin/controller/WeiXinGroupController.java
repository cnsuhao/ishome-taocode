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
	MyWeixinGroupService MyWeixinDeptBusiness_;

	/**
	 * 增加一个企业用户组（数据来源于DB）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add/{companyId}/{groupId}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean companyIdGroupIdAddPOST(@PathVariable String companyId,@PathVariable String groupId) {
		RESTResultBean result = new RESTResultBean();
		result.setResult(MyWeixinDeptBusiness_.companyIdGroupIdAdd(companyId,groupId));
		return result;
	}

	/**
	 * 删除一个企业用户组（数据来源于接口）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete/{companyId}/{groupId}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean companyIdGroupIdDeletePOST(@PathVariable String companyId,@PathVariable String groupId) {
		RESTResultBean result = new RESTResultBean();
		result.setResult(MyWeixinDeptBusiness_.companyIdGroupIdDelete(companyId,groupId));
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
		result.setResult(MyWeixinDeptBusiness_.companyIdGroupIdSync(companyId,groupId));
		return result;
	}
}
