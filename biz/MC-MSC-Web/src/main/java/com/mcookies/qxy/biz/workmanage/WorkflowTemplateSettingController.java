package com.mcookies.qxy.biz.workmanage;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.OaRule.OaRuleService;
import com.mcookies.qxy.common.OaTags.OaTagsService;
import com.mcookies.qxy.common.OaTags.OaTagsDBO;
import com.mcookies.qxy.common.User.UserDBO;

/**
 * 工作管理-工作流程模板设置
 * 
 * @author linyh
 *
 */
@Controller
public class WorkflowTemplateSettingController extends MyControllerSupport {

	@Resource
	protected OaTagsService oaTagsService;
	@Resource
	protected OaRuleService oaRuleService;

	/**
	 * 工作流程事项模板查询接口 /qxy/oatags/token=[token]
	 */
	@RequestMapping(value = "/qxy/oatags", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean oaTagsGET(@RequestBody UserDBO user) {
		// TODO
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(user.getToken()) == false) {
				return tokenFail();
			}

			String userId = getLoginer().getUserId();

			result.setInfo("欢迎访问千校云平台：" + userId + "," + user.getAccount());
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 工作流程事项模板新增接口 /qxy/oatags
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/qxy/oatags", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean oaTagsPOST(@RequestBody OaTagsDBO oaTags) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(oaTags.getToken()) == false) {
				return tokenFail();
			}
			if (StringUtils.isEmpty(oaTags.getOatagsName())) {
				throw new IllegalArgumentException("流程模版名称不能为空");
			}
			OaTagsDBO condition = new OaTagsDBO();
			condition.setOatagsName(oaTags.getOatagsName());
			List<OaTagsDBO> oaTagss = (List<OaTagsDBO>) oaTagsService.doSelectData(condition);
			if (oaTagss.size() > 0) {
				result.setInfo("新增失败，重复的流程模版名称");
				result.setStatus(2);
				return result;
			}
			oaTagsService.doInsert(oaTags);
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
	 * 工作流程事项模板修改接口 /qxy/oatags
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/qxy/oatags", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean oaTagsPUT(@RequestBody OaTagsDBO oaTags) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (doCheckToken(oaTags.getToken()) == false) {
					return tokenFail();
				}
				// 查询是否存在
				if (oaTags.getOatagsId() == null) {
					throw new IllegalArgumentException("流程模版id不能为空");
				}
				OaTagsDBO condition = new OaTagsDBO();
				condition.setOatagsId(oaTags.getOatagsId());
				List<OaTagsDBO> oaTagss = (List<OaTagsDBO>) oaTagsService.doSelectData(condition);
				if (oaTagss.size() == 0) {
					result.setInfo("修改失败，流程模版不存在");
					result.setStatus(2);
					return result;
				}
				// 判断是否已经启用
				if (oaTagss.get(0).getIsUse() != null && oaTagss.get(0).getIsUse() == 1) {
					result.setInfo("修改失败，流程模板已被使用");
					result.setStatus(1);
					return result;
				}
				// 查询名称是否重复
				// 先判断是否已经改名
				if (!StringUtils.isEmpty(oaTags.getOatagsName()) && !oaTagss.get(0).getOatagsName().equals(oaTags.getOatagsName())) {
					// 改了名
					condition = new OaTagsDBO();
					condition.setOatagsName(oaTags.getOatagsName());
					oaTagss = (List<OaTagsDBO>) oaTagsService.doSelectData(condition);
					if (oaTagss.size() > 0) {
						result.setInfo("修改失败，流程模版名称已存在");
						result.setStatus(3);
						return result;
					}
				}
				oaTagsService.doUpdate(oaTags);
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
	 * 工作流程事项模板删除接口 /qxy/oatags
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/qxy/oatags", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean oaTagsDELETE(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
			String token = (String) param.get("token");
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			JSONArray oaTagsIds = param.getJSONArray("oatagsIds");
			List<Long> tagsIds = (List<Long>) param.get("oatagsIds");
			// 检查是否有子规则
			if (oaRuleService.doSelectByOaTagsIds(tagsIds).size() > 0) {
				result.setInfo("删除失败，要删除的流程模版包含了子规则");
				result.setStatus(2);
				return result;
			}
			// 检查是否有已启用的流程模版
			if (oaTagsService.doSelectUsedByOaTagsIds(tagsIds).size() > 0) {
				result.setInfo("删除失败，流程模板已被使用");
				result.setStatus(1);
				return result;
			}
			for (Object oaTagsId : oaTagsIds) {
				Long tmp = Long.valueOf(oaTagsId.toString());
				// 乐观锁操作
				OaTagsDBO dbo = new OaTagsDBO();
				dbo.setOatagsId(tmp);
				dbo = (OaTagsDBO) oaTagsService.doRead(dbo);
				@SuppressWarnings("unused")
				int flag = oaTagsService.doDelete(dbo);
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
