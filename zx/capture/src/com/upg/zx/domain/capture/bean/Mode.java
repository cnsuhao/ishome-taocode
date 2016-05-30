package com.upg.zx.domain.capture.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mode {
	//属性列表
	private Map<String,List<Column>> columnMap = new HashMap<String,List<Column>>();
	
	/**
	 * 获取属性列表
	 * @param areaCode
	 * @param modeCode
	 * @return
	 */
	public List<Column> getColumn(String areaCode,String modeCode){
		return columnMap.get(areaCode+"_"+modeCode);
	}
	
	/**
	 * 设置属性列表
	 * @param areaCode
	 * @param modeCode
	 * @param columns
	 */
	public void setColumnMap(String areaCode,String modeCode,List<Column> columns){
		columnMap.put(areaCode+"_"+modeCode, columns);
	}
}
