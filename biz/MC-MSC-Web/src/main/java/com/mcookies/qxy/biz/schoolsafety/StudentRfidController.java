package com.mcookies.qxy.biz.schoolsafety;

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
import com.mcookies.qxy.common.Class.ClassPVO;
import com.mcookies.qxy.common.Class.ClassService;
import com.mcookies.qxy.common.ClassStudent.ClassStudentDBO;
import com.mcookies.qxy.common.ClassStudent.ClassStudentService;
import com.mcookies.qxy.common.SGradeLabel.SGradeLabelService;
import com.mcookies.qxy.common.STerm.STermService;
import com.mcookies.qxy.common.StudentRfid.StudentRfidDBO;
import com.mcookies.qxy.common.StudentRfid.StudentRfidPVO;
import com.mcookies.qxy.common.StudentRfid.StudentRfidService;
import com.mcookies.qxy.common.UStudent.UStudentPVO;

@Controller
/** 学生rfid*/
public class StudentRfidController extends MyControllerSupport {
	@Resource
	protected StudentRfidService StudentRfidService_;
	@Resource
	protected ClassStudentService ClassStudentService_;
	@Resource
	protected ClassService ClassService_;
	@Resource
	protected SGradeLabelService SGradeLabelService_;
	@Resource
	protected STermService STermService_;
	
	
	@RequestMapping(value = "/studentrfidlist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean studentrfidlistGET(Long studentId,String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			StudentRfidDBO rfids = new StudentRfidDBO();
			rfids.setStudentId(studentId);
			List<StudentRfidDBO> rlist =(List<StudentRfidDBO>)StudentRfidService_.doSelectData(rfids);
			result.setData(rlist);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}
	
	
	/**
	 * 学生rfid卡新增接口
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/studentrfid", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean studentrfidPOST(@RequestBody StudentRfidPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(pvo.getToken()) == false) {
				return tokenFail();
			}
			//查询该学生是否已经有卡
			StudentRfidDBO sdbo = new StudentRfidDBO();
			sdbo.setStudentId(pvo.getStudentId());
			List<StudentRfidDBO> rlist =(List<StudentRfidDBO>)StudentRfidService_.doSelectData(sdbo);
			if(rlist!=null&&rlist.size()>0){
				pvo.setIsEffective(0);
				pvo.setIsUse(1);
				for(StudentRfidDBO tmp:rlist){
					//重复
					if(tmp.getStudentId().equals(pvo.getStudentId()) && tmp.getRfid().equals(pvo.getRfid())){
						result.setInfo("已经添加过此卡");
						result.setStatus(2);
						return result;
					}
					StudentRfidService_.doInsert(pvo);
				}
			}else{
				//直接添加，并默认为有效
				pvo.setIsEffective(1);
				pvo.setIsUse(1);
				StudentRfidService_.doInsert(pvo);
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}
	
	/**
	 * 学生rfid卡修改及设为有效（默认为无效）接口
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/studentrfid", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean studentrfidPUT(@RequestBody StudentRfidPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(pvo.getToken()) == false) {
				return tokenFail();
			}
			//修改其他rfid卡为无效
			StudentRfidDBO srfid = new StudentRfidDBO();
			srfid.setStudentId(pvo.getStudentId());
			srfid.setIsEffective(0);
			StudentRfidService_.doUpdateAll(srfid);
			//
			StudentRfidService_.doUpdate(pvo);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 学生rfid卡删除接口
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/studentrfid", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean studentrfidDELETE(@RequestBody StudentRfidPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(pvo.getToken()) == false) {
				return tokenFail();
			}
			//删除该关联
			StudentRfidService_.doDelete(pvo);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}	
	
	/**
	 * rfid卡对应学生列表查询及搜索接口
	 * @param token
	 * @param rfid
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/rfidstudent", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean rfidstudentGET(String token,Long rfid,Integer page,Integer size) {
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
			//获取学生列表
			StudentRfidDBO param = new StudentRfidDBO();
			param.setRfid(rfid);
			pageModel.config();
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);
			pageModel.setFormParamBean(param);
			StudentRfidService_.doSelectPageStudentByrfid(pageModel);
			List<UStudentPVO> rlist = (List<UStudentPVO>)pageModel.getPageListData();
			JSONObject data = new JSONObject();
			data.put("page", page);
			data.put("size", size);
			data.put("count", pageModel.getResultCount());
			if(rlist!=null&&rlist.size()>0){
				for(UStudentPVO tmp:rlist){
					//得到关联的班级
					ClassStudentDBO cs = new ClassStudentDBO();
					cs.setStudentId(tmp.getStudentId());
					List<ClassStudentDBO> list1 =(List<ClassStudentDBO>)ClassStudentService_.doSelectData(cs);
					if(list1!=null&&list1.size()>0){
						Long cid = list1.get(0).getCid();
						ClassPVO cpvo = new ClassPVO();
						cpvo.setCid(cid);
						pageModel.config();
						pageModel.setPageCurrent(1);
						pageModel.setPageLimit(12);
						pageModel.setFormParamBean(cpvo);
						ClassService_.doSelectPageClass(pageModel);
						List<ClassPVO> list2 = (List<ClassPVO>)pageModel.getPageListData(); 
						if(list2!=null&&list2.size()>0){
							tmp.setClassName(list2.get(0).getClassName());
							tmp.setTermName(list2.get(0).getTermName());
							tmp.setGradeName(list2.get(0).getGradeName());
						}
					}
				}
			}
			data.put("rfidStudent",rlist);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
}
