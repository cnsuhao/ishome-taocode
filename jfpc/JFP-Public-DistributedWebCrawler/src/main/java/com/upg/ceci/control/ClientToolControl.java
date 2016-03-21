package com.upg.ceci.control;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upg.ceci.biz.captcha.CaptchaCodeImpl;
import com.upg.ceci.biz.search.CompanyInfoSearchImpl;

/**
 * qiyejiansuo
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

	@RequestMapping(value = "/CTC", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean index() throws Exception {
		RESTResultBean rrb = new RESTResultBean();
		rrb.setMessage("服务运行中");
		try {
			Object val = "";
			// mq.offerObjectInList(CompanyInfoSearchImpl.COMPANY_INFO,"123;310000;中新力合");
			val = CompanyInfoSearch_.getCompanyName();
			if (EmptyHelper.isEmpty(val)) {
				mq.offerObjectInList(CaptchaCodeImpl.CODE_LIST, "123;/resources/upload/20160317/1.png");
				val = CaptchaCode_.loadJobWithList();
				if (EmptyHelper.isEmpty(val)) {
					rrb.setResult(EMPTY);
					rrb.setCode(ZERO);
				} else {
					rrb.setResult(val);
					rrb.setCode(TWO);
				}
			} else {
				rrb.setResult(val);
				rrb.setCode(ONE);
			}
		} catch (Exception e) {
			rrb.setCode(ZERO);
			rrb.setResult(EMPTY);

		}

		return rrb;
	}
}
