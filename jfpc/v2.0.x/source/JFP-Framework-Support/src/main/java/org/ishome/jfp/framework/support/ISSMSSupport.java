package org.ishome.jfp.framework.support;

import org.ishome.jfp.framework.constants.ISFrameworkConstants;

/**
 * 短信发送类
 * @author Spook
 * @since 2.0.0
 * @version 2.0.0 2015/4/17
 */
public interface ISSMSSupport {

	public static final String CONFIG_KEY = "HOSPITAL:SMSLIST";
	
	/**
	 * 短信发送
	 * 群发的场合phoneNum参数，传递多个手机号码，使用标准的半角分号间隔<br>
	 * @see ISFrameworkConstants.SEMICOLON
	 * @param hosId 医院ID
	 * @param phoneNum 手机号码
	 * @param message 短信内容
	 * @return 发送结果
	 */
	public boolean send(String hosId,String phoneNum,String message);
	
	/**
	 * 短信发送
	 * 群发的场合phoneNum参数，传递多个手机号码，使用标准的半角分号间隔<br>
	 * @see ISFrameworkConstants.SEMICOLON
	 * @param hosId 医院ID
	 * @param phoneNum 手机号码
	 * @param message 短信内容
	 * @param userId    用户ID
	 * @param patientID  患者ID
	 * @return 发送结果
	 */
	public boolean send(String hosId,String phoneNum,String message,String userId,String patientID);
	
}
