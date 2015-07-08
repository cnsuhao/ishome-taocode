package org.isotope.fpm.framework.page.bean;

public class PageMessage {

	/* 最普通的枚举 */
	public enum MessageType {
		Error, Info, Waring, Stop;
	}

	/**
	 * 提示信息种类
	 */
	private MessageType MessageType;
	/**
	 * 提示信息代码标识
	 */
	private String MessageVendorCode;
	/**
	 * 提示信息内容
	 */
	private String MessageValue;

	public MessageType getMessageType() {
		return MessageType;
	}

	public void setMessageType(MessageType messageType) {
		MessageType = messageType;
	}

	public String getMessageVendorCode() {
		return MessageVendorCode;
	}

	public void setMessageVendorCode(String messageVendorCode) {
		MessageVendorCode = messageVendorCode;
	}

	public String getMessageValue() {
		return MessageValue;
	}

	public void setMessageValue(String messageValue) {
		MessageValue = messageValue;
	}

}
