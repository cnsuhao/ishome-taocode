package org.isotope.jfp.framework.support;

import org.ishome.jfp.framework.beands.common.RESTResultBean;
import org.ishome.jfp.framework.beands.pub.PushBean;

/**
 * 推送发送
 * @author fucy
 * @version 2.3.0 2015/6/15
 * @since 2.3.0
 *
 */

public interface ISPushGatewaySupport {

	/**
	 * 推送发送
	 * @param message
	 * @return
	 */
	public RESTResultBean doPush(PushBean message);
}
