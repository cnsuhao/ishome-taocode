package org.isotope.jfp.framework.beans.message;

import org.isotope.jfp.framework.beans.ObjectBean;
import org.isotope.jfp.framework.beans.message.info.CustomerSenderBean;
import org.isotope.jfp.framework.beans.message.info.MessageValueBean;
import org.isotope.jfp.framework.beans.message.info.UserReceverBean;
import org.isotope.jfp.framework.constants.pub.ISPushConstant;

/**
 * 消息对象
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
public class MessageInfoBean extends ObjectBean implements ISPushConstant {

	/**
	 * 网关类别
	 */
	MessageType messgeType;

	/**
	 * 发送者
	 */
	CustomerSenderBean sender;
	/**
	 * 接受者
	 */
	UserReceverBean recever;
	/**
	 * 消息对象
	 */
	MessageValueBean message;

	public MessageType getMessgeType() {
		return messgeType;
	}

	public void setMessgeType(MessageType messgeType) {
		this.messgeType = messgeType;
	}

	public CustomerSenderBean getSender() {
		return sender;
	}

	public void setSender(CustomerSenderBean sender) {
		this.sender = sender;
	}

	public UserReceverBean getRecever() {
		return recever;
	}

	public void setRecever(UserReceverBean recever) {
		this.recever = recever;
	}

	public MessageValueBean getMessage() {
		return message;
	}

	public void setMessage(MessageValueBean message) {
		this.message = message;
	}

}
