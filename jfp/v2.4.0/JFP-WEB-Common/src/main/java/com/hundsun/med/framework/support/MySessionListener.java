package com.hundsun.med.framework.support;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.ishome.jfp.framework.beands.LoginerBean;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
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
			if (tokenSession != null) {
				loginer.setToken(tokenSession.toString());
				//LoginService loginService = (LoginService)BeanFactoryHelper.getBean("loginService");
				//loginService.doLogOut(loginer);
			}
			
			HttpSession session = event.getSession();
			Enumeration<?> es = session.getAttributeNames();
			while(es.hasMoreElements())
			{
				session.removeAttribute(es.nextElement().toString());
			}

		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
}