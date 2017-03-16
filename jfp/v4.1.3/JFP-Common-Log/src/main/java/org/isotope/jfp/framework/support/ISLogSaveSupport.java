package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.beands.common.RESTResultBean;
import org.isotope.jfp.framework.beands.pub.LogBean;

/**
 * 日志处理
 * 
 * @author fucy
 * @version 2.4.1 2015/9/9
 * @since 2.4.1
 *
 */
public interface ISLogSaveSupport {

	/**
	 * 短信发送
	 * 
	 * @param message
	 * @return
	 */
	public RESTResultBean doSave(LogBean message);
}
