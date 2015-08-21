package org.isotope.jfp.framework.sms;

import java.util.HashMap;
import java.util.Map;

import org.isotope.jfp.framework.utils.EmptyHelper;

/**
 * 短信模版设置
 * 
 * @author fucy
 * @version 2.3.0 2015/6/11
 * @since 2.3.0
 */
public class SMSTemplateConfig {

	/**
	 * 日常监听业务
	 */
	Map<String, String> templateConfig =  new HashMap<String, String> ();

	public Map<String, String> getTemplateConfig() {
		return templateConfig;
	}

	public void setTemplateConfig(Map<String, String> templateConfig) {
		this.templateConfig = templateConfig;
	}

	/**
	 * 获得一个模版的内容
	 * @param typeId
	 * @return
	 */
	public String getTemplate(String typeId) {
		return templateConfig.get(typeId);
	}	

	/**
	 * 基于模版进行信息转换
	 * @param typeId 模版ID
	 * @param param 模版内使用的参数
	 * @return 发送结果
	 */
	public String getMessageWithTemplate(String typeId, Object ... param){
		String temp = getTemplate(typeId);
		if(EmptyHelper.isNotEmpty(temp)){
			return String.format(temp,param);
		}
		return typeId + " ... 不存在";
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {		
System.out.println(String.format("【恒生芸泰】验证码%s，%s掌医APP提醒您，有效时间为%s分钟。如有疑问请联系%s。", "aaa","aaa","aaa","aaa","aaa"));
System.out.println(String.format("【恒生芸泰】尊敬的用户，%s。退订回N", "今天的程序还有BUG么","aaa","aaa","aaa","aaa"));
	}
}
