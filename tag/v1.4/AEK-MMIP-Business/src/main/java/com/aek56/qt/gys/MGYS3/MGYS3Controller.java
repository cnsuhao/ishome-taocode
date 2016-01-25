package com.aek56.qt.gys.MGYS3;

import javax.annotation.Resource;

import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.page.PageVOSupport;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aek56.atm.company.MGYS3_GYSTJYYXX.MGYS3_GYSTJYYXXDBO;
import com.aek56.atm.company.MGYS3_GYSTJYYXX.MGYS3_GYSTJYYXXService;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXDBO;

/**
 * 供应商医院信息
 * @ClassName: MGYS3Controller 
 * @author: Gaoqin
 * @date: 2014-12-4 下午3:09:28
 */
@Controller
public class MGYS3Controller extends MyControllerSupport {
    private static final Logger logger = LoggerFactory.getLogger(MGYS3Controller.class);

    /**
	 * 供应商医院信息
	 */
	@Resource
	private MGYS3_GYSTJYYXXService gystjyyxxService;
	
	/**
	 * 供应商医院事务类
	 */
	@Resource
	protected MGYS3Business MGYS3Business_;

	/**
	 * 默认加载页
	 */
	public MyModelAndViewSupport getModelAndView(String strPath) {
		return new MyModelAndViewSupport(strPath);
	}
	
	/**
	 * 跳转到供应商查看医院页面  
	 * @param @param pvo
	 * @param @param param
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/31022001", method = RequestMethod.POST)
	public MyModelAndViewSupport m31022001post() {
		MyModelAndViewSupport mv = getModelAndView("qt/gys/MGYS3/MGYS301");
		
		return mv;
	}
	
	@RequestMapping(value = "/31022001", method = RequestMethod.GET)
    public MyModelAndViewSupport m31022001get() {
        MyModelAndViewSupport mv = getModelAndView("qt/gys/MGYS3/MGYS301");
        
        return mv;
    }
	
	/**
	 * 供应商查看医院一览数据提供
	 * @param @param pvo
	 * @param @param param
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/31022001/1", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m310220011post(String keywords, PageVOSupport pvo, MGYS3_GYSTJYYXXDBO param) {
		RESTResultBean rs = new RESTResultBean();
		if (logger.isDebugEnabled()) {
			logger.debug("param:{},pageCurrent:{},pageLimit:{}", param, pvo.getPageCurrent(), pvo.getPageLimit());
		}
		
		pageModel.setPageCurrent(pvo.getPageCurrent());
		pageModel.setPageLimit(pvo.getPageLimit());
		param.setP01_gysid(super.getCompanyId());
		pageModel.setFormParamBean(param);
		this.gystjyyxxService.doSelectPage(pageModel, false);
		rs.setResult(super.pageModel.getPageListData());
		
		if (logger.isDebugEnabled()) {
			logger.debug("result:{}", pageModel);
		}
		
		return rs;
	}
	
	/**
	 * 跳转添加医院页面
	 *
	 * @return
	 * @Author:zhengxw
	 * @Date:2014-12-8
	 */
	@RequestMapping(value = "/31022002", method = RequestMethod.GET)
    public MyModelAndViewSupport m31022002get() {
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("qt/gys/MGAAC/hospital_add");
        return view;
    }
	
	@RequestMapping(value = "/31022002", method = RequestMethod.POST)
    public MyModelAndViewSupport m31022002post() {
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("qt/gys/MGAAC/hospital_add");
        return view;
    }
	
	/**
	 * 供应商添加医院
	 *
	 * @param param
	 * @return
	 * @throws Exception 
	 * @Author:zhengxw
	 * @Date:2014-12-8
	 */
	@RequestMapping(value = "/31022003", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m31022003post(MD3_YYXXDBO param) throws Exception {
        RESTResultBean rs = new RESTResultBean();
        if (logger.isDebugEnabled()) {
            logger.debug("param:{}", param);
        }
        
        int result =this.MGYS3Business_.addHospital(param);
        if(result ==0){
            rs.setMessage("310220030");
        }else{
            rs.setMessage("310220031");
        }
        rs.setResult(result);
        
        if (logger.isDebugEnabled()) {
            logger.debug("result:{}", pageModel);
        }
        
        return rs;
    }
	
}
