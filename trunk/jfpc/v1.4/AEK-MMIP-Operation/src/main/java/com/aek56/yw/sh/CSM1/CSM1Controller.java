package com.aek56.yw.sh.CSM1;

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

import com.aek56.atm.auditing.CSM1_YYXX.CSM1_YYXXDBO;
import com.aek56.atm.auditing.CSM1_YYXX.CSM1_YYXXService;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXDBO;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXService;


/**
 * @Description: TODO(医院审核)
 * @author tangmuming
 * @date 2014-12-1
 * @业务标识 3920200
 */
@Controller
public class CSM1Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory
			.getLogger(CSM1Controller.class);
	
	/**
	 * 医院主数据
	 */
	@Resource
	private MD3_YYXXService md3_YYXXService;
	
	/**
	 * 医院审核信息
	 */
	@Resource
	private CSM1_YYXXService csm1_YYXXService;
	
	/**
	 * 医院审核，时间段查询服务
	 */
	@Resource
	private CSM1Service csm1Service;
	@Resource
	private CSM1Business csm1Business;

	public MyModelAndViewSupport getModelAndView(String path) {
		return new MyModelAndViewSupport(path);
	}

	/**
	 * 审核医院列表
	 * 
	 * @param pvo
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/392020001", method = RequestMethod.POST)
	public MyModelAndViewSupport m392020001post(PageVOSupport pvo, CSM1_YYXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}
		MyModelAndViewSupport mv = getModelAndView("yw/sh/CSM1/CSM101");
		pageModel.setPageCurrent(pvo.getPageCurrent());
		pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		mv.addObject(PAGE, csm1Service.doSelectPage(pageModel, false));
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
	@RequestMapping(value = "/392020002", method = RequestMethod.POST)
	public MyModelAndViewSupport m392020002post(CSM1_YYXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}	
		MyModelAndViewSupport mv = getModelAndView("yw/sh/CSM1/CSM102");
		//获取 审核数据  新数据
		CSM1_YYXXDBO newobj = (CSM1_YYXXDBO)csm1_YYXXService.doRead(param);
		//获取主数据信息  老数据
		MD3_YYXXDBO mparam = new MD3_YYXXDBO();
		mparam.setPuk(newobj.getP01_puk());
		FrameworkDataBean oldobj = md3_YYXXService.doRead(mparam);
		
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
	@RequestMapping(value = "/392020003", method = RequestMethod.POST)
	public MyModelAndViewSupport m392020003post(CSM1_YYXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}	
		String message = "成功!";
		try {
			csm1Business.check(param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			message="失败!";
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON, message, "/392020001");
	}
}
