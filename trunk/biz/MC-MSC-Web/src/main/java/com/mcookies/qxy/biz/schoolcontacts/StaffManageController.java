package com.mcookies.qxy.biz.schoolcontacts;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.User.UserService;
/**
 * 班级课表查询
 * @author macBookTang
 *
 */
@Controller
public class StaffManageController extends MyControllerSupport {
	@Resource
	protected UserService UserService_;

	
	@RequestMapping(value = "/syllabus", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean syllabusPOST(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken("") == false) {
				return tokenFail();
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

}
