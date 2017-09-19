package org.isotope.jfp.mpc.weixin.service;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.SWxMessageDBO;
import org.isotope.jfp.common.weixin.WeiXinCompanyGroupDBO;
import org.isotope.jfp.common.weixin.WeiXinCompanyGroupUserDBO;
import org.isotope.jfp.common.weixin.WeixinService;
import org.isotope.jfp.framework.beans.message.MessageInfoBean;
import org.isotope.jfp.framework.common.message.UserMessageSendService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISPushConstant.MessageType;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.mpc.weixin.beans.message.WeiXinMessageValueBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyGroupReceverBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyGroupUserReceverBean;
import org.isotope.jfp.mpc.weixin.beans.recevers.WeiXinCompanyGroupReceverListBean;
import org.isotope.jfp.mpc.weixin.beans.recevers.WeiXinCompanyGroupUserReceverListBean;
import org.isotope.jfp.mpc.weixin.beans.sender.WeiXinCompanySenderBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
	@Resource
	WeixinService WeixinService_;
	@Resource
	MyWeixinCompanyGroupService MyWeixinCompanyGroupService_;
	@Resource
	MyWeixinCompanyGroupUserService MyWeixinCompanyGroupUserService_;
	
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
		SWxMessageDBO sWxMessageDBO = new SWxMessageDBO();
		sWxMessageDBO.setCompanyid(Long.valueOf(companyId));
		sWxMessageDBO.setMediatype(weixinMessage.getMediaType());
		sWxMessageDBO.setAgentid(weixinMessage.getAgentId());
		sWxMessageDBO.setMessage(weixinMessage.getMessage());
//		sWxMessageDBO.setUserids();
//		sWxMessageDBO.setUsernames();
//		sWxMessageDBO.setTid();
		String t = DateHelper.currentTimeMillis4();
		sWxMessageDBO.setSendingTime(t);
		sWxMessageDBO.setCreateTime(t);
		sWxMessageDBO.setUpdateTime(t);
//		sWxMessageDBO.setCreator(Long.valueOf(companyId));
//		sWxMessageDBO.setUpdator(Long.valueOf(companyId));
		WeixinService_.doInsert(sWxMessageDBO);	
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
		MyWeixinCompanyGroupService myds = BeanFactoryHelper.getBean("MyWeixinCompanyGroupService");
		{
			WeiXinCompanyGroupReceverBean recever = myds.loadWeiXinCompanyGroupReceverBean(companyId, groupId);
			if (recever == null)
				return "9020";
			message.setRecever(recever);
		}

		umss.send(message);
		doInsertGroup(companyId, groupId, weixinMessage);
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
		MyWeixinCompanyGroupUserService myds = BeanFactoryHelper.getBean("MyWeixinCompanyGroupUserService");
		{
			WeiXinCompanyGroupUserReceverBean recever = myds.loadWeiXinUserReceverBean(companyId, userId);
			if (recever == null)
				return "9030";
			message.setRecever(recever);
		}

		umss.send(message);
		return ZERO;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 企业信息发送给多个部门（部门ID与微信ID一致）
	 * 
	 * @param companyId
	 *            企业ID
	 * @param groupId
	 *            用户组ID
	 * @param message
	 * @return
	 */
	public String sendToCompanyIdGroupIds(String companyId, String groupIds, WeiXinMessageValueBean weixinMessage) {
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
			WeiXinCompanyGroupReceverListBean recever = new WeiXinCompanyGroupReceverListBean();
			recever.setRecevers(StringUtils.commaDelimitedListToStringArray(groupIds));
			message.setRecever(recever);
		}

		umss.send(message);
		doInsertGroup(companyId, groupIds, weixinMessage);	
		return ZERO;
	}

	/**
	 * 添加给群发给组微信
	 * @param companyId
	 * @param groupIds
	 * @param weixinMessage
	 * @throws NumberFormatException
	 */
	public void doInsertGroup(String companyId, String groupIds, WeiXinMessageValueBean weixinMessage)
			throws NumberFormatException {
		SWxMessageDBO sWxMessageDBO = new SWxMessageDBO();
		sWxMessageDBO.setCompanyid(Long.valueOf(companyId));
		sWxMessageDBO.setMediatype(weixinMessage.getMediaType());
		sWxMessageDBO.setAgentid(weixinMessage.getAgentId());
		sWxMessageDBO.setMessage(weixinMessage.getMessage());
//		sWxMessageDBO.setUserids();
//		sWxMessageDBO.setUsernames();
		sWxMessageDBO.setGroupids(groupIds);
		StringBuffer sb = new StringBuffer();
		String[] groupIdList = groupIds.split(",");
		for (String groupId : groupIdList) {
			WeiXinCompanyGroupDBO weiXinCompanyGroupDBO  = MyWeixinCompanyGroupService_.loadWeiXinCompanyGroupDBO(companyId,groupId);
			weiXinCompanyGroupDBO.getGroupName();
			sb.append(weiXinCompanyGroupDBO.getGroupName()+",");
		}
		sWxMessageDBO.setGroupnames(sb.substring(0, sb.length()-1).toString());
//		sWxMessageDBO.setTid();
		String t = DateHelper.currentTimeMillis4();
		sWxMessageDBO.setSendingTime(t);
		sWxMessageDBO.setCreateTime(t);
		sWxMessageDBO.setUpdateTime(t);
//		sWxMessageDBO.setCreator(Long.valueOf(companyId));
//		sWxMessageDBO.setUpdator(Long.valueOf(companyId));
		WeixinService_.doInsert(sWxMessageDBO);
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
	public String sendToCompanyIdUserIds(Long tid,String companyId, String userIds, WeiXinMessageValueBean weixinMessage) {
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
			WeiXinCompanyGroupUserReceverListBean recever = new WeiXinCompanyGroupUserReceverListBean();
			recever.setRecevers(StringUtils.commaDelimitedListToStringArray(userIds));
			message.setRecever(recever);
		}

		umss.send(message);
		if (tid != 0) {
			sendMessageUserIds(tid, companyId, userIds, weixinMessage);
		}
		return ZERO;
	}

	/**
	 * 发送消息给多个人
	 * @param tid
	 * @param companyId
	 * @param userIds
	 * @param weixinMessage
	 * @throws NumberFormatException
	 */
	public void sendMessageUserIds(Long tid, String companyId, String userIds, WeiXinMessageValueBean weixinMessage)
			throws NumberFormatException {
		SWxMessageDBO sWxMessageDBO = new SWxMessageDBO();
		sWxMessageDBO.setCompanyid(Long.valueOf(companyId));
		sWxMessageDBO.setMediatype(weixinMessage.getMediaType());
		sWxMessageDBO.setAgentid(weixinMessage.getAgentId());
		sWxMessageDBO.setMessage(weixinMessage.getMessage());
		sWxMessageDBO.setUserids(userIds);
		StringBuffer sb = new StringBuffer();
		String[] userIdList = userIds.split(",");
		for (String userId : userIdList) {
			WeiXinCompanyGroupUserDBO weiXinCompanyGroupUserDBO = MyWeixinCompanyGroupUserService_.loadWeiXinCompanyUserDBO(companyId,userId);
			weiXinCompanyGroupUserDBO.getUserName();
			sb.append(weiXinCompanyGroupUserDBO.getUserName()+",");
		}
		sWxMessageDBO.setUsernames(sb.substring(0, sb.length()-1).toString());
		sWxMessageDBO.setTid(tid);
		String t = DateHelper.currentTimeMillisCN1();
		sWxMessageDBO.setSendingTime(t);
		sWxMessageDBO.setCreateTime(t);
		sWxMessageDBO.setUpdateTime(t);
		sWxMessageDBO.setCreator(tid);
		sWxMessageDBO.setUpdator(tid);
		WeixinService_.doInsert(sWxMessageDBO);
	}
}
