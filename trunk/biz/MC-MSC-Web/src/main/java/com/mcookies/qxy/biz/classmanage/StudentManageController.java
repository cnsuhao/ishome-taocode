package com.mcookies.qxy.biz.classmanage;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcookies.qxy.common.UStudent.UStudentPVO;
import com.mcookies.qxy.common.UStudent.UStudentService;
import com.mcookies.qxy.common.User.UserDBO;

/**
 * 班级管理-学生管理
 * 
 * @author linyh
 *
 */
@Controller
public class StudentManageController extends MyControllerSupport {

//	@Resource
//	protected ClassService classService;
//	@Resource
//	protected UserService userService;
	@Resource
	protected UStudentService uStudentService;
//	@Resource
//	protected UStudentExtService uStudentExtService;
//	@Resource
//	protected UParentService uParentService;
//	@Resource
//	protected ClassStudentService classStudentService;
//	@Resource
//	protected UStudentParentService uStudentParentService;

	/**
	 * 学生列表搜索查询接口
	 * /qxy/stundent/list/class=[cid]&page=[page]&size=[size]&token=[token]
	 */
	@RequestMapping(value = "/stundent/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean studentListGET(@RequestBody UserDBO user) {
		// TODO
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(user.getToken()) == false) {
				return tokenFail();
			}

			Long userId = getLoginer().getUserId();

			result.setInfo("欢迎访问千校云平台：" + userId + "," + user.getAccount());
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 学生搜索接口
	 * 实现与班级学生管理中的班级学生搜索接口类似，多了年级
	 * /qxy/stundent/search/student=[studentId]&number=[number]&token=[token]
	 */
	@RequestMapping(value = "/student/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean studentSearchGET(UStudentPVO student) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(student.getToken()) == false) {
				return tokenFail();
			}
			student = (UStudentPVO) uStudentService.findByIdOrNumberWithGrade(student);
			result.setData(student);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 学生(家长)转班接口 /qxy/student/move
	 */
	@RequestMapping(value = "/student/move", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean studentMovePUT(@RequestBody UserDBO user) {
		// TODO
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(user.getToken()) == false) {
				return tokenFail();
			}

			Long userId = getLoginer().getUserId();

			result.setInfo("欢迎访问千校云平台：" + userId + "," + user.getAccount());
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

}
