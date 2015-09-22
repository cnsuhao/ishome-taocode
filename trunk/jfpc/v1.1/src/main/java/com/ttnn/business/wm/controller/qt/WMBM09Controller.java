package com.ttnn.business.wm.controller.qt;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;
import com.ttnn.business.cs.service.CSSR01Service;
import com.ttnn.business.wm.biz.WMQTJLCXBussiness;
import com.ttnn.business.wm.biz.WMQTTJXXBusiness;
import com.ttnn.business.wm.service.UserService;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMBM09")
/** 下线管理*/
public class WMBM09Controller extends MyControllerSupportImpl {
	@Resource
	protected WMQTTJXXBusiness WMTJXXBusiness_;
	@Resource
	protected UserService userService;
	@Resource
	protected CSSR01Service CSSR01Service_;

	public MyServiceSupportImpl getCSSR01Service() {
		return CSSR01Service_;
	}

	@Resource
	protected WMQTJLCXBussiness WMJLCXBussiness_;

	@Override
	public WMQTJLCXBussiness getService() {
		return WMJLCXBussiness_;
	}

	@Resource
	protected WMUI01Service WMUI01Service_;
//
//	public MyServiceSupportImpl getCSSB01Service() {
//		return CSSB01Service_;
//	}
//
//	@Resource
//	protected WMBM01Service WMBM01Service_;
//
//	public MyServiceSupportImpl getWMBM01Service() {
//		return WMBM01Service_;
//	}
//
//	@Resource
//	protected WMBS01Service WMBS01Service_;
//
//	public MyServiceSupportImpl getWMBS01Service() {
//		return WMBS01Service_;
//	}
//
//	@Resource
//	protected CSSPR1Service CSSPR1Service_;
//
//	public MyServiceSupportImpl getCSSPR1Service() {
//		return CSSPR1Service_;
//	}

	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMBM09Controller.class);
	}

	@Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("WM/QT/WMXXX1");
	}

	/**
	 * 数据一览
	 */

	@RequestMapping(value = "/F.go")
	public ModelAndView doSelectPage(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		if(!StringUtils.isNullOrEmpty(paramBean.getPuk()))
		{
			pageMAV.addObject("message", "会员添加成功");
		}
        paramBean.setK01(super.getLoginerId());
        paramBean.setEb5(super.getProductId());
        paramBean.setK02("DFB_QT_VIP_0");
		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		pageVO.setPageData(paramBean);
		pageMAV.addObject(pageVO);
		pageMAV.addObject("paramBean", paramBean);
		getService().doSelectPageHY(pageVO);
		pageMAV.addObject("list", pageVO.getPageData());
		pageMAV.setViewName("WM/QT/WMXXD1");
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
		FrameworkDataBean data= userService.showDetail(paramBean);
		pageMAV.addObject("user", data);
		pageMAV.setViewName("WM/QT/WMYHXQ");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	@Override
	@RequestMapping(value = "/H.go", method = RequestMethod.POST)
	public ModelAndView home(CSPVOSupport paramBean) {
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		
		// 获得会员组信息
		FrameworkDataBean fdb = new FrameworkDataBean();
		fdb.setEb5(super.getProductId());// 产品ID
		fdb.setTablename("wmuip1");
		pageMAV.addObject("list1", getDictionaryOnTable(fdb));
		
		pageMAV.addObject("message", "*为必填");
		pageMAV.setViewName("WM/QT/WMXXX1");
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}

	@RequestMapping(value = "/M.go", method = RequestMethod.POST)
	public ModelAndView home2(CSPVOSupport paramBean) {
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		// 获得会员组信息
		FrameworkDataBean fdb = new FrameworkDataBean();
		fdb.setEb5(super.getProductId());// 产品ID
		fdb.setTablename("wmuip1");
		pageMAV.addObject("list1", getDictionaryOnTable(fdb));
		
		pageMAV.setViewName("WM/QT/WMZHY2");
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}

	@RequestMapping(value = "/A.go", method = RequestMethod.POST)
	public ModelAndView doInsert1(CSPVOSupport paramBean) {
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
         
		WMTJXXBusiness_.doAddCustom(paramBean);
		
		pageMAV.addObject("userInfo", paramBean);
		
		paramBean.setK01(paramBean.getPuk());
		paramBean.setK03(super.getLoginerId());
		paramBean.setEb5(super.getProductId());
		
		pageVO.setPageData(paramBean);
		WMQTJLCXBussiness_.doSelectPageFACTD(pageVO);

		pageMAV.addObject("message", "设定新会员的交易通道");
//		pageMAV.addObject("list", pageVO.getPageData());
		
		String a = "redirect:" + paramBean.getPuk() + "/F.go";
		pageMAV = new ModelAndView(a);
		
		
		
		
//		pageMAV.setViewName("WM/QT/WMKHTD");
		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
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
	@Resource
	protected WMQTJLCXBussiness WMQTJLCXBussiness_;
	/**
	 * 添加会员
	 * 
	 * @param paramBean
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
	 */
	@RequestMapping(value = "/C.go")
	public ModelAndView doInsert(CSPVOSupport paramBean,HttpServletRequest request) {
		ModelAndView pageMAV = getModelAndView();
		String puk = WMTJXXBusiness_.doMyInsert(paramBean);
		paramBean.setPuk(puk);
		pageMAV.addObject("userInfo", paramBean);
		pageMAV.addObject(pageVO);
		
		paramBean.setK01(puk);
		paramBean.setK03(super.getLoginerId());
		paramBean.setEb5(super.getProductId());
		
		pageVO.setPageData(paramBean);
		WMQTJLCXBussiness_.doSelectPageFACTD(pageVO);

		pageMAV.addObject("message", "设定新会员的交易通道");
		pageMAV.addObject("list", pageVO.getPageData());
		pageMAV.addObject(pageVO);
		pageMAV.setViewName("WM/QT/WMKHTD");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		
		return pageMAV;
	}

//	/**
//	 * 删除一条记录
//	 * 
//	 * @param paramBean
//	 * @see ISSQLDaoSupport#doBatchDelete(java.util.List)
//	 */
//	@Override
//	@RequestMapping(value = "/D.go", method = RequestMethod.POST)
//	public ModelAndView doDelete(CSPVOSupport paramBean) {
//		ModelAndView pageMAV = getModelAndView();
//		getLogger().debug("paramBean===>>>" + paramBean);
//		int result = getService().doDelete(paramBean);
//		if (result > 0) {
//			pageMAV.addObject("obj", "success");
//		} else if (result <= 0) {
//			pageMAV.addObject("obj", "删除失败");
//		} else {
//			pageMAV.addObject("obj", "删除失败");
//		}
//		PageVO paramPageModel = new PageVO();
//		paramPageModel.setPageData(paramBean);
//		pageMAV.addObject(paramPageModel);
//		// List<FrameworkDataBean> list =
//		// getService().doFindList(paramPageModel);
//		pageMAV.addObject("list", getService().doSelectPage(paramPageModel)
//				.getPageData());
//		pageMAV.setViewName("WM/QT/WMXXX1");
//		getLogger().debug("pageMAV===>>>" + pageMAV);
//		return pageMAV;
//	}

//	/**
//	 * 更新一条记录
//	 * 
//	 * @param paramBean
//	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
//	 */
//	@Override
//	@RequestMapping(value = "/U.go", method = RequestMethod.POST)
//	public ModelAndView doUpdate(CSPVOSupport paramBean) {
//		ModelAndView pageMAV = getModelAndView();
//		getLogger().debug("paramBean===>>>" + paramBean);
//		// 查询得到该记录，加载至“cs/sb03c1”页面
//		int result = getService().doUpdate(paramBean);
//
//		pageMAV.setViewName("wm/WMXXZ2");
//		PageVO paramPageModel = new PageVO();
//		paramPageModel.setPageData(paramBean);
//		pageMAV.addObject(paramPageModel);
//		// List<FrameworkDataBean> list =
//		// getService().doFindList(paramPageModel);
//		pageMAV.addObject("list", getService().doSelectPage(paramPageModel).getPageData());
//		pageMAV.setViewName("WM/QT/WMXXX1");
//		getLogger().debug("pageMAV===>>>" + pageMAV);
//		return pageMAV;
//	}

//	/**
//	 * 查询一条记录
//	 * 
//	 * @param paramBean
//	 * @see ISSQLDaoSupport#doSelect(CSPVOSupport)
//	 */
//
//	@RequestMapping(value = "/N.go")
//	public void doFind2(HttpServletRequest request, HttpServletResponse response) {
//
//		CSPVOSupport paramBean = new CSPVOSupport();
//		paramBean.setPuk(request.getParameter("k03"));
//		FrameworkDataBean dataBean = WMBM01Service_.doRead(paramBean);
//
//		response.setContentType("text/xml;charset=UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		response.setHeader("Cache-Control", "no-cache");
//		try {
//			PrintWriter out = response.getWriter();
//
//			out = response.getWriter();
//			out.print(dataBean);// 用于返回对象参数
//			out.flush();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}


}
