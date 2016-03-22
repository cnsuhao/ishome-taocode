package org.isotope.jfp.mcws.search.impl;

import javax.annotation.Resource;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.mcws.search.ISCompanyInfoSearch;
import org.springframework.stereotype.Service;

/**
 * 多源检索
 * 
 * @author 001745
 *
 */
@Service
public class CompanyInfoSearchImpl implements ISCompanyInfoSearch, ISFrameworkConstants {

	public static final String COMPANY_URL = "COMPANY:URL:";
	@Resource
	protected ICacheService mq;

	/**
	 * jobId + SEMICOLON + areaCode + SEMICOLON + companyName
	 */
	@Override
	public Object getCompanyName() {
		// jobId + SEMICOLON + areaCode + SEMICOLON + companyName
		String company = (String) mq.peekFirstObjectInList(COMPANY_INFO);
		if (EmptyHelper.isEmpty(company))
			return EMPTY;
		String[] cs = company.split(SEMICOLON);
		Object url = getAreaUrl(cs[1]);
		if (EmptyHelper.isEmpty(url))
			return EMPTY;
		StringBuffer res = new StringBuffer(200);
		res.append(company);
		res.append(SEMICOLON);
		res.append(url);
		return res.toString();
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
