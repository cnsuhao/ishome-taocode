package com.aek56.yw.sh.CSM0;

import javax.annotation.Resource;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.helper.MessagePageHelper;
import org.jfpc.framework.page.PageVOSupport;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aek56.atm.auditing.CSM0_GYSXX.CSM0_GYSXXDBO;
import com.aek56.atm.auditing.CSM0_GYSXX.CSM0_GYSXXService;
import com.aek56.atm.master.MD2_GYSXX.MD2_GYSXXDBO;
import com.aek56.atm.master.MD2_GYSXX.MD2_GYSXXService;
/**
 * @Description: TODO(供应商审核)
 * @author tangmuming
 * @date 2014-12-1
 * @业务标识 3920100
 */
@Controller
public class CSM0Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory
			.getLogger(CSM0Controller.class);
	
	/**
	 * 供应商主数据
	 */
	@Resource
	private MD2_GYSXXService md2_GYSXXService;
	
	/**
	 * 供应商审核信息
	 */
	@Resource
	private CSM0_GYSXXService csm0_GYSXXService;
	
	/**
	 * 供应商审核，时间段查询服务
	 */
	@Resource
	private CSM0Service csm0Service;
	@Resource
	private CSM0Business  csm0Business;

	public MyModelAndViewSupport getModelAndView(String path) {
		return new MyModelAndViewSupport(path);
	}

	/**
	 * 审核供应商列表
	 * 
	 * @param pvo
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/392010001", method = RequestMethod.POST)
	public MyModelAndViewSupport m392010001post(PageVOSupport pvo, CSM0_GYSXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}
		MyModelAndViewSupport mv = getModelAndView("yw/sh/CSM0/CSM001");
		pageModel.setPageCurrent(pvo.getPageCurrent());
		pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		mv.addObject(PAGE, csm0Service.doSelectPage(pageModel, false));
		mv.addObject(SEARCH, param);
		if (logger.isDebugEnabled()) {
			logger.debug("result:{}", pageModel);
		}
		return mv;
	}
	
	/**
	 * 获取对比信息
	 * 
	 * @param pvo
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/392010002", method = RequestMethod.POST)
	public MyModelAndViewSupport m392010002post(CSM0_GYSXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}	
		MyModelAndViewSupport mv = getModelAndView("yw/sh/CSM0/CSM002");
		//获取 审核数据  新数据
		CSM0_GYSXXDBO newobj = (CSM0_GYSXXDBO)csm0_GYSXXService.doRead(param);
		//获取主数据信息  老数据
		MD2_GYSXXDBO mparam = new MD2_GYSXXDBO();
		mparam.setPuk(newobj.getP01_puk());
		FrameworkDataBean oldobj = md2_GYSXXService.doRead(mparam);
		
		mv.addObject("newobj",newobj);
		mv.addObject("oldobj",oldobj);
		if (logger.isDebugEnabled()) {
			logger.debug("newobj:{},oldobj{}", newobj,oldobj);
		}
		return mv;
	}
	
	/**
	 * 审核结果
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/392010003", method = RequestMethod.POST)
	public MyModelAndViewSupport m392010003post(CSM0_GYSXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}	
		String message = "成功!";
		try {
			csm0Business.check(param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			message="失败!";
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON, message, "/392010001");
	}
}
