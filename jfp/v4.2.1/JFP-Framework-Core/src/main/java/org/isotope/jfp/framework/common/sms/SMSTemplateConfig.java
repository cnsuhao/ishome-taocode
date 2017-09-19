package org.isotope.jfp.framework.common.sms;

import java.util.HashMap;
import java.util.Map;

import org.isotope.jfp.framework.utils.EmptyHelper;

/**
 * 短信模版设置
 * 
 * @author Spook
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
}
