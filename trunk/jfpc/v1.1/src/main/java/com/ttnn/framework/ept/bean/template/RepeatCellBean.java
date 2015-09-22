package com.ttnn.framework.ept.bean.template;

import java.io.Serializable;
import java.util.ArrayList;

import com.ttnn.framework.ept.SEPTConstants;

/**
 * 单元格配置
 * 
 * @see <ept-template.xml>
 * @since 1.2
 * @version 1.3 模版升级
 */
public class RepeatCellBean implements SEPTConstants,Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 2027895038051287347L;
    
	private ArrayList<CellBean> cellBeanList = new ArrayList<CellBean>();

	public ArrayList<CellBean> getCellBeanList() {
		return cellBeanList;
	}

	@Override
	public String toString() {
		return "RepeatCellBean [cellBeanList=" + cellBeanList + ", name=" + name + ", sheetName=" + sheetName + ", pojoName=" + pojoName + ", startRowNumber=" + startRowNumber + "]";
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
	/**
	 * 单元格的列号
	 */
	private String startRowNumber;

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

	public int getStartRowNumber() {
		return Integer.parseInt(startRowNumber);
	}

	public void setStartRowNumber(String startRowNumber) {
		this.startRowNumber = startRowNumber;
	}
}
