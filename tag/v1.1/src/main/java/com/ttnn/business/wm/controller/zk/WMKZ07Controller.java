package com.ttnn.business.wm.controller.zk;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSPR1Service;
import com.ttnn.business.cs.service.CSSR01Service;
import com.ttnn.business.cs.service.CSSR03Service;
import com.ttnn.business.cs.service.CSSS01Service;
import com.ttnn.business.wm.service.WMBMA1Service;
import com.ttnn.business.wm.service.WMUIP1Service;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMKZ07")
/**vip*/
public class WMKZ07Controller extends MyControllerSupportImpl {
    @Resource
    protected CSSR01Service CSSR01Service_;//允许用户登录
    
    @Resource
    protected CSSS01Service CSSS01Service_;//系统授权信息
    
    @Resource
    protected CSSR03Service CSSR03Service_;//用户组定义
    
    @Resource
    protected WMUIP1Service WMUIP1Service_;//VIP等级定义
    
    @Resource
    protected CSSPR1Service CSSPR1Service_;//系统用户所属用户组信息
    
    @Resource
    protected WMBMA1Service WMBMA1Service_;//支付通道（系统）   

//    @Resource
//    protected CSSP01Service CSSP01Service_;//人员信息
    
    @Override
    public MyServiceSupportImpl getService(){
        return CSSR01Service_;
    }
    
	@Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMKZ07Controller.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("WM/ZK/WMKZV2");
    }
    
    @Override
    @RequestMapping(value = "/H.go" )
    public ModelAndView home(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageMAV.setViewName("WM/ZK/WMKZV2");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

    @RequestMapping(value = "/E.go" )
	public ModelAndView home1(CSPVOSupport paramBean) {
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		pageMAV.setViewName("WM/ZK/WMKZV2");
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}
    
//    @RequestMapping(value = "/A.go")
//	public ModelAndView home3(CSPVOSupport paramBean) {
//		getLogger().debug("paramBean" + paramBean);
//		ModelAndView pageMAV = getModelAndView();
//		pageMAV.setViewName("WM/ZK/WMKZ");
//		getLogger().debug("pageMAV" + pageMAV);
//		return pageMAV;
//	}
    
    @RequestMapping(value = "/A.go" )
   	public ModelAndView home2(CSPVOSupport paramBean) {
   		getLogger().debug("paramBean" + paramBean);
   		ModelAndView pageMAV = getModelAndView();
   		pageMAV.setViewName("WM/ZK/WMKZV1");
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
		WMUIP1Service_.doInsert(paramBean);	
		String url = "redirect:F.go";
	   	pageMAV = new ModelAndView(url);	  
	/*	PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramPageModel);
		pageMAV.addObject("list", WMUIP1Service_.doFindList(paramPageModel));*/	
		pageMAV.setViewName("WM/ZK/WMKZV1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
    	return pageMAV;
    }
    
    /**
	 * 通道分配
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
//	@RequestMapping(value = "/C1.go" )
	public void doInsert1(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
//		int result = getService().doFindList(formParamPageModel)
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
//		List<FrameworkDataBean> list1 = WMBMA1Service_.doFindList(paramPageModel);
//		pageMAV.addObject("list", getService().doFindList(paramPageModel));	
//		pageMAV.addObject("list1",list1);
//		System.out.println(list1.isEmpty());
		pageMAV.setViewName("WM/ZK/WMKZV1");
		getLogger().debug("pageMAV===>>>" + pageMAV);

	}
    
    /**
	 * 插入一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
	 */
	/**@RequestMapping(value = "/C.go" )
	public ModelAndView doInsert(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
//		paramBean.setCc2("oo");
		int result = getService().doInsert(paramBean);
		if (result == 1) {
			pageMAV.addObject("message", paramBean);
		} else if (result == -1) {
			pageMAV.addObject("message", "failure");
		} else {
			pageMAV.addObject("message", "failure");
		}			
		try {
			FrameworkDataBean c2 = (FrameworkDataBean) BeanUtils.cloneBean(paramBean);
			c2.setK03(paramBean.getPuk());
			CSSS01Service_.doInsert((CSPVOSupport) c2);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}	
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
//		pageMAV.addObject(paramPageModel);
		List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
		pageMAV.addObject("list", list);
		pageMAV.setViewName("WM/ZK/WMKZA1");		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}*/
    
    
	
	/**
	 * 插入一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
	 */
	/*@RequestMapping(value = "/CC.go")
	public ModelAndView doInsert1(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean" + paramBean);
	//	int result = getService().doInsert(paramBean);
//		if (result == 1) {
//			pageMAV.addObject("obj", paramBean);
//		} else if (result == -1) {
//			pageMAV.addObject("obj", "failure");
//		} else {
//			pageMAV.addObject("obj", "failure");
//		}
//		CSPVOSupport c2 = (CSPVOSupport)BeanUtils.cloneBean(paramBean);		
       try {
			CSPVOSupport c2 = (CSPVOSupport)BeanUtils.cloneBean(paramBean);
			c2.setF03(null);
			c2.setK01(null);
			c2.setK02(null);
			c2.setK03(null);
//			getService_().doInsert(c2);
			PageVO paramPageModel = new PageVO();
			paramPageModel.setPageData(c2);
			pageMAV.addObject(paramPageModel);
			List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
			pageMAV.addObject("list", list);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		try {
			CSPVOSupport c1 = (CSPVOSupport)BeanUtils.cloneBean(paramBean);
			c1.setF01(null);
			c1.setF02(null);
			getService().doInsert(c1);
			PageVO paramPageModel = new PageVO();
			paramPageModel.setPageData(c1);
			pageMAV.addObject(paramPageModel);
			List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
			pageMAV.addObject("list", list);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageMAV.setViewName("WM/QT/WMXXX2");
		getLogger().debug("pageMAV" + pageMAV);
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
		//TODO
		paramBean.setEb5(paramBean.getEb5());
		pageVO.setPageData(paramBean);
        pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
        WMUIP1Service_.doSelectPage(pageVO);	
	    pageMAV.addObject(pageVO);	
	/*	PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramPageModel);
		pageMAV.addObject("list", WMUIP1Service_.doFindList(paramPageModel));*/
		pageMAV.setViewName("WM/ZK/WMKZV1");
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
			pageMAV.setViewName("WM/ZK/WMKZV1");	
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
		pageMAV.addObject(paramBean);
		pageMAV.addObject("parambean1", WMUIP1Service_.doRead(paramBean));
		pageMAV.setViewName("WM/ZK/WMKZV2");
//		pageMAV.setViewName("WM/ZK/WMXGM");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 查询一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doRead(CSPVOSupport)
	 */
	@RequestMapping(value = "/M.go" )
	public ModelAndView doRead1(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramBean);
		pageMAV.addObject("parambean1", getService().doRead(paramBean));
//		pageMAV.setViewName("WM/ZK/WMKZA2");
		pageMAV.setViewName("WM/ZK/WMXGM");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

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
		int result = WMUIP1Service_.toDelete(paramBean);
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
		List<FrameworkDataBean> list = WMUIP1Service_.doFindList(paramPageModel);
		System.out.println(list.size());
		for (FrameworkDataBean frameworkDataBean : list) {
			System.out.println(frameworkDataBean.toString());
		}
		pageMAV.addObject("list", list);
		pageMAV.setViewName("WM/ZK/WMKZV1");*/
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
		int result = WMUIP1Service_.doUpdate(paramBean);
		String url = "redirect:F.go";
	   	pageMAV = new ModelAndView(url);	
//		pageMAV.setViewName("wm/WMZHY2");
/*		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
//		pageMAV.addObject(paramPageModel);
		List<FrameworkDataBean> list = WMUIP1Service_.doFindList(paramPageModel);
		pageMAV.addObject("list", list);
		pageMAV.setViewName("WM/ZK/WMKZV1");*/
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
}
