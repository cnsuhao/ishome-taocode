package com.mcookies.qxy.biz.app;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcookies.qxy.common.Class.ClassDBO;
import com.mcookies.qxy.common.Class.ClassPVO;
import com.mcookies.qxy.common.Class.ClassService;
import com.mcookies.qxy.common.SDuty.SDutyService;
import com.mcookies.qxy.common.SDutyContent.SDutyContentService;
import com.mcookies.qxy.common.SDutyScheduling.SDutySchedulingPVO;
import com.mcookies.qxy.common.SDutyScheduling.SDutySchedulingService;
import com.mcookies.qxy.common.SGradeLabel.SGradeLabelDBO;
import com.mcookies.qxy.common.SGradeLabel.SGradeLabelService;
import com.mcookies.qxy.common.STerm.STermPVO;
import com.mcookies.qxy.common.STerm.STermService;
import com.mcookies.qxy.common.UTeacher.UTeacherDBO;
import com.mcookies.qxy.common.UTeacher.UTeacherPVO;
import com.mcookies.qxy.common.UTeacher.UTeacherService;
import com.mcookies.qxy.common.User.UserDBO;
import com.mcookies.qxy.utils.DateUtils;

/**
 * 手机端-通讯录
 * 
 * @author linyh
 *
 */
@Controller
public class ContactController extends MyControllerSupport {
	@Resource
	protected STermService sTermService;
	@Resource
	protected SDutyService sDutyService;
	@Resource
	protected SDutyContentService sDutyContentService;
	@Resource
	protected SDutySchedulingService sDutySchedulingService;
	@Resource
	protected UTeacherService uTeacherService;
	@Resource
	protected ClassService classService;
	@Resource
	protected SGradeLabelService sGradeLabelService;

	/**
	 * 教师查询任课班级 /teacher/class/term=[term]&teacher=[tid]&token=[token]
	 */
	@RequestMapping(value = "/teacher/class", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean teacherClassGET(UTeacherPVO teacher) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(teacher.getToken()) == false) {
				return tokenFail();
			}
			if (teacher.getTid() == null) {
				throw new IllegalArgumentException("tid不能为空");
			}
			UTeacherDBO dbo = new UTeacherDBO();
			dbo.setTid(teacher.getTid());
			dbo = (UTeacherDBO) uTeacherService.doRead(dbo);
			if (dbo == null) {
				throw new IllegalArgumentException("tid所对应的教师不存在");
			}
			Map<String, Object> data = new HashMap<String, Object>();
			STermPVO term = new STermPVO();
			term.setTermId(teacher.getTermId());
			term = (STermPVO) sTermService.findByTermId(term);
			if (term == null) {
				throw new IllegalArgumentException("termId所对应的学期或默认学期不存在");
			}
			ClassPVO clz = new ClassPVO();
			clz.setTermId(term.getTermId());
			clz.setTid(dbo.getTid());
			List<ClassPVO> classes = (List<ClassPVO>) classService.findByTermIdAndTid(clz);
			data.put("tid", dbo.getTid());
			data.put("teacherName", dbo.getTeacherName());
			data.put("count", classes.size());
			data.put("classlist", classes);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 班级查询任课老师 /class/teacher/term=[term]&class=[cid]&token=[token]
	 */
	@RequestMapping(value = "/class/teacher", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classTeacherGET(ClassPVO clazz) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(clazz.getToken()) == false) {
				return tokenFail();
			}
			if (clazz.getCid() == null) {
				throw new IllegalArgumentException("cid不能为空");
			}
			ClassDBO dbo = new ClassDBO();
			dbo.setCid(clazz.getCid());
			dbo = (ClassDBO) classService.doRead(dbo);
			if (dbo == null) {
				throw new IllegalArgumentException("cid所对应的班级不存在");
			}
			Map<String, Object> data = new HashMap<String, Object>();
			STermPVO term = new STermPVO();
			term.setTermId(clazz.getTermId());
			term = (STermPVO) sTermService.findByTermId(term);
			if (term == null) {
				throw new IllegalArgumentException("termId所对应的学期或默认学期不存在");
			}
			SGradeLabelDBO grade = new SGradeLabelDBO();
			grade.setGradeId(dbo.getGradeId());
			grade = (SGradeLabelDBO) sGradeLabelService.doRead(grade);
			
			UTeacherPVO teacher = new UTeacherPVO();
			teacher.setTermId(term.getTermId());
			teacher.setCid(dbo.getCid());
			List<UTeacherPVO> teachers = (List<UTeacherPVO>) uTeacherService.findByTermIdAndCid(teacher);
			data.put("cid", dbo.getCid());
			data.put("className", dbo.getClassName());
			data.put("termName", term.getTermName());
			if (grade != null) {
				data.put("graderName", grade.getGradeName());
			}
			data.put("count", teachers.size());
			data.put("teacherlist", teachers);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 班级查询家长通讯录
	 * /class/parent/term=[term]&class=[cid]&student=[student_id]&page=[page
	 * ]&size=[size]&token=[token]
	 */
	@RequestMapping(value = "/class/parent", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classParentGET(@RequestBody UserDBO user) {
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
	 * 家长角色对应学生所属班
	 * /parent/class/term=[term]&parent=[parent_id]&student=[student_id]&
	 * token=[token]
	 */
	@RequestMapping(value = "/parent/class", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean parentClassGET(@RequestBody UserDBO user) {
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
	 * 值周工作（手机端）安排查询接口
	 * /qxy/schedule/mobile/week?termId=[termId]&week=[week]&tid=[tid]&token=[token]
	 */
	@RequestMapping(value = "/schedule/mobile/week", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean scheduleMobileWeekGET(SDutySchedulingPVO dutyScheduling) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(dutyScheduling.getToken()) == false) {
				return tokenFail();
			}
			if (dutyScheduling.getWeek() == null) {
				throw new IllegalArgumentException("week不能为空");
			}
			Map<String, Object> data = new HashMap<String, Object>();
			STermPVO term = new STermPVO();
			term.setTermId(dutyScheduling.getTermId());
			term.setSid(dutyScheduling.getSid());
			term = (STermPVO) sTermService.findByTermId(term);
			if (term == null) {
				throw new IllegalArgumentException("termId所对应的学期或默认学期不存在");
			}
			dutyScheduling.setTermId(term.getTermId());
			List<SDutySchedulingPVO> dutySchedulings = (List<SDutySchedulingPVO>) sDutySchedulingService
					.findByTermIdAndTidAndWeekAndIsUsed(dutyScheduling);
			data.put("week", dutyScheduling.getWeek());
			data.put("startTime", term.getStartTime());
			data.put("endTime", term.getEndTime());
			data.put("count", dutySchedulings.size());
			data.put("scheduleweek", dutySchedulings);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}
	
	/**
	 * 值日工作安排查询接口
	 * /qxy/schedule/mobile/day?termId=[termId]&date=[date]&tid=[tid]&token=[token]
	 */
	@RequestMapping(value = "/schedule/mobile/day", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean scheduleMobileDayGET(SDutySchedulingPVO dutyScheduling) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(dutyScheduling.getToken()) == false) {
				return tokenFail();
			}
			if (dutyScheduling.getDate() == null) {
				throw new IllegalArgumentException("date不能为空");
			}
			Map<String, Object> data = new HashMap<String, Object>();
			STermPVO term = new STermPVO();
			term.setTermId(dutyScheduling.getTermId());
			term.setSid(dutyScheduling.getSid());
			term = (STermPVO) sTermService.findByTermId(term);
			if (term == null) {
				throw new IllegalArgumentException("termId所对应的学期或默认学期不存在");
			}
			dutyScheduling.setTermId(term.getTermId());
			dutyScheduling.setTermId(term.getTermId());
			List<SDutySchedulingPVO> dutySchedulings = (List<SDutySchedulingPVO>) sDutySchedulingService
					.findByTermIdAndTidAndDateAndIsUsed(dutyScheduling);
			data.put("date", dutyScheduling.getDate());
			data.put("count", dutySchedulings.size());
			data.put("scheduleday", dutySchedulings);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

}
