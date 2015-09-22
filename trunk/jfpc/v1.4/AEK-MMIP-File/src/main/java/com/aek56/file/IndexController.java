package com.aek56.file;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.jfpc.framework.support.MyFrameworkSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 域名访问系统首页
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/5/30
 */
@Controller
public class IndexController extends MyFrameworkSupport{

	/**
	 * 默认首页
	 * @return
	 */
	public MyModelAndViewSupport getModelAndView() {
		//return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_WEB, "注册失败，请联系客服解决", "/");
		return new MyModelAndViewSupport("index");
	}

	/**
	 * 默认登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public MyModelAndViewSupport root(Locale locale,HttpServletRequest request) {

		// 销毁session
		request.getSession().invalidate();
		return getModelAndView();
	}
	
	/**
	 * 默认登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public MyModelAndViewSupport rootPOST(Locale locale,HttpServletRequest request) {
		
		// 销毁session
		request.getSession().invalidate();
		return getModelAndView();
	}
	

}
