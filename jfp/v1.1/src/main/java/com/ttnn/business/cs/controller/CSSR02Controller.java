package com.ttnn.business.cs.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSR02Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("CSSR02")
/** 登录用户临时授权*/
public class CSSR02Controller extends MyControllerSupportImpl {
	@Resource
	protected CSSR02Service CSSR02Service_;

	@Override
	public MyServiceSupportImpl getService() {
		return CSSR02Service_;
	}
	
	@Override	
    public Logger getLogger(){
        return LoggerFactory.getLogger(CSSR02Controller.class);
    }
    
   
    /**
	 * 页面视图
	 */
    @Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("cs/sr02");
	}
    
	@Override
	@RequestMapping(value = "/H.go", method = RequestMethod.GET)
	public ModelAndView home(CSPVOSupport paramBean){
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}
	
	
	
	/**
	 * 插入一条记录
	 * @param paramBean
	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
	 */
	@Override
	@RequestMapping(value = "/C.go", method = RequestMethod.GET)
	public ModelAndView doInsert(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean" + paramBean);	
		int result=getService().doInsert(paramBean);
		if(result==1){
			pageMAV.addObject("obj", paramBean);
		}else if(result==-1){
			pageMAV.addObject("obj", "failure");
		}else{
			pageMAV.addObject("obj", "failure");
		}
		
		pageMAV.setViewName("redirect:F.go");
		getLogger().debug("pageMAV" + pageMAV);		
		return pageMAV;
	}
	
	
	
	/**
	 * 数据一览
	 */
	@RequestMapping(value = "/F.go", method = RequestMethod.GET)
	public ModelAndView doFindList(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		pageVO.setPageData(paramBean);		
		pageMAV.addObject(pageVO);
		pageMAV.addObject("list", getService().doSelectPage(pageVO).getPageData());
		pageMAV.setViewName("cs/sr02");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	
	
	/**
	 * 查询一条记录
	 * @param paramBean
	 * @see ISSQLDaoSupport#doSelect(CSPVOSupport)
	 */
	@Override
	@RequestMapping(value = "/R.go", method = RequestMethod.POST)
	public ModelAndView doRead(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);			
		pageMAV.addObject("parambean1", getService().doRead(paramBean));
		FrameworkDataBean result=getService().doRead(paramBean);
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	
	/**
	 * 删除一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doBatchDelete(java.util.List)
	 */
	@Override
	@RequestMapping(value = "/D.go", method = RequestMethod.GET)
	public ModelAndView doDelete(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		int result=getService().doDelete(paramBean);
		if(result==-1){
			pageMAV.addObject("obj", "success");
			pageMAV.setViewName("cs/sr02");
		}else if(result==1){
			pageMAV.addObject("obj", "删除失败");
		}else{
			pageMAV.addObject("obj", "删除失败");
		}
	
		pageMAV.setViewName("redirect:F.go");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	
	
	/**
	 * 更新一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
	@Override
	@RequestMapping(value = "/U.go", method = RequestMethod.GET)
	public ModelAndView doUpdate(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		//查询得到该记录，加载至“cs/sb03c1”页面	
		 int result=getService().doUpdate(paramBean);	
		 
		 pageMAV.setViewName("redirect:F.go");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	}


