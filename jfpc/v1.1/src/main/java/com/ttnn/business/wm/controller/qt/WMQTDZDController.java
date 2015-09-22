package com.ttnn.business.wm.controller.qt;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.biz.WMQTJLCXBussiness;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;


@Controller
@RequestMapping("WMQTDZD")
/** 对账单*/
public class WMQTDZDController extends MyControllerSupportImpl {
 
	  @Resource
	    protected WMQTJLCXBussiness WMQTJLCXBussiness_;
	
	
	@Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMQTDZDController.class);
    }
 
    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("WM/QT/WMDZD");
    }
   
   
	@Override
	@RequestMapping(value = "/H.go", method = RequestMethod.POST)
	public ModelAndView home(CSPVOSupport paramBean){
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}
	@RequestMapping(value = "/F.go")
	public ModelAndView doSelectPage(CSPVOSupport ParamBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + ParamBean);
		ParamBean.setK01(super.getLoginerId());
		ParamBean.setEb5(super.getProductId());
		pageVO.setPageCurrent(Integer.parseInt(ParamBean.getPageCurrent()));
		pageVO.setPageData(ParamBean);		
		pageMAV.addObject(pageVO);
		WMQTJLCXBussiness_.doSelectPageDZD(pageVO);
		pageMAV.addObject("list",pageVO.getPageData());
		pageMAV.setViewName("WM/QT/WMDZD");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
}