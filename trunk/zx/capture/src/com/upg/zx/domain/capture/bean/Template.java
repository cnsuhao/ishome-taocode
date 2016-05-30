package com.upg.zx.domain.capture.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 数据模板类
 * @author lujf
 *
 */
public class Template {
    //模块信息
	public static Map<String,Mode> modeMap = new HashMap<String,Mode>();
	
	/**
	 * 获取 模块信息
	 * @param areaCode
	 * @return
	 */
	public static Mode getModeMap(String areaCode){
		return modeMap.get(areaCode);
	}
	
	/**
	 * 设置模块信息
	 * @param areaCode
	 * @param mode
	 */
	public static void setModeMap(String areaCode,Mode mode){
		modeMap.put(areaCode, mode);
	}
	
	/**
	 * 获取列表配置信息
	 * @param areaCode
	 * @param modeCode
	 */
	public static List<Column> getColumnMap(String areaCode,String modeCode){
		Mode mode = modeMap.get(areaCode);
		if(mode != null){
			return mode.getColumn(areaCode, modeCode);
		}
		return null;
	}

	public static Map<String, Mode> getModeMap() {
		return modeMap;
	}
}
