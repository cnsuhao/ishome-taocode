package com.mcookies.qxy.biz.classmanage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcookies.qxy.common.Class.ClassDBO;
import com.mcookies.qxy.common.Class.ClassService;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherDBO;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherPVO;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherService;
import com.mcookies.qxy.common.SCourse.SCourseDBO;
import com.mcookies.qxy.common.SCourse.SCourseService;
import com.mcookies.qxy.common.STerm.STermService;
import com.mcookies.qxy.common.UTeacher.UTeacherDBO;
import com.mcookies.qxy.common.UTeacher.UTeacherService;

/**
 * 班级管理-班级管理
 * 
 * @author linyh
 *
 */
@Controller("ClassCourseController")
public class ClassCourseController extends MyControllerSupport {

	@Resource
	protected ClassService classService;
	@Resource
	protected SCourseService sCourseService;
	@Resource
	protected STermService sTermService;
	@Resource
	protected UTeacherService uTeacherService;
	@Resource
	protected ClassTeacherService classTeacherService;

	/**
	 * 班级课程查询接口 /class/course/cid=[cid]&token=[token]
	 */
	@RequestMapping(value = "/class/course", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classCourseGET(@RequestParam(required = false) String token,
			@RequestParam(required = false) Long cid) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if (cid == null) {
				throw new IllegalArgumentException("cid不能为空");
			}
			Map<String, Object> data = new HashMap<String, Object>();
			ClassDBO clazz = new ClassDBO();
			clazz.setCid(cid);
			clazz = (ClassDBO) classService.doRead(clazz);
			if (clazz == null) {
				throw new IllegalArgumentException("cid所对应的班级不存在");
			}
			ClassTeacherDBO classTeacher = new ClassTeacherDBO();
			classTeacher.setCid(cid);
			UTeacherDBO leader = (UTeacherDBO) uTeacherService.findClassLeader(classTeacher);
			List<ClassTeacherPVO> classTeachers = (List<ClassTeacherPVO>) classTeacherService.findByCid(classTeacher);
			data.put("cid", cid);
			data.put("className", clazz.getClassName());
			data.put("leaderName", leader != null ? leader.getTeacherName() : "");
			data.put("count", classTeachers.size());
			data.put("course", classTeachers);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 班级课程新增接口 /class/course
	 */
	@RequestMapping(value = "/class/course", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classCoursePOST(String token, @RequestBody ClassTeacherDBO classTeacher) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if (classTeacher.getCid() == null) {
				throw new IllegalArgumentException("cid不能为空");
			}
			ClassDBO clazz = new ClassDBO();
			clazz.setCid(classTeacher.getCid());
			clazz = (ClassDBO) classService.doRead(clazz);
			if (clazz == null) {
				throw new IllegalArgumentException("cid所对应的班级不存在");
			}
			if (classTeacher.getCourseId() == null) {
				throw new IllegalArgumentException("courseId不能为空");
			}
			SCourseDBO course = new SCourseDBO();
			course.setCourseId(classTeacher.getCourseId());
			course = (SCourseDBO) sCourseService.doRead(course);
			if (course == null) {
				throw new IllegalArgumentException("courseId所对应的课程不存在");
			}
			if (classTeacher.getIsUse() == null) {
				classTeacher.setIsUse(1);
			}
			classTeacherService.doInsert(classTeacher);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("新增失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 班级课程修改接口 /class/course
	 */
	@RequestMapping(value = "/class/course", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classCoursePUT(String token, @RequestBody ClassTeacherDBO classTeacher) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			// 查询是否存在
			if (classTeacher.getId() == null) {
				throw new IllegalArgumentException("班级课程id不能为空");
			}
			ClassTeacherDBO origin = new ClassTeacherDBO();
			origin.setId(classTeacher.getId());
			origin = (ClassTeacherDBO) classTeacherService.doRead(origin);
			if (origin == null) {
				throw new IllegalArgumentException("班级课程不存在");
			}
			if (classTeacher.getCourseId() == null) {
				throw new IllegalArgumentException("courseId不能为空");
			}
			SCourseDBO course = new SCourseDBO();
			course.setCourseId(classTeacher.getCourseId());
			course = (SCourseDBO) sCourseService.doRead(course);
			if (course == null) {
				throw new IllegalArgumentException("courseId所对应的课程不存在");
			}
			classTeacherService.doUpdate(classTeacher);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("修改失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 班级课程教师指定及修改接口 /class/teacher
	 */
	@RequestMapping(value = "/class/teacher", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classTeacherPUT(String token, @RequestBody ClassTeacherDBO classTeacher) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			// 查询是否存在
			if (classTeacher.getId() == null) {
				throw new IllegalArgumentException("班级课程id不能为空");
			}
			ClassTeacherDBO origin = new ClassTeacherDBO();
			origin.setId(classTeacher.getId());
			origin = (ClassTeacherDBO) classTeacherService.doRead(origin);
			if (origin == null) {
				throw new IllegalArgumentException("班级课程不存在");
			}
			
			if (classTeacher.getTid() == null) {
				throw new IllegalArgumentException("班级课程教师tid不能为空");
			}
			UTeacherDBO teacher = new UTeacherDBO();
			teacher.setTid(classTeacher.getTid());
			teacher = (UTeacherDBO) uTeacherService.doRead(teacher);
			if (teacher == null) {
				throw new IllegalArgumentException("tid所对应的教师不存在");
			}
			if (classTeacher.getIsLeader() == null) {
				classTeacher.setIsLeader(0);
			}
			classTeacherService.doUpdate(classTeacher);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("修改失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 班级课程删除接口 /class/course
	 */
	@RequestMapping(value = "/class/course", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classCourseDELETE(String token, @RequestBody ClassTeacherDBO classTeacher) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			// 查询是否存在
			if (classTeacher.getId() == null) {
				throw new IllegalArgumentException("班级课程id不能为空");
			}
			ClassTeacherDBO origin = new ClassTeacherDBO();
			origin.setId(classTeacher.getId());
			origin = (ClassTeacherDBO) classTeacherService.doRead(origin);
			if (origin == null) {
				throw new IllegalArgumentException("班级课程不存在");
			}
			classTeacherService.doDelete(origin);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("删除失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}

}
