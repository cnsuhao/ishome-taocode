package com.ttnn.framework.support;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.ttnn.framework.ISFrameworkConstants;

public class CSModelAndViewSupport extends ModelAndView implements ISFrameworkConstants {
	
	/**
	 * 获得当前画面的版本号，根据产品ID
	 * @return
	 */
	protected static String getVerssion(){
		Object usb = RequestContextHolder.getRequestAttributes().getAttribute(CONSTANT_USER_TOKEN, 1);
//		if(usb instanceof String)
//		{
//			return TokenUtil.getProductId(usb.toString());
//		}
		
		return "";
	}
	
	/**
	 * Convenient constructor when there is no model data to expose.
	 * Can also be used in conjunction with <code>addObject</code>.
	 * @param view View object to render
	 * @see #addObject
	 */
	public CSModelAndViewSupport(View view) {
		super(view);
	}

	/**
	 * 设定返回画面名称
	 * 
	 * @param viewName
	 *            画面名称
	 * @param verssion
	 *            版本ID
	 */
	public CSModelAndViewSupport(String viewName) {
		super(viewName + getVerssion());
	}
	
	/**
	 * 设定返回画面名称
	 * @param viewName
	 *            画面名称
	 */
	public void setViewName(String viewName) {
		super.setViewName(viewName + getVerssion());
	}

}
