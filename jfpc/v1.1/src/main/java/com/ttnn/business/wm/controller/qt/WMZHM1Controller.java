package com.ttnn.business.wm.controller.qt;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.service.WMBM01Service;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMZHM1")
/** 我的支付通道*/
public class WMZHM1Controller extends MyControllerSupportImpl {
	@Resource
	protected WMBM01Service WMBM01Service;
	@Resource
	protected WMUI01Service WMUI01Service;
	
	@Override
	public MyServiceSupportImpl getService() {
		return WMBM01Service;
	}

	
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMZHM1Controller.class);
	}

	@Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("WM/QT/WMXXZ2");
	}

	@Override
	@RequestMapping(value = "/H.go")
	public ModelAndView home(CSPVOSupport paramBean) {
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}

	
	/**
	 * 数据一览
	 */

	@RequestMapping(value = "/F.go")
	public ModelAndView doFindList(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		
		
		//获得用户类型
		CSPVOSupport formParamBean = new CSPVOSupport ();
		formParamBean.setPuk(super.getLoginerId());

		pageMAV.addObject("userinfo", WMUI01Service.doRead(formParamBean));		
		
		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		paramBean.setK01(super.getLoginerId());
		pageVO.setPageData(paramBean);
		pageMAV.addObject(pageVO);
		pageMAV.addObject("list", getService().doSelectPage(pageVO).getPageData());
		pageMAV.setViewName("WM/QT/WMZHM1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}



}