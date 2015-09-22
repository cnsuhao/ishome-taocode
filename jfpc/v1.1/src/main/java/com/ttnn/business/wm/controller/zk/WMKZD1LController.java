package com.ttnn.business.wm.controller.zk;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("WMKZD1L")
/**系统检索查询*/
public class WMKZD1LController extends MyControllerSupportImpl {

    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMKZD1LController.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("WM/ZK/WMKZD1L");
    }
    

    @RequestMapping(value = "/H.go", method = RequestMethod.POST )
    public ModelAndView home(CSPVOSupport paramBean,HttpServletRequest request) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		String pageid = request.getParameter("pageid");
		//返回页面参数
		pageMAV.addObject("bbb",pageid);	
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

  
}
