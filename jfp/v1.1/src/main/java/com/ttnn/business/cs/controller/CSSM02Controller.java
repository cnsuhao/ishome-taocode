package com.ttnn.business.cs.controller;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSM02Service;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("CSSM02")
/** 内部短信*/
public class CSSM02Controller extends MyControllerSupportImpl {
    @Resource
    protected CSSM02Service CSSM02Service_;

	@Override
	public MyServiceSupportImpl getService() {
		return CSSM02Service_;
	}
	
	@Override	
    public Logger getLogger(){
        return LoggerFactory.getLogger(CSSM02Controller.class);
    }
    
    @Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("CSSM02");
	} 
}
