package org.jfpc.platform.CSSTT.service;

import org.jfpc.base.support.MyServiceSupport;
import org.jfpc.platform.CSSTT.dao.CSSTTDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** TOKEN存放表 */
@Service
public class CSSTTService extends MyServiceSupport {
	protected static final Logger logger = LoggerFactory.getLogger(CSSTTService.class);

	public CSSTTDao getDao() {
		return getMySqlSession().getMapper(CSSTTDao.class);
	}

}
