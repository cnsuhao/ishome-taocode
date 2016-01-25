package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.beands.common.RESTResultBean;
import org.isotope.jfp.framework.beands.pub.PushBean;

/**
 * 推送接口
 * @author fucy
 * @version 2.3.0 2015/6/15
 * @since 2.3.0
 *
 */

public interface ISPushCometSupport {

	/**
	 * 推送发送
	 * @param message
	 * @return
	 */
	public RESTResultBean doPush(PushBean message);
}
