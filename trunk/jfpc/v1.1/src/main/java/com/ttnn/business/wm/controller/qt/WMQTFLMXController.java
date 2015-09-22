package com.ttnn.business.wm.controller.qt;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.biz.WMQTJLCXBussiness;
import com.ttnn.business.wm.service.WMBMA2Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMQTFLMX")
/** 费率明细*/
public class WMQTFLMXController extends MyControllerSupportImpl {
	@Resource
	protected WMBMA2Service WMBMA2Service_;
	@Resource
	protected WMQTJLCXBussiness WMQTJLCXBussiness_;
	@Override
	public MyServiceSupportImpl getService() {
		return WMBMA2Service_;
	}
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMQTFLMXController.class);
	}

	@Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("WM/QT/WMQTFLMX1");
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
	 * 费率明细一览
	 */

	@RequestMapping(value = "/F.go")
	public ModelAndView doSelectPage1(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageVO.setPageData(paramBean);
		WMBMA2Service_.doSelectPage(pageVO);
	    pageMAV.addObject("list",pageVO.getPageData());
	    pageMAV.setViewName("WM/QT/WMQTHYFLMX");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 添加通道费率
	 */
	@Override
	@RequestMapping(value = "/C.go")
	public ModelAndView doInsert(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageVO.setPageData(paramBean);
	    getService().doInsert(paramBean);
	    pageMAV=new ModelAndView("redirect:F.go");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}		
	/**
	 * 查询费率
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
		pageMAV.setViewName("WM/QT/WMQTFLMX1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 修改费率
	 */
	@Override
	@RequestMapping(value = "/U.go")
	public ModelAndView doUpdate(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageVO.setPageData(paramBean);
	    getService().doUpdate(paramBean);
	    pageMAV=new ModelAndView("redirect:F.go");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	/**
	 * 删除费率
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
	
	
	/**
	 * 查询一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doSelect(CSPVOSupport)
	 */
	@RequestMapping(value = "/{userid}/Z.go",method = RequestMethod.POST)
	public ModelAndView showDetail1(@PathVariable String userid,CSPVOSupport paramBean ) {
		ModelAndView pageMAV = getModelAndView();
		paramBean.setK01(userid);
		 pageVO.setPageData(paramBean);
		 WMBMA2Service_.doSelectPage(pageVO);
		pageMAV.addObject("list",pageVO.getPageData());
		pageMAV.addObject("ddd","0");
		pageMAV.setViewName("WM/QT/WMQTHYFLMX");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	
	@RequestMapping(value = "/{userid}/Z.go",method = RequestMethod.GET)
	public ModelAndView showDetailHT(@PathVariable String userid,CSPVOSupport paramBean ) {
		ModelAndView pageMAV = getModelAndView();
		paramBean.setK01(userid);
		 pageVO.setPageData(paramBean);
		 WMBMA2Service_.doSelectPage(pageVO);
		pageMAV.addObject("list",pageVO.getPageData());
		pageMAV.addObject("ddd","0");
		pageMAV.setViewName("WM/QT/WMQTHYFLMX");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	
	
	
}