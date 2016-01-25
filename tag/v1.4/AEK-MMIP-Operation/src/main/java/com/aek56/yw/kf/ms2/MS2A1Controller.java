package com.aek56.yw.kf.ms2;

import javax.annotation.Resource;

import org.jfpc.beans.common.MS2A1.MS2A1DBO;
import org.jfpc.beans.common.MS2A1.MS2A1Service;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MS2A1Controller extends MyControllerSupport{

	private static final Logger logger = LoggerFactory.getLogger(MS2A1Controller.class);
	@Resource
	private MS2A1Service MS2A1Service_;
	
	/**
	 * 页面视图
	 */
	public MyModelAndViewSupport getModelAndView(String pageId) {
		return new MyModelAndViewSupport(pageId);
	}
	
	/**
	 * 前台使用,用户组一览
	 */
	@RequestMapping(value = "/90101011", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m90101011POST() {
		RESTResultBean rs = new RESTResultBean();
		if (logger.isDebugEnabled()) {
			logger.debug("Enter into MS2A1Controller#m90101011POST()!");
		}
		
		rs.setResult(MS2A1Service_.doSelectPage(new MS2A1DBO(), false));

		if (logger.isDebugEnabled()) {
			logger.debug("RESTResultBean:{}", rs);
			logger.debug("Exit MS2A1Controller#m90101011POST()!");
		}

		return rs;
	}
	
	/**
	 * 后台使用,用户组一览
	 */
	@RequestMapping(value = "/90101021", method = RequestMethod.POST)
	public ModelAndView m90101021POST() {
		if (logger.isDebugEnabled()) {
			logger.debug("Enter into MS2A1Controller#m90101021POST()!");
		}
		
		MyModelAndViewSupport view = getModelAndView("yw/kf/MS2/MS2A1");

		view.addObject(LIST, MS2A1Service_.doSelectPage(new MS2A1DBO(), false));


		if (logger.isDebugEnabled()) {
			logger.debug("pageModel:{}", view);
			logger.debug("Exit MS2A1Controller#m90101021POST()!");
		}

		return view;
	}

	/**
	 * 后台使用,用户组添加
	 */
	@RequestMapping(value = "/90101022", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m90101022POST(MS2A1DBO userGroup) {
		RESTResultBean rs = new RESTResultBean();
		if (logger.isDebugEnabled()) {
			logger.debug("Enter into MS2A1Controller#m90101011POST()!.userGroup="+userGroup);
		}
		userGroup.makePuk();
		MS2A1Service_.doInsert(userGroup);
		rs.setResult(userGroup.getPuk());

		if (logger.isDebugEnabled()) {
			logger.debug("RESTResultBean:{}", rs);
			logger.debug("Exit MS2A1Controller#m90101011POST()!");
		}

		return rs;
	}
	
	/**
	 * 后台使用,用户组编辑
	 */
	@RequestMapping(value = "/90101023", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m90101023POST(MS2A1DBO userGroup) {
		RESTResultBean rs = new RESTResultBean();
		if (logger.isDebugEnabled()) {
			logger.debug("Enter into MS2A1Controller#m90101011POST()!.userGroup="+userGroup);
		}
		
		rs.setResult(MS2A1Service_.doUpdate(userGroup));

		if (logger.isDebugEnabled()) {
			logger.debug("RESTResultBean:{}", rs);
			logger.debug("Exit MS2A1Controller#m90101011POST()!");
		}

		return rs;
	}
	
	/**
	 * 后台使用,用户组删除(物理)
	 */
	@RequestMapping(value = "/90101024", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m90101024POST(MS2A1DBO userGroup) {
		RESTResultBean rs = new RESTResultBean();
		if (logger.isDebugEnabled()) {
			logger.debug("Enter into MS2A1Controller#m90101011POST()!.userGroup="+userGroup);
		}
		
		rs.setResult(MS2A1Service_.doDelete(userGroup));

		if (logger.isDebugEnabled()) {
			logger.debug("RESTResultBean:{}", rs);
			logger.debug("Exit MS2A1Controller#m90101011POST()!");
		}

		return rs;
	}
	
	/**
	 * 后台使用,用户组查看
	 */
	@RequestMapping(value = "/90101025", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m90101025POST(MS2A1DBO userGroup) {
		RESTResultBean rs = new RESTResultBean();
		if (logger.isDebugEnabled()) {
			logger.debug("Enter into MS2A1Controller#m90101011POST()!.userGroup="+userGroup);
		}
		
		rs.setResult(MS2A1Service_.doRead(userGroup));

		if (logger.isDebugEnabled()) {
			logger.debug("RESTResultBean:{}", rs);
			logger.debug("Exit MS2A1Controller#m90101011POST()!");
		}

		return rs;
	}
}
