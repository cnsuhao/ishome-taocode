package com.aek56.yw.md.MD2;

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

/**
 * @ClassName: MD2Controller
 * @Description: TODO(主数据-供应商信息)
 * @author tangmuming
 * @date 2014-11-29 下午3:35:00
 * @业务标识 3910200
 */
@Controller
public class MD2Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MD2Controller.class);
	/**
	 * 供应商信息
	 */
	@Resource
	private MD2_GYSXXService gysxxService;

	public MyModelAndViewSupport getModelAndView(String path) {
		return new MyModelAndViewSupport(path);
	}

	/**
	 * 供应商列表
	 * 
	 * @param pvo
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/391020001", method = RequestMethod.POST)
	public MyModelAndViewSupport m391020001post(PageVOSupport pvo, MD2_GYSXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}
		MyModelAndViewSupport mv = getModelAndView("yw/md/MD2/MD201");
		pageModel.setPageCurrent(pvo.getPageCurrent());
		pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		mv.addObject(PAGE, gysxxService.doSelectPage(pageModel, false));
		mv.addObject(SEARCH, param);
		if (logger.isDebugEnabled()) {
			logger.debug("result:{}", pageModel);
		}
		return mv;
	}

	/**
	 * 跳转到新增或编辑
	 * 
	 * @return
	 */
	@RequestMapping(value = "/391020002", method = RequestMethod.POST)
	public MyModelAndViewSupport m391020002post(MD2_GYSXXDBO param) {
		MyModelAndViewSupport mv = getModelAndView("yw/md/MD2/MD202");
		if (EmptyHelper.isEmpty(param.getPuk())) {
			mv.addObject(USER_DATA, new MD2_GYSXXDBO());
		} else {
			FrameworkDataBean result = gysxxService.doRead(param);
			mv.addObject(USER_DATA, result);
		}
		return mv;
	}

	/**
	 * 新增或编辑供应商
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/391020003", method = RequestMethod.POST)
	public MyModelAndViewSupport m391020003post(MD2_GYSXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}
		String msg = "";
		int flag = 0;
		//拼音码
		param.setF02_qcpym(StringHelper.getPinYinSample(param.getF01_qyqc()));
		//新增
		if (EmptyHelper.isEmpty(param.getPuk())) {
			flag = gysxxService.doInsert(param);
		}else{
			flag = gysxxService.doUpdate(param);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("result:{}", flag);
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON, msg, "/391020001");
	}

	/**
	 * 删除供应商信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/391020004", method = RequestMethod.POST)
	public MyModelAndViewSupport m391020006post(MD2_GYSXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}
		int result = gysxxService.toDelete(param);
		String msg = "删除供应商失败!";
		if (result == 1)
			msg = "删除供应商成功!";
		if (logger.isDebugEnabled()) {
			logger.debug("result:{}", result);
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON, msg, "/391020001");
	}
}
