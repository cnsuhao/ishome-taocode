package com.upg.ceci.control;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upg.ceci.biz.search.CompanyInfoSearchImpl;

/**
 * 企业信息检索
 * 
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
public class CompanyInfoSearchControl implements ISFrameworkConstants {
	@Resource
	protected CompanyInfoSearchImpl CompanyInfoSearch_;

	@RequestMapping(value = "/M310", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean index() throws Exception {
		RESTResultBean rrb = new RESTResultBean();
		rrb.setMessage("企业信息检索");
		return rrb;
	}

	@RequestMapping(value = "/M310/010", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean getCompanyName() throws Exception {
		RESTResultBean rrb = new RESTResultBean();
		// jobId + BACKSLASH2 + areaCode + BACKSLASH2 + companyName
		String company = (String) CompanyInfoSearch_.getCompanyName();
		if (EmptyHelper.isEmpty(company))
			return rrb;
		String[] cs = company.split(BACKSLASH2);
		Object url = CompanyInfoSearch_.getAreaUrl(cs[1]);
		if (EmptyHelper.isEmpty(url))
			return rrb;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("jobId", cs[0]);
		map.put("areaCode", cs[1]);
		map.put("companyName", cs[2]);
		map.put("url", url);

		return rrb;
	}

	@RequestMapping(value = "/M310/020", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean saveCompanyInfo(HttpServletRequest request, @RequestParam String jobId, @RequestParam String html) throws Exception {
		RESTResultBean rrb = new RESTResultBean();
		CompanyInfoSearch_.saveCompanyInfo(jobId, html);
		rrb.setMessage("企业信息检索");
		return rrb;
	}
}
