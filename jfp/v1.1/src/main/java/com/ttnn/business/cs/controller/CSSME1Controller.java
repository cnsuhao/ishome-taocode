package com.ttnn.business.cs.controller;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSME1Service;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("CSSME1")
/** 业务运行日志*/
public class CSSME1Controller extends MyControllerSupportImpl {
    @Resource
    protected CSSME1Service CSSME1Service_;

	@Override
	public MyServiceSupportImpl getService() {
		return CSSME1Service_;
	}
	
	@Override	
    public Logger getLogger(){
        return LoggerFactory.getLogger(CSSME1Controller.class);
    }
    
    @Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("CSSME1");
	} 
}
