package com.ttnn.framework.ept.bean.config;

import java.io.Serializable;

import com.ttnn.framework.ept.SEPTConstants;

/**
 * 校验器配置
 * 
 * @author Fcy
 * @see <ept-config.xml>
 * @since 1.1
 * @version 1.3 模版升级
 */
public class ValidateBean implements SEPTConstants,Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = -2823523334779411869L;
	/**
	 * 校验器的名称标识
	 */
	private String name;
	/**
	 * 校验器实体对象类路径
	 */
	private String className;
	/**
	 * 校验失败提示信息
	 */
	private String message;

	/**
	 * 校验器的参数
	 */
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ValidateBean [name=" + name + ", className=" + className + ", message=" + message + ", value=" + value + "]";
	}
}
