package com.ttnn.business.wm.controller.zk;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.biz.WMZKJGService;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;

@Controller
@RequestMapping("WMJG02")
/** 费率管理对账单*/
public class WMJG02Controller extends MyControllerSupportImpl {
	
	@Resource
	protected WMZKJGService WMZKJGService_;

	public WMZKJGService getService() {
		return WMZKJGService_;
	}

	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMJG02Controller.class);
	}

	@Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("WM/ZK/WMJGF2");
	}

	/**
	 * 数据一览
	 */
	@Override
	@RequestMapping(value = "/F.go")
	public ModelAndView doFindList(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		paramBean.setEb5(super.getProductId());
		pageVO.setPageData(paramBean);
		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		getService().doSelectPageFL(pageVO);
		pageMAV.addObject(pageVO);
		pageMAV.setViewName("WM/ZK/WMJGF1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 通道对账
	 * 
	 * @param paramBean
	 * @return
	 */
	@RequestMapping(value = "/A.go", method = RequestMethod.POST)
	public ModelAndView doUpdate1(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageVO.setPageData(paramBean);
		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		getService().doSelectPageDZ(pageVO);
		pageMAV.addObject(pageVO);
		pageMAV.setViewName("WM/ZK/WMJGF2");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

}
