package org.ishome.jfp.framework.push.utils;

import org.ishome.jfp.framework.beands.PushBean;
import org.ishome.jfp.framework.beands.RESTResultBean;

/**
 * 推送发送
 * @author Spook
 * @version 2.0.1 2015/6/15
 * @since 2.0.1 2015/7/7
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
