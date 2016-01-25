package com.aek56.yw.md.MDF;

import javax.annotation.Resource;

import org.jfpc.framework.page.PageVOSupport;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aek56.atm.master.MDF_HZXX.MDF_HZXXDBO;
import com.aek56.atm.master.MDF_HZXX.MDF_HZXXService;

/**
 * @ClassName: MDFController
 * @Description: TODO(主数据-患者信息)
 * @author tangmuming
 * @date 2014-12-1
 * @业务标识 3911600
 */
@Controller
public class MDFController extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MDFController.class);
	/**
	 * 患者信息
	 */
	@Resource
	private MDF_HZXXService hzxxService;

	public MyModelAndViewSupport getModelAndView(String path) {
		return new MyModelAndViewSupport(path);
	}

	/**
	 * 患者列表
	 * 
	 * @param pvo
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/391160001", method = RequestMethod.POST)
	public MyModelAndViewSupport m391160001post(PageVOSupport pvo, MDF_HZXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}
		MyModelAndViewSupport mv = getModelAndView("yw/md/MDF/MDF01");
		pageModel.setPageCurrent(pvo.getPageCurrent());
		pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		mv.addObject(PAGE, hzxxService.doSelectPage(pageModel, false));
		mv.addObject(SEARCH, param);
		if (logger.isDebugEnabled()) {
			logger.debug("result:{}", pageModel);
		}
		return mv;
	}
}
