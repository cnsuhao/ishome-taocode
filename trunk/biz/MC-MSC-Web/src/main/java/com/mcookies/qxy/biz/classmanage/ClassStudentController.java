package com.mcookies.qxy.biz.classmanage;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcookies.qxy.common.Class.ClassDBO;
import com.mcookies.qxy.common.ClassCourse.ClassCourseDBO;
import com.mcookies.qxy.common.SCourse.SCourseDBO;
import com.mcookies.qxy.common.User.UserDBO;

/**
 * 班级管理-班级学生
 * 
 * @author linyh
 *
 */
@Controller("ClassStudentController")
public class ClassStudentController extends MyControllerSupport {
/*
	@Resource
	protected ICacheService myCacheService;

	*//**
	 * 班级学生(家长)列表查询接口
	 * /qxy/class/student/list/cid=[cid]&page=[page]&size=[size]&token=[token]
	 *//*
	@RequestMapping(value = "/qxy/class/student/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classStudentListGET(@RequestBody UserDBO user) {
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

	*//**
	 * 班级学生(家长)搜索接口
	 * /qxy/class/student/search/cid=[cid]&number=[number]&token=[token]
	 *//*
	@RequestMapping(value = "/qxy/class/student/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classStudentSearchGET(@RequestBody UserDBO user) {
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

	*//**
	 * 班级学生(家长)信息查询接口
	 * /qxy/class/student/info/cid=[cid]&student=[student_id]&token=[token]
	 *//*
	@RequestMapping(value = "/qxy/class/student/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classStudentInfoGET(@RequestBody UserDBO user) {
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

	*//**
	 * 班级学生(家长)新增接口 /qxy/class/student/info
	 *//*
	@RequestMapping(value = "/qxy/class/student/info", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classStudentInfoPOST(@RequestBody ClassCourseDBO classCourse) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(classCourse.getToken()) == false) {
				return tokenFail();
			}
			if (classCourse.getCid() == null) {
				throw new IllegalArgumentException("cid不能为空");
			}
			ClassDBO clazz = new ClassDBO();
			clazz.setCid(classCourse.getCid());
			clazz = (ClassDBO) classService.doRead(clazz);
			if (clazz == null) {
				throw new IllegalArgumentException("cid所对应的班级不存在");
			}
			if (classCourse.getCourseId1() == null) {
				throw new IllegalArgumentException("courseId不能为空");
			}
			SCourseDBO course = new SCourseDBO();
			course.setCourseId(classCourse.getCourseId1());
			course = (SCourseDBO) sCourseService.doRead(course);
			if (course == null) {
				throw new IllegalArgumentException("courseId所对应的课程不存在");
			}
			classCourseService.doInsert(classCourse);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("新增失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	*//**
	 * 班级学生(家长)修改接口 /qxy/class/student/info
	 *//*
	@RequestMapping(value = "/qxy/class/student/info", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classStudentInfoPUT(@RequestBody ClassCourseDBO classCourse) {
		// TODO: test
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(classCourse.getToken()) == false) {
				return tokenFail();
			}
			// 查询是否存在
			if (classCourse.getId() == null) {
				throw new IllegalArgumentException("班级课程id不能为空");
			}
			ClassCourseDBO origin = new ClassCourseDBO();
			origin.setId(classCourse.getId());
			origin = (ClassCourseDBO) classCourseService.doRead(origin);
			if (origin == null) {
				throw new IllegalArgumentException("班级课程不存在");
			}
			if (classCourse.getCid() != null) {
				ClassDBO clazz = new ClassDBO();
				clazz.setCid(classCourse.getCid());
				clazz = (ClassDBO) classService.doRead(clazz);
				if (clazz == null) {
					throw new IllegalArgumentException("cid所对应的班级不存在");
				}
			}
			if (classCourse.getCourseId1() != null) {
				SCourseDBO course = new SCourseDBO();
				course.setCourseId(classCourse.getCourseId1());
				course = (SCourseDBO) sCourseService.doRead(course);
				if (course == null) {
					throw new IllegalArgumentException("courseId所对应的课程不存在");
				}
			}
			classCourseService.doUpdate(classCourse);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("修改失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	*//**
	 * 班级学生(家长)删除接口 /qxy/class/student/info
	 *//*
	@RequestMapping(value = "/qxy/class/student/info", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classStudentInfoDELETE(@RequestBody ClassCourseDBO classCourse) {
		// TODO: test
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(classCourse.getToken()) == false) {
				return tokenFail();
			}
			// 查询是否存在
			if (classCourse.getId() == null) {
				throw new IllegalArgumentException("班级课程id不能为空");
			}
			ClassCourseDBO origin = new ClassCourseDBO();
			origin.setId(classCourse.getId());
			origin = (ClassCourseDBO) classCourseService.doRead(origin);
			if (origin == null) {
				throw new IllegalArgumentException("班级课程不存在");
			}
			classCourseService.doDelete(origin);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("删除失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}*/

}
