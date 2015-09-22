package org.jfpc.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.jfpc.common.dict.DictionaryService;
import org.jfpc.framework.helper.BeanFactoryHelper;

/**
 * 数据字典下拉列表
 * 
 * @author Spook
 * @since 0.2.1
 * @version 0.2.1 2014/12/1
 * 
 */
public class DictionaryTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4198502887189042959L;

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = null;
		try {
			out = this.pageContext.getOut();
			StringBuilder sb = new StringBuilder();

			// <select class="form-control" id="f12_ssgj" name="f12_ssgj">
			// </select>
			sb.append("<select");

			sb.append(" id=\"").append(id).append("\"");
			sb.append(" name=\"").append(name).append("\"");
			sb.append(" css=\"").append(css).append("\"");
			sb.append(" onChange=\"").append(onChange).append("\"");
			sb.append(">");

			//获取字典列表
			DictionaryService ds = BeanFactoryHelper.getBean("myDictionaryService");
			ds.getDictionary(ggg, big, middle, small, value, sb, true);

			sb.append("</select>");

			out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.println("<span>国家标签渲染出错</span>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return SKIP_BODY;
		}
		return EVAL_BODY_INCLUDE;
	}

	// ///////////////////////////////////////////////////
	private String value = "";
	private String name = "";
	private String css = "";
	private String onChange = "";

	private String ggg = "";// 字典分类标识
	private String big = "";// 大分类
	private String middle = "";// 中分类
	private String small = "";// 小分类

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getOnChange() {
		return onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public String getGgg() {
		return ggg;
	}

	public void setGgg(String ggg) {
		this.ggg = ggg;
	}

	public String getBig() {
		return big;
	}

	public void setBig(String big) {
		this.big = big;
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public String getSmall() {
		return small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

}
