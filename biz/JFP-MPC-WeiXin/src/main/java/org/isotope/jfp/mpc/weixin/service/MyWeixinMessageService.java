package org.isotope.jfp.mpc.weixin.service;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.message.MessageInfoBean;
import org.isotope.jfp.framework.common.message.UserMessageSendService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISPushConstant.MessageType;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.mpc.weixin.beans.message.WeiXinMessageValueBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyGroupReceverBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyUserReceverBean;
import org.isotope.jfp.mpc.weixin.beans.sender.WeiXinCompanySenderBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 发送微信消息
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 * @see <UserMessageSendService>
 */
@Service("MyWeixinMessageService")
public class MyWeixinMessageService implements ISFrameworkConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	UserMessageSendService umss;

	MessageInfoBean message;

	public MyWeixinMessageService() {
		message = new MessageInfoBean();
		message.setMessgeType(MessageType.WeiXin);
	}

	/**
	 * 企业信息发送给自身
	 * 
	 * @param companyId
	 *            企业ID
	 * @param message
	 * @return
	 */
	public String sendToCompany(String companyId, WeiXinMessageValueBean weixinMessage) {
		MyWeixinCompanyService mycs = BeanFactoryHelper.getBean("MyWeixinCompanyService");
		{
			WeiXinCompanySenderBean sender = mycs.loadWeiXinCompanySenderBean(companyId);
			if (sender == null)
				return "9090";
			message.setSender(sender);
		}
		{
			if (weixinMessage == null)
				return "9080";
			message.setMessage(weixinMessage);
		}
		{
			message.setRecever(null);
		}

		umss.send(message);
		return ZERO;
	}

	/**
	 * 企业信息发送给部门
	 * 
	 * @param companyId
	 *            企业ID
	 * @param groupId
	 *            用户组ID
	 * @param message
	 * @return
	 */
	public String sendToCompanyIdGroupId(String companyId, String groupId, WeiXinMessageValueBean weixinMessage) {
		MyWeixinCompanyService mycs = BeanFactoryHelper.getBean("MyWeixinCompanyService");
		{
			WeiXinCompanySenderBean sender = mycs.loadWeiXinCompanySenderBean(companyId);
			if (sender == null)
				return "9090";
			message.setSender(sender);
		}
		{
			if (weixinMessage == null)
				return "9080";
			message.setMessage(weixinMessage);
		}
		MyWeixinGroupService myds = BeanFactoryHelper.getBean("MyWeixinGroupService");
		{
			WeiXinCompanyGroupReceverBean recever = myds.loadWeiXinCompanyGroupReceverBean(companyId, groupId);
			if (recever == null)
				return "9020";
			message.setRecever(recever);
		}

		umss.send(message);
		return ZERO;
	}

	/**
	 * 企业信息发送给个人
	 * 
	 * @param companyId
	 *            企业ID
	 * @param userId
	 *            个人ID
	 * @param message
	 * @return
	 */
	public String sendToCompanyIdUserId(String companyId, String userId, WeiXinMessageValueBean weixinMessage) {
		MyWeixinCompanyService mycs = BeanFactoryHelper.getBean("MyWeixinCompanyService");
		{
			WeiXinCompanySenderBean sender = mycs.loadWeiXinCompanySenderBean(companyId);
			if (sender == null)
				return "9090";
			message.setSender(sender);
		}
		{
			if (weixinMessage == null)
				return "9080";
			message.setMessage(weixinMessage);
		}
		MyWeixinUserService myds = BeanFactoryHelper.getBean("MyWeixinUserService");
		{
			WeiXinCompanyUserReceverBean recever = myds.loadWeiXinUserReceverBean(companyId, userId);
			if (recever == null)
				return "9030";
			message.setRecever(recever);
		}

		umss.send(message);
		return ZERO;
	}

}
