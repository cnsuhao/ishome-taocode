package com.mcookies.qxy.biz.moraleducation;

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
 * 德育个人管理
 * @author james
 *
 */
@Controller
public class MoraleducationPersonalController extends MyControllerSupport {
	
	
	
	/**
	 * 12.2.2德育个人评分列表查询接口 /qxy/moraleducation/personal/score?termId=[termId]&gradeId=[gradeId]&cid=[cid]&order=[order]&page=[page]&size=[size]&token=[token]
	 * 德育班级评分列表查询接口当学期id为空时，返回默认学期下班级列表数据。本接口支持分页查询班级评分列表
     * 需要使用Token验证查询者身份
	 */
	
	@RequestMapping(value = "/moraleducation/personal/score", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean moraleducationPersonalGET(Boolean test,Long termId,Long cid,String order,String token,Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {			
					String str = "{\"page\":\"0\",\"size\":\"14\",\"count\":\"3\",\"personalScoreList\":[{\"studentId\":\"1\",\"studentName\":\"张三\",\"number\":\"1000003\",\"totalScore\":\"90\",\"ranking\":\"12\",\"updateTime\":\"2016-09-19 00:00:00\"},{\"studentId\":\"2\",\"studentName\":\"王五\",\"number\":\"1000004\",\"totalScore\":\"93\",\"ranking\":\"11\",\"updateTime\":\"2016-09-19 00:00:00\"},{\"studentId\":\"3\",\"studentName\":\"赵六\",\"number\":\"1000005\",\"totalScore\":\"97\",\"ranking\":\"9\",\"updateTime\":\"2016-09-19 00:00:00\"}]}";
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
	 * 德育个人评分个人搜索接口  /qxy/moraleducation/personal/score/search?studentId=[studentId]&token=[token]
	 * 需要使用Token验证查询者身份
	 * 根据班级cid进行搜索，只用与班级搜索
	 * @param test
	 * @param termId
	 * @param order
	 * @param page
	 * @param size
	 * @return
	 */
			 
	@RequestMapping(value = "/moraleducation/personal/score/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean moraleducationPersonalSearchGET(Boolean test,Long studentId) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				if (studentId != null) {
					String str = "{\"studentId\":\"2\",\"studentName\":\"王五\",\"number\":\"1000004\",\"totalScore\":\"93\",\"ranking\":\"11\",\"updateTime\":\"2016-09-19 00:00:00\"}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
				} else{
					result.setInfo("学生id --必填项");
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
		 * 德育个人评分详情列表查询接口 /qxy/moraleducation/personal/score/list?studentId=[studentId]&page=[page]&size=[size]&token=[token]
		 * 需要使用Token验证查询者身份
		 * @param test
		 * @param page
		 * @param size
		 * @return
		 */
				 
		@RequestMapping(value = "/moraleducation/personal/score/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalScoreListGET(Boolean test,Long studentId,Integer page, Integer size) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					if (studentId != null) {
						String str = "{\"studentId\":\"1\",\"studentName\":\"段誉\",\"number\":\"0291029\",\"totalScore\":\"23\",\"ranking\":\"12\",\"page\":\"1\",\"size\":\"12\",\"count\":\"22\",\"mepScoreList\":[{\"mepScoreId\":\"1\",\"mepItemName\":\"黑板报\",\"score\":\"-1\",\"mepItemExplain\":\"扣分针对所有班级\",\"createTime\":\"2016-09-13 00:00:00\",\"teacherName\":\"王主任\"},{\"mepScoreId\":\"2\",\"mepItemName\":\"打扫卫生只加分不扣分\",\"score\":\"+2\",\"mepItemExplain\":\"只加分不扣分\",\"scoreTime\":\"2016-09-13 00:00:00\",\"teacherName\":\"王主任\"},{\"mepScoreId\":\"3\",\"mepItemName\":\"通报批评\",\"score\":\"-1\",\"mepItemExplain\":\"只减分不加分\",\"scoreTime\":\"2016-09-13 00:00:00\",\"teacherName\":\"王主任\"}]}";
						JSONObject jobj=JSON.parseObject(str);    
						result.setData(jobj);	
					} else{
						result.setInfo("学生id --必填项");
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
		 * 德育个人评分详情列表搜索接口 /qxy/moraleducation/personal/score/list/search?studentId=[studentId]&mepItemId=[mepItemId]&startTime=[startTime]&endTime=[endTime]&page=[page]&size=[size]&token=[token]
		 * 需要使用Token验证查询者身份
		 * @param test
		 * @param page
		 * @param size
		 * @return
		 */
				 
		@RequestMapping(value = "/moraleducation/personal/score/list/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalScoreListSearchGET(Boolean test,Long studentId,Long mepItemId,String startTime,String endTime,Integer page, Integer size) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
						String str = "{\"studentId\":\"1\",\"studentName\":\"段誉\",\"number\":\"0291029\",\"totalScore\":\"23\",\"ranking\":\"12\",\"page\":\"1\",\"size\":\"12\",\"count\":\"22\",\"mepScoreList\":[{\"mepScoreId\":\"1\",\"mepItemName\":\"黑板报\",\"score\":\"-1\",\"mepItemExplain\":\"扣分针对所有班级\",\"createTime\":\"2016-09-13 00:00:00\",\"teacherName\":\"王主任\"},{\"mepScoreId\":\"2\",\"mepItemName\":\"打扫卫生只加分不扣分\",\"score\":\"+2\",\"mepItemExplain\":\"只加分不扣分\",\"scoreTime\":\"2016-09-13 00:00:00\",\"teacherName\":\"王主任\"},{\"mepScoreId\":\"3\",\"mepItemName\":\"通报批评\",\"score\":\"-1\",\"mepItemExplain\":\"只减分不加分\",\"scoreTime\":\"2016-09-13 00:00:00\",\"teacherName\":\"王主任\"}]}";
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
		 * 德育个人评分新增接口/qxy/moraleducation/personal/score?token=[token]
		 * @param pvo
		 * @return
		 */
		@RequestMapping(value = "/moraleducation/personal/score", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalScorePOST(Boolean test,@RequestBody JSONObject josn) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					if (josn.get("studentId") != null && josn.get("mepItemId") != null && josn.get("scoreOrder") != null) {
//						String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//						JSONObject jobj=JSON.parseObject(str);    
//						result.setData(jobj);	
					} else{
						result.setInfo("字生id --必填,德育个人项目id --必填,德育个人评分序号（合理范围为1-6）--必填");
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
		 * 德育个人评分删除接口/qxy/moraleducation/personal/score?token=[token]
		 */
		@RequestMapping(value = "/moraleducation/personal/score", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalScoreDELETE(Boolean test,@RequestBody JSONObject josn) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					if (josn.get("mepScoreId") != null) {
//						String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//						JSONObject jobj=JSON.parseObject(str);    
//						result.setData(jobj);	
					} else{
						result.setInfo("德育个人评分id --必填");
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
		 * 德育班个人分详情查询接口/qxy/moraleducation/personal/score/info?mepScoreId=[mepScoreId]&token=[token]
		 * 需要使用Token验证查询者身份
		 */
				 
		@RequestMapping(value = "/moraleducation/personal/score/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalScoreInfoGET(Boolean test,Long mepScoreId) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					if (mepScoreId != null) {
						String str = "{\"mepItemName\":\"黑板报\",\"score\":\"-1\",\"mepItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"createTime\":\"2016-09-13 00:00:00\",\"teacherName\":\"王主任\",\"scoreOrder\":\"1\",\"scoreContent\":\"1\",\"scorePic\":[\"http://www.100xyun.com/upload/123.jpg\",\"http://www.100xyun.com/upload/123.jpg\"]}";
						JSONObject jobj=JSON.parseObject(str);    
						result.setData(jobj);	
					} else{
						result.setInfo(" 个人评分id --必填");
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
		 * 德育个人项目列表查询接口/qxy/moraleducation/personal?sid=[sid]&token=[token]
		 * 需要使用Token验证查询者身份
		 */
				 
		@RequestMapping(value = "/moraleducation/personal", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalGET(Boolean test,Long sid) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					
						String str = "{\"count\":\"12\",\"mepItemList\":[{\"mepItemId\":\"23\",\"mepItemName\":\"黑板报\",\"mepItemExplain\":\"扣分苍围针对所有班级\",\"ruleNum\":\"1\",\"initialScore\":\"0\",\"intervalScore\":\"2\",\"isUse\":\"1\"},{\"mepItemId\":\"24\",\"mepItemName\":\"大扫除\",\"mepItemExplain\":\"一次2分\",\"ruleNum\":\"3\",\"initialScore\":\"3\",\"intervalScore\":\"3\",\"isUse\":\"1\"},{\"mepItemId\":\"25\",\"mepItemName\":\"通报批评\",\"mepItemExplain\":\"扣分苍围针对所有班级\",\"ruleNum\":\"5\",\"initialScore\":\"5\",\"intervalScore\":\"5\",\"isUse\":\"1\"}]}";
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
		 *  德育个人项目新增接口/qxy/moraleducation/personal?token=[token]
		 * @param pvo
		 * @return
		 */
		@RequestMapping(value = "/moraleducation/personal", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalPOST(Boolean test,@RequestBody JSONObject josn) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					if (josn.get("sid") != null && josn.get("mepItemName") != null && josn.get("ruleNum") != null) {
//						String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//						JSONObject jobj=JSON.parseObject(str);    
//						result.setData(jobj);	
					} else{
						result.setInfo("学校id --必填项,德育个人项目名称 --必填项,规则分数量必须大于0  --必填项");
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
		 * 德育个人项目修改接口 /qxy/moraleducation/personal?token=[token]
		 */
		@RequestMapping(value = "/moraleducation/personal", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalPUT(Boolean test,@RequestBody JSONObject josn) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					if (josn.get("mepItemId") != null ) {
//						String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//						JSONObject jobj=JSON.parseObject(str);    
//						result.setData(jobj);	
					} else{
						result.setInfo("德育个人项目id --必填项");
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
		 * 德育个人项目停用/启用接口  /qxy/moraleducation/personal/set?token=[token]
		 */
		@RequestMapping(value = "/moraleducation/personal/set", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalSetPUT(Boolean test,@RequestBody JSONObject josn) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					if (josn.get("mepItemId") != null && josn.get("isUse") != null) {
//						String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//						JSONObject jobj=JSON.parseObject(str);    
//						result.setData(jobj);	
					} else{
						result.setInfo("德育个人项目id --必填项,0-启用；1-停用 --必填项");
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
		 * 德育个人项目删除接口  /qxy/moraleducation/personal?token=[token]
		 */
		@RequestMapping(value = "/moraleducation/personal", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalDELETE(Boolean test,@RequestBody JSONObject josn) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					if (josn.get("mepItemId") != null) {
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
		
		/**
		 * 12.2.14 德育个人项目详情查询接口/qxy/moraleducation/personal/info?mepItemId=[mepItemId]&token=[token]
		 * 德育个人项目详情查询接口提供对德育个人项目详情查询的功能。
		 * 需要使用Token验证查询者身份
		 */
				 
		@RequestMapping(value = "/moraleducation/personal/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalInfoGET(Boolean test,Long mepItemId) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					if (mepItemId != null) {
						String str = "{\"mepItemId\":\"23\",\"mepItemName\":\"黑板报\",\"mepItemExplain\":\"扣分苍围针对所有班级\",\"ruleNum\":\"1\",\"initialScore\":\"0\",\"intervalScore\":\"2\",\"isUse\":\"1\"}";
						JSONObject jobj=JSON.parseObject(str);    
						result.setData(jobj);	
					} else{
						result.setInfo("德育个人项目id   ---必填项");
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
