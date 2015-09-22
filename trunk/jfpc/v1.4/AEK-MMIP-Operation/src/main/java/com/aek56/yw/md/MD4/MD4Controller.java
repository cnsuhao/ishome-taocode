package com.aek56.yw.md.MD4;

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

import com.aek56.atm.master.MD4_CSXX.MD4_CSXXDBO;
import com.aek56.atm.master.MD4_CSXX.MD4_CSXXService;

/**
 * @ClassName: MD4Controller
 * @Description: TODO(主数据-厂商)
 * @author tangmuming
 * @date 2014-11-1
 * @业务标识 3910400
 */
@Controller
public class MD4Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MD4Controller.class);
	/**
	 * 厂商信息
	 */
	@Resource
	private MD4_CSXXService csxxService;

	public MyModelAndViewSupport getModelAndView(String path) {
		return new MyModelAndViewSupport(path);
	}

	/**
	 * 厂商列表
	 * 
	 * @param pvo
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/391040001", method = RequestMethod.POST)
	public MyModelAndViewSupport m391040001post(PageVOSupport pvo, MD4_CSXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}
		MyModelAndViewSupport mv = getModelAndView("yw/md/MD4/MD401");
		pageModel.setPageCurrent(pvo.getPageCurrent());
		pageModel.setPageLimit(pvo.getPageLimit());
		pageModel.setFormParamBean(param);
		mv.addObject(PAGE, csxxService.doSelectPage(pageModel, false));
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
	@RequestMapping(value = "/391040002", method = RequestMethod.POST)
	public MyModelAndViewSupport m391040002post(MD4_CSXXDBO param) {
		MyModelAndViewSupport mv = getModelAndView("yw/md/MD4/MD402");
		if (EmptyHelper.isEmpty(param.getPuk())) {
			mv.addObject(USER_DATA, new MD4_CSXXDBO());
		} else {
			FrameworkDataBean result = csxxService.doRead(param);
			mv.addObject(USER_DATA, result);
		}
		return mv;
	}

	/**
	 * 新增或编辑厂商
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/391040003", method = RequestMethod.POST)
	public MyModelAndViewSupport m391040003post(MD4_CSXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}
		String msg = "";
		int flag = 0;
		//拼音码
		param.setF02_qcpym(StringHelper.getPinYinSample(param.getF01_qyqc()));
		//新增
		if (EmptyHelper.isEmpty(param.getPuk())) {
			flag = csxxService.doInsert(param);
		}else{
			flag = csxxService.doUpdate(param);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("result:{}", flag);
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON, msg, "/391040001");
	}

	/**
	 * 删除厂商信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/391040004", method = RequestMethod.POST)
	public MyModelAndViewSupport m391040006post(MD4_CSXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}
		int result = csxxService.toDelete(param);
		String msg = "删除厂商失败!";
		if (result == 1)
			msg = "删除厂商成功!";
		if (logger.isDebugEnabled()) {
			logger.debug("result:{}", result);
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_CON, msg, "/391040001");
	}
}
