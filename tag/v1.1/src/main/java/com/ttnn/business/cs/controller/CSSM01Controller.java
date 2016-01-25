package com.ttnn.business.cs.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;
import com.ttnn.business.cs.service.AQSZ01Service;
import com.ttnn.business.cs.service.CSSM01Service;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("CSSM01")
/** 系统公告*/
public class CSSM01Controller extends MyControllerSupportImpl {
	
	@Resource
	protected CSSM01Service CSSM01Service_;//系统公告
	
	@Resource
	protected AQSZ01Service AQSZ01Service_;//公告查询
	
	@Override
    public MyServiceSupportImpl getService(){
        return CSSM01Service_;
    }
	
    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(CSSM01Controller.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("CS/CSSM01R1");
    }
    
    @RequestMapping(value = "/H.go" )
    public ModelAndView home(CSPVOSupport paramBean,HttpServletRequest request) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		pageMAV.addObject("fb1",paramBean.getFb1());
		
		pageMAV.setViewName("CS/CSSM01C1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
      
    /**
     * 增加公告数据
     */
    @RequestMapping(value = "/C.go" )
    public ModelAndView doInsert(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		CSSM01Service_.doInsert(paramBean);
		
		String url = "redirect:/CSSM01/F.go?fb1="+paramBean.getFb1();
	   	pageMAV = new ModelAndView(url);	  
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
       
    
	/**
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
	@RequestMapping(value = "/F.go" )
	public ModelAndView doUpdate1(CSPVOSupport paramBean,HttpServletRequest request) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		
		paramBean.setEb5(super.getProductId());
		
		pageVO.setPageData(paramBean);
        pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
        
        String fb1 = "3";
        if(StringUtils.isNullOrEmpty(paramBean.getFb1())){
		if("WM.ZK".equals(request.getParameter("pageid")))		
			fb1 = "1";			
		else if("WM.HT".equals(request.getParameter("pageid")))		
			fb1 = "2";		
        }else{
        	fb1 = paramBean.getFb1();
        }
		pageMAV.addObject("fb1",fb1);
		paramBean.setFb1(fb1);

		AQSZ01Service_.doSelectPageGG(pageVO);
		
		pageMAV.addObject(pageVO);
		pageMAV.setViewName("CS/CSSM01R1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
	@RequestMapping(value = "/F.go",method = RequestMethod.GET )
	public ModelAndView doUpdate2(CSPVOSupport paramBean,HttpServletRequest request) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		
		paramBean.setEb5(super.getProductId());
		
		pageVO.setPageData(paramBean);
        pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
        
        String fb1 = "2";
        
		pageMAV.addObject("fb1",fb1);
		paramBean.setFb1(fb1);

		AQSZ01Service_.doSelectPageGG(pageVO);
		
		pageMAV.addObject(pageVO);
		pageMAV.setViewName("CS/CSSM01R1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	 /**
     * 修改公告数据
     */
    @RequestMapping(value = "/U.go" )
    public ModelAndView doUpdate(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageVO.setPageData(paramBean);
		CSSM01Service_.doUpdate(paramBean);
		String url = "redirect:/CSSM01/F.go?fb1="+paramBean.getFb1();
	   	pageMAV = new ModelAndView(url);	  
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
       
    /**
     * 读取一条数据
     */
    @RequestMapping(value = "/R.go" )
    public ModelAndView doRead(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
//		pageVO.setPageData(paramBean);			
		pageMAV.addObject("parambean1", CSSM01Service_.doRead(paramBean));
		pageMAV.addObject("fb1",paramBean.getFb1());
		pageMAV.setViewName("CS/CSSM01C1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
    
    /**
     * 删除公告
     */
    @RequestMapping(value = "/D.go" )
    public ModelAndView toDelete(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		int result=CSSM01Service_.doDelete(paramBean);
		String url = "redirect:/CSSM01/F.go?fb1="+paramBean.getFb1();
	   	pageMAV = new ModelAndView(url);	 
//		pageVO.setPageData(paramBean);
//        pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
//		AQSZ01Service_.doSelectPageGG(pageVO);
//		pageMAV.addObject(pageVO);
//		pageMAV.setViewName("CS/CSSM01R1");		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
    
}
