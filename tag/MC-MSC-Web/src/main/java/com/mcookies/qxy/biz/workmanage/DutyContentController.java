package com.mcookies.qxy.biz.workmanage;

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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.NewsColumn.NewsColumnDBO;
import com.mcookies.qxy.common.SDuty.SDutyDBO;
import com.mcookies.qxy.common.SDuty.SDutyService;
import com.mcookies.qxy.common.SDutyContent.SDutyContentDBO;
import com.mcookies.qxy.common.SDutyContent.SDutyContentService;

/**
 * 工作管理-岗位详情
 * 
 * @author linyh
 *
 */
@Controller
public class DutyContentController extends MyControllerSupport {

	@Resource
	protected SDutyService sDutyService;
	@Resource
	protected SDutyContentService sDutyContentService;

	/**
	 * 值周(值日)工作岗位详情新增接口 /duty/content
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/duty/content", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean dutyContentPOST(String token, @RequestBody SDutyContentDBO sDutyContent) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			// 判断所属岗位是否存在
			if (sDutyContent.getDutyId() == null) {
				throw new IllegalArgumentException("所属岗位id不能为空");
			}
			SDutyDBO condition = new SDutyDBO();
			condition.setDutyId(sDutyContent.getDutyId());
			List<NewsColumnDBO> newsColumns = (List<NewsColumnDBO>) sDutyService.doSelectData(condition);
			if (newsColumns.size() == 0) {
				throw new IllegalArgumentException("对应的岗位不存在");
			}
			sDutyContentService.doInsert(sDutyContent);
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
	 * 值周(值日)工作岗位详情修改接口 /duty/content
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/duty/content", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean dutyContentPUT(String token, @RequestBody SDutyContentDBO sDutyContent) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			// 查询是否存在
			if (sDutyContent.getId() == null) {
				throw new IllegalArgumentException("岗位规则id不能为空");
			}
			SDutyContentDBO condition = new SDutyContentDBO();
			condition.setId(sDutyContent.getId());
			List<SDutyContentDBO> sDutyContents = (List<SDutyContentDBO>) sDutyContentService.doSelectData(condition);
			if (sDutyContents.size() == 0) {
				result.setInfo("修改失败，岗位规则不存在");
				result.setStatus(1);
				return result;
			}
			sDutyContentService.doUpdate(sDutyContent);
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
	 * 值周(值日)工作岗位详情删除接口 /duty/content
	 */
	@RequestMapping(value = "/duty/content", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean dutyContentDELETE(String token, @RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			JSONArray sDutyContentIds = param.getJSONArray("dutycontentlist");
			for (Object sDutyContentId : sDutyContentIds) {
				Long tmp = Long.valueOf(sDutyContentId.toString());
				// 乐观锁操作
				SDutyContentDBO dbo = new SDutyContentDBO();
				dbo.setId(tmp);
				dbo = (SDutyContentDBO) sDutyContentService.doRead(dbo);
				@SuppressWarnings("unused")
				int flag = sDutyContentService.doDelete(dbo);
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
