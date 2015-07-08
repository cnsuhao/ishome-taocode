package org.jfpc.platform.MS0A3.service;

import org.jfpc.base.support.MyServiceSupport;
import org.jfpc.platform.MS0A3.dao.MS0A3Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** */
@Service
public class MS0A3Service extends MyServiceSupport {
	protected static final Logger logger = LoggerFactory.getLogger(MS0A3Service.class);

	public MS0A3Dao getDao() {
		return getMySqlSession().getMapper(MS0A3Dao.class);
	}

}
