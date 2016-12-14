package org.isotope.jfp.framework.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.isotope.jfp.framework.beans.pub.LogBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.support.common.ILogSupport;
import org.isotope.jfp.framework.utils.HttpRequestHelper;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 日志输出模型
 * 
 * @author Spook
 * 
 */
public class BusinessLoggerInterceptorAdapter extends HandlerInterceptorAdapter implements ISFrameworkConstants {
	/**
	 * 日志输出实现
	 */
	private ILogSupport log;

	public ILogSupport getLog() {
		return log;
	}

	public void setLog(ILogSupport log) {
		this.log = log;
	}

	/**
	 * 保存业务访问日志
	 * 
	 * @param request
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		LogBean logBean = new LogBean();
		try {
			logBean.setRequestURL(HttpRequestHelper.transcode(request.getRequestURI(), SYSTEM_CHARSET));
			// 获取用户IP
			logBean.setRemoteIP(HttpRequestHelper.getIpAddr(request));
			// 获得所有参数
			logBean.setAccessParams(HttpRequestHelper.transcode(request, SYSTEM_CHARSET));

			// 日志保存
			if (log != null) {
				log.send(logBean);
			}
		} catch (Exception e) {
			System.err.println("保存业务访问日志信息出错...... " + logBean);
		}
		return super.preHandle(request, response, handler);
	}
}
