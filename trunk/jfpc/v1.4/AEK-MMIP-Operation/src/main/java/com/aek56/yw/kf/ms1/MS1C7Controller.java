package com.aek56.yw.kf.ms1;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.jfpc.beans.common.MS1C7.MS1C7DBO;
import org.jfpc.beans.common.MS1C7.MS1C7Service;
import org.jfpc.common.menu.MenuBean;
import org.jfpc.common.menu.MenuService;
import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MS1C7Controller extends MyControllerSupport{

	private static final Logger logger = LoggerFactory.getLogger(MS1C7Controller.class);
	@Resource
	private MS1C7Service MS1C7Service_;
	
	/**
	 * 页面视图
	 */
	public MyModelAndViewSupport getModelAndView(String pageId) {
		return new MyModelAndViewSupport(pageId);
	}
	
	/**
	 * 后台使用,用户组权限一览
	 */
	@RequestMapping(value = "/90102021", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m90102021POST(String id) {
		RESTResultBean rs = new RESTResultBean();
		if (logger.isDebugEnabled()) {
			logger.debug("Enter into MS1C7Controller#m90102021POST()!");
		}
		
		MS1C7DBO rool = new MS1C7DBO();
		rool.setK01_qyid("SYSTEM");
		rool.setK02_jsid(id);
		List<FrameworkDataBean> rools = MS1C7Service_.doSelectPage(rool, false);
		List<String> menusr = new ArrayList<String> (rools.size());
		for(FrameworkDataBean fd : rools)
			menusr.add(fd.getPuk());
		// [{"id":111, "pId":11, "name":"test111"，checked：true}]
		List<MS1C7PVO> tree = new ArrayList<MS1C7PVO>(MenuService_.loadMenu().size());
		MS1C7PVO t ;
		for(MenuBean m : MenuService_.loadMenu()){
			t= new MS1C7PVO();
			t.setId(m.getPuk());
			t.setpId(m.getK01_xsfcdid());
			t.setName(m.getF02_cdmc());
			if(menusr.contains(m.getPuk())){
				t.setChecked(true);
			}
			
			tree.add(t);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("pageModel:{}", rs);
			logger.debug("Exit MS1C7Controller#m90102021POST()!");
		}

		rs.setResult(tree);
		return rs;
	}

	@Resource
	MenuService MenuService_;
	
	/**
	 * 后台使用,用户组权限添加
	 */
	@RequestMapping(value = "/90102022", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m90102022POST(String id,String rool) {
		RESTResultBean rs = new RESTResultBean();
		if (logger.isDebugEnabled()) {
			logger.debug("Enter into MS1C7Controller#m90102011POST()!.rool="+rool);
		}
		MS1C7DBO userGroupRool = new MS1C7DBO();
		userGroupRool.setK02_jsid(id);
		MS1C7Service_.doDelete(userGroupRool);
		
		for(String r : rool.split(SEMICOLON)){
			userGroupRool = new MS1C7DBO();
			userGroupRool.setPuk(r);
			userGroupRool.setK01_qyid("SYSTEM");
			userGroupRool.setK02_jsid(id);
			MS1C7Service_.doInsert(userGroupRool);
		}
		//rs.setResult(MS1C7Service_.doInsert(userGroup));

		if (logger.isDebugEnabled()) {
			logger.debug("RESTResultBean:{}", rs);
			logger.debug("Exit MS1C7Controller#m90102011POST()!");
		}

		return rs;
	}
}
