package com.mcookies.qxy.biz.moraleducation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherDBO;
import com.mcookies.qxy.common.News.NewsPVO;
import com.mcookies.qxy.common.SCourse.SCourseDBO;
import com.mcookies.qxy.common.STrip.STripPVO;
import com.mcookies.qxy.common.UTeacher.UTeacherDBO;

/**
 * 德育班级管理
 * @author james
 *
 */
@Controller
public class MoraleducationClassController extends MyControllerSupport {
	
	
	/**
	 * 德育班级评分列表查询接口 /qxy/moraleducation/class?termId=[termId]&order=[order]&page=[page]&size=[size]&token=[token]
	 * 德育班级评分列表查询接口当学期id为空时，返回默认学期下班级列表数据。本接口支持分页查询班级评分列表
     * 需要使用Token验证查询者身份
	 */
	
	@RequestMapping(value = "/moraleducation/class/score", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean moraleducationClassGET(Boolean test,Long termId,String order,Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {					
					String str = "{\"page\":\"1\",\"size\":\"14\",\"count\":\"3\",\"classScoreList\": [{\"cid\": \"4\",\"className\": \"初一1班\",\"totalScore\": \"90\",\"ranking\": \"12\",\"updateTime\":\"2016-09-19 00:00:00\"},{\"cid\": \"4\",\"className\": \"初一2班\",\"totalScore\": \"92\",\"ranking\": \"11\",\"updateTime\":\"2016-09-19 00:00:00\"}]}";
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
	 * 德育班级评分班级搜索接口 /qxy/moraleducation/class/search?cid=[cid]&token=[token]
	 * 需要使用Token验证查询者身份
	 * 根据班级cid进行搜索，只用与班级搜索
	 * @param test
	 * @return
	 */
			 
	@RequestMapping(value = "/moraleducation/class/score/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean moraleducationClassSearchGET(Boolean test,Long cid) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				if (cid != null) {
					String str = "{\"cid\":\"4\",\"className\":\"初一1班\",\"totalScore\":\"90\",\"ranking\":\"12\",\"updateTime\":\"2016-09-19 00:00:00\"}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
				} else{
					result.setInfo("班级id --必填项");
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
		 * 德育班级评分详情列表查询接口 /qxy/moraleducation/class/score/list?cid=[cid]&page=[page]&size=[size]&token=[token]
		 * 需要使用Token验证查询者身份
		 * @param test
		 * @param page
		 * @param size
		 * @return
		 */
				 
		@RequestMapping(value = "/moraleducation/class/score/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecClassScoreListGET(Boolean test,Long cid,Integer page, Integer size) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					if (cid != null) {
						String str = "{\"termName\":\"2016-2017-2\",\"className\":\"高一三班\",\"totalScore\":\"20\",\"ranking\":\"12\",\"page\":\"2\",\"size\":\"12\",\"count\":\"20\",\"mecScoreList\":[{\"mecScoreId\":\"1\",\"mecItemId\":\"1\",\"mecItemName\":\"黑板报\",\"score\":\"-1\",\"teacherName\":\"王主任\",\"mecItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"createTime\":\"2016-09-13 00:00:00\"},{\"mecScoreId\":\"2\",\"mecItemId\":\"2\",\"mecItemName\":\"黑板报1\",\"score\":\"-2\",\"teacherName\":\"王主任\",\"mecItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"createTime\":\"2016-09-13 00:00:00\"}]}";
						JSONObject jobj=JSON.parseObject(str);    
						result.setData(jobj);	
					} else{
						result.setInfo("班级id --必填项");
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
		 * 德育班级评分详情列表搜索接口 /qxy/moraleducation/class/score/list/search?mecItemId=[mecItemId]&startTime=[startTime]&endTime=[endTime]&page=[page]&size=[size]&token=[token]
		 * 需要使用Token验证查询者身份
		 * @param test
		 * @param page
		 * @param size
		 * @return
		 */
				 
		@RequestMapping(value = "/moraleducation/class/score/list/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecClassScoreListSearchGET(Boolean test,Long mecItemId,String startTime,String endTime,Integer page, Integer size) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
						String str = "{\"termName\":\"2016-2017-2\",\"className\":\"高一三班\",\"totalScore\":\"20\",\"ranking\":\"12\",\"page\":\"2\",\"size\":\"12\",\"count\":\"20\",\"mecScoreList\":[{\"mecScoreId\":\"1\",\"mecItemId\":\"1\",\"mecItemName\":\"黑板报\",\"score\":\"-1\",\"teacherName\":\"王主任\",\"mecItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"createTime\":\"2016-09-13 00:00:00\"},{\"mecScoreId\":\"2\",\"mecItemId\":\"2\",\"mecItemName\":\"黑板报1\",\"score\":\"-2\",\"teacherName\":\"王主任\",\"mecItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"createTime\":\"2016-09-13 00:00:00\"}]}";
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
		 * 德育班级评分新增接口/qxy/moraleducation/class/score?token=[token]
		 * @param pvo
		 * @return
		 */
		@RequestMapping(value = "/moraleducation/class/score", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecClassScorePOST(Boolean test,@RequestBody JSONObject josn) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					if (josn.get("cid") != null && josn.get("mecItemId") != null && josn.get("scoreOrder") != null) {
//						String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//						JSONObject jobj=JSON.parseObject(str);    
//						result.setData(jobj);	
					} else{
						result.setInfo("班级id --必填项,德育项目id --必填,德育班级评分序号（合理范围为1-6）--必填");
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
		 * 德育班级评分删除接口/qxy/moraleducation/class/score?token=[token]
		 */
		@RequestMapping(value = "/moraleducation/class/score", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecClassScoreDELETE(Boolean test,@RequestBody JSONObject josn) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					if (josn.get("mecScoreId") != null) {
//						String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//						JSONObject jobj=JSON.parseObject(str);    
//						result.setData(jobj);	
					} else{
						result.setInfo("德育项目id --必填");
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
		 * 德育班级评分详情查询接口/qxy/moraleducation/class/score/info?mecScoreId=[mecScoreId]&token=[token]
		 * 需要使用Token验证查询者身份
		 */
				 
		@RequestMapping(value = "/moraleducation/class/score/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecClassScoreInfoGET(Boolean test,Long mecScoreId) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					if (mecScoreId != null) {
						String str = "{\"mecItemName\":\"黑板报\",\"score\":\"-1\",\"mecItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"createTime\":\"2016-09-13 00:00:00\",\"teacherName\":\"王主任\",\"scoreOrder\":\"1\",\"scoreContent\":\"1\",\"scorePic\":[\"http://www.100xyun.com/upload/123.jpg\",\"http://www.100xyun.com/upload/123.jpg\"]}";
						JSONObject jobj=JSON.parseObject(str);    
						result.setData(jobj);	
					} else{
						result.setInfo(" 班级评分id  --必填项");
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
		 * 德育班级项目列表查询接口/qxy/moraleducation/class?sid=[sid]&token=[token]
		 * 需要使用Token验证查询者身份
		 */
				 
		@RequestMapping(value = "/moraleducation/class", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecClassGET(Boolean test,Long sid) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					
						String str = "{\"count\":\"12\",\"mecItemList\":[{\"mecItemId\":\"23\",\"mecItemName\":\"黑板报\",\"mecItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"ruleNum\":\"1\",\"initialScore\":\"0\",\"intervalScore\":\"2\",\"isUse\":\"1\"},{\"mecItemId\":\"24\",\"mecItemName\":\"黑板报\",\"mecItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"ruleNum\":\"1\",\"initialScore\":\"0\",\"intervalScore\":\"2\",\"isUse\":\"1\"},{\"mecItemId\":\"25\",\"mecItemName\":\"黑板报\",\"mecItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"ruleNum\":\"1\",\"initialScore\":\"0\",\"intervalScore\":\"2\",\"isUse\":\"1\"}]}";
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
		 *  德育班级项目新增接口/qxy/moraleducation/class?token=[token]
		 * @param pvo
		 * @return
		 */
		@RequestMapping(value = "/moraleducation/class", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecClassPOST(Boolean test,@RequestBody JSONObject josn) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					if (josn.get("cid") != null && josn.get("mecItemName") != null && josn.get("ruleNum") != null) {
//						String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//						JSONObject jobj=JSON.parseObject(str);    
//						result.setData(jobj);	
					} else{
						result.setInfo("班级id --必填项,德育班级项目名称  --必填项,规则分数量必须大于0  --必填项");
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
		 * 德育班级项目修改接口 /qxy/moraleducation/class?token=[token]
		 */
		@RequestMapping(value = "/moraleducation/class", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecClassPUT(Boolean test,@RequestBody JSONObject josn) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					if (josn.get("mecItemId") != null ) {
//						String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//						JSONObject jobj=JSON.parseObject(str);    
//						result.setData(jobj);	
					} else{
						result.setInfo("德育班级项目id --必填项");
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
		 * 德育班级项目停用/启用接口  /qxy/moraleducation/class/set?token=[token]
		 */
		@RequestMapping(value = "/moraleducation/class/set", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecClassSetPUT(Boolean test,@RequestBody JSONObject josn) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					if (josn.get("mecItemId") != null && josn.get("isUse") != null) {
//						String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//						JSONObject jobj=JSON.parseObject(str);    
//						result.setData(jobj);	
					} else{
						result.setInfo("德育班级项目id --必填项,0-启用；1-停用 --必填项");
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
		 * 德育班级项目删除接口  /qxy/moraleducation/class?token=[token]
		 */
		@RequestMapping(value = "/moraleducation/class", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecClassDELETE(Boolean test,@RequestBody JSONObject josn) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					if (josn.get("mecItemId") != null) {
//						String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//						JSONObject jobj=JSON.parseObject(str);    
//						result.setData(jobj);	
					} else{
						result.setInfo("评分id --必填项");
					}
				} else {

				}
			} catch (Exception e) {
				result.setInfo("删除失败，" + e.getMessage());
				result.setStatus(1);
			}
			return result;
		}	
		
		
}
