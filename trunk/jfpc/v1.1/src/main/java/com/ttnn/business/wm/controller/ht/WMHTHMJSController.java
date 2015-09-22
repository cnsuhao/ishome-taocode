package com.ttnn.business.wm.controller.ht;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;

@Controller
@RequestMapping("WMHTHMJS")
/** 画面检索*/
public class WMHTHMJSController extends MyControllerSupportImpl {

	@Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMHTHMJSController.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("/WM/HT/WMHTHMJS");
    }
    
    @RequestMapping(value = "/H.go", method = RequestMethod.POST)
    public ModelAndView home(CSPVOSupport paramBean,HttpServletRequest request) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		String pageid = request.getParameter("pageid");
		//返回页面参数
		pageMAV.addObject("bbb",pageid);
		
		// 获得个人数据通道
		FrameworkDataBean fdb = new FrameworkDataBean();
		fdb.setEb5(super.getProductId());// 产品ID
		fdb.setTablename("wmbma1");
		pageMAV.addObject("listXttd", getDictionaryOnTable(fdb));
		
		pageMAV.addObject("paramBean", paramBean);		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

}
