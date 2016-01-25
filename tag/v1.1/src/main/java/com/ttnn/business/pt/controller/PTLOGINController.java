package com.ttnn.business.pt.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;
import com.ttnn.business.cs.ISCSConstants;
import com.ttnn.business.cs.fvo.UserBean;
import com.ttnn.business.pt.biz.PTLoginBusiness;
import com.ttnn.business.wm.controller.WMLoginController;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSModelAndViewSupport;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;

@Controller
@RequestMapping("PTLOGIN")
public class PTLOGINController extends MyControllerSupportImpl implements ISCSConstants{

	@Resource
	protected PTLoginBusiness PTLoginBusiness_;

	@Resource
	PageVO pageVO;

	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMLoginController.class);
	}

	@Override
	public ModelAndView getModelAndView() {
		return new CSModelAndViewSupport("PT/LOGIN");
	}

	/**
	 * 总控制台
	 */
	@RequestMapping(value = "/HOME.go", method = RequestMethod.POST)
	public ModelAndView loginProcess(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		
		//判断用户名密码，防止二次登录
		if (!StringUtils.isNullOrEmpty(paramBean.getF04())) {
			// 执行用户登录
			UserBean userInfo = PTLoginBusiness_.doLogin(paramBean);
	
			// 登录结果判断
			if (StringUtils.isNullOrEmpty(userInfo.getUserId())) {
				// 登录失败
				//页面保存产品ID
				pageMAV.addObject("eb5", userInfo.getProductId());
				pageMAV.addObject("LoginUrl", paramBean.getF02().replace(".", userInfo.getProductId())+".go");
				pageMAV.addObject("message", paramBean.getBbb());	
				pageMAV.addObject(CONSTANT_USER_TOKEN, paramBean.getEb5());
				pageMAV.setViewName("PT/LOGIN");
				
				getLogger().debug("usertoken===>>>" + paramBean.getEb5());
				getLogger().debug("用户登录失败>>>==="+paramBean.getF02());
				getLogger().debug("ViewName>>>===" +pageMAV.getViewName());
				return pageMAV;
			}        
	
			//页面保存产品ID
			pageMAV.addObject("eb5", userInfo.getProductId());
			pageMAV.addObject("LoginUrl", paramBean.getF02().replace(".", userInfo.getProductId())+".go");
			//保存用户ID
			paramBean.setPuk(userInfo.getUserId());
			/////////////////////页面菜单权限/////////////////////
	
			// 根据用户ID获得功能快捷菜单（顶部）
			PTLoginBusiness_.getMyTopMenu(paramBean, pageMAV);
			// 根据用户ID获得全部业务菜单（左上侧）
			PTLoginBusiness_.getMyRool(paramBean, pageMAV);
			// 根据用户ID获得应用菜单（左下侧）
			PTLoginBusiness_.getMyApp(paramBean, pageMAV);			

			pageMAV.setViewName("PT/MAIN");
			getLogger().debug("pageMAV===>>>" + pageMAV);
		}
		else
		{
			System.err.println("#########################>>>>>>>>>>>>>>>>"+paramBean.getEb5());
			
			pageMAV.setViewName("error/404");
		}
		return pageMAV;
	}
}
