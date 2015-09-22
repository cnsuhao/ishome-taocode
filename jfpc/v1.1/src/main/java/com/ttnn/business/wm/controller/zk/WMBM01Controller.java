package com.ttnn.business.wm.controller.zk;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.biz.WMQTTJXXBusiness;
import com.ttnn.business.wm.service.WMBM01Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMBM01")
/**
 * 只需更新.sql和.xml文件
 * 
 * 查询时需要设置setDdd为0,调用doSelectPage()方法；
 */

/** 支付通道*/
public class WMBM01Controller extends MyControllerSupportImpl {
	@Resource
	protected WMBM01Service WMBM01Service_;
	
	@Resource
	protected WMQTTJXXBusiness WMQTTJXXBusiness_;

	@Override
	public MyServiceSupportImpl getService() {
		return WMBM01Service_;
	}

	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMBM01Controller.class);
	}

	@Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("WM/ZK/WMJGD2");
	}

	@Override
	@RequestMapping(value = "/H.go")
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
	@RequestMapping(value = "/C.go" )
	public ModelAndView doInsert(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean" + paramBean);
		int result = getService().doInsert(paramBean);
		if (result == 1) {
			pageMAV.addObject("message", paramBean);
		} else if (result == -1) {
			pageMAV.addObject("message", "failure");
		} else {
			pageMAV.addObject("message", "failure");
		}
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramPageModel);
//		List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
//		pageMAV.addObject("list", list);
		pageMAV.setViewName("WM/ZK/WMJGD1");
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
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramPageModel);
//		pageMAV.addObject("list", getService().doFindList(paramPageModel));
		pageMAV.setViewName("WM/ZK/WMJGD1");
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
	@RequestMapping(value = "/D.go" )
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
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramPageModel);
//		ageMAV.addObject("list", list);
		pageMAV.setViewName("WM/ZK/WMJGD1");
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
	@RequestMapping(value = "/U.go" )
	public ModelAndView doUpdate(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		// 查询得到该记录，加载至“cs/sb03c1”页面
		int result = getService().doUpdate(paramBean);

		pageMAV.setViewName("wm/WMXXZ2");
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramPageModel);
//		ageMAV.addObject("list", list);
		pageMAV.setViewName("WM/ZK/WMJGD1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
    
	
	
	
    //给用户XXX分配通道
	@RequestMapping(value = "/{userid}/P.go")
	public ModelAndView doRoad(CSPVOSupport param,@PathVariable String userid){
		ModelAndView pageMAV = getModelAndView();
		param.setK01(userid); //用户ID
	    param.setK02(super.getLoginerId());  //上级用户ID
//		分配过的通道去掉
		param.setK01(userid);
		List<FrameworkDataBean> systemRoad = null;
		try{			
			systemRoad = WMQTTJXXBusiness_.doSelectUnDis(param);
		}catch(Exception e){
			e.printStackTrace();
		}
		pageMAV.addObject("puk", userid);
		pageMAV.addObject("list", systemRoad);
		pageMAV.setViewName("WM/QT/WMXXX3");
		return pageMAV;
	}
	
	

	
	
	/**
	 * 查询一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doSelect(CSPVOSupport)
	 */
	@Override
	@RequestMapping(value = "/R.go" )
	public ModelAndView doRead(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramBean);

		pageMAV.addObject("parambean1", getService().doRead(paramBean));
		System.out.println();
		pageMAV.setViewName("WM/ZK/WMJGD2");
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
	@RequestMapping(value = "/L.go" )
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
		pageMAV.setViewName("WM/ZK/WMJGD1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	
	
}
