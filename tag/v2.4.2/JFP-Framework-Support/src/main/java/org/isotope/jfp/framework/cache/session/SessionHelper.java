package org.isotope.jfp.framework.cache.session;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;

/**
 * 获得Session
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 */
public class SessionHelper {
	
	public static final String SESSION_SERVICE_NAME = "mySessionService";

	public static ICacheService getMqService() {
		return BeanFactoryHelper.getBean(SESSION_SERVICE_NAME);
	}

}
