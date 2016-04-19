package com.upg.biz.search.Client.control;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upg.biz.search.Captcha.CheckCode.impl.CaptchaCodeImpl;
import com.upg.biz.search.Captcha.CompanyInfo.impl.CompanyInfoSearchImpl;
import com.upg.biz.search.Captcha.CompanyList.impl.CompanyListSearchImpl;

/**
 * 网页人工抓取服务端
 * 
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
public class ClientToolControl implements ISFrameworkConstants {
	@Resource
	protected ICacheService mq;
	@Resource
	protected CaptchaCodeImpl CaptchaCode_;
	@Resource
	protected CompanyInfoSearchImpl CompanyInfoSearch_;
	@Resource
	protected CompanyListSearchImpl CompanyListSearch_;

	@RequestMapping(value = "/CTC", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean index() throws Exception {
		RESTResultBean rrb = new RESTResultBean();
		rrb.setMessage("服务运行中");
		try {
			Object val = "";
			//实时网页信息抓取 
			//mq.offerObjectInList(CompanyInfoSearchImpl.COMPANY_INFO,"123;310000;中新力合");
			val = CompanyInfoSearch_.getCompanyName();
			if (EmptyHelper.isEmpty(val)) {
				//验证码图片 
				//mq.offerObjectInList(CaptchaCodeImpl.CODE_LIST, "123;/resources/upload/20160317/1.png");
				val = CaptchaCode_.loadJobWithList();
				if (EmptyHelper.isEmpty(val)) {
					//关键字多源检索
					val = CompanyListSearch_.getSearchKeyword();
					if (EmptyHelper.isEmpty(val)) {
						rrb.setResult(EMPTY);
						rrb.setCode(ZERO);
					}else {
						rrb.setResult(val);
						rrb.setCode(THREE);
					}
				} else {
					rrb.setResult(val);
					rrb.setCode(TWO);
				}
			} else {
				rrb.setResult(val);
				rrb.setCode(ONE);
			}
		} catch (Exception e) {
			rrb.setCode(NINE);
			rrb.setResult(EMPTY);
		}

		return rrb;
	}

}
