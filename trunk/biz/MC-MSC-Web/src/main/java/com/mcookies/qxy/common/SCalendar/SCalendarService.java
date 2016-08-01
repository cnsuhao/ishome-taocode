package com.mcookies.qxy.common.SCalendar;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 校历表 */
@Service
public class SCalendarService extends MyServiceSupport {

	public SCalendarDao getDao() {
		return getMySqlSession().getMapper(SCalendarDao.class);
	}

}
