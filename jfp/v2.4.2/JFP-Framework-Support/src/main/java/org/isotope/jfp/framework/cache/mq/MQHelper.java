package org.isotope.jfp.framework.cache.mq;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;

/**
 * 获得MQ
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 */
public class MQHelper {
	
	public static final String MQ_SERVICE_NAME = "myMqService";

	public static ICacheService getMqService() {
		return BeanFactoryHelper.getBean(MQ_SERVICE_NAME);
	}

}
