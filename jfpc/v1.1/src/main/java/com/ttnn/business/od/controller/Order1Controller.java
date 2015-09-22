package com.ttnn.business.od.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSR01Service;
import com.ttnn.business.cs.service.CSSS01Service;
import com.ttnn.business.cs.service.PTCPService;
import com.ttnn.common.util.PKUtil;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("HOMEORDER1")
/**产品功能*/
public class Order1Controller extends MyControllerSupportImpl {
    @Resource
    protected CSSS01Service CSSS01Service_;
    
    @Resource
    protected PTCPService PTCPService_;

    @Override
    public MyServiceSupportImpl getService(){
        return CSSS01Service_;
    }

    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(Order1Controller.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("OD/Custom");
    }
    
    @Override
    @RequestMapping(value = "/H.go" )
    public ModelAndView home(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
    
    @RequestMapping(value = "/A.go" )
    public ModelAndView subSuccess(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);		
		pageMAV.setViewName("OD/Complete");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
    
    @RequestMapping(value = "/B.go" )
    public ModelAndView independentSelection(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);		
		pageMAV.setViewName("OD/Custom");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
    
    /**
     * 增加
     */
    @Override
    @RequestMapping(value = "/C.go" )
    public ModelAndView doInsert(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);	
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
    @Override
    @RequestMapping(value = "/F.go" )
    public ModelAndView doSelectPage(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		paramBean.setK01("1384156198796.0");//测试数据	
		//查询功能信息
//		pageVO.setPageData(paramBean);
		List<FrameworkDataBean> list=PTCPService_.doSelectCPGN(paramBean);
		int rs=0;//总价
		int ct=0;//折扣价
		for (FrameworkDataBean frameworkDataBean : list) {
			rs+=Integer.parseInt(frameworkDataBean.getEb1());
			ct+=Integer.parseInt(frameworkDataBean.getEb2())*2;
		}
		pageVO.setPageData(list);
		pageMAV.addObject("rs",String.valueOf(rs));
		pageMAV.addObject("ct", String.valueOf(ct));
		pageMAV.addObject("data",pageVO.getPageData());
		pageMAV.setViewName("OD/Custom");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
    
}
