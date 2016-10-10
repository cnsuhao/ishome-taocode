package com.mcookies.qxy.biz.annex;

import java.util.ArrayList;
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
			if (!date.equals(null) || !"".equals(date)) {
				String[] dates = null;
				if (date.contains("-")) {
					dates = date.split("-");
				} else {
					dates = date.split("/");
				}

				String year = dates[0];
				String month = dates[1];
				String day = dates[2];
				if (Integer.parseInt(month) < 10 && !month.contains("0")) {
					month = "0" + month;
				}
				if (Integer.parseInt(day) < 10 && !day.contains("0")) {
					day = "0" + day;
				}
				date = year + "-" + month + "-" + day;
				
			}
			/*String[] dates= date.split("-");
			String year = dates[0];
			String month = dates[1];
			String day = dates[2];
			if(Integer.parseInt(month)<10){
				month = "0"+month;
			}
			if(Integer.parseInt(day)<10){
				day = "0"+day;
			}
			date = year+"-"+month+"-"+day;*/
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
	
	/**
	 * 1.5 个人日历表状态查询接口
	 * 
	 * 
	*/
	@RequestMapping(value= "/trip/status",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean tripStatus(Long tid ,String token){
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			//tid
			if(tid == null ){
				throw new IllegalArgumentException("cid不能为空");
			}
			UTeacherDBO teacher = new UTeacherDBO();
			teacher.setTid(tid);
			teacher.setPuk("1");
			teacher = (UTeacherDBO) uTeacherService.doRead(teacher);
			if(teacher == null){
				result.setInfo("该教师不存在");
				result.setStatus(1);
				return result;
			}
			STripDBO strip = new STripDBO();
			strip.setTid(tid);
			List<STripDBO> slist = (List<STripDBO>) STripService_.doSelectData(strip);
			String[] temp = null;
			if(slist.size()>0){
				List<String> nlist = new ArrayList<String> ();
				String[] tripstatus = new String[slist.size()];
				
				for(int i= 0;i<slist.size();i++){
					String a =slist.get(i).getDate().toString();
					a = a.substring(0, 10);
					tripstatus[i]=a;
				}
				for(int j=0; j<tripstatus.length;j++){
					for(int k=j+1; k<tripstatus.length;k++){
						if(tripstatus[j] .equals(tripstatus[k])){
							tripstatus[j]=null;
							break;
						}
					}
				}
				for(String s :tripstatus){
					if(s!=null){
						nlist.add(s);
					}
				}
				temp = new String[nlist.size()];
				for(int i= 0;i<nlist.size();i++){
					temp[i] = nlist.get(i);
				}
				
			}	
			JSONObject data = new JSONObject();
			data.put("tripstatus", temp);
			result.setStatus(0);
			result.setData(data);	
			
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		
		
		
		return result;
	}
	
}
