package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.beans.message.MessageInfoBean;

/**
 * 消息发送类（客户端SDK）
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
public interface IMessageSendSupport {

	/**
	 * 消息发送
	 * 
	 * @param message
	 *            消息对象
	 * @return 发送结果
	 */
	public boolean send(MessageInfoBean message);

}
