package com.ttnn.business.wm.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSR01Service;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/**
 * 贷付宝首页页面
 * @deprecated
 */
@Controller
@RequestMapping("INDEX")
public class WMIndexController extends MyControllerSupportImpl {

	@Resource
    protected CSSR01Service CSSR01Service_;

    @Override
    public MyServiceSupportImpl getService(){
        return CSSR01Service_;
    }

    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMIndexController.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("WM/LOGIN");
    }   
    
	/**
	 * 顶部页面
	 */
	@RequestMapping(value = "/TOP.go", method = RequestMethod.GET)
	public ModelAndView top(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		//TODO
		pageMAV.setViewName("WM/TOP");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	/**
	 * 中间页面
	 */
	@RequestMapping(value = "/BLANK.go", method = RequestMethod.GET)
	public ModelAndView blank(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		//TODO
		pageMAV.setViewName("WM/BLANK");		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 中间页面
	 */
	@RequestMapping(value = "/CENTER.go", method = RequestMethod.GET)
	public ModelAndView center(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		//TODO
		pageMAV.setViewName("WM/CENTER");		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 业务页面
	 */
	@RequestMapping(value = "/MAIN.go", method = RequestMethod.GET)
	public ModelAndView main(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		//TODO
		pageMAV.setViewName("WM/MAIN");		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 左侧菜单
	 */
	@RequestMapping(value = "/LEFT.go", method = RequestMethod.GET)
	public ModelAndView left(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		getService().doCheckLogin();
		
		//TODO
		pageMAV.setViewName("WM/LEFT");		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 页脚
	 */
	@RequestMapping(value = "/FOOT.go", method = RequestMethod.GET)
	public ModelAndView foot(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		getService().doCheckLogin();
		
		//TODO
		pageMAV.setViewName("WM/FOOT");	
		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
}
