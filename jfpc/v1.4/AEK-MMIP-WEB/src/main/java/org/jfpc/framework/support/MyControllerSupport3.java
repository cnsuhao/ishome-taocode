package org.jfpc.framework.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jfpc.framework.bean.LoginerBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 画面控制层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class MyControllerSupport3 extends MyControllerSupport {

	private static final Logger logger = LoggerFactory.getLogger(MyControllerSupport3.class);

	public void goBack(HttpServletRequest request, HttpServletResponse response) {}
	
	@ModelAttribute
	public boolean doCheckLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		
		//TOKEN检验通过
		if(!LoginService_.doCheckToken(request.getParameter("token"))){
			return false;
		}

		if (logger.isDebugEnabled())
			logger.error("CONSTANT_LOGINER///////CONSTANT_LOGINER///>>>>>>=====" + session.getAttribute(CONSTANT_LOGINER));

		// 判断TOKEN有效性
		if (!doCheckToken(request, response)) {
			goBack(request, response);
		}
		return true;
	}


}
