package org.jfpc.framework.bean;

import javax.inject.Named;

/**
 * 参数配置信息
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/2/8
 */
@Named
public class ConfigBean {
	 /**
	  * 配置使用关键字
	  */
    public String key = "";

    /**
     * 配置对应的内容
     */
    public String value = "";

	public String getKey() {
    	return key;
    }

	public void setKey(String key) {
    	this.key = key;
    }

	public String getValue() {
    	return value;
    }

	public void setValue(String value) {
    	this.value = value;
    }

	@Override
    public String toString() {
	    return "ConfigBean [key=" + key + ", value=" + value + "]";
    }
    
}
