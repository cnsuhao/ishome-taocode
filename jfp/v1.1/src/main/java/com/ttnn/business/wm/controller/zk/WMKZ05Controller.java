package com.ttnn.business.wm.controller.zk;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSL11Service;
import com.ttnn.business.cs.service.CSSM01Service;
import com.ttnn.business.cs.service.CSSR01Service;
import com.ttnn.business.wm.biz.WMFZGLService;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMKZ05")
/** 用户登录日志*/
public class WMKZ05Controller extends MyControllerSupportImpl {

	@Resource
	protected CSSM01Service CSSM01Service_;//系统公告
	
	@Resource
	protected WMFZGLService WMFZGLService_;
	
    @Resource
    protected CSSL11Service CSSL11Service_;//登录日志
    
    @Resource
    protected CSSR01Service CSSR01Service_;

    @Override
    public MyServiceSupportImpl getService(){
        return CSSL11Service_;
    }

    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMKZ05Controller.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("WM/ZK/WMKZL1");
    }
    
    @Override
    @RequestMapping(value = "/H.go" )
    public ModelAndView home(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageMAV.setViewName("WM/ZK/WMKZL1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

    @RequestMapping(value = "/A.go" )
	public ModelAndView home1(CSPVOSupport paramBean) {
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		pageMAV.setViewName("WM/ZK/WMKZL1L");
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
			pageMAV.addObject("obj", paramBean);
		} else if (result == -1) {
			pageMAV.addObject("obj", "failure");
		} else {
			pageMAV.addObject("obj", "failure");
		}
		String url = "redirect:F.go";
	   	pageMAV = new ModelAndView(url);	  
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 数据一览
	 */
	@RequestMapping(value = "/F.go" )
	public ModelAndView doFindList(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
//		paramBean.setEb5(super.getProductId());
//		System.out.println(paramBean.getF07()+""+paramBean.getF10()+""+
//				paramBean.getK01()+""+paramBean.getK02());
		CSPVOSupport pb1=new CSPVOSupport();
		pb1.setEb5(super.getProductId());
		pb1.setF07(paramBean.getF07());
		pb1.setK01(paramBean.getK01());
		pb1.setK02(paramBean.getK02());
		pb1.setF10(paramBean.getF10());
		pageVO.setPageData(pb1);
        pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
        WMFZGLService_.doSelectPageRZ(pageVO);
	    pageMAV.addObject(pageVO);
		pageMAV.setViewName("WM/ZK/WMKZL1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
/*	@RequestMapping(value = "/F.go" )
	public ModelAndView doUpdate1(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);	
		pageVO.setPageData(paramBean);
		CSSM01Service_.doSelectPage(pageVO);
		pageMAV.addObject(pageVO);
		pageMAV.setViewName("AQ/CSSM01R1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}*/
       
	
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
			pageMAV.setViewName("WM/ZK/WMKZL1");	
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
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
//		pageMAV.addObject(paramBean);
		pageMAV.addObject("parambean1", getService().doRead(paramBean));
		pageMAV.setViewName("WM/ZK/WMKZL1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 删除一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doBatchDelete(java.util.List)
	 */
/*	@RequestMapping(value = "/D.go")
	public ModelAndView doDelete(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
//		int result = getService().doDelete(paramBean);
		int result = getService().toDelete(paramBean);
		if (result > 0) {
			pageMAV.addObject("message", "success");
		} else if (result <= 0) {
			pageMAV.addObject("message", "failure");
		} else {
			pageMAV.addObject("message", "failure");
		}
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
//		pageMAV.addObject(paramPageModel);
		List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
		pageMAV.addObject("list", list);
		pageMAV.setViewName("WM/ZK/WMKZL1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}*/
	
	/**
	 * 删除一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doBatchDelete(java.util.List)
	 */
	@RequestMapping(value = "/D.go" )
	public ModelAndView doDelete(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
//		int result = getService().doDelete(paramBean);
		int result = getService().toDelete(paramBean);
		if (result > 0) {
			pageMAV.addObject("message", "success");
		} else if (result <= 0) {
			pageMAV.addObject("message", "删除失败");
		} else {
			pageMAV.addObject("message", "删除失败");
		}
		String url = "redirect:F.go";
	   	pageMAV = new ModelAndView(url);	  
	/*PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramPageModel);
		List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
		pageMAV.addObject("list", list);
		pageMAV.setViewName("WM/ZK/WMKZL1");*/
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
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
		int result = getService().doUpdate(paramBean);
		String url = "redirect:F.go";
	   	pageMAV = new ModelAndView(url);	  
//		pageMAV.setViewName("wm/WMZHY2");
/*		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
//		pageMAV.addObject(paramPageModel);
		List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
		pageMAV.addObject("list", list);
		pageMAV.setViewName("WM/ZK/WMKZL1");*/
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
}
