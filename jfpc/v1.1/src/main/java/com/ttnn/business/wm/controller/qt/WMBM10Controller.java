package com.ttnn.business.wm.controller.qt;



import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.service.WMBM03Service;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMBM10")
/** 提现交易记录*/
public class WMBM10Controller extends MyControllerSupportImpl {
	@Resource
	protected WMBM03Service WMBM03Service_;

	@Override
	public MyServiceSupportImpl getService() {
		return WMBM03Service_;
	}

	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMBM10Controller.class);
	}

	@Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("WM/QT/WMCZG2");
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
	@RequestMapping(value = "/C.go", method = RequestMethod.POST)
	public ModelAndView doInsert(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
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
//		List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
		pageMAV.addObject("list", getService().doSelectPage(pageVO).getPageData());
		pageMAV.setViewName("WM/QT/WMCZG2");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 数据一览
	 */
//	@Override
//	@RequestMapping(value = "/F.go")
//	public ModelAndView doFindList(CSPVOSupport paramBean) {
//		ModelAndView pageMAV = getModelAndView();
//		getLogger().debug("paramBean===>>>" + paramBean);
//		PageVO paramPageModel = new PageVO();
//		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
//		pageVO.setPageData(paramBean);
//		pageMAV.addObject(pageVO);
//		 getService().doSelectPage(pageVO);
//		 pageMAV.addObject(paramPageModel);
////			getService().doSelectPage(pageVO).getPageData();
//			pageMAV.addObject("list", getService().doFindList(paramPageModel));
//		pageMAV.setViewName("WM/QT/WMJYT1");
//		getLogger().debug("pageMAV===>>>" + pageMAV);
//		return pageMAV;
//	}
	
	/**
	 * 数据一览
	 */

	@RequestMapping(value = "/F.go")
	public ModelAndView doSelectPage1(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		paramBean.setK01(super.getLoginerId());
		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		pageVO.setPageData(paramBean);		
		pageMAV.addObject(pageVO);
//		paramPageModel.setPageData(paramBean);
//		pageMAV.addObject(paramPageModel);
		
		pageMAV.addObject("list", getService().doSelectPage(pageVO).getPageData());
		pageMAV.setViewName("WM/QT/WMCZG1");
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
		int result = getService().doDelete(paramBean);
		if (result == -1) {
			pageMAV.addObject("obj", "success");
			pageMAV.setViewName("cs/sb01");
		} else if (result == 1) {
			pageMAV.addObject("obj", "删除失败");
		} else {
			pageMAV.addObject("obj", "删除失败");
		}
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramPageModel);
//		List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
		pageMAV.addObject("list", getService().doSelectPage(paramPageModel).getPageData());
		pageMAV.setViewName("cs/sb01");
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
	@RequestMapping(value = "/U.go", method = RequestMethod.POST)
	public ModelAndView doUpdate(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		// 查询得到该记录，加载至“cs/sb03c1”页面
		int result = getService().doUpdate(paramBean);

		pageMAV.setViewName("WM/QT/WMCZG2");
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramPageModel);
//		List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
		pageMAV.addObject("list", getService().doSelectPage(paramPageModel).getPageData());
		pageMAV.setViewName("WM/QT/WMCZG1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 查询一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doSelect(CSPVOSupport)
	 */
	@Override
	@RequestMapping(value = "/R.go")
	public ModelAndView doRead(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramBean);
		pageMAV.addObject("parambean1", getService().doRead(paramBean));
		pageMAV.setViewName("WM/QT/WMJYT2");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 分页显示
	 * 
	 * @param paramPageModel
	 * @return
	 * @see ISSQLDaoSupport#doSelectList(PageVO)
	 */
	@Override
	@RequestMapping(value = "/L.go", method = RequestMethod.GET)
	public ModelAndView doSelectPage(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		
		PageVO paramPageModel = new PageVO();
		// 设定页面参数
		paramPageModel.setPageData(paramBean);
		PageVO result = getService().doSelectPage(paramPageModel);
		result.getPageCount();

		System.out.println(getService().doSelectPage(paramPageModel));
		pageMAV.addObject(paramPageModel);
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
}
