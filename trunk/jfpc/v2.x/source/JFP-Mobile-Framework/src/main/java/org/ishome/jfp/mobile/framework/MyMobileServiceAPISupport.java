package org.ishome.jfp.mobile.framework;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ishome.jfp.common.Hospital.HospitalInfoService;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.mq.IMedMqService;
import org.ishome.jfp.mobile.conf.MobileSecyrityConfig;
import org.ishome.jfp.mobile.constants.IMSRConstants;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * 接口控制层超类
 * 
 * @author Spook 
 * @version 2.3.1 2015/6/23
 * @since 2.3.1 2015/6/23
 */
public class MyMobileServiceAPISupport implements IMSRConstants, ISFrameworkConstants {

	//缓存
	@Resource
	protected IMedMqService mqService;
	//医院信息类
	@Resource
	protected HospitalInfoService hospitalInfoService;
	//安全相关类
	@Resource
	protected MobileSecyrityConfig cloundSecyrityConfig;
	
	/**
	 * 检查医院合法性
	 * 
	 * @param hosId
	 * @return
	 */
	public boolean checkHospital(@PathVariable String hosId, HttpServletRequest request) {
		// 检查医院ID合法性
		// 检查访问请求地址合法性

		return true;
	}

	public String getIpAddr(HttpServletRequest request, boolean remote) {
		if (remote)
			return request.getRemoteAddr();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 登录拦截
	 * 
	 * @param response
	 */
	public void doCheckLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

	}

	/**
	 * 返回拦截
	 * 
	 * @param request
	 * @param response
	 */
	public void goBack(HttpServletRequest request, HttpServletResponse response) {
		// RESTResultBean rs = new RESTResultBean();
		// rs.setCode("9");
		// rs.setMessage("9");//登录失效，请重新登录
		// OutputStream out = response.getOutputStream();
		// out.write(JSONObject.fromObject(rs).toString().getBytes("UTF-8"));
		// out.flush();
		// out.close();
	}
}
