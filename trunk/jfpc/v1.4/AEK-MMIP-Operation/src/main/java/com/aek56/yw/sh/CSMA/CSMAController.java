package com.aek56.yw.sh.CSMA;

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
import com.aek56.atm.auditing.CSMA_PPXX.CSMA_PPXXDBO;
import com.aek56.atm.auditing.CSMA_PPXX.CSMA_PPXXService;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXDBO;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXService;
import com.aek56.atm.master.MDC_PPXX.MDC_PPXXDBO;
import com.aek56.atm.master.MDC_PPXX.MDC_PPXXService;


/**
 * 品牌审核
 * @ClassName: CSMAController 
 * @author: Gaoqin
 * @date: 2014-12-2 下午8:40:56
 */
@Controller
public class CSMAController extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(CSMAController.class);
	
	/**
	 * 品牌主数据
	 */
	@Resource
	private MDC_PPXXService mdc_PPXXService;
	
	/**
	 * 品牌审核信息
	 */
	@Resource
	private CSMA_PPXXService csma_PPXXService;
	
	/**
	 * 品牌审核，时间段查询服务
	 */
	@Resource
	private CSMAService csmaService;
	@Resource
	private CSMABusiness csmaBusiness;

	public MyModelAndViewSupport getModelAndView(String strPath) {
		return new MyModelAndViewSupport(strPath);
	}

	/**
	 * 审核品牌列表
	 * 
	 * @param pvo
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/39211001", method = RequestMethod.POST)
	public MyModelAndViewSupport m39211001post(PageVOSupport pvo, CSMA_PPXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}
		
		MyModelAndViewSupport mv = getModelAndView("yw/sh/CSMA/CSMA01");
		pageModel.setPageCurrent(pvo.getPageCurrent());
		pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		mv.addObject(PAGE, csmaService.doSelectPage(pageModel, false));
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
	@RequestMapping(value = "/39211002", method = RequestMethod.POST)
	public MyModelAndViewSupport m39211002post(CSMA_PPXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}
		
		MyModelAndViewSupport mv = getModelAndView("yw/sh/CSMA/CSMA02");
		//获取 审核数据  新数据
		CSMA_PPXXDBO newobj = (CSMA_PPXXDBO)csma_PPXXService.doRead(param);
		//获取主数据信息  老数据
		MDC_PPXXDBO mparam = new MDC_PPXXDBO();
		mparam.setPuk(newobj.getP01_puk());
		FrameworkDataBean oldobj = mdc_PPXXService.doRead(mparam);
		
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
	@RequestMapping(value = "/39211003", method = RequestMethod.POST)
	public MyModelAndViewSupport m39211003post(CSMA_PPXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}	
		String strMessage = "成功!";
		try {
			csmaBusiness.check(param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			strMessage="失败!";
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON, strMessage, "/39211003");
	}
}
