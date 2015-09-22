package com.ttnn.business.wm.controller.qt;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.service.WMBM05Service;
import com.ttnn.business.wm.service.WMBS01Service;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMBM05")
/** 佣金结算记录*/
public class WMBM05Controller extends MyControllerSupportImpl {
    @Resource
    protected WMBM05Service WMBM05Service_;

    @Override
    public MyServiceSupportImpl getService(){
        return WMBM05Service_;
    }
    @Resource
    protected WMUI01Service WMUI01Service_;

   
    public MyServiceSupportImpl getWMUI01Service(){
        return WMUI01Service_;
    }
    @Resource
    protected WMBS01Service WMBS01Service_;

   
    public MyServiceSupportImpl getWMBS01Service(){
        return WMBS01Service_;
    }

    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMBM05Controller.class);
    }


	@Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("WM/QT/WMJYY2");
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
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramPageModel);
//		List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
		pageMAV.addObject("list", getService().doSelectPage(paramPageModel).getPageData());
		pageMAV.setViewName("WM/QT/WMJYY1");		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	
	
	
	
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
		pageMAV.addObject("list", getService().doSelectPage(pageVO).getPageData());
		pageMAV.addObject("list", getWMUI01Service().doSelectPage(pageVO).getPageData());
		pageMAV.addObject("list", getWMBS01Service().doSelectPage(pageVO).getPageData());
		pageMAV.setViewName("WM/QT/WMJYY1");
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
		if (result > 0) {
			pageMAV.addObject("obj", "success");
			pageMAV.setViewName("wm/WMJYY1");
		} else if (result <= 0) {
			pageMAV.addObject("obj", "删除失败");
		} else {
			pageMAV.addObject("obj", "删除失败");
		}
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramPageModel);
//		List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
		pageMAV.addObject("list", getService().doSelectPage(paramPageModel).getPageData());
		pageMAV.setViewName("WM/QT/WMJYY1");
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

		pageMAV.setViewName("WM/QT/WMJYY2");
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramPageModel);
//		List<FrameworkDataBean> list = getService()(paramPageModel);
		pageMAV.addObject("list",  getService().doSelectPage(paramPageModel).getPageData());
		pageMAV.setViewName("WM/QT/WMJYY1");
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
	@RequestMapping(value = "/R.go", method = RequestMethod.POST)
	public ModelAndView doRead(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramBean);

		pageMAV.addObject("parambean1", getService().doRead(paramBean));
		System.out.println();
		pageMAV.setViewName("WM/QT/WMJYY2");
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
	@RequestMapping(value = "/L.go", method = RequestMethod.POST)
	public ModelAndView doSelectPage(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		// PageVO paramPageModel = new PageVO();
		// 设定页面参数
		pageVO.setPageCurrent(1);
		pageVO.setPageLimit(5);
		// List<FrameworkDataBean> list=getService().doFindList(pageVO);
		// pageVO.setResultCount(list.size());
		pageVO.setPageData(paramBean);
		pageVO = getService().doSelectPage(pageVO);
		pageMAV.addObject("list", pageVO.getPageData());
		pageMAV.addObject("pagecount",pageVO.getPageCount());
		pageMAV.addObject("pagecurrent",pageVO.getPageCurrent());
		pageMAV.addObject("pagelimit",pageVO.getPageLimit());
		pageMAV.addObject("totalpage",pageVO.getResultCount());
		pageMAV.setViewName("WM/QT/WMJYY1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
}
