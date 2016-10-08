package com.mcookies.qxy.biz.scoremanage;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.ClassStudent.ClassStudentDBO;
import com.mcookies.qxy.common.ClassStudent.ClassStudentService;
import com.mcookies.qxy.common.MecScore.MecScorePVO;
import com.mcookies.qxy.common.MecScore.MecScoreService;
import com.mcookies.qxy.common.Results.ResultsDBO;
import com.mcookies.qxy.common.Results.ResultsPVO;
import com.mcookies.qxy.common.Results.ResultsService;
import com.mcookies.qxy.common.ResultsTag.ResultsTagDBO;
import com.mcookies.qxy.common.ResultsTag.ResultsTagService;
import com.mcookies.qxy.common.UStudent.UStudentDBO;
import com.mcookies.qxy.common.UStudent.UStudentPVO;
import com.mcookies.qxy.common.UStudent.UStudentService;
import com.mcookies.qxy.utils.DateUtils;
import com.mcookies.qxy.utils.ExcelUtil;

import jodd.util.StringUtil;

/**
 * 成绩表管理
 * @author james
 *
 */
@Controller
public class ResultsController extends MyControllerSupport {
	@Resource
	protected ResultsService ResultsService_;
	@Resource
	protected ClassStudentService ClassStudentService_  ;
	@Resource
	protected UStudentService UStudentService_  ;
	@Resource
	protected ResultsTagService ResultsTagService_  ;
	
	/**
	 * 13.2.1 通过考试标签查询班级列表查询接口/qxy/results/classlist?resultsTagId=[resultsTagId]&tid=[tid]&page=[page]&size=[size]&token=[token]
     * 需要使用Token验证查询者身份
     * 待处理
	 */
	
	@RequestMapping(value = "/results/classlist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsClassListGET(Boolean test,Long resultsTagId,Long tid,String token,Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {					
				if (resultsTagId != null ) {
					String str = "{\"resultsTagName\":\"期中考试\",\"resultsTagId\":\"2\",\"termId\":\"12\",\"page\":\"2\",\"size\":\"12\",\"count\":\"23\",\"gradelist\":[{\"gradeId\":\"1\",\"gradeName\":\"一年级\"，\"classlist\":[{\"cid\":\"1\",\"className\":\"三班\"},{\"cid\":\"2\",\"className\":\"四班\"}]},{\"gradeId\":\"2\",\"gradeName\":\"二年级\"，\"classlist\":[{\"cid\":\"3\",\"className\":\"三班\"},{\"cid\":\"4\",\"className\":\"四班\"}]}]}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
				} else{
					result.setInfo(" 成绩标签id--必填项");
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
				if (resultsTagId == null) {
					throw new IllegalArgumentException("该成绩标签id不存在");
				}
				//获得termId
				ResultsTagDBO resultsTagDBO =  new ResultsTagDBO();
				resultsTagDBO.setResultsTagId(resultsTagId);
				resultsTagDBO = (ResultsTagDBO) ResultsTagService_.doRead(resultsTagDBO);
				Long termId = resultsTagDBO.getTermId();
				// 获取年级信息
				ResultsPVO resultsPVO = new ResultsPVO();
				resultsPVO.setResultsTagId(resultsTagId);
				resultsPVO.setTermId(termId);
				resultsPVO.setTid(tid);
				pageModel.setPageCurrent(page);
				pageModel.setPageLimit(size);
				pageModel.setFormParamBean(resultsPVO);
				ResultsService_.doSelectPageGrade(pageModel);
				List<ResultsPVO> gradelist = (List<ResultsPVO>) pageModel.getPageListData();
				JSONArray jsonArray = new JSONArray();
				for (ResultsPVO resultsPVO2 : gradelist) {
					JSONObject data2 = new JSONObject();
					data2.put("gradeId",resultsPVO2.getGradeId() );
					data2.put("gradeName",resultsPVO2.getGradeName());
					ResultsPVO resultsPVO3 = new ResultsPVO();
					resultsPVO3.setResultsTagId(resultsTagId);
					resultsPVO3.setTermId(termId);
					resultsPVO3.setTid(tid);
					resultsPVO3.setGradeId(resultsPVO2.getGradeId());
					List<ResultsPVO> classlist = (List<ResultsPVO>)ResultsService_.doSelectClassList(resultsPVO3);
					data2.put("classlist",classlist );
					jsonArray.add(data2);
				}
				data.put("page", page);
				data.put("size", size);
				data.put("count", pageModel.getResultCount());
				data.put("gradelist", jsonArray);
				data.put("termId", termId);
				data.put("resultsTagName", resultsTagDBO.getResultsTagName());
				data.put("resultsTagId", resultsTagDBO.getResultsTagId());
				result.setData(data);				
			}	
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}
	/**
	 * 13.2.1-1 通过考试标签查询班级列表（班级列表）查询接口/qxy/results/classlist/info?resultsTagId=[resultsTagId]&tid=[tid]&page=[page]&size=[size]&token=[token]
     * 需要使用Token验证查询者身份
     * 待处理
	 */
	
	@RequestMapping(value = "/results/classlist/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsClassListInfoGET(Boolean test,Long resultsTagId,Long tid,String token,Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {					
				if (resultsTagId != null ) {
					String str = "{\"resultsTagName\":\"期中考试\",\"resultsTagId\":\"2\",\"termId\":\"12\",\"page\":\"2\",\"size\":\"12\",\"count\":\"23\",\"classlist\":[{\"cid\":\"1\",\"className\":\"三班\"\"gradeId\":\"1\",\"gradeName\":\"一年级\"},{\"cid\":\"1\",\"className\":\"三班\"\"gradeId\":\"1\",\"gradeName\":\"一年级\"}]}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
				} else{
					result.setInfo(" 成绩标签id--必填项");
				}	
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				if (resultsTagId == null) {
					throw new IllegalArgumentException("该成绩标签id不存在");
				}
				if (size == null || size == 0) {
					size = 12;
				}
				if (page == null || page == 0) {
					page = 1;
				}
				JSONObject data = new JSONObject();
				//获得termId
				ResultsTagDBO resultsTagDBO =  new ResultsTagDBO();
				resultsTagDBO.setResultsTagId(resultsTagId);
				resultsTagDBO = (ResultsTagDBO) ResultsTagService_.doRead(resultsTagDBO);
				Long termId = resultsTagDBO.getTermId();
				// 获取年级班级信息
				
				ResultsPVO resultsPVO = new ResultsPVO();
				resultsPVO.setResultsTagId(resultsTagId);
				resultsPVO.setTermId(termId);
				resultsPVO.setTid(tid);
				pageModel.setPageCurrent(page);
				pageModel.setPageLimit(size);
				pageModel.setFormParamBean(resultsPVO);
				ResultsService_.doSelectPageGradeClassList(pageModel);
				List<ResultsPVO> classlist = (List<ResultsPVO>) pageModel.getPageListData();	
				data.put("page", page);
				data.put("size", size);
				data.put("count", pageModel.getResultCount());
				data.put("classlist", classlist);
				data.put("termId", termId);
				data.put("resultsTagName", resultsTagDBO.getResultsTagName());
				data.put("resultsTagId", resultsTagDBO.getResultsTagId());
				result.setData(data);			
			}	
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 13.2.2 班级列表搜索接口/qxy/results/classlist/search?&cid=[cid]&token=[token]
     * 需要使用Token验证查询者身份
     * 待处理
	 */
	
	@RequestMapping(value = "/results/classlist/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsClassListSearchGET(Boolean test,Long cid,String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {					
				if (cid != null ) {
					String str = "{\"resultsTagName\":\"期中考试\",\"resultsTagId\":\"2\",\"termId\":\"12\",\"gradeId\":\"1\",\"gradeName\":\"一年级\"，\"cid\":\"1\",\"className\":\"三班\"}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
				} else{
					result.setInfo("班级id--可选项");
				}	
			} else {			
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				JSONObject data = new JSONObject();
				// 获取学生成绩信息
				ResultsDBO resultsDBO = new ResultsDBO();
				if (cid == null) {
					throw new IllegalArgumentException("该cid不存在");
				}
				resultsDBO.setCid(cid);
				ResultsPVO resultsPVO = ResultsService_.doReadGradeClass(resultsDBO);
				result.setData(resultsPVO);
				
			}	
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}
	
	
	/**
	 * 13.2.3 班级学生成绩列表查询接口/qxy/results?resultsTagId=[resultsTagId]&cid=[cid]&page=[page]&size=[size]&token=[token]
     * 需要使用Token验证查询者身份
	 */
	
	@RequestMapping(value = "/results", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsGET(Boolean test,Long resultsTagId,Long cid,String token,Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {					
				if (cid != null && resultsTagId != null ) {
					String str = "{\"resultsTagName\":\"期中考试\",\"resultsTagId\":\"2\",\"cid\":\"12\",\"className\":\"高一三班\",\"page\":\"2\",\"size\":\"12\",\"count\":\"23\",\"studentlist\":[{\"studentId\":\"1\",\"studentName\":\"张三三\",\"number\":\"01021200\",\"totalPoints\":\"603\",\"ranking\":\"2\"},{\"studentId\":\"2\",\"studentName\":\"张四三\",\"number\":\"01021201\",\"totalPoints\":\"601\",\"ranking\":\"3\"}]}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
				} else{
					result.setInfo("班级id--必填项, 成绩标签id--必填项");
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
				// 获取学生成绩信息
				ResultsDBO resultsDBO = new ResultsDBO();
				if (resultsTagId == null) {
					throw new IllegalArgumentException("该成绩标签id不存在");
				}
				if (cid == null) {
					throw new IllegalArgumentException("该班级id不存在");
				}
				resultsDBO.setResultsTagId(resultsTagId);
				resultsDBO.setCid(cid);
				ResultsPVO resultsPVO = ResultsService_.doReadResult(resultsDBO);
				data.put("resultsTagName", resultsPVO.getResultsTagName());
				data.put("resultsTagId", resultsPVO.getResultsTagId());
				data.put("cid",resultsPVO.getCid() );
				data.put("className", resultsPVO.getClassName());
				// 获取学生总成绩列表 
				ResultsDBO resultsDBO2 = new ResultsDBO();
				resultsDBO2.setResultsTagId(resultsTagId);
				resultsDBO2.setCid(cid);
				pageModel.setPageCurrent(page);
				pageModel.setPageLimit(size);
				pageModel.setFormParamBean(resultsDBO2);
				ResultsService_.doSelectPageResultDESC(pageModel);
				List<ResultsPVO> studentlist = (List<ResultsPVO>) pageModel.getPageListData();			
				data.put("page", page);
				data.put("size", size);
				data.put("count", pageModel.getResultCount());
				data.put("studentlist", studentlist);
				result.setData(data);
			}	
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 13.2.4 班级学生成绩搜索接口 /qxy/results/search?resultsTagId=[resultsTagId]&studentId=[studentId]&number=[number]&token=[token]
	 * 需要使用Token验证查询者身份
	 * 根据班级cid进行搜索，只用与班级搜索
	 * @param test
	 * @return
	 */
			 
	@RequestMapping(value = "/results/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsSearchGET(Boolean test,Long resultsTagId,Long studentId,Long number,String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {
				if (resultsTagId != null && (studentId != null ||number != null) ) {
					String str = "{\"resultsTagName\":\"期中考试\",\"resultsTagId\":\"2\",\"cid\":\"12\",\"className\":\"高一三班\",\"studentlist\":{\"studentId\":\"1\",\"studentName\":\"张三三\",\"number\":\"01021200\",\"totalPoints\":\"603\",\"ranking\":\"2\"}}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
				} else{
					result.setInfo("成绩标签id--必填项, 学生id--选填（id学号二选一），学生学号--选填（id学号二选一）");
				}
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				JSONObject data = new JSONObject();
				// 获取学生成绩信息
				ResultsPVO resultsPVO  = new ResultsPVO();
				if (resultsTagId == null) {
					throw new IllegalArgumentException("该成绩标签id不存在");
				}
				if (studentId == null && number == null ) {
					throw new IllegalArgumentException("该学生id且number不存在");
				}
				resultsPVO.setResultsTagId(resultsTagId);
				resultsPVO.setStudentId(studentId);
				resultsPVO.setNumber(number);
				resultsPVO = ResultsService_.doReadResultByStudentId(resultsPVO);
				data.put("resultsTagName", resultsPVO.getResultsTagName());
				data.put("resultsTagId", resultsPVO.getResultsTagId());
				data.put("cid",resultsPVO.getCid() );
				data.put("className", resultsPVO.getClassName());
				// 获取学生总成绩列表 
				ResultsPVO resultsPVO2  = new ResultsPVO();
				resultsPVO2.setResultsTagId(resultsTagId);
				resultsPVO2.setStudentId(studentId);
				resultsPVO2.setNumber(number);
				List<ResultsPVO> studentlist = ResultsService_.doSelectResult(resultsPVO2);
				data.put("studentlist", studentlist);
				result.setData(data);
			}			
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}		 

	/**
	 * 13.2.5 班级学生成绩删除接口  /qxy/results/student?token=[token]
	 */
	@RequestMapping(value = "/results/student", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsStudentDELETE(Boolean test,String token,@RequestBody ResultsDBO dbo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {
//				if (josn.get("resultsTagId") != null&& josn.get("studentId") != null) {
////					String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
////					JSONObject jobj=JSON.parseObject(str);    
////					result.setData(jobj);	
//				} else{
//					result.setInfo("成绩标签id--必填项, 学生id--必填项");
//				}
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				if (dbo.getResultsTagId() == null) {
					throw new IllegalArgumentException("该成绩标签id不存在");
				}
				if (dbo.getStudentId() == null) {
					throw new IllegalArgumentException("该学生id不存在");
				}
				List<ResultsDBO> resultsDBOs = (List<ResultsDBO>) ResultsService_.doSelectData(dbo);			
				for (ResultsDBO resultsDBO : resultsDBOs) {				
					ResultsService_.doDelete(resultsDBO);
				}
			}
		} catch (Exception e) {
			result.setInfo("删除失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}	
	
	/**
	 * 13.2.6 学生科目成绩列表查询接口 /qxy/results/student/course?resultsTagId=[resultsTagId]&studentId=[studentId]&token=[token]
     * 需要使用Token验证查询者身份
	 */
	
	@RequestMapping(value = "/results/student/course", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsStudentCourseGET(Boolean test,Long resultsTagId,String token,Long studentId) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {					
				if (resultsTagId != null&& studentId != null ) {
					String str = "{\"resultsTagName\":\"期中考试\",\"resultsTagId\":\"2\",\"cid\":\"12\",\"className\":\"高一三班\",\"studentId\":\"1\",\"studentName\":\"张三三\",\"number\":\"01021200\",\"totalPoints\":\"603\",\"courseScoreList\":[{\"courseId\":\"1\",\"courseName\":\"语文\",\"courseScore\":\"89\",\"courseRanking\":\"5\",\"updateTime\":\"2016-8-9 00:00:00\"},{\"courseId\":\"2\",\"courseName\":\"数学\",\"courseScore\":\"89\",\"courseRanking\":\"5\",\"updateTime\":\"2016-8-9 00:00:00\"}]}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
				} else{
					result.setInfo("成绩标签id--必填项, 学生id--必填项");
				}	
			} else {
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				if (resultsTagId == null) {
					throw new IllegalArgumentException("该成绩标签id不存在");
				}
				if (studentId == null) {
					throw new IllegalArgumentException("该学生id不存在");
				}
				ResultsDBO resultsDBO = new ResultsDBO();
				resultsDBO.setResultsId(resultsTagId);
				resultsDBO.setStudentId(studentId);
				ResultsPVO resultsPVO = ResultsService_.doReadByRTIdSId(resultsDBO);
				JSONObject data = new JSONObject();
				data.put("resultsTagName", resultsPVO.getResultsTagName());
				data.put("resultsTagId",resultsPVO.getResultsTagId() );
				data.put("cid", resultsPVO.getCid());
				data.put("className", resultsPVO.getClassName());
				data.put("studentId",resultsPVO.getStudentId() );
				data.put("studentName",resultsPVO.getStudentName() );
				data.put("number", resultsPVO.getNumber());
				data.put("totalPoints", resultsPVO.getTotalPoints());
				//获得各课程的排名doSelectByCidRtidStuId
				ClassStudentDBO classStudentDBO =  new ClassStudentDBO();
				classStudentDBO.setStudentId(studentId);
				List<ClassStudentDBO> classStudentDBOs = (List<ClassStudentDBO>) ClassStudentService_.doSelectData(classStudentDBO);
				Long cid = classStudentDBOs.get(0).getCid();
				if (cid == null) {
					throw new IllegalArgumentException("该班级cid不存在");
				}
				ResultsDBO dbo = new ResultsDBO();
				dbo.setCid(cid);
				dbo.setResultsTagId(resultsTagId);
				dbo.setStudentId(studentId);
				JSONArray courseScoreList = new JSONArray();
//				List<ResultsPVO> courseScoreList = new ArrayList<ResultsPVO>();
				List<ResultsDBO> resultsDBOs = (List<ResultsDBO>) ResultsService_.doSelectData(dbo);
				for (ResultsDBO resultsDBO2 : resultsDBOs) {
					ResultsDBO dbo2 = new ResultsDBO();
					dbo2.setResultsId(resultsTagId);
					dbo2.setCid(cid);
					dbo2.setStudentId(studentId);
					dbo2.setCourseId(resultsDBO2.getCourseId());
					ResultsPVO resultsPVO1 = (ResultsPVO) ResultsService_.doSelectByCidRtidStuId(resultsDBO2);
					courseScoreList.add(resultsPVO1);
				}			
				data.put("courseScoreList", courseScoreList);
				result.setData(data);
		
			}	
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 *  13.2.7 学生科目成绩新增接口/qxy/results/student/course?token=[token]
	 *  
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/results/student/course", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsStudentCoursePOST(Boolean test,String token,@RequestBody ResultsDBO dbo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {
//				if (josn.get("resultsTagId") != null && josn.get("studentId") != null&&josn.get("courseId") != null && josn.get("score") != null) {
////					String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
////					JSONObject jobj=JSON.parseObject(str);    
////					result.setData(jobj);	
//				} else{
//					result.setInfo("成绩标签id--必填项,学生id--必填项,科目id--必填项,学生科目分数--必填项");
//				}
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				dbo.setIsUse(1);
				if (dbo.getResultsTagId() == null) {
					throw new IllegalArgumentException("该成绩标签id不存在");
				}
				if (dbo.getStudentId() == null) {
					throw new IllegalArgumentException("该学生id不存在");
				}
				if (dbo.getCourseId() == null) {
					throw new IllegalArgumentException("该科目id不存在");
				}
				if (dbo.getScore() == null) {
					throw new IllegalArgumentException("该学生科目分数不存在");
				}
				UStudentPVO student = new UStudentPVO();
				student.setStudentId(dbo.getStudentId());
				student =UStudentService_.findByIdOrNumberWithGrade(student);
				dbo.setCid(student.getCid());
				dbo.setStudentName(student.getStudentName());
				ResultsService_.doInsert(dbo);
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 13.2.8 学生科目成绩修改接口  /qxy/results/student/course?token=[token]
	 */
	@RequestMapping(value = "/results/student/course", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsStudentCoursePUT(Boolean test,String token,@RequestBody ResultsDBO dbo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {
//				if (josn.get("resultsId") != null && josn.get("score") != null ) {
////					String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
////					JSONObject jobj=JSON.parseObject(str);    
////					result.setData(jobj);	
//				} else{
//					result.setInfo("成绩id--必填项,学生科目分数--必填项");
//				}
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				if (dbo.getResultsId() == null) {
					throw new IllegalArgumentException("该成绩标签id不存在");
				}
				if (dbo.getScore() == null) {
					throw new IllegalArgumentException("该学生科目分数不存在");
				}
				ResultsService_.doUpdate(dbo);

			}
	
		} catch (Exception e) {
			result.setInfo("修改失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}


	/**
	 * 13.2.9 学生科目成绩删除接口  /qxy/results/student/course?token=[token]
	 */
	@RequestMapping(value = "/results/student/course", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsStudentCourseDELETE(Boolean test,String token,@RequestBody ResultsDBO dbo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {
//				if (josn.get("resultsId") != null) {
////					String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
////					JSONObject jobj=JSON.parseObject(str);    
////					result.setData(jobj);	
//				} else{
//					result.setInfo("成绩id--必填项");
//				}
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				if (dbo.getResultsId() == null) {
					throw new IllegalArgumentException("该成绩id不存在");
				}
				dbo = (ResultsDBO) ResultsService_.doRead(dbo);
				if(dbo == null){
					result.setInfo("请不要重复删除");
					result.setStatus(2);
					return result;
				}
				ResultsService_.doDelete(dbo);

			}
		} catch (Exception e) {
			result.setInfo("删除失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}	
	
	/**
	 *  13.2.10 班级学生成绩导入接口/qxy/import/results/course?token=[token]
	 *  先通过2.1.6 资源上传接口上传excel返回资源地址，再调用这个接口导入班级学生成绩
	 */
	@RequestMapping(value = "/import/results/course", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean importResultsCoursePOST(Boolean test,@RequestParam("excel") MultipartFile file,Long resultsTagId,Long cid,
			Long courseId,String token,@RequestBody JSONObject josn) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {
				if (josn.get("resultsTagId") != null && josn.get("cid") != null&&josn.get("courseId") != null && josn.get("excel") != null) {
//					String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
//					JSONObject jobj=JSON.parseObject(str);    
//					result.setData(jobj);	
				} else{
					result.setInfo("成绩标签id--必填项,班级id--必填项,科目id--必填项, excel地址--必填项");
				}
			} else {
				InputStream is = null;
				Workbook workbook = null;
				try {
					if (doCheckToken(token) == false) {
						return tokenFail();
					}
					if (cid == null) {
						throw new IllegalArgumentException("cid不能为空");
					}
					if (resultsTagId == null) {
						throw new IllegalArgumentException("resultsTagId不能为空");
					}
					if (courseId == null) {
						throw new IllegalArgumentException("courseId不能为空");
					}		
					is = file.getInputStream();
					String filename = file.getOriginalFilename();
					if (filename != null && (filename.lastIndexOf(".xlsx") == (filename.length() - 5))) {
						workbook = new XSSFWorkbook(is);
					} else {
						workbook = new HSSFWorkbook(is);
					}					
					Sheet sheet = workbook.getSheetAt(0);
					
					int rowCount = sheet.getLastRowNum();
					Map<String, Object> data = new HashMap<String, Object>();
					Map<String, String> error = new HashMap<String, String>();
					int successCount = 0;
					ResultsDBO resultsDBO;
					UStudentDBO uStudentDBO;
					for (int i = 1; i <= rowCount; i++) {
						try {
							Row row = sheet.getRow(i);
							if (row.getLastCellNum() > 1) {
								//学生姓名	学号	成绩分数
								String studentName = ExcelUtil.convertToString(row.getCell(1)); // 学生姓名
								Long number = ExcelUtil.convertToLong(row.getCell(2));// 学号
								String score = ExcelUtil.convertToString(row.getCell(3));// 分数
								if (StringUtils.isEmpty(studentName)) {
									throw new IllegalArgumentException("学生姓名不能为空");
								}
								if (StringUtils.isEmpty(score)) {
									throw new IllegalArgumentException("分数不能为空");
								}
								if (number == null) {
									throw new IllegalArgumentException("学号不能为空");
								}
								//获得studentId
								uStudentDBO = new UStudentDBO();
								uStudentDBO.setNumber(number);
								uStudentDBO = (UStudentDBO) UStudentService_.doRead(uStudentDBO);
								Long studentid = uStudentDBO.getStudentId();
								resultsDBO = new ResultsDBO();
								resultsDBO.setResultsId(resultsTagId);
								resultsDBO.setCourseId(courseId);
								resultsDBO.setCid(cid);
								resultsDBO.setStudentId(studentid);
								resultsDBO.setStudentName(studentName);
								resultsDBO.setScore(score);
								resultsDBO.setIsUse(1);
								resultsDBO.setPuk(ONE);
								ResultsService_.doInsert(resultsDBO);									
								successCount++;							
							}
						} catch (Exception e) {
							error.put("" + i, e.getMessage());
						}
					}
					data.put("count", successCount);
					data.put("error", error);
					data.put("info", "ok");
					result.setData(data);
				} catch (Exception e) {
					logger.error("导入失败", e);
					result.setInfo("导入失败，" + e.getMessage());
					result.setStatus(1);
				} finally {
					if (workbook != null) {
						try {
							workbook.close();
						} catch (Exception e) {
							logger.error("导入workbook关闭异常", e);
						}
					}
					if (is != null) {
						try {
							is.close();
						} catch (IOException e) {
							logger.error("导入excel关闭异常", e);
						}
					}
				}

			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 13.2.11 考试科目成绩删除接口/qxy/results/course?token=[token]
	 */
	@RequestMapping(value = "/results/course", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsCourseDELETE(Boolean test,String token,@RequestBody JSONObject josn) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {
				if (josn.get("resultsTagId") != null && josn.get("cid") != null && josn.get("courseId") != null) {
	//				String str = "{\"status\":\"0\",	\"data\":{		\"info\":\"ok\"	}}";
	//				JSONObject jobj=JSON.parseObject(str);    
	//				result.setData(jobj);	
				} else{
					result.setInfo("成绩标签id--必填项,班级id--必填项,科目id--必填项");
				}
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				if (josn.get("resultsTagId") != null && josn.get("cid") != null && josn.get("courseId") != null) {
					ResultsDBO resultsDBO = new ResultsDBO();
					resultsDBO.setResultsTagId((Long)josn.get("resultsTagId"));
					resultsDBO.setCid((Long) josn.get("cid"));
					resultsDBO.setCourseId((Long) josn.get("courseId"));
					List<ResultsDBO> resultsDBOs = (List<ResultsDBO>) ResultsService_.doSelectData(resultsDBO);
					for (ResultsDBO resultsDBO2 : resultsDBOs) {
						ResultsService_.doDelete(resultsDBO2);
					}
				} else{
					result.setInfo("成绩标签id--必填项,班级id--必填项,科目id--必填项");
				}
			}
		} catch (Exception e) {
			result.setInfo("删除失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}


	/**
	 * 13.2.12 班级科目列表查询接口/qxy/results/class/course?resultsTagId=[resultsTagId]&cid=[cid]&token=[token]
	 * 需要使用Token验证查询者身份
	 * 仅获取所有有效的课程
	 * status值需要接口根据cid+resultsTagId+courseId联合索引，如果有值则为1，没有值则为0；
	 */
	
	@RequestMapping(value = "/results/class/course", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsClassCourseGET(Boolean test,Long resultsTagId,String token,Long cid) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {					
				if (cid != null && resultsTagId != null ) {
					String str = "{\"resultsTagName\":\"期中考试\",\"resultsTagId\":\"2\",\"cid\":\"12\",\"className\":\"高一三班\",\"courseList\":[{\"courseId\":\"1\",\"courseName\":\"语文\",\"status\":\"1\"},{\"courseId\":\"2\",\"courseName\":\"数学\",\"status\":\"0\"}]}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
				} else{
					result.setInfo("班级id--必填项, 成绩标签id--必填项");
				}	
                  
			} else {
				// token校验
				if (doCheckToken(token) == false) {
					return tokenFail();
				}
				if (resultsTagId != null || cid != null)  {
					JSONObject data = new JSONObject();
					// 获取学生成绩信息
					ResultsDBO resultsDBO = new ResultsDBO();	
					resultsDBO.setResultsTagId(resultsTagId);
					resultsDBO.setCid(cid);
					ResultsPVO resultsPVO = ResultsService_.doReadResult(resultsDBO);
					data.put("resultsTagName", resultsPVO.getResultsTagName());
					data.put("resultsTagId", resultsPVO.getResultsTagId());
					data.put("cid",resultsPVO.getCid() );
					data.put("className", resultsPVO.getClassName());
					//根据成绩标签id,班级id，resultsTagId成绩标签id获取班级学生成绩状态信息
					ResultsDBO resultsDBO2 = new ResultsDBO();	
					resultsDBO2.setResultsTagId(resultsTagId);
					resultsDBO2.setCid(cid);
					List<ResultsPVO> resultsPVOs = ResultsService_.findByRTIdCidCourseId(resultsDBO2);
					List<Map<String,Object>> courseList = new ArrayList<Map<String, Object>>();
					for (ResultsPVO resultsPVO2 : resultsPVOs) {
						Map<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("courseId", resultsPVO2.getCourseId());
						hashMap.put("courseName", resultsPVO2.getCourseName());
						hashMap.put("status", resultsPVO2.getScore()==null?"0":"1");
						courseList.add(hashMap);	
					}
					data.put("courseList", courseList);
					result.setData(data);
				} else{
					result.setInfo("成绩标签id--必填项,班级id--必填项");
				}
				
			}	
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 13.2.13 班级科目学生成绩列表查询接口/qxy/results/class/course/student?resultsTagId=[resultsTagId]&cid=[cid]&courseId=[courseId]&page=[page]&size=[size]&order=[order]&token=[token]
     * 需要使用Token验证查询者身份
     *  声明开发测试时前端需要在url上加上test=true参数，标示为测试接口，接口会返回静态数据以提供给前端调试页面使用，前端需要使用测试接口完成数据渲染工作后，等到正式接口开发完成后，才能调用正式接口；
	 */
	
	@RequestMapping(value = "/results/class/course/student", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean resultsClassCourseStudentGET(Boolean test,Long resultsTagId,Long cid,Long courseId,String token,String order,Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (test != null && test == true) {					
				if (cid != null && resultsTagId != null && courseId != null ) {
					String str = "{\"resultsTagName\":\"期中考试\",\"resultsTagId\":\"2\",\"cid\":\"12\",\"className\":\"高一三班\",\"courseId\":\"1\",\"courseName\":\"语文\",\"studentList\":[{\"studentId\":\"1\",\"studentName\":\"张三\",\"number\":\"100000\",\"courseScore\":\"100\",\"courseRanking\":\"1\"},{\"studentId\":\"2\",\"studentName\":\"张三三\",\"number\":\"100001\",\"courseScore\":\"99\",\"courseRanking\":\"2\"}]}";
					JSONObject jobj=JSON.parseObject(str);    
					result.setData(jobj);	
				} else{
					result.setInfo("班级id--必填项, 成绩标签id--必填项，科目id--必填项");
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
				// 获取学生成绩信息
				ResultsDBO resultsDBO = new ResultsDBO();
				if (resultsTagId == null) {
//					throw new IllegalArgumentException("该成绩标签id不存在");
					result.setInfo("该成绩标签id不存在");
				}
				if (cid == null) {
//					throw new IllegalArgumentException("该班级id不存在");
					result.setInfo("该班级id不存在");
				}
				if (courseId == null) {
//					throw new IllegalArgumentException("该科目id不存在");
					result.setInfo("该科目id不存在");
				}
				resultsDBO.setResultsTagId(resultsTagId);
				resultsDBO.setCid(cid);
				resultsDBO.setCourseId(courseId);;
				ResultsPVO resultsPVO = ResultsService_.findByRTIdCidCsId(resultsDBO);
				data.put("resultsTagName", resultsPVO.getResultsTagName());
				data.put("resultsTagId", resultsPVO.getResultsTagId());
				data.put("cid",resultsPVO.getCid() );
				data.put("className", resultsPVO.getClassName());
				data.put("courseId", resultsPVO.getCourseId());
				data.put("courseName", resultsPVO.getCourseName());
				// 获取学生科目成绩列表 
				ResultsDBO resultsDBO2 = new ResultsDBO();
				resultsDBO2.setResultsTagId(resultsTagId);
				resultsDBO2.setCid(cid);
				resultsDBO2.setCourseId(courseId);;
				pageModel.setPageCurrent(page);
				pageModel.setPageLimit(size);
				pageModel.setFormParamBean(resultsDBO2);
				if ("0".equals(order)) {
					ResultsService_.doSelectPageResASC(pageModel);
				} else {
					ResultsService_.doSelectPageResDESC(pageModel);
				}
				List<ResultsPVO> studentlist = (List<ResultsPVO>) pageModel.getPageListData();			
				data.put("page", page);
				data.put("size", size);
				data.put("count", pageModel.getResultCount());
				data.put("studentlist", studentlist);
				result.setData(data);
			}	
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}
	


}
