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
 * 班级管理-学生管理
 * 
 * @author linyh
 *
 */
@Controller
public class ClassManageController extends MyControllerSupport {

	@Resource
	protected ICacheService myCacheService;

	/**
	 * 班级列表查询接口
	 * /qxy/classlist/term=[term_id]&grade=[grade_id]&class=[cid]&page=[page]&
	 * size=[size]&teacher=[tid]&token=[token]
	 */
	@RequestMapping(value = "/qxy/classlist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classListGET(@RequestBody UserDBO user) {
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
