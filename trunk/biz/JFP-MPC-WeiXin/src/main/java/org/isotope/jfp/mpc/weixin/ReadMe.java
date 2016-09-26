package org.isotope.jfp.mpc.weixin;

import org.isotope.jfp.framework.beans.message.MessageInfoBean;
import org.isotope.jfp.framework.common.message.UserMessageSendService;
import org.isotope.jfp.mpc.weixin.beans.message.WeiXinMessageValueBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinUserReceverBean;
import org.isotope.jfp.mpc.weixin.beans.sender.WeiXinCompanySenderBean;

public class ReadMe {
	public static void main(String[] args) throws Exception {
		UserMessageSendService umss = new UserMessageSendService();
		MessageInfoBean messageBean = new MessageInfoBean();

		WeiXinCompanySenderBean sender = new WeiXinCompanySenderBean();
		WeiXinMessageValueBean message = new WeiXinMessageValueBean();
		WeiXinUserReceverBean recever = new WeiXinUserReceverBean();

		messageBean.setSender(sender);
		messageBean.setRecever(recever);
		messageBean.setMessage(message);
		umss.send(messageBean);
	}
}
