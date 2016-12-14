package com.ttnn.framework.ept.bean.template;

import java.io.Serializable;

import com.ttnn.framework.ept.SEPTConstants;

/**
 * 模版定义
 * @see <ept-template.xml>
 * @since 1.3
 * @version 1.3 模版升级
 */
public class TemplateBean implements SEPTConstants,Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 752690823460858120L;
	//版本号
	String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "TemplateBean [version=" + version + "]";
	}
	
}
