package com.mcookies.qxy.biz.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcookies.qxy.common.School.SchoolDBO;
import com.mcookies.qxy.common.School.SchoolService;
import com.mcookies.qxy.common.UTeacher.UTeacherDBO;
import com.mcookies.qxy.common.UTeacher.UTeacherService;
import com.mcookies.qxy.common.User.UserDBO;
import com.mcookies.qxy.common.User.UserService;

/**
 * login.token.4 学校相关
 * 
 * @author linyh
 *
 */
@Controller
public class SchoolManageController extends MyControllerSupport {

	@Resource
	protected SchoolService schoolService;
	@Resource
	protected UserService userService;
	@Resource
	protected UTeacherService uTeacherService;

	/**
	 * 学校列表查询接口 /school/list?type=[type]
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/school/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean schoolListGET(@RequestParam(required = false) String token,
			@RequestParam(defaultValue = "0") Integer type) {
		RESTResultBean result = new RESTResultBean();
		try {
			SchoolDBO school = new SchoolDBO();
			if (type != 1) {
				school.setIsUse(1);
			}
			school.setPuk("1");
			List<SchoolDBO> schools = (List<SchoolDBO>) schoolService.doSelectData(school);

			Map<String, Object> data = new HashMap<String, Object>();
			data.put("count", schools.size());
			data.put("school", schools);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}
	
	/**
	 * 学校详细信息查询接口 /school/info?sid=[sid]
	 */
	@RequestMapping(value = "/school/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean schoolInfoGET(SchoolDBO dbo) {
		RESTResultBean result = new RESTResultBean();
		try {
			Long sid = dbo.getSid();
			if (doCheckToken(dbo.getToken()) == false) {
				return tokenFail();
			}
			SchoolDBO origin = new SchoolDBO();
			if (sid != null) {
				origin.setSid(sid);
				origin.setPuk("1");
			}
			origin = (SchoolDBO) schoolService.doRead(origin);
			result.setData(origin);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}
		
		return result;
	}

	/**
	 * 学校详细信息新增接口 /school?token=[token]
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/school", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean schoolPOST(String token, @RequestBody SchoolDBO school) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if (school.getCreateUid() == null) {
				throw new IllegalArgumentException("createUid不能为空");
			}
			UserDBO user = new UserDBO();
			user.setUid(school.getCreateUid());
			user = (UserDBO) userService.doRead(user);
			if (user == null) {
				throw new IllegalArgumentException("createUid对应的用户不存在");
			}
			if (StringUtils.isEmpty(school.getSCode())) {
				throw new IllegalArgumentException("识别码不能为空");
			}
			SchoolDBO sCodeCondition = new SchoolDBO();
			sCodeCondition.setSCode(school.getSCode());
			sCodeCondition.setPuk("1");
			List<SchoolDBO> schools = (List<SchoolDBO>) schoolService.doSelectData(sCodeCondition);
			if (schools.size() > 0) {
				result.setInfo("新增失败，该识别码已存在");
				result.setStatus(1);
				return result;
			}
			if (StringUtils.isEmpty(school.getSchoolName())) {
				throw new IllegalArgumentException("学校名字不能为空");
			}
			SchoolDBO schoolNameCondition = new SchoolDBO();
			schoolNameCondition.setSchoolName(school.getSchoolName());
			schoolNameCondition.setPuk("1");
			schools = (List<SchoolDBO>) schoolService.doSelectData(schoolNameCondition);
			if (schools.size() > 0) {
				result.setInfo("新增失败，该学校名字已存在");
				result.setStatus(2);
				return result;
			}
			if (school.getIsUse() == null) {
				school.setIsUse(1);
			}
			school.setPuk("1");
			schoolService.doInsert(school);
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
	 * 学校详细信息修改/停用（启用）接口 /school?token=[token]
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/school", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean schoolPUT(String token, @RequestBody SchoolDBO school) {
		RESTResultBean result = new RESTResultBean();
		try {
			// 要改的sid
			Long sid = school.getSid();
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			SchoolDBO origin = new SchoolDBO();
			if (sid != null) {
				origin.setSid(sid);
				origin.setPuk("1");
			}
			origin = (SchoolDBO) schoolService.doRead(origin);
			if (origin == null) {
				throw new IllegalArgumentException("修改/停用（启用）失败，sid或所属学校不存在");
			}
			// 查询识别码是否重复
			// 先判断是否已经改识别码
			if (!StringUtils.isEmpty(school.getSCode())
					&& !origin.getSCode().equals(school.getSCode())) {
				// 改了识别码
				SchoolDBO condition = new SchoolDBO();
				condition.setSCode(school.getSCode());
				condition.setPuk("1");
				List<SchoolDBO> schools = (List<SchoolDBO>) schoolService.doSelectData(condition);
				if (schools.size() > 0 && schools.get(0).getSid() != origin.getSid()) {
					result.setInfo("修改失败，该识别码已存在");
					result.setStatus(1);
					return result;
				}
			}
			// 查询名称是否重复
			// 先判断是否已经改名
			if (!StringUtils.isEmpty(school.getSchoolName())
					&& !origin.getSchoolName().equals(school.getSchoolName())) {
				// 改了名
				SchoolDBO condition = new SchoolDBO();
				condition.setSchoolName(school.getSchoolName());
				condition.setPuk("1");
				List<SchoolDBO> schools = (List<SchoolDBO>) schoolService.doSelectData(condition);
				if (schools.size() > 0 && schools.get(0).getSid() != origin.getSid()) {
					result.setInfo("修改失败，该学校名字已存在");
					result.setStatus(2);
					return result;
				}
			}
			// 如果没传sid，从token中获取sid
			if (sid != null) {
				school.setPuk("1");
			}
			schoolService.doUpdate(school);
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
	 * 学校删除接口 /school?token=[token]
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/school", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean schoolDELETE(String token, @RequestBody SchoolDBO school) {
		RESTResultBean result = new RESTResultBean();
		try {
			// 要改的sid
			Long sid = school.getSid();
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			// 查询是否存在
			if (sid == null) {
				throw new IllegalArgumentException("sid不能为空");
			}
			// 查询是否存在
			SchoolDBO origin = new SchoolDBO();
			if (sid != null) {
				origin.setSid(sid);
				origin.setPuk("1");
			}
			origin = (SchoolDBO) schoolService.doRead(origin);
			if (origin == null) {
				result.setInfo("删除失败，该学校不存在");
				result.setStatus(2);
				return result;
			}
			// 查询是否有教工关联
			UTeacherDBO teacher = new UTeacherDBO();
			teacher.setSid(sid);
			teacher.setPuk("1");
			List<UTeacherDBO> teachers = (List<UTeacherDBO>) uTeacherService.doSelectData(teacher);
			if (teachers.size() > 0) {
				result.setInfo("删除失败，该学校已经关联了教工");
				result.setStatus(1);
				return result;
			}
			// 是否还存在
			origin.setPuk("1");
			int flag = schoolService.doDelete(origin);
			if (flag == 0) {
				result.setInfo("删除失败，该学校不存在");
				result.setStatus(2);
				return result;
			}
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
