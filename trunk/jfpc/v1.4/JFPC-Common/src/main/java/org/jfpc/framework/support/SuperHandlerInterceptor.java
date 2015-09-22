package org.jfpc.framework.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 基底拦截器
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.2.1 2014/11/05
 * @version 0.1.0 2014/2/8
 */
public class SuperHandlerInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(SuperHandlerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    System.out.println("====>>>>>>>>>>>>"+handler);  
		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = (HandlerMethod) handler;  
			System.out.println("1111111111111"+handlerMethod.getMethod());
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("22222222222222222222"+handler);  
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("33333333333333333"+handler);  
	}
}
