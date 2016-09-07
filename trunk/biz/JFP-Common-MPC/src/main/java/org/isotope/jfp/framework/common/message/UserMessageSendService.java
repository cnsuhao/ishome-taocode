package org.isotope.jfp.framework.common.message;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.isotope.jfp.framework.beans.message.MessageInfoBean;
import org.isotope.jfp.framework.beans.message.info.CustomerSenderBean;
import org.isotope.jfp.framework.beans.message.info.MessageValueBean;
import org.isotope.jfp.framework.beans.message.info.UserReceverBean;
import org.isotope.jfp.framework.common.CommonChannelConfig;
import org.isotope.jfp.framework.constants.pub.ISPushConstant;
import org.isotope.jfp.framework.support.IMessageSendSupport;
import org.isotope.jfp.framework.support.common.ISMSSupport;

import com.alibaba.fastjson.JSON;

/**
 * 消息发送SDK
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 * 
 */
public class UserMessageSendService extends CommonChannelConfig implements IMessageSendSupport, ISPushConstant {
	public UserMessageSendService() {
		this(ISMSSupport.CONFIG_KEY);
	}

	public UserMessageSendService(String key) {
		this.channelKey = key;
	}

	@Override
	public boolean send(MessageInfoBean message) {
		logger.debug(message.toString());
		// 压入队列
		if (catchService == null) {
			logger.error(">>>>>缓存服务没有定义...xxx");
			return false;
		} else {
			catchService.selectDB(defaultIndex);
			catchService.offerObjectInList(channelKey, JSON.toJSONString(message), false);
			catchService.init();
		}
		// 直接保存到短信队列
		return true;

	}

	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		UserMessageSendService umss = new UserMessageSendService();
		MessageInfoBean messageBean = new MessageInfoBean();
		CustomerSenderBean sender = new CustomerSenderBean();
		MessageValueBean message = new MessageValueBean();
		UserReceverBean recever = new UserReceverBean();
		
		
		
		
		messageBean.setSender(sender);
		messageBean.setRecever(recever);
		messageBean.setMessage(message);
		umss.send(messageBean);
	}
}
