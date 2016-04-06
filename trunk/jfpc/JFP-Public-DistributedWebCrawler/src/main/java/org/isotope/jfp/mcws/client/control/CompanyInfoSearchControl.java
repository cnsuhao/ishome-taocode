package org.isotope.jfp.mcws.client.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.isotope.jfp.dwc.config.JobConfig;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.mcws.search.impl.CompanyInfoSearchImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

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

	/**
	 * 默认页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/M310", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean index() throws Exception {
		RESTResultBean rrb = new RESTResultBean();
		rrb.setMessage("企业信息检索");
		return rrb;
	}

	/**
	 * 获得一个等待打码的企业(工具使用)
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/M310/010", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean getCompanyName() throws Exception {
		RESTResultBean rrb = new RESTResultBean();
		// jobId + BACKSLASH2 + areaCode + BACKSLASH2 + companyName
		String company = (String) CompanyInfoSearch_.getCompanyName();
		if (EmptyHelper.isEmpty(company))
			return rrb;

		rrb.setResult(company);
		rrb.setCode(TWO);

		return rrb;
	}

	/**
	 * 打码队列获得一个等待打码的企业
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/M310/0101", method = RequestMethod.GET)
	@ResponseBody
	public CompanyInfoSearchImpl.ForCodeCompany getCompanyNameByCap() throws Exception {
		return CompanyInfoSearch_.getCompanyNameByCap();
	}
	
	@Resource
	protected JobConfig config;

	/**
	 * 客户端上传HTML
	 * 
	 * @param request
	 * @param jobId
	 * @param html
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/M310/020", method = RequestMethod.POST)
	public ModelAndView saveCompanyInfo(HttpServletRequest request, @RequestParam String jobId, @RequestParam String html) throws Exception {
		RESTResultBean rrb = new RESTResultBean();
		if (EmptyHelper.isNotEmpty(jobId) && EmptyHelper.isNotEmpty(html)) {
			try {
				// 文件存储
				String path = config.getFileSavePath() + DateHelper.currentDate3() + "/";
				String realDirPath = request.getServletContext().getRealPath(path);
				File f = new File(realDirPath);
				if (f.exists() == false)
					f.mkdirs();

				FileOutputStream fos = new FileOutputStream(realDirPath + "/" + jobId + ".html");
				Writer out = new OutputStreamWriter(fos, "UTF-8");
				out.write(html);
				out.flush();
				fos.close();

				// 设定返回值
				rrb.setMessage("OK");
				// 缓存到队列
				CompanyInfoSearch_.saveCompanyInfo(jobId, path + jobId + ".html");
			} catch (Exception e) {
				e.printStackTrace();
				rrb.setMessage("FAIL");
			}
		} else {
			rrb.setMessage("EMPTY");
		}

		ModelAndView mav = new ModelAndView("CTC/M310020");

		mav.addObject("MESSAGE", JSON.toJSONString(rrb));// 任务队列描述

		return mav;
	}

	/**
	 * 获得一个等待打码的企业
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/M310/030", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean addCompanyName(HttpServletRequest request, @RequestParam String jobId, @RequestParam String areaCode, @RequestParam String companyName) throws Exception {
		RESTResultBean rrb = new RESTResultBean();
		CompanyInfoSearch_.addCompanyName(jobId, areaCode, companyName);
		rrb.setMessage("OK");
		return rrb;
	}
}
