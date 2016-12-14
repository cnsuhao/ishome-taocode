package org.jfpc.platform.MSSUU.service;

import org.jfpc.base.support.MyServiceSupport;
import org.jfpc.platform.MSSUU.dao.MSSUUDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 允许登录用户 */
@Service
public class MSSUUService extends MyServiceSupport {
	protected static final Logger logger = LoggerFactory.getLogger(MSSUUService.class);

	public MSSUUDao getDao() {
		return getMySqlSession().getMapper(MSSUUDao.class);
	}

}
