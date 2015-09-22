package com.ttnn.business.wm.controller.qt;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSR01Service;
import com.ttnn.business.wm.biz.WMQTCZTXBussiness;
import com.ttnn.business.wm.biz.WMQTJLCXBussiness;
import com.ttnn.business.wm.service.WMBM02Service;
import com.ttnn.business.wm.service.WMBM03Service;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.common.util.PKUtil;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSModelAndViewSupport;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMZHD1")
/** 我的账户*/
public class WMZHD1Controller extends MyControllerSupportImpl {
	@Resource
	PageVO pageVO;
	@Resource
	protected WMUI01Service WMUI01Service_;

	@Override
	public MyServiceSupportImpl getService() {
		return WMUI01Service_;
	}
	

    @Resource
    protected WMBM02Service WMBM02Service_;

    @Resource
    protected WMBM03Service WMBM03Service_;
	@Resource
	protected WMQTJLCXBussiness WMQTJLCXBussiness_;
	
	@Resource
	protected CSSR01Service CSSR01Service_;

	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMZHD1Controller.class);
	}

	@Override
	public CSModelAndViewSupport getModelAndView() {
		return new CSModelAndViewSupport("WM/QT/WMJYC2");
	}

	@Override
	@RequestMapping(value = "/H.go", method = RequestMethod.POST)
	public ModelAndView home(CSPVOSupport paramBean) {
		getLogger().debug("paramBean" + paramBean);
		CSModelAndViewSupport pageMAV = getModelAndView();
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}

	// /**
	// * 更新一条记录
	// *
	// * @param paramBean
	// * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	// */
	// @RequestMapping(value = "/U.go")
	// public ModelAndView doUpdate(CSPVOSupport paramBean) {
	// ModelAndView pageMAV = getModelAndView();
	// getLogger().debug("paramBean===>>>" + paramBean);
	// getWMBM04Service().doUpdate(paramBean);
	// // pageMAV.setViewName("wm/WMZHY2");
	// PageVO paramPageModel = new PageVO();
	// paramPageModel.setPageData(paramBean);
	// // pageMAV.addObject(paramPageModel);
	// // List<FrameworkDataBean> list =
	// getService().doFindList(paramPageModel);
	// pageMAV.addObject("list",
	// getService().doSelectPage(paramPageModel).getPageData());
	// pageMAV.setViewName("WM/QT/WMJYT3");
	// getLogger().debug("pageMAV===>>>" + pageMAV);
	// return pageMAV;
	// }

	/**
	 * 数据一览
	 */

	@RequestMapping(value = "/F.go")
	public ModelAndView doSelectPage(CSPVOSupport paramBean) {
		CSModelAndViewSupport pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		paramBean.setK01(super.getLoginerId());
		pageVO.setPageData(paramBean);
		pageMAV.addObject("list1", WMBM02Service_.doSelectPage(pageVO).getPageData()); // 充值记录
		pageVO.setPageData(paramBean);	
	    pageMAV.addObject("list2",  WMBM03Service_.doSelectPage(pageVO).getPageData()); // 近期提现记录
		paramBean.setK03(super.getLoginerId());
		pageVO.setPageData(paramBean);
		 WMQTJLCXBussiness_.doSelectPageCommission(pageVO); // 近期佣金记录
	    paramBean.setPuk(super.getLoginerId());
		FrameworkDataBean databean = getService().doRead(paramBean); // 查询个人信息
		pageMAV.addObject("wmui01dbo", databean);
		FrameworkDataBean allowbean = CSSR01Service_.doRead(paramBean); // 查询允许用户登录信息
		pageMAV.addObject("allowbean", allowbean);
		pageMAV.setViewName("WM/QT/WMZHD1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	
	@Resource
	private WMQTCZTXBussiness WMQTCZTXBussiness_;
	
	/**
	 * 佣金结算
	 */
	@RequestMapping(value = "/M.go")
	public ModelAndView dojiesuan(CSPVOSupport formParamBean) {
		CSModelAndViewSupport pageMAV = getModelAndView();
		
		formParamBean.setPuk(PKUtil.getPUKey());
		
		try {
			WMQTCZTXBussiness_.doYJJSInsert(formParamBean,pageVO,pageMAV);
		} catch (Exception e) {
			pageMAV.addObject("message",e.getMessage());
		}
		pageMAV.addObject("puk", formParamBean.getPuk());
		pageMAV.setViewName("WM/QT/WMJYT3");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
}
