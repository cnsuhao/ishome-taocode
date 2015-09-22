package com.ttnn.framework.ept.bean.template;

import java.io.Serializable;
import java.util.ArrayList;


import com.ttnn.framework.ept.SEPTConstants;
import com.ttnn.framework.ept.bean.config.ValidateBean;


/**
 * 单元格配置
 * 
 * @see <ept-template.xml>
 * @since 1.1
 * @version 1.3 模版升级
 */
public class CellBean implements SEPTConstants,Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 5289039326899656908L;
    
	private ArrayList<ValidateBean> validateBeanList = new ArrayList<ValidateBean>();

	public ArrayList<ValidateBean> getValidateBeanList() {
		return validateBeanList;
	}

	public void setValidateBeanList(ArrayList<ValidateBean> validateBeanList) {
		this.validateBeanList = validateBeanList;
	}

	/**
	 * 单元格的名称标识
	 */
	private String name;
	/**
	 * 单元格的行号
	 */
	private int rowNumber;
	/**
	 * 单元格的列号
	 */
	private int columnNumber;
	/**
	 * 单元格的对应POJO的Bean属性
	 */
	private String beanName;
	/**
	 * 单元格的对应POJO的Dao属性
	 */
	private String daoName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getDaoName() {
		return daoName;
	}

	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}

	@Override
	public String toString() {
		return "CellBean [validateBeanList=" + validateBeanList + ", name=" + name + ", rowNumber=" + rowNumber + ", columnNumber=" + columnNumber + ", beanName=" + beanName + ", daoName=" + daoName + "]";
	}
}
