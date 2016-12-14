package org.jfpc.framework.helper;

import org.jfpc.constants.ISFrameworkConstants;
import org.jfpc.framework.support.MyModelAndViewSupport;


/**
 * 提示信息过渡页
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/8/27
 */
public class MessagePageHelper implements ISFrameworkConstants {
	/**
	 * 主页提示页面
	 */
	public static final String MESSAGE_WWW = "0";
	/**
	 * 前台提示页面
	 */
	public static final String MESSAGE_WEB = "1";
	/**
	 * 后台提示页面
	 */
	public static final String MESSAGE_CON = "9";
	/**
	 * 跳转到提示信息过渡页面
	 * @param type
	 * @return
	 */
	public static MyModelAndViewSupport getMessageMAV(String type){
		MyModelAndViewSupport mv = new MyModelAndViewSupport();
		mv.setViewName("msg/"+type+"message");
		return mv;
	}
	/**
	 * 跳转到提示信息过渡页面
	 * @param type
	 * @return
	 */
	public static MyModelAndViewSupport getMessageMAV(String type,String message){
		MyModelAndViewSupport mv = new MyModelAndViewSupport();
		mv.setViewName("msg/"+type+"message");
		mv.addObject("message", message);
		return mv;
	}
	/**
	 * 跳转到提示信息过渡页面
	 * @param type
	 * @return
	 */
	public static MyModelAndViewSupport getMessageMAV(String type,String message,String forwordURL){
		MyModelAndViewSupport mv = new MyModelAndViewSupport();
		mv.setViewName("msg/message"+type);
		mv.addObject("message", message);
		mv.addObject("forwordURL",forwordURL);
		return mv;
	}
	
	/**
	 * 跳转到提示信息过渡页面
	 * @param type
	 * @return
	 */
	public static MyModelAndViewSupport getMessageMAV(String type,String message,String forwordURL,boolean error){
		MyModelAndViewSupport mv = new MyModelAndViewSupport();
		mv.setViewName("msg/message"+type);
		mv.addObject("message", message);
		mv.addObject("forwordURL",forwordURL);
		if(error==false)
			mv.addObject("error", error);
		return mv;
	}
}
