package com.ttnn.business.pt.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSR01Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMXG01")
/**修改密码*/
public class WMXG01Controller extends MyControllerSupportImpl {
    @Resource
    protected CSSR01Service CSSR01Service_;

    @Override
    public MyServiceSupportImpl getService(){
        return CSSR01Service_;
    }
    
	@Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMXG01Controller.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("WM/ZK/WMKZA2");
    }
    
    @Override
    @RequestMapping(value = "/H.go", method = RequestMethod.GET)
    public ModelAndView home(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageMAV.setViewName("WM/ZK/WMXGM");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

    @RequestMapping(value = "/A.go")
	public ModelAndView home1(CSPVOSupport paramBean) {
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		pageMAV.setViewName("WM/ZK/WMKZ");
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}
    
    @RequestMapping(value = "/B.go")
   	public ModelAndView home2(CSPVOSupport paramBean) {
   		getLogger().debug("paramBean" + paramBean);
   		ModelAndView pageMAV = getModelAndView();
   		pageMAV.setViewName("WM/ZK/WMKA1L");
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
			pageMAV.addObject("message", paramBean);
		} else if (result == -1) {
			pageMAV.addObject("message", "failure");
		} else {
			pageMAV.addObject("message", "failure");
		}	 
		
		pageVO.setPageLimit(Integer.MAX_VALUE);
		pageVO.setPageData(paramBean);
		pageMAV.addObject(pageVO);		
		pageMAV.addObject("list", getService().doSelectPage(pageVO).getPageData());
		pageMAV.setViewName("WM/ZK/WMKZA1");		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 插入一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
	 */
	@RequestMapping(value = "/CC.go")
	public ModelAndView doInsert1(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean" + paramBean);
//  
//			CSPVOSupport c2 = (CSPVOSupport)BeanUtils.cloneBean(paramBean);
//			c2.setF03(null);
//			c2.setK01(null);
//			c2.setK02(null);
//			c2.setK03(null);
//
//			pageVO.setPageLimit(Integer.MAX_VALUE);
//			pageVO.setPageData(c2);
//			pageMAV.addObject(pageVO);		
//			pageMAV.addObject("list", getService().doSelectPage(pageVO).getPageData());
//		
//		
//			CSPVOSupport c1 = (CSPVOSupport)BeanUtils.cloneBean(paramBean);
//			c1.setF01(null);
//			c1.setF02(null);
//			getService().doInsert(c1);
			
//			pageVO.setPageLimit(Integer.MAX_VALUE);
//			pageVO.setPageData(c1);
//			pageMAV.addObject(pageVO);		
//			pageMAV.addObject("list1", getService().doSelectPage(pageVO).getPageData());

		pageMAV.setViewName("WM/QT/WMXXX2");
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 数据一览
	 */
	@Override
	@RequestMapping(value = "/F.go")
	public ModelAndView doFindList(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
//		PageVO paramPageModel = new PageVO();
//		paramPageModel.setPageData(paramBean);
//		pageMAV.addObject(paramPageModel);
//		pageMAV.addObject("list", getService().doFindList(paramPageModel));
		pageMAV.setViewName("WM/ZK/WMKZA1");
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
	@RequestMapping(value = "/L.go", method = RequestMethod.POST)
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
			pageMAV.setViewName("WM/ZK/WMKZA1");	
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	/**
	 * 查询一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doRead(CSPVOSupport)
	 */
	@RequestMapping(value = "/R.go")
	public ModelAndView doRead(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramBean);
		pageMAV.addObject("parambean1", getService().doRead(paramBean));
		pageMAV.setViewName("WM/ZK/WMKZA2");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 删除一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doBatchDelete(java.util.List)
	 */
	@RequestMapping(value = "/D.go")
	public ModelAndView doDelete(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		int result = getService().doDelete(paramBean);
		if (result > 0) {
			pageMAV.addObject("message", "success");
		} else if (result <= 0) {
			pageMAV.addObject("message", "删除失败");
		} else {
			pageMAV.addObject("message", "删除失败");
		}
//		PageVO paramPageModel = new PageVO();
//		paramPageModel.setPageData(paramBean);
//		pageMAV.addObject(paramPageModel);
//		List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
//		pageMAV.addObject("list", list);
		pageMAV.setViewName("WM/ZK/WMKZA1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 更新一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
	@RequestMapping(value = "/U.go", method = RequestMethod.POST)
	public ModelAndView doUpdate(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
//		getLogger().debug("paramBean===>>>" + paramBean);
//		int result = getService().doUpdate(paramBean);
////		pageMAV.setViewName("wm/WMZHY2");
//		PageVO paramPageModel = new PageVO();
//		paramPageModel.setPageData(paramBean);
////		pageMAV.addObject(paramPageModel);
//		List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
//		pageMAV.addObject("list", list);
		pageMAV.setViewName("WM/ZK/WMKZA1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 管理员密码修改
	 */
	/*@RequestMapping(value = "/U.go", method = RequestMethod.POST)
	public ModelAndView updatePwd(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		/*getService().doRead(paramBean);
		try {
			CSPVOSupport c2 = (CSPVOSupport)BeanUtils.cloneBean(paramBean);
			
			c2.setF04("");
			PageVO paramPageModel = new PageVO();
			paramPageModel.setPageData(c2);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}*/
		/*getLogger().debug("pageMAV===>>>" + pageMAV);
		pageMAV.setViewName("WM/ZK/WMKZA1");
		return pageMAV;
	}*/
}
