package com.ttnn.business.cs.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ttnn.business.cs.service.CSSL11Service;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSModelAndViewSupport;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("CSSL11")
/** 客户系统管理系统授权*/
public class CSSL11Controller extends MyControllerSupportImpl {
    @Resource
    protected CSSL11Service CSSL11Service_;

    @Override
    public MyServiceSupportImpl getService(){
        return CSSL11Service_;
    }

    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(CSSL11Controller.class);
    }

    @Override
    public CSModelAndViewSupport getModelAndView(){
        return new CSModelAndViewSupport("CS/CSSL11");
    }
    
    /**
	 * 分页显示
	 * @param paramPageModel
	 * @return
	 * @see ISSQLDaoSupport#doSelectList(PageVO)
	 */
	@RequestMapping(value = "/F.go", method = RequestMethod.GET)
	public CSModelAndViewSupport doSelectPage1(CSPVOSupport paramBean,HttpServletRequest request) {
		CSModelAndViewSupport pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		
		paramBean.setFb2(request.getParameter("pageid"));
		
		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		pageVO.setPageData(paramBean);
		
		pageVO = getService().doSelectPage(pageVO);
		
		pageMAV.addObject(pageVO);
		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	/**
	 * 分页显示
	 * @param paramPageModel
	 * @return
	 * @see ISSQLDaoSupport#doSelectList(PageVO)
	 */
	@RequestMapping(value = "/F.go", method = RequestMethod.POST)
	public CSModelAndViewSupport doSelectPage(CSPVOSupport paramBean,HttpServletRequest request) {
		CSModelAndViewSupport pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		
		paramBean.setFb2(request.getParameter("pageid"));
		
		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		pageVO.setPageData(paramBean);
		
		pageVO = getService().doSelectPage(pageVO);
		
		pageMAV.addObject(pageVO);
		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
}
