package com.ttnn.business.wm.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;
import com.ttnn.business.cs.ISCSConstants;
import com.ttnn.business.wm.biz.WMLoginBusiness;
import com.ttnn.business.wm.biz.WMMainTotalBusiness;
import com.ttnn.common.util.TokenUtil;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSModelAndViewSupport;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;

/**
 * 贷付宝登录页面
 */
@Controller
@RequestMapping("WMHOME")
public class WMHomeController extends MyControllerSupportImpl implements ISCSConstants {

	@Resource
	protected WMLoginBusiness CSLoginBusiness_;

	@Resource
	PageVO pageVO;

	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMHomeController.class);
	}

	@Override
	public ModelAndView getModelAndView() {
		return new CSModelAndViewSupport("WM/INDEX");
	}

	@RequestMapping(value = "")
	public String index() {
		FrameworkDataBean dbParamBean = new FrameworkDataBean();
		// 定义中分类
		dbParamBean.setK02("DFB.XTFL");
		// 定义产品发行内部识别ID
		dbParamBean.setEb5("TTNN_DFB");
		// 获得 字典数据
		System.err.println(getDictionaryDefault(dbParamBean));
		// 获得字典名称
		System.err.println(getDictionaryName("DFB.XTFL.1"));

		return "WM/INDEX";
	}

	/**
	 * 前台登录
	 */
	@RequestMapping(value = "/{id}/QT.go")
	public ModelAndView qtLogin(@PathVariable(value = "id") String id, CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		// 设定产品ID
		pageMAV.addObject(CONSTANT_USER_TOKEN, id);

		// 显示登录页面
		pageMAV.setViewName("WM/QT/LOGIN");

		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 后台登录
	 */
	@RequestMapping(value = "/{id}/HT.go")
	public ModelAndView htLogin(@PathVariable(value = "id") String id, CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		// 设定产品ID
		pageMAV.addObject(CONSTANT_USER_TOKEN, id);

		// 显示登录页面
		pageMAV.setViewName("WM/HT/LOGIN");

		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 总控制台
	 */
	@RequestMapping(value = "/{id}/ZK.go")
	public ModelAndView zkLogin(@PathVariable(value = "id") String id, CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		// 设定产品ID
		pageMAV.addObject(CONSTANT_USER_TOKEN, id);

		// 显示登录页面
		pageMAV.setViewName("WM/ZK/LOGIN");

		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	// **************************************************************************************//
	// **************************************************************************************//
	// **************************************************************************************//

	/**
	 * 客户端登录
	 */
	@ResponseBody
	@RequestMapping(value = "/LOGIN.go")
	public FrameworkDataBean clLogin(CSPVOSupport formParamBean) {
		getLogger().debug("paramBean===>>>" + formParamBean);
		// 判断用户名密码，防止二次登录
		if (!StringUtils.isNullOrEmpty(formParamBean.getF04())) {
			// 执行用户登录
			FrameworkDataBean userInfo = CSLoginBusiness_.doLogin(formParamBean,false);

			if(userInfo!=null&&"1".equals(userInfo.getFb3())){
				getLogger().debug("userInfo===>>>" + userInfo);
				return userInfo;
			}else{
				formParamBean.setBbb("客户端仅限管理员登录！");
				getLogger().debug("客户端仅限管理员登录XXXXXXXXXXXXXX===>>>" + formParamBean.getEb5());
			}
		} 
		return formParamBean;
	}

	@Resource
	protected WMMainTotalBusiness mainTotalService;

	/**
	 * 客户端登录
	 */
	@ResponseBody
	@RequestMapping(value = "/{productId}/ZKTOTAL.go", method = RequestMethod.GET)
	public FrameworkDataBean clgTotal(@PathVariable(value = "productId") String productId) {
		getLogger().debug("productId===>>>" + productId);
		// 执行用户登录
		CSPVOSupport paramBean = new CSPVOSupport();
		paramBean.setPuk(productId);
		return mainTotalService.doTotlaHT(paramBean);
	}
	/**
	 * 客户端登录
	 */
	@ResponseBody
	@RequestMapping(value = "/{token}/TOTAL.go", method = RequestMethod.POST)
	public FrameworkDataBean clpTotal(@PathVariable(value = "token") String token) {
		getLogger().debug("token===>>>" + token);
		// 执行用户登录
		CSPVOSupport paramBean = new CSPVOSupport();
		paramBean.setPuk(TokenUtil.getProductId(token));
		return mainTotalService.doTotlaHT(paramBean);
	}
}
