package com.ttnn.business.wm.controller.zk;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSR03Service;
import com.ttnn.business.wm.biz.WMZKJGService;
import com.ttnn.business.wm.service.WMBM01Service;
import com.ttnn.business.wm.service.WMBMA1Service;
import com.ttnn.business.wm.service.WMUIP1Service;
import com.ttnn.common.util.PKUtil;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMJG01")
/**通道管理*/
public class WMJG01Controller extends MyControllerSupportImpl {

    @Resource
    protected WMBMA1Service WMBMA1Service_;//支付通道（系统）

    @Resource
    protected WMBM01Service WMBM01Service_;//支付通道（个人）
    
    @Resource
    protected WMUIP1Service WMUIP1Service_;//VIP等级定义
    
    @Resource
    protected CSSR03Service CSSR03Service_;//用户组定义
    
    @Resource
    protected WMZKJGService  WMZKJGService_;
    
    Boolean flag=false;
  
    public MyServiceSupportImpl getService(){
        return WMBMA1Service_;
    }

    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMJG01Controller.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("WM/ZK/WMKZK3");
    }
    
    @Override
    @RequestMapping(value = "/H.go" )
    public ModelAndView home(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageMAV.setViewName("WM/ZK/WMKZK3");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

    @RequestMapping(value = "/A.go" )
	public ModelAndView home1(CSPVOSupport paramBean) {
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		paramBean.setEb5(super.getProductId());
		pageVO.setPageData(paramBean);
        pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
        WMZKJGService_.doSelectPageTD(pageVO);	
	    pageMAV.addObject(pageVO);		
//	   	pageMAV = new ModelAndView("redirect:F.go");	   
		pageMAV.setViewName("WM/ZK/WMJGD1L");
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
		CSPVOSupport pb = new CSPVOSupport();
        pb.setPuk(paramBean.getF03());
        FrameworkDataBean databean = WMBMA1Service_.doRead(pb);  //读取系统通道	
        paramBean.setPuk(PKUtil.getPUKey());
        paramBean.setF01(databean.getF01());
        paramBean.setF02(databean.getF02());
        paramBean.setF03(databean.getF03());
        paramBean.setF04(databean.getF04());
        paramBean.setF05(databean.getF05());
        paramBean.setF06(databean.getF06());
        paramBean.setF07(databean.getF07());
        paramBean.setF08(databean.getF08());
        paramBean.setF09(databean.getF09());
        paramBean.setF10(databean.getF10());
        paramBean.setF11(databean.getF11());
        paramBean.setF12(databean.getF12());		
		int result = WMBMA1Service_.doInsert(paramBean);
		if (result == 1) {
			pageMAV.addObject("message", paramBean);
		} else if (result == -1) {
			pageMAV.addObject("message", "failure");
		} else {
			pageMAV.addObject("message", "failure");
		}			
		String url = "redirect:/WMKZ07/F.go";
	   	pageMAV = new ModelAndView(url);	   	
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 
	 * 总控通道管理
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
        WMZKJGService_.doSelectPageTD(pageVO);	
	    pageMAV.addObject(pageVO);	
	    pageMAV.addObject("flag",flag);
	    pageMAV.setViewName("WM/ZK/WMJGD1");
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
		pageMAV.addObject("parambean1", getService().doRead(paramBean));
		pageMAV.setViewName("WM/ZK/WMKZK3");
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
		int result = WMBMA1Service_.toDelete(paramBean);
		System.out.println(result);
		if (result > 0) {
			pageMAV.addObject("message", "success");
		} else if (result < 0) {
			pageMAV.addObject("message", "failure");
		} else {
			pageMAV.addObject("message", "failure");
		}		
		String url = "redirect:F.go";
	   	pageMAV = new ModelAndView(url);	   	
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
	/*	PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);		
		List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
		pageMAV.addObject("list", list);
		pageMAV.setViewName("WM/ZK/WMJGD1");*/
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
	@RequestMapping(value = "/B.go" )
	public ModelAndView doInsert1(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		
		CSPVOSupport c2 = new CSPVOSupport();
		c2.setPuk(paramBean.getFb3());// 系统通道
		FrameworkDataBean fvo = WMBMA1Service_.doRead(c2);
		fvo.setFb3(paramBean.getFb3());
		fvo.setEb5(paramBean.getEb5());
		
		pageMAV.addObject("message", "*为必填");
		pageMAV.addObject("paramBean", fvo);
		
		pageMAV.setViewName("WM/ZK/WMKZK3");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 通道
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
	@RequestMapping(value = "/P.go" )
	public ModelAndView doInsert2(CSPVOSupport paramBean) {
		getLogger().debug("paramBean===>>>" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		paramBean.setEb5(super.getProductId());	
		pageVO.setPageData(paramBean);
        pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
        WMZKJGService_.doSelectPageTD(pageVO);	
	    pageMAV.addObject(pageVO);	
	    String url = "redirect:F.go";
	   	pageMAV = new ModelAndView(url);	   	
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
		
}
