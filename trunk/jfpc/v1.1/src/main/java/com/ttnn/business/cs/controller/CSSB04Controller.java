package com.ttnn.business.cs.controller;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSB04Service;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("CSSB04")
/** 异常信息对照表*/
public class CSSB04Controller extends MyControllerSupportImpl {
    @Resource
    protected CSSB04Service CSSB04Service_;

	@Override
	public MyServiceSupportImpl getService() {
		return CSSB04Service_;
	}
	
	@Override	
    public Logger getLogger(){
        return LoggerFactory.getLogger(CSSB04Controller.class);
    }
    
    @Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("cs/sb04c1");
	} 
    
    @RequestMapping(value = "/H.go", method = RequestMethod.GET)
    public ModelAndView home(CSPVOSupport paramBean){
    	ModelAndView pageMAV = getModelAndView();
    	getLogger().debug("paramBean===>>>" + paramBean);
    	getLogger().debug("pageMAV===>>>" + pageMAV);
    	return pageMAV;
    }
}
