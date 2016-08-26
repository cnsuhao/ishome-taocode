package com.mcookies.qxy.biz.annex;

import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.STrip.STripDBO;
import com.mcookies.qxy.common.STrip.STripPVO;
import com.mcookies.qxy.common.STrip.STripService;
import com.mcookies.qxy.common.UTeacher.UTeacherDBO;
import com.mcookies.qxy.common.UTeacher.UTeacherService;

/**
 * 个人行程
 * @author macBookTang
 *
 */
@Controller
public class STripController extends MyControllerSupport {
	@Resource
	protected STripService STripService_;
	@Resource
	protected UTeacherService uTeacherService;
	
	@RequestMapping(value = "/trip", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean tripGET(String token,Long tid,String date,Integer page,Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if(size==null||size==0){
				size = 12;
			}
			if(page==null||page==0){
				page = 1;
			}
			STripDBO strip = new STripDBO();
			strip.setTid(tid);
			strip.setDate(date);
			pageModel.config();
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);
			pageModel.setFormParamBean(strip);
			STripService_.doSelectPageBytid(pageModel);
			JSONObject data = new JSONObject();
			data.put("page", page);
			data.put("size", size);
			data.put("count", pageModel.getResultCount());
			data.put("triplist", pageModel.getPageListData());
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	
	/**
	 * 个人行事历新增接口
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/trip", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean tripPOST(String token, @RequestBody STripPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			pvo.setIsUse(1);
			
			Long schoolId = getLoginer().getSchoolId();
			Long userId = getLoginer().getUserId();
			
			UTeacherDBO teacher = new UTeacherDBO();
			teacher.setUid(userId);
			teacher.setSid(schoolId);
			List<UTeacherDBO> teachers = (List<UTeacherDBO>) uTeacherService.doSelectData(teacher);
			
			pvo.setSid(schoolId);
			pvo.setTid(teachers.get(0).getTid());
			
			STripService_.doInsert(pvo);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 个人行事历修改接口
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/trip", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean tripPUT(String token, @RequestBody STripPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			Long schoolId = getLoginer().getSchoolId();
			Long userId = getLoginer().getUserId();
			
			UTeacherDBO teacher = new UTeacherDBO();
			teacher.setUid(userId);
			teacher.setSid(schoolId);
			List<UTeacherDBO> teachers = (List<UTeacherDBO>) uTeacherService.doSelectData(teacher);
			
			pvo.setSid(schoolId);
			pvo.setTid(teachers.get(0).getTid());
			
			STripService_.doUpdate(pvo);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 个人行事历删除接口
	 * @return
	 */
	@RequestMapping(value = "/trip", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean tripDELETE(String token, @RequestBody STripPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			STripService_.doDelete(pvo);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
}
