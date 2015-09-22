package com.ttnn.framework.ept.bean.config;

import java.io.Serializable;

import com.ttnn.framework.ept.SEPTConstants;

/**
 * 基本配置参数
 * 
 * @see <ept-config.xml>
 * @since 0.1
 * @version 0.1 
 */
public class ConfigBean implements SEPTConstants,Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 1095221230433736420L;
	/**
	 * 模版版本
	 */
	private String templateVersion;
	/**
	 * 编码语言
	 */
	private String encodingLanguage;
	/**
	 * 每次提交时处理的记录数目
	 */
	private int commitNumber;
	/**
	 * 默认模版名称
	 * 
	 * @return
	 */
	private String defaultTemplate;

	public String getTemplateVersion() {
		return templateVersion;
	}

	public void setTemplateVersion(String fieldTemplateVersion) {
		templateVersion = fieldTemplateVersion;
	}

	public String getDefaultTemplate() {
		return defaultTemplate;
	}

	public void setDefaultTemplate(String fieldDefaultTemplate) {
		defaultTemplate = fieldDefaultTemplate;
	}

	public String getEncodingLanguage() {
		return encodingLanguage;
	}

	public void setEncodingLanguage(String fieldEncodingLanguage) {
		encodingLanguage = fieldEncodingLanguage;
	}

	public int getCommitNumber() {
		return commitNumber;
	}

	public void setCommitNumber(int fieldCommitNumber) {
		commitNumber = fieldCommitNumber;
	}

	@Override
	public String toString() {
		return "ConfigBean [templateVersion=" + templateVersion + ", encodingLanguage=" + encodingLanguage + ", commitNumber=" + commitNumber + ", defaultTemplate=" + defaultTemplate + "]";
	}
	
}
