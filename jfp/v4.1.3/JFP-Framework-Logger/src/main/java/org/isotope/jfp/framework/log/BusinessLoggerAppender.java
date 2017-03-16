package org.isotope.jfp.framework.log;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;
import org.isotope.jfp.framework.beans.pub.LogBean;
import org.isotope.jfp.framework.constants.pub.ISLogConstants;
import org.isotope.jfp.framework.support.common.ILogSupport;

/**
 * 日志输出模型
 * 
 * @author Spook
 * 
 */
public class BusinessLoggerAppender extends AppenderSkeleton implements ISLogConstants {

	/**
	 * 日志格式化模版
	 */
	protected Layout layout;

	public BusinessLoggerAppender(Layout layout) {
		this.layout = layout;
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	/**
	 * 日志输出实现
	 */
	private ILogSupport log;

	public ILogSupport getLog() {
		return log;
	}

	public void setLog(ILogSupport log) {
		this.log = log;
	}

	public void close() {
		if (this.closed)
			return;
		this.closed = true;
	}

	public boolean requiresLayout() {
		return false;
	}

	// 推送到消息队列
	protected void append(LoggingEvent event) {
		LogBean logBean = new LogBean();
		try {
			// 日志等级类别
			logBean.setLevel(event.getLevel().toString());
			// 获知当前方法、行号的
			LocationInfo locationInfo = event.getLocationInformation();
			logBean.setFullInfo(locationInfo.fullInfo);
			// 自定义日志信息
			logBean.setMessag(event.getMessage());
			// 异常信息
			if (event.getThrowableStrRep() != null)
				logBean.setThrowable(event.getThrowableStrRep());
			// 日志保存
			if (log != null) {
				log.send(logBean);
			}
		} catch (Exception e) {
			System.err.println("保存业务访问日志信息出错...... " + logBean);
		}
	}

}
