package com.mcookies.qxy.biz.school;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.SCalendar.SCalendarDBO;
import com.mcookies.qxy.common.SCalendar.SCalendarPVO;
import com.mcookies.qxy.common.SCalendar.SCalendarService;
import com.mcookies.qxy.utils.DateUtils;

/**
 * 
 * 类名称：CalendarManageController 类描述： 校历管理contronller 创建时间：2016年8月6日 下午1:06:27
 */
@Controller
public class CalendarManageController extends MyControllerSupport {

	@Resource
	protected SCalendarService SCalendarService_;

	/**
	 * 查询学校校历接口
	 * 
	 * @param termId
	 * @param time
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/calendar", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean calendarGET(Long termId, String startTime,String endTime, String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			SCalendarDBO param = new SCalendarDBO();
			param.setTermId(termId);
			param.setStartTime(startTime);
			param.setEndTime(endTime);
			List<SCalendarDBO> sSanlendarlist = (List<SCalendarDBO>) SCalendarService_.doSelectList(param);
			if (sSanlendarlist != null) {
				JSONObject json = new JSONObject();
				json.put("count", sSanlendarlist.size());
				JSONArray array = new JSONArray();
				for (SCalendarDBO sCalendar : sSanlendarlist) {
					JSONObject calendar = new JSONObject();
					JSONObject times = new JSONObject();
					calendar.put("calendarId", sCalendar.getCalendarId());
					calendar.put("content", sCalendar.getContent());
					calendar.put("department", sCalendar.getDepartment());
					times.put("startTime",sCalendar.getStartTime());
					times.put("endTime",sCalendar.getEndTime());
					calendar.put("time", times);
					array.add(calendar);
				}
				json.put("calendarinfo", array);
				result.setData(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo("查询失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 新增学校校历接口
	 * 
	 * @param sCalendar
	 * @return
	 */
	@RequestMapping(value = "/calendar", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean calendarPOST(String token, @RequestBody SCalendarPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if(pvo.getTermId()==null){
				result.setInfo("termId不能为空");
				result.setStatus(1);
				return result;
			}
			if(pvo.getStartTime()==null||pvo.getEndTime()==null){
				result.setInfo("起始时间不能为空");
				result.setStatus(1);
				return result;
			}
			pvo.setIsUse(1);
			int flag = SCalendarService_.doInsert(pvo);
			if(flag!=1){
				result.setInfo("新增失败");
				result.setStatus(1);
			}
		} catch (Exception e) {
			result.setInfo("新增失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 学校校历修改接口
	 * 
	 * @param sCalendar
	 * @return
	 */
	@RequestMapping(value = "/calendar", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean calendarPUT(String token, @RequestBody SCalendarPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}

			SCalendarDBO sCalendar = new SCalendarDBO();

			// 校验是否存在
			sCalendar.setCalendarId(pvo.getCalendarId());
			SCalendarDBO sCalendarInfo = (SCalendarDBO) SCalendarService_.doRead(sCalendar);
			if (null == sCalendarInfo) {
				result.setInfo("更新失败，该学校校历不存在");
				result.setStatus(2);
				return result;
			}
			// 修改
			SCalendarService_.doUpdate(pvo);
		} catch (Exception e) {
			result.setInfo("修改失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 学校校历删除接口
	 * 
	 * @param jsonparam
	 * @return
	 */
	@RequestMapping(value = "/calendar", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean calendarDELETE(String token, @RequestBody SCalendarPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if (pvo.getCalendarId()!=null) {
				SCalendarService_.doDelete(pvo);
			}

		} catch (Exception e) {
			result.setInfo("删除失败");
			result.setStatus(1);
		}
		return result;
	}

}
