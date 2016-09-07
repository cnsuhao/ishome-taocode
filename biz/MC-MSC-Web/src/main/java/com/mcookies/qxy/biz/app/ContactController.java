package com.mcookies.qxy.biz.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcookies.qxy.common.Class.ClassDBO;
import com.mcookies.qxy.common.Class.ClassPVO;
import com.mcookies.qxy.common.Class.ClassService;
import com.mcookies.qxy.common.ClassStudent.ClassStudentDBO;
import com.mcookies.qxy.common.SDuty.SDutyService;
import com.mcookies.qxy.common.SDutyContent.SDutyContentService;
import com.mcookies.qxy.common.SDutyScheduling.SDutySchedulingPVO;
import com.mcookies.qxy.common.SDutyScheduling.SDutySchedulingService;
import com.mcookies.qxy.common.SGradeLabel.SGradeLabelDBO;
import com.mcookies.qxy.common.SGradeLabel.SGradeLabelService;
import com.mcookies.qxy.common.STerm.STermPVO;
import com.mcookies.qxy.common.STerm.STermService;
import com.mcookies.qxy.common.UParent.UParentDBO;
import com.mcookies.qxy.common.UParent.UParentPVO;
import com.mcookies.qxy.common.UParent.UParentService;
import com.mcookies.qxy.common.UStudent.UStudentDBO;
import com.mcookies.qxy.common.UStudent.UStudentPVO;
import com.mcookies.qxy.common.UStudent.UStudentService;
import com.mcookies.qxy.common.UStudentParent.UStudentParentDBO;
import com.mcookies.qxy.common.UStudentParent.UStudentParentPVO;
import com.mcookies.qxy.common.UStudentParent.UStudentParentService;
import com.mcookies.qxy.common.UTeacher.UTeacherDBO;
import com.mcookies.qxy.common.UTeacher.UTeacherPVO;
import com.mcookies.qxy.common.UTeacher.UTeacherService;

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
	protected UStudentService uStudentService;
	@Resource
	protected UParentService uParentService;
	@Resource
	protected UStudentParentService uStudentParentService;
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
	public RESTResultBean classParentGET(UParentPVO pvo, Integer page, Integer size) {
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
			// 學期是否存在
			if (pvo.getTermId() != null) {

				STermPVO term = new STermPVO();
				term.setTermId(pvo.getTermId());
				term = (STermPVO) sTermService.findByTermId(term);
				if (term == null) {
					throw new IllegalArgumentException("termId所对应的学期或默认学期不存在");
				}
				data.put("termName", term.getTermName());
			}
			if (pvo.getCid() != null) {

				// 查询班級是否存在
				if (pvo.getCid() == null) {
					throw new IllegalArgumentException("cid不能为空");
				}
				ClassDBO condition = new ClassDBO();
				condition.setCid(pvo.getCid());
				condition = (ClassDBO) classService.doRead(condition);
				if (condition == null) {
					throw new IllegalArgumentException("该班级不存在");
				}
				data.put("cid", pvo.getCid());
				data.put("className", condition.getClassName());
				ClassPVO cp = classService.findClassersAndGrade(condition);
				data.put("gradeName", cp.getGradeName());
			}
			// 查询學生是否存在，可为空，则查全部
			if (pvo.getStudentId() != null) {
				UStudentDBO student = new UStudentDBO();
				student.setStudentId(pvo.getStudentId());
				student = (UStudentDBO) uStudentService.doRead(student);
				if (student == null) {
					throw new IllegalArgumentException("studentId对应的学生不存在");
				}
				// 查询学生关联的年级班级学期
				ClassStudentDBO clz = new ClassStudentDBO();
				clz.setStudentId(pvo.getStudentId());
				List<ClassPVO> clist = classService.findByTid(clz);
				Long cid = clist.get(0).getCid();
				Long termId = clist.get(0).getTermId();
				String termName = clist.get(0).getTermName();
				String gradeName = clist.get(0).getGradeName();
				String className = clist.get(0).getClassName();
				if (pvo.getTermId() == null) {
					pvo.setTermId(termId);
					data.put("termName", termName);
				}
				if (pvo.getCid() == null) {
					pvo.setCid(cid);
					data.put("cid", cid);
					data.put("className", className);
					data.put("gradeName", gradeName);
				}
			}

			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);
			pageModel.setFormParamBean(pvo);
			uParentService.doSelectPageByTermIdAndCidAndStudentId(pageModel);

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
	 * 班级学生查询家长详细信息
	 * class/parent/info?parentId=[parentId]&token=[token]
	 *GET
	*/
	@RequestMapping(value = "class/parent/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean studentParentInfo( Long parentId, String token) {
		RESTResultBean rs = new RESTResultBean();		
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			Map<String, Object> data = new HashMap<String, Object>();
			if(parentId == null){
				throw new IllegalArgumentException("parentId不能为空");				
			}
			UParentPVO parent = new UParentPVO();
			parent.setParentId(parentId);
			parent = uParentService.findByParentId(parent);
			if(parent == null ){
				rs.setInfo("您还不是家长");
				rs.setStatus(2);
				return rs;
			}
			data.put("parentId", parent.getParentId());
			data.put("parentName", parent.getParentName());
			data.put("role", parent.getRole());
			data.put("phone", parent.getPhone());
			data.put("workUnit", parent.getWorkUnit());
			data.put("position", parent.getPosition());
			
			UStudentParentPVO stu = new UStudentParentPVO();
			stu.setParentId(parentId);
			List<UStudentParentPVO> stulist = uStudentParentService.doFindByParentId(stu);
			data.put("studentinfo", stulist);
			rs.setData(data);
		} catch (Exception e) {
			rs.setInfo("查询失败，" + e.getMessage());
			rs.setStatus(1);
		}
		
		
	   return rs;	
	}	
	

	/**
	 * 家长角色对应学生所属班
	 * /parent/class/term=[term]&parent=[parent_id]&student=[student_id]&
	 * token=[token]
	 */
	@RequestMapping(value = "/parent/class", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean parentClassGET(UStudentPVO pvo, Integer page, Integer size) {
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
			// 查询學生是否存在，可为空，则查全部
			if (pvo.getStudentId() != null) {
				UStudentDBO student = new UStudentDBO();
				student.setStudentId(pvo.getStudentId());
				student = (UStudentDBO) uStudentService.doRead(student);
				if (student == null) {
					throw new IllegalArgumentException("studentId对应的学生不存在");
				}
			}

			// 家长是否存在
			if (pvo.getParentId() == null) {
				throw new IllegalArgumentException("parentId不能为空");
			}
			UParentDBO parent = new UParentDBO();
			parent.setParentId(pvo.getParentId());
			parent = (UParentDBO) uParentService.doRead(parent);
			if (parent == null) {
				throw new IllegalArgumentException("parentId所对应的家长不存在");
			}
			// 學期是否存在
			STermPVO term = new STermPVO();
			term.setTermId(pvo.getTermId());
			term = (STermPVO) sTermService.findByTermId(term);
			if (term == null) {
				throw new IllegalArgumentException("termId所对应的学期或默认学期不存在");
			}
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);
			pageModel.setFormParamBean(pvo);
			uStudentService.doSelectPageByParentIdAndTermIdAndStudentID(pageModel);

			data.put("parentId", parent.getParentId());
			data.put("parentName", parent.getParentName());
			data.put("termName", term.getTermName());
			data.put("page", pageModel.getPageCurrent());
			data.put("size", pageModel.getPageLimit());
			data.put("count", pageModel.getResultCount());
			data.put("studentlist", pageModel.getPageListData());
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 值周工作（手机端）安排查询接口
	 * /qxy/schedule/mobile/week?termId=[termId]&week=[week]&tid=[tid]&token=[
	 * token]
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
			List<SDutySchedulingPVO> dutySchedulings = (List<SDutySchedulingPVO>) sDutySchedulingService.findByTermIdAndTidAndWeekAndIsUsed(dutyScheduling);
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
	 * /qxy/schedule/mobile/day?termId=[termId]&date=[date]&tid=[tid]&token=[
	 * token]
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
			List<SDutySchedulingPVO> dutySchedulings = (List<SDutySchedulingPVO>) sDutySchedulingService.findByTermIdAndTidAndDateAndIsUsed(dutyScheduling);
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
