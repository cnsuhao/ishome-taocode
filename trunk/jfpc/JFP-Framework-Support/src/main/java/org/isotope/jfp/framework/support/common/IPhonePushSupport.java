package org.isotope.jfp.framework.support.common;

import org.isotope.jfp.framework.beans.pub.PushBean;

/**
 * 手机推送
 * @author fucy
 * @since 2.1.2
 * @version 2.1.2.20150417
 */
public interface IPhonePushSupport {
	public static final String CONFIG_KEY = "JFP:PUSHLIST";	

	/**
	 * 消息推送到手机
	 * @return
	 */
	public boolean push(PushBean push);

}
