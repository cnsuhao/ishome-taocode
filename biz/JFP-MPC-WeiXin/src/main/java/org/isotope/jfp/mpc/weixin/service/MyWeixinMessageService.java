package org.isotope.jfp.mpc.weixin.service;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.message.MessageInfoBean;
import org.isotope.jfp.framework.common.message.UserMessageSendService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISPushConstant.MessageType;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.mpc.weixin.beans.WeiXinCompanyDeptReceverBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinCompanySenderBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinCompanyTagReceverBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinMessageValueBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinUserReceverBean;
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
	public String sendToCompanyId(String companyId, WeiXinMessageValueBean weixinMessage) {
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
			WeiXinCompanyTagReceverBean recever = mycs.loadWeiXinCompanyTagReceverBean(companyId);
			if (recever == null)
				return "9010";
			message.setRecever(recever);
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
		MyWeixinDeptService myds = BeanFactoryHelper.getBean("MyWeixinDeptService");
		{
			WeiXinCompanyDeptReceverBean recever = myds.loadWeiXinCompanyDeptReceverBean(groupId);
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
			WeiXinUserReceverBean recever = myds.loadWeiXinUserReceverBean(userId);
			if (recever == null)
				return "9030";
			message.setRecever(recever);
		}

		umss.send(message);
		return ZERO;
	}

}
