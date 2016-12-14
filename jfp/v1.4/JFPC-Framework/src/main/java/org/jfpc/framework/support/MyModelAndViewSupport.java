package org.jfpc.framework.support;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

/**
 * 页面视图层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class MyModelAndViewSupport extends ModelAndView{
	public MyModelAndViewSupport() {
		super();
	}
	
	public MyModelAndViewSupport(String viewName) {
		super(viewName);
	}
	
	public MyModelAndViewSupport(View view) {
		super(view);
	}
	
//	/**
//	 * 页面路径
//	 */
//	String pagePath;
//	/**
//	 * 页面名称
//	 */
//	String pageName;
//	public String getPageName() {
//		return pageName;
//	}
//
//	public void setPageName(String pageName) {
//		this.pageName = pageName;
//	}
//
//	/**
//	 * 设定ViewName
//	 */
//	public void setViewName(String viewName) {
//		if(StringUtils.isNotEmpty(pageName))
//			super.setViewName(pagePath+"/"+pageName);
//		else
//			super.setViewName(viewName);
//	}
}
