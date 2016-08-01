package com.mcookies.qxy.common.STrip;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 行程表 */
@Service
public class STripService extends MyServiceSupport {

	public STripDao getDao() {
		return getMySqlSession().getMapper(STripDao.class);
	}

}
