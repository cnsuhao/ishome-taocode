package com.mcookies.qxy.biz.classmanage;

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
 * 班级管理-班级学生
 * 
 * @author linyh
 *
 */
@Controller("ClassStudentController")
public class ClassStudentController extends MyControllerSupport {

	@Resource
	protected ICacheService myCacheService;

	/**
	 * 班级学生(家长)列表查询接口
	 * /qxy/class/student/list/cid=[cid]&page=[page]&size=[size]&token=[token]
	 */
	@RequestMapping(value = "/qxy/class/student/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classStudentListGET(@RequestBody UserDBO user) {
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
	 * 班级学生(家长)搜索接口
	 * /qxy/class/student/search/cid=[cid]&number=[number]&token=[token]
	 */
	@RequestMapping(value = "/qxy/class/student/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classStudentSearchGET(@RequestBody UserDBO user) {
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
	 * 班级学生(家长)信息查询接口
	 * /qxy/class/student/info/cid=[cid]&student=[student_id]&token=[token]
	 */
	@RequestMapping(value = "/qxy/class/student/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classStudentInfoGET(@RequestBody UserDBO user) {
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
	 * 班级学生(家长)新增接口 /qxy/class/student/info
	 */
	@RequestMapping(value = "/qxy/class/student/info", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classStudentInfoPOST(@RequestBody UserDBO user) {
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
	 * 班级学生(家长)修改接口 /qxy/class/student/info
	 */
	@RequestMapping(value = "/qxy/class/student/info", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classStudentInfoPUT(@RequestBody UserDBO user) {
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
	 * 班级学生(家长)删除接口 /qxy/class/student/info
	 */
	@RequestMapping(value = "/qxy/class/student/info", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classStudentInfoDELETE(@RequestBody UserDBO user) {
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
