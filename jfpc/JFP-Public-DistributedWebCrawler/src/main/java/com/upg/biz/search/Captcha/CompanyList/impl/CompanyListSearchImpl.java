package com.upg.biz.search.Captcha.CompanyList.impl;

import javax.annotation.Resource;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.springframework.stereotype.Service;
/**
 * 多源检索
 * 
 * @author 001745
 *
 */
@Service
public class CompanyListSearchImpl implements ISFrameworkConstants {
	/**
	 * 检索Key队列
	 */
	public static final String KEYWORD_SEARCH = "KEYWORD:SEARCH";
	@Resource
	protected ICacheService mq;

	public Object searchOthers(String keyword) {
		return false;
	}

	public Object getSearchKeyword() {
		return mq.pollFirstObjectInList(KEYWORD_SEARCH);
	}
	
}
