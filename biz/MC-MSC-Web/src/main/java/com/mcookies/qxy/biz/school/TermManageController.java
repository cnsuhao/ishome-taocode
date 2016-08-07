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
import com.mcookies.qxy.common.STerm.STermDBO;
import com.mcookies.qxy.common.STerm.STermService;
import com.mcookies.qxy.utils.DateUtils;

/**
 * 
 * 类名称：TermManageController 类描述： 期设置表 创建时间：2016年8月6日 下午2:28:25
 */
@Controller
public class TermManageController extends MyControllerSupport {

	@Resource
	protected STermService STermService_;

	@Resource
	protected SCalendarService SCalendarService_;
	
	/**
	 * 学校学期及校历日历状态查询接口
	 * 
	 * @param termId
	 * @param time
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/termcalendarstatus", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean termcalendarstatusGET(String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			STermDBO param = new STermDBO();
			// 有效
			param.setIsUse(1);

			@SuppressWarnings("unchecked")
			List<STermDBO> sTermDBOlist = (List<STermDBO>) STermService_.doSelectData(param);
			if (sTermDBOlist != null) {
				JSONObject json = new JSONObject();
				json.put("count", sTermDBOlist.size());
				JSONArray array = new JSONArray();
				for (STermDBO sTermDBO : sTermDBOlist) {
					JSONObject term = new JSONObject();

					term.put("termId", sTermDBO.getTermId() + "");
					term.put("termName", sTermDBO.getTermName());
					term.put("isDefault", sTermDBO.getIsDefault());

					if (1 == sTermDBO.getIsDefault().intValue())
					{
						SCalendarDBO calendarParam = new SCalendarDBO();
						calendarParam.setTermId(sTermDBO.getTermId());
						@SuppressWarnings("unchecked")
						List<SCalendarDBO> sSanlendarlist = (List<SCalendarDBO>)SCalendarService_.doSelectData(calendarParam);
					
						if (null != sSanlendarlist && !sSanlendarlist.isEmpty())
						{
							JSONArray calArray = new JSONArray();
							for (SCalendarDBO sCalendarDBO : sSanlendarlist)
							{
								JSONObject calJson = new JSONObject();
								calJson.put("startTime", DateUtils.formatDate(sCalendarDBO.getStartTime(), DateUtils.FORMAT_yyyyMMdd_HH_mm_ss_));
								calJson.put("endTime", DateUtils.formatDate(sCalendarDBO.getEndTime(), DateUtils.FORMAT_yyyyMMdd_HH_mm_ss_));
								calArray.add(calJson);
							}
							term.put("calendarstatus", calArray);
						}
					}
					term.put("startTime", DateUtils.formatDate(sTermDBO.getStartTime(), DateUtils.FORMAT_yyyyMMdd_HH_mm_ss_));
					term.put("endTime", DateUtils.formatDate(sTermDBO.getEndTime(), DateUtils.FORMAT_yyyyMMdd_HH_mm_ss_));
					array.add(term);
				}
				json.put("termlist", array);
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
	 * 学校学期查询接口
	 * 
	 * @param termId
	 * @param time
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/term", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean termGET(String type, String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			STermDBO param = new STermDBO();

			if (StringUtils.isEmpty(type) || "0".equals(type)) {
				param.setIsUse(1);
			}

			@SuppressWarnings("unchecked")
			List<STermDBO> sTermDBOlist = (List<STermDBO>) STermService_.doSelectData(param);
			if (sTermDBOlist != null) {
				JSONObject json = new JSONObject();
				json.put("count", sTermDBOlist.size());
				JSONArray array = new JSONArray();
				for (STermDBO sTermDBO : sTermDBOlist) {
					JSONObject term = new JSONObject();

					term.put("termId", sTermDBO.getTermId() + "");
					term.put("termName", sTermDBO.getTermName());
					term.put("isDefault", sTermDBO.getIsDefault());

					term.put("isUse", sTermDBO.getIsUse());
					term.put("startTime", DateUtils.formatDate(sTermDBO.getStartTime(), DateUtils.FORMAT_yyyyMMdd_HH_mm_ss_));
					term.put("endTime", DateUtils.formatDate(sTermDBO.getEndTime(), DateUtils.FORMAT_yyyyMMdd_HH_mm_ss_));
					array.add(term);
				}
				json.put("termlist", array);
				result.setData(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo("查询失败");
			result.setStatus(1);
		}
		return result;
	}
	
	public static void main(String[] args) {
		String startTime = "2017/01/01 00:09:44";
			System.out.println(startTime.indexOf("/"));
			System.out.println(startTime.lastIndexOf("/"));
	}

	/**
	 * 学校学期新增接口
	 * 
	 * @param sCalendar
	 * @return
	 */
	@RequestMapping(value = "/term", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean termPOST(@RequestBody String jsonparam) {
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
			if (StringUtils.isEmpty(param.getString("termName")) || StringUtils.isEmpty(startTime) ||StringUtils.isEmpty(endTime))
			{
				result.setInfo("新增失败，入参格式错误");
				result.setStatus(3);
				return result;
			}
			if (startTime.indexOf("/") != 4 || startTime.lastIndexOf("/") != 7
					|| endTime.indexOf("/") != 4 || endTime.lastIndexOf("/") != 7)
			{
				result.setInfo("新增失败，时间入参格式错误");
				result.setStatus(3);
				return result;
			}
				
			// 校验学期名是否已经存在
			STermDBO sTermDBO = new STermDBO();
			sTermDBO.setTermName(param.getString("termName"));
			@SuppressWarnings("unchecked")
			List<STermDBO> termlist = (List<STermDBO>) STermService_.doSelectData(sTermDBO);
			if (termlist != null && !termlist.isEmpty()) {
				result.setInfo("新增失败，学期名已存在");
				result.setStatus(1);
				return result;
			}
			sTermDBO.setStartTime(DateUtils.parseDate(param.getString("startTime")));
			sTermDBO.setEndTime(DateUtils.parseDate(param.getString("endTime")));
			// 默认添加的学期为启用状态
			sTermDBO.setIsUse(1);
			
			sTermDBO.setIsDefault(0);
			// 新增
			STermService_.doInsert(sTermDBO);
		} catch (Exception e) {
			result.setInfo("新增失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 学校学期修改\停用(启用)\设为缺省学期接口
	 * 
	 * @param sCalendar
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/term", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean termPUT(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
			String token = param.getString("token");
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			
			// TOTO 校验学期是否被应用（未完成）
			
			String startTime = param.getString("startTime");
			String endTime = param.getString("endTime");
			if (StringUtils.isEmpty(param.getString("termName")) || StringUtils.isEmpty(startTime) ||StringUtils.isEmpty(endTime))
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
			
			
			// 校验学期名是否已经存在
			String termId = param.getString("termId");
			STermDBO sTermDBO = new STermDBO();
			sTermDBO.setTermName(param.getString("termName"));
			List<STermDBO> termlist = (List<STermDBO>) STermService_.doSelectData(sTermDBO);
			if (termlist != null && !termlist.isEmpty()) {
				
				for (STermDBO stermDBO : termlist)
				{
					if (!stermDBO.getTermId().toString().equals(termId))
					{
						result.setInfo("修改失败，学期名已存在");
						result.setStatus(1);
						return result;
					}
				}
			}
			sTermDBO = new STermDBO();
			termlist = null;
			//是否为缺省学期	 0-否；1-是														
			sTermDBO.setIsDefault(1);
			
			termlist = (List<STermDBO>) STermService_.doSelectData(sTermDBO);
			if (termlist != null && !termlist.isEmpty()) {
				
				for (STermDBO stermDBO : termlist)
				{
					if (!stermDBO.getTermId().toString().equals(termId))
					{
						result.setInfo("修改失败，已存在缺失学期");
						result.setStatus(3);
						return result;
					}
				}
				
			}
			sTermDBO.setStartTime(DateUtils.parseDate(startTime));
			sTermDBO.setEndTime(DateUtils.parseDate(endTime));
			sTermDBO.setIsDefault(param.getInteger("isDefaule"));
			sTermDBO.setIsUse(param.getInteger("isUse"));
			sTermDBO.setTermName(param.getString("termName"));
			sTermDBO.setTermId(Long.valueOf(termId));
			// 修改
			STermService_.doUpdate(sTermDBO);
		} catch (Exception e) {
			result.setInfo("修改失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 学校学期删除接口
	 * 
	 * @param jsonparam
	 * @return
	 */
	@RequestMapping(value = "/term", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean termDELETE(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		JSONObject param = JSONObject.parseObject(jsonparam);
		String token = param.getString("token");

		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			String termId = param.getString("termId");

			if (StringUtils.isNotEmpty(termId)) {
				// 乐观锁操作
				STermDBO dbo = new STermDBO();
				dbo.setTermId(Long.valueOf(termId));
				dbo = (STermDBO) STermService_.doRead(dbo);
				
				if (null != dbo)
				{
					// TOTO 校验学期是否被应用（未完成）
					
					STermService_.doDelete(dbo);
				}
				else
				{
					result.setInfo("没有对应的学期数据可以删除");
					result.setStatus(1);
				}
				STermService_.doDelete(dbo);
			}

		} catch (Exception e) {
			result.setInfo("删除失败");
			result.setStatus(1);
		}
		return result;
	}
}
