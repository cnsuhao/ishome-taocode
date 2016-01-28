package com.upg.ceci.beans.CeciHttpProxy;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** Http代理地址*/
@Service("CeciHttpProxyService")
public class CeciHttpProxyService extends MyServiceSupport {
	protected static final Logger logger = LoggerFactory.getLogger(CeciHttpProxyService.class);

	public CeciHttpProxyDao getDao() {
		return getMySqlSession().getMapper(CeciHttpProxyDao.class);
	}
}
