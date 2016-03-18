package com.upg.ceci.biz.search;

/**
 * 多源检索
 * @author 001745
 *
 */
public interface ISCompanyListSearch {

	/**
	 * 检索Key队列
	 */
	public static final String KEYWORD_SEARCH = "KEYWORD:SEARCH";

	/**
	 * 3.第三方数据源检索
	 * @return
	 */
	Object searchOthers(String keyword);
	
	/**
	 * 获得一个检索key
	 * @return
	 */
	Object getSearchKeyword();
	
}
