package org.jfpc.base.log;

import net.sf.json.JSONObject;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;
import org.jfpc.base.helper.BeanFactoryHelper;
import org.jfpc.base.helper.DateHelper;
import org.jfpc.base.mq.impl.MyMQSendServer;

/**
 * 日志输出模型
 * 
 * @author Spook
 * 
 */
public class MyLoggerAppender extends AppenderSkeleton implements ISLogConstants {
	protected Layout layout;

	public MyLoggerAppender() {
	}

	public MyLoggerAppender(Layout layout) {
		this.layout = layout;
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
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
		// 获知当前方法、行号的
		LocationInfo locationInfo = event.getLocationInformation();
		// if (locationInfo.fullInfo.indexOf(PKG_JFPC) == 0)
		{
			MyMQSendServer mms = null;
			try {
				mms = (MyMQSendServer) BeanFactoryHelper.getBean("logSend");
			} catch (Exception e) {

			}
			if (mms != null) {
				JSONObject ml = new JSONObject();
				ml.put(LOG_LEVEL, event.getLevel());
				ml.put(LOG_FMN, locationInfo.fullInfo);
				ml.put(LOG_MSG, event.getMessage());
				mms.send(ml.toString());
			} else {
				System.out.println(">>>>>> " + event.getLevel()+ "  ["+ DateHelper.currentTimeMillis2() + "]"  );
				System.out.println("  >>>> " + locationInfo.fullInfo);
				System.out.println("    >> " + event.getMessage());
			}
		}
	}

}
