package com.ttnn.business.wm.controller.zk;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSPR1Service;
import com.ttnn.business.cs.service.CSSR01Service;
import com.ttnn.business.cs.service.CSSR03Service;
import com.ttnn.business.cs.service.CSSS01Service;
import com.ttnn.business.wm.biz.WMFZGLService;
import com.ttnn.business.wm.biz.WMZKJGService;
import com.ttnn.business.wm.service.WMBMA1Service;
import com.ttnn.common.util.PKUtil;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMKZ04")
/**管理员管理*/
public class WMKZ04Controller extends MyControllerSupportImpl {
	
	
	@Resource
	protected WMFZGLService  WMFZGLService_;
	
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
   
    @Resource
    protected WMZKJGService WMZKJGService_;

    @Override
    public MyServiceSupportImpl getService(){
        return CSSR01Service_;
    }
    
	@Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMKZ04Controller.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("WM/ZK/WMKZA2");
    }
    
    @Override
    @RequestMapping(value = "/H.go" )
    public ModelAndView home(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageMAV.setViewName("WM/ZK/WMKZA2");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

    @RequestMapping(value = "/E.go" )
	public ModelAndView home1(CSPVOSupport paramBean) {
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		pageMAV.setViewName("WM/ZK/WMKZA2");
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}    
    
    @RequestMapping(value = "/A.go" )
   	public ModelAndView home2(CSPVOSupport paramBean) {
   		getLogger().debug("paramBean" + paramBean);
   		ModelAndView pageMAV = getModelAndView();
   		pageMAV.setViewName("WM/ZK/WMKZA1L");
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
//    @trancsactional
    public ModelAndView doInsert(CSPVOSupport paramBean) {
    	ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);		
		//查询系统通道				
				CSPVOSupport c5= new CSPVOSupport();				
				c5.setEb5("TTNN_DFB");
				pageVO.setOrderby("f03");
				pageVO.setPageData(c5);
				pageVO.setPageLimit(Integer.MAX_VALUE);
				pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
				WMBMA1Service_.doSelectPage(pageVO);			
		//用户登录
		CSPVOSupport c2 = new CSPVOSupport();
		c2.setPuk(PKUtil.getPUKey());
		c2.setF01(paramBean.getFb1());//管理员ID用户名
		c2.setF02("Y");
		c2.setEb5(paramBean.getPuk());
		c2.setF03(paramBean.getFb1());//管理员ID用户名
		c2.setF04(new Md5PasswordEncoder().encodePassword(paramBean.getF04(), null));//设置密码

		c2.setF05("WM.HT");
		c2.setF06("1");
		//保存结果
		CSSR01Service_.doInsert(c2);
		//用户授权
		CSPVOSupport c3 = new CSPVOSupport();
		c3.setPuk(c2.getPuk());
		c3.setEb5(paramBean.getPuk());
		c3.setK01(paramBean.getF09());
		CSSPR1Service_.doInsert(c3);
		//系统授权
		//TODO
		CSPVOSupport c4 = new CSPVOSupport();
		c4.setK03(paramBean.getFb1());//设置k03用户名
		c4.setPuk(paramBean.getPuk());
		CSSS01Service_.doUpdate(c4);
		System.out.println(c4.getEb5());		
		pageMAV.addObject("c4", c4);	
		pageMAV.addObject("eb5", paramBean.getEb5());
		pageMAV.addObject(pageVO);
		pageMAV.setViewName("WM/ZK/WMKZK3");	
		getLogger().debug("pageMAV===>>>" + pageMAV);
    	return pageMAV;
    }

    /**
     *添加超级管理员
     */
    @RequestMapping(value = "/CX.go" )
    public ModelAndView doINSERT(CSPVOSupport paramBean) {
    	ModelAndView pageMAV = getModelAndView();
    	CSPVOSupport pb1 = new CSPVOSupport();
    	pb1.setPuk(PKUtil.getPUKey());
    	pb1.setF01(paramBean.getF03());
    	pb1.setF02("Y");    	
    	pb1.setF03(paramBean.getF03());
    	pb1.setF04(new Md5PasswordEncoder().encodePassword(paramBean.getF04(), null));//设置密码
    	pb1.setEb5(paramBean.getEb5());
    	pb1.setF05("WM.HT");
    	pb1.setF06("1");
		//保存结果
		CSSR01Service_.doInsert(pb1);
//		pageVO.setPageData(paramBean);	
		pageMAV=new ModelAndView("redirect:/WMKZ01/F.go");

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
		paramBean.setEb5(super.getProductId());
		pageVO.setPageData(paramBean);
        pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
        WMFZGLService_.doSelectPageGL(pageVO);
	    pageMAV.addObject(pageVO);	
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
	@RequestMapping(value = "/R.go" )
	public ModelAndView doRead(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
//		PageVO paramPageModel = new PageVO();
//		paramPageModel.setPageData(paramBean);
//		pageMAV.addObject(paramBean);
		pageMAV.addObject("parambean", getService().doRead(paramBean));
		pageMAV.setViewName("WM/ZK/WMKZA2");
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
	/*	PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramPageModel);
		List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
		pageMAV.addObject("list", list);
		pageMAV.setViewName("WM/ZK/WMKZA1");*/
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
		String url = "redirect:/WMKZ01/F.go";
	   	pageMAV = new ModelAndView(url);
	/*	PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
//		pageMAV.addObject(paramPageModel);
		List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
		pageMAV.addObject("list", list);
		pageMAV.setViewName("WM/ZK/WMKZA1");*/
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
}
