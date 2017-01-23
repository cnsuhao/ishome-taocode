package org.isotope.boxy.bean;

/**
 * 消息描述
 * 
 * @author 001745
 *
 */
public class MessageBean {
	/**
	 * 发送时间
	 */
	String sendTime;
	/**
	 * 发送内容
	 */
	String message;
	/**
	 * 发送者
	 */
	String sender;

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

}
