package org.jfpc.platform.MS0A4.service;

import org.jfpc.base.support.MyServiceSupport;
import org.jfpc.platform.MS0A4.dao.MS0A4Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 页面功能按钮定义 */
@Service
public class MS0A4Service extends MyServiceSupport {
	protected static final Logger logger = LoggerFactory.getLogger(MS0A4Service.class);

	public MS0A4Dao getDao() {
		return getMySqlSession().getMapper(MS0A4Dao.class);
	}

}
