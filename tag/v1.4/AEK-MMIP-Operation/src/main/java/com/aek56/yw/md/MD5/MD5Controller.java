package com.aek56.yw.md.MD5;

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

import com.aek56.atm.master.MD5_XZJGDW.MD5_XZJGDWDBO;
import com.aek56.atm.master.MD5_XZJGDW.MD5_XZJGDWService;

/**
 * @ClassName: MD5Controller
 * @Description: TODO(主数据-行政机构)
 * @author tangmuming
 * @date 2014-12-1
 * @业务标识 3910600
 */
@Controller
public class MD5Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MD5Controller.class);
	/**
	 * 行政机构信息
	 */
	@Resource
	private MD5_XZJGDWService xzjgdwService;

	public MyModelAndViewSupport getModelAndView(String path) {
		return new MyModelAndViewSupport(path);
	}

	/**
	 * 行政机构列表
	 * 
	 * @param pvo
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/391060001", method = RequestMethod.POST)
	public MyModelAndViewSupport m391060001post(PageVOSupport pvo, MD5_XZJGDWDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}
		MyModelAndViewSupport mv = getModelAndView("yw/md/MD5/MD501");
		pageModel.setPageCurrent(pvo.getPageCurrent());
		pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		mv.addObject(PAGE, xzjgdwService.doSelectPage(pageModel, false));
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
	@RequestMapping(value = "/391060002", method = RequestMethod.POST)
	public MyModelAndViewSupport m391060002post(MD5_XZJGDWDBO param) {
		MyModelAndViewSupport mv = getModelAndView("yw/md/MD5/MD502");
		if (EmptyHelper.isEmpty(param.getPuk())) {
			mv.addObject(USER_DATA, new MD5_XZJGDWDBO());
		} else {
			FrameworkDataBean result = xzjgdwService.doRead(param);
			mv.addObject(USER_DATA, result);
		}
		return mv;
	}

	/**
	 * 新增或编辑行政机构
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/391060003", method = RequestMethod.POST)
	public MyModelAndViewSupport m391060003post(MD5_XZJGDWDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}
		String msg = "";
		int flag = 0;
		//拼音码
		param.setF02_jgqcpym(StringHelper.getPinYinSample(param.getF01_jgqc()));
		//新增
		if (EmptyHelper.isEmpty(param.getPuk())) {
			flag = xzjgdwService.doInsert(param);
		}else{
			flag = xzjgdwService.doUpdate(param);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("result:{}", flag);
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON, msg, "/391060001");
	}

	/**
	 * 删除行政机构信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/391060004", method = RequestMethod.POST)
	public MyModelAndViewSupport m391060006post(MD5_XZJGDWDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}
		int result = xzjgdwService.toDelete(param);
		String msg = "删除行政机构失败!";
		if (result == 1)
			msg = "删除行政机构成功!";
		if (logger.isDebugEnabled()) {
			logger.debug("result:{}", result);
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON, msg, "/391060001");
	}
}
