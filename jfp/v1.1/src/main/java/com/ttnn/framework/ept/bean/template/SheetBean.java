package com.ttnn.framework.ept.bean.template;

import java.io.Serializable;

import com.ttnn.framework.ept.SEPTConstants;

/**
 * 操作模版POJO配置
 * @see <ept-template.xml>
 * @since 1.2
 * @version 1.3 模版升级
 * 
 */
public class SheetBean implements SEPTConstants,Serializable{
	@Override
	public String toString() {
		return "SheetBean [name=" + name + ", numIndex=" + numIndex + ", title=" + title + ", book=" + book + "]";
	}

	/**
     * 
     */
    private static final long serialVersionUID = 1819367347882038647L;
	/**
	 * SHEET的名称标识
	 */
	private String name;
	/**
	 * SHEET序号
	 */
	private int numIndex;
	/**
	 * SHEET完整名称
	 */
	private String title;
	/**
	 * SHEET所属文件名称（EXCEL模版名称）
	 */
	private String book;

	public String getBook() {
		return book;
	}

	public void setBook(String fieldBook) {
		book = fieldBook;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String fieldTitle) {
		title = fieldTitle;
	}

	public String getName() {
		return name;
	}

	public void setName(String fieldName) {
		name = fieldName;
	}

	public int getNumIndex() {
		return numIndex;
	}

	public void setNumIndex(int fieldNumIndex) {
		numIndex = fieldNumIndex;
	}

}
