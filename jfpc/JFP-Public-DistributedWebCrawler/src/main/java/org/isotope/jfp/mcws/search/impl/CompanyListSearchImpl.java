package org.isotope.jfp.mcws.search.impl;

import javax.annotation.Resource;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.mcws.search.ISCompanyListSearch;
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
