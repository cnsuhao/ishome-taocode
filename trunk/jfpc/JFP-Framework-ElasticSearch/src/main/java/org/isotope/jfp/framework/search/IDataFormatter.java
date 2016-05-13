package org.isotope.jfp.framework.search;

import com.alibaba.fastjson.JSONObject;

public interface IDataFormatter {
	/**
	 * 数据格式化
	 * 
	 * @param js
	 *            原始数据
	 * @param cb
	 *            转换后对象类
	 */
	JSONObject doDataFormatter(JSONObject data);
}
