package com.ttnn.framework.ept.bean;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.HashMap;

import com.ttnn.framework.ept.SEPTConstants;

/**
 * Excel 读取操作，数据保存到缓存
 * @since 0.1
 * @version 0.1
 */
public class EPTDataBean implements SEPTConstants,Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 2542805005624834986L;
	/**
	 * 操作类别（插入||更新||查询），默认插入
	 */
	private String OperationType = OPERATION_TYPE_INSERT;
	/**
	 * 操作SQLMap
	 */
	private String OperationSQLMap = "";
	/**
	 * 被操作数据(dao)
	 */
	private ArrayDeque<HashMap<String, Object>> OperationData = new ArrayDeque<HashMap<String, Object>>(20);
	
	public String getOperationType() {
		return OperationType;
	}

	public void setOperationType(String operationType) {
		OperationType = operationType;
	}

	public String getOperationSQLMap() {
		return OperationSQLMap;
	}

	public void setOperationSQLMap(String operationSQLMap) {
		OperationSQLMap = operationSQLMap;
	}

	public ArrayDeque<HashMap<String, Object>> getOperationData() {
		return OperationData;
	}

	public void setOperationData(ArrayDeque<HashMap<String, Object>> operationData) {
		OperationData = operationData;
	}
}
