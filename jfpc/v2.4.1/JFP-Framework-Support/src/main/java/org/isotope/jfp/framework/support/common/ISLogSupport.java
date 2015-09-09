package org.isotope.jfp.framework.support.common;

import org.isotope.jfp.framework.beands.pub.LogBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;

/**
 * 日志记录类
 * @author fucy
 * @since 2.1.2
 * @version 2.1.2.20150417
 */
public interface ISLogSupport {

	public static final String CONFIG_KEY = "JFP:LOGLIST";
	
	/**
	 * 短信发送
	 * @see ISFrameworkConstants.SEMICOLON
	 * @param log 日志洗洗脑
	 * @return 发送结果
	 */
	public boolean send(LogBean log);
	
}
