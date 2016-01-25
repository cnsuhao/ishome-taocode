package com.aek56.yw.sh.CSM3;

import javax.annotation.Resource;

import org.jfpc.framework.helper.MessagePageHelper;
import org.jfpc.framework.page.PageVOSupport;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aek56.atm.auditing.CSM3_XZJGDW.CSM3_XZJGDWDBO;
import com.aek56.atm.auditing.CSM3_XZJGDW.CSM3_XZJGDWService;
import com.aek56.atm.master.MD5_XZJGDW.MD5_XZJGDWService;
/**
 * @Description: TODO(行政机关单位审核)
 * @author tangmuming
 * @date 2014-12-2
 * @业务标识 3920400
 */
@Controller
public class CSM3Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory
			.getLogger(CSM3Controller.class);
	
	/**
	 * 行政机关单位主数据
	 */
	@Resource
	private MD5_XZJGDWService md5_XZJGDWService;
	
	/**
	 * 行政机关单位审核信息
	 */
	@Resource
	private CSM3_XZJGDWService csm3_XZJGDWService;
	@Resource
	private CSM3Business csm3Business;

	public MyModelAndViewSupport getModelAndView(String path) {
		return new MyModelAndViewSupport(path);
	}

	/**
	 * 审核行政机关单位列表
	 * 
	 * @param pvo
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/392040001", method = RequestMethod.POST)
	public MyModelAndViewSupport m392040001post(PageVOSupport pvo, CSM3_XZJGDWDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}
		MyModelAndViewSupport mv = getModelAndView("yw/sh/CSM3/CSM301");
		pageModel.setPageCurrent(pvo.getPageCurrent());
		pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		mv.addObject(PAGE, csm3_XZJGDWService.doSelectPage(pageModel, false));
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
	@RequestMapping(value = "/392040002", method = RequestMethod.POST)
	public MyModelAndViewSupport m392040002post(CSM3_XZJGDWDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}	
		MyModelAndViewSupport mv = getModelAndView("yw/sh/CSM3/CSM302");
		//获取 审核数据  新数据
		CSM3_XZJGDWDBO newobj = (CSM3_XZJGDWDBO)csm3_XZJGDWService.doRead(param);
		mv.addObject(USER_DATA,newobj);
		if (logger.isDebugEnabled()) {
			logger.debug("newobj:{}", newobj);
		}
		return mv;
	}
	
	/**
	 * 审核结果
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/392040003", method = RequestMethod.POST)
	public MyModelAndViewSupport m392040003post(CSM3_XZJGDWDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}
		String message = "成功!";
		try {
			csm3Business.check(param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			message="失败!";
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON, message, "/392040001");
	}
}
