package org.isotope.jfp.framework.mq.rabbit;

import org.isotope.jfp.framework.support.IMQReceiveSupport;

/**
 * 队列处理程序
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/2/8
 */
public class MyQueryListener implements IMQReceiveSupport {
	
	/**
	 * 消息接受处理
	 */
	public void handleMessage(String message) {
		//System.out.println("<<<=Query==========" + message);
	}

}
