package org.isotope.jfp.framework.search.bean;

import java.util.List;

/**
 * 查询结果
 * 
 * @author 001745
 *
 */
public class QueryResultBean {
	int took = 0;
	int total = 0;
	List<String> hits;

	public int getTook() {
		return took;
	}

	public void setTook(int took) {
		this.took = took;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<String> getHits() {
		return hits;
	}

	public void setHits(List<String> hits) {
		this.hits = hits;
	}
}
