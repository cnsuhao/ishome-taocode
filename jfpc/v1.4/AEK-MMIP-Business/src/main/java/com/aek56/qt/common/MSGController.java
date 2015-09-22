package com.aek56.qt.common;

import javax.annotation.Resource;

import org.jfpc.beans.common.CS0D1.CS0D1DBO;
import org.jfpc.beans.common.CS0D1.CS0D1Service;
import org.jfpc.beans.common.MS0C1.MS0C1DBO;
import org.jfpc.beans.common.MS0C1.MS0C1Service;
import org.jfpc.beans.common.MS3H1.MS3H1DBO;
import org.jfpc.beans.common.MS3H1.MS3H1Service;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.page.PageVOSupport;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 消息中心页面
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/5/30
 */
@Controller
public class MSGController extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MSGController.class);		
	
	@RequestMapping(value = "/3000200", method = RequestMethod.POST)
	public MyModelAndViewSupport m3000200POST(String index) {
		MyModelAndViewSupport mv = new MyModelAndViewSupport("qt/MSG/MSG01");
		mv.addObject("index", index);
		return mv;
	}

	
	//系统公告
	@Resource
	MS0C1Service MS0C1Service_;
	@RequestMapping(value = "/30002001", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m30002001POST(PageVOSupport pvo) {
		RESTResultBean rb = new RESTResultBean();
		pageModel.setPageCurrent(pvo.getPageCurrent());
		pageModel.setPageLimit(pvo.getPageLimit());
		MS0C1DBO dbo = new MS0C1DBO();
		
		pageModel.setFormParamBean(dbo);
		rb.setResult(MS0C1Service_.doSelectPage(pageModel, false));
		return rb;
	}
	//内部消息
	@Resource
	CS0D1Service CS0D1Service_;
	@RequestMapping(value = "/30002002", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m30002002POST(PageVOSupport pvo) {
		RESTResultBean rb = new RESTResultBean();
		pageModel.setPageCurrent(pvo.getPageCurrent());
		pageModel.setPageLimit(pvo.getPageLimit());
		CS0D1DBO dbo = new CS0D1DBO();
		dbo.setFb4(ONE);
		dbo.setEb5(super.getCompanyId());
		pageModel.setFormParamBean(dbo);
		rb.setResult(CS0D1Service_.doSelectPage(pageModel, false));
		return rb;
	}
	
	//用户建议
	@Resource
	MS3H1Service MS3H1Service_;
	@RequestMapping(value = "/30002003", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m30002003POST(PageVOSupport pvo) {
		RESTResultBean rb = new RESTResultBean();
		pageModel.setPageCurrent(pvo.getPageCurrent());
		pageModel.setPageLimit(pvo.getPageLimit());
		MS3H1DBO dbo = new MS3H1DBO();
		dbo.setK01_yhid(super.getLoginerId());
		pageModel.setFormParamBean(dbo);
		rb.setResult(MS3H1Service_.doSelectPage(pageModel, false));
		return rb;
	}
	
	//订单消息
//		@Resource
//		MS0C1Service MS0C1Service_;
//		@RequestMapping(value = "/30002004", method = RequestMethod.POST)
//		@ResponseBody
//		public RESTResultBean m30002004POST(PageVOSupport pvo) {
//			RESTResultBean rb = new RESTResultBean();
//	pageModel.setPageCurrent(pvo.getPageCurrent());
//	pageModel.setPageLimit(pvo.getPageLimit());
//			rb.setResult(MS0C1Service_.doSelectPage(pageModel, false));
//			return rb;
//		}
		
}
