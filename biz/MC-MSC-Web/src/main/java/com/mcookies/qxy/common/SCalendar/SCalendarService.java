package com.mcookies.qxy.common.SCalendar;

import java.util.List;

import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 校历表 */
@Service
public class SCalendarService extends MyServiceSupport {

	public SCalendarDao getDao() {
		return getMySqlSession().getMapper(SCalendarDao.class);
	}

	public List<SCalendarDBO> doSelectList(SCalendarDBO param){
		return getDao().doSelectList(param);
	}
}
