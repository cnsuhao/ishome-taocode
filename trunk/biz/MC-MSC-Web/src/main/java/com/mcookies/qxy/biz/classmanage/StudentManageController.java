package com.mcookies.qxy.biz.classmanage;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcookies.qxy.common.Class.ClassDBO;
import com.mcookies.qxy.common.Class.ClassPVO;
import com.mcookies.qxy.common.Class.ClassService;
import com.mcookies.qxy.common.ClassStudent.ClassStudentDBO;
import com.mcookies.qxy.common.ClassStudent.ClassStudentService;
import com.mcookies.qxy.common.UStudent.UStudentDBO;
import com.mcookies.qxy.common.UStudent.UStudentPVO;
import com.mcookies.qxy.common.UStudent.UStudentService;

/**
 * 班级管理-学生管理
 * 
 * @author linyh
 *
 */
@Controller
public class StudentManageController extends MyControllerSupport {

	@Resource
	protected ClassService classService;
	@Resource
	protected UStudentService uStudentService;
	@Resource
	protected ClassStudentService classStudentService;

	/**
	 * 学生列表搜索查询接口
	 * /qxy/stundent/list/class=[cid]&page=[page]&size=[size]&token=[token]
	 */
	@RequestMapping(value = "/student/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean studentListGET(ClassPVO pvo, Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(pvo.getToken()) == false) {
				return tokenFail();
			}
			Map<String, Object> data = new HashMap<String, Object>();
			if (page == null || page == 0) {
				page = 1;
			}
			if (size == null || size == 0) {
				size = 12;
			}
			// 查询是否存在
			if (pvo.getCid() == null) {
				throw new IllegalArgumentException("cid不能为空");
			}
			ClassDBO condition = new ClassDBO();
			condition.setCid(pvo.getCid());
			condition = (ClassDBO) classService.doRead(condition);
			if (condition == null) {
				throw new IllegalArgumentException("该班级不存在");
			}
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);
			pageModel.setFormParamBean(pvo);
			uStudentService.doSelectPageByCidWithClassAndGrade(pageModel);
			
			data.put("page", pageModel.getPageCurrent());
			data.put("size", pageModel.getPageLimit());
			data.put("count", pageModel.getResultCount());
			data.put("student", pageModel.getPageListData());
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
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
	public RESTResultBean studentMovePUT(@RequestBody ClassStudentDBO classStudent) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(classStudent.getToken()) == false) {
				return tokenFail();
			}
			// 查询班级是否存在
			if (classStudent.getCid() == null) {
				throw new IllegalArgumentException("cid不能为空");
			}
			ClassDBO clazz = new ClassDBO();
			clazz.setCid(classStudent.getCid());
			clazz = (ClassDBO) classService.doRead(clazz);
			if (clazz == null) {
				throw new IllegalArgumentException("cid对应的班级不存在");
			}
			// 查询学生是否存在
			if (classStudent.getStudentId() == null) {
				throw new IllegalArgumentException("studentId不能为空");
			}
			UStudentDBO student = new UStudentDBO();
			student.setStudentId(classStudent.getStudentId());
			student = (UStudentDBO) uStudentService.doRead(student);
			if (student == null) {
				throw new IllegalArgumentException("studentId对应的学生不存在");
			}
			classStudentService.updateCid(classStudent);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("修改失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

}
