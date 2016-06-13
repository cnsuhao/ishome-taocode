package org.isotope.jfp.framework.search;

import com.alibaba.fastjson.JSONObject;

/**
 * 检索数据整理
 * @author 001745
 *
 */
public interface IPrepareDataType {
	
	/**
	 * 数据格式化
	 * 
	 * @param js
	 *            原始数据
	 * @param cb
	 *            转换后对象类
	 */
	JSONObject prepareDataType(JSONObject data);
}
