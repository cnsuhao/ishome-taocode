package com.ttnn.business.wm.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;
import com.ttnn.business.cs.ISCSConstants;
import com.ttnn.business.wm.biz.WMLoginBusiness;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSModelAndViewSupport;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;

/**
 * 贷付宝登录页面
 */
@Controller
@RequestMapping("WMLOGIN")
public class WMLoginController extends MyControllerSupportImpl implements ISCSConstants {

	@Resource
	protected WMLoginBusiness CSLoginBusiness_;
	
	@Resource
	protected WMUI01Service WMUI01Service_;
	
	@Resource
	PageVO pageVO;

	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMLoginController.class);
	}

	@Override
	public ModelAndView getModelAndView() {
		return new CSModelAndViewSupport("WM/INDEX");
	}

	/**
	 * 前台登录业务
	 */
	@RequestMapping(value = "/HOME.go")
	public ModelAndView getLoginProcess(CSPVOSupport formParamBean) {
		return getModelAndView();
	}
	/**
	 * 前台登录业务
	 */
	@RequestMapping(value = "/HOME.go", method = RequestMethod.POST)
	public ModelAndView postLoginProcess(CSPVOSupport formParamBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + formParamBean);

		// 页面保存域名
		pageMAV.addObject("k02", formParamBean.getEb5());
		// 页面保存用户名
		pageMAV.addObject("k01", formParamBean.getF03());
		getLogger().error("#########################>>>>>>>>>>>>>>>>" + formParamBean.getEb5());

		// 判断用户名密码，防止二次登录
		if (!StringUtils.isNullOrEmpty(formParamBean.getF04())) {
			// 执行用户登录
			FrameworkDataBean userInfo = CSLoginBusiness_.doLogin(formParamBean,true);

			// 登录结果判断
			if (userInfo==null||StringUtils.isNullOrEmpty(userInfo.getPuk())) {
				// 登录失败
				// 页面保存产品ID
				pageMAV.addObject(CONSTANT_USER_TOKEN, formParamBean.getEb5());
				pageMAV.addObject("eb5", formParamBean.getEb5());
				pageMAV.addObject("LoginUrl", formParamBean.getF02().replace(".", "/"+formParamBean.getEb5()) + "/.go");
				pageMAV.addObject("message", formParamBean.getBbb());
				pageMAV.setViewName(formParamBean.getF02().replace(".", "/") + "/LOGIN");

				getLogger().debug("usertoken===>>>" + formParamBean.getEb5());
				getLogger().debug("用户登录失败>>>===" + formParamBean.getF02());
				getLogger().debug("ViewName>>>===" + pageMAV.getViewName());
				return pageMAV;
			} else {
				// 页面保存产品ID
				pageMAV.addObject("eb5", userInfo.getEb5());
				pageMAV.addObject("LoginUrl", formParamBean.getF02().replace(".", "/"+userInfo.getEb5()) + "/.go");
				// 保存用户ID
				formParamBean.setPuk(userInfo.getPuk());
				//获得会员组
				CSPVOSupport c2 = new CSPVOSupport();
				c2.setPuk(userInfo.getPuk());
				FrameworkDataBean user = WMUI01Service_.doRead(c2);
				if(user!=null)
					pageMAV.addObject("vip", user.getK02());
				
				// ///////////////////页面菜单权限/////////////////////

				// 根据用户ID获得功能快捷菜单（顶部）
				CSLoginBusiness_.getMyTopMenu(formParamBean, pageMAV);
				
				//超级管理员判断
				if("0".equals(userInfo.getFb3())){
					// 根据用户ID获得全部业务菜单（左上侧）
					int menus = CSLoginBusiness_.getMyRool(formParamBean, pageMAV);
					if(menus==0){
						//TODO 跨域登录
						pageMAV.setViewName("error/505");
						return pageMAV;
					}
				}else
				{
					CSLoginBusiness_.getAllMyRool(formParamBean, pageMAV);
				}
				// 根据用户ID获得应用菜单（左下侧）
				CSLoginBusiness_.getMyApp(formParamBean, pageMAV);

				pageMAV.setViewName(formParamBean.getF02().replace(".", "/") + "/MAIN");
				getLogger().debug("pageMAV===>>>" + pageMAV);
			}

		} else {
			System.err.println("#########################>>>>>>>>>>>>>>>>" + formParamBean.getEb5());

			pageMAV.setViewName("error/505");
		}
		return pageMAV;
	}

}
