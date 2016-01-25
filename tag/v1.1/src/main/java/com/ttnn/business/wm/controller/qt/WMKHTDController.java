package com.ttnn.business.wm.controller.qt;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.biz.WMQTJLCXBussiness;
import com.ttnn.business.wm.biz.WMQTTJXXBusiness;
import com.ttnn.business.wm.service.WMBM01Service;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMKHTD")
/** 客户详情*/
public class WMKHTDController extends MyControllerSupportImpl {

	@Resource
	protected WMBM01Service WMBM01Service;
	@Resource
	protected WMQTJLCXBussiness WMQTJLCXBussiness_;
	@Resource
	protected WMQTTJXXBusiness WMQTTJXXBusiness_;
	@Resource
	protected WMUI01Service WMUI01Service_;

	@Override
	public MyServiceSupportImpl getService() {
		return WMBM01Service;
	}

	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMKHTDController.class);
	}

	/**
	 * 客户详情中分配支付通道
	 * 
	 */

	@RequestMapping(value = "/{userid}/F.go")
	public ModelAndView doFindList(CSPVOSupport paramBean, @PathVariable String userid) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		// paramBean.setK01(super.getLoginerId());
		paramBean.setK01(userid);
		pageVO.setPageData(paramBean);
		CSPVOSupport userInfo = new CSPVOSupport();
		userInfo.setPuk(userid);
		FrameworkDataBean db = WMUI01Service_.doRead(userInfo); // 个人信息
		pageMAV.addObject("userInfo", db);
		pageMAV.addObject(pageVO);

		paramBean.setK01(userInfo.getPuk());
		paramBean.setK03(super.getLoginerId());
		paramBean.setEb5(super.getProductId());

		pageVO.setPageData(paramBean);
		WMQTJLCXBussiness_.doSelectPageFACTD(pageVO);
		pageMAV.addObject("list", pageVO.getPageData());
		pageMAV.addObject(pageVO);
		pageMAV.setViewName("WM/QT/WMKHTD");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 插入一条记录
	 * 
	 * @param paramBean
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
	 */
	@Override
	@RequestMapping(value = "/C.go")
	public ModelAndView doInsert(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		// String puk = WMQTTJXXBusiness_.doMyInsert(paramBean);
		// pageMAV.addObject("paramBean", paramBean);
		// pageMAV.addObject("puk", puk);
		paramBean.setK01(super.getLoginerId());
		pageMAV.addObject("message", "*为必填");
		pageMAV.addObject("puk", paramBean.getPuk());// 用户ID
		pageMAV.addObject("k03", paramBean.getK03());// 系统通道
		pageMAV.addObject("parambean", WMQTTJXXBusiness_.doFindMyzfTD(paramBean).get(0));
		pageMAV.setViewName("WM/QT/WMXXX4");

		return pageMAV;
	}

	/**
	 * 插入一条记录
	 * 
	 * @param paramBean
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
	 */

	@RequestMapping(value = "/Y.go")
	public ModelAndView doInsert1(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		paramBean.setK01(super.getLoginerId());
		pageMAV.addObject("message", "*为必填");
		pageMAV.addObject("puk", paramBean.getPuk());// 用户ID
		pageMAV.addObject("k03", paramBean.getK03());// 系统通道
		pageMAV.addObject("parambean", WMQTTJXXBusiness_.doFindMyzfTD(paramBean).get(0));
		pageMAV.setViewName("WM/QT/WMXXX5");

		return pageMAV;
	}

	/**
	 * 修改费率
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
	@Override
	@RequestMapping(value = "/U.go")
	public ModelAndView doUpdate(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		paramBean.setPuk(paramBean.getF07());
		getService().doUpdate(paramBean);
		String a = "redirect:" + paramBean.getF05() + "/F.go";
		pageMAV = new ModelAndView(a);
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 取消通道分配
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doBatchDelete(java.util.List)
	 */

	@RequestMapping(value = "/D.go", method = RequestMethod.POST)
	public ModelAndView doDelete(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		CSPVOSupport c2 = new CSPVOSupport();
		c2.setPuk(paramBean.getK01());
		c2.setUu1(paramBean.getUu1());
		getService().doDelete(c2);
		pageMAV.setViewName("redirect:" + paramBean.getPuk() + "/F.go");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 客户详情中修改费率
	 * 
	 */

	@RequestMapping(value = "/{userid}/M.go")
	public ModelAndView doFindList1(CSPVOSupport paramBean, @PathVariable String userid) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		// paramBean.setK01(super.getLoginerId());
		paramBean.setK01(userid);
		pageVO.setPageData(paramBean);
		CSPVOSupport userInfo = new CSPVOSupport();
		userInfo.setPuk(userid);
		FrameworkDataBean db = WMUI01Service_.doRead(userInfo); // 个人信息
		pageMAV.addObject("userInfo", db);
		pageMAV.addObject(pageVO);
		
		
		paramBean.setK01(userid);
		pageVO.setPageData(paramBean);
		WMBM01Service.doSelectPage(pageVO);
		pageMAV.addObject("list", pageVO.getPageData());
		pageMAV.addObject(pageVO);
		pageMAV.setViewName("WM/QT/WMGRFLXG");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 费率修改中的查一条
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doBatchDelete(java.util.List)
	 */

	@RequestMapping(value = "/{userid}/R.go", method = RequestMethod.POST)
	public ModelAndView doRead(CSPVOSupport paramBean,@PathVariable String userid) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		paramBean.setPuk(userid);
		FrameworkDataBean db =WMBM01Service.doRead(paramBean);
		pageMAV.addObject("userid", db);
		pageMAV.setViewName("WM/QT/WMGRFLXG1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	/**
	 * 修改会员费率
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
	@RequestMapping(value = "/G.go")
	public ModelAndView doUpdate1(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		CSPVOSupport c2=new CSPVOSupport();
		c2.setPuk(paramBean.getF07());
		c2.setUu1(paramBean.getUu1());
		c2.setFb1(paramBean.getFb1());
		c2.setF15(paramBean.getF15());
		c2.setF16(paramBean.getF16());
		c2.setF17(paramBean.getF17());
		c2.setEb3(paramBean.getEb3());
		c2.setEb4(paramBean.getEb4());
		WMBM01Service.doUpdate(c2);
		String a = "redirect:" + paramBean.getK03()+ "/M.go";
		pageMAV = new ModelAndView(a);
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
}
