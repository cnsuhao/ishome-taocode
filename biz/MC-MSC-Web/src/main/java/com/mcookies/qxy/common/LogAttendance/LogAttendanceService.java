package com.mcookies.qxy.common.LogAttendance;

import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 考勤日志表 */
@Service
public class LogAttendanceService extends MyServiceSupport {
	protected static final Logger logger = LoggerFactory.getLogger(LogAttendanceService.class);

	public LogAttendanceDao getDao() {
		return getMySqlSession().getMapper(LogAttendanceDao.class);
	}

}
