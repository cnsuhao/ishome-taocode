package com.ttnn.business.wm.controller.qt;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.biz.WMQTJLCXBussiness;
import com.ttnn.business.wm.service.WMBM04Service;
import com.ttnn.business.wm.service.WMBS01Service;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMBM04")
/** 佣金交易记录*/
public class WMBM04Controller extends MyControllerSupportImpl {
	@Resource
	protected  WMQTJLCXBussiness WMJLCXBussiness_;
    @Resource
    protected WMBM04Service WMBM04Service_;

    @Override
    public MyServiceSupportImpl getService(){
        return WMBM04Service_;
    }

    @Resource
    protected WMBS01Service WMBS01Service_;

   
    public MyServiceSupportImpl getWMBS01Service(){
        return WMBS01Service_;
    }
    
    @Resource
    protected WMUI01Service WMUI01Service_;

    
    public MyServiceSupportImpl getWMUI01Service(){
        return WMUI01Service_;
    }
    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMBM04Controller.class);
    }

   
        @Override
        public ModelAndView getModelAndView(){
            return new ModelAndView("WM/QT/WMJYY2");
        }
       
    	@Override
    	@RequestMapping(value = "/H.go", method = RequestMethod.POST)
    	public ModelAndView home(CSPVOSupport paramBean){
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
    		
    		pageVO.setPageData(paramBean);
    		pageMAV.addObject(pageVO);
//    		List<FrameworkDataBean> list = getService().doSelectPage(paramPageModel);
    		pageMAV.addObject("list", getService().doSelectPage(pageVO).getPageData());
    		pageMAV.setViewName("WM/QT/WMJYY2");		
    		getLogger().debug("pageMAV===>>>" + pageMAV);
    		return pageMAV;
    	}
    	
        /**
    	 * 数据一览
    	 */
         //我的佣金记录
    	@RequestMapping(value = "/F.go")
    	public ModelAndView doSelectPage1(CSPVOSupport paramBean) {
    		ModelAndView pageMAV = getModelAndView();
    		getLogger().debug("paramBean===>>>" + paramBean);
    		paramBean.setEb5(super.getProductId());
    		paramBean.setK03(super.getLoginerId());
    		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
    		pageVO.setPageData(paramBean);		
    		pageMAV.addObject(pageVO);
    		WMJLCXBussiness_.doSelectPageCommission(pageVO);
    		//佣金记录
    		pageMAV.addObject("list", pageVO.getPageData());
    		pageMAV.addObject("resultCount",pageVO.getResultCount());
    		pageMAV.addObject("eb2",paramBean.getEb2());
    	
    		pageMAV.setViewName("WM/QT/WMJYY1");
    		getLogger().debug("pageMAV===>>>" + pageMAV);
    		return pageMAV;
    	}
    	
    	
    	
    	   //某用户为自己的佣金
    	@RequestMapping(value = "/{userid}/X.go")
    	public ModelAndView doSomoBodys(CSPVOSupport paramBean,@PathVariable String userid) {
    		ModelAndView pageMAV = getModelAndView();
    		getLogger().debug("paramBean===>>>" + paramBean);
    		paramBean.setK03(userid); //
    		paramBean.setEb5(super.getProductId());
    		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
    		pageVO.setPageData(paramBean);		
    		pageMAV.addObject(pageVO);
    		WMJLCXBussiness_.doSelectPageCommission(pageVO);
    		//佣金记录
    		CSPVOSupport userInfo = new CSPVOSupport();
    		userInfo.setPuk(userid);
    		FrameworkDataBean db = WMUI01Service_.doRead(userInfo); //个人信息
            pageMAV.addObject("userInfo", db);
    		
    		
    		pageMAV.addObject("list", pageVO.getPageData());
    		pageMAV.setViewName("WM/QT/WMJYY3");
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
    		int result=getService().doDelete(paramBean);
    		if(result==-1){
    			pageMAV.addObject("obj", "success");
    			pageMAV.setViewName("WM/QT/WMJYY1");
    		}else if(result==1){
    			pageMAV.addObject("obj", "删除失败");
    		}else{
    			pageMAV.addObject("obj", "删除失败");
    		}
    		
    		pageVO.setPageData(paramBean);		
    		pageMAV.addObject(pageVO);
//    		List<FrameworkDataBean> list=getService().doSelectPage(paramPageModel);
    		pageMAV.addObject("list", getService().doSelectPage(pageVO).getPageData());
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
    		//查询得到该记录，加载至“cs/sb03c1”页面	
    		
    		
    	
    		
    		int result=getService().doUpdate(paramBean);	
    		pageMAV.getViewName();
    		 pageMAV.setViewName("WM/QT/WMJYY2");
    		 
    		 pageVO.setPageData(paramBean);		
    		 pageMAV.addObject(pageVO);
//    		 List<FrameworkDataBean> list=getService().doSelectPage(paramPageModel);
    		 pageMAV.addObject("list", getService().doSelectPage(pageVO).getPageData());
    		 pageMAV.setViewName("WM/QT/WMJYY1");
    		  //获取结束时间
    		getLogger().debug("pageMAV===>>>" + pageMAV);
    		return pageMAV;
    	}
    	
    	
    	
    	
    	
    	/**
    	 * 查询一条记录
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
    	@RequestMapping(value = "/L.go", method = RequestMethod.GET)
    	public ModelAndView doSelectPage(CSPVOSupport paramBean) {
    		ModelAndView pageMAV = getModelAndView();
    		getLogger().debug("paramBean===>>>" + paramBean);

    	
    		PageVO paramPageModel = new PageVO();
    		// 设定页面参数
    		paramPageModel.setPageData(paramBean);
    		PageVO result=getService().doSelectPage(paramPageModel);
    		result.getPageCount();
    		
    		System.out.println(getService().doSelectPage(paramPageModel));
    		pageMAV.addObject(paramPageModel);
    		getLogger().debug("pageMAV===>>>" + pageMAV);
    		return pageMAV;
    	}
    	
    
    	
    	
    	}
