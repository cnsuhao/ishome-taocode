package com.aek56.yw.md.MDE;

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

import com.aek56.atm.master.MDE_YSXX.MDE_YSXXDBO;
import com.aek56.atm.master.MDE_YSXX.MDE_YSXXService;

/**
 * @ClassName: MDEController
 * @Description: TODO(主数据-医生信息)
 * @author tangmuming
 * @date 2014-12-1
 * @业务标识 3911500
 */
@Controller
public class MDEController extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MDEController.class);
	/**
	 * 医生信息
	 */
	@Resource
	private MDE_YSXXService ysxxService;

	public MyModelAndViewSupport getModelAndView(String path) {
		return new MyModelAndViewSupport(path);
	}

	/**
	 * 医生列表
	 * 
	 * @param pvo
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/391150001", method = RequestMethod.POST)
	public MyModelAndViewSupport m391150001post(PageVOSupport pvo, MDE_YSXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}
		MyModelAndViewSupport mv = getModelAndView("yw/md/MDE/MDE01");
		pageModel.setPageCurrent(pvo.getPageCurrent());
		pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		mv.addObject(PAGE, ysxxService.doSelectPage(pageModel, false));
		mv.addObject(SEARCH, param);
		if (logger.isDebugEnabled()) {
			logger.debug("result:{}", pageModel);
		}
		return mv;
	}
}
