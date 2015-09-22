package com.ttnn.framework.support.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSModelAndViewSupport;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISControllerSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;

/**
 * 页面控制器基类
 * 
 * 请覆盖对应方法
 * 
 * 参数说明
 * @param paramBean 页面数据
 * @param pageMAV	 页面视图
 * 
 * 
 * 基本方法示例代码
   	//设定画面返回数据
	pageMAV.addObject("serverTime", "" );
	//设定跳转页面
	pageMAV.setViewName("home");
	//采用重定向方式跳转页面
	pageMAV = new CSModelAndViewSupport(new RedirectView("home"));
 *
 * @author Spook
 * @since 0.1.0 2013-8-21
 * @version 0.1.0
 * @see ISControllerSupport
 */
public class MyControllerSupportImpl extends MyBusinessSupportImpl implements ISControllerSupport {

	@Resource
	protected PageVO pageVO;
	
	/**
	 * 获得业务处理对象
	 * 
	 * @return
	 */
	public MyServiceSupportImpl getService() {
		return new MyServiceSupportImpl();
	}

	/**
	 * 页面视图
	 * 
	 * @return
	 */
	public ModelAndView getModelAndView() {
		return new CSModelAndViewSupport("home");
	}

	/**
	 * 获得系统日志输出对象
	 * 
	 * @return
	 */
	public Logger getLogger() {
		return LoggerFactory.getLogger(MyControllerSupportImpl.class);
	}

//	@RequestMapping(value = "/H.go", method = RequestMethod.GET)
	public ModelAndView home(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		//TODO
		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	// //////////////////////////////////////////////////////////////////////
//	/**
//	 * 批量删除记录
//	 * 
//	 * @param paramBean
//	 * @deprecated
//	 */
////	@RequestMapping(value = "/B.go", method = RequestMethod.POST)
//	public ModelAndView doBatchDelete(CSPVOSupport paramBean) {
//		ModelAndView pageMAV = getModelAndView();
//		getLogger().debug("paramBean===>>>" + paramBean);
//
//		//TODO
//		PageVO paramPageModel = new PageVO();
//		// 设定页面参数
//		paramPageModel.setPageData(paramBean);
//		// 调用业务逻辑
//		getService().doBatchDelete(paramPageModel);
//		// 设定返回数据用于显示
//		pageMAV.addObject(paramPageModel);
//
//		getLogger().debug("pageMAV===>>>" + pageMAV);
//		return pageMAV;
//	}
//
//	/**
//	 * 批量插入记录
//	 * 
//	 * @param paramBean
//	 * @deprecated
//	 */
////	@RequestMapping(value = "/A.go", method = RequestMethod.POST)
//	public ModelAndView doBatchInsert(CSPVOSupport paramBean) {
//		ModelAndView pageMAV = getModelAndView();
//		getLogger().debug("paramBean===>>>" + paramBean);
//		
//		//TODO
//		PageVO paramPageModel = new PageVO();
//		// 设定页面参数
//		paramPageModel.setPageData(paramBean);
//		// 调用业务逻辑
//		getService().doBatchInsert(paramPageModel);
//		// 设定返回数据用于显示
//		pageMAV.addObject(paramPageModel);
//		
//		getLogger().debug("pageMAV===>>>" + pageMAV);
//		return pageMAV;
//	}

	/**
	 * 数据一览
	 * 
	 * @param paramBean
	 * @return
	 * @see ISSQLDaoSupport#doFindList(CSPVOSupport)
	 */
//	@RequestMapping(value = "/F.go", method = RequestMethod.POST)
	public ModelAndView doFindList(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		//TODO
		pageVO = new PageVO();
		// 设定页面参数
		pageVO.setPageData(paramBean);
		// 调用业务逻辑
		getService().doSelectPage(pageVO);
		// 设定返回数据用于显示
		pageMAV.addObject(pageVO);
		
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
//	@RequestMapping(value = "/L.go", method = RequestMethod.POST)
	public ModelAndView doSelectPage(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		//TODO
		PageVO paramPageModel = new PageVO();
		// 设定页面参数
		paramPageModel.setPageData(paramBean);
		// 调用业务逻辑
		getService().doSelectPage(paramPageModel);
		// 设定返回数据用于显示
		pageMAV.addObject(paramPageModel);
		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	//////////////////////////////////////////////////
//	/**
//	 * 根据条件更新所有记录<br>
//	 * 添加与结果不能使用同一个字段<br>
//	 * 如果需要，请自行修改对应方法<br>
//	 * 参考XML#doUpdateAll
//	 * 
//	 * @param paramBean
//	 * @return
//	 * @see ISSQLDaoSupport#doFindList(CSPVOSupport)
//	 */
////	@RequestMapping(value = "/F.go", method = RequestMethod.POST)
//	public ModelAndView doUpdateAll(CSPVOSupport paramBean) {
//		ModelAndView pageMAV = getModelAndView();
//		getLogger().debug("paramBean===>>>" + paramBean);
//
//		//TODO
//		getService().doUpdateAll(paramBean);
//		
//		getLogger().debug("pageMAV===>>>" + pageMAV);
//		return pageMAV;
//	}
	
	// //////////////////////////////////////////////////////////////////////
	/**
	 * 插入一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
	 */
//	@RequestMapping(value = "/C.go", method = RequestMethod.POST)
	public ModelAndView doInsert(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		//TODO
		getService().doInsert(paramBean);
		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 更新一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
//	@RequestMapping(value = "/U.go", method = RequestMethod.POST)
	public ModelAndView doUpdate(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		
		//TODO
		getService().doUpdate(paramBean);
		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 查询一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doRead(CSPVOSupport)
	 */
//	@RequestMapping(value = "/R.go", method = RequestMethod.POST)
	public ModelAndView doRead(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		//TODO
		getService().doRead(paramBean);
		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 删除一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doBatchDelete(java.util.List)
	 */
//	@RequestMapping(value = "/D.go", method = RequestMethod.POST)
	public ModelAndView doDelete(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		
		//TODO
		getService().doDelete(paramBean);
		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
}