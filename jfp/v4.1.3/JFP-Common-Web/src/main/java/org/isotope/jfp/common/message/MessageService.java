package org.isotope.jfp.common.message;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class MessageService {
//	@Resource
//	MessageModelUtils model;
//	
//	/**
//	 * 设定SQL连接<br>
//	 * 多个Service调用的时候，建议使用手动设定
//	 * 
//	 * @param mySqlSession
//	 */
//	public void setMySqlSession(SqlSession mySqlSession) {
//		CS0D1Service_.setMySqlSession(mySqlSession);
//	}
//	
//	@Resource
//	CS0D1Service CS0D1Service_;
//	public boolean sendMessage(MessageBean msg) throws Exception {
//		CS0D1Service_.doInsert(msg);
//		return true;
//	}
//	
//	//发送内部消息
//	public boolean sendMessage(String bizId, String receiver, String modelId, String... param){
//		CS0D1DBO msg = new CS0D1DBO();
//		//MessageBean sms = new MessageBean();
//		msg.setK01_fjrid(CS0D1Service_.getLoginerId());
//		msg.setK02_sjrid(receiver);
//		//sms.setF03_bt(title);
//		msg.setF04_jjd("3");
//		msg.setF05_zyd("3");
//		msg.setFb3(bizId);
//		if(model.buildMessage(msg, modelId, param)==false)
//			return false;
//		CS0D1Service_.doInsert(msg);
//		return true;
//	}
//
//	//发送内部消息
//	public boolean sendMessage(String bizId, String receiver, String title, String message){			
//		CS0D1DBO msg = new CS0D1DBO();
//		msg.setK01_fjrid(CS0D1Service_.getLoginerId());
//		msg.setK02_sjrid(receiver);
//		msg.setF03_bt(title);
//		msg.setF04_jjd("3");
//		msg.setF05_zyd("3");
//		msg.setBbb(message);
//		msg.setFb3(bizId);
//		CS0D1Service_.doInsert(msg);
//		return true;
//	}
}
