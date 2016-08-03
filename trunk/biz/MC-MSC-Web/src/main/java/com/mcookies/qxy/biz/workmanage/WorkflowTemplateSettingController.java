package com.mcookies.qxy.biz.workmanage;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcookies.qxy.common.User.UserDBO;

/**
 * 工作管理-工作流程模板设置
 * 
 * @author linyh
 *
 */
@Controller
public class WorkflowTemplateSettingController extends MyControllerSupport {

	@Resource
	protected ICacheService myCacheService;

	/**
	 * 工作流程事项模板查询接口 /qxy/oatags/token=[token]
	 */
	@RequestMapping(value = "/qxy/oatags", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean oaTagsGET(@RequestBody UserDBO user) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(user.getToken()) == false) {
				return tokenFail();
			}

			String userId = getLoginer().getUserId();

			result.setInfo("欢迎访问千校云平台：" + userId + "," + user.getAccount());
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 工作流程事项模板新增接口 /qxy/oatags
	 */
	@RequestMapping(value = "/qxy/oatags", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean oaTagsPOST(@RequestBody UserDBO user) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(user.getToken()) == false) {
				return tokenFail();
			}

			String userId = getLoginer().getUserId();

			result.setInfo("欢迎访问千校云平台：" + userId + "," + user.getAccount());
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 工作流程事项模板修改接口 /qxy/oatags
	 */
	@RequestMapping(value = "/qxy/oatags", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean oaTagsPUT(@RequestBody UserDBO user) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(user.getToken()) == false) {
				return tokenFail();
			}

			String userId = getLoginer().getUserId();

			result.setInfo("欢迎访问千校云平台：" + userId + "," + user.getAccount());
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 工作流程事项模板删除接口 /qxy/oatags
	 */
	@RequestMapping(value = "/qxy/oatags", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean oaTagsDELETE(@RequestBody UserDBO user) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(user.getToken()) == false) {
				return tokenFail();
			}

			String userId = getLoginer().getUserId();

			result.setInfo("欢迎访问千校云平台：" + userId + "," + user.getAccount());
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}
}
