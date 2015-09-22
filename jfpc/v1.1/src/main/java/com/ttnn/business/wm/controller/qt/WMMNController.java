package com.ttnn.business.wm.controller.qt;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSM01Service;
import com.ttnn.business.cs.service.CSSR01Service;
import com.ttnn.business.wm.service.WMBM02Service;
import com.ttnn.business.wm.service.WMBM03Service;
import com.ttnn.business.wm.service.WMBM04Service;
import com.ttnn.business.wm.service.WMBM05Service;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMMN")
/** 默认页面*/
public class WMMNController extends MyControllerSupportImpl {
	 @Resource
	 PageVO pageVO;
    @Resource
    protected WMUI01Service WMUI01Service_;
    @Resource
    protected CSSM01Service CSSM01Service_;
    
    //允许用户登录信息
    @Resource 
    protected CSSR01Service CSSR01Service_;
    
    @Override
    public MyServiceSupportImpl getService(){
        return WMUI01Service_;
    }
    @Resource
    protected WMBM02Service WMBM02Service_;

   
    public MyServiceSupportImpl getWMBM02Service(){
        return WMBM02Service_;
    }
    @Resource
    protected WMBM03Service WMBM03Service_;

    
    public MyServiceSupportImpl getWMBM03Service(){
        return WMBM03Service_;
    }
    @Resource
    protected WMBM04Service WMBM04Service_;

    
    public MyServiceSupportImpl getWMBM04Service(){
        return WMBM04Service_;
    }
    @Resource
    protected WMBM05Service WMBM05Service_;

   
    public MyServiceSupportImpl getWMBM05Service(){
        return WMBM05Service_;
    }

    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMMNController.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("WM/QT/WMMN");
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
	 * 数据一览
	 */

	@RequestMapping(value = "/F.go")
	public ModelAndView doSelectPage(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		
		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		pageVO.setPageData(paramBean);	
		pageMAV.addObject(pageVO);
	
		
		paramBean.setPuk(super.getLoginerId());
		FrameworkDataBean  databean = (FrameworkDataBean)getService().doRead(paramBean); //查询个人信息
		pageMAV.addObject("wmui01dbo", databean);
		FrameworkDataBean  allowbean = (FrameworkDataBean)CSSR01Service_.doRead(paramBean); //查询允许用户登录信息
		pageMAV.addObject("allowbean", allowbean);
		paramBean.setPuk("");
		pageVO.setPageData(paramBean);	
		pageMAV.addObject("list", getService().doSelectPage(pageVO));
		pageVO.setPageData(paramBean);	
		pageMAV.addObject("list1", getWMBM02Service().doSelectPage(pageVO).getPageData()); //充值记录
		
		pageVO.setPageData(paramBean);	
		pageMAV.addObject("list2", getWMBM03Service().doSelectPage(pageVO).getPageData()); //提现记录
		
		pageVO.setPageData(paramBean);	
		pageMAV.addObject("list3", getWMBM04Service().doSelectPage(pageVO).getPageData()); //佣金记录
		pageVO.setPageData(paramBean);	
		pageMAV.addObject("list4", CSSM01Service_.doSelectPage(pageVO).getPageData()); //系统公告
	
		pageMAV.setViewName("WM/QT/WMMN");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	

	}
