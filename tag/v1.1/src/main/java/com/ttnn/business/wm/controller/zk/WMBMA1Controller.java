package com.ttnn.business.wm.controller.zk;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.service.WMBMA1Service;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMBMA1")

public class WMBMA1Controller extends MyControllerSupportImpl {
    @Resource
    protected WMBMA1Service WMBMA1Service_;

    @Override
    public MyServiceSupportImpl getService(){
        return WMBMA1Service_;
    }

    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMBMA1Controller.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("WM/ZK/WMBMA1");
    }
    
    @Override
	@RequestMapping(value = "/H.go" )
	public ModelAndView home(CSPVOSupport paramBean) {
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}

}
