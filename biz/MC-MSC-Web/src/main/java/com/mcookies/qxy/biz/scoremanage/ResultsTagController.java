package com.mcookies.qxy.biz.scoremanage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.MecScore.MecScorePVO;
import com.mcookies.qxy.common.MecScore.MecScoreService;
import com.mcookies.qxy.common.ResultsTag.ResultsTagDBO;
import com.mcookies.qxy.common.ResultsTag.ResultsTagService;
import com.mcookies.qxy.common.STerm.STermDBO;
import com.mcookies.qxy.common.STerm.STermService;
import com.mcookies.qxy.utils.DateUtils;

/**
 * 成绩标签管理
 * 
 * @author james
 *
 */
@Controller
public class ResultsTagController extends MyControllerSupport {
	@Resource
	protected STermService STermService_;
	@Resource
	protected ResultsTagService ResultsTagService_;

	/**
	 * 成绩标签列表查询接口
	 * /qxy/results/tag?termId=[termId]&page=[page]&size=[size]&token=[token]
	 * 需要使用Token验证查询者身份
	 */

	@RequestMapping(value = "/results/tag", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsTagGET(Boolean test, Long termId, String token, Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {
				String str = "{\"termName\":\"2016-2017-2\",\"page\":\"2\",\"size\":\"12\",\"count\":\"20\",\"resultsTagList\":[{\"resultsTagId\":\"1\",\"resultsTagName\":\"2016年9月校考模拟考试\",\"resultsTagExplain\":\"这是一场摸底考试\",\"startTime\":\"2016-8-9 00:00:00\",\"endTime\":\"2016-8-10 00:00:00\",\"createTime\":\"2016-8-10 00:00:00\"},{\"resultsId\":\"2\",\"resultsTagName\":\"2016年9月校考模拟考试1\",\"resultsTagExplain\":\"这是一场摸底考试2\",\"startTime\":\"2016-9-9 00:00:00\",\"endTime\":\"2016-9-10 00:00:00\",\"createTime\":\"2016-9-10 00:00:00\"}]}";
				JSONObject jobj = JSON.parseObject(str);
				result.setData(jobj);
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				if (size == null || size == 0) {
					size = 12;
				}
				if (page == null || page == 0) {
					page = 1;
				}
				// 获得学期名字
				STermDBO sTermDBO = new STermDBO();
				sTermDBO.setTermId(termId);
				STermService_.doRead(sTermDBO);
				String termName = sTermDBO.getTermName();

				// 获取成绩标签列表信息
				ResultsTagDBO resultsTagDBO = new ResultsTagDBO();
				resultsTagDBO.setTermId(termId);
				pageModel.setPageCurrent(page);
				pageModel.setPageLimit(size);
				pageModel.setFormParamBean(resultsTagDBO);
				ResultsTagService_.doSelectPage(pageModel);
				List<ResultsTagDBO> resultsTagDBOs = (List<ResultsTagDBO>) pageModel.getPageListData();
				JSONArray jsonArray = new JSONArray();
				for (ResultsTagDBO resultsTagDBO2 : resultsTagDBOs) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("resultsTagId", resultsTagDBO2.getResultsTagId());
					jsonObject.put("resultsTagName", resultsTagDBO2.getResultsTagName());
					jsonObject.put("resultsTagExplain", resultsTagDBO2.getResultsTagExplain());
					jsonObject.put("startTime", resultsTagDBO2.getStartTime());
					jsonObject.put("endTime", resultsTagDBO2.getEndTime());
					jsonObject.put("createTime", resultsTagDBO2.getCreateTime());
					jsonArray.add(jsonObject);
				}

				JSONObject data = new JSONObject();
				data.put("termName", termName);
				data.put("page", page);
				data.put("size", size);
				data.put("count", pageModel.getResultCount());
				data.put("resultsTagList", jsonArray);
				result.setData(data);

			}
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 13.1.2 成绩标签新增接口 /qxy/results/tag?token=[token]
	 * 成绩标签默认有学校属性，新增时接口需要根据token中sid进行关联；
	 * 
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/results/tag", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsTagPOST(Boolean test, String token, @RequestBody ResultsTagDBO dbo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {
				// if (josn.get("termId") != null && josn.get("resultsTagName")
				// != null) {
				//// String str = "{\"status\":\"0\", \"data\":{ \"info\":\"ok\"
				// }}";
				//// JSONObject jobj=JSON.parseObject(str);
				//// result.setData(jobj);
				// } else{
				// result.setInfo("学期id--必填项,成绩标签名称--必填项");
				// }
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				dbo.setIsUse(1);
				if (dbo.getTermId() == null) {
					throw new IllegalArgumentException("该学期id不存在");
				}
				if (dbo.getResultsTagName() == null) {
					throw new IllegalArgumentException("该成绩标签名称不存在");
				}

				ResultsTagService_.doInsert(dbo);

			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 13.1.3 成绩标签修改接口 /qxy/results/tag?token=[token]
	 */
	@RequestMapping(value = "/results/tag", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsTagPUT(Boolean test, String token, @RequestBody ResultsTagDBO dbo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {
				// if (josn.get("resultsTagId") != null ) {
				//// String str = "{\"status\":\"0\", \"data\":{ \"info\":\"ok\"
				// }}";
				//// JSONObject jobj=JSON.parseObject(str);
				//// result.setData(jobj);
				// } else{
				// result.setInfo("成绩标签id--必填项");
				// }
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				if (dbo.getResultsTagId() == null) {
					throw new IllegalArgumentException("该成绩标签id不存在");
				}

				ResultsTagService_.doUpdate(dbo);

			}

		} catch (Exception e) {
			result.setInfo("修改失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 13.1.4 成绩标签删除接口 /qxy/results/tag?token=[token]
	 */
	@RequestMapping(value = "/results/tag", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsTagDELETE(Boolean test, String token, @RequestBody ResultsTagDBO dbo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {
				// if (josn.get("resultsTagId") != null) {
				//// String str = "{\"status\":\"0\", \"data\":{ \"info\":\"ok\"
				// }}";
				//// JSONObject jobj=JSON.parseObject(str);
				//// result.setData(jobj);
				// } else{
				// result.setInfo("成绩标签id--必填项");
				// }
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				if (dbo.getResultsTagId() == null) {
					throw new IllegalArgumentException("该成绩标签id不存在");
				}
				ResultsTagService_.doDelete(dbo);

			}
		} catch (Exception e) {
			result.setInfo("删除失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 13.1.5 成绩标签名字搜索接口
	 * /qxy/results/tag/search?termId=[termId]&resultsTagName=[resultsTagName]&
	 * token=[token] 需要使用Token验证查询者身份 根据班级cid进行搜索，只用与班级搜索
	 * 
	 * @param test
	 * @return
	 */

	@RequestMapping(value = "/results/tag/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsTagSearchGET(Boolean test, Long termId, String token, String resultsTagName) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {
				if (termId != null && resultsTagName != null) {
					String str = "{\"termName\":\"2016-2017-2\",\"count\":\"2\",\"resultsTagList\":[{\"resultsTagId\":\"1\",\"resultsTagName\":\"2016年9月校考模拟考试\",\"resultsTagExplain\":\"这是一场摸底考试\",\"startTime\":\"2016-8-9 00:00:00\",\"endTime\":\"2016-8-10 00:00:00\",\"createTime\":\"2016-8-10 00:00:00\"},{\"resultsId\":\"2\",\"resultsTagName\":\"2016年9月校考模拟考试1\",\"resultsTagExplain\":\"这是一场摸底考试2\",\"startTime\":\"2016-9-9 00:00:00\",\"endTime\":\"2016-9-10 00:00:00\",\"createTime\":\"2016-9-10 00:00:00\"}]}";
					JSONObject jobj = JSON.parseObject(str);
					result.setData(jobj);
				} else {
					result.setInfo("学期Id--必填项, 成绩标签名称--必填项");
				}
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}

				// 获得学期名字
				STermDBO sTermDBO = new STermDBO();
				if (termId == null) {
					throw new IllegalArgumentException("该学期id不存在");
				}
				sTermDBO.setTermId(termId);
				STermService_.doRead(sTermDBO);
				String termName = sTermDBO.getTermName();

				// 获取成绩标签列表信息
				ResultsTagDBO resultsTagDBO = new ResultsTagDBO();
				resultsTagDBO.setTermId(termId);
				if (resultsTagName == null) {
					throw new IllegalArgumentException("该成绩标签名称不存在");
				}
				resultsTagDBO.setResultsTagName(resultsTagName);
				List<ResultsTagDBO> resultsTagList = (List<ResultsTagDBO>) ResultsTagService_.doSelectDataList(resultsTagDBO);
				JSONObject data = new JSONObject();
				data.put("termName", termName);
				data.put("count", resultsTagList.size());
				data.put("resultsTagList", resultsTagList);
				result.setData(data);
			}
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 13.1.6
	 * 成绩标签详情查询接口/qxy/results/tag/info?resultsTagId=[resultsTagId]&token=[token]
	 * 需要使用Token验证查询者身份
	 */

	@RequestMapping(value = "/results/tag/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsTagInfoGET(Boolean test, Long resultsTagId, String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {
				if (resultsTagId != null) {
					String str = "{\"resultsTagId\":\"1\",\"resultsTagName\":\"2016年9月校考模拟考试\",\"resultsTagExplain\":\"这是一场摸底考试\",\"startTime\":\"2016-8-9 00:00:00\",\"endTime\":\"2016-8-10 00:00:00\",\"createTime\":\"2016-8-10 00:00:00\"}";
					JSONObject jobj = JSON.parseObject(str);
					result.setData(jobj);
				} else {
					result.setInfo("成绩标签id--必填项");
				}
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				// 获取成绩标签信息
				ResultsTagDBO resultsTagDBO = new ResultsTagDBO();
				if (resultsTagId == null) {
					throw new IllegalArgumentException("该成绩标签id不存在");
				}
				resultsTagDBO.setResultsTagId(resultsTagId);
				ResultsTagService_.doRead(resultsTagDBO);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("resultsTagId", resultsTagDBO.getResultsTagId());
				jsonObject.put("resultsTagName", resultsTagDBO.getResultsTagName());
				jsonObject.put("resultsTagExplain", resultsTagDBO.getResultsTagExplain());
				jsonObject.put("startTime", resultsTagDBO.getStartTime());
				jsonObject.put("endTime", resultsTagDBO.getEndTime());
				jsonObject.put("createTime", resultsTagDBO.getCreateTime());
				result.setData(jsonObject);
			}

		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}
	

}
