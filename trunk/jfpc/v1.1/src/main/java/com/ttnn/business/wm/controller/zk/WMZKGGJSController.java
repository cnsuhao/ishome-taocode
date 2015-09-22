package com.ttnn.business.wm.controller.zk;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.service.WMSS01Service;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMZKGGJS")
/** 首页*/
public class WMZKGGJSController extends MyControllerSupportImpl {
    @Resource
    protected WMSS01Service WMSS01Service_;//系统扩展信息

    @Override
    public MyServiceSupportImpl getService(){
        return WMSS01Service_;
    }

    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMZKGGJSController.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("/WM/ZK/WMKZ");
    }
    
    @Override
    @RequestMapping(value = "/H.go", method = RequestMethod.POST)
    public ModelAndView home(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageMAV.addObject("paramBean", paramBean);		
		pageMAV.setViewName("WM/ZK/WMKZ");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

    @Override
    @RequestMapping(value = "/F.go" )
    public ModelAndView doFindList(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageMAV.addObject("paramBean", paramBean);		
		pageMAV.setViewName("WM/ZK/WMJGF1L");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
}
