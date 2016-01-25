package com.aek56.qt.gys.MGYS0;


import javax.annotation.Resource;

import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXDBO;
import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXService;

/**
 * 供应商基础信息
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Controller
public class MGYS0Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MGYS0Controller.class);

	/**
	 * 供应商基础信息
	 */
	@Resource
	protected MGYS0_JBXXService MGYS0_JBXXService_;

	/**
	 * 跳转到基础信息页面
	 * 
	 * @param pukid
	 * @return
	 * @Author:zhengxw
	 * @Date:2014-12-3
	 */
	@RequestMapping(value = "/3101000", method = RequestMethod.GET)
	public ModelAndView m3101000get() {
	    MGYS0_JBXXDBO param = new MGYS0_JBXXDBO();
	    param.setPuk(this.getCompanyId());
	    MGYS0_JBXXDBO rs = (MGYS0_JBXXDBO) this.MGYS0_JBXXService_.doRead(param);
		MyModelAndViewSupport view = this.getModelAndView();
		view.setViewName("qt/gys/MGAAC/baseInfo");
		view.addObject(USER_DATA, rs);
		return view;
	}
	
	@RequestMapping(value = "/3101000", method = RequestMethod.POST)
    public ModelAndView m3101000post() {
        MGYS0_JBXXDBO param = new MGYS0_JBXXDBO();
        param.setPuk(this.getCompanyId());
        MGYS0_JBXXDBO rs = (MGYS0_JBXXDBO) this.MGYS0_JBXXService_.doRead(param);
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("qt/gys/MGAAC/baseInfo");
        view.addObject(USER_DATA, rs);
        return view;
    }
	
	/**
	 * 新增或修改保存
	 * 
	 * @param param
	 * @return
	 * @Author:zhengxw
	 * @Date:2014-10-20
	 */
	@RequestMapping(value = "/31010001", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m31010001post(MGYS0_JBXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("Enter into MGYS1Controller#m31011001post()!");
		}

		int updatedNum = 0;
		if (StringUtils.isEmpty(param.getPuk())) {
		    updatedNum = this.MGYS0_JBXXService_.doInsert(param);
		} else {
		    updatedNum = this.MGYS0_JBXXService_.doUpdate(param);
		}

		RESTResultBean rs = new RESTResultBean();
		rs.setMessage(updatedNum > 0 ? "310100011" : "310100012");
		return rs;
	}
}
