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
@RequestMapping("WMYJJSJL")
/**佣金结算记录 */
public class WMYJJSJLController extends MyControllerSupportImpl {
	  @Resource
	    protected WMQTJLCXBussiness WMQTJLCXBussiness_;
	
	
	@Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMYJJSJLController.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("WM/QT/WMYJJSJL");
    }
  
   
   
	@Override
	@RequestMapping(value = "/H.go", method = RequestMethod.POST)
	public ModelAndView home(CSPVOSupport paramBean){
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}
	
	@RequestMapping(value = "/F.go", method = RequestMethod.POST)
	public ModelAndView doSelectPage(CSPVOSupport ParamBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + ParamBean);
		ParamBean.setEb5(super.getProductId());
		ParamBean.setK01(super.getLoginerId());
		pageVO.setPageCurrent(Integer.parseInt(ParamBean.getPageCurrent()));
		pageVO.setPageData(ParamBean);		
		pageMAV.addObject(pageVO);
		pageMAV.addObject("ParamBean",ParamBean);
		WMQTJLCXBussiness_.doSelectPageYJJSJL(pageVO);
		pageMAV.addObject("list",pageVO.getPageData());
		pageMAV.setViewName("WM/QT/WMYJJSJL");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
}