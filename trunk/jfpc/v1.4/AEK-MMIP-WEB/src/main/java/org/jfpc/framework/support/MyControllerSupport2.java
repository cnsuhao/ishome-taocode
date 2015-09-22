package org.jfpc.framework.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 画面控制层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class MyControllerSupport2 extends MyControllerSupport {

	private static final Logger logger = LoggerFactory.getLogger(MyControllerSupport2.class);

	public void goBack(HttpServletRequest request, HttpServletResponse response) {}

}
