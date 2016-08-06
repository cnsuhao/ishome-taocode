package com.mcookies.qxy.biz.system;

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
import com.mcookies.qxy.common.SCourse.SCourseDBO;
import com.mcookies.qxy.common.SCourse.SCoursePVO;
import com.mcookies.qxy.common.SCourse.SCourseService;

@Controller
/** 课程管理 */
public class CourseManageController extends MyControllerSupport {
	@Resource
	protected SCourseService SCourseService_;

	/**
	 * 学校课程查询接口
	 * @param type
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/course", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean courseGET(String type,String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			Integer isuse = null;
			if(type==null||"".equals(type)||"0".equals(type)){
				isuse = 1;
			}else{
				isuse = null;
			}
			SCourseDBO param = new SCourseDBO();
			param.setIsUse(isuse);
			List<SCourseDBO> rlist = (List<SCourseDBO>)SCourseService_.doSelectData(param);
			JSONObject json = new JSONObject();
			json.put("count", rlist.size());
			json.put("courselist", rlist);
			result.setData(json);
			
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}
	
	
	/**
	 * 学校课程新增接口
	 * 
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/course", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean coursePOST(@RequestBody SCoursePVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(pvo.getToken()) == false) {
				return tokenFail();
			}
			// 判断课程是否存在
			List<SCourseDBO> rlist = (List<SCourseDBO>) SCourseService_.doSelectData(pvo);
			if (rlist != null && rlist.size() > 0) {
				result.setInfo("新增失败，名字重复");
				result.setStatus(2);
			} else {
				pvo.setIsUse(0);
				SCourseService_.doInsert(pvo);
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 学校课程修改包括停用(启用)接口
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/course", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean coursePUT(@RequestBody SCoursePVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(pvo.getToken()) == false) {
				return tokenFail();
			}
			// 判断课程是否存在
			SCourseDBO param = new SCourseDBO();
			param.setCourseName(pvo.getCourseName());
			List<SCourseDBO> rlist = (List<SCourseDBO>) SCourseService_.doSelectData(param);
			if (rlist != null && rlist.size() > 0) {
				for (SCourseDBO tmp : rlist) {
					if (tmp.getCourseId() != pvo.getCourseId()) {
						result.setInfo("编辑失败，名字重复");
						result.setStatus(2);
						return result;
					}
				}
			}
			SCourseService_.doUpdate(pvo);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 学校课程删除接口
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/course", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean courseDELETE(@RequestBody SCoursePVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(pvo.getToken()) == false) {
				return tokenFail();
			}
			int flag = SCourseService_.doDelete(pvo);
			if(flag!=1){
				result.setInfo("删除失败，无此编号");
				result.setStatus(2);
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	

}
