package com.aek56.yw.md.MD6;

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
import com.aek56.atm.master.MD6_CPXX.MD6_CPXXService;
import com.aek56.atm.master.MDC_PPXX.MDC_PPXXDBO;

/**
 * 产品信息管理
 * @ClassName: MD6Controller 
 * @author: Gaoqin
 * @date: 2014-11-30 下午5:55:35
 */
@Controller
public class MD6Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MD6Controller.class);
	
	/**
	 * 产品信息service
	 */
    @Resource
    private MD6_CPXXService cpxxService;
    
    /**
	 * 供应商信息
	 */
	@Resource
	private MD2_GYSXXService gysxxService;
    
    /**
     * 默认首页
     */
    public MyModelAndViewSupport getModelAndView(String strPath){
		return new MyModelAndViewSupport(strPath);
	}
    
    /**
     * 产品一览
     * @param @param pvo
     * @param @param param
     * @param @return
     * @return MyModelAndViewSupport
     * @throws
     */
	@RequestMapping(value = "/39107001", method = RequestMethod.POST)
	public MyModelAndViewSupport m39107001post(PageVOSupport pvo, MD6_CPXXDBO param){
		if(logger.isDebugEnabled()){
			logger.debug("Enter into method MD6Controller#m39107001post!");
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}
		
		MyModelAndViewSupport mv = getModelAndView("yw/md/MD6/MD601");
		super.pageModel.setPageCurrent(pvo.getPageCurrent());
		super.pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		//mv.setViewName("");
		mv.addObject(PAGE, cpxxService.doSelectPage(super.pageModel, false));//分页查询
		mv.addObject(SEARCH, param);//搜索条件
		
		logger.info("END MD6controller.39107001 POST");
		if(logger.isDebugEnabled()){
			logger.debug("Exit method MD6controller#m39107001post!");
		}
		
		return mv;
	}

	/**
	 * 跳转到新增或编辑产品页面
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/39107002", method = RequestMethod.POST)
	public MyModelAndViewSupport m39107002post(MD6_CPXXDBO param){
		MyModelAndViewSupport mv = getModelAndView("yw/md/MD6/MD602");
		if(EmptyHelper.isEmpty(param.getPuk())){
			mv.addObject(USER_DATA, new MD6_CPXXDBO());
		}else{
			FrameworkDataBean result = cpxxService.doRead(param);
			mv.addObject(USER_DATA, result);
		}
		return mv;
	}
	
	/**
	 * 新增或编辑产品
	 * @param @param param
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/39107003", method = RequestMethod.POST)
	public MyModelAndViewSupport m39107003post(MD6_CPXXDBO param){
		if(logger.isDebugEnabled()){
			logger.debug("param:{}", param);
		}
		
		/*String strMsg = "添加产品失败!";
		int iResult = cpxxService.doInsert(param);
		if(iResult == 1){
			strMsg = "添加产品成功!";
		}*/
		
		String strMsg = "";
		int iResult = 1;
		//拼音码
		param.setF02_cpqcpym(StringHelper.getPinYinSample(param.getF01_cpqc()));
		//新增
		if (EmptyHelper.isEmpty(param.getPuk())) {
			iResult = cpxxService.doInsert(param);
		}else{
			iResult = cpxxService.doUpdate(param);
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("Exit method MD6Controller#m39107003post!");
			logger.debug("result:{}", iResult);
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON,
				strMsg, "/39107001");
	}
	
	/**
	 * 获取产品信息，跳转到编辑页面
	 * @param @param param
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 *//*
	@RequestMapping(value = "/39107004",method = RequestMethod.POST)
	public MyModelAndViewSupport m39107004post(MD6_CPXXDBO param){
		if(logger.isDebugEnabled()){
			logger.debug("Enter into method MD6Controller#m39107004post!");
			logger.debug("param:{}", param);
		}
		
		MyModelAndViewSupport mv = getModelAndView("yw/md/MDC/MDC02");
		FrameworkDataBean result = cpxxService.doRead(param);
		mv.addObject(USER_DATA, result);
		if(logger.isDebugEnabled()){
			logger.debug("Exit method MD6Controller#m39107004post!");
		}
		
		return mv;
	}
	
	*//**
	 * 更新产品信息
	 * @param @param param
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 *//*
	@RequestMapping(value="/39107005", method = RequestMethod.POST)
	public MyModelAndViewSupport m39107005post(MD6_CPXXDBO param){
		if(logger.isDebugEnabled()){
			logger.debug("param:{}", param);
		}
		
		int iResult = cpxxService.doUpdate(param);
		String strMsg = "编辑产品失败!";
		if (iResult == 1){
			strMsg = "编辑产品成功!";
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Exit method MDCConstants#m39107005post!");
			logger.debug("result:{}", iResult);
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON,
				strMsg, "/39107001");
	}*/
	
	/**
	 * 删除产品信息
	 * @param @param param
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/39107006", method = RequestMethod.POST)
	public MyModelAndViewSupport m39107006post(MD6_CPXXDBO param) {
		if(logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}
		
		int iResult = cpxxService.toDelete(param);
		String strMsg = "删除产品失败!";
		if (iResult == 1){
			strMsg = "删除产品成功!";
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Exit method MD6Controller#m39107006post!");
			logger.debug("result:{}", iResult);
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON,
				strMsg, "/39107001");
	}
	
	/**
	 * 跳转到供货商选择页面
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 *//*
	@RequestMapping(value = "/39107007", method = RequestMethod.POST)
	public MyModelAndViewSupport m39107007post(){
		MyModelAndViewSupport mv = getModelAndView("yw/md/MD6/MD603");
		return mv;
	}*/
	
	/**
	 * 跳转到规格选择页面
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/39107008", method = RequestMethod.POST)
	public MyModelAndViewSupport m39107008post(){
		MyModelAndViewSupport mv = getModelAndView("yw/md/MD6/MD604");
		return mv;
	}
	
	/**
	 * 检索供应商
	 * @param @param pvo
	 * @param @param param
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/39107009", method = RequestMethod.POST)
	public MyModelAndViewSupport m39107009post(PageVOSupport pvo, MD2_GYSXXDBO param){
		if(logger.isDebugEnabled()){
			logger.debug("Enter into method MD6Controller#m39107009post!");
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}
		
		MyModelAndViewSupport mv = getModelAndView("yw/md/MD6/MD603");
		super.pageModel.setPageCurrent(pvo.getPageCurrent());
		super.pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		mv.addObject(PAGE, gysxxService.doSelectPage(super.pageModel, false));//分页查询
		mv.addObject(SEARCH, param);//搜索条件
		
		logger.info("END MD6controller.39107009 POST");
		if(logger.isDebugEnabled()){
			logger.debug("Exit method MD6controller#m39107009post!");
		}
		
		return mv;
	}
	
	/**
	 * 跳转到添加页面
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/39107007", method = RequestMethod.POST)
	public MyModelAndViewSupport m39107007post(PageVOSupport pvo, MD2_GYSXXDBO param){
		if(logger.isDebugEnabled()){
			logger.debug("Enter into method MD6Controller#m39107007post!");
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}
		
		MyModelAndViewSupport mv = getModelAndView("yw/md/MD6/MD605");
		super.pageModel.setPageCurrent(pvo.getPageCurrent());
		super.pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		mv.addObject(PAGE, gysxxService.doSelectPage(super.pageModel, false));//分页查询
		mv.addObject(SEARCH, param);//搜索条件
		
		logger.info("END MD6controller.39107004 POST");
		if(logger.isDebugEnabled()){
			logger.debug("Exit method MD6controller#m39107004post!");
		}
		
		return mv;
	}
	
	/**
	 * 保存
	 * @param @param param
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/39107004", method = RequestMethod.POST)
	public MyModelAndViewSupport m39107004post(MD2_GYSXXDBO param){
		if(logger.isDebugEnabled()){
			logger.debug("param:{}", param);
		}
		
		/*String strMsg = "添加产品失败!";
		int iResult = cpxxService.doInsert(param);
		if(iResult == 1){
			strMsg = "添加产品成功!";
		}*/
		
		String strMsg = "";
		int iResult = 1;
		//新增
		if (EmptyHelper.isEmpty(param.getPuk())) {
			iResult = cpxxService.doInsert(param);
		}else{
			iResult = cpxxService.doUpdate(param);
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("Exit method MD6Controller#m39107003post!");
			logger.debug("result:{}", iResult);
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON,
				strMsg, "/39107009");
	}
	
}
