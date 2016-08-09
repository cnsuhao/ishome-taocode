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
import com.mcookies.qxy.common.SDuty.SDutyDBO;
import com.mcookies.qxy.common.SDuty.SDutyService;
import com.mcookies.qxy.common.SDutyContent.SDutyContentService;

/**
 * 工作管理-值日值周岗位
 * 
 * @author linyh
 *
 */
@Controller
public class ScheduleDutyController extends MyControllerSupport {

	@Resource
	protected SDutyService sDutyService;
	@Resource
	protected SDutyContentService sDutyContentService;

	/**
	 * 值周(值日)工作岗位新增接口 /duty
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/duty", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean dutyPOST(@RequestBody SDutyDBO sDuty) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(sDuty.getToken()) == false) {
				return tokenFail();
			}
			if (StringUtils.isEmpty(sDuty.getDutyName())) {
				throw new IllegalArgumentException("岗位名字不能为空");
			}
			SDutyDBO condition = new SDutyDBO();
			condition.setDutyName(sDuty.getDutyName());
			List<SDutyDBO> sDutys = (List<SDutyDBO>) sDutyService.doSelectData(condition);
			if (sDutys.size() > 0) {
				result.setInfo("新增失败，重复的岗位名字");
				result.setStatus(2);
				return result;
			}
			sDutyService.doInsert(sDuty);
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
	 * 值周(值日)工作岗位修改及停用(启用)接口 /duty
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/duty", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean dutyPUT(@RequestBody SDutyDBO sDuty) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(sDuty.getToken()) == false) {
				return tokenFail();
			}
			// 查询是否存在
			if (sDuty.getDutyId() == null) {
				throw new IllegalArgumentException("岗位id不能为空");
			}
			SDutyDBO condition = new SDutyDBO();
			condition.setDutyId(sDuty.getDutyId());
			List<SDutyDBO> sDutys = (List<SDutyDBO>) sDutyService.doSelectData(condition);
			if (sDutys.size() == 0) {
				result.setInfo("启用/停用/修改失败，岗位不存在");
				result.setStatus(2);
				return result;
			}
			// 查询名称是否重复
			// 先判断是否已经改名
			if (!StringUtils.isEmpty(sDuty.getDutyName()) && !sDutys.get(0).getDutyName().equals(sDuty.getDutyName())) {
				// 改了名
				condition = new SDutyDBO();
				condition.setDutyName(sDuty.getDutyName());
				sDutys = (List<SDutyDBO>) sDutyService.doSelectData(condition);
				if (sDutys.size() > 0) {
					result.setInfo("修改失败，名字已存在");
					result.setStatus(1);
					return result;
				}
			}
			sDutyService.doUpdate(sDuty);
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
	 * 值周(值日)工作岗位删除接口 /duty
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/duty", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean dutyDELETE(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
			String token = (String) param.get("token");
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			JSONArray sDutyIds = param.getJSONArray("dutyIds");
			List<Long> dutyIds = (List<Long>) param.get("dutyIds");
			// 检查是否有子规则
			if (sDutyContentService.doSelectByDutyIds(dutyIds).size() > 0) {
				result.setInfo("删除失败，要删除的岗位包含了子规则");
				result.setStatus(1);
				return result;
			}
			for (Object sDutyId : sDutyIds) {
				Long tmp = Long.valueOf(sDutyId.toString());
				// 乐观锁操作
				SDutyDBO dbo = new SDutyDBO();
				dbo.setDutyId(tmp);
				dbo = (SDutyDBO) sDutyService.doRead(dbo);
				@SuppressWarnings("unused")
				int flag = sDutyService.doDelete(dbo);
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
