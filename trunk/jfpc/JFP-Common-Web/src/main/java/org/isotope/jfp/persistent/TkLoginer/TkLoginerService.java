package org.isotope.jfp.persistent.TkLoginer;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 第三方授权Token表 */
@Service
public class TkLoginerService extends MyServiceSupport {
	protected static final Logger logger = LoggerFactory.getLogger(TkLoginerService.class);

	public TkLoginerDao getDao() {
		return getMySqlSession().getMapper(TkLoginerDao.class);
	}

}
