package com.upg.ceci.biz.search;

import javax.annotation.Resource;

import org.isotope.jfp.framework.cache.ICacheService;
import org.springframework.stereotype.Service;

/**
 * 多源检索
 * 
 * @author 001745
 *
 */
@Service
public class CompanyInfoSearchImpl implements ISCompanyInfoSearch {

	public static final String COMPANY_URL = "COMPANY:URL:";
	@Resource
	protected ICacheService mq;

	/**
	 * jobId + SEMICOLON + areaCode + SEMICOLON + companyName
	 */
	@Override
	public Object getCompanyName() {
		return mq.peekFirstObjectInList(COMPANY_INFO);
	}

	@Override
	public boolean saveCompanyInfo(String jobId, String html) {
		return mq.putObject(jobId, html, 30, false);
	}

	@Override
	public Object getAreaUrl(String areaCode) {
		return mq.getObject(COMPANY_URL + areaCode, false);
	}

}
