package com.mcookies.qxy.biz.classmanage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.security.value.MD5SecurityHelper;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.isotope.jfp.framework.utils.DateHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcookies.qxy.common.Class.ClassDBO;
import com.mcookies.qxy.common.Class.ClassService;
import com.mcookies.qxy.common.ClassStudent.ClassStudentDBO;
import com.mcookies.qxy.common.ClassStudent.ClassStudentService;
import com.mcookies.qxy.common.UParent.UParentService;
import com.mcookies.qxy.common.UStudent.UStudentDBO;
import com.mcookies.qxy.common.UStudent.UStudentPVO;
import com.mcookies.qxy.common.UStudent.UStudentService;
import com.mcookies.qxy.common.UStudentExt.UStudentExtDBO;
import com.mcookies.qxy.common.UStudentExt.UStudentExtService;
import com.mcookies.qxy.common.UStudentParent.UStudentParentDBO;
import com.mcookies.qxy.common.UStudentParent.UStudentParentService;
import com.mcookies.qxy.common.User.UserDBO;
import com.mcookies.qxy.common.User.UserService;

/**
 * 班级管理-班级学生
 * 
 * @author linyh
 *
 */
@Controller("ClassStudentController")
public class ClassStudentController extends MyControllerSupport {

	@Resource
	protected ClassService classService;
	@Resource
	protected UserService userService;
	@Resource
	protected UStudentService uStudentService;
	@Resource
	protected UStudentExtService uStudentExtService;
	@Resource
	protected UParentService uParentService;
	@Resource
	protected ClassStudentService classStudentService;
	@Resource
	protected UStudentParentService uStudentParentService;

	/**
	 * 班级学生列表查询接口
	 * /qxy/class/student/list/cid=[cid]&page=[page]&size=[size]&token=[token]
	 */
	@RequestMapping(value = "/class/student/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
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

	/**
	 * 班级学生搜索接口
	 * /qxy/class/student/search?studentId=[studentId]&number=[number]&token=[token]
	 */
	@RequestMapping(value = "/class/student/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classStudentSearchGET(UStudentPVO student) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(student.getToken()) == false) {
				return tokenFail();
			}
			student = (UStudentPVO) uStudentService.findByIdOrNumber(student);
			result.setData(student);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 班级学生信息查询接口
	 * /qxy/class/student/info/cid=[cid]&student=[student_id]&token=[token]
	 */
	@RequestMapping(value = "/class/student/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classStudentInfoGET(UStudentPVO student) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(student.getToken()) == false) {
				return tokenFail();
			}
			// 查询是否存在
			if (student.getStudentId() == null) {
				throw new IllegalArgumentException("学生id不能为空");
			}
			UStudentDBO origin = new UStudentDBO();
			origin.setStudentId(student.getStudentId());
			origin = (UStudentDBO) uStudentService.doRead(origin);
			if (origin == null) {
				throw new IllegalArgumentException("学生不存在");
			}
			// 扩展信息
			UStudentExtDBO ext = new UStudentExtDBO();
			ext.setStudentId(student.getStudentId());
			ext = (UStudentExtDBO) uStudentExtService.doRead(ext);
			if (ext == null) {
				throw new IllegalStateException("学生扩展信息不存在");
			}
			UStudentPVO pvo = new UStudentPVO();
			pvo.setStudentId(origin.getStudentId());
			pvo.setPhoto(ext.getPhoto());
			pvo.setStudentName(origin.getStudentName());
			pvo.setEmail(origin.getEmail());
			pvo.setNumber(origin.getNumber());
			pvo.setPhone(origin.getPhone());
			pvo.setDateOfSchool(ext.getDateOfSchool());
			pvo.setStudentType(ext.getStudentType());
			pvo.setNation(ext.getNation());
			pvo.setNativePlace(ext.getNativePlace());
			pvo.setCardType(ext.getCardType());
			pvo.setCardNumber(ext.getCardNumber());
			pvo.setPolitical(ext.getPolitical());
			pvo.setIsOverseas(ext.getIsOverseas());
			pvo.setDateOfBirth(ext.getDateOfBirth());
			pvo.setResidenceType(ext.getResidenceType());
			pvo.setResidenceAddress(ext.getResidenceAddress());
			result.setData(pvo);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 班级学生新增接口 /qxy/class/student/info
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/class/student/info", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	@Transactional
	public RESTResultBean classStudentInfoPOST(@RequestBody UStudentPVO student) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(student.getToken()) == false) {
				return tokenFail();
			}
			if (student.getPhone() == null) {
				throw new IllegalArgumentException("手机号码不能为空");
			}
			if (student.getEmail() == null) {
				throw new IllegalArgumentException("电子邮箱不能为空");
			}
			if (student.getNumber() == null) {
				throw new IllegalArgumentException("学号不能为空");
			}
			if (student.getCid() == null) {
				throw new IllegalArgumentException("cid不能为空");
			}
			ClassDBO clazz = new ClassDBO();
			clazz.setCid(student.getCid());
			clazz = (ClassDBO) classService.doRead(clazz);
			if (clazz == null) {
				throw new IllegalArgumentException("班级不存在");
			}
			Long uid = null;
			Long studentId = null;
			// 检查手机号码、邮箱是否被使用
			UStudentDBO stu = new UStudentDBO();
			stu.setPhone(student.getPhone());
			List<UStudentDBO> stus = (List<UStudentDBO>) uStudentService.doSelectData(stu);
			if (stus != null && stus.size() > 0) {
				result.setInfo("手机号码已经存在");
				result.setStatus(2);
				return result;
			}
			stu = new UStudentDBO();
			stu.setEmail(student.getEmail());
			stus = (List<UStudentDBO>) uStudentService.doSelectData(stu);
			if (stus != null && stus.size() > 0) {
				result.setInfo("邮箱已经存在");
				result.setStatus(2);
				return result;
			}
			stu = new UStudentDBO();
			stu.setSid(student.getSid()); // 学校中学号唯一即可
			stu.setNumber(student.getNumber());
			stus = (List<UStudentDBO>) uStudentService.doSelectData(stu);
			if (stus != null && stus.size() > 0) {
				result.setInfo("学号已经存在");
				result.setStatus(2);
				return result;
			}
			// 匹配用户表
			UserDBO userp = new UserDBO();
			userp.setPhone(student.getPhone());
			List<UserDBO> plist = (List<UserDBO>) userService.doSelectData(userp);

			UserDBO usere = new UserDBO();
			usere.setEmail(student.getEmail());
			List<UserDBO> elist = (List<UserDBO>) userService.doSelectData(usere);
			// 存在则直接获取uid
			if (plist != null && plist.size() > 0) {
				uid = plist.get(0).getUid();
			} else if (elist != null && elist.size() > 0) {
				uid = elist.get(0).getUid();
			} else {
				// 新建
				UserDBO user = new UserDBO();
				user.setAccount(student.getPhone());
				user.setEmail(student.getEmail());
				user.setEmailStatus(1);
				user.setPhone(student.getPhone());
				user.setPassword(MD5SecurityHelper.encrypt("qxy123456"));
				user.setStatus(1);
				userService.doInsert(user);
				uid = user.getUid();
			}
			// 插入学生表
			UStudentDBO theStu = new UStudentDBO();
			theStu.setStudentName(student.getStudentName());
			theStu.setSid(student.getSid());
			theStu.setUid(uid);
			theStu.setNumber(student.getNumber());
			theStu.setPhone(student.getPhone());
			theStu.setEmail(student.getEmail());
			theStu.setCity(student.getCity());
			theStu.setGender(student.getGender());
			theStu.setStatus(1);
			theStu.setIsUse(1);
			uStudentService.doInsert(theStu);
			studentId = theStu.getStudentId();
			// 插入扩展表
			UStudentExtDBO ext = new UStudentExtDBO();
			ext.setStudentId(studentId);
			ext.setPhoto(student.getPhone());
			ext.setNation(student.getNation());
			ext.setNativePlace(student.getNativePlace());
			ext.setCardType(student.getCardType());
			ext.setCardNumber(student.getCardNumber());
			ext.setIsOverseas(student.getIsOverseas());
			ext.setPolitical(student.getPolitical());
			ext.setFith(student.getFith());
			ext.setHealth(student.getHealth());
			ext.setBlood(student.getBlood());
			ext.setDateOfSchool(student.getDateOfSchool());
			ext.setDateOfBirth(student.getDateOfBirth());
			ext.setStudentType(student.getStudentType());
			ext.setResidenceType(student.getResidenceType());
			ext.setResidenceAddress(student.getResidenceAddress());
			ext.setIsUse(1);
			uStudentExtService.doInsert(ext);
			// 插入班级学生表
			ClassStudentDBO classStudent = new ClassStudentDBO();
			classStudent.setCid(clazz.getCid());
			classStudent.setJoinTime(DateHelper.currentTimeMillis2());
			classStudent.setStudentId(studentId);
			classStudent.setIsUse(1);
			classStudentService.doInsert(classStudent);
		} catch (Exception e) {
			result.setInfo("新增失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 班级学生修改接口 /qxy/class/student/info
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/class/student/info", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	@Transactional
	public RESTResultBean classStudentInfoPUT(@RequestBody UStudentPVO student) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(student.getToken()) == false) {
				return tokenFail();
			}
			// 查询是否存在
			if (student.getStudentId() == null) {
				throw new IllegalArgumentException("学生id不能为空");
			}
			UStudentDBO origin = new UStudentDBO();
			origin.setStudentId(student.getStudentId());
			origin = (UStudentDBO) uStudentService.doRead(origin);
			if (origin == null) {
				throw new IllegalArgumentException("学生不存在");
			}
			if (student.getCid() != null) {
				throw new IllegalArgumentException("不能修改学生所属班级");
			}
			// 检查手机号码、邮箱是否被使用
			if (!StringUtils.isEmpty(student.getPhone())) {
				UStudentDBO stu = new UStudentDBO();
				stu.setPhone(student.getPhone());
				List<UStudentDBO> stus = (List<UStudentDBO>) uStudentService.doSelectData(stu);
				if (stus != null && stus.size() > 0 && stus.get(0).getStudentId() != student.getStudentId()) {
					result.setInfo("手机号码已经存在");
					result.setStatus(2);
					return result;
				}
			}
			if (!StringUtils.isEmpty(student.getEmail())) {
				UStudentDBO stu = new UStudentDBO();
				stu.setEmail(student.getEmail());
				List<UStudentDBO> stus = (List<UStudentDBO>) uStudentService.doSelectData(stu);
				if (stus != null && stus.size() > 0 && stus.get(0).getStudentId() != student.getStudentId()) {
					result.setInfo("邮箱已经存在");
					result.setStatus(2);
					return result;
				}
			}
			if (student.getNumber() != null) {
				UStudentDBO stu = new UStudentDBO();
				stu.setSid(student.getSid()); // 学校中学号唯一即可
				stu.setNumber(student.getNumber());
				List<UStudentDBO> stus = (List<UStudentDBO>) uStudentService.doSelectData(stu);
				if (stus != null && stus.size() > 0 && stus.get(0).getStudentId() != student.getStudentId()) {
					result.setInfo("学号已经存在");
					result.setStatus(2);
					return result;
				}
			}
			uStudentService.doUpdate(student);
			UStudentExtDBO ext = new UStudentExtDBO();
			ext.setStudentId(student.getStudentId());
			ext.setPhoto(student.getPhone());
			ext.setNation(student.getNation());
			ext.setNativePlace(student.getNativePlace());
			ext.setCardType(student.getCardType());
			ext.setCardNumber(student.getCardNumber());
			ext.setIsOverseas(student.getIsOverseas());
			ext.setPolitical(student.getPolitical());
			ext.setFith(student.getFith());
			ext.setHealth(student.getHealth());
			ext.setBlood(student.getBlood());
			ext.setDateOfSchool(student.getDateOfSchool());
			ext.setDateOfBirth(student.getDateOfBirth());
			ext.setStudentType(student.getStudentType());
			ext.setResidenceType(student.getResidenceType());
			ext.setResidenceAddress(student.getResidenceAddress());
			uStudentExtService.doUpdate(ext);
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
	 * 班级学生删除接口 /qxy/class/student/info
	 */
	@RequestMapping(value = "/class/student/info", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	@Transactional
	public RESTResultBean classStudentInfoDELETE(@RequestBody UStudentPVO student) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(student.getToken()) == false) {
				return tokenFail();
			}
			// 查询是否存在
			if (student.getStudentId() == null) {
				throw new IllegalArgumentException("学生id不能为空");
			}
			UStudentDBO origin = new UStudentDBO();
			origin.setStudentId(student.getStudentId());
			origin = (UStudentDBO) uStudentService.doRead(origin);
			if (origin == null) {
				throw new IllegalArgumentException("学生不存在");
			}
			
			// 主表
			uStudentService.doDelete(origin);
			
			// 扩展表
			UStudentExtDBO ext = new UStudentExtDBO();
			ext.setStudentId(student.getStudentId());
			uStudentExtService.doDelete(ext);
			
			// 班级学生关联表
			ClassStudentDBO classStudent = new ClassStudentDBO();
			classStudent.setStudentId(student.getStudentId());
			classStudentService.deleteByStudentId(classStudent);
			
			// 学生家长关联表
			UStudentParentDBO studentParent = new UStudentParentDBO();
			studentParent.setStudentId(student.getStudentId());
			uStudentParentService.deleteByStudentId(studentParent);
			
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("删除失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 班级学生家长列表查询接口
	 * /qxy/class/student/parent/list/student=[studentId]&type=[type]&token=[
	 * token]
	 *//*
	@RequestMapping(value = "/class/student/parent/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classStudentParentListGET(@RequestBody UserDBO user) {
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
	 * 班级学生家长新增接口 /qxy/class/student/parent
	 *//*
	@RequestMapping(value = "/class/student/parent", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classStudentParentPOST(@RequestBody ClassCourseDBO classCourse) {
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
	 * 班级学生家长修改/停用(启用)及指定默认家长接口 /qxy/class/student/parent
	 *//*
	@RequestMapping(value = "/class/student/parent", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classStudentParentPUT(@RequestBody ClassCourseDBO classCourse) {
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
	 * 班级学生家长删除接口 /qxy/class/student/info
	 *//*
	@RequestMapping(value = "/class/student/info", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
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
