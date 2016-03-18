package com.upg.ceci.biz.search;

import javax.annotation.Resource;

import org.isotope.jfp.framework.cache.ICacheService;
/**
 * 多源检索
 * 
 * @author 001745
 *
 */
public class CompanyListSearchImpl implements ISCompanyListSearch {
	@Resource
	protected ICacheService mq;

	@Override
	public Object searchOthers(String keyword) {
		return false;
	}

	@Override
	public Object getSearchKeyword() {
		return mq.peekFirstObjectInList(KEYWORD_SEARCH);
	}

	
}
