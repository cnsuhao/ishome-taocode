package com.ttnn.business.wm.controller.zk;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSPB1Service;
import com.ttnn.business.cs.service.CSSS01Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMSH02")
/** 云端黑名单*/
public class WMSH02Controller extends MyControllerSupportImpl {
    @Resource
    protected CSSPB1Service CSSPB1Service;

    @Override
    public MyServiceSupportImpl getService(){
        return CSSPB1Service;
    }
    
    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMSH02Controller.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("WM/ZK/WMSHY1");
    }
    
    @Override
    @RequestMapping(value = "/H.go" )
    public ModelAndView home(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageMAV.setViewName("WM/ZK/WMSHY1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

    @RequestMapping(value = "/A.go" )
	public ModelAndView home1(CSPVOSupport paramBean) {
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		pageMAV.setViewName("WM/ZK/WMSHY1");
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}
    
    @RequestMapping(value = "/B.go" )
   	public ModelAndView home2(CSPVOSupport paramBean) {
   		getLogger().debug("paramBean" + paramBean);
   		ModelAndView pageMAV = getModelAndView();
   		pageMAV.setViewName("WM/ZK/WMSHY2");
   		getLogger().debug("pageMAV" + pageMAV);
   		return pageMAV;
   	}
    
    /**
	 * 插入一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
	 */
	@RequestMapping(value = "/C.go" )
	public ModelAndView doInsert(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		int result = getService().doInsert(paramBean);
		if (result == 1) {
			pageMAV.addObject("message", paramBean);
		} else if (result == -1) {
			pageMAV.addObject("message", "failure");
		} else {
			pageMAV.addObject("message", "failure");
		}
		String url = "redirect:F.go";
	   	pageMAV = new ModelAndView(url);	
	/*	PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramPageModel);
		List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
		pageMAV.addObject("list", list);
		pageMAV.setViewName("WM/ZK/WMSHY1");	*/	
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 数据一览
	 */
	@Override
	@RequestMapping(value = "/F.go" )
	public ModelAndView doFindList(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageVO.setPageData(paramBean);
        pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
        getService().doSelectPage(pageVO);	
	    pageMAV.addObject(pageVO);	
//		paramBean.setDdd("");
	/*	PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramPageModel);
		pageMAV.addObject("list", getService().doFindList(paramPageModel));
		pageMAV.setViewName("WM/ZK/WMSHY1");*/
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 分页显示
	 * @param paramPageModel
	 * @return
	 * @see ISSQLDaoSupport#doSelectList(PageVO)
	 */
	@Override
	@RequestMapping(value = "/L.go" )
	public ModelAndView doSelectPage(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
			pageVO.setPageCurrent(1);
			pageVO.setPageLimit(5);
			pageVO.setPageData(paramBean);
			pageVO = getService().doSelectPage(pageVO);
			pageMAV.addObject("list", pageVO.getPageData());
			pageMAV.addObject("pagecount", pageVO.getPageCount());//总页数
			pageMAV.addObject("pagecurrent", pageVO.getPageCurrent());//当前页
			pageMAV.addObject("pagelimit", pageVO.getPageLimit());//每页记录数
			pageMAV.addObject("totalcount", pageVO.getResultCount());//总记录数
			pageMAV.setViewName("WM/ZK/WMSHY1");	
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	/**
	 * 查询一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doRead(CSPVOSupport)
	 */
	@RequestMapping(value = "/R.go" )
	public ModelAndView doRead(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		paramBean.setDdd("");
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramBean);
		pageMAV.addObject("parambean1", getService().doRead(paramBean));
		pageMAV.setViewName("WM/ZK/WMSHY2");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 删除一条记录
	 * method = RequestMethod.POST
	 * @param paramBean
	 * @see ISSQLDaoSupport#doBatchDelete(java.util.List)
	 */
	@RequestMapping(value = "/D.go" )
	public ModelAndView doDelete(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
//		int result =getService().toDelete(paramBean);
		int result = getService().doDelete(paramBean);
		if (result > 0) {
			pageMAV.addObject("message", "success");
		} else if (result <= 0) {
			pageMAV.addObject("message", "删除失败");
		} else {
			pageMAV.addObject("message", "删除失败");
		}
		String url = "redirect:F.go";
	   	pageMAV = new ModelAndView(url);	
	/*	PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramPageModel);
		List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
		pageMAV.addObject("list", list);
		pageMAV.setViewName("WM/ZK/WMSHY1");*/
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
//		return new ModelAndView("redirect:WM/ZK/WMKZK1"); 
	}
	
	/**
	 * 更新一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
	@RequestMapping(value = "/U.go" )
	public ModelAndView doUpdate(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		paramBean.setUu2("a");
		int result = getService().doUpdate(paramBean);
		String url = "redirect:F.go";
	   	pageMAV = new ModelAndView(url);	
//		pageMAV.setViewName("wm/WMZHY2");
/*		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
//		pageMAV.addObject(paramPageModel);
		List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
		pageMAV.addObject("list", list);
		pageMAV.setViewName("WM/ZK/WMSHY1");*/
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
}
