package com.aek56.utils;

import org.jfpc.common.sms.SMSBean;
import org.jfpc.common.sms.client.DefaultSMSClient;
import org.jfpc.constants.ISFrameworkConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aek56.constants.ISAek56SMSConstants;

/**
 * 中国移动网关
 * @author Spook
 */
public class SMSYMRTClient extends DefaultSMSClient implements ISFrameworkConstants,ISAek56SMSConstants {
	private static final Logger logger = LoggerFactory.getLogger(SMSYMRTClient.class);

	private String authkey = "bb5eeb3413e2d59cd55c0d9939d3eea9";
	
	public SMSYMRTClient(){}

	/**
	 * 根据对象发送模版
	 * @param sms
	 * @return
	 * @throws Exception
	 */
	public SMSBean sendMessage(SMSBean sms) throws Exception {
		if(logger.isDebugEnabled())
			logger.debug(sms.toString());
		SmsAek56Bean bean = new SmsAek56Bean();
		bean.setUsr(sp.getSmsId());
		bean.setPsw(sp.getSmsPassword());
		bean.setAuthkey(authkey);
		bean.setMobile(sms.getPhoneNum());
		String msgModel = "";
		//根据模版ID翻转信息内容
		//	您正在{25}短信校验码{10}请于{2}分钟内输入验证{1}过期失效{1}请不要向他人透露校验码{1}客服热线{18}
		//	贵公司的{30}过期{1}为不影响贵公司的业务{1}请尽快登录爱医康证件管理平台维护更新相关信息{1}客服热线{18}
		//	{35}通知贵公司使用爱医康证件管理平台{20}维护三证相关资料{1}请尽快维护{1}以备检查{1}客服热线{18}
		
		if(ZCZ_SGY.equals(sms.getModelId())){
			//【您持有的注册证资料存在三个月内过期，请尽快更新】
			msgModel = ModelExpirationReminder("注册证资料存在三个月内",SMS_PHONE);
		}else if(ZCZ_YGQ.equals(sms.getModelId())){
			//【您持有的注册证资料已经部分过期，请立刻更新】
			msgModel = ModelExpirationReminder("注册证资料已经部分",SMS_PHONE);
		}else if(ZJZL_SGY.equals(sms.getModelId())){
			//【您的证件资料存在45天内过期，请尽快更新！】名称，编号
			//message.replaceAll("<br>", "\r\n")
			String[] str = sms.getMessage().split("<br>");
			msgModel = ModelExpirationReminder("证件资料("+str[1]+")存在45天内",SMS_PHONE);
		}else if(ZJZL_YGQ.equals(sms.getModelId())){
			//【您的证件资料已经过期，请尽快更新！】名称，编号
			String[] str = sms.getMessage().split("<br>");
			msgModel = ModelExpirationReminder("证件资料("+str[1]+")已经",SMS_PHONE);
		}else if(USER_ZHMM.equals(sms.getModelId())){
			String code = sms.getModelParam()[0];
			msgModel = ModelChecksum(code,"5",SMS_PHONE);
		}else if(USER_TXZC.equals(sms.getModelId())){
			String name = sms.getModelParam()[0];
			msgModel = ModelNotice(name,",",SMS_PHONE);
		}else if(SYS_SH.equals(sms.getModelId())){
			String p1 = sms.getModelParam()[0];
			//通过
			if(ZERO.equals(p1)){
				msgModel=ModelRegisteredOK(SMS_PHONE);
			}else{
				String cause = sms.getModelParam()[1];
				msgModel=ModelRegisteredNo(cause,SMS_PHONE);
			}
		}
		
		bean.setTpl(ZERO);
		bean.setText(msgModel);
		String result = HttpGetInterface.callServicePost(sp.getSmsUrl(),bean);
		sms.setResult(result);
		return sms;
	}

//	/**
//	 * 直接发送短信
//	 * @param mobilNumber
//	 * @param Message
//	 * @return
//	 * @throws Exception
//	 */
//	public SMSBean sendMessage(String mobilNumber, String Message) throws Exception {
//		if(logger.isDebugEnabled()){
//			logger.debug("mobilNumber=="+mobilNumber);
//			logger.debug("Message=="+Message);
//		}
//		SmsAek56Bean bean = new SmsAek56Bean();
//		bean.setUsr(sp.getSmsId());
//		bean.setPsw(sp.getSmsPassword());
//		bean.setTpl(ZERO);
//		bean.setAuthkey(authkey);
//		bean.setMobile(mobilNumber);
//		bean.setText(Message);
//		String result = HttpGetInterface.callServicePost(sp.getSmsUrl(),bean);
//		SMSBean sms = new SMSBean();
//		sms.setResult(result);
//		return sms;
//	}
//	

	/*public static void main(String[] args) {
		SMSYMRTClient client = new SMSYMRTClient();
		try {
			client.sendMessage("18627369620", "中文哥哥，哈哈");
			SMSBean bean  = new SMSBean();
			bean.setPhoneNum("18627369620");
			bean.setModelId("1");
			bean.setMessage("ssss");
			//client.sendMessage(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	/**
	 * 验证码
	 * @param code
	 * @param minute
	 * @param smsPhone
	 * @return
	 */
	public String ModelChecksum(String code,String minute,String smsPhone ){
		String model = "您正在使用爱医康证件管理平台，短信校验码"+code+"，请于"+minute+"分钟内输入验证，过期失效，请不要向他人透露校验码。客服热线："+smsPhone;
		return model;
	}
	/**
	 * 过期提醒
	 * @param title
	 * @param smsPhone
	 * @return
	 */
	public String ModelExpirationReminder(String title,String smsPhone){
		String model = "贵公司的"+title+"过期，为不影响贵公司的业务，请尽快登录爱医康证件管理平台维护更新相关信息。客服热线："+smsPhone;
		return model;
	}
	/**
	 * 通知
	 * @param smsPhone
	 * @return
	 */
	public String ModelNotice(String uName,String doWhat,String smsPhone){
		String model = uName+"通知贵公司使用爱医康证件管理平台"+doWhat+"维护三证相关资料，请尽快维护，以备检查。客服热线："+smsPhone;
		return model;
	}
	
	/**
	 * 审核通过
	 * @param smsPhone
	 * @return
	 */
	public String ModelRegisteredOK(String smsPhone){
		String model = "您在爱医康证件管理平台登记的信息，已经通过审核，您可以继续下一步操作了。客服热线："+smsPhone;
		return model;
	}
	
	/**
	 * 审核不通过
	 * @param cause
	 * @param smsPhone
	 * @return
	 */
	public String ModelRegisteredNo(String cause,String smsPhone){
		String model = "您在爱医康证件管理平台登记的信息，审核没有通过，原因："+cause+"，请核对信息后重新注册。客服热线："+smsPhone;
		return model;
	}	
	
}
