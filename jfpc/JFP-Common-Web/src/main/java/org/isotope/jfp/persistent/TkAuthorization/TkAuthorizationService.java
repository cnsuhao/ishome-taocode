package org.isotope.jfp.persistent.TkAuthorization;

import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 第三方授权Token表 */
@Service
public class TkAuthorizationService extends MyServiceSupport {
	protected static final Logger logger = LoggerFactory.getLogger(TkAuthorizationService.class);

	public TkAuthorizationDao getDao() {
		return getMySqlSession().getMapper(TkAuthorizationDao.class);
	}

}
