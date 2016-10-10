package com.mcookies.qxy.common.SCalendar;

import java.util.List;

import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 校历表 */
public interface SCalendarDao extends IDatabaseSupport {

	List<SCalendarDBO> doSelectList(SCalendarDBO param);
}
