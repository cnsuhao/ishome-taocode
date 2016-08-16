package com.mcookies.qxy.common.STerm;

import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 学期设置表 */
@Service
public class STermService extends MyServiceSupport {

	public STermDao getDao() {
		return getMySqlSession().getMapper(STermDao.class);
	}

	public STermPVO findByTermId(STermPVO term) {
		return getDao().findByTermId(term);
	}

}
