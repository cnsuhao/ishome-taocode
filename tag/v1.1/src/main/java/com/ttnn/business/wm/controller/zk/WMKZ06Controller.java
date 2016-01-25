package com.ttnn.business.wm.controller.zk;

import java.util.List;

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
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMKZ06")
/**用户组*/
public class WMKZ06Controller extends MyControllerSupportImpl {
    @Resource
    protected CSSR01Service CSSR01Service_;//允许用户登录
    
    @Resource
    protected CSSS01Service CSSS01Service_;//系统授权信息
    
    @Resource
    protected CSSR03Service CSSR03Service_;//用户组定义
    
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
        return LoggerFactory.getLogger(WMKZ06Controller.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("WM/ZK/WMKZH2");
    }
    
    @Override
    @RequestMapping(value = "/H.go" )
    public ModelAndView home(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		
		//查询当前客户的用户组
		pageVO.setPageLimit(Integer.MAX_VALUE);
		FrameworkDataBean dbParamBean = new FrameworkDataBean ();
		dbParamBean.setEb5(paramBean.getEb5());
		pageVO.setPageData(dbParamBean);
		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		CSSR03Service_.doSelectPage(pageVO);
		//查询用户组分类
		dbParamBean = new FrameworkDataBean ();
		//定义中分类
		dbParamBean.setK02("DFB.XTFL");
		//定义产品发行内部识别ID
		dbParamBean.setEb5("TTNN_DFB");
		//获得 字典数据	
		List<FrameworkDataBean>  list=getDictionaryDefault(dbParamBean);
		pageMAV.addObject(pageVO);
		pageMAV.addObject("list", pageVO.getPageData());
		pageMAV.addObject("list1", list);	
		pageMAV.addObject("eb5",paramBean.getEb5());
		paramBean.setPuk(paramBean.getEb5());
		//pageMAV.addObject("parambean",paramBean);
		//画面跳转
		pageMAV.setViewName("WM/ZK/WMKZH2");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
    
    
    /**
     * 准备添加超级管理员
     * @param paramBean
     * @return
     */
    @RequestMapping(value = "/E.go" )
	public ModelAndView home1(CSPVOSupport paramBean) {
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
	/*	System.out.println("dffd");
		System.out.println(paramBean.getEb5());
		System.out.println("dffd");
		pageVO.setPageData(paramBean);*/
//		System.out.println(paramBean.getEb5());
		
		CSPVOSupport  pbr = new CSPVOSupport();
		pbr.setPuk(paramBean.getEb5());
		FrameworkDataBean db =CSSS01Service_.doRead(pbr);
		pageMAV.addObject("parambean", db);
		pageMAV.addObject("eb5",paramBean.getEb5());
		
		
		
		pageMAV.setViewName("WM/ZK/WMKZA2");
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
   		pageMAV.setViewName("WM/ZK/WMKZH1");
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
		pageVO.setOrderby("f02");
		CSSR03Service_.doInsert(paramBean);	
		String url = "redirect:F.go?eb5="+paramBean.getEb5();
	   	pageMAV.setViewName(url);
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
		pageMAV.addObject(pageVO);
		pageMAV.addObject("list", CSSR03Service_.doSelectPage(pageVO).getPageData());		
		FrameworkDataBean 	dbParamBean = new FrameworkDataBean ();
		//定义中分类
		dbParamBean.setK02("DFB.XTFL");
		//定义产品发行内部识别ID
		dbParamBean.setEb5("TTNN_DFB");
		//获得 字典数据	
		List<FrameworkDataBean>  list=getDictionaryDefault(dbParamBean);
		pageMAV.addObject("list1",list);				
		pageMAV.addObject("eb5", paramBean.getEb5());
		pageMAV.setViewName("WM/ZK/WMKZH1");
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
			pageVO.setPageData(paramBean);
			pageVO = CSSR03Service_.doSelectPage(pageVO);
			pageMAV.addObject("list", pageVO.getPageData());
			pageMAV.addObject("pagecount", pageVO.getPageCount());//总页数
			pageMAV.addObject("pagecurrent", pageVO.getPageCurrent());//当前页
			pageMAV.addObject("pagelimit", pageVO.getPageLimit());//每页记录数
			pageMAV.addObject("totalcount", pageVO.getResultCount());//总记录数
			pageMAV.setViewName("WM/ZK/WMKZH1");	
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
//		CSPVOSupport paramBean1=(CSPVOSupport) CSSR03Service_.doRead(paramBean);
//		System.out.println(paramBean1.toString());
		pageVO.setPageData(paramBean);
		pageMAV.addObject(paramBean);
		pageMAV.addObject("parambean1", CSSR03Service_.doRead(paramBean));
		pageMAV.setViewName("WM/ZK/WMKZH2");
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
		pageMAV.addObject(paramBean);
		pageMAV.addObject("parambean1", CSSR03Service_.doRead(paramBean));
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
		int result = CSSR03Service_.toDelete(paramBean);
		if (result > 0) {
			pageMAV.addObject("message", "success");
		} else if (result <= 0) {
			pageMAV.addObject("message", "删除失败");
		} else {
			pageMAV.addObject("message", "删除失败");
		}
		String url = "redirect:F.go";
	   	pageMAV = new ModelAndView(url);
/*		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramPageModel);
		List<FrameworkDataBean> list = CSSR03Service_.doFindList(paramPageModel);
		pageMAV.addObject("list", list);*/
//		pageMAV.setViewName("WM/ZK/WMKZH1");
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
		int result = CSSR03Service_.doUpdate(paramBean);
		String url = "redirect:F.go";
	   	pageMAV = new ModelAndView(url);	  
//		pageMAV.setViewName("wm/WMZHY2");
	/*	PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
//		pageMAV.addObject(paramPageModel);
		List<FrameworkDataBean> list = CSSR03Service_.doFindList(paramPageModel);
		pageMAV.addObject("list", list);
		pageMAV.setViewName("WM/ZK/WMKZH1");*/
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
}
