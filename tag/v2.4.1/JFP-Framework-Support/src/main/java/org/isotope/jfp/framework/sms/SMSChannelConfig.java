package org.isotope.jfp.framework.sms;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.support.ISSMSSupport;

/**
 * 短信通道队列设置
 * 
 * @author fucy
 * @version 2.3.0 2015/6/11
 * @since 2.3.0 
 */
public class SMSChannelConfig implements ISFrameworkConstants {

	public String keyList = ISSMSSupport.CONFIG_KEY;

	public String getKeyList() {
		return keyList;
	}

	public void setKeyList(String keyList) {
		this.keyList = ISSMSSupport.CONFIG_KEY + COLON + keyList;
	}

}
