package com.ttnn.business.wm.controller.zk;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.biz.WMZKJGService;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;

@Controller
@RequestMapping("WMJG03")
/** 充值记录管理*/
public class WMJG03Controller extends MyControllerSupportImpl {
	@Resource
	protected WMZKJGService WMZKJGService_;// 充值

	public WMZKJGService getService() {
		return WMZKJGService_;
	}

	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMJG03Controller.class);
	}

	@Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("WM/ZK/WMJGJ1");
	}

	@Override
	@RequestMapping(value = "/H.go")
	public ModelAndView home(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageMAV.setViewName("WM/ZK/WMJGJ1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
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
		getService().doSelectPageCZ(pageVO);
		pageMAV.addObject(pageVO);
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
}
