package com.ttnn.business.cs.controller;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSB01Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("Page")
/** 数据字典管理*/
public class PageController extends MyControllerSupportImpl   {
    @Resource
    protected CSSB01Service CSSB01Service_;

    @Override
    public MyServiceSupportImpl getService(){
        return CSSB01Service_;
    }

    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(PageController.class);
    }
    /**
	 * 页面视图
	 */
    @Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("home");
	}
    
	@Override
	@RequestMapping(value = "/H.go", method = RequestMethod.GET)
	public ModelAndView home(CSPVOSupport paramBean){
		ModelAndView pageMAV = getModelAndView();
		
		pageVO.setPageData(paramBean);		
		pageVO.setPageCurrent(1);
		pageVO.setPageLimit(10);
		getLogger().debug("=====================================================");
		pageVO=getService().doSelectPage(pageVO);
		for (FrameworkDataBean o : (List<FrameworkDataBean>)pageVO.getPageData())
			System.out.println("===xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx========" + o);
		
		pageMAV.addObject("list", pageVO.getPageData());
		pageMAV.setViewName("home");
		return pageMAV;
	}
	
	
	}
