package com.ttnn.framework.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

 public class MyFrameworkInterceptor extends HandlerInterceptorAdapter {
	 protected final Logger logger = LoggerFactory.getLogger(getClass());
     @Override
     public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) {
  
         String className = handler.getClass().getName();// packageName.ClassName
         logger.debug(className);
//         if (!true) {
//             return false;
//         }
         return true;
     }
 }