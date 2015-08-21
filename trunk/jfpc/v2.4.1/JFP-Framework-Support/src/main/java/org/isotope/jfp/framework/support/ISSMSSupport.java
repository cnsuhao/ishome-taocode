package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;

/**
 * 短信发送类
 * @author fucy
 * @since 2.1.2
 * @version 2.1.2.20150417
 */
public interface ISSMSSupport {

	public static final String CONFIG_KEY = "JFP:SMSLIST";
	
	/**
	 * 短信发送
	 * 群发的场合phoneNum参数，传递多个手机号码，使用标准的半角分号间隔<br>
	 * @see ISFrameworkConstants.SEMICOLON
	 * @param companyId 企业ID
	 * @param phoneNum 手机号码
	 * @param message 短信内容
	 * @return 发送结果
	 */
	public boolean send(String companyId,String phoneNum,String message);
	
	/**
	 * 短信发送
	 * 群发的场合phoneNum参数，传递多个手机号码，使用标准的半角分号间隔<br>
	 * @see ISFrameworkConstants.SEMICOLON
	 * @param companyId 企业ID
	 * @param phoneNum 手机号码
	 * @param message 短信内容
	 * @param userId    用户ID
	 * @return 发送结果
	 */
	public boolean send(String companyId,String phoneNum,String message,String userId);
	
}
