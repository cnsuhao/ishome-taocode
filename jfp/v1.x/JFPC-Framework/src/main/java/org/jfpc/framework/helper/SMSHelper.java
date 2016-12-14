package org.jfpc.framework.helper;

/**
 * 核对短信验证码
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/18
 */
public class SMSHelper {

	/**
	 * 基于缓存中心进行校验
	 * @param phoneNum 目标手机号码
	 * @param bizId 业务识别ID
	 * @param codeLength 验证码长度
	 * @return 0正确1失败2过期
	 */
	public static int validateSMSCode(String phoneNum,String bizId,String codeLength){
		
		return 0;
	}
}
