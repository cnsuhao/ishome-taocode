package org.isotope.jfp.framework.beans;

import com.alibaba.fastjson.JSON;

/**
 * 参数配置信息
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/2/8
 */
public class ObjectBean {

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
