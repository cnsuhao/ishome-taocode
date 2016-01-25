package org.ishome.jfp.framework.push;

import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.support.ISPhonePushSupport;


/**
 * 推送通道队列设置
 * 
 * @author Spook
 * @version 2.0.1 2015/6/11
 * @since 2.0.1 2015/7/7
 */
public class PushChannelConfig implements ISFrameworkConstants {

	public String keyList = ISPhonePushSupport.CONFIG_KEY;

	public String getKeyList() {
		return keyList;
	}

	public void setKeyList(String keyList) {
		this.keyList = ISPhonePushSupport.CONFIG_KEY + COLON + keyList;
	}

}
