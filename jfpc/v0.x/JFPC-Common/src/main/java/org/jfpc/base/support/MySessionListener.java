package org.jfpc.base.support;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.jfpc.base.ISFrameworkConstants;
import org.jfpc.base.bean.LoginerBean;
import org.jfpc.base.helper.BeanFactoryHelper;
import org.jfpc.common.login.service.LoginService;
import org.springframework.web.context.request.RequestContextHolder;

public class MySessionListener implements HttpSessionListener, ISFrameworkConstants {

	/**
	 * 用户连接
	 * 
	 * @param event
	 */
	public void sessionCreated(HttpSessionEvent event) {
	}

	/**
	 * 用户退出
	 * 
	 * @param event
	 */
	public void sessionDestroyed(HttpSessionEvent event) {
		try {
			LoginerBean loginer = new LoginerBean();
			Object tokenSession = RequestContextHolder.getRequestAttributes().getAttribute(CONSTANT_USER_TOKEN, 1);
			RequestContextHolder.getRequestAttributes().removeAttribute(CONSTANT_USER_TOKEN, 1);
			if (tokenSession != null) {
				loginer.setToken(tokenSession.toString());
				LoginService loginService = (LoginService)BeanFactoryHelper.getBean("loginService");
				loginService.doLogOut(loginer);
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
}