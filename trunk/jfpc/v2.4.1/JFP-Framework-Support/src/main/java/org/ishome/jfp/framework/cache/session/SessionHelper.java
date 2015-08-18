package org.ishome.jfp.framework.cache.session;

import org.ishome.jfp.framework.cache.ICatchService;
import org.ishome.jfp.framework.utils.BeanFactoryHelper;

/**
 * 获得Session
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 */
public class SessionHelper {
	
	public static final String SESSION_SERVICE_NAME = "mySessionService";

	public static ICatchService getMqService() {
		return BeanFactoryHelper.getBean(SESSION_SERVICE_NAME);
	}

}
