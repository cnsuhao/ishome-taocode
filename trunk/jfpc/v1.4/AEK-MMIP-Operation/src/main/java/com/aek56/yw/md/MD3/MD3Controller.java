package com.aek56.yw.md.MD3;

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

import com.aek56.atm.master.MD3_YYXX.MD3_YYXXDBO;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXService;

/**
 * @ClassName: MD3Controller
 * @Description: TODO(主数据-医院信息)
 * @author tangmuming
 * @date 2014-11-1
 * @业务标识 3910300
 */
@Controller
public class MD3Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MD3Controller.class);
	/**
	 * 医院信息
	 */
	@Resource
	private MD3_YYXXService yyxxService;

	public MyModelAndViewSupport getModelAndView(String path) {
		return new MyModelAndViewSupport(path);
	}

	/**
	 * 医院列表
	 * 
	 * @param pvo
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/391030001", method = RequestMethod.POST)
	public MyModelAndViewSupport m391030001post(PageVOSupport pvo, MD3_YYXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}
		MyModelAndViewSupport mv = getModelAndView("yw/md/MD3/MD301");
		pageModel.setPageCurrent(pvo.getPageCurrent());
		pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		mv.addObject(PAGE, yyxxService.doSelectPage(pageModel, false));
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
	@RequestMapping(value = "/391030002", method = RequestMethod.POST)
	public MyModelAndViewSupport m391030002post(MD3_YYXXDBO param) {
		MyModelAndViewSupport mv = getModelAndView("yw/md/MD3/MD302");
		if (EmptyHelper.isEmpty(param.getPuk())) {
			mv.addObject(USER_DATA, new MD3_YYXXDBO());
		} else {
			FrameworkDataBean result = yyxxService.doRead(param);
			mv.addObject(USER_DATA, result);
		}
		return mv;
	}

	/**
	 * 新增或编辑医院
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/391030003", method = RequestMethod.POST)
	public MyModelAndViewSupport m391030003post(MD3_YYXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}
		String msg = "";
		int flag = 0;
		//拼音码
		param.setF02_qcpym(StringHelper.getPinYinSample(param.getF01_qyqc()));
		//新增
		if (EmptyHelper.isEmpty(param.getPuk())) {
			flag = yyxxService.doInsert(param);
		}else{
			flag = yyxxService.doUpdate(param);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("result:{}", flag);
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON, msg, "/391030001");
	}

	/**
	 * 删除医院信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/391030004", method = RequestMethod.POST)
	public MyModelAndViewSupport m391030006post(MD3_YYXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}
		int result = yyxxService.toDelete(param);
		String msg = "删除医院失败!";
		if (result == 1)
			msg = "删除医院成功!";
		if (logger.isDebugEnabled()) {
			logger.debug("result:{}", result);
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON, msg, "/391030001");
	}
}
