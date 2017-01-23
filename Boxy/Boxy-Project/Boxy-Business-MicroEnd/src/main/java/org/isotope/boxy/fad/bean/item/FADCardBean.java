package org.isotope.boxy.fad.bean.item;

import java.util.ArrayList;

/**
 * 角色瓶子信息
 * 
 * @author 001745
 *
 */
public class FADCardBean {
	ArrayList<FADMessageBean> messages = new ArrayList<FADMessageBean>();

	public ArrayList<FADMessageBean> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<FADMessageBean> messages) {
		this.messages = messages;
	}

	public void addMessages(FADMessageBean message) {
		this.messages.add(message);
	}

	int driftingTimes = 0;// 最大漂流次数

	public int getDriftingTimes() {
		return driftingTimes;
	}

	public void setDriftingTimes(int driftingTimes) {
		this.driftingTimes = driftingTimes;
	}

	String bottleID;
	String sender;
	String receiver;
	String sendTime;

	public String getBottleID() {
		return bottleID;
	}

	public void setBottleID(String bottleID) {
		this.bottleID = bottleID;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

}
