package org.ishome.jfp.framework.push.server.impl;

import java.util.UUID;

import org.ishome.jfp.framework.utils.DateHelper;


/**
 * MedPushMessage.java
 * 
 *  TODO
 * @author ChenYa
 * @version 创建时间：2014-6-27 上午11:13:43
 */
public class MedPushMessage extends PushMessage {
	private static final long serialVersionUID = 1639056446657345498L;

	private String id = UUID.randomUUID().toString().replace("-", "");
	private String usId;
	private int userType;
	/**
	 * 见 @PushType
	 */
	private int taskType = 0; //0:文本	1:网页  	2:	3:聊天	4:通知提醒(目前有排队叫号通知)  5:充值提醒
	private long sendTime = System.currentTimeMillis();
	private String showTime = DateHelper.currentTimeMillisCN1();
	private String httpsUrl;
	private String imgUrl;
	
	//for chat
	private String recUsId;
	private String recUsName;
	private String roomId;
	private String recId;
	private String senderName;

	public MedPushMessage() {
	}
	
	public MedPushMessage(String key, String title, String content, int termType) {
		super(key, title, content, termType);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsId() {
		return usId;
	}

	public void setUsId(String usId) {
		this.usId = usId;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getTaskType() {
		return taskType;
	}

	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}

	public long getSendTime() {
		return sendTime;
	}

	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getHttpsUrl() {
		return httpsUrl;
	}

	public void setHttpsUrl(String httpsUrl) {
		this.httpsUrl = httpsUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getRecUsId() {
		return recUsId;
	}

	public void setRecUsId(String recUsId) {
		this.recUsId = recUsId;
	}

	public String getRecUsName() {
		return recUsName;
	}

	public void setRecUsName(String recUsName) {
		this.recUsName = recUsName;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRecId() {
		return recId;
	}

	public void setRecId(String recId) {
		this.recId = recId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

}
