package com.mcookies.qxy.biz.schoolsafety;

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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.AlarmRule.AlarmRuleDBO;
import com.mcookies.qxy.common.AlarmRule.AlarmRulePVO;
import com.mcookies.qxy.common.AlarmRule.AlarmRuleService;
import com.mcookies.qxy.common.Class.ClassDBO;
import com.mcookies.qxy.common.ClassAlarm.ClassAlarmDBO;
import com.mcookies.qxy.common.ClassAlarm.ClassAlarmPVO;
import com.mcookies.qxy.common.ClassAlarm.ClassAlarmService;

@Controller
@Transactional
/** 报警管理 */
public class AlarmRuleController extends MyControllerSupport {

	@Resource
	protected AlarmRuleService AlarmRuleService_;
	@Resource
	protected ClassAlarmService ClassAlarmService_;

	/**
	 * 班级报警列表查询接口
	 * @param termId
	 * @param gradeId
	 * @param token
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/class/alarmlist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classAlarmlistGET(Long termId,Long gradeId,String token,Integer page,Integer size) {
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
			ClassDBO param = new ClassDBO();
			param.setTermId(termId);
			param.setGradeId(gradeId);
			pageModel.config();
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);
			pageModel.setFormParamBean(param);
			AlarmRuleService_.doSelectPageCountAlarmRule(pageModel);
			List<AlarmRulePVO> rlist = (List<AlarmRulePVO>)pageModel.getPageListData();
			JSONObject data = new JSONObject();
			data.put("page", page);
			data.put("size", size);
			data.put("count", pageModel.getResultCount());
			data.put("classlist", rlist);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	/**
	 * 班级报警列表查询接口
	 * @param cid
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/class/alarmlist/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classAlarmlistSearchGET(Long cid,String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			ClassDBO param = new ClassDBO();
			param.setCid(cid);
			pageModel.config();
			pageModel.setPageCurrent(1);
			pageModel.setPageLimit(200);
			pageModel.setFormParamBean(param);
			AlarmRuleService_.doSelectPageCountAlarmRule(pageModel);
			List<AlarmRulePVO> rlist = (List<AlarmRulePVO>)pageModel.getPageListData();
			result.setData(rlist);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}	
	/**
	 * 班级报警规则查询接口
	 * @param cid
	 * @param token
	 * @param size
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/class/alarmrule", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classAlarmruleGET(Long cid,String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			ClassAlarmDBO param = new ClassAlarmDBO();
			param.setCid(cid);
			List<AlarmRuleDBO> rlist =(List<AlarmRuleDBO>)AlarmRuleService_.doSelectPageClassAlarm(param);
			result.setData(rlist);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	
	
	
	/**
	 * 班级报警规则新增接口
	 * 
	 * @param jsonparam
	 * @return
	 */
	@RequestMapping(value = "/class/alarmrule", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classAlarmrulePOST(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
			String token = (String) param.get("token");
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			Long cid = param.getLong("cid");
			JSONObject alarmrulejson = param.getJSONObject("alarmrule");
			AlarmRuleDBO alarmrule = JSONObject.toJavaObject(alarmrulejson, AlarmRuleDBO.class);
			alarmrule.setIsUse(1);
			AlarmRuleService_.doInsert(alarmrule);
			Long alarmruleId = alarmrule.getAlarmruleId();
			// 插入关联关系
			ClassAlarmDBO ca = new ClassAlarmDBO();
			ca.setCid(cid);
			ca.setAlarmruleId(alarmruleId);
			ca.setIsUse(1);
			ClassAlarmService_.doInsert(ca);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 班级报警规则修改接口
	 * 
	 * @param jsonparam
	 * @return
	 */
	@RequestMapping(value = "/class/alarmrule", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classAlarmrulePUT(@RequestBody AlarmRulePVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(pvo.getToken()) == false) {
				return tokenFail();
			}
			AlarmRuleService_.doUpdate(pvo);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

	@RequestMapping(value = "/class/alarmrule", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classAlarmruleDELETE(@RequestBody ClassAlarmPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(pvo.getToken()) == false) {
				return tokenFail();
			}
			// 删除关联关系
			ClassAlarmService_.doDelete(pvo);

		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 班级报警规则应用至其他班接口
	 * 
	 * @param jsonparam
	 * @return
	 */
	@RequestMapping(value = "/class/alarmrule/copy", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classAlarmruleCopyPUT(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
			String token = (String) param.get("token");
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			JSONArray cids = param.getJSONArray("cids");
			JSONArray alarmruleIds = param.getJSONArray("alarmruleIds");
			for (Object cidtmp : cids) {
				Long cid = Long.valueOf(cidtmp.toString());
				for (Object atmp : alarmruleIds) {
					Long alarmruleId = Long.valueOf(atmp.toString());
					// 删除关联关系
					ClassAlarmDBO ca = new ClassAlarmDBO();
					ca.setCid(cid);
					ca.setAlarmruleId(alarmruleId);
					ClassAlarmService_.doDelete(ca);
					// 新增
					ca.setIsUse(1);
					ClassAlarmService_.doInsert(ca);
				}
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}
}
