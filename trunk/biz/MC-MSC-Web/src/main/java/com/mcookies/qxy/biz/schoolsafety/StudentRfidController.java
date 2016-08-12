package com.mcookies.qxy.biz.schoolsafety;

import java.util.List;

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
import com.mcookies.qxy.common.ClassStudent.ClassStudentDBO;
import com.mcookies.qxy.common.ClassStudent.ClassStudentService;
import com.mcookies.qxy.common.SGradeLabel.SGradeLabelService;
import com.mcookies.qxy.common.STerm.STermService;
import com.mcookies.qxy.common.StudentRfid.StudentRfidDBO;
import com.mcookies.qxy.common.StudentRfid.StudentRfidPVO;
import com.mcookies.qxy.common.StudentRfid.StudentRfidService;
import com.mcookies.qxy.common.UStudent.UStudentDBO;
import com.mcookies.qxy.common.UStudent.UStudentPVO;
import com.mcookies.qxy.common.UStudent.UStudentService;

@Controller
/** 学生rfid*/
public class StudentRfidController extends MyControllerSupport {
	@Resource
	protected StudentRfidService StudentRfidService_;
	@Resource
	protected ClassStudentService ClassStudentService_;
	@Resource
	protected ClassService ClassService_;
	@Resource
	protected SGradeLabelService SGradeLabelService_;
	@Resource
	protected STermService STermService_;
	@Resource
	protected UStudentService UStudentService_;	
	
	
	
	
	
	
	
	@RequestMapping(value = "/studentrfidlist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean studentrfidlistGET(Long termId,Long cid,String token,Integer page,Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if(size==null||size==0){
				size = 12;
			}
			if(page==null||page==0){
				page = 1;
			}
			JSONObject data = new JSONObject();
			//获取学生列表
			ClassDBO cdbo = new ClassDBO();
			cdbo.setTermId(termId);
			cdbo.setCid(cid);
			pageModel.config();
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);
			pageModel.setFormParamBean(cdbo);
			List<UStudentDBO> relist = (List<UStudentDBO>)UStudentService_.doselectPageStudent(pageModel);
			data.put("page",page);
			data.put("size",size);
			data.put("count", pageModel.getResultCount());
			JSONArray studentrfid = new JSONArray();
			if(relist!=null&&relist.size()>0){
				for(UStudentDBO tmp:relist){
					JSONObject student = new JSONObject();
					student.put("studentId", tmp.getStudentId());
					student.put("studentName", tmp.getStudentName());
					student.put("number",tmp.getNumber());
					//获取 rfid列表
					StudentRfidDBO rfiddbo = new StudentRfidDBO();
					rfiddbo.setStudentId(tmp.getStudentId());
					List<StudentRfidDBO> rfidlist =(List<StudentRfidDBO>)StudentRfidService_.doSelectData(rfiddbo);
					if(rfidlist!=null&&rfidlist.size()>0){
						JSONArray rfidjson = new JSONArray();
						for(StudentRfidDBO tmp1:rfidlist){
							JSONObject tmp1json = new JSONObject();
							tmp1json.put("rfid", tmp1.getRfid());
							tmp1json.put("isEffective",tmp1.getIsEffective());
							rfidjson.add(tmp1json);
						}
					}
					student.put("rfidlist", rfidlist);
					studentrfid.add(student);
				}
			}
			data.put("studentrfid",studentrfid);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}
	
	
	
	
	
	
	/**
	 * 学生rfid卡列表搜索接口
	 * @param studentId
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/studentrfidlist/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean studentrfidlistSearchGET(Long studentId,String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			//获取学生信息
			UStudentDBO stu = new UStudentDBO();
			stu.setStudentId(studentId);
			stu = (UStudentDBO)UStudentService_.doRead(stu);
			if(stu ==null){
				result.setInfo("studentId不存在");
				result.setStatus(2);
				return result;
			}
			JSONObject data = new JSONObject();
			data.put("studentId", stu.getStudentId());
			data.put("studentName", stu.getStudentName());
			data.put("number", stu.getNumber());
			StudentRfidDBO rfids = new StudentRfidDBO();
			rfids.setStudentId(studentId);
			List<StudentRfidDBO> rlist =(List<StudentRfidDBO>)StudentRfidService_.doSelectData(rfids);
			data.put("rfidlist", rlist);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}
	
	
	/**
	 * 学生rfid卡新增接口
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/studentrfid", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean studentrfidPOST(@RequestBody StudentRfidPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(pvo.getToken()) == false) {
				return tokenFail();
			}
			//查询该学生是否已经有卡
			StudentRfidDBO sdbo = new StudentRfidDBO();
			sdbo.setStudentId(pvo.getStudentId());
			List<StudentRfidDBO> rlist =(List<StudentRfidDBO>)StudentRfidService_.doSelectData(sdbo);
			if(rlist!=null&&rlist.size()>0){
				pvo.setIsEffective(0);
				pvo.setIsUse(1);
				for(StudentRfidDBO tmp:rlist){
					//重复
					if(tmp.getStudentId().equals(pvo.getStudentId()) && tmp.getRfid().equals(pvo.getRfid())){
						result.setInfo("已经添加过此卡");
						result.setStatus(2);
						return result;
					}
					StudentRfidService_.doInsert(pvo);
				}
			}else{
				//直接添加，并默认为有效
				pvo.setIsEffective(1);
				pvo.setIsUse(1);
				StudentRfidService_.doInsert(pvo);
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}
	
	/**
	 * 学生rfid卡修改及设为有效（默认为无效）接口
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/studentrfid", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean studentrfidPUT(@RequestBody StudentRfidPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(pvo.getToken()) == false) {
				return tokenFail();
			}
			//修改其他rfid卡为无效
			StudentRfidDBO srfid = new StudentRfidDBO();
			srfid.setStudentId(pvo.getStudentId());
			srfid.setIsEffective(0);
			StudentRfidService_.doUpdateAll(srfid);
			//
			StudentRfidService_.doUpdate(pvo);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 学生rfid卡删除接口
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/studentrfid", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean studentrfidDELETE(@RequestBody StudentRfidPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(pvo.getToken()) == false) {
				return tokenFail();
			}
			//删除该关联
			StudentRfidService_.doDelete(pvo);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}	
	
	/**
	 * rfid卡对应学生搜索接口
	 * @param token
	 * @param rfid
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/rfidstudent/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean rfidstudentSearchGET(String token,Long rfid) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			//获取学生列表
			StudentRfidDBO param = new StudentRfidDBO();
			param.setRfid(rfid);
			pageModel.config();
			pageModel.setPageCurrent(1);
			pageModel.setPageLimit(1);
			pageModel.setFormParamBean(param);
			StudentRfidService_.doSelectPageStudentByrfid(pageModel);
			List<UStudentPVO> rlist = (List<UStudentPVO>)pageModel.getPageListData();
			if(rlist!=null&&rlist.size()>0){
				result.setData(rlist.get(0));
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	
	@RequestMapping(value = "/rfidstudent", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean rfidstudentGET(String token,Integer page,Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if (size == null || size == 0) {
				size = 12;
			}
			if (page == null || page == 0) {
				page = 1;
			}
			//获取学生列表
			StudentRfidDBO param = new StudentRfidDBO();
			pageModel.config();
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);
			pageModel.setFormParamBean(param);
			StudentRfidService_.doSelectPageStudentByrfid(pageModel);
			List<UStudentPVO> rlist = (List<UStudentPVO>)pageModel.getPageListData();
			JSONObject data = new JSONObject();
			data.put("page", page);
			data.put("size", size);
			data.put("count", pageModel.getResultCount());
			data.put("rfidstudent", rlist);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

}
