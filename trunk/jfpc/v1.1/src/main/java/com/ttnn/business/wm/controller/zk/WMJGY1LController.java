package com.ttnn.business.wm.controller.zk;

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
@RequestMapping("WMJGY1L")
/** 画面检索*/
public class WMJGY1LController extends MyControllerSupportImpl {

	@Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMJGY1LController.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("/WM/ZK/WMJGY1L");
    }
    
    @RequestMapping(value = "/H.go", method = RequestMethod.POST)
    public ModelAndView home(CSPVOSupport paramBean,HttpServletRequest request) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		String pageid = request.getParameter("pageid");
		
		//通道一览
		if("/WMJG01/F.go".equals(pageid)){
			pageMAV.addObject("k01","1");
		}
		
		//返回页面参数
		pageMAV.addObject("bbb",pageid);
		
		// 获得产品销售一览
		FrameworkDataBean fdb = new FrameworkDataBean();
		fdb.setEb5(super.getProductId());// 产品ID
		fdb.setTablename("csss01");
		pageMAV.addObject("listCpkh", getDictionaryOnTable(fdb));
		
		// 获得个人数据通道
		fdb = new FrameworkDataBean();
		fdb.setEb5(super.getProductId());// 产品ID
		fdb.setTablename("wmbma1");
		pageMAV.addObject("listXttd", getDictionaryOnTable(fdb));
		
		pageMAV.addObject("paramBean", paramBean);		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

}
