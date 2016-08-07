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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.SGradeLabel.SGradeLabelDBO;
import com.mcookies.qxy.common.SGradeLabel.SGradeLabelPVO;
import com.mcookies.qxy.common.SGradeLabel.SGradeLabelService;

@Controller
/**
 * 年级管理
 * @author macBookTang
 *
 */
public class GradeManageController extends MyControllerSupport {
	@Resource
	protected SGradeLabelService SGradeLabelService_;
	
	/**
	 * 班级年级标签查询接口
	 * @param type
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/gradelabel", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean gradelabelGET(String type, String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			//获取当前学校ID
			Long schoolId = getLoginer().getSchoolId();
			Integer isuse = null;
			if(type==null||"".equals(type)||"0".equals(type)){
				isuse = 1;
			}else{
				isuse = null;
			}
			SGradeLabelDBO param = new SGradeLabelDBO();
			param.setSid(schoolId);
			param.setIsUse(isuse);
			List<SGradeLabelDBO> relist = (List<SGradeLabelDBO>)SGradeLabelService_.doSelectData(param);
			JSONObject json = new JSONObject();
			json.put("count", relist.size());
			json.put("grade", relist);
			result.setData(json);
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	

	/**
	 * 学校年级标签新增接口
	 * @param grade
	 * @return
	 */
	@RequestMapping(value = "/gradelabel", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean deviceinfoPOST(@RequestBody SGradeLabelPVO grade) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(grade.getToken()) == false) {
				return tokenFail();
			}
			
			// 校验是否已经存在
			SGradeLabelDBO param = new SGradeLabelDBO();
			Long schoolId = getLoginer().getSchoolId();
			param.setSid(schoolId);
			param.setGradeName(grade.getGradeName());
			List<SGradeLabelDBO> relist = (List<SGradeLabelDBO>)SGradeLabelService_.doSelectData(param);
			if (relist != null && relist.size() > 0) {
				result.setInfo("新增失败，年级标签已存在");
				result.setStatus(2);
			} else {
				// 新增
				grade.setSid(schoolId);
				grade.setIsUse(1);
				SGradeLabelService_.doInsert(grade);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 班级年级标签修改及停用（启用）接口
	 * @param grade
	 * @return
	 */
	@RequestMapping(value = "/gradelabel", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean deviceinfoPUT(@RequestBody SGradeLabelPVO grade) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(grade.getToken()) == false) {
				return tokenFail();
			}
			
			// 校验是否已经存在
			if(grade.getGradeName()!=null&&!"".equals(grade.getGradeName())){
				SGradeLabelDBO param = new SGradeLabelDBO();
				Long schoolId = getLoginer().getSchoolId();
				param.setSid(schoolId);
				param.setGradeName(grade.getGradeName());
				List<SGradeLabelDBO> relist = (List<SGradeLabelDBO>)SGradeLabelService_.doSelectData(param);
				if (relist != null && relist.size() > 0) {
					for (SGradeLabelDBO temp : relist) {
						if (temp.getGradeId() != grade.getGradeId()) {
							result.setInfo("更新失败，年级标签已存在");
							result.setStatus(2);
							return result;
						}
					}
				}
			}
			// 编辑
			SGradeLabelService_.doUpdate(grade);
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	
	@RequestMapping(value = "/gradelabel", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean gradelabelDELETE(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		JSONObject param = JSONObject.parseObject(jsonparam);
		String token = (String)param.get("token");
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			JSONArray gradeIds = param.getJSONArray("gradeId");
			for(Object device:gradeIds){
				Long gradeid = Long.valueOf(device.toString());
				//乐观锁操作
				SGradeLabelDBO dbo = new SGradeLabelDBO();
				dbo.setGradeId(gradeid);
				dbo = (SGradeLabelDBO)SGradeLabelService_.doRead(dbo);
				int flag = SGradeLabelService_.doDelete(dbo);
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
}
