package com.aek56.yw.sh.CSM2;

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

import com.aek56.atm.auditing.CSM2_CSXX.CSM2_CSXXDBO;
import com.aek56.atm.auditing.CSM2_CSXX.CSM2_CSXXService;
import com.aek56.atm.master.MD4_CSXX.MD4_CSXXDBO;
import com.aek56.atm.master.MD4_CSXX.MD4_CSXXService;
/**
 * @Description: TODO(厂商审核)
 * @author tangmuming
 * @date 2014-12-1
 * @业务标识 3920300
 */
@Controller
public class CSM2Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory
			.getLogger(CSM2Controller.class);
	
	/**
	 * 厂商主数据
	 */
	@Resource
	private MD4_CSXXService md4_CSXXService;
	
	/**
	 * 厂商审核信息
	 */
	@Resource
	private CSM2_CSXXService csm2_CSXXService;
	
	/**
	 * 厂商审核，时间段查询服务
	 */
	@Resource
	private CSM2Service csm2Service;
	@Resource
	private CSM2Business  csm2Business;

	public MyModelAndViewSupport getModelAndView(String path) {
		return new MyModelAndViewSupport(path);
	}

	/**
	 * 审核厂商列表
	 * 
	 * @param pvo
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/392030001", method = RequestMethod.POST)
	public MyModelAndViewSupport m392030001post(PageVOSupport pvo, CSM2_CSXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}
		MyModelAndViewSupport mv = getModelAndView("yw/sh/CSM2/CSM201");
		pageModel.setPageCurrent(pvo.getPageCurrent());
		pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		mv.addObject(PAGE, csm2Service.doSelectPage(pageModel, false));
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
	@RequestMapping(value = "/392030002", method = RequestMethod.POST)
	public MyModelAndViewSupport m392030002post(CSM2_CSXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}	
		MyModelAndViewSupport mv = getModelAndView("yw/sh/CSM2/CSM202");
		//获取 审核数据  新数据
		CSM2_CSXXDBO newobj = (CSM2_CSXXDBO)csm2_CSXXService.doRead(param);
		//获取主数据信息  老数据
		MD4_CSXXDBO mparam = new MD4_CSXXDBO();
		mparam.setPuk(newobj.getP01_puk());
		FrameworkDataBean oldobj = md4_CSXXService.doRead(mparam);
		
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
	@RequestMapping(value = "/392030003", method = RequestMethod.POST)
	public MyModelAndViewSupport m392030003post(CSM2_CSXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}	
		String message = "成功!";
		try {
			csm2Business.check(param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			message="失败!";
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON, message, "/392030001");
	}
}
