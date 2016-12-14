package com.ttnn.business.wm.controller.ht;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.biz.WMHTZHYWBussiness;
import com.ttnn.business.wm.biz.WMQTJLCXBussiness;
import com.ttnn.business.wm.service.WMBM01Service;
import com.ttnn.business.wm.service.WMBMA1Service;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMHTTDFP")
/** 客户详情*/
public class WMHTTDFPController extends MyControllerSupportImpl {
	
	@Resource
	protected WMBM01Service WMBM01Service;
	@Resource
	protected WMQTJLCXBussiness WMQTJLCXBussiness_;
//	@Resource
//	protected WMQTTJXXBusiness WMQTTJXXBusiness_;
	@Resource
	protected WMHTZHYWBussiness WMHTZHYWBussiness_;
	@Resource
	protected WMUI01Service WMUI01Service_;
	
	@Override
	public MyServiceSupportImpl getService() {
		return WMBM01Service;
	}
	
	
	 @Override
	    public Logger getLogger(){
	        return LoggerFactory.getLogger(WMHTTDFPController.class);
	    }

	
	/**
	 *  客户详情中分配支付通道
	 *  
	 */

	@RequestMapping(value = "/{userid}/F.go")
	public ModelAndView doFindList(CSPVOSupport paramBean,@PathVariable String userid) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		
		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
//		paramBean.setK01(super.getLoginerId());
		paramBean.setK01(userid);
		pageVO.setPageData(paramBean);
		CSPVOSupport userInfo = new CSPVOSupport();
		userInfo.setPuk(userid);
		FrameworkDataBean db = WMUI01Service_.doRead(userInfo); //个人信息
        pageMAV.addObject("userInfo", db);
		pageMAV.addObject(pageVO);
		
		paramBean.setK01(userInfo.getPuk());
		paramBean.setK03(super.getLoginerId());
		paramBean.setEb5(super.getProductId());
		
		pageVO.setPageData(paramBean);
		WMHTZHYWBussiness_.doSelectPageFPTD(pageVO);
		pageMAV.addObject("list", pageVO.getPageData());
		pageMAV.addObject(pageVO);
		pageMAV.setViewName("WM/QT/WMKHTD");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}	
	
		@RequestMapping(value = "/{userid}/C.go",method = RequestMethod.POST)
	public String doRoad(CSPVOSupport paramBean,@PathVariable String userid,FrameworkDataBean data,Model model){
		paramBean = new CSPVOSupport();
		paramBean.setPuk(userid);
		
		model.addAttribute("roadinfo",WMUI01Service_.doRead(paramBean));

		paramBean.setK01(paramBean.getPuk());
		paramBean.setEb5(super.getProductId());
		
		pageVO.setPageData(paramBean);
		WMHTZHYWBussiness_.doSelectPageFPTD(pageVO);	
		
		model.addAttribute("systemRoad", pageVO.getPageData());
		return "WM/HT/WMAKK2";
	}
}
