package com.ttnn.business.wm.controller.ht;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSS01Service;
import com.ttnn.business.wm.biz.WMMainTotalBusiness;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMGL")
/** 首页*/
public class WMGLController extends MyControllerSupportImpl {
    @Resource
    protected CSSS01Service CSSS01Service_;
    
    @Resource
    protected WMMainTotalBusiness  wmss01service;

    @Override
    public MyServiceSupportImpl getService(){
        return CSSS01Service_;
    }

    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMGLController.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("WM/HT/WMGL");
    }
    
    @Override
    @RequestMapping(value = "/H.go", method = RequestMethod.POST)
    public ModelAndView home(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageMAV.setViewName("WM/HT/WMGL");		
		paramBean.setPuk(super.getProductId());
		paramBean.setDdd("0");
		FrameworkDataBean syinfo = wmss01service.doTotlaHT(paramBean);
		
		
		FrameworkDataBean data = CSSS01Service_.doRead(paramBean);
		pageMAV.addObject("syinfo", syinfo);
		pageMAV.addObject("systeminfo", data);
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

    
}
