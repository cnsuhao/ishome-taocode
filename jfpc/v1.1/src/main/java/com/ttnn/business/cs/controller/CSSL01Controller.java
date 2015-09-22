package com.ttnn.business.cs.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSL01Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("CSSL01")
/** 业务运行日志*/
public class CSSL01Controller extends MyControllerSupportImpl {
    @Resource
    protected CSSL01Service CSSL01Service_;

	@Override
	public MyServiceSupportImpl getService() {
		return CSSL01Service_;
	}
	
	@Override	
    public Logger getLogger(){
        return LoggerFactory.getLogger(CSSL01Controller.class);
    }
    
	@Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("cs/sl01");
	}
	 @Override
	    @RequestMapping(value = "/H.go", method = RequestMethod.GET)
	    public ModelAndView home(CSPVOSupport paramBean){
	    	ModelAndView pageMAV = getModelAndView();
	    	getLogger().debug("paramBean===>>>" + paramBean);
	    	pageMAV.setViewName("cs/sl01");
	    	getLogger().debug("pageMAV===>>>" + pageMAV);
	    	return pageMAV;
	    }
	 /**
		 * 插入一条记录
		 * @param paramBean
		 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
		 */
//		@Override	
//		@RequestMapping(value = "/C.go", method = RequestMethod.GET)
//		public ModelAndView doInsert(CSPVOSupport paramBean) {
//			ModelAndView pageMAV = getModelAndView();
//			getLogger().debug("paramBean" + paramBean);	
//			int result=getService().doInsert(paramBean);
//			if(result==1){
//				pageMAV.addObject("obj", paramBean);
//			}else if(result==-1){
//				pageMAV.addObject("obj", "failure");
//			}else{
//				pageMAV.addObject("obj", "failure");
//			}
//			
//			PageVO paramPageModel = new PageVO();
//			paramPageModel.setPageData(paramBean);		
//			pageMAV.addObject(paramPageModel);
//			List<FrameworkDataBean> list=getService().doFindList(paramPageModel);
//			pageMAV.addObject("list", list);
//			pageMAV.setViewName("cs/sl02");
//			getLogger().debug("pageMAV" + pageMAV);		
//			return pageMAV;
//		}
		
		/**
		 * 数据一览
		 */
		@RequestMapping(value = "/F.go")
		public ModelAndView doFindList(CSPVOSupport paramBean) {
			ModelAndView pageMAV = getModelAndView();
			getLogger().debug("paramBean===>>>" + paramBean);
			pageVO.setPageData(paramBean);
			pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
			pageMAV.addObject(pageVO);
			pageMAV.addObject("list", getService().doSelectPage(pageVO).getPageData());
			pageMAV.setViewName("cs/sl01");
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
			pageMAV.setViewName("cs/sl01");
			getLogger().debug("pageMAV===>>>" + pageMAV);
			
			return pageMAV;
		}
		

}
