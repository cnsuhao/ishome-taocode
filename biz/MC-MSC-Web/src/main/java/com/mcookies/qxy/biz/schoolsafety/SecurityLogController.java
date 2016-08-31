package com.mcookies.qxy.biz.schoolsafety;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.ClassStudent.ClassStudentDBO;
import com.mcookies.qxy.common.ClassStudent.ClassStudentService;
import com.mcookies.qxy.common.LogSecurity.LogSecurityPVO;
import com.mcookies.qxy.common.LogSecurity.LogSecurityService;
import com.mcookies.qxy.common.UStudent.UStudentDBO;
import com.mcookies.qxy.common.UStudent.UStudentService;

/**
 * 安全日志
 * 
 * @author macBookTang
 *
 */
@Controller
public class SecurityLogController extends MyControllerSupport {
	@Resource
	protected UStudentService UStudentService_;
	@Resource
	protected ClassStudentService ClassStudentService_;
	@Resource
	protected LogSecurityService LogSecurityService_;

	/**
	 * 学生列表查询接口
	 * 
	 * @param token
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "/student/namelist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean stundentNamelistGET(String token, Long cid) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			ClassStudentDBO param = new ClassStudentDBO();
			param.setCid(cid);
			List<UStudentDBO> rlist = (List<UStudentDBO>) UStudentService_.doSelectListBycid(param);
			JSONObject data = new JSONObject();
			data.put("count", rlist.size());
			data.put("student", rlist);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 安全日志查询接口
	 * 
	 * @param token
	 * @param cid
	 * @param studentId
	 * @param deviceId
	 * @param time
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/securitylog", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean securitylogGET(String token, Long termId, Long cid, Long[] studentId, Long deviceId, String time, Integer page, Integer size) {
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

			LogSecurityPVO pvo = new LogSecurityPVO();
			pvo.setCid(cid);
			pvo.setDeviceId(deviceId);
			if(termId!=null){
				pvo.setTermId(termId);				
			}
			if (time != null && !"".equals(time)) {
				String[] times = time.split("|");
				pvo.setStartTime(times[0]);
				pvo.setEndTime(times[2]);
			}
			JSONObject data = new JSONObject();
			data.put("page", page);
			data.put("size", size);
			
			if (studentId!=null) {
				List<FrameworkDataBean> logslist = new ArrayList<FrameworkDataBean>();
				//List<PageVOSupport> pa= new ArrayList<PageVOSupport>();
				//JSONArray securitylog= new JSONArray();
				for (Long id : studentId) {
					pvo.setStudentId(id);
					pageModel.config();
					pageModel.setPageCurrent(page);
					pageModel.setPageLimit(size);
					pageModel.setFormParamBean(pvo);
					LogSecurityService_.doSelectPageSecurityLog(pageModel);
					for(FrameworkDataBean log : pageModel.getPageListData()){
						logslist.add(log);
					}
					/*List<LogSecurityPVO> loglist = (List<LogSecurityPVO>) LogSecurityService_.doSelectPageSecurityLog(pvo);
					data.put("count", pageModel.getResultCount());	
					//securitylog.add(pageModel.getPageListData());
					//pa.add( (PageVOSupport) pageModel.getPageListData());
					for(LogSecurityPVO log : loglist){
						logslist.add(log);
					}*/
					
				}
				//pageModel.setPageListData(logslist);
				data.put("securitylog",logslist);
								

			} else {
				pageModel.config();
				pageModel.setPageCurrent(page);
				pageModel.setPageLimit(size);
				pageModel.setFormParamBean(pvo);
				LogSecurityService_.doSelectPageSecurityLog(pageModel);
				data.put("count", pageModel.getResultCount());
				data.put("securitylog",pageModel.getPageListData());
			}
			
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}
}
