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
import com.ttnn.business.wm.service.WMBM01Service;
import com.ttnn.business.wm.service.WMBMA2Service;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMBN01")
/** 支付通道*/
public class WMBN01Controller extends MyControllerSupportImpl {
	@Resource
	protected WMBM01Service WMBM01Service_;
	@Resource
	protected WMBMA2Service WMBMA2Service_;
	@Resource
	protected	WMQTJLCXBussiness  WMJLCXBussiness_;
	@Override
	public MyServiceSupportImpl getService() {
		return WMBM01Service_;
	}
	@Resource
	protected	WMUI01Service  WMUI01Service_;

	public MyServiceSupportImpl getWMUI01Service() {
		return WMUI01Service_;
	}

	
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMBN01Controller.class);
	}

	@Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("WM/QT/WMXXZ2");
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
	 * 下线支付通道一览
	 */

	@RequestMapping(value = "/F.go")
	public ModelAndView doSelectPage1(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		
		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		paramBean.setK01(super.getLoginerId());
		paramBean.setEb5(super.getProductId());
		pageVO.setPageData(paramBean);	
		pageVO.setOrderby(" wmbm01.cc1 ");
		pageMAV.addObject(pageVO);
//		getService().doSelectPage(pageVO);
//		WMJLCXBussiness_.dochazhao4(paramBean);
		WMJLCXBussiness_.doSelectPagePayChannel(pageVO);
	    pageMAV.addObject("list",pageVO.getPageData());
	  
		pageMAV.setViewName("WM/QT/WMXXZ1");
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
	@RequestMapping(value = "/D.go", method = RequestMethod.POST)
	public ModelAndView doDelete(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
	    getService().toDelete(paramBean);
		pageMAV=new ModelAndView("redirect:F.go");
		
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
	@RequestMapping(value = "/U.go")
	public ModelAndView doUpdate(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		// 查询得到该记录，加载至“cs/sb03c1”页面
	    getService().doUpdate(paramBean);
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
		FrameworkDataBean data= WMBM01Service_.doRead(paramBean);
		paramBean.setPuk(data.getK01());
		FrameworkDataBean data1= WMUI01Service_.doRead(paramBean);
		data.setF04(data1.getF04());
		pageMAV.addObject("user", data);
		pageMAV.setViewName("WM/QT/WMXXZ2");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	


}