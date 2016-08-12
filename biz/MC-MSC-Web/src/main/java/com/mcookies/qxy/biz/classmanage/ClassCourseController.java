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
import com.mcookies.qxy.common.ClassCourse.ClassCourseDBO;
import com.mcookies.qxy.common.ClassCourse.ClassCoursePVO;
import com.mcookies.qxy.common.ClassCourse.ClassCourseService;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherDBO;
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
	protected ClassCourseService classCourseService;
	@Resource
	protected UTeacherService uTeacherService;
	@Resource
	protected ClassTeacherService classTeacherService;

	/**
	 * 班级课程查询接口 /class/course/cid=[cid]&token=[token]
	 */
	@SuppressWarnings("unchecked")
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
			ClassCourseDBO classCourse = new ClassCourseDBO();
			classCourse.setCid(cid);
			List<ClassCoursePVO> classCourses = (List<ClassCoursePVO>) classCourseService.doSelectData(classCourse);
			for (ClassCoursePVO classCoursePVO : classCourses) {
				if (leader != null && classCoursePVO.getTid() != null 
						&& classCoursePVO.getTid() == leader.getTid()) {
					classCoursePVO.setIsLeader(1);
				} else {
					classCoursePVO.setIsLeader(0);
				}
			}
			data.put("cid", cid);
			data.put("className", clazz.getClassName());
			data.put("leaderName", leader != null ? leader.getTeacherName() : "");
			data.put("count", classCourses.size());
			data.put("course", classCourses);
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
	public RESTResultBean classCoursePOST(@RequestBody ClassCourseDBO classCourse) {
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
			if (classCourse.getCourseId() == null) {
				throw new IllegalArgumentException("courseId不能为空");
			}
			SCourseDBO course = new SCourseDBO();
			course.setCourseId(classCourse.getCourseId());
			course = (SCourseDBO) sCourseService.doRead(course);
			if (course == null) {
				throw new IllegalArgumentException("courseId所对应的课程不存在");
			}
			classCourse.setCourseName(course.getCourseName());
			classCourse.setTerm(clazz.getTermId());
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

	/**
	 * 班级课程修改接口 /class/course
	 */
	@RequestMapping(value = "/class/course", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classCoursePUT(@RequestBody ClassCourseDBO classCourse) {
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
			if (classCourse.getCourseId() == null) {
				throw new IllegalArgumentException("courseId不能为空");
			}
			SCourseDBO course = new SCourseDBO();
			course.setCourseId(classCourse.getCourseId());
			course = (SCourseDBO) sCourseService.doRead(course);
			if (course == null) {
				throw new IllegalArgumentException("courseId所对应的课程不存在");
			}
			classCourse.setCourseName(course.getCourseName());
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

	/**
	 * 班级课程教师指定及修改接口 /class/teacher
	 */
	@RequestMapping(value = "/class/teacher", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classTeacherPUT(@RequestBody ClassCourseDBO classCourse) {
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
			
			if (classCourse.getTid() == null) {
				throw new IllegalArgumentException("班级课程教师tid不能为空");
			}
			UTeacherDBO teacher = new UTeacherDBO();
			teacher.setTid(classCourse.getTid());
			teacher = (UTeacherDBO) uTeacherService.doRead(teacher);
			if (teacher == null) {
				throw new IllegalArgumentException("tid所对应的教师不存在");
			}
			classCourse.setTeacherName(teacher.getTeacherName());
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

	/**
	 * 班级课程删除接口 /class/course
	 */
	@RequestMapping(value = "/class/course", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classCourseDELETE(@RequestBody ClassCourseDBO classCourse) {
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
	}

}
