package org.isotope.jfp.common.push.server.impl;

import org.isotope.jfp.framework.beands.common.RESTResultBean;
import org.isotope.jfp.framework.beands.pub.PushBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISPushConstant;
import org.isotope.jfp.framework.support.ISPushCometSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认推送通道
 * 
 * @author fucy
 * @version 2.4.1 2015/9/9
 * @since 2.4.1
 * 
 */
public class DefaultPushServiceImpl implements ISPushConstant, ISPushCometSupport, ISFrameworkConstants {
	private Logger log = LoggerFactory.getLogger(DefaultPushServiceImpl.class);
	/**
	 * 是否关闭
	 */
	private boolean disabled = false;

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	@Override
	public RESTResultBean doPush(PushBean message) {
		log.debug(message.toString());
		RESTResultBean rs = new RESTResultBean();
		rs.setCode(SYSTEM_ERROR_CODE);
		rs.setMessage("推送通道未开启");
		return rs;
	}

}
