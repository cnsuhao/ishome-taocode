package com.mcookies.qxy.biz.system;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.Class.ClassDBO;
import com.mcookies.qxy.common.Class.ClassPVO;
import com.mcookies.qxy.common.Class.ClassService;
import com.mcookies.qxy.common.SGradeLabel.SGradeLabelDBO;
import com.mcookies.qxy.common.SGradeLabel.SGradeLabelService;
import com.mcookies.qxy.common.STerm.STermDBO;
import com.mcookies.qxy.common.STerm.STermService;
import com.mcookies.qxy.common.SWorkRule.SWorkRuleDBO;
import com.mcookies.qxy.common.SWorkRule.SWorkRulePVO;
import com.mcookies.qxy.common.SWorkRule.SWorkRuleService;
import com.mcookies.qxy.common.SWorkTime.SWorkTimeDBO;
import com.mcookies.qxy.common.SWorkTime.SWorkTimePVO;
import com.mcookies.qxy.common.SWorkTime.SWorkTimeService;

@Controller
/**
 * 学校作息模版设置
 * 
 * @author macBookTang
 *
 */
public class WorkTimeManageController extends MyControllerSupport {
	// 作息时间模板表
	@Resource
	protected SWorkTimeService SWorkTimeService_;
	// 班级表
	@Resource
	protected ClassService ClassService_;
	// 学期设置表
	@Resource
	protected STermService STermService_;
	// 年级标签表
	@Resource
	protected SGradeLabelService SGradeLabelService_;
	// 作息时间规则表
	@Resource
	protected SWorkRuleService SWorkRuleService_;

	/**
	 * 班级作息模版查询接口
	 * 
	 * @param cpvo
	 * @param page
	 * @param size
	 * @param tid
	 * @return
	 */
	@RequestMapping(value = "/class/worktime", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classWorktimeGET(ClassPVO cpvo, Integer page, Integer size, Long tid) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject data = new JSONObject();
			JSONArray grade = new JSONArray();
			// token校验
			if (doCheckToken(cpvo.getToken()) == false) {
				return tokenFail();
			}
			// 获取当前学校ID
			Long schoolId = getLoginer().getSchoolId();
			cpvo.setSid(schoolId);
			if (size == null || size == 0) {
				size = 12;
			}
			if (page == null || page == 0) {
				page = 1;
			}
			// 获取学期内容
			STermDBO sterm = new STermDBO();
			sterm.setTermId(cpvo.getTermId());
			sterm = (STermDBO) STermService_.doRead(sterm);
			if (sterm == null) {
				result.setInfo("学期不存在");
				result.setStatus(2);
				return result;
			}
			// 通过班级获取年级信息
			pageModel.config();
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);
			pageModel.setFormParamBean(cpvo);
			List<ClassDBO> classList = (List<ClassDBO>) ClassService_.doSelectPage(pageModel).getPageListData();
			List<Long> gradeIds = new LinkedList<Long>();
			if (classList != null) {
				for (ClassDBO temp : classList) {
					if (!gradeIds.contains(temp.getGradeId())) {
						gradeIds.add(temp.getGradeId());
					}
				}
			}
			// 根据得到的年级ID，获取年级详情
			for (Long gradeid : gradeIds) {
				SGradeLabelDBO sgradel = new SGradeLabelDBO();
				sgradel.setGradeId(gradeid);
				sgradel = (SGradeLabelDBO) SGradeLabelService_.doRead(sgradel);
				JSONObject gradeInfo = new JSONObject();
				gradeInfo.put("gradeId", gradeid);
				gradeInfo.put("gradeName", sgradel.getGradeName());
				JSONArray classinfo = new JSONArray();
				for (ClassDBO tmp1 : classList) {
					if (gradeid == tmp1.getGradeId()) {
						ClassDBO dbo = new ClassDBO();
						dbo.setCid(tmp1.getCid());
						dbo.setTermId(cpvo.getTermId());
						dbo.setGradeId(gradeid);
						List<ClassPVO> classtmplist = ClassService_.doSelectWorkTime(dbo);
						for (ClassPVO tmp : classtmplist) {
							JSONObject c = new JSONObject();
							c.put("cid", tmp.getCid());
							c.put("className", tmp.getClassName());
							c.put("termName", sterm.getTermName());
							c.put("gradeName", sgradel.getGradeName());
							c.put("workId", tmp.getWorkId());
							c.put("workName", tmp.getWorkName());
							c.put("createTime", tmp.getCreateTime());
							classinfo.add(c);
						}
					}
				}
				gradeInfo.put("class", classinfo);
				grade.add(gradeInfo);
			}
			data.put("page", page);
			data.put("size", size);
			data.put("count", pageModel.getResultCount());
			data.put("termName", sterm.getTermName());
			data.put("grade", grade);
			result.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 班级作息模版新增接口
	 * 
	 * @param jsonparam
	 * @return
	 */
	@RequestMapping(value = "/class/worktime", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classWorktimePOST(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
			String token = (String) param.get("token");
			Long workId = null;
			Long termId = null;
			if (param.get("workId") != null) {
				workId = Long.valueOf(param.get("workId").toString());
			}
			if (param.get("termId") != null) {
				termId = Long.valueOf(param.get("termId").toString());
			}
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			JSONArray cids = param.getJSONArray("cid");
			for (Object temp : cids) {
				Long cid = Long.valueOf(temp.toString());
				ClassDBO cdbo = new ClassDBO();
				cdbo.setCid(cid);
				cdbo.setWorkId(workId);
				cdbo.setTermId(termId);
				ClassService_.doUpdate(cdbo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 班级作息模版修改接口
	 * 
	 * @param cpvo
	 * @return
	 */
	@RequestMapping(value = "/class/worktime", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classWorktimePUT(@RequestBody ClassPVO cpvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(cpvo.getToken()) == false) {
				return tokenFail();
			}
			int flag = ClassService_.doUpdate(cpvo);
			if (flag == 0) {
				result.setInfo("修改失败，班级不存在");
				result.setStatus(2);
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 班级作息模版删除接口
	 * 
	 * @param jsonparam
	 * @return
	 */
	@RequestMapping(value = "/class/worktime", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classWorktimeDELETE(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
			String token = (String) param.get("token");
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			JSONArray cids = param.getJSONArray("cid");
			for (Object temp : cids) {
				Long cid = Long.valueOf(temp.toString());
				ClassDBO cdbo = new ClassDBO();
				cdbo.setCid(cid);
				cdbo.setWorkId(0l);
				ClassService_.doUpdate(cdbo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 学校作息模版查询接口
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/worktime", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean worktimeGET(String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			JSONObject data = new JSONObject();
			JSONArray schoolwork = new JSONArray();
			// 获取当前学校ID
			Long schoolId = getLoginer().getSchoolId();
			// 学校模板列表
			SWorkTimeDBO swork = new SWorkTimeDBO();
			swork.setSid(schoolId);
			List<SWorkTimeDBO> worklist = (List<SWorkTimeDBO>) SWorkTimeService_.doSelectData(swork);
			data.put("count", worklist.size());
			// 循环模板获取对应详情
			for (SWorkTimeDBO temp : worklist) {
				JSONObject worktmp = new JSONObject();
				// JSONArray workRule = new JSONArray();
				worktmp.put("workId", temp.getWorkId());
				worktmp.put("workName", temp.getWorkName());
				worktmp.put("createTime", temp.getCreateTime());
				// 获取详情
				SWorkRuleDBO rule = new SWorkRuleDBO();
				rule.setWorkId(temp.getWorkId());
				List<SWorkRuleDBO> rulelist = (List<SWorkRuleDBO>) SWorkRuleService_.doSelectData(rule);
				worktmp.put("count", rulelist.size());
				worktmp.put("workRule", rulelist);
				schoolwork.add(worktmp);
			}
			data.put("schoolWork", schoolwork);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 学校作息模版新增接口
	 * 
	 * @param work
	 * @return
	 */
	@RequestMapping(value = "/worktime", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean worktimePOST(@RequestBody SWorkTimePVO work) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(work.getToken()) == false) {
				return tokenFail();
			}
			// 获取当前学校ID
			Long schoolId = getLoginer().getSchoolId();
			work.setSid(schoolId);
			// 判断是否已经存在
			List<SWorkTimeDBO> relist = (List<SWorkTimeDBO>) SWorkTimeService_.doSelectData(work);
			if (relist != null && relist.size() > 0) {
				result.setInfo("建立模版失败，名称重复");
				result.setStatus(2);
			} else {
				work.setIsUse(1);
				SWorkTimeService_.doInsert(work);
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 学校作息模版修改接口
	 * 
	 * @param work
	 * @return
	 */
	@RequestMapping(value = "/worktime", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean worktimePUT(@RequestBody SWorkTimePVO work) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(work.getToken()) == false) {
				return tokenFail();
			}
			// 获取当前学校ID
			Long schoolId = getLoginer().getSchoolId();
			// 判断是否已经存在
			SWorkTimeDBO workt = new SWorkTimeDBO();
			workt.setWorkName(work.getWorkName());
			workt.setSid(schoolId);
			List<SWorkTimeDBO> hashwork = (List<SWorkTimeDBO>) SWorkTimeService_.doSelectData(workt);
			if (hashwork != null) {
				for (SWorkTimeDBO temp : hashwork) {
					if (temp.getWorkId() != work.getWorkId()) {
						result.setInfo("更新模版失败，名称重复");
						result.setStatus(2);
						return result;
					}
				}
			}
			// 编辑
			SWorkTimeService_.doUpdate(work);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 学校作息模版删除接口
	 * 
	 * @param work
	 * @return
	 */
	@RequestMapping(value = "/worktime", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean worktimeDELETE(@RequestBody SWorkTimePVO work) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(work.getToken()) == false) {
				return tokenFail();
			}
			// 获取当前学校ID
			Long schoolId = getLoginer().getSchoolId();
			// 判断当前模板是否被班级使用
			ClassDBO classd = new ClassDBO();
			classd.setWorkId(work.getWorkId());
			List<ClassDBO> classlist = (List<ClassDBO>) ClassService_.doSelectData(classd);
			if (classlist != null && classlist.size() > 0) {
				result.setInfo("此模板正在被使用，无法删除");
				result.setStatus(2);
			} else {
				//
				// 乐观锁操作

				SWorkTimeDBO dbo = new SWorkTimeDBO();
				dbo.setWorkId(work.getWorkId());
				dbo.setSid(schoolId);
				dbo = (SWorkTimeDBO) SWorkTimeService_.doRead(dbo);
				int flag = SWorkTimeService_.doDelete(dbo);
				if (flag != 1) {
					result.setInfo("删除模版失败，无此模版编号");
					result.setStatus(2);
				}
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 作息模版详情新增接口
	 * 
	 * @param rule
	 * @return
	 */
	@RequestMapping(value = "/workrule", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean workrulePOST(@RequestBody SWorkRulePVO rule) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(rule.getToken()) == false) {
				return tokenFail();
			}
			// 获取当前学校ID
			Long schoolId = getLoginer().getSchoolId();
			// 判断模板是否存在
			SWorkTimeDBO worktime = new SWorkTimeDBO();
			worktime.setWorkId(rule.getWorkId());
			worktime = (SWorkTimeDBO) SWorkTimeService_.doRead(worktime);
			if (worktime == null) {
				result.setInfo("新增详情失败，无对应的workId");
				result.setStatus(2);
				return result;
			}
			// 新增
			rule.setSid(schoolId);
			rule.setIsUse(1);
			SWorkRuleService_.doInsert(rule);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 学校作息模版修改接口
	 * 
	 * @param rule
	 * @return
	 */
	@RequestMapping(value = "/workrule", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean workrulePUT(@RequestBody SWorkRulePVO rule) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(rule.getToken()) == false) {
				return tokenFail();
			}
			// 获取当前学校ID
			Long schoolId = getLoginer().getSchoolId();
			// 判断模板是否存在
			SWorkTimeDBO worktime = new SWorkTimeDBO();
			worktime.setWorkId(rule.getWorkId());
			worktime = (SWorkTimeDBO) SWorkTimeService_.doRead(worktime);
			if (worktime == null) {
				result.setInfo("更新新增详情失败，无对应的work_id");
				result.setStatus(2);
				return result;
			}
			// 编辑
			SWorkRuleService_.doUpdate(rule);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 作息模版详情删除接口
	 * 
	 * @param jsonparam
	 * @return
	 */
	@RequestMapping(value = "/workrule", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean workruleDELETE(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
			String token = (String) param.get("token");
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			// 删除规则
			JSONArray wruleIds = param.getJSONArray("wruleIds");
			if (wruleIds != null && wruleIds.size() > 0) {
				for (Object temp : wruleIds) {
					Long wruleId = Long.valueOf(temp.toString());
					SWorkRuleDBO sworkrule = new SWorkRuleDBO();
					sworkrule.setWruleId(wruleId);
					SWorkRuleService_.doDelete(sworkrule);
				}
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
}
