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
import com.mcookies.qxy.common.Class.ClassDBO;
import com.mcookies.qxy.common.Class.ClassService;
import com.mcookies.qxy.common.ClassCourse.ClassCourseDBO;
import com.mcookies.qxy.common.ClassCourse.ClassCourseService;
import com.mcookies.qxy.common.SCalendar.SCalendarDBO;
import com.mcookies.qxy.common.SCalendar.SCalendarService;
import com.mcookies.qxy.common.SDutyScheduling.SDutySchedulingDBO;
import com.mcookies.qxy.common.SDutyScheduling.SDutySchedulingService;
import com.mcookies.qxy.common.STerm.STermDBO;
import com.mcookies.qxy.common.STerm.STermPVO;
import com.mcookies.qxy.common.STerm.STermService;
import com.mcookies.qxy.common.STrip.STripDBO;
import com.mcookies.qxy.common.STrip.STripService;
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
	@Resource
	protected ClassService ClassService_;
	@Resource
	protected ClassCourseService ClassCourseService_;
	@Resource
	protected SDutySchedulingService SDutySchedulingService_;
	@Resource
	protected STripService STripService_;
	
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
								calJson.put("startTime",sCalendarDBO.getStartTime());
								calJson.put("endTime",sCalendarDBO.getEndTime());
								calArray.add(calJson);
							}
							term.put("calendarstatus", calArray);
						}
					}
					term.put("startTime",sTermDBO.getStartTime());
					term.put("endTime",sTermDBO.getEndTime());
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
					term.put("startTime",sTermDBO.getStartTime());
					term.put("endTime", sTermDBO.getEndTime());
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
	public RESTResultBean termPOST(String token, @RequestBody STermPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			// 校验学期名是否已经存在
			STermDBO sTermDBO = new STermDBO();
			sTermDBO.setTermName(pvo.getTermName());
			@SuppressWarnings("unchecked")
			List<STermDBO> termlist = (List<STermDBO>) STermService_.doSelectData(sTermDBO);
			if (termlist != null && !termlist.isEmpty()) {
				result.setInfo("新增失败，学期名已存在");
				result.setStatus(1);
				return result;
			}
			// 默认添加的学期为启用状态
			pvo.setIsUse(1);
			pvo.setIsDefault(0);
			// 新增
			STermService_.doInsert(pvo);
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
	public RESTResultBean termPUT(String token, @RequestBody STermPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			//检验该数据是否被系统使用
			//班级关联
			ClassDBO cdbo = new ClassDBO();
			cdbo.setTermId(pvo.getTermId());
			List<ClassDBO> classlist = (List<ClassDBO>)ClassService_.doSelectData(cdbo);
			if(classlist!=null&&classlist.size()>0){
				result.setInfo("修改失败,该学期已被系统使用");
				result.setStatus(1);
				return result;
			}
			//课程关联
			ClassCourseDBO classdbo = new ClassCourseDBO();
			classdbo.setTerm(pvo.getTermId());
			List<ClassCourseDBO> cclist =(List<ClassCourseDBO>)ClassCourseService_.doSelectData(classdbo);
			if(cclist!=null&&cclist.size()>0){
				result.setInfo("修改失败,该学期已被系统使用");
				result.setStatus(1);
				return result;
			}
			//校历关联
			SCalendarDBO scdbo = new SCalendarDBO();
			scdbo.setTermId(pvo.getTermId());
			List<SCalendarDBO> sclist =(List<SCalendarDBO>)SCalendarService_.doSelectData(scdbo);
			if(sclist!=null&&sclist.size()>0){
				result.setInfo("修改失败,该学期已被系统使用");
				result.setStatus(1);
				return result;
			}
			//行程
			STripDBO stdbo = new STripDBO();
			stdbo.setTermId(pvo.getTermId());
			List<STripDBO> stlist = (List<STripDBO>)STripService_.doSelectData(stdbo);
			if(stlist!=null&&stlist.size()>0){
				result.setInfo("修改失败,该学期已被系统使用");
				result.setStatus(1);
				return result;
			}
			//值日值周
			SDutySchedulingDBO sdutyDBO = new SDutySchedulingDBO();
			sdutyDBO.setTermId(pvo.getTermId());
			List<SDutySchedulingDBO> sdutyList=(List<SDutySchedulingDBO>)SDutySchedulingService_.doSelectData(sdutyDBO);
			if(sdutyList!=null&&sdutyList.size()>0){
				result.setInfo("修改失败,该学期已被系统使用");
				result.setStatus(1);
				return result;
			}			
			// 校验学期名是否已经存在
			STermDBO sTermDBO = new STermDBO();
			sTermDBO.setTermName(pvo.getTermName());
			List<STermDBO> termlist = (List<STermDBO>) STermService_.doSelectData(sTermDBO);
			if (termlist != null && !termlist.isEmpty()) {
				for (STermDBO stermDBO : termlist)
				{
					if (stermDBO.getTermId()!=pvo.getTermId())
					{
						result.setInfo("修改失败，学期名已存在");
						result.setStatus(1);
						return result;
					}
				}
			}
			//检查此次是否有设置缺省学期操作
			if(pvo.getIsDefault()!=null&&pvo.getIsDefault()==1){
				//其他学期设置为 非缺省
				STermDBO sterm = new STermDBO();
				sterm.setIsDefault(0);
				STermService_.doUpdateAll(sterm);
			}
			//更新操作
			STermService_.doUpdate(pvo);
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
