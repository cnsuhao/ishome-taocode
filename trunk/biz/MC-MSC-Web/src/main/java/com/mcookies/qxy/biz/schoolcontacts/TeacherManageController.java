package com.mcookies.qxy.biz.schoolcontacts;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherService;
import com.mcookies.qxy.common.SLabel.SLabelDBO;
import com.mcookies.qxy.common.SLabel.SLabelPVO;
import com.mcookies.qxy.common.SLabel.SLabelService;
import com.mcookies.qxy.common.SLabelTeacher.SLabelTeacherDBO;
import com.mcookies.qxy.common.SLabelTeacher.SLabelTeacherService;
import com.mcookies.qxy.common.UTeacher.UTeacherDBO;
import com.mcookies.qxy.common.UTeacher.UTeacherService;
import com.mcookies.qxy.common.UTeacherExt.UTeacherExtService;
import com.mcookies.qxy.common.User.UserService;

/**
 * 教工管理
 * 
 * @author macBookTang
 *
 */
@Controller
@Transactional
public class TeacherManageController extends MyControllerSupport {
	@Resource
	protected UserService UserService_;
	@Resource
	protected UTeacherService UTeacherService_;
	@Resource
	protected UTeacherExtService UTeacherExtService_;
	@Resource
	protected SLabelService SLabelService_;
	@Resource
	protected SLabelTeacherService SLabelTeacherService_;

	@Resource
	protected ClassTeacherService ClassTeacherService_;

	/**
	 * 教工标签查询接口
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/label", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean labelGET(String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			SLabelDBO label = new SLabelDBO();
			List<SLabelDBO> rlist = (List<SLabelDBO>) SLabelService_.doSelectData(label);
			JSONObject data = new JSONObject();
			if (rlist != null) {
				data.put("count", rlist.size());
				data.put("label", rlist);
			} else {
				rlist = new ArrayList<SLabelDBO>();
				data.put("count", 0);
				data.put("label", rlist);
			}
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 教工列表查询接口
	 * 
	 * @param labelId
	 * @param type
	 * @param token
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/teacher/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean teacherListGET(Long labelId, String type, String token, Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			Integer isuse = null;
			if (size == null || size == 0) {
				size = 12;
			}
			if (page == null || page == 0) {
				page = 1;
			}
			if (type == null || "".equals(type) || "0".equals(type)) {
				isuse = 1;
			} else {
				isuse = null;
			}
			pageModel.config();
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);
			if (labelId != null && labelId != 0) {
//				Long lId = Long.valueOf(labelId);
				// 获取该标签下的教工列表
				SLabelPVO label = new SLabelPVO();
				label.setLabelId(labelId);
				label.setIsUse(isuse);
				pageModel.setFormParamBean(label);
				UTeacherService_.doSelectPageTeacherOnLabel(pageModel);
			} else {
				// 所有教工列表
				UTeacherDBO ut = new UTeacherDBO();
				ut.setIsUse(isuse);
				pageModel.setFormParamBean(ut);
				UTeacherService_.doSelectPage(pageModel);
			}
			List<UTeacherDBO> list = (List<UTeacherDBO>) pageModel.getPageListData();
			JSONObject data = new JSONObject();
			data.put("page", page);
			data.put("size", size);
			data.put("count", pageModel.getResultCount());
			data.put("teacherlist", list);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	
	/**
	 * 教工搜索接口
	 * @param type
	 * @param token
	 * @param phone
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/teacher/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean teacherSearchGET(String type, String token,String phone,String email) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			Integer isuse = null;
			if (type == null || "".equals(type) || "0".equals(type)) {
				isuse = 1;
			} else {
				isuse = null;
			}
			if((phone == null || "".equals(phone))&&(email==null||"".equals(email))){
				result.setInfo("参数为空");
				result.setStatus(2);
				return result;
			}
			SLabelPVO pvo = new SLabelPVO();
			pvo.setPhone(phone);
			pvo.setEmail(email);
			pvo.setIsUse(isuse);
			pageModel.config();
			pageModel.setPageCurrent(1);
			pageModel.setPageLimit(12);
			pageModel.setFormParamBean(pvo);
			UTeacherService_.doSelectPageTeacherOnLabel(pageModel);
			List<UTeacherDBO> list = (List<UTeacherDBO>) pageModel.getPageListData();
			if(list!=null && list.size()>0){
				result.setData(list.get(0));
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	
	/**
	 *  教工移动到特定标签接口
	 * @param jsonparam
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/teacher/move", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean teacherMovePUT(String token, @RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
//			String token = (String) param.get("token");
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			JSONObject teacher = JSON.parseObject(jsonparam);
			if(teacher!=null){
				Long tid = teacher.getLong("tid");
				Long labelId = teacher.getLong("labelId");
				Long target = teacher.getLong("target");
				
				if(labelId.intValue()!=0){
					//删除原有关联
					SLabelTeacherDBO stmp = new SLabelTeacherDBO();
					stmp.setTid(tid);
					stmp.setLabelId(labelId);
					SLabelTeacherService_.doDeleteBytid(stmp);
				}
				//新建新关联
				SLabelTeacherDBO stmp2 = new SLabelTeacherDBO();
				stmp2.setTid(tid);
				stmp2.setLabelId(target);
				SLabelTeacherService_.doInsert(stmp2);
				
			}else{
				result.setInfo("参数为空");
				result.setStatus(2);
			}
			
			
			/*JSONArray teacher = param.getJSONArray("teacher");
			if(teacher!=null&&teacher.size()>0){
				for(Object t:teacher){
					JSONObject tjson = (JSONObject)t;
					Long tid = tjson.getLong("tid");
					Long labelId = tjson.getLong("labelId");
					Long target = tjson.getLong("target");
					if(labelId.intValue()!=0){
						//删除原有关联
						SLabelTeacherDBO stmp = new SLabelTeacherDBO();
						stmp.setTid(tid);
						stmp.setLabelId(labelId);
						SLabelTeacherService_.doDeleteBytid(stmp);
					}
					//新建新关联
					SLabelTeacherDBO stmp2 = new SLabelTeacherDBO();
					stmp2.setTid(tid);
					stmp2.setLabelId(target);
					SLabelTeacherService_.doInsert(stmp2);
				}
			}else{
				result.setInfo("参数为空");
				result.setStatus(2);
			}*/
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	
	/**
	 * 移除指定标签下教工的接口
	 * @param jsonparam
	 * @return
	 */
	@RequestMapping(value = "/teacher/remove", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean teacherRemovePUT(String token, @RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
//			String token = (String) param.get("token");
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			JSONArray teacher = param.getJSONArray("teacher");
			if(teacher!=null&&teacher.size()>0){
				for(Object t:teacher){
					JSONObject tjson = (JSONObject)t;
					Long tid = tjson.getLong("tid");
					Long labelId = tjson.getLong("labelId");
					if(labelId.intValue()!=0){
						//删除原有关联
						SLabelTeacherDBO stmp = new SLabelTeacherDBO();
						stmp.setTid(tid);
						stmp.setLabelId(labelId);
						SLabelTeacherService_.doDeleteBytid(stmp);
					}
				}
			}else{
				result.setInfo("参数为空");
				result.setStatus(2);
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
}
