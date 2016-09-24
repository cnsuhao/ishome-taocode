package com.mcookies.qxy.biz.scoremanage;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * 成绩标签管理
 * @author james
 *
 */
@Controller
public class ResultsTagController extends MyControllerSupport {

	/**
	 * 成绩标签列表查询接口 /qxy/results/tag?termId=[termId]&page=[page]&size=[size]&token=[token]
     * 需要使用Token验证查询者身份
	 */
	
	@RequestMapping(value = "/results/tag", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsTagGET(Boolean test,Long termId,Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {					
					String str = "{\"termName\":\"2016-2017-2\",\"page\":\"2\",\"size\":\"12\",\"count\":\"20\",\"resultsTagList\":[{\"resultsTagId\":\"1\",\"resultsTagName\":\"2016年9月校考模拟考试\",\"resultsTagExplain\":\"这是一场摸底考试\",\"startTime\":\"2016-8-9 00:00:00\",\"endTime\":\"2016-8-10 00:00:00\",\"createTime\":\"2016-8-10 00:00:00\"},{\"resultsId\":\"2\",\"resultsTagName\":\"2016年9月校考模拟考试1\",\"resultsTagExplain\":\"这是一场摸底考试2\",\"startTime\":\"2016-9-9 00:00:00\",\"endTime\":\"2016-9-10 00:00:00\",\"createTime\":\"2016-9-10 00:00:00\"}]}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
			} else {

			}	
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 *  13.1.2 成绩标签新增接口 /qxy/results/tag?token=[token]
	 *  成绩标签默认有学校属性，新增时接口需要根据token中sid进行关联；
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/results/tag", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsTagPOST(Boolean test,@RequestBody JSONObject josn) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				if (josn.get("termId") != null && josn.get("resultsTagName") != null) {
//					String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//					JSONObject jobj=JSON.parseObject(str);    
//					result.setData(jobj);	
				} else{
					result.setInfo("学期id--必填项,成绩标签名称--必填项");
				}
			} else {

			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 13.1.3 成绩标签修改接口 /qxy/results/tag?token=[token]
	 */
	@RequestMapping(value = "/results/tag", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsTagPUT(Boolean test,@RequestBody JSONObject josn) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				if (josn.get("resultsTagId") != null ) {
//					String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//					JSONObject jobj=JSON.parseObject(str);    
//					result.setData(jobj);	
				} else{
					result.setInfo("成绩标签id--必填项");
				}
			} else {

			}
	
		} catch (Exception e) {
			result.setInfo("修改失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}


	/**
	 * 13.1.4 成绩标签删除接口  /qxy/results/tag?token=[token]
	 */
	@RequestMapping(value = "/results/tag", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsTagDELETE(Boolean test,@RequestBody JSONObject josn) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				if (josn.get("resultsTagId") != null) {
//					String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//					JSONObject jobj=JSON.parseObject(str);    
//					result.setData(jobj);	
				} else{
					result.setInfo("成绩标签id--必填项");
				}
			} else {

			}
		} catch (Exception e) {
			result.setInfo("删除失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}	
	
	/**
	 * 13.1.5 成绩标签名字搜索接口  /qxy/results/tag/search?termId=[termId]&resultsTagName=[resultsTagName]&token=[token]
	 * 需要使用Token验证查询者身份
	 * 根据班级cid进行搜索，只用与班级搜索
	 * @param test
	 * @return
	 */
			 
	@RequestMapping(value = "/results/tag/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsTagSearchGET(Boolean test,Long termId,String resultsTagName) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				if (termId != null && resultsTagName != null ) {
					String str = "{\"termName\":\"2016-2017-2\",\"count\":\"2\",\"resultsTagList\":[{\"resultsTagId\":\"1\",\"resultsTagName\":\"2016年9月校考模拟考试\",\"resultsTagExplain\":\"这是一场摸底考试\",\"startTime\":\"2016-8-9 00:00:00\",\"endTime\":\"2016-8-10 00:00:00\",\"createTime\":\"2016-8-10 00:00:00\"},{\"resultsId\":\"2\",\"resultsTagName\":\"2016年9月校考模拟考试1\",\"resultsTagExplain\":\"这是一场摸底考试2\",\"startTime\":\"2016-9-9 00:00:00\",\"endTime\":\"2016-9-10 00:00:00\",\"createTime\":\"2016-9-10 00:00:00\"}]}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
				} else{
					result.setInfo("学期Id--必填项, 成绩标签名称--必填项");
				}
			} else {

			}			
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}		 

	/**
	 * 13.1.6 成绩标签详情查询接口/qxy/results/tag/info?resultsTagId=[resultsTagId]&token=[token]
	 * 需要使用Token验证查询者身份
	 */
			 
	@RequestMapping(value = "/results/tag/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsTagInfoGET(Boolean test,Long resultsTagId) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				if (resultsTagId != null) {
					String str = "{\"resultsTagId\":\"1\",\"resultsTagName\":\"2016年9月校考模拟考试\",\"resultsTagExplain\":\"这是一场摸底考试\",\"startTime\":\"2016-8-9 00:00:00\",\"endTime\":\"2016-8-10 00:00:00\",\"createTime\":\"2016-8-10 00:00:00\"}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
				} else{
					result.setInfo("成绩标签id--必填项");
				}
			} else {

			}
			
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}
	
	
	
}
