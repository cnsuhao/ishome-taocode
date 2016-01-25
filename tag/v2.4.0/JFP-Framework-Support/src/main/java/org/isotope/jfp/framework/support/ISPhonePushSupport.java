package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.beands.pub.PushBean;

/**
 * 手机推送
 * @author fucy
 * @since 2.1.2
 * @version 2.1.2.20150417
 */
public interface ISPhonePushSupport {
	public static final String CONFIG_KEY = "JFP:PUSHLIST";	

	/**
	 * 消息推送到手机
	 * @return
	 */
	public abstract boolean push(PushBean push);

}
