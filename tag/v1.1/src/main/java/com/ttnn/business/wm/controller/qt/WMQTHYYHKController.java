package com.ttnn.business.wm.controller.qt;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.service.UserService;
import com.ttnn.business.wm.service.WMBS01Service;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;

@Controller
@RequestMapping("WMQTHYYHK")
public class WMQTHYYHKController extends MyControllerSupportImpl {

	@Autowired
	private UserService userService;

	@Resource
	protected WMUI01Service WMUI01Service_;
	@Resource
	protected WMBS01Service WMBS01Service_;
	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMQTHYYHKController.class);
	}

	@Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("WM/QT/WMQTHYYHK");
	}
	// 个人银行卡管理
	@RequestMapping(value = "/{userid}/Y.go")
	public ModelAndView home(@PathVariable String userid, CSPVOSupport paramBean,HttpServletRequest request) {
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();

		paramBean.setK01(userid);

		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		pageVO.setPageData(paramBean);
		
		CSPVOSupport userInfo = new CSPVOSupport();
		userInfo.setPuk(userid);
		FrameworkDataBean db = WMUI01Service_.doRead(userInfo); //个人信息
		userService.doSelectPageBank(pageVO);
		pageMAV.addObject("userInfo", db);
		pageMAV.addObject("bbb",request.getParameter("pageid"));
		pageMAV.addObject(pageVO);
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 更新一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
	
	@RequestMapping(value = "/U.go")
	public ModelAndView doUpdate(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		WMBS01Service_.doUpdate(paramBean);
	    String a = "redirect:" + paramBean.getK01() + "/Y.go";
		pageMAV = new ModelAndView(a);
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

}
