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
	public RESTResultBean calendarGET(String termId, String time, String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			SCalendarDBO param = new SCalendarDBO();
			param.setTermId(Long.valueOf(termId));
			if (StringUtils.isNotEmpty(time)) {
				
				if (time.split("\\|").length < 2)
				{
					result.setInfo("查询失败，时间入参格式错误");
					result.setStatus(3);
					return result;
				}
				
				String startTime = time.split("\\|")[0];
				String endTime = time.split("\\|")[1];
				if (startTime.indexOf("/") != 4 || startTime.lastIndexOf("/") != 7
						|| endTime.indexOf("/") != 4 || endTime.lastIndexOf("/") != 7)
				{
					result.setInfo("修改失败，时间入参格式错误");
					result.setStatus(3);
					return result;
				}
				
				param.setStartTime(DateUtils.parseDate(startTime));
				param.setEndTime(DateUtils.parseDate(endTime));
			}
			
			List<SCalendarDBO> sSanlendarlist = (List<SCalendarDBO>) SCalendarService_.doSelectList(param);
			if (sSanlendarlist != null) {
				JSONObject json = new JSONObject();
				json.put("count", sSanlendarlist.size());
				JSONArray array = new JSONArray();
				for (SCalendarDBO sCalendar : sSanlendarlist) {
					JSONObject calendar = new JSONObject();
					JSONObject times = new JSONObject();
					calendar.put("calendarId", sCalendar.getCalendarId() + "");
					calendar.put("content", sCalendar.getContent());
					calendar.put("department", sCalendar.getDepartment());
					times.put("startTime", DateUtils.formatDate(sCalendar.getStartTime(), DateUtils.FORMAT_yyyyMMdd_HH_mm_ss_));
					times.put("endTime", DateUtils.formatDate(sCalendar.getEndTime(), DateUtils.FORMAT_yyyyMMdd_HH_mm_ss_));
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
	public RESTResultBean calendarPOST(@RequestBody String jsonparam) {

		RESTResultBean result = new RESTResultBean();

		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
			String token = param.getString("token");
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			
			String startTime = param.getString("startTime");
			String endTime = param.getString("endTime");
			
			if (StringUtils.isEmpty(param.getString("termId")))
			{
				result.setInfo("修改失败，学期为空");
				result.setStatus(3);
				return result;
			}
			
			if (StringUtils.isEmpty(startTime) ||StringUtils.isEmpty(endTime))
			{
				result.setInfo("修改失败，入参格式错误");
				result.setStatus(3);
				return result;
			}
			if (startTime.indexOf("/") != 4 || startTime.lastIndexOf("/") != 7
					|| endTime.indexOf("/") != 4 || endTime.lastIndexOf("/") != 7)
			{
				result.setInfo("修改失败，时间入参格式错误");
				result.setStatus(3);
				return result;
			}

			// 新增
			SCalendarDBO sCalendar = new SCalendarDBO();
			sCalendar.setTermId(param.getLong("termId"));
			sCalendar.setContent(param.getString("content"));
			sCalendar.setDepartment(param.getString("department"));
			sCalendar.setStartTime(DateUtils.parseDate(startTime));
			sCalendar.setEndTime(DateUtils.parseDate(endTime));
			sCalendar.setIsUse(1);
			SCalendarService_.doInsert(sCalendar);
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
	public RESTResultBean calendarPUT(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {

			JSONObject param = JSONObject.parseObject(jsonparam);
			String token = param.getString("token");
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			
			String startTime = param.getString("startTime");
			String endTime = param.getString("endTime");
			if (StringUtils.isEmpty(param.getString("termId")))
			{
				result.setInfo("修改失败，学期为空");
				result.setStatus(3);
				return result;
			}
			
			if (StringUtils.isEmpty(param.getString("calendarId")))
			{
				result.setInfo("修改失败，学校校历为空");
				result.setStatus(3);
				return result;
			}
			
			
			if (StringUtils.isEmpty(startTime) ||StringUtils.isEmpty(endTime))
			{
				result.setInfo("修改失败，入参格式错误");
				result.setStatus(3);
				return result;
			}
			if (startTime.indexOf("/") != 4 || startTime.lastIndexOf("/") != 7
					|| endTime.indexOf("/") != 4 || endTime.lastIndexOf("/") != 7)
			{
				result.setInfo("修改失败，时间入参格式错误");
				result.setStatus(3);
				return result;
			}

			SCalendarDBO sCalendar = new SCalendarDBO();

			// 校验是否存在
			sCalendar.setCalendarId(param.getLong("calendarId"));
			SCalendarDBO sCalendarInfo = (SCalendarDBO) SCalendarService_.doRead(sCalendar);
			if (null == sCalendarInfo) {
				result.setInfo("更新失败，该学校校历不存在");
				result.setStatus(2);
				return result;
			}

			sCalendar.setTermId(param.getLong("termId"));
			sCalendar.setContent(param.getString("content"));
			sCalendar.setDepartment(param.getString("department"));
			sCalendar.setStartTime(DateUtils.parseDate(startTime));
			sCalendar.setEndTime(DateUtils.parseDate(endTime));

			// 修改
			SCalendarService_.doUpdate(sCalendar);
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
	public RESTResultBean calendarDELETE(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		JSONObject param = JSONObject.parseObject(jsonparam);
		String token = param.getString("token");

		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			String calendarId = param.getString("calendarId");

			if (StringUtils.isNotEmpty(calendarId)) {
				// 乐观锁操作
				SCalendarDBO dbo = new SCalendarDBO();
				dbo.setCalendarId(Long.valueOf(calendarId));
				dbo = (SCalendarDBO) SCalendarService_.doRead(dbo);

				if (null != dbo) {
					SCalendarService_.doDelete(dbo);
				} else {
					result.setInfo("没有对应的学校校历数据可以删除");
					result.setStatus(1);
				}
			}

		} catch (Exception e) {
			result.setInfo("删除失败");
			result.setStatus(1);
		}
		return result;
	}

}
