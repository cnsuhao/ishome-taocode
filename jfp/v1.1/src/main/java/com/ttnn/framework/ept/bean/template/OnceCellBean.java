package com.ttnn.framework.ept.bean.template;

import java.util.ArrayList;


import com.ttnn.framework.ept.SEPTConstants;
import com.ttnn.framework.support.CSPVOSupport;

/**
 * 单元格配置
 * 
 * @see <ept-template.xml>
 * @since 1.2
 * @version 1.3 模版升级
 */
public class OnceCellBean extends CSPVOSupport implements SEPTConstants{
	/**
     * 
     */
    private static final long serialVersionUID = -216777094996063063L;
    
	private ArrayList<CellBean> cellBeanList = new ArrayList<CellBean>();

	public ArrayList<CellBean> getCellBeanList() {
		return cellBeanList;
	}

	public void setCellBeanList(ArrayList<CellBean> cellBeanList) {
		this.cellBeanList = cellBeanList;
	}

	/**
	 * 单元格的名称标识
	 */
	private String name;
	/**
	 * 单元格所属Sheet定义名称
	 */
	private String sheetName;
	/**
	 * 单元格的对应POJO
	 */
	private String pojoName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getPojoName() {
		return pojoName;
	}

	public void setPojoName(String pojoName) {
		this.pojoName = pojoName;
	}

	@Override
	public String toString() {
		return "OnceCellBean [cellBeanList=" + cellBeanList + ", name=" + name + ", sheetName=" + sheetName + ", pojoName=" + pojoName + "]";
	}

}
