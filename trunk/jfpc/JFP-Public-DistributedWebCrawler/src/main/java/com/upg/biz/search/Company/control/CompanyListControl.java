package com.upg.biz.search.Company.control;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upg.biz.search.Captcha.CompanyList.impl.CompanyListSearchImpl;

/**
 * 企业信息多源检索
 * 
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
public class CompanyListControl implements ISFrameworkConstants {
	@Resource
	protected CompanyListSearchImpl CompanyListSearch_;

	/**
	 * 默认页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/M210", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean index() throws Exception {
		RESTResultBean rrb = new RESTResultBean();
		rrb.setMessage("企业信息多源检索");
		return rrb;
	}

	/**
	 * 获得一个等待检索的关键字(工具使用)
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/M210/010", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean getCompanyName() throws Exception {
		RESTResultBean rrb = new RESTResultBean();
		// keyword
		String company = (String) CompanyListSearch_.getSearchKeyword();
		if (EmptyHelper.isEmpty(company))
			return rrb;

		rrb.setResult(company);
		rrb.setCode(THREE);

		return rrb;
	}
	
}
