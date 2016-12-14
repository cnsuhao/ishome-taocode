package com.ttnn.business.wm.controller.zk;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSM01Service;
import com.ttnn.business.cs.service.CSSS01Service;
import com.ttnn.business.wm.biz.WMMainTotalBusiness;
import com.ttnn.business.wm.biz.WMZKJGService;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMKZ")
/** 首页*/
public class WMKZController extends MyControllerSupportImpl {
	
	@Resource
	protected CSSS01Service CSSS01Service_;
	
    @Resource
    protected WMMainTotalBusiness WMSS01Service_;//系统扩展信息
    
    @Resource
    protected WMZKJGService  WMZKJGService_;
    
    @Resource
    protected CSSM01Service  CSSM01Service_;//系统公告
    
    @Override
    public MyServiceSupportImpl getService(){
        return CSSM01Service_;
    }

    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMKZController.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("/WM/ZK/WMKZ");
    }
    
    @Override
    @RequestMapping(value = "/H.go" )
    public ModelAndView home(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		paramBean.setPuk(super.getProductId());
		paramBean.setEb5(super.getProductId());
		pageMAV.addObject("paramBean", WMSS01Service_.doTotlaZK(paramBean));
		pageVO.setPageData(paramBean);
		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		pageVO.setOrderby("cc1");
		WMZKJGService_.doSelectPageGG(pageVO);
		pageMAV.addObject("xt", CSSS01Service_.doRead(paramBean));
		pageMAV.addObject("gg",pageVO.getPageData());
		pageMAV.setViewName("WM/ZK/WMKZ");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

    
    @RequestMapping(value = "/A.go" )
    public ModelAndView home2(CSPVOSupport paramBean) {
    	ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		CSPVOSupport paramBean1=new CSPVOSupport();
		paramBean1.setPuk(paramBean.getPuk());
		paramBean1.setF01(paramBean.getF01());	
//		pageVO.setPageData(paramBean1);
		pageMAV.addObject("bbb",CSSM01Service_.doRead(paramBean1));
    	pageMAV.setViewName("WM/ZK/WMKZGG1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
    }
      
}
