package com.mcookies.qxy.biz.task;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
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
import com.mcookies.qxy.common.Class.ClassDBO;
import com.mcookies.qxy.common.Class.ClassPVO;
import com.mcookies.qxy.common.Class.ClassService;
import com.mcookies.qxy.common.SGradeLabel.SGradeLabelDBO;
import com.mcookies.qxy.common.SGradeLabel.SGradeLabelService;
import com.mcookies.qxy.common.STerm.STermDBO;
import com.mcookies.qxy.common.STerm.STermService;
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
	@Resource
	protected ClassService ClassService;
	@Resource
	protected SGradeLabelService SGradeLabelService_;
	@Resource
	protected STermService STermService;
	
	/**
	 * 学校学期年级班级查询接口（通用）
	 * @param cid
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/task/class", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean taskWithClassGET(String cid,String token,Integer page,Integer size) {
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
			if(cid.equals(null)|| cid.equals("")){
				throw new IllegalArgumentException("cid不能为空");
			}
			TaskPVO taskdbo = new TaskPVO();
			if(!cid.equals(null)|| !cid.equals("")){
				ClassDBO model = new ClassDBO();
				model.setCid(Long.parseLong(cid));
				ClassPVO cla = ClassService.findClassersAndGrade(model);
				if(cla == null){
					result.setInfo("你所查的班级不存在");
					result.setStatus(2);
					return result;
				}
				taskdbo.setCid(cid);
				taskdbo.setSid(cla.getSid());
			}
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);			
			pageModel.setFormParamBean(taskdbo);
			TaskServicer.doSelectPageTaskWithCid(pageModel);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("page", pageModel.getPageCurrent());
			data.put("size", pageModel.getPageLimit());
			data.put("count", pageModel.getResultCount());
			data.put("tasklist", pageModel.getPageListData());
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 学校学期年级班级查询接口（通用）
	 * @param cid,tid
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/task/teacher", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean taskWithTeacherGET(Long tid,String cid,String token,Integer page,Integer size) {
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
			
			if(tid == null || tid<=0){
				throw new IllegalArgumentException("tid不能为空");
			}
			if(cid.equals(null)|| cid.equals("")){
				throw new IllegalArgumentException("cid不能为空");
			}
			Map<String, Object> data = new HashMap<String, Object>();

			TaskPVO taskdbo = new TaskPVO();			 
			taskdbo.setCid(cid);					
			taskdbo.setAuthor(tid);			
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);			
			pageModel.setFormParamBean(taskdbo);
			TaskServicer.doSelectPagetTaskWithCiddAndTid(pageModel);
			data.put("page", pageModel.getPageCurrent());
			data.put("size", pageModel.getPageLimit());
			data.put("count", pageModel.getResultCount());
			data.put("tasklist", pageModel.getPageListData());
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
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
	public RESTResultBean taskInfoGET(Long taskId,String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if(taskId<=0){
				throw new IllegalArgumentException("taskId不能为空");
			}
			TreeSet<String> taskClasserName = new TreeSet<String>();
			TaskDBO dbo = new TaskDBO();
			dbo.setTaskId(taskId);
			dbo = (TaskDBO) TaskServicer.doRead(dbo);
			if(dbo == null ){
				result.setInfo("作业不存在");
				result.setStatus(5);
				return result;
			}
			String taskclass = dbo.getTaskClasser();
			String[] cids = taskclass.split(";");
			String[] className = new String[cids.length];
			for(String cid:cids){
				if(!cid.equals(null)|| !cid.equals("")){
					ClassDBO model = new ClassDBO();
					model.setCid(Long.parseLong(cid));
					ClassPVO cla = ClassService.findClassersAndGrade(model);
					if(cla!= null){
						
						taskClasserName.add(cla.getTermName()+cla.getGradeName()+cla.getClassName());
					}
				}
				
			}
			TaskPVO task = new TaskPVO();
			task = TaskServicer.doSelectTaskInfo(dbo);
			task.setTaskClasserName(taskClasserName);
			result.setData(task);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
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
	public RESTResultBean taskSearchGET(TaskPVO taskdbo,Long tid ,String token,Integer page,Integer size) {
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
			if(taskdbo.getCourseId() == null || taskdbo.getCourseId()<=0){
				throw new IllegalArgumentException("courseId不能为空");
			}
			if(taskdbo.getTaskName().equals(null) || taskdbo.getTaskName().equals("")){
				throw new IllegalArgumentException("搜索内容不能为空");
			}
			String taskName = new String(taskdbo.getTaskName().getBytes("ISO-8859-1"),"UTF-8");
			taskdbo.setTaskName(taskName);
			Map<String, Object> data = new HashMap<String, Object>();
		
			if(tid != null && tid > 0){
			    taskdbo.setAuthor(tid);
			}
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);			
			pageModel.setFormParamBean(taskdbo);
			TaskServicer.doSelectPageSearch(pageModel);
			data.put("page", pageModel.getPageCurrent());
			data.put("size", pageModel.getPageLimit());
			data.put("count", pageModel.getResultCount());
			data.put("tasklist", pageModel.getPageListData());
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
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
	public RESTResultBean taskAddPOST(@RequestBody TaskDBO task,String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			
			if(task.getContent()==null&&task.getPublishTime()==null){
				result.setInfo("作业信息不全，请核对后再提交");
				result.setStatus(2);
				return result;
			}
			 Long sid = getLoginer().getSchoolId();
			 task.setSid(sid);
			 task.setIsAudit(1);
			 task.setIsUse(1);
			 task.setNum(0L);
			 Long termId = task.getTermId();
			 if(termId == null || termId == 0){
					STermDBO term = new STermDBO();
					term.setSid(sid);
					term.setIsDefault(1);
					List<STermDBO> termlist = (List<STermDBO>) STermService.doSelectData(term);
					termId = termlist.get(0).getTermId();
					task.setTermId(termId);
				}
			 TaskServicer.doInsert(task);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("作业新增失败，" + e.getMessage());
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
	public RESTResultBean taskSetPUT(@RequestBody TaskDBO task ,String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			
			Long taskId = task.getTaskId();
			Integer isTop = task.getIsTop();
			if(isTop==null || taskId==null){
				result.setInfo("isTop,taskId不能为空");
				result.setStatus(2);
				return result;
			}
			TaskDBO model =  (TaskDBO) TaskServicer.doRead(task);
			if(model.getIsTop() == task.getIsTop()){
				result.setInfo("请不要重复操作");
				result.setStatus(3);
				return result;
			}
			TaskServicer.doUpdate(task);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("作业置顶/取消置顶，" + e.getMessage());
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
	public RESTResultBean taskPUT(@RequestBody TaskDBO task ,String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			
			Long taskId = task.getTaskId();
			if(taskId==null){
				result.setInfo("taskId不能为空");
				result.setStatus(2);
				return result;
			}
			TaskServicer.doUpdate(task); 
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("修改失败，" + e.getMessage());
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
	public RESTResultBean taskDELETE(@RequestBody TaskDBO task ,String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			Long taskId = task.getTaskId();
			if(taskId==null){
				result.setInfo("taskId不能为空");
				result.setStatus(2);
				return result;
			}
			TaskServicer.doDelete(task); 
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("删除失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 学校学期年级班级查询接口（通用）
	 * @param type
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/termgradeclass/teacher", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean termgradeclassGET(Long tid,String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			// 获取当前学校ID
			Long schoolId = getLoginer().getSchoolId();
			Integer isuse = null;
			
			JSONObject data = new JSONObject();
			//获得学期列表
			STermDBO stermtmp = new STermDBO();
			stermtmp.setIsUse(isuse);
			stermtmp.setSid(schoolId);               
			List<STermDBO> terms = (List<STermDBO>)STermService.doSelectData(stermtmp);
			data.put("count", terms.size());
			JSONArray termsjson = new JSONArray();
			if(terms!=null){
				for(STermDBO tmp:terms){
					JSONObject termjson = new JSONObject();
					termjson.put("termId", tmp.getTermId());
					termjson.put("termName", tmp.getTermName());
					termjson.put("isDefault", tmp.getIsDefault());
					termjson.put("isUse", tmp.getIsUse());
					//获取该学期下的班级
					ClassDBO cdbo = new ClassDBO();
					cdbo.setTermId(tmp.getTermId());
					cdbo.setSid(schoolId);
					List<ClassDBO> classlist = (List<ClassDBO>)ClassService.doSelectData(cdbo);
					List<Long> gradeIds = new LinkedList<Long>();
					if (classlist != null && classlist.size()>0) {
						for (ClassDBO temp : classlist) {
							if (!gradeIds.contains(temp.getGradeId())) {
								gradeIds.add(temp.getGradeId());
							}
						}
						//循环获取年级
						JSONArray gradelsjson = new JSONArray();
						for(Long gradeid:gradeIds){
							SGradeLabelDBO sgradtmp = new SGradeLabelDBO();
							sgradtmp.setGradeId(gradeid);
							sgradtmp = (SGradeLabelDBO) SGradeLabelService_.doRead(sgradtmp);
							JSONObject gjson = new JSONObject();
							gjson.put("gradeId", sgradtmp.getGradeId());
							gjson.put("gradeName", sgradtmp.getGradeName());
							gjson.put("isUse", sgradtmp.getIsUse());
							//获取该年级下的所有班级
							ClassPVO ctmp = new ClassPVO();
							ctmp.setGradeId(gradeid);
							ctmp.setTermId(tmp.getTermId());
							if(tid != null && tid >0){
								ctmp.setTid(tid);
							}
							List<ClassPVO> clisttmp = (List<ClassPVO>)ClassService.doSelectPageClassAndTid(ctmp);
							JSONArray classjson = new JSONArray();
							for(ClassDBO tmp1:clisttmp){
								JSONObject jtmp = new JSONObject();
								jtmp.put("cid", tmp1.getCid());
								jtmp.put("className", tmp1.getClassName());
								classjson.add(jtmp);
							}
							gjson.put("class", classjson);
							gradelsjson.add(gjson);
						}
						termjson.put("grade", gradelsjson);
					}
					termsjson.add(termjson);
				}
			}
			data.put("term", termsjson);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

}
