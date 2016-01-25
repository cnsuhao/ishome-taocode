package org.jfpc.platform.MS0A1.service;

import org.jfpc.base.support.MyServiceSupport;
import org.jfpc.platform.MS0A1.dao.MS0A1Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 系统菜单定义 */
@Service
public class MS0A1Service extends MyServiceSupport {
	protected static final Logger logger = LoggerFactory.getLogger(MS0A1Service.class);

	public MS0A1Dao getDao() {
		return getMySqlSession().getMapper(MS0A1Dao.class);
	}

}
