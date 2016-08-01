package com.mcookies.qxy.common.SWorkTime;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 作息时间模板 */
@Service
public class SWorkTimeService extends MyServiceSupport {

	public SWorkTimeDao getDao() {
		return getMySqlSession().getMapper(SWorkTimeDao.class);
	}

}
