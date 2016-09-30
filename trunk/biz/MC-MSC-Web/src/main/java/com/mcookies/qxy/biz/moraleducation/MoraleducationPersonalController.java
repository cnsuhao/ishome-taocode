package com.mcookies.qxy.biz.moraleducation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.xmlbeans.impl.jam.mutable.MPackage;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.token.UserCacheHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.Class.ClassDBO;
import com.mcookies.qxy.common.Class.ClassService;
import com.mcookies.qxy.common.ClassStudent.ClassStudentDBO;
import com.mcookies.qxy.common.ClassStudent.ClassStudentService;
import com.mcookies.qxy.common.MecItem.MecItemDBO;
import com.mcookies.qxy.common.MecItem.MecItemService;
import com.mcookies.qxy.common.MecScore.MecScorePVO;
import com.mcookies.qxy.common.MecScore.MecScoreService;
import com.mcookies.qxy.common.MepItem.MepItemDBO;
import com.mcookies.qxy.common.MepItem.MepItemService;
import com.mcookies.qxy.common.MepScore.MepScoreDBO;
import com.mcookies.qxy.common.MepScore.MepScorePVO;
import com.mcookies.qxy.common.MepScore.MepScoreService;
import com.mcookies.qxy.common.STerm.STermDBO;
import com.mcookies.qxy.common.STerm.STermService;
import com.mcookies.qxy.common.UTeacher.UTeacherDBO;
import com.mcookies.qxy.common.UTeacher.UTeacherService;
import com.mcookies.qxy.utils.DateUtils;
/**
 * 德育个人管理
 * @author james
 *
 */
@Controller
public class MoraleducationPersonalController extends MyControllerSupport {
	@Resource
	protected MepScoreService MepScoreService_;
	@Resource
	protected MepItemService MepItemService_;
	@Resource
	protected ClassStudentService ClassStudentService_;
	@Resource
	protected ClassService ClassService_;
	@Resource
	protected STermService STermService_;
	@Resource
	protected UTeacherService UTeacherService_;
	
	/**
	 * 12.2.2德育个人评分列表查询接口 /qxy/moraleducation/personal/score?termId=[termId]&gradeId=[gradeId]&cid=[cid]&order=[order]&page=[page]&size=[size]&token=[token]
	 * 德育班级评分列表查询接口当学期id为空时，返回默认学期下班级列表数据。本接口支持分页查询班级评分列表
     * 需要使用Token验证查询者身份
	 */
	
	@RequestMapping(value = "/moraleducation/personal/score", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean moraleducationPersonalGET(Boolean test,Long termId,Long cid,String order,String token,Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {			
					String str = "{\"page\":\"0\",\"size\":\"14\",\"count\":\"3\",\"personalScoreList\":[{\"studentId\":\"1\",\"studentName\":\"张三\",\"number\":\"1000003\",\"totalScore\":\"90\",\"ranking\":\"12\",\"updateTime\":\"2016-09-19 00:00:00\"},{\"studentId\":\"2\",\"studentName\":\"王五\",\"number\":\"1000004\",\"totalScore\":\"93\",\"ranking\":\"11\",\"updateTime\":\"2016-09-19 00:00:00\"},{\"studentId\":\"3\",\"studentName\":\"赵六\",\"number\":\"1000005\",\"totalScore\":\"97\",\"ranking\":\"9\",\"updateTime\":\"2016-09-19 00:00:00\"}]}";
					JSONObject jobj=JSON.parseObject(str);    
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
				// 获取德育个人评分列表信息
				MepScorePVO mepScorePVO = new MepScorePVO();
				mepScorePVO.setTermId(termId);
				if (cid == null) {
					throw new IllegalArgumentException("该班级cid不存在");
				}
				mepScorePVO.setCid(cid);
				pageModel.setPageCurrent(page);
				pageModel.setPageLimit(size);
				pageModel.setFormParamBean(mepScorePVO);
				if ("1".equals(order)) {
					MepScoreService_.doSelectPageMepScoreDESC(pageModel);
				} else {
					MepScoreService_.doSelectPageMepScoreASC(pageModel);
				}
				JSONArray jsonArray = new JSONArray();
				List<MepScorePVO> personalScoreList = (List<MepScorePVO>) pageModel.getPageListData();
				for (MepScorePVO mepScorePVO2 : personalScoreList) {
				
					JSONObject data2 = new JSONObject();
					data2.put("studentId", mepScorePVO2.getStudentId());
					data2.put("studentName", mepScorePVO2.getStudentName());
					data2.put("number", mepScorePVO2.getNumber());
					data2.put("totalScore", mepScorePVO2.getTotalScore());
					data2.put("ranking", mepScorePVO2.getRanking());
					data2.put("updateTime", DateHelper.currentTimeMillisCN1());
					jsonArray.add(data2);	
				}
				JSONObject data = new JSONObject();
				data.put("page", page);
				data.put("size", size);
				data.put("count", pageModel.getResultCount());
				data.put("personalScoreList", jsonArray);
				result.setData(data);
			}
			
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}
	 
	/**
	 * 12.2.3 德育个人评分个人搜索接口 /qxy/moraleducation/personal/score/search?termId=[termId]&gradeId=[gradeId]&cid=[cid]&studentName=[studentName]&token=[token]
	 * 需要使用Token验证查询者身份
	 * 根据班级cid进行搜索，只用与班级搜索
	 * @return
	 */
			 
	@SuppressWarnings({ "unchecked", "unchecked", "unchecked" })
	@RequestMapping(value = "/moraleducation/personal/score/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean moraleducationPersonalSearchGET(Boolean test,Long termId, Long gradeId,Long cid,String studentName,Long studentId,String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {
				if (studentId != null) {
					String str = "{\"studentId\":\"2\",\"studentName\":\"王五\",\"number\":\"1000004\",\"totalScore\":\"93\",\"ranking\":\"11\",\"updateTime\":\"2016-09-19 00:00:00\"}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
				} else{
					result.setInfo("学生id --必填项");
				}
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				//获得学期名称
				STermDBO sTermDBO = new STermDBO();
				sTermDBO.setTermId(termId);
				sTermDBO = (STermDBO) STermService_.doRead(sTermDBO);
				String termName = sTermDBO.getTermName();
				// 获取德育个人评分列表信息
				MepScorePVO mepScorePVO = new MepScorePVO();
				mepScorePVO.setTermId(termId);
				mepScorePVO.setGradeId(gradeId);
				mepScorePVO.setCid(cid);
				if (studentName == null) {
					result.setInfo("学生名字不存在");
				} else {
					mepScorePVO.setStudentName(studentName);
				}
				List<MepScorePVO> studentlist = MepScoreService_.doSeleDataPers(mepScorePVO);
				List<Map<String,Object>> retListMap = new ArrayList<Map<String, Object>>();
				for (MepScorePVO mepScorePVO2 : studentlist) {
					Map<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("studentId", mepScorePVO2.getStudentId());
					hashMap.put("studentName", mepScorePVO2.getStudentName());
					hashMap.put("number", mepScorePVO2.getNumber());
					hashMap.put("totalScore", mepScorePVO2.getTotalScore());
					hashMap.put("ranking", mepScorePVO2.getRanking());
					SimpleDateFormat df = new SimpleDateFormat(DateUtils.FORMAT_yyyy_MM_dd_HH_mm_ss);// 设置日期格式
					hashMap.put("updateTime", df.format(new Date()));
					retListMap.add(hashMap);
				}
				
				JSONObject data =  new JSONObject();
				data.put("termName", termName);
				data.put("count", studentlist.size());
				data.put("studentlist", retListMap);
				
				SimpleDateFormat df = new SimpleDateFormat(DateUtils.FORMAT_yyyy_MM_dd_HH_mm_ss);// 设置日期格式
				mepScorePVO.setUpdateTime(df.format(new Date()));// new Date()为获取当前系统时间
				result.setData(data);
			}
			
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}		 
	
	
		/**
		 * 12.2.4德育个人评分详情列表查询接口 /qxy/moraleducation/personal/score/list?termId=[termId]&studentId=[studentId]&page=[page]&size=[size]&token=[token]
		 * 需要使用Token验证查询者身份
		 * @param test
		 * @param page
		 * @param size
		 * @return
		 */
				 
		@RequestMapping(value = "/moraleducation/personal/score/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalScoreListGET(Boolean test,Long studentId,Long termId,String token,Integer page, Integer size) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test != null && test == true) {
					if (studentId != null) {
						String str = "{\"studentId\":\"1\",\"studentName\":\"段誉\",\"number\":\"0291029\",\"totalScore\":\"23\",\"ranking\":\"12\",\"page\":\"1\",\"size\":\"12\",\"count\":\"22\",\"mepScoreList\":[{\"mepScoreId\":\"1\",\"mepItemName\":\"黑板报\",\"score\":\"-1\",\"mepItemExplain\":\"扣分针对所有班级\",\"scoreTime\":\"2016-09-13 00:00:00\",\"teacherName\":\"王主任\"},{\"mepScoreId\":\"2\",\"mepItemName\":\"打扫卫生只加分不扣分\",\"score\":\"+2\",\"mepItemExplain\":\"只加分不扣分\",\"scoreTime\":\"2016-09-13 00:00:00\",\"teacherName\":\"王主任\"},{\"mepScoreId\":\"3\",\"mepItemName\":\"通报批评\",\"score\":\"-1\",\"mepItemExplain\":\"只减分不加分\",\"scoreTime\":\"2016-09-13 00:00:00\",\"teacherName\":\"王主任\"}]}";
						JSONObject jobj=JSON.parseObject(str);    
						result.setData(jobj);	
					} else{
						result.setInfo("学生id --必填项");
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
					if (studentId == null) {
						throw new IllegalArgumentException("该学生id不存在");
					}
					//获得cid
					ClassStudentDBO classStudentDBO = new ClassStudentDBO();
					classStudentDBO.setStudentId(studentId);
					List<ClassStudentDBO> classStudentDBOs = (List<ClassStudentDBO>) ClassStudentService_.doSelectData(classStudentDBO);
					if (classStudentDBOs == null || classStudentDBOs.size() <= 0) {
						throw new IllegalArgumentException("班级不已经存在");
					}
					Long cid = classStudentDBOs.get(0).getCid();
			/*		//获得学期termId
					ClassDBO classDBO = new ClassDBO();
					classDBO.setPuk(ONE);
					classDBO.setCid(cid);
					classDBO = (ClassDBO) ClassService_.doRead(classDBO);
					Long termId = classDBO.getTermId();*/
					// 获取德育个人评分排名信息
					MepScorePVO mepScorePVO = new MepScorePVO();
					mepScorePVO.setTermId(termId);
					mepScorePVO.setCid(cid);
					mepScorePVO.setStudentId(studentId);
					mepScorePVO = MepScoreService_.doReadMepScore(mepScorePVO);
					JSONObject data = new JSONObject();
					data.put("studentId",studentId );
					data.put("studentName",mepScorePVO.getStudentName());
					data.put("number", mepScorePVO.getNumber());
					data.put("totalScore",mepScorePVO.getTotalScore() );
					data.put("ranking",mepScorePVO.getRanking() );
					// 获取德育个人评分列表信息
					MepScorePVO mepScorePVO1 = new MepScorePVO();
					mepScorePVO1.setStudentId(studentId);
					pageModel.setPageCurrent(page);
					pageModel.setPageLimit(size);
					pageModel.setFormParamBean(mepScorePVO1);
					MepScoreService_.doSelectPageMepScore(pageModel);
					JSONArray jsonArray = new JSONArray();
					List<MepScorePVO> mepScoreList = (List<MepScorePVO>) pageModel.getPageListData();
					for (MepScorePVO mepScorePVO2 : mepScoreList) {
						JSONObject data2 = new JSONObject();
						data2.put("mepScoreId", mepScorePVO2.getMepScoreId());
						data2.put("mepItemName", mepScorePVO2.getMepItemName());
						data2.put("score", mepScorePVO2.getScroe());
						data2.put("mepItemExplain", mepScorePVO2.getMepItemExplain());
						data2.put("teacherName", mepScorePVO2.getTeacherName());
						data2.put("scoreTime", mepScorePVO2.getScoreTime());
						jsonArray.add(data2);	
					}
					data.put("page", page);
					data.put("size", size);
					data.put("count", pageModel.getResultCount());
					data.put("mepScoreList", jsonArray);
					result.setData(data); 
					
				}
				
			} catch (Exception e) {
				result.setInfo("查询失败，" + e.getMessage());
				result.setStatus(1);
			}

			return result;
		}		
	
		
		/**
		 * 12.2.5德育个人评分详情列表搜索接口/qxy/moraleducation/personal/score/list/search?termId=[termId]&studentId=[studentId]&mepItemId=[mepItemId]&startTime=[startTime]&endTime=[endTime]&page=[page]&size=[size]&token=[token]
		 * 需要使用Token验证查询者身份
		 * @param test
		 * @param page
		 * @param size
		 * @return
		 */
				 
		@RequestMapping(value = "/moraleducation/personal/score/list/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalScoreListSearchGET(Boolean test,Long studentId,Long termId,Long mepItemId,String startTime,String endTime,String token,Integer page, Integer size) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test != null && test == true) {
						String str = "{\"studentId\":\"1\",\"studentName\":\"段誉\",\"number\":\"0291029\",\"totalScore\":\"23\",\"ranking\":\"12\",\"page\":\"1\",\"size\":\"12\",\"count\":\"22\",\"mepScoreList\":[{\"mepScoreId\":\"1\",\"mepItemName\":\"黑板报\",\"score\":\"-1\",\"mepItemExplain\":\"扣分针对所有班级\",\"createTime\":\"2016-09-13 00:00:00\",\"teacherName\":\"王主任\"},{\"mepScoreId\":\"2\",\"mepItemName\":\"打扫卫生只加分不扣分\",\"score\":\"+2\",\"mepItemExplain\":\"只加分不扣分\",\"scoreTime\":\"2016-09-13 00:00:00\",\"teacherName\":\"王主任\"},{\"mepScoreId\":\"3\",\"mepItemName\":\"通报批评\",\"score\":\"-1\",\"mepItemExplain\":\"只减分不加分\",\"scoreTime\":\"2016-09-13 00:00:00\",\"teacherName\":\"王主任\"}]}";
						JSONObject jobj=JSON.parseObject(str);    
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
					if (studentId == null) {
						throw new IllegalArgumentException("该学生id不存在");
					}
					//获得cid
					ClassStudentDBO classStudentDBO = new ClassStudentDBO();
					classStudentDBO.setStudentId(studentId);
					List<ClassStudentDBO> classStudentDBOs = (List<ClassStudentDBO>) ClassStudentService_.doSelectData(classStudentDBO);
					if (classStudentDBOs == null || classStudentDBOs.size() <= 0) {
						throw new IllegalArgumentException("班级不已经存在");
					}
					Long cid = classStudentDBOs.get(0).getCid();
					//获得学期termId
					/*ClassDBO classDBO = new ClassDBO();
					classDBO.setPuk(ONE);
					classDBO.setCid(cid);
					classDBO = (ClassDBO) ClassService_.doRead(classDBO);
					Long termId = classDBO.getTermId();*/
					// 获取德育个人评分排名信息
					MepScorePVO mepScorePVO = new MepScorePVO();
					mepScorePVO.setTermId(termId);
					mepScorePVO.setCid(cid);
					mepScorePVO.setStudentId(studentId);
					mepScorePVO = MepScoreService_.doReadMepScore(mepScorePVO);
					JSONObject data = new JSONObject();
					data.put("studentId",studentId );
					data.put("studentName",mepScorePVO.getStudentName());
					data.put("number", mepScorePVO.getNumber());
					data.put("totalScore",mepScorePVO.getTotalScore() );
					data.put("ranking",mepScorePVO.getRanking() );
					// 获取德育个人评分列表信息
					MepScorePVO mepScorePVO1 = new MepScorePVO();
					mepScorePVO1.setStudentId(studentId);
					mepScorePVO1.setMepItemId(mepItemId);
					mepScorePVO1.setStartTime(startTime);
					mepScorePVO1.setEndTime(endTime);
					pageModel.setPageCurrent(page);
					pageModel.setPageLimit(size);
					pageModel.setFormParamBean(mepScorePVO1);
					MepScoreService_.doSelectPageMepScoreList(pageModel);
					JSONArray jsonArray = new JSONArray();
					List<MepScorePVO> mepScoreList = (List<MepScorePVO>) pageModel.getPageListData();
					for (MepScorePVO mepScorePVO2 : mepScoreList) {
						JSONObject data2 = new JSONObject();
						data2.put("mepScoreId", mepScorePVO2.getMepScoreId());
						data2.put("mepItemName", mepScorePVO2.getMepItemName());
						data2.put("score", mepScorePVO2.getScroe());
						data2.put("mepItemExplain", mepScorePVO2.getMepItemExplain());
						data2.put("teacherName", mepScorePVO2.getTeacherName());
						data2.put("scoreTime", mepScorePVO2.getScoreTime());
						jsonArray.add(data2);
					}
					data.put("page", page);
					data.put("size", size);
					data.put("count", pageModel.getResultCount());
					data.put("mepScoreList", jsonArray);
					result.setData(data);									
				}
				
			} catch (Exception e) {
				result.setInfo("查询失败，" + e.getMessage());
				result.setStatus(1);
			}
			return result;
		}
		
		/**
		 * 德育个人评分新增接口/qxy/moraleducation/personal/score?token=[token]
		 * @param pvo
		 * @return
		 */
		@RequestMapping(value = "/moraleducation/personal/score", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalScorePOST(Boolean test,String token,@RequestBody MepScoreDBO dbo) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test != null && test == true) {
//					if (josn.get("studentId") != null && josn.get("mepItemId") != null && josn.get("scoreOrder") != null) {
////						String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
////						JSONObject jobj=JSON.parseObject(str);    
////						result.setData(jobj);	
//					} else{
//						result.setInfo("字生id --必填,德育个人项目id --必填,德育个人评分序号（合理范围为1-6）--必填");
//					}
				} else {
					// token校验
					if (doCheckToken(token) == false) {
						return tokenFail();
					}
					//获得tid用户信息
					UserBean userBean= UserCacheHelper.checkUser(token);
					UTeacherDBO uTeacherDBO = new UTeacherDBO();
					uTeacherDBO.setUid(userBean.getUserId());
					uTeacherDBO = (UTeacherDBO) UTeacherService_.doReadByUid(uTeacherDBO);
					dbo.setTid(uTeacherDBO.getTid());
					
					//获得cid
					ClassStudentDBO classStudentDBO = new ClassStudentDBO();
					classStudentDBO.setStudentId(dbo.getStudentId());
					List<ClassStudentDBO> classStudentDBOs =  (List<ClassStudentDBO>) ClassStudentService_.doSelectData(classStudentDBO);
					dbo.setCid(classStudentDBOs.get(0).getCid());
					//termId学期
					ClassDBO classDBO =  new ClassDBO();
					classDBO.setPuk(ONE);
					classDBO.setCid(classStudentDBOs.get(0).getCid());
					classDBO = (ClassDBO) ClassService_.doRead(classDBO);
					Long termId = classDBO.getTermId();
					dbo.setTermId(termId);
					
					dbo.setIsUse(1);
					if (dbo.getStudentId() == null) {
						throw new IllegalArgumentException("studentId该德育班级cid不存在");
					}
					if (dbo.getMepItemId() == null) {
						throw new IllegalArgumentException("该德育个人项目id不存在");
					}
					if (dbo.getScroeOrder() == null) {
						throw new IllegalArgumentException("德育个人评分序号（合理范围为1-6）");
					}
					MepItemDBO mepItemDBO =  new MepItemDBO();
					mepItemDBO.setMepItemId(dbo.getMepItemId());
					mepItemDBO = (MepItemDBO) MepItemService_.doRead(mepItemDBO);
					//初始分+间隔分*评分序号
					String scroeOrder = dbo.getScroeOrder();//德育班级评分序号
				    String initialScore = mepItemDBO.getInitialScore();//初始分
				    String intervalScore = mepItemDBO.getIntervalScore();//间隔分
				    int scroeOrderInt =  Integer.parseInt(scroeOrder);
				    int initialScoreInt = Integer.parseInt(initialScore);
				    int intervalScoreInt = Integer.parseInt(intervalScore);
				    int scroeInt = intervalScoreInt*scroeOrderInt+initialScoreInt;
				    String scroe = Integer.toString(scroeInt);
				    dbo.setScroe(scroe);
					MepScoreService_.doInsert(dbo);					
				}
			} catch (Exception e) {
				result.setInfo("访问失败" +e.getMessage());
				result.setStatus(1);
			}
			return result;
		}
		
		/**
		 * 德育个人评分删除接口/qxy/moraleducation/personal/score?token=[token]
		 */
		@RequestMapping(value = "/moraleducation/personal/score", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalScoreDELETE(Boolean test,String token,@RequestBody MepScoreDBO dbo) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test != null && test == true) {
//					if (josn.get("mepScoreId") != null) {
////						String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
////						JSONObject jobj=JSON.parseObject(str);    
////						result.setData(jobj);	
//					} else{
//						result.setInfo("德育个人评分id --必填");
//					}
				} else {
					// token校验
					if (doCheckToken(token) == false) {
						return tokenFail();
					}
					MepScoreService_.doDelete(dbo);

				}
			} catch (Exception e) {
				result.setInfo("删除失败，" + e.getMessage());
				result.setStatus(1);
			}
			return result;
		}				
	
		/**
		 * 12.2.8 德育个人评分详情查询接口/qxy/moraleducation/personal/score/info?mepScoreId=[mepScoreId]&token=[token]
		 * 需要使用Token验证查询者身份
		 */
				 
		@RequestMapping(value = "/moraleducation/personal/score/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalScoreInfoGET(Boolean test,String token,Long mepScoreId) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test != null && test == true) {
					if (mepScoreId != null) {
						String str = "{\"mepItemName\":\"黑板报\",\"score\":\"-1\",\"mepItemExplain\":\"凡是一次没有做的扣一分，扣分苍围针对所有班级\",\"createTime\":\"2016-09-13 00:00:00\",\"teacherName\":\"王主任\",\"scoreOrder\":\"1\",\"scoreContent\":\"1\",\"scorePic\":[\"http://www.100xyun.com/upload/123.jpg\",\"http://www.100xyun.com/upload/123.jpg\"]}";
						JSONObject jobj=JSON.parseObject(str);    
						result.setData(jobj);	
					} else{
						result.setInfo(" 个人评分id --必填");
					}
				} else {
					if (doCheckToken(token) == false) {
						return tokenFail();
					}
					if (mepScoreId == null) {
						throw new IllegalArgumentException("个人评分id不存在");
					}
					MepScoreDBO mepScoreDBO = new MepScoreDBO();
					mepScoreDBO.setMepScoreId(mepScoreId);
					mepScoreDBO = (MepScoreDBO) MepScoreService_.doRead(mepScoreDBO);
					Long termId = mepScoreDBO.getTermId();
					Long cid = mepScoreDBO.getCid();
					Long studentId = mepScoreDBO.getStudentId();
					// 获取德育个人评分排名信息
					MepScorePVO mepScorePVO = new MepScorePVO();
					mepScorePVO.setTermId(termId);
					mepScorePVO.setCid(cid);
					mepScorePVO.setStudentId(studentId);
					mepScorePVO = MepScoreService_.doReadMepScore(mepScorePVO);
					JSONObject data = new JSONObject();
					data.put("totalScore",mepScorePVO.getTotalScore() );
					//
					MepScoreDBO mepScoreDBO2 = new MepScoreDBO();
					mepScoreDBO2.setMepScoreId(mepScoreId);
					MepScorePVO mepScorePVO2 = (MepScorePVO) MepScoreService_.doReadMepScoreBymepScoreId(mepScoreDBO2);
					data.put("mepItemName", mepScorePVO2.getMepItemName());
					data.put("score", mepScorePVO2.getScroe());
					data.put("mepItemExplain", mepScorePVO2.getMepItemExplain());
					data.put("scoreTime", mepScorePVO2.getScoreTime());
					data.put("teacherName", mepScorePVO2.getTeacherName());
					data.put("scoreOrder", mepScorePVO2.getScroeOrder());
					data.put("scoreContent", mepScorePVO2.getScoreContent());
					data.put("scorePic", mepScorePVO2.getScorePic());
					result.setData(data);
				}
			} catch (Exception e) {
				result.setInfo("查询失败，" + e.getMessage());
				result.setStatus(1);
			}

			return result;
		}		
		
		
		/**
		 *12.2.9  德育个人项目列表查询接口/qxy/moraleducation/personal?sid=[sid]&token=[token]
		 * 需要使用Token验证查询者身份
		 */
				 
		@RequestMapping(value = "/moraleducation/personal", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalGET(Boolean test,Long sid,String token) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test != null && test == true) {
					
						String str = "{\"count\":\"12\",\"mepItemList\":[{\"mepItemId\":\"23\",\"mepItemName\":\"黑板报\",\"mepItemExplain\":\"扣分苍围针对所有班级\",\"ruleNum\":\"1\",\"initialScore\":\"0\",\"intervalScore\":\"2\",\"isUse\":\"1\"},{\"mepItemId\":\"24\",\"mepItemName\":\"大扫除\",\"mepItemExplain\":\"一次2分\",\"ruleNum\":\"3\",\"initialScore\":\"3\",\"intervalScore\":\"3\",\"isUse\":\"1\"},{\"mepItemId\":\"25\",\"mepItemName\":\"通报批评\",\"mepItemExplain\":\"扣分苍围针对所有班级\",\"ruleNum\":\"5\",\"initialScore\":\"5\",\"intervalScore\":\"5\",\"isUse\":\"1\"}]}";
						JSONObject jobj=JSON.parseObject(str);    
						result.setData(jobj);	
				
				} else {
					// 得到德育个人项目列表
					MepItemDBO mepItemDBO = new MepItemDBO();
					mepItemDBO.setSid(sid);					
					List<MepItemDBO> mepItemList = (List<MepItemDBO>) MepItemService_.doSelectData(mepItemDBO);
					JSONArray jsonArray = new JSONArray(); 
		            for (MepItemDBO mepItemDBO2 : mepItemList) {
		            	JSONObject data2 = new JSONObject();
		            	data2.put("mepItemId",mepItemDBO2.getMepItemId() );
		            	data2.put("mepItemName",mepItemDBO2.getMepItemName() );
		            	data2.put("mepItemExplain",mepItemDBO2.getMepItemExplain() );
		            	data2.put("ruleNum",mepItemDBO2.getRuleNum() );
		            	data2.put("initialScore",mepItemDBO2.getInitialScore() );
		            	data2.put("intervalScore",mepItemDBO2.getIntervalScore() );
		            	data2.put("isUse",mepItemDBO2.getIsUse() );
		            	jsonArray.add(data2);
					}
					JSONObject data = new JSONObject();
					data.put("count", mepItemList.size());
					data.put("mepItemList", jsonArray);
					result.setData(data);
					
				}
				
			} catch (Exception e) {
				result.setInfo("查询失败，" + e.getMessage());
				result.setStatus(1);
			}

			return result;
		}	
	
		/**
		 *  德育个人项目新增接口/qxy/moraleducation/personal?token=[token]
		 * @param pvo
		 * @return
		 */
		@RequestMapping(value = "/moraleducation/personal", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalPOST(Boolean test,String token,@RequestBody MepItemDBO dbo) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test != null && test == true) {
//					if (josn.get("sid") != null && josn.get("mepItemName") != null && josn.get("ruleNum") != null) {
////						String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
////						JSONObject jobj=JSON.parseObject(str);    
////						result.setData(jobj);	
//					} else{
//						result.setInfo("学校id --必填项,德育个人项目名称 --必填项,规则分数量必须大于0  --必填项");
//					}
				} else {
					// token校验
					if (doCheckToken(token) == false) {
						return tokenFail();
					}
					dbo.setIsUse(1);
					if (dbo.getSid() == null) {
						throw new IllegalArgumentException("该学校id不存在");
					}
					if (dbo.getMepItemName() == null) {
						throw new IllegalArgumentException("该德育个人项目名称不存在");
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
					MepItemService_.doInsert(dbo);
				}
			} catch (Exception e) {
				result.setInfo("访问失败"+ e.getMessage());
				result.setStatus(1);
			}
			return result;
		}
		
		/**
		 * 德育个人项目修改接口 /qxy/moraleducation/personal?token=[token]
		 */
		@RequestMapping(value = "/moraleducation/personal", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalPUT(Boolean test,String token,@RequestBody  MepItemDBO dbo) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test != null && test == true) {
//					if (josn.get("mepItemId") != null ) {
////						String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
////						JSONObject jobj=JSON.parseObject(str);    
////						result.setData(jobj);	
//					} else{
//						result.setInfo("德育个人项目id --必填项");
//					}
				} else {
					//token校验
					if (doCheckToken(token) == false) {
						return tokenFail();
					}
					if (dbo.getMepItemId() == null) {
						throw new IllegalArgumentException("该德育个人项目id不存在");
					}
					
					MepItemService_.doUpdate(dbo);


				}
		
			} catch (Exception e) {
				result.setInfo("修改失败，" + e.getMessage());
				result.setStatus(1);
			}

			return result;
		}

		/**
		 * 德育个人项目停用/启用接口  /qxy/moraleducation/personal/set?token=[token]
		 */
		@RequestMapping(value = "/moraleducation/personal/set", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalSetPUT(Boolean test,String token,@RequestBody  MepItemDBO dbo) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test != null && test == true) {
//					if (josn.get("mepItemId") != null && josn.get("isUse") != null) {
////						String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
////						JSONObject jobj=JSON.parseObject(str);    
////						result.setData(jobj);	
//					} else{
//						result.setInfo("德育个人项目id --必填项,0-启用；1-停用 --必填项");
//					}
				} else {
					if (doCheckToken(token) == false) {
						return tokenFail();
					}
					if (dbo.getMepItemId() == null) {
						throw new IllegalArgumentException("该德育个人项目id不存在");
					}
					if (dbo.getIsUse() == null) {
						throw new IllegalArgumentException("该启用和停用不存在");
					}
					MepItemService_.doUpdate(dbo);

				}
		
			} catch (Exception e) {
				result.setInfo("修改失败，" + e.getMessage());
				result.setStatus(1);
			}

			return result;
		}
		
		/**
		 * 德育个人项目删除接口  /qxy/moraleducation/personal?token=[token]
		 */
		@RequestMapping(value = "/moraleducation/personal", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalDELETE(Boolean test,String token,@RequestBody  MepItemDBO dbo) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test != null && test == true) {
//					if (josn.get("mepItemId") != null) {
////						String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
////						JSONObject jobj=JSON.parseObject(str);    
////						result.setData(jobj);	
//					} else{
//						result.setInfo("评分id --必填项");
//					}
				} else {
					// token校验
					if (doCheckToken(token) == false) {
						return tokenFail();
					}
					if (dbo.getMepItemId() == null) {
						throw new IllegalArgumentException("该德育班级项目id不存在");
					}
					MepItemService_.doDelete(dbo);
					
				}
			} catch (Exception e) {
				result.setInfo("删除失败，" + e.getMessage());
				result.setStatus(1);
			}
			return result;
		}	
		
		/**
		 * 12.2.14 德育个人项目详情查询接口/qxy/moraleducation/personal/info?mepItemId=[mepItemId]&token=[token]
		 * 德育个人项目详情查询接口提供对德育个人项目详情查询的功能。
		 * 需要使用Token验证查询者身份
		 */
				 
		@RequestMapping(value = "/moraleducation/personal/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
		@ResponseBody
		public RESTResultBean mecPersonalInfoGET(Boolean test,String token,Long mepItemId) {
			RESTResultBean result = new RESTResultBean();
			try {
				if (test != null && test == true) {
					if (mepItemId != null) {
						String str = "{\"mepItemId\":\"23\",\"mepItemName\":\"黑板报\",\"mepItemExplain\":\"扣分苍围针对所有班级\",\"ruleNum\":\"1\",\"initialScore\":\"0\",\"intervalScore\":\"2\",\"isUse\":\"1\"}";
						JSONObject jobj=JSON.parseObject(str);    
						result.setData(jobj);	
					} else{
						result.setInfo("德育个人项目id   ---必填项");
					}
				} else {
					if (doCheckToken(token) == false) {
						return tokenFail();
					}
					// 得到德育班级项目列表
					MepItemDBO mepItemDBO = new MepItemDBO();
					if (mepItemId == null) {
						result.setInfo("德育个人项目id不存在");
					} else {
						mepItemDBO.setMepItemId(mepItemId);
					}
					mepItemDBO = (MepItemDBO) MepItemService_.doRead(mepItemDBO);
//					JSONObject data = new JSONObject();;
//					data.put("mecItemList", mecItemList);
					result.setData(mepItemDBO);				
				
					
				}
				
			} catch (Exception e) {
				result.setInfo("查询失败，" + e.getMessage());
				result.setStatus(1);
			}

			return result;
		}
	
	
}
