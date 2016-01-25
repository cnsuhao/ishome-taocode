package com.ttnn.framework.ept.bean.template;

import java.io.Serializable;

import com.ttnn.framework.ept.SEPTConstants;

/**
 * 操作模版POJO配置
 * @see <ept-template.xml>
 * @since 1.2
 * @version 1.3 模版升级
 */
public class BookBean implements SEPTConstants,Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = -7225448142218343495L;
	/**
	 * EXCEL输出模版标识
	 */
	private String name;
	/**
	 * 输出模版名称
	 */
	private String template;
	/**
	 * 输出文件名
	 */
	private String title;

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

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String fieldTemplate) {
		template = fieldTemplate;
	}

	@Override
	public String toString() {
		return "BookBean [name=" + name + ", template=" + template + ", title=" + title + "]";
	}

}
