package com.mcookies.qxy.biz.task;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.Task.TaskDBO;
import com.mcookies.qxy.common.Task.TaskPVO;
import com.mcookies.qxy.common.Task.TaskService;
import com.mcookies.qxy.common.TaskQuestion.TaskQuestionService;



/**
 * 班级作业设定
 * @author macBookTang
 *
 */
@Controller
public class TaskManegeController extends MyControllerSupport{
	
	@Resource
	protected TaskService TaskServicer;
	@Resource
	protected TaskQuestionService TaskQuestionService;
	
	/**
	 * 学校学期年级班级查询接口（通用）
	 * @param termid,cid
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/task/class", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean taskWithClassGET(Long termId,String cid,String token,Integer page,Integer size,Boolean test) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if (page == null || page == 0) {
				page = 1;
			}
			if (size == null || size == 0) {
				size = 12;
			}
			if(!test){
				result.setInfo("你使用的测试接口地址错误");
				result.setStatus(3);
				return result;
			}
			if(termId<=0&& (cid.equals(null)|| cid.equals(""))){
				throw new IllegalArgumentException("termId或者cid不能同时为空");
			}
			Map<String, Object> data = new HashMap<String, Object>();
			JSONArray array = new JSONArray();
			for(Long i=1L; i<=3;i++){
				TaskPVO task = new TaskPVO();
				task.setTaskId(i);
				task.setTaskName("写一篇500论点描述");
				task.setIsTop(0);
				task.setAuthor(20L);
				task.setAuthorName("陈老师");
				task.setCourseId(3L);
				task.setCourseName("语文");
				task.setPublishTime("2019-09-22 16:20:30");
				array.add(task);
			}
			data.put("count", 3);
			data.put("page", page);
			data.put("size", size);
			data.put("tasklist", array);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 学校学期年级班级查询接口（通用）
	 * @param termid,tid
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/task/teacher", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean taskWithTeacherGET(Long termId,Long tid,String token,Integer page,Integer size,Boolean test) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if (page == null || page == 0) {
				page = 1;
			}
			if (size == null || size == 0) {
				size = 12;
			}
			if(!test){
				result.setInfo("你使用的测试接口地址错误");
				result.setStatus(3);
				return result;
			}
			if(termId<=0&& tid<=0){
				throw new IllegalArgumentException("termId或者cid不能同时为空");
			}
			Map<String, Object> data = new HashMap<String, Object>();
			JSONArray array = new JSONArray();
			for(Long i=1L; i<=3;i++){
				TaskPVO task = new TaskPVO();
				task.setTaskId(i);
				task.setTaskName("写一篇500论点描述");
				task.setIsTop(0);
				task.setAuthor(20L);
				task.setAuthorName("陈老师");
				task.setCourseId(3L);
				task.setCourseName("语文");
				task.setPublishTime("2019-09-22 16:20:30");
				array.add(task);
			}
			data.put("count", 3);
			data.put("page", page);
			data.put("size", size);
			data.put("tasklist", array);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	
	/**
	 * 作业详情查看接口（通用）
	 * @param taskid
	 * @param token
	 * @return
	 */
	
	@RequestMapping(value = "/task/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean taskInfoGET(Long taskId,String token,Boolean test) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if(!test){
				result.setInfo("你使用的测试接口地址错误");
				result.setStatus(3);
				return result;
			}
			if(taskId<=0){
				throw new IllegalArgumentException("taskId不能为空");
			}
				TaskPVO task = new TaskPVO();
				task.setTaskId(taskId);
				task.setTaskName("写一篇500论点描述");
				task.setContent("论秦始皇功大于过.....");
				task.setVideo("url");
				task.setPic("url1,url2");
				task.setTaskClasser("1,2,5");
				task.setTaskClasserName("初三1班,初三3班,初三5班");
				task.setIsTop(0);
				task.setAuthor(20L);
				task.setAuthorName("陈老师");
				task.setCourseId(3L);
				task.setCourseName("语文");
				task.setPublishTime("2019-09-22 16:20:30");
				task.setStartTime("2019-09-22 16:20:30");
				task.setEndTime("2019-09-23 16:20:30");

			result.setData(task);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 作业搜索接口（通用）
	 * @param termid,tid
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/task/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean taskSearchGET(Long courseId,String taskName,String token,Integer page,Integer size,Boolean test) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if (page == null || page == 0) {
				page = 1;
			}
			if (size == null || size == 0) {
				size = 12;
			}
			if(!test){
				result.setInfo("你使用的测试接口地址错误");
				result.setStatus(3);
				return result;
			}
			if(courseId<=0){
				throw new IllegalArgumentException("courseId不能为空");
			}
			Map<String, Object> data = new HashMap<String, Object>();
			JSONArray array = new JSONArray();
			for(Long i=1L; i<=3;i++){
				TaskPVO task = new TaskPVO();
				task.setTaskId(i);
				task.setTaskName("写一篇500论点描述");
				task.setIsTop(0);
				task.setAuthor(20L);
				task.setAuthorName("陈老师");
				task.setCourseId(3L);
				task.setCourseName("语文");
				task.setPublishTime("2019-09-22 16:20:30");
				array.add(task);
			}
			data.put("count", 3);
			data.put("page", page);
			data.put("size", size);
			data.put("tasklist", array);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 作业新增接口（通用）
	 * @param termid,tid
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/task", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean taskAddPOST(@RequestBody TaskDBO task,String token,Boolean test) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if(!test){
				result.setInfo("你使用的测试接口地址错误");
				result.setStatus(3);
				return result;
			}
			if(task.getContent()==null&&task.getPublishTime()==null){
				result.setInfo("作业信息不全，请核对后再提交");
				result.setStatus(2);
				return result;
			}
			 
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 作业置顶/取消置顶接口（通用）
	 * @param top
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/task/set", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean taskSetPUT(@RequestBody TaskDBO task ,String token,Boolean test) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if(!test){
				result.setInfo("你使用的测试接口地址错误");
				result.setStatus(3);
				return result;
			}
			Long taskId = task.getTaskId();
			Integer isTop = task.getIsTop();
			if(isTop==null&&taskId==null){
				result.setInfo("isTop,taskId不能为空");
				result.setStatus(2);
				return result;
			}
			 
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	/**
	 * 作业修改接口（通用）
	 * @TaskDBO task
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/task", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean taskPUT(@RequestBody TaskDBO task ,String token,Boolean test) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if(!test){
				result.setInfo("你使用的测试接口地址错误");
				result.setStatus(3);
				return result;
			}
			Long taskId = task.getTaskId();
			if(taskId==null){
				result.setInfo("taskId不能为空");
				result.setStatus(2);
				return result;
			}
			 
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 作业删除改接口（通用）
	 * @TaskDBO task
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/task", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean taskDELETE(@RequestBody TaskDBO task ,String token,Boolean test) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if(!test){
				result.setInfo("你使用的测试接口地址错误");
				result.setStatus(3);
				return result;
			}
			Long taskId = task.getTaskId();
			if(taskId==null){
				result.setInfo("taskId不能为空");
				result.setStatus(2);
				return result;
			}
			 
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

}
