package com.hundsun.med.sms.test;

import com.hundsun.med.framework.beands.SMSBean;
import com.hundsun.med.framework.sms.server.impl.bjlx.BjlxSMSGatewayImpl;

/**
 * 北京领先短信平台
 * 验证码通道模版报备发送
 * @author fucy
 * 
 */
public class BjlxSMSGatewayImplTest {

	public static void main(String[] args) throws Exception {


		//System.out.println(PBESecurityHelper.encrypt("70hsdz001", "hsdz001hsdz001"));
		//System.out.println(PBESecurityHelper.encrypt("79hsdzyingxiao", "hsdzyingxiaohsdzyingxiao"));

		 BjlxSMSGatewayImpl sms = new BjlxSMSGatewayImpl();
		
		SMSBean message = new SMSBean();
		
		message.setPhoneNum("15057177411");
	
		message.setMessage("【恒生芸泰】尊敬的用户1123，aaa掌医APP提醒您，您的手机挂号记录rrrrrrrrrrrr，因（专家停诊）已被取消xxxxxxxxxx。如有疑问请联系热线12345。");
		message.setMessage("【恒生芸泰】验证码1111，1111掌医APP提醒您，短信验证码有效时间为1111，22222。如有疑问请联系热线11111。");
		message.setMessage("【恒生芸泰】尊敬的用户1111，1111掌医APP提醒您，由于您的账户11111，系统根据手机挂号规则对您的APP挂号行为进行限制，1111后将自动解除限制。如有疑问请联系热线1111。");
		message.setMessage("【恒生芸泰】尊敬的用户222，111掌医APP提醒您，您已成功预约222科111医生，12313。如有疑问请联系热线1111。");
		message.setMessage("【恒生芸泰】尊敬的用户111，1111掌医APP提醒您，您的预约记录111111111111111111因未能在规定时间内完成支付，导致本次挂号失败。如有疑问请联系热线11111。");
		message.setMessage("【恒生芸泰】尊敬的用户222，111掌医APP提醒您，您的手机挂号记录22222已成功取消11111。如有疑问请联系热线111。");
		//message.setMessage("【恒生芸泰】尊敬的用户，aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa。退订回N");
	
	    sms.doSend(message);
	
	}

}
