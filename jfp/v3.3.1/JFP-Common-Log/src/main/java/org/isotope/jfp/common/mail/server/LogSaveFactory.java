package org.isotope.jfp.common.mail.server;

import org.isotope.jfp.framework.beands.common.RESTResultBean;
import org.isotope.jfp.framework.beands.pub.LogBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.support.ISLogSaveSupport;

/**
 * 日志处理工厂
 * 
 * @author fucy
 * @version 2.4.1 2015/9/9
 * @since 2.4.1
 */
public class LogSaveFactory implements ISLogSaveSupport, ISFrameworkConstants {
	/**
	 * 默认日志处理
	 */
	private ISLogSaveSupport defaultLogSave;

	public ISLogSaveSupport getDefaultLogSave() {
		return defaultLogSave;
	}

	public void setDefaultLogSave(ISLogSaveSupport defaultLogSave) {
		this.defaultLogSave = defaultLogSave;
	}

	// 这里仅仅为了保持Common下面代码的风格一致性，不习惯的可以修改
	/**
	 * 消息推送
	 */
	@Override
	public RESTResultBean doSave(LogBean message) {
		return defaultLogSave.doSave(message);
	}

}
