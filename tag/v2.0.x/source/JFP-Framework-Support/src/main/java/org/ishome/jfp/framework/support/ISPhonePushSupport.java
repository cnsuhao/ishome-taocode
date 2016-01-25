package org.ishome.jfp.framework.support;

import org.ishome.jfp.framework.beands.PushBean;

/**
 * 手机推送
 * @author Spook
 * @since 2.0.0
 * @version 2.0.0 20150417
 */
public interface ISPhonePushSupport {
	public static final String CONFIG_KEY = "HOSPITAL:PUSHLIST";	

	/**
	 * 消息推送到手机
	 * @return
	 */
	public abstract boolean push(PushBean push);

}
