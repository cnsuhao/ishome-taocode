package com.mcookies.qxy.biz.course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherDBO;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherService;
import com.mcookies.qxy.common.SWorkRule.SWorkRuleDBO;
import com.mcookies.qxy.common.SWorkRule.SWorkRuleService;
import com.mcookies.qxy.common.SWorkTime.SWorkTimeDBO;
import com.mcookies.qxy.common.SWorkTime.SWorkTimeService;

/**
 * 班级课表查询
 * 
 * @author macBookTang
 *
 */
@Controller
@Transactional
public class ClassTimetableController extends MyControllerSupport {
	@Resource
	protected ClassService ClassService_;
	@Resource
	protected SWorkTimeService SWorkTimeService_;
	@Resource
	protected SWorkRuleService SWorkRuleService_;
	@Resource
	protected ClassCourseService ClassCourseService_;
	@Resource
	protected ClassTeacherService ClassTeacherService_;
	/**
	 * 班级课表查询接口
	 * @param cid
	 * @param token
	 * @param day
	 * @return
	 */
	@RequestMapping(value = "/syllabus", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean syllabusGET(Long cid,String token,String startTime,String endTime) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if(cid == null){
				result.setInfo("访问失败,cid为空");
				result.setStatus(2);
				return result;
			}
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("cid",cid);
			if((startTime!=null&&!"".equals(startTime))&&(endTime!=null&&!"".equals(endTime))){
				param.put("startTime", startTime);
				param.put("endTime", endTime);
			}
			JSONObject data = new JSONObject();
			//获取班级信息
			ClassDBO cdbo = new ClassDBO();
			cdbo.setCid(cid);
			cdbo =(ClassDBO)ClassService_.doRead(cdbo);
			if(cdbo == null){
				result.setInfo("cid不存在");
				result.setStatus(2);
				return result;
			}
			//获取班级对应的作息模板
			SWorkTimeDBO swork = new SWorkTimeDBO();
			swork.setWorkId(cdbo.getWorkId());
			swork = (SWorkTimeDBO)SWorkTimeService_.doRead(swork);
			data.put("workId", swork.getWorkId());
			data.put("workName", swork.getWorkName());
			//得到对应的规则列表
			SWorkRuleDBO ruledbo = new SWorkRuleDBO();
			ruledbo.setWorkId(swork.getWorkId());
			pageModel.config();
			pageModel.setPageCurrent(1);
			pageModel.setPageLimit(100);
			pageModel.setFormParamBean(ruledbo);
			pageModel.setOrderby("wrule_stage asc");
			SWorkRuleService_.doSelectPage(pageModel);
			List<SWorkRuleDBO> rulelist = (List<SWorkRuleDBO>)pageModel.getPageListData();
			data.put("workRule",rulelist);
			//课表日期列表
			JSONArray syllabus = new JSONArray();
			List<ClassCourseDBO> daylist = ClassCourseService_.doSelectUseDayList(param);
			if(daylist!=null){
				for(ClassCourseDBO tmp:daylist){
					JSONObject syll = new JSONObject();
					syll.put("date", tmp.getUseDay());
					ClassCourseDBO ct = new ClassCourseDBO();
					ct.setCid(cid);
					ct.setUseDay(tmp.getUseDay());
					//得到这一天的课程列表，并按照class_time排序
					List<ClassCourseDBO> courselist =ClassCourseService_.doSelectCourseOnDay(ct);
					syll.put("courseteacher", courselist);
					syllabus.add(syll);
				}
			}
			data.put("syllabus", syllabus);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 班级课表新增接口
	 * @param jsonparam
	 * @return
	 */
	@RequestMapping(value = "/syllabus", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean syllabusPOST(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
			String token = (String) param.get("token");
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			Long cid = param.getLong("cid");
			//校验cid
			ClassDBO cdbo = new ClassDBO();
			cdbo.setCid(cid);
			cdbo =(ClassDBO)ClassService_.doRead(cdbo);
			if(cdbo == null){
				result.setInfo("cid不存在");
				result.setStatus(2);
				return result;
			}
			Long termId = param.getLong("termId");
			JSONArray syllabus = param.getJSONArray("syllabus");
			for(Object tmp1:syllabus){
				JSONObject json1 = (JSONObject)tmp1;
				String date = json1.getString("date");
				JSONArray courseteacher = json1.getJSONArray("courseteacher");
				for(Object tmp2:courseteacher){
					JSONObject json2 = (JSONObject)tmp2;
					ClassCourseDBO classbean = JSONObject.toJavaObject(json2, ClassCourseDBO.class);
					classbean.setIsUse(1);
					classbean.setCid(cid);
					classbean.setTerm(termId);
					classbean.setUseDay(date);
					ClassCourseService_.doInsert(classbean);
					//判断教师是否已经关联
					ClassTeacherDBO has = new ClassTeacherDBO();
					has.setCid(cid);
					has.setTid(classbean.getTid());
					List<ClassTeacherDBO> haslist =(List<ClassTeacherDBO>)ClassTeacherService_.doSelectData(has);
					if(haslist==null||haslist.size()==0){
						ClassTeacherDBO ctdbo = new ClassTeacherDBO();
						ctdbo.setCid(cid);
						ctdbo.setCourseId(classbean.getCourseId());
						ctdbo.setTid(classbean.getTid());
						ctdbo.setIsLeader(0);
						ctdbo.setIsUse(1);
						ClassTeacherService_.doInsert(ctdbo);
					}
				}
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	
	/**
	 * 班级课表修改及删除接口
	 * @param jsonparam
	 * @return
	 */
	@RequestMapping(value = "/syllabus", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean syllabusPUT(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
			String token = (String) param.get("token");
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			JSONArray syllabus = param.getJSONArray("courseteacher");
			for(Object tmp2:syllabus){
					JSONObject json2 = (JSONObject)tmp2;
					ClassCourseDBO classbean = JSONObject.toJavaObject(json2, ClassCourseDBO.class);
					ClassCourseService_.doUpdate(classbean);
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 班级课表应用到其他周接口
	 * @param jsonparam
	 * @return
	 */
	@RequestMapping(value = "/syllabus/copy", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean syllabusCopyPUT(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
			String token = (String) param.get("token");
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			Long cid = param.getLong("cid");
			//校验cid
			ClassDBO cdbo = new ClassDBO();
			cdbo.setCid(cid);
			cdbo =(ClassDBO)ClassService_.doRead(cdbo);
			if(cdbo == null){
				result.setInfo("cid不存在");
				result.setStatus(2);
				return result;
			}
			Long termId = param.getLong("termId");
			JSONArray sourcesyllabus = param.getJSONArray("sourcesyllabus");
			JSONArray targetweeks = param.getJSONArray("targetweeks");
			for(Object target:targetweeks){
				JSONArray targetjson = (JSONArray)target;
				for(int i=0;i<sourcesyllabus.size();i++){
					String useDay = String.valueOf(targetjson.get(i));
					//得到一天的课程
					String cday = String.valueOf(sourcesyllabus.get(i));
					ClassCourseDBO ct = new ClassCourseDBO();
					ct.setCid(cid);
					ct.setUseDay(cday);
					ct.setTerm(termId);
					//得到这一天的课程列表，并按照class_time排序
					List<ClassCourseDBO> courselist =ClassCourseService_.doSelectCourseOnDay(ct);
					for(ClassCourseDBO cctmp:courselist){
						cctmp.setId(null);
						cctmp.setUseDay(useDay);
						//cctmp.setTerm(termId);
						ClassCourseService_.doInsert(cctmp);
					}
				}
			}
			
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
}
