package org.isotope.jfp.mpc.weixin;

import org.isotope.jfp.framework.beans.message.MessageInfoBean;
import org.isotope.jfp.framework.common.message.UserMessageSendService;
import org.isotope.jfp.mpc.weixin.beans.message.WeiXinMessageValueBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyUserReceverBean;
import org.isotope.jfp.mpc.weixin.beans.sender.WeiXinCompanySenderBean;

public class ReadMe {
	public static void main(String[] args) throws Exception {
		UserMessageSendService umss = new UserMessageSendService();
		MessageInfoBean messageBean = new MessageInfoBean();

		WeiXinCompanySenderBean sender = new WeiXinCompanySenderBean();
		WeiXinMessageValueBean message = new WeiXinMessageValueBean();
		WeiXinCompanyUserReceverBean recever = new WeiXinCompanyUserReceverBean();

		messageBean.setSender(sender);
		messageBean.setRecever(recever);
		messageBean.setMessage(message);
		umss.send(messageBean);
	}
}
