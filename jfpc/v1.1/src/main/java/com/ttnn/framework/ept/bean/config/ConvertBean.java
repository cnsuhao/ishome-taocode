package com.ttnn.framework.ept.bean.config;

import java.io.Serializable;

import com.ttnn.framework.ept.SEPTConstants;

/**
 * 容器配置
 * 
 * @author Fcy
 * @see <ept-config.xml>
 * @since 1.1
 * @since 1.2.2
 */
public class ConvertBean implements SEPTConstants,Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 8233345310796090684L;
	/**
	 * 容器的名称标识
	 */
	private String name;
	/**
	 * 容器实体对象类路径
	 */
	private String className;

	public String getName() {
		return name;
	}

	public void setName(String fieldName) {
		name = fieldName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String fieldClass) {
		className = fieldClass;
	}

	@Override
	public String toString() {
		return "ConvertBean [name=" + name + ", className=" + className + "]";
	}

}
