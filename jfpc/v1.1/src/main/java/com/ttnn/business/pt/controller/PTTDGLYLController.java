package com.ttnn.business.pt.controller;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ttnn.business.wm.service.WMBMA1Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("PTTDGLYL")
/** 云端通道管理*/
public class PTTDGLYLController extends MyControllerSupportImpl {
	  @Resource
	    protected WMBMA1Service WMBMA1Service_;

		@Override
		public MyServiceSupportImpl getService() {
			return WMBMA1Service_;
		}
		public Logger getLogger() {
			return LoggerFactory.getLogger(PTTDGLYLController.class);
		}
		
		
		@Override
		public ModelAndView getModelAndView() {
			return new ModelAndView("PT/PTTDGLYL1");
		}

		@Override
		@RequestMapping(value = "/H.go")
		public ModelAndView home(CSPVOSupport paramBean) {
			getLogger().debug("paramBean" + paramBean);
			ModelAndView pageMAV = getModelAndView();
			getLogger().debug("pageMAV" + pageMAV);
			return pageMAV;
		}		
		/**
		 * 系统通道一览
		 */
		@Override
		@RequestMapping(value = "/F.go")
		public ModelAndView doFindList(CSPVOSupport paramBean) {
			ModelAndView pageMAV = getModelAndView();
			getLogger().debug("paramBean===>>>" + paramBean);
			paramBean.setEb5("TTNN_DFB");
			pageVO.setPageData(paramBean);
		    getService().doSelectPage(pageVO);
		    pageMAV.addObject("list",pageVO.getPageData());
			pageMAV.setViewName("PT/PTTDGLYL");
			getLogger().debug("pageMAV===>>>" + pageMAV);
			return pageMAV;
		}
		/**
		 * 添加系统通道
		 */
		@Override
		@RequestMapping(value = "/C.go")
		public ModelAndView doInsert(CSPVOSupport paramBean) {
			ModelAndView pageMAV = getModelAndView();
			getLogger().debug("paramBean===>>>" + paramBean);
			paramBean.setEb5("TTNN_DFB");
			pageVO.setPageData(paramBean);
		    getService().doInsert(paramBean);
		    pageMAV=new ModelAndView("redirect:F.go");
			getLogger().debug("pageMAV===>>>" + pageMAV);
			return pageMAV;
		}		
		/**
		 * 查询一条记录
		 * 
		 * @param paramBean
		 * @see ISSQLDaoSupport#doSelect(CSPVOSupport)
		 */
		@RequestMapping(value = "/{userid}/R.go",method = RequestMethod.POST)
		public ModelAndView showDetail(@PathVariable String userid,CSPVOSupport paramBean ) {
			ModelAndView pageMAV = getModelAndView();
			paramBean.setPuk(userid);
			FrameworkDataBean data=  getService().doRead(paramBean);
			pageMAV.addObject("paramBean", data);
			pageMAV.setViewName("PT/PTTDGLYL1");
			getLogger().debug("pageMAV===>>>" + pageMAV);
			return pageMAV;
		}
		
		/**
		 * 修改系统通道
		 */
		@Override
		@RequestMapping(value = "/U.go")
		public ModelAndView doUpdate(CSPVOSupport paramBean) {
			ModelAndView pageMAV = getModelAndView();
			getLogger().debug("paramBean===>>>" + paramBean);
			paramBean.setEb5("TTNN_DFB");
			pageVO.setPageData(paramBean);
		    getService().doUpdate(paramBean);
		    pageMAV=new ModelAndView("redirect:F.go");
			getLogger().debug("pageMAV===>>>" + pageMAV);
			return pageMAV;
		}
		/**
		 * 删除系统通道
		 */
		@Override
		@RequestMapping(value = "/D.go")
		public ModelAndView doDelete(CSPVOSupport paramBean) {
			ModelAndView pageMAV = getModelAndView();
			getLogger().debug("paramBean===>>>" + paramBean);
			pageVO.setPageData(paramBean);
		    getService().toDelete(paramBean);
		    pageMAV=new ModelAndView("redirect:F.go");
			getLogger().debug("pageMAV===>>>" + pageMAV);
			return pageMAV;
		}	
		
		
//		/**
//		 * 分配系统通道
//		 */
//		@Override
//		@RequestMapping(value = "/U.go")
//		public ModelAndView doUpdate(CSPVOSupport paramBean) {
//			ModelAndView pageMAV = getModelAndView();
//			getLogger().debug("paramBean===>>>" + paramBean);
//			paramBean.setEb5("TTNN_DFB");
//			pageVO.setPageData(paramBean);
//		    getService().doUpdate(paramBean);
//		    pageMAV=new ModelAndView("redirect:F.go");
//			getLogger().debug("pageMAV===>>>" + pageMAV);
//			return pageMAV;
//		}
		
		
		
}