package com.mcookies.qxy.biz.moraleducation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.jsoup.helper.DataUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.Class.ClassDBO;
import com.mcookies.qxy.common.Class.ClassService;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherDBO;
import com.mcookies.qxy.common.DeviceTag.DeviceTagService;
import com.mcookies.qxy.common.MecItem.MecItemDBO;
import com.mcookies.qxy.common.MecItem.MecItemDao;
import com.mcookies.qxy.common.MecItem.MecItemService;
import com.mcookies.qxy.common.MecScore.MecScoreDBO;
import com.mcookies.qxy.common.MecScore.MecScorePVO;
import com.mcookies.qxy.common.MecScore.MecScoreService;
import com.mcookies.qxy.common.News.NewsPVO;
import com.mcookies.qxy.common.SCourse.SCourseDBO;
import com.mcookies.qxy.common.STerm.STermDBO;
import com.mcookies.qxy.common.STerm.STermService;
import com.mcookies.qxy.common.STrip.STripPVO;
import com.mcookies.qxy.common.SWorkRule.SWorkRuleDBO;
import com.mcookies.qxy.common.SWorkTime.SWorkTimeDBO;
import com.mcookies.qxy.common.UTeacher.UTeacherDBO;
import com.mcookies.qxy.utils.DateUtils;
import com.sun.mail.handlers.message_rfc822;

/**
 * 德育班级管理
 * 
 * @author james
 *
 */
@Controller
public class MoraleducationClassController extends MyControllerSupport {
	@Resource
	protected MecScoreService MecScoreService_;
	@Resource
	protected MecItemService MecItemService_;
	@Resource
	protected ClassService ClassService_;
	@Resource
	protected STermService STermService_;

	
	/**
	 *  12.1.1-2 根据年级查询班级列表接口
	 * /qxy/classlist/grade/search?termId=[termId]&gradeId=[gradeId]&page=[page]&size=[size]&token=[token]
	 * 可以根据年级标签查询查询班级列表，学期为可选参数，不填或为0则默认查询该学期下的班级；年级参数为必填参数；
	 * 需要使用Token验证查询者身份
	 */

	@RequestMapping(value = "/classlist/grade/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classlistGradeSearchGET(Boolean test, String token, Long termId,Long gradeId,Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				if (gradeId != null) {
					String str = "{\"page\":\"1\",\"size\":\"12\",\"count\":\"2\",\"classlist\":[{\"cid\":\"2\",\"className\":\"一班\"},{\"cid\":\"3\",\"className\":\"二班\"}]}";
					JSONObject jobj = JSON.parseObject(str);
					result.setData(jobj);	
				} else {
					result.setInfo("年级id， --必填项");
				}
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
				// 获取班级列表信息
				ClassDBO classDBO = new ClassDBO();
				classDBO.setTermId(termId);
				if (gradeId == null) {
					result.setInfo("年级id");
				} else {
					classDBO.setGradeId(gradeId);
				}
				pageModel.setPageCurrent(page);
				pageModel.setPageLimit(size);
				pageModel.setFormParamBean(classDBO);
				ClassService_.doSelectPage(pageModel);
				List<ClassDBO> classDBOs = (List<ClassDBO>) pageModel.getPageListData();
				List<Map<String, Object>> classlist = new ArrayList<Map<String, Object>>();
				for (ClassDBO classDBO2 : classDBOs) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("cid", classDBO2.getCid());
					map.put("className", classDBO2.getClassName());	
					classlist.add(map);
				}
				JSONObject data = new JSONObject();
				data.put("page", page);
				data.put("size", size);
				data.put("count", pageModel.getResultCount());
				data.put("classlist", classlist);
				result.setData(data);
			}
			 
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 *  12.1.2 德育班级评分列表查询接口德育班级评分列表查询接口
	 * /qxy/moraleducation/class?termId=[termId]&order=[order]&page=[page]&size=
	 * [size]&token=[token] 德育班级评分列表查询接口当学期id为空时，返回默认学期下班级列表数据。本接口支持分页查询班级评分列表
	 * 需要使用Token验证查询者身份
	 */

	@RequestMapping(value = "/moraleducation/class/score", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean moraleducationClassGET(Boolean test, String token, Long termId, String order, Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				String str = "{\"page\":\"1\",\"size\":\"14\",\"count\":\"3\",\"classScoreList\": [{\"cid\": \"4\",\"className\": \"初一1班\",\"totalScore\": \"90\",\"ranking\": \"12\",\"updateTime\":\"2016-09-19 00:00:00\"},{\"cid\": \"4\",\"className\": \"初一2班\",\"totalScore\": \"92\",\"ranking\": \"11\",\"updateTime\":\"2016-09-19 00:00:00\"}]}";
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
				// 获取德育班级评分列表信息
				MecScorePVO mecScorePVO = new MecScorePVO();
				mecScorePVO.setTermId(termId);
				pageModel.setPageCurrent(page);
				pageModel.setPageLimit(size);
				pageModel.setFormParamBean(mecScorePVO);
				if ("1".equals(order)) {
					MecScoreService_.doSelectPageMecScoreDESC(pageModel);
				} else {
					MecScoreService_.doSelectPageMecScoreASC(pageModel);
				}
				List<MecScorePVO> classScoreList = (List<MecScorePVO>) pageModel.getPageListData();
				for (MecScorePVO mecScorePVO2 : classScoreList) {
					SimpleDateFormat df = new SimpleDateFormat(DateUtils.FORMAT_yyyy_MM_dd_HH_mm_ss);// 设置日期格式
					mecScorePVO2.setUpdateTime(df.format(new Date()));// new
																		// Date()为获取当前系统时间
				}
				JSONObject data = new JSONObject();
				data.put("page", page);
				data.put("size", size);
				data.put("count", pageModel.getResultCount());
				data.put("classScoreList", classScoreList);
				result.setData(data);

			}

		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 12.1.3-1 德育班级评分班级搜索接口 /qxy/moraleducation/class/search?cid=[cid]&token=[token]
	 * 需要使用Token验证查询者身份 根据班级cid进行搜索，只用与班级搜索
	 * 
	 * @param test
	 * @return
	 */

	@RequestMapping(value = "/moraleducation/class/score/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean moraleducationClassSearchGET(Boolean test, String token, Long cid) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				if (cid != null) {
					String str = "{\"cid\":\"4\",\"className\":\"初一1班\",\"totalScore\":\"90\",\"ranking\":\"12\",\"updateTime\":\"2016-09-19 00:00:00\"}";
					JSONObject jobj = JSON.parseObject(str);
					result.setData(jobj);
				} else {
					result.setInfo("班级id --必填项");
				}
			} else {
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				if (cid == null) {
					throw new IllegalArgumentException("班级id不存在");
				}
				MecScorePVO mecScorePVO = new MecScorePVO();
				mecScorePVO.setCid(cid);
				mecScorePVO = (MecScorePVO) MecScoreService_.doReadMecScore(mecScorePVO);
				SimpleDateFormat df = new SimpleDateFormat(DateUtils.FORMAT_yyyy_MM_dd_HH_mm_ss);// 设置日期格式
				mecScorePVO.setUpdateTime(df.format(new Date()));// new
																	// Date()为获取当前系统时间
				result.setData(mecScorePVO);
			}

		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 12.1.3-2 德育班级评分年级班级搜索接口
	 * /qxy/moraleducation/class/score/gradeclass/search?termId=[termId]&gradeId=[gradeId]&cid=[cid]&order=[order]&page=[page]&size=[size]&token=[token]
	 * 需要使用Token验证查询者身份
	 * @param test
	 * @param page
	 * @param size
	 * @return
	 */

	@RequestMapping(value = "/moraleducation/class/score/gradeclass/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean mecClassScoreGradeClassGET(Boolean test, Long termId,Long gradeId,Long cid, String order,String token, Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
					String str = "{\"page\":\"1\",\"size\":\"14\",\"count\":\"3\",\"classScoreList\":[{\"cid\":\"4\",\"className\":\"初一1班\",\"totalScore\":\"90\",\"ranking\":\"12\",\"updateTime\":\"2016-09-19 12:00:00\"},{\"cid\":\"4\",\"className\":\"初一2班\",\"totalScore\":\"92\",\"ranking\":\"11\",\"updateTime\":\"2016-09-19 12:00:00\"},{\"cid\":\"4\",\"className\":\"初一3班\",\"totalScore\":\"95\",\"ranking\":\"9\",\"updateTime\":\"2016-09-19 12:00:00\"}]}";
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
				// 获取德育班级评分列表信息
				MecScorePVO mecScorePVO = new MecScorePVO();
				mecScorePVO.setTermId(termId);
				mecScorePVO.setGradeId(gradeId);
				mecScorePVO.setCid(cid);
				pageModel.setPageCurrent(page);
				pageModel.setPageLimit(size);
				pageModel.setFormParamBean(mecScorePVO);
				if ("1".equals(order)) {
					MecScoreService_.doSelectPageMecScrDESC(pageModel);
				} else {
					MecScoreService_.doSelectPageMecScrASC(pageModel);
				}
				List<MecScorePVO> classScoreList = (List<MecScorePVO>) pageModel.getPageListData();
				for (MecScorePVO mecScorePVO2 : classScoreList) {
					SimpleDateFormat df = new SimpleDateFormat(DateUtils.FORMAT_yyyy_MM_dd_HH_mm_ss);// 设置日期格式
					mecScorePVO2.setUpdateTime(df.format(new Date()));// new
																		// Date()为获取当前系统时间
				}
				JSONObject data = new JSONObject();
				data.put("page", page);
				data.put("size", size);
				data.put("count", pageModel.getResultCount());
				data.put("classScoreList", classScoreList);
				result.setData(data);				
			}

		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}
	
	/**
	 * 12.1.4  德育班级评分详情列表查询接口
	 * /qxy/moraleducation/class/score/list?cid=[cid]&page=[page]&size=[size]&
	 * token=[token] 需要使用Token验证查询者身份
	 * 
	 * @param test
	 * @param page
	 * @param size
	 * @return
	 */

	@RequestMapping(value = "/moraleducation/class/score/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean mecClassScoreListGET(Boolean test, Long cid, String token, Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				if (cid != null) {
					String str = "{\"termName\":\"2016-2017-2\",\"className\":\"高一三班\",\"totalScore\":\"20\",\"ranking\":\"12\",\"page\":\"2\",\"size\":\"12\",\"count\":\"20\",\"mecScoreList\":[{\"mecScoreId\":\"1\",\"mecItemId\":\"1\",\"mecItemName\":\"黑板报\",\"score\":\"-1\",\"teacherName\":\"王主任\",\"mecItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"createTime\":\"2016-09-13 00:00:00\"},{\"mecScoreId\":\"2\",\"mecItemId\":\"2\",\"mecItemName\":\"黑板报1\",\"score\":\"-2\",\"teacherName\":\"王主任\",\"mecItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"createTime\":\"2016-09-13 00:00:00\"}]}";
					JSONObject jobj = JSON.parseObject(str);
					result.setData(jobj);
				} else {
					result.setInfo("班级id --必填项");
				}
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
				JSONObject data = new JSONObject();
				// 获得termId
				ClassDBO classDBO = new ClassDBO();
				classDBO.setPuk(ONE);
				if (cid == null) {
					throw new IllegalArgumentException("该班级cid不存在");
				}
				classDBO.setCid(cid);
				classDBO = (ClassDBO) ClassService_.doRead(classDBO);
				// 获得学期名称
				STermDBO sTermDBO = new STermDBO();
				sTermDBO.setTermId(classDBO.getTermId());
				sTermDBO = (STermDBO) STermService_.doRead(sTermDBO);
				data.put("termName", sTermDBO.getTermName());
				// 获得班级排名情况
				MecScorePVO mecScorePVO = new MecScorePVO();
				mecScorePVO.setCid(cid);
				mecScorePVO = (MecScorePVO) MecScoreService_.doReadMecScore(mecScorePVO);
				data.put("className", mecScorePVO.getClassName());
				data.put("totalScore", mecScorePVO.getTotalScore());
				data.put("ranking", mecScorePVO.getRanking());
				// 获得班级评分详情列表
				MecScorePVO mecSP = new MecScorePVO();
				mecSP.setTermId(classDBO.getTermId());
				mecSP.setCid(cid);
				pageModel.setPageCurrent(page);
				pageModel.setPageLimit(size);
				pageModel.setFormParamBean(mecSP);
				MecScoreService_.doSelectPageMecScore(pageModel);
				List<MecScorePVO> mecScoreList = (List<MecScorePVO>) pageModel.getPageListData();
				data.put("page", page);
				data.put("size", size);
				data.put("count", pageModel.getResultCount());
				data.put("mecScoreList", mecScoreList);
				result.setData(data);
			}

		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 12.1.5德育班级评分详情列表搜索接口 /qxy/moraleducation/class/score/list/search?cid=[cid]&mecItemId=[mecItemId]&startTime=[startTime]&endTime=[endTime]&page=[page]&size=[size]&token=[token]
	 *  需要使用Token验证查询者身份
	 * 
	 * @param test
	 * @param page
	 * @param size
	 * @return
	 */

	@RequestMapping(value = "/moraleducation/class/score/list/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean mecClassScoreListSearchGET(Boolean test, Long cid,Long mecItemId, String token, String startTime, String endTime, Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				String str = "{\"termName\":\"2016-2017-2\",\"className\":\"高一三班\",\"totalScore\":\"20\",\"ranking\":\"12\",\"page\":\"2\",\"size\":\"12\",\"count\":\"20\",\"mecScoreList\":[{\"mecScoreId\":\"1\",\"mecItemId\":\"1\",\"mecItemName\":\"黑板报\",\"score\":\"-1\",\"teacherName\":\"王主任\",\"mecItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"createTime\":\"2016-09-13 00:00:00\"},{\"mecScoreId\":\"2\",\"mecItemId\":\"2\",\"mecItemName\":\"黑板报1\",\"score\":\"-2\",\"teacherName\":\"王主任\",\"mecItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"createTime\":\"2016-09-13 00:00:00\"}]}";
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
				// 获得班级cid
//				MecScoreDBO mecScoreDBO = new MecScoreDBO();
//				if (mecItemId == null) {
//					throw new IllegalArgumentException("该德育班级项目id不存在");
//				}
//				mecScoreDBO.setMecItemId(mecItemId);
//				MecScoreService_.doRead(mecScoreDBO);
//				Long cid = mecScoreDBO.getCid();
				JSONObject data = new JSONObject();
				// 获得termId
				ClassDBO classDBO = new ClassDBO();
				classDBO.setPuk(ONE);
				classDBO.setCid(cid);
				classDBO = (ClassDBO) ClassService_.doRead(classDBO);
				// 获得学期名称
				STermDBO sTermDBO = new STermDBO();
				sTermDBO.setTermId(classDBO.getTermId());
				sTermDBO = (STermDBO) STermService_.doRead(sTermDBO);
				data.put("termName", sTermDBO.getTermName());
				// 获得班级排名情况
				MecScorePVO mecScorePVO = new MecScorePVO();
				mecScorePVO.setCid(cid);
				mecScorePVO = (MecScorePVO) MecScoreService_.doReadMecScore(mecScorePVO);
				data.put("className", mecScorePVO.getClassName());
				data.put("totalScore", mecScorePVO.getTotalScore());
				data.put("ranking", mecScorePVO.getRanking());
				// 获得班级评分详情列表
				MecScorePVO mecSP = new MecScorePVO();
				mecSP.setTermId(classDBO.getTermId());
				mecSP.setCid(cid);
				mecSP.setMecItemId(mecItemId);
				mecSP.setStartTime(startTime);
				mecSP.setEndTime(endTime);
				pageModel.setPageCurrent(page);
				pageModel.setPageLimit(size);
				pageModel.setFormParamBean(mecSP);
				MecScoreService_.doSelectPageMecScoreList(pageModel);
				List<MecScorePVO> mecScoreList = (List<MecScorePVO>) pageModel.getPageListData();
				data.put("page", page);
				data.put("size", size);
				data.put("count", pageModel.getResultCount());
				data.put("mecScoreList", mecScoreList);
				result.setData(data);
			}

		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 德育班级评分新增接口/qxy/moraleducation/class/score?token=[token]
	 * 
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/moraleducation/class/score", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean mecClassScorePOST(Boolean test, String token, @RequestBody MecScoreDBO dbo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				// if (josn.get("cid") != null && josn.get("mecItemId") != null
				// && josn.get("scoreOrder") != null) {
				// // String str = "{\"status\":\"0\", \"data\":{
				// // \"info\":\"ok\" }}";
				// // JSONObject jobj=JSON.parseObject(str);
				// // result.setData(jobj);
				// } else {
				// result.setInfo("班级id --必填项,德育项目id
				// --必填,德育班级评分序号（合理范围为1-6）--必填");
				// }
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				dbo.setIsUse(1);
				if (dbo.getCid() == null) {
					throw new IllegalArgumentException("该德育班级cid不存在");
				}
				if (dbo.getMecItemId() == null) {
					throw new IllegalArgumentException("该德育班级项目id不存在");
				}
				if (dbo.getScroeOrder() == null) {
					throw new IllegalArgumentException("德育班级评分序号（合理范围为1-6）");
				}
				MecItemDBO mecItemDBO =  new MecItemDBO();
				mecItemDBO.setMecItemId(dbo.getMecItemId());
				mecItemDBO = (MecItemDBO) MecItemService_.doRead(mecItemDBO);
				//初始分+间隔分*评分序号
				String scroeOrder = dbo.getScroeOrder();//德育班级评分序号
			    String initialScore = mecItemDBO.getInitialScore();//初始分
			    String intervalScore = mecItemDBO.getIntervalScore();//间隔分
			    int scroeOrderInt =  Integer.parseInt(scroeOrder);
			    int initialScoreInt = Integer.parseInt(initialScore);
			    int intervalScoreInt = Integer.parseInt(intervalScore);
			    int scroeInt = intervalScoreInt*scroeOrderInt+initialScoreInt;
			    String scroe = Integer.toString(scroeInt);
			    dbo.setScroe(scroe);
				MecScoreService_.doInsert(dbo);
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}		
		return result;
	}

	/**
	 * 德育班级评分删除接口/qxy/moraleducation/class/score?token=[token]
	 */
	@RequestMapping(value = "/moraleducation/class/score", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean mecClassScoreDELETE(Boolean test, String token, @RequestBody MecScoreDBO dbo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				// if (josn.get("mecScoreId") != null) {
				// // String str = "{\"status\":\"0\", \"data\":{
				// // \"info\":\"ok\" }}";
				// // JSONObject jobj=JSON.parseObject(str);
				// // result.setData(jobj);
				// } else {
				// result.setInfo("德育项目id --必填");
				// }
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				MecScoreService_.doDelete(dbo);

			}
		} catch (Exception e) {
			result.setInfo("删除失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 12.1.8 德育班级评分详情查询接口/qxy/moraleducation/class/score/info?mecScoreId=[mecScoreId]&
	 * token=[token] 需要使用Token验证查询者身份
	 */

	@RequestMapping(value = "/moraleducation/class/score/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean mecClassScoreInfoGET(Boolean test, String token, Long mecScoreId) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
				if (mecScoreId != null) {
					String str = "{\"mecItemName\":\"黑板报\",\"score\":\"-1\",\"mecItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"scoreTime\":\"2016-09-13 00:00:00\",\"teacherName\":\"王主任\",\"scoreOrder\":\"1\",\"scoreContent\":\"1\",\"scorePic\":[\"http://www.100xyun.com/upload/123.jpg\",\"http://www.100xyun.com/upload/123.jpg\"]}";
					JSONObject jobj = JSON.parseObject(str);
					result.setData(jobj);
				} else {
					result.setInfo(" 班级评分id  --必填项");
				}
			} else {
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				if (mecScoreId == null) {
					throw new IllegalArgumentException("班级评分id不存在");
				}
				//获得cid
				MecScoreDBO mecScoreDBO = new MecScoreDBO();
				mecScoreDBO.setMecScoreId(mecScoreId);
				mecScoreDBO = (MecScoreDBO) MecScoreService_.doRead(mecScoreDBO);
				Long cid = mecScoreDBO.getCid();
				JSONObject data = new JSONObject();
				// 获得班级排名情况
				MecScorePVO mecScorePVO = new MecScorePVO();
				mecScorePVO.setCid(cid);
				mecScorePVO = (MecScorePVO) MecScoreService_.doReadMecScore(mecScorePVO);
				data.put("totalScore", mecScorePVO.getTotalScore());
								
				MecScoreDBO mecScoreDBO2 = new MecScoreDBO();
				mecScoreDBO2.setMecScoreId(mecScoreId);
				MecScorePVO mecScorePVO1 = MecScoreService_.doReadMecScoreBymecScoreId(mecScoreDBO);
				data.put("mecItemName", mecScorePVO1.getMecItemName());
				data.put("score", mecScorePVO1.getScroe());
				data.put("mecItemExplain", mecScorePVO1.getMecItemExplain());
				data.put("scoreTime", mecScorePVO1.getScoreTime());
				data.put("teacherName", mecScorePVO1.getTeacherName());
				data.put("scoreOrder", mecScorePVO1.getScroeOrder());
				data.put("scoreContent", mecScorePVO1.getScoreContent());
				data.put("scorePic", mecScorePVO1.getScorePic());
				result.setData(data);
			}

		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 德育班级项目列表查询接口/qxy/moraleducation/class?sid=[sid]&token=[token]
	 * 需要使用Token验证查询者身份
	 */

	@RequestMapping(value = "/moraleducation/class", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean mecClassGET(Boolean test, Long sid, String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {

				String str = "{\"count\":\"12\",\"mecItemList\":[{\"mecItemId\":\"23\",\"mecItemName\":\"黑板报\",\"mecItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"ruleNum\":\"1\",\"initialScore\":\"0\",\"intervalScore\":\"2\",\"isUse\":\"1\"},{\"mecItemId\":\"24\",\"mecItemName\":\"黑板报\",\"mecItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"ruleNum\":\"1\",\"initialScore\":\"0\",\"intervalScore\":\"2\",\"isUse\":\"1\"},{\"mecItemId\":\"25\",\"mecItemName\":\"黑板报\",\"mecItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"ruleNum\":\"1\",\"initialScore\":\"0\",\"intervalScore\":\"2\",\"isUse\":\"1\"}]}";
				JSONObject jobj = JSON.parseObject(str);
				result.setData(jobj);

			} else {
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				if (sid == null) {
					throw new IllegalArgumentException("该学校id不存在");
				}
				// 得到德育班级项目列表
				MecItemDBO mecItemDBO = new MecItemDBO();
				mecItemDBO.setSid(sid);
				pageModel.setFormParamBean(mecItemDBO);
				MecItemService_.doSelectPage(pageModel);
				List<MecItemDBO> mecItemList = (List<MecItemDBO>) pageModel.getPageListData();
				JSONObject data = new JSONObject();
				data.put("count", pageModel.getResultCount());
				data.put("mecItemList", mecItemList);
				result.setData(data);
			}

		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 德育班级项目新增接口/qxy/moraleducation/class?token=[token]
	 * 
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/moraleducation/class", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean mecClassPOST(Boolean test, String token, @RequestBody MecItemDBO dbo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
//				if (josn.get("cid") != null && josn.get("mecItemName") != null && josn.get("ruleNum") != null) {
//					// String str = "{\"status\":\"0\", \"data\":{
//					// \"info\":\"ok\" }}";
//					// JSONObject jobj=JSON.parseObject(str);
//					// result.setData(jobj);
//				} else {
//					result.setInfo("班级id --必填项,德育班级项目名称  --必填项,规则分数量必须大于0  --必填项");
//				}
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				dbo.setIsUse(1);
				if (dbo.getSid() == null) {
					throw new IllegalArgumentException("该学校id不存在");
				}
				if (dbo.getMecItemName() == null) {
					throw new IllegalArgumentException("该德育班级项目名称不存在");
				}
				if (dbo.getRuleNum() == null) {
					throw new IllegalArgumentException("规则分数量必须大于0");
				}
				if (dbo.getInitialScore() == null) {
					throw new IllegalArgumentException("初始分不存在");
				}
				if (dbo.getIntervalScore() == null) {
					throw new IllegalArgumentException("间隔分不存在");
				}
				MecItemService_.doInsert(dbo);
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 德育班级项目修改接口 /qxy/moraleducation/class?token=[token]
	 */
	@RequestMapping(value = "/moraleducation/class", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean mecClassPUT(Boolean test, String token, @RequestBody MecItemDBO dbo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
//				if (josn.get("mecItemId") != null) {
//					// String str = "{\"status\":\"0\", \"data\":{
//					// \"info\":\"ok\" }}";
//					// JSONObject jobj=JSON.parseObject(str);
//					// result.setData(jobj);
//				} else {
//					result.setInfo("德育班级项目id --必填项");
//				}
			} else {
				//token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				if (dbo.getMecItemId() == null) {
					throw new IllegalArgumentException("该德育班级项目id不存在");
				}
				
				MecItemService_.doUpdate(dbo);

			}

		} catch (Exception e) {
			result.setInfo("修改失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 德育班级项目停用/启用接口 /qxy/moraleducation/class/set?token=[token]
	 */
	@RequestMapping(value = "/moraleducation/class/set", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean mecClassSetPUT(Boolean test, String token, @RequestBody MecItemDBO dbo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
//				if (josn.get("mecItemId") != null && josn.get("isUse") != null) {
//					// String str = "{\"status\":\"0\", \"data\":{
//					// \"info\":\"ok\" }}";
//					// JSONObject jobj=JSON.parseObject(str);
//					// result.setData(jobj);
//				} else {
//					result.setInfo("德育班级项目id --必填项,0-启用；1-停用 --必填项");
//				}
			} else {
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				if (dbo.getMecItemId() == null) {
					throw new IllegalArgumentException("该德育班级项目id不存在");
				}
				if (dbo.getIsUse() == null) {
					throw new IllegalArgumentException("该启用和停用不存在");
				}
				MecItemService_.doUpdate(dbo);
			}

		} catch (Exception e) {
			result.setInfo("修改失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 德育班级项目删除接口 /qxy/moraleducation/class?token=[token]
	 */
	@RequestMapping(value = "/moraleducation/class", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean mecClassDELETE(Boolean test, String token, @RequestBody MecItemDBO dbo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test == true) {
//				if (josn.get("mecItemId") != null) {
//					// String str = "{\"status\":\"0\", \"data\":{
//					// \"info\":\"ok\" }}";
//					// JSONObject jobj=JSON.parseObject(str);
//					// result.setData(jobj);
//				} else {
//					result.setInfo("评分id --必填项");
//				}
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				if (dbo.getMecItemId() == null) {
					throw new IllegalArgumentException("该德育班级项目id不存在");
				}
				MecScoreService_.doDelete(dbo);
			}
		} catch (Exception e) {
			result.setInfo("删除失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}
	
			/**
		 * 12.1.14 德育班级项目详情查询接口/qxy/moraleducation/class/info?mecItemId=[mecItemId]&token=[token]
		 * 德育班级项目详情查询接口提供对德育班级项目详情查询的功能。
		 * 需要使用Token验证查询者身份
		 */
				 
		@RequestMapping(value = "/moraleducation/class/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecClassInfoGET(Boolean test,String token,Long mecItemId) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test == true) {
					if (mecItemId != null) {
						String str = "{\"mecItemId\":\"23\",\"mecItemName\":\"黑板报\",\"mecItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"ruleNum\":\"1\",\"initialScore\":\"0\",\"intervalScore\":\"2\",\"isUse\":\"1\"}";
						JSONObject jobj=JSON.parseObject(str);    
						result.setData(jobj);	
					} else{
						result.setInfo("班级德育项目id ---必填项");
					}
				} else {
					if (doCheckToken(token) == false) {
						return tokenFail();
					}
					// 得到德育班级项目列表
					MecItemDBO mecItemDBO = new MecItemDBO();
					if (mecItemId == null) {
						result.setInfo("德育班级项目id不存在");
					} else {
						mecItemDBO.setMecItemId(mecItemId);
					}
					mecItemDBO = (MecItemDBO) MecItemService_.doRead(mecItemDBO);
//					JSONObject data = new JSONObject();;
//					data.put("mecItemList", mecItemList);
					result.setData(mecItemDBO);				
				}
				
			} catch (Exception e) {
				result.setInfo("查询失败，" + e.getMessage());
				result.setStatus(1);
			}

			return result;
		}

}
