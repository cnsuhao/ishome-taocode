package com.aek56.qt.common;

import javax.annotation.Resource;

import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aek56.atm.company.MQC_PZZX.MQC_PZZXDBO;
import com.aek56.atm.company.MQC_PZZX.MQC_PZZXService;

/**
 * 系统设置页面
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/5/30
 */
@Controller
public class CFGController extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(CFGController.class);

	@Resource
	MQC_PZZXService MQC_PZZXService_;
	
	@RequestMapping(value = "/3000100", method = RequestMethod.POST)
	public MyModelAndViewSupport m3000100POST(String index) {
		if(logger.isDebugEnabled())
			logger.debug(index);
		MyModelAndViewSupport mv = new MyModelAndViewSupport("qt/CFG/CFG01");
		mv.addObject("index", index);
		MQC_PZZXDBO dbo = new MQC_PZZXDBO();
		dbo.setPuk(super.getCompanyId());
		dbo = (MQC_PZZXDBO) MQC_PZZXService_.doRead(dbo);
		if(dbo==null){
			dbo = new MQC_PZZXDBO();
			dbo.setPuk(super.getCompanyId());
			MQC_PZZXService_.doInsert(dbo);
		}
		mv.addObject(USER_DATA, dbo);
		if(logger.isDebugEnabled())
			logger.debug(dbo.toString());
		return mv;
	}
	
	@RequestMapping(value = "/30001001", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m30001001POST(MQC_PZZXDBO paramBean) {
		if(logger.isDebugEnabled())
			logger.debug(paramBean.toString());
		RESTResultBean rb = new RESTResultBean();
		paramBean.setPuk(super.getCompanyId());
		rb.setResult(MQC_PZZXService_.doUpdate(paramBean));
		if(logger.isDebugEnabled())
			logger.debug(paramBean.toString());
		return rb;
	}
}
