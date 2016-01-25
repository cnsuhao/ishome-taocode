package org.jfpc.base.support;

import org.springframework.web.servlet.ModelAndView;

/**
 * 页面视图层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class MyModelAndViewSupport extends ModelAndView{

	public MyModelAndViewSupport(String viewName) {
		super(viewName);
	}

}
