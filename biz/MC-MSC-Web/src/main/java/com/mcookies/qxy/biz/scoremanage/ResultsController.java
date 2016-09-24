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
 * 成绩表管理
 * @author james
 *
 */
@Controller
public class ResultsController extends MyControllerSupport {

	/**
	 * 13.2.3 班级学生成绩列表查询接口/qxy/results?resultsTagId=[resultsTagId]&cid=[cid]&page=[page]&size=[size]&token=[token]
     * 需要使用Token验证查询者身份
	 */
	
	@RequestMapping(value = "/results", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsGET(Boolean test,Long resultsTagId,Long cid,Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {					
				if (cid != null && resultsTagId != null ) {
					String str = "{\"resultsTagName\":\"期中考试\",\"resultsTagId\":\"2\",\"cid\":\"12\",\"className\":\"高一三班\",\"page\":\"2\",\"size\":\"12\",\"count\":\"23\",\"studentlist\":[{\"studentId\":\"1\",\"studentName\":\"张三三\",\"number\":\"01021200\",\"totalPoints\":\"603\",\"ranking\":\"2\"},{\"studentId\":\"2\",\"studentName\":\"张四三\",\"number\":\"01021201\",\"totalPoints\":\"601\",\"ranking\":\"3\"}]}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
				} else{
					result.setInfo("班级id--必填项, 成绩标签id--必填项");
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
	 * 13.2.4 班级学生成绩搜索接口 /qxy/results/search?resultsTagId=[resultsTagId]&studentId=[studentId]&number=[number]&token=[token]
	 * 需要使用Token验证查询者身份
	 * 根据班级cid进行搜索，只用与班级搜索
	 * @param test
	 * @return
	 */
			 
	@RequestMapping(value = "/results/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsSearchGET(Boolean test,Long resultsTagId,Long studentId,Long number,String resultsTagName) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				if (resultsTagId != null && (resultsTagName != null ||number != null) ) {
					String str = "{\"resultsTagName\":\"期中考试\",\"resultsTagId\":\"2\",\"cid\":\"12\",\"className\":\"高一三班\",\"studentlist\":{\"studentId\":\"1\",\"studentName\":\"张三三\",\"number\":\"01021200\",\"totalPoints\":\"603\",\"ranking\":\"2\"}}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
				} else{
					result.setInfo("成绩标签id--必填项, 学生id--选填（id学号二选一），学生学号--选填（id学号二选一）");
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
	 * 13.2.5 班级学生成绩删除接口  /qxy/results/student?token=[token]
	 */
	@RequestMapping(value = "/results/student", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsStudentDELETE(Boolean test,@RequestBody JSONObject josn) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				if (josn.get("resultsTagId") != null&& josn.get("studentId") != null) {
//					String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//					JSONObject jobj=JSON.parseObject(str);    
//					result.setData(jobj);	
				} else{
					result.setInfo("成绩标签id--必填项, 学生id--必填项");
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
	 * 13.2.6 学生科目成绩列表查询接口 /qxy/results/student/course?resultsTagId=[resultsTagId]&studentId=[studentId]&token=[token]
     * 需要使用Token验证查询者身份
	 */
	
	@RequestMapping(value = "/results/student/course", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsStudentCourseGET(Boolean test,Long resultsTagId,Long studentId) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {					
				if (resultsTagId != null&& studentId != null ) {
					String str = "{\"resultsTagName\":\"期中考试\",\"resultsTagId\":\"2\",\"cid\":\"12\",\"className\":\"高一三班\",\"studentId\":\"1\",\"studentName\":\"张三三\",\"number\":\"01021200\",\"totalPoints\":\"603\",\"courseScoreList\":[{\"courseId\":\"1\",\"courseName\":\"语文\",\"courseScore\":\"89\",\"courseRanking\":\"5\",\"updateTime\":\"2016-8-9 00:00:00\"},{\"courseId\":\"2\",\"courseName\":\"数学\",\"courseScore\":\"89\",\"courseRanking\":\"5\",\"updateTime\":\"2016-8-9 00:00:00\"}]}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
				} else{
					result.setInfo("成绩标签id--必填项, 学生id--必填项");
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
	 *  13.2.7 学生科目成绩新增接口/qxy/results/student/course?token=[token]
	 *  
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/results/student/course", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsStudentCoursePOST(Boolean test,@RequestBody JSONObject josn) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				if (josn.get("resultsTagId") != null && josn.get("studentId") != null&&josn.get("courseId") != null && josn.get("score") != null) {
//					String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//					JSONObject jobj=JSON.parseObject(str);    
//					result.setData(jobj);	
				} else{
					result.setInfo("成绩标签id--必填项,学生id--必填项,科目id--必填项,学生科目分数--必填项");
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
	 * 13.2.8 学生科目成绩修改接口  /qxy/results/student/course?token=[token]
	 */
	@RequestMapping(value = "/results/student/course", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsStudentCoursePUT(Boolean test,@RequestBody JSONObject josn) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				if (josn.get("resultsId") != null && josn.get("score") != null ) {
//					String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//					JSONObject jobj=JSON.parseObject(str);    
//					result.setData(jobj);	
				} else{
					result.setInfo("成绩id--必填项,学生科目分数--必填项");
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
	 * 13.2.9 学生科目成绩删除接口  /qxy/results/student/course?token=[token]
	 */
	@RequestMapping(value = "/results/student/course", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsStudentCourseDELETE(Boolean test,@RequestBody JSONObject josn) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				if (josn.get("resultsId") != null) {
//					String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//					JSONObject jobj=JSON.parseObject(str);    
//					result.setData(jobj);	
				} else{
					result.setInfo("成绩id--必填项");
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
	 *  13.2.10 班级学生成绩导入接口/qxy/import/results/course?token=[token]
	 *  先通过2.1.6 资源上传接口上传excel返回资源地址，再调用这个接口导入班级学生成绩
	 */
	@RequestMapping(value = "/import/results/course", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean importResultsCoursePOST(Boolean test,@RequestBody JSONObject josn) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				if (josn.get("resultsTagId") != null && josn.get("cid") != null&&josn.get("courseId") != null && josn.get("excel") != null) {
//					String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//					JSONObject jobj=JSON.parseObject(str);    
//					result.setData(jobj);	
				} else{
					result.setInfo("成绩标签id--必填项,班级id--必填项,科目id--必填项, excel地址--必填项");
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
	 * 13.2.11 考试科目成绩删除接口/qxy/results/course?token=[token]
	 */
	@RequestMapping(value = "/results/course", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsCourseDELETE(Boolean test,@RequestBody JSONObject josn) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				if (josn.get("resultsTagId") != null && josn.get("cid") != null && josn.get("courseId") != null) {
	//				String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
	//				JSONObject jobj=JSON.parseObject(str);    
	//				result.setData(jobj);	
				} else{
					result.setInfo("成绩标签id--必填项,班级id--必填项,科目id--必填项");
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
	 * 13.2.12 班级科目列表查询接口/qxy/results/class/course?resultsTagId=[resultsTagId]&cid=[cid]&token=[token]
	 * 需要使用Token验证查询者身份
	 * 仅获取所有有效的课程
	 * status值需要接口根据cid+resultsTagId+courseId联合索引，如果有值则为1，没有值则为0；
	 */
	
	@RequestMapping(value = "/results/class/course", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsClassCourseGET(Boolean test,Long resultsTagId,Long cid) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {					
				if (cid != null && resultsTagId != null ) {
					String str = "{\"resultsTagName\":\"期中考试\",\"resultsTagId\":\"2\",\"cid\":\"12\",\"className\":\"高一三班\",\"courseList\":[{\"courseId\":\"1\",\"courseName\":\"语文\",\"status\":\"1\"},{\"courseId\":\"2\",\"courseName\":\"数学\",\"status\":\"0\"}]}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
				} else{
					result.setInfo("班级id--必填项, 成绩标签id--必填项");
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
	 * 13.2.13 班级科目学生成绩列表查询接口/qxy/results/class/course/student?resultsTagId=[resultsTagId]&cid=[cid]&courseId=[courseId]&page=[page]&size=[size]&order=[order]&token=[token]
     * 需要使用Token验证查询者身份
     *  声明开发测试时前端需要在url上加上test=true参数，标示为测试接口，接口会返回静态数据以提供给前端调试页面使用，前端需要使用测试接口完成数据渲染工作后，等到正式接口开发完成后，才能调用正式接口；
	 */
	
	@RequestMapping(value = "/results/class/course/student", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsClassCourseStudentGET(Boolean test,Long resultsTagId,Long cid,Long courseId,Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {					
				if (cid != null && resultsTagId != null && courseId != null ) {
					String str = "{\"resultsTagName\":\"期中考试\",\"resultsTagId\":\"2\",\"cid\":\"12\",\"className\":\"高一三班\",\"courseId\":\"1\",\"courseName\":\"语文\",\"studentList\":[{\"studentId\":\"1\",\"studentName\":\"张三\",\"number\":\"100000\",\"courseScore\":\"100\",\"courseRanking\":\"1\"},{\"studentId\":\"2\",\"studentName\":\"张三三\",\"number\":\"100001\",\"courseScore\":\"99\",\"courseRanking\":\"2\"}]}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
				} else{
					result.setInfo("班级id--必填项, 成绩标签id--必填项，科目id--必填项");
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
