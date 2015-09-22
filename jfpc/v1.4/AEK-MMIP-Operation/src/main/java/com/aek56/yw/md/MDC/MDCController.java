package com.aek56.yw.md.MDC;

import javax.annotation.Resource;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.MessagePageHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.page.PageVOSupport;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aek56.atm.master.MD2_GYSXX.MD2_GYSXXDBO;
import com.aek56.atm.master.MD2_GYSXX.MD2_GYSXXService;
import com.aek56.atm.master.MD6_CPXX.MD6_CPXXDBO;
import com.aek56.atm.master.MDC_PPXX.MDC_PPXXDBO;
import com.aek56.atm.master.MDC_PPXX.MDC_PPXXService;
import com.aek56.atm.master.MDD_PPGYSXX.MDD_PPGYSXXDBO;
import com.aek56.atm.master.MDD_PPGYSXX.MDD_PPGYSXXService;

/**
 * 品牌信息管理
 * @ClassName: MDCController 
 * @author: Gaoqin
 * @date: 2014-11-29 下午6:49:39
 */
@Controller
public class MDCController extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MDCController.class);

	/**
	 * 品牌信息service
	 */
	@Resource
	private MDC_PPXXService ppxxService;
	
	/**
	 * 品牌供应商信息
	 */
	@Resource
	private MDD_PPGYSXXService ppgysxxService;

	/**
	 * 供应商信息
	 */
	@Resource
	private MD2_GYSXXService gysxxService;
	
	/**
	 * 注入
	 */
	@Resource
	private MDCBussiness mdcBussiness;
	
	/**
	 * 默认首页
	 */
	public MyModelAndViewSupport getModelAndView(String strPath) {
		return new MyModelAndViewSupport(strPath);
	}

	/**
	 * 品牌一览列表
	 * @param @param pvo
	 * @param @param param
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/39113001", method = RequestMethod.POST)
	public MyModelAndViewSupport m39113001post(PageVOSupport pvo, MDC_PPXXDBO param){
		if(logger.isDebugEnabled()){
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}

		MyModelAndViewSupport mv = getModelAndView("yw/md/MDC/MDC01");
		super.pageModel.setPageCurrent(pvo.getPageCurrent());
		super.pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		mv.addObject(PAGE, ppxxService.doSelectPage(super.pageModel, false));//分页查询
		mv.addObject(SEARCH, param);//搜索条件

		logger.info("END CMACcontroller.39113001 POST");
		if(logger.isDebugEnabled()){
			logger.debug("Exit method MDCController#m39113001post!");
		}

		return mv;
	}

	/**
	 * 跳转到新增或编辑品牌页面
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/39113002", method = RequestMethod.POST)
	public MyModelAndViewSupport m39107002post(MDC_PPXXDBO param){
		MyModelAndViewSupport mv = getModelAndView("yw/md/MDC/MDC02");
		if(EmptyHelper.isEmpty(param.getPuk())){
			mv.addObject(USER_DATA, new MDC_PPXXDBO());
		}else{
			FrameworkDataBean result = ppxxService.doRead(param);
			mv.addObject(USER_DATA, result);
		}
		return mv;
	}

	/**
	 * 新增或编辑品牌
	 * @param @param param
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/39113003", method = RequestMethod.POST)
	public MyModelAndViewSupport m39113002post(MDC_PPXXDBO param){
		if(logger.isDebugEnabled()){
			logger.debug("param:{}", param);
		}

		String strMsg = "";
		int iResult = 1;
		//拼音码 F02_PPQCPYM
		param.setF02_ppqcpym(StringHelper.getPinYinSample(param.getF01_ppqc()));
		//新增
		if (EmptyHelper.isEmpty(param.getPuk())) {
			iResult = ppxxService.doInsert(param);
		}else{
			iResult = ppxxService.doUpdate(param);
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("Exit method MDCController#m39113003post!");
			logger.debug("result:{}", iResult);
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON,
				strMsg, "/39113001");
	}

	/**
	 * 删除品牌信息
	 * @param @param param
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/39113006", method = RequestMethod.POST)
	public MyModelAndViewSupport m39113006post(MDC_PPXXDBO param) {
		if(logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}

		int iResult = ppxxService.toDelete(param);
		String strMsg = "删除品牌失败!";
		if (iResult == 1){
			strMsg = "删除品牌成功!";
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Exit method MDCController#m39113006post()!");
			logger.debug("result:{}", iResult);
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON,
				strMsg, "/39113001");
	}
	
	/**
	 * 根据品牌获取供应商列表
	 * @param @param pvo
	 * @param @param param
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/39114001", method = RequestMethod.POST)
	public MyModelAndViewSupport m39114001post(PageVOSupport pvo, MDC_PPXXDBO param){
		if(logger.isDebugEnabled()){
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}

		MyModelAndViewSupport mv = getModelAndView("yw/md/MDC/MDC05");
		super.pageModel.setPageCurrent(pvo.getPageCurrent());
		super.pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		mv.addObject(PAGE, gysxxService.doSelectPage(super.pageModel, false));//分页查询
		mv.addObject(SEARCH, param);//搜索条件

		logger.info("END CMACcontroller.39114001 POST");
		if(logger.isDebugEnabled()){
			logger.debug("Exit method MDCController#m39114001post!");
		}

		return mv;
	}
	
	/**
	 * 跳转到供应商选择页面
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/39114002", method = RequestMethod.POST)
	public MyModelAndViewSupport m39114002post(PageVOSupport pvo, MD2_GYSXXDBO param){
		if(logger.isDebugEnabled()){
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}

		MyModelAndViewSupport mv = getModelAndView("yw/md/MDC/MDC06");
		super.pageModel.setPageCurrent(pvo.getPageCurrent());
		super.pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		mv.addObject(PAGE, gysxxService.doSelectPage(super.pageModel, false));//分页查询
		mv.addObject(SEARCH, param);//搜索条件

		logger.info("END CMACcontroller.39114002 POST");
		if(logger.isDebugEnabled()){
			logger.debug("Exit method MDCController#m39114002post!");
		}
		
		return mv;
	}
	
	/**
	 * 选择供应商后保存
	 * @param @param param
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/39114003", method = RequestMethod.POST)
	public MyModelAndViewSupport m39114003post(MDD_PPGYSXXDBO param){
		if(logger.isDebugEnabled()){
			logger.debug("param:{}", param);
		}

		String strMsg = "添加失败!";
		int iResult = ppgysxxService.doInsert(param);
		if(iResult == 1){
			strMsg = "添加成功!";
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("Exit method MDCController#m39114003post!");
			logger.debug("result:{}", iResult);
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON,
				strMsg, "/39113001");
	}
	
	//*********************************************
	
	/*

	*//**
	 * 跳转到品牌设置页面
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 *//*
	@RequestMapping(value = "/39113007", method = RequestMethod.POST)
	public MyModelAndViewSupport m39113007post(){
		MyModelAndViewSupport mv = getModelAndView("yw/md/MDC/MDC03");
		return mv;
	}
	
	*//**
	 * 检索供应商
	 * @param @param pvo
	 * @param @param param
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 *//*
	@RequestMapping(value = "/39113008", method = RequestMethod.POST)
	public MyModelAndViewSupport m39113008post(PageVOSupport pvo, MD2_GYSXXDBO param){
		if(logger.isDebugEnabled()){
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}

		MyModelAndViewSupport mv = getModelAndView("yw/md/MDC/MDC03");
		super.pageModel.setPageCurrent(pvo.getPageCurrent());
		super.pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		mv.addObject(PAGE, gysxxService.doSelectPage(super.pageModel, false));//分页查询
		mv.addObject(SEARCH, param);//搜索条件

		logger.info("END CMACcontroller.39113008 POST");
		if(logger.isDebugEnabled()){
			logger.debug("Exit method MDCController#m39113008post!");
		}

		return mv;
	}
	
	*//**
	 * 删除供应商
	 * @param @param param
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 *//*
	@RequestMapping(value = "/39113009", method = RequestMethod.POST)
	public MyModelAndViewSupport m39113009post(MDC_PPXXDBO param) {
		if(logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}

		int iResult = gysxxService.toDelete(param);
		String strMsg = "删除供应商失败!";
		if (iResult == 1){
			strMsg = "删除供应商成功!";
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Exit method MDCController#m39113009post()!");
			logger.debug("result:{}", iResult);
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON,
				strMsg, "/39113007");
	}*/

}
