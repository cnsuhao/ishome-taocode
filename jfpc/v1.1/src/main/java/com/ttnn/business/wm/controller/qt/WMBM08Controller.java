package com.ttnn.business.wm.controller.qt;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.biz.WMQTJLCXBussiness;
import com.ttnn.business.wm.service.WMBMA1Service;
import com.ttnn.business.wm.service.WMBS01Service;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMBM08")
/** 银行卡*/
public class WMBM08Controller extends MyControllerSupportImpl {
	@Resource
	protected WMBS01Service WMBS01Service_;
	@Resource
	protected WMBMA1Service WMBMA1Service_;
   @Resource
   protected WMQTJLCXBussiness WMQTJLCXBussiness_;
	@Override
	public MyServiceSupportImpl getService() {
		return WMBS01Service_;
	}

	public MyServiceSupportImpl getWMBMA1Service() {
		return WMBMA1Service_;
	}
	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMBM08Controller.class);
	}

	@Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("WM/QT/WMZHY2");
	}

	@Override
	@RequestMapping(value = "/H.go", method = RequestMethod.POST)
	public ModelAndView home(CSPVOSupport paramBean) {
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}

	/**
	 * 插入一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
	 */
	@Override
	@RequestMapping(value = "/C.go")
	public ModelAndView doInsert(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean" + paramBean);
		int result = getService().doInsert(paramBean);
		if (result == 1) {
			pageMAV.addObject("obj", paramBean);
		} else if (result == -1) {
			pageMAV.addObject("obj", "failure");
		} else {
			pageMAV.addObject("obj", "failure");
		}
	
		pageVO.setPageData(paramBean);
		pageMAV.addObject(pageVO);
		pageMAV.addObject("list", getService().doSelectPage(pageVO).getPageData());
		pageMAV.setViewName("WM/QT/WMZHT3");
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}
	/**
	 * 我的银行卡
	 */

	@RequestMapping(value = "/F.go")
	public ModelAndView doSelectPage(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		paramBean.setK01(super.getLoginerId());
		pageVO.setPageData(paramBean);		
		pageMAV.addObject(pageVO);
		pageMAV.addObject("list", getService().doSelectPage(pageVO).getPageData());
//		pageMAV.addObject("list", 
		pageMAV.setViewName("WM/QT/WMZHY1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	/**
	 * 会员的银行卡
	 */

	@RequestMapping(value = "Y.go")
	public ModelAndView doSelectPage1(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		paramBean.setK01(super.getLoginerId());
		paramBean.setEb5(super.getProductId());
		pageVO.setOrderby(" wmbs01.cc1 ");
		pageVO.setPageData(paramBean);		
		pageMAV.addObject(pageVO);
        WMQTJLCXBussiness_.doSelectPageYHK(pageVO);
	    pageMAV.addObject("list", pageVO.getPageData());
	    pageMAV.setViewName("WM/QT/WMQTHYYHK");
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
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);	
		pageMAV.addObject(paramBean);
		pageMAV.addObject("parambean1", getService().doRead(paramBean));
        pageMAV.setViewName("WM/QT/WMZHY2");
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
	    getService().doDelete(paramBean);
		pageMAV=new ModelAndView("redirect:Y.go");
		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
}
