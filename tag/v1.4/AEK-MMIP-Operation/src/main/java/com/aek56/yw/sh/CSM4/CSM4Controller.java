package com.aek56.yw.sh.CSM4;

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
import com.aek56.atm.auditing.CSM4_CPXX.CSM4_CPXXDBO;
import com.aek56.atm.auditing.CSM4_CPXX.CSM4_CPXXService;
import com.aek56.atm.auditing.CSMA_PPXX.CSMA_PPXXDBO;
import com.aek56.atm.auditing.CSMA_PPXX.CSMA_PPXXService;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXDBO;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXService;
import com.aek56.atm.master.MD6_CPXX.MD6_CPXXDBO;
import com.aek56.atm.master.MD6_CPXX.MD6_CPXXService;
import com.aek56.atm.master.MDC_PPXX.MDC_PPXXDBO;
import com.aek56.atm.master.MDC_PPXX.MDC_PPXXService;


/**
 * 产品审核
 * @ClassName: CSM4Controller 
 * @author: Gaoqin
 * @date: 2014-12-2 下午8:41:42
 */
@Controller
public class CSM4Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(CSM4Controller.class);
	
	/**
	 * 产品主数据
	 */
	@Resource
	private MD6_CPXXService md6_CPXXService;
	
	/**
	 * 产品审核信息
	 */
	@Resource
	private CSM4_CPXXService csm4_CPXXService;
	
	/**
	 * 品牌审核，时间段查询服务
	 */
	@Resource
	private CSM4Service csm4Service;
	@Resource
	private CSM4Business csm4Business;

	public MyModelAndViewSupport getModelAndView(String strPath) {
		return new MyModelAndViewSupport(strPath);
	}

	/**
	 * 审核产品列表
	 * 
	 * @param pvo
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/39205001", method = RequestMethod.POST)
	public MyModelAndViewSupport m39211001post(PageVOSupport pvo, CSM4_CPXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}
		
		MyModelAndViewSupport mv = getModelAndView("yw/sh/CSM4/CSM401");
		pageModel.setPageCurrent(pvo.getPageCurrent());
		pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		mv.addObject(PAGE, csm4_CPXXService.doSelectPage(pageModel, false));
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
	@RequestMapping(value = "/39205002", method = RequestMethod.POST)
	public MyModelAndViewSupport m39205002post(CSM4_CPXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}
		
		MyModelAndViewSupport mv = getModelAndView("yw/sh/CSM4/CSM402");
		//获取 审核数据  新数据
		CSM4_CPXXDBO newobj = (CSM4_CPXXDBO)csm4_CPXXService.doRead(param);
		//获取主数据信息  老数据
		MD6_CPXXDBO mparam = new MD6_CPXXDBO();
		mparam.setPuk(newobj.getP01_puk());
		FrameworkDataBean oldobj = md6_CPXXService.doRead(mparam);
		
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
	@RequestMapping(value = "/39205003", method = RequestMethod.POST)
	public MyModelAndViewSupport m39205003post(CSM4_CPXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}	
		String strMessage = "成功!";
		try {
			csm4Business.check(param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			strMessage="失败!";
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON, strMessage, "/39205001");
	}
}
