package com.ttnn.framework.ept.bean.config;

import java.io.Serializable;

import com.ttnn.framework.ept.SEPTConstants;

/**
 * 提示信息配置
 * 
 * @author Fcy
 * @see <ept-config.xml>
 * @since 1.1
 * @version 1.3 模版升级
 */
public class MessageBean implements SEPTConstants,Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = -8360213705915278873L;
	/**
	 * 提示信息的名称标识
	 */
	private String name;
	/**
	 * 提示信息具体内容
	 */
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String fieldName) {
		name = fieldName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String fieldValue) {
		value = fieldValue;
	}

	/**
	 * 参数替换
	 * 
	 * @param args
	 * @return
	 */
	public String prepareMessage(String... args) {
		if (args == null)
			return value;
		StringBuilder msgSB = new StringBuilder(100);
		for (int i = 0; i < args.length; i++) {
			value = value.replace("{" + i + "}", args[i]);
		}
		return msgSB.toString();
	}

	@Override
	public String toString() {
		return "MessageBean [name=" + name + ", value=" + value + "]";
	}
	
}
