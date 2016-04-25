package org.isotope.jfp.framework.beans.HttpProxy;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** Http代理地址*/
@Service("HttpProxyService")
public class HttpProxyService extends MyServiceSupport {
	protected static final Logger logger = LoggerFactory.getLogger(HttpProxyService.class);

	public HttpProxyDao getDao() {
		return getMySqlSession().getMapper(HttpProxyDao.class);
	}
}
