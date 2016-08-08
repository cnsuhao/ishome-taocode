package com.mcookies.qxy.biz.workmanage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.Class.ClassPVO;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherDBO;
import com.mcookies.qxy.common.OaRule.OaRuleDBO;
import com.mcookies.qxy.common.OaRule.OaRulePVO;
import com.mcookies.qxy.common.OaRule.OaRuleService;
import com.mcookies.qxy.common.OaTags.OaTagsDBO;
import com.mcookies.qxy.common.OaTags.OaTagsService;
import com.mcookies.qxy.common.UTeacher.UTeacherDBO;

/**
 * 工作管理-工作流程模板规则
 * 
 * @author linyh
 *
 */
@Controller
public class WorkflowTemplateRuleController extends MyControllerSupport {

	@Resource
	protected OaTagsService oaTagsService;
	@Resource
	protected OaRuleService oaRuleService;

	/**
	 * 工作流程事项模板规则查询接口 /qxy/oarule/token=[token]
	 */
	@RequestMapping(value = "/qxy/oarule", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean oaRuleGET(@RequestParam(required = false) String token,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "12") int size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}

			Long sid = getLoginer().getSchoolId();
			if (sid == null) {
				throw new IllegalStateException("获取学校id失败");
			}
			Map<String, Object> data = new HashMap<String, Object>();
			OaRuleDBO rule = new OaRuleDBO();
			rule.setSid(sid);
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);
			pageModel.setFormParamBean(rule);
			// pageModel.setOrderby("publish_time desc");
			oaRuleService.doSelectPage(pageModel);
			
			for (FrameworkDataBean each: pageModel.getPageListData()) {
				OaRulePVO tmp = (OaRulePVO) each;
				OaTagsDBO oatags = new OaTagsDBO();
				oatags.setOatagsId(tmp.getOatagsId());
				oatags = (OaTagsDBO) oaTagsService.doRead(oatags);
				if (oatags != null) {
					tmp.setOaruleName(oatags.getOatagsName());
				}
			}
			data.put("page", pageModel.getPageCurrent());
			data.put("size", pageModel.getPageLimit());
			data.put("count", pageModel.getResultCount());
			data.put("oarule", pageModel.getPageListData());
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 工作流程事项模板规则新增接口 /qxy/oarule
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/qxy/oarule", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean oaRulePOST(@RequestBody OaRuleDBO oaRule) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(oaRule.getToken()) == false) {
				return tokenFail();
			}
			OaTagsDBO parent = new OaTagsDBO();
			parent.setOatagsId(oaRule.getOatagsId());
			List<OaTagsDBO> parents = (List<OaTagsDBO>) oaTagsService.doSelectData(parent);
			if (parents.size() == 0) {
				throw new IllegalArgumentException("oatagsId所对应的流程模板不存在");
			}
			// 检查serialNumber是否合法（不重复）
			OaRuleDBO condition = new OaRuleDBO();
			condition.setOatagsId(oaRule.getOatagsId());
			condition.setSerialNumber(oaRule.getSerialNumber());
			List<OaRuleDBO> conditions = (List<OaRuleDBO>) oaRuleService.doSelectData(condition);
			if (conditions.size() > 0) {
				throw new IllegalArgumentException("该流程模板存在相同的serialNumber");
			}
			oaRuleService.doInsert(oaRule);
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
	 * 工作流程事项模板规则修改及指定审批人接口 /qxy/schedule
	 */
	@RequestMapping(value = "/qxy/oarule", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean oaRulePUT(@RequestBody OaRuleDBO oaRule) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(oaRule.getToken()) == false) {
				return tokenFail();
			}
			// 不允许在传递oatagsId
//			// 查询是否存在
//			if (oaRule.getOatagsId() == null) {
//				throw new IllegalArgumentException("流程模版id不能为空");
//			}
//			OaRuleDBO condition = new OaRuleDBO();
//			condition.setOatagsId(oaRule.getOatagsId());
//			List<OaRuleDBO> oaRules = (List<OaRuleDBO>) oaRuleService.doSelectData(condition);
//			if (oaRules.size() == 0) {
//				throw new IllegalArgumentException("流程模版不存在");
//			}
			// 检查是否已经启用
			List<Long> ruleIds = new ArrayList<Long>();
			ruleIds.add(oaRule.getOaruleId());
			if (oaTagsService.doSelectUsedByOaRuleIds(ruleIds).size() > 0) {
				throw new IllegalArgumentException("流程模板已被使用");
			}
			// TODO: 判断审核人是否存在
			oaRuleService.doUpdate(oaRule);
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
	 * 工作流程事项模板规则删除接口 /qxy/oarule
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/qxy/oarule", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean oaRuleDELETE(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
			String token = (String) param.get("token");
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			JSONArray oaRuleIds = param.getJSONArray("oaruleIdS");
			List<Long> ruleIds = (List<Long>) param.get("oaruleIdS");
			// 检查是否有已启用的流程模版
			if (oaTagsService.doSelectUsedByOaRuleIds(ruleIds).size() > 0) {
				throw new IllegalArgumentException("流程模板已被使用");
			}
			for (Object oaRuleId : oaRuleIds) {
				Long tmp = Long.valueOf(oaRuleId.toString());
				// 乐观锁操作
				OaRuleDBO dbo = new OaRuleDBO();
				dbo.setOaruleId(tmp);
				dbo = (OaRuleDBO) oaRuleService.doRead(dbo);
				@SuppressWarnings("unused")
				int flag = oaRuleService.doDelete(dbo);
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
