package org.jfpc.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.jfpc.common.dict.DictionaryService;
import org.jfpc.framework.helper.BeanFactoryHelper;

/**
 * 国家下拉列表
 * @author Administrator
 *
 */
public class CountryTag extends TagSupport {

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
			//<select class="form-control" id="f12_ssgj" name="f12_ssgj">
			//</select>
			sb.append("<select");
			
			sb.append(" id=\"").append(id).append("\"");
			sb.append(" name=\"").append(name).append("\"");
			sb.append(" css=\"").append(css).append("\"");
			sb.append(" onChange=\"").append(onChange).append("\"");
			sb.append(">");
			
			DictionaryService ds = BeanFactoryHelper.getBean("myDictionaryService");
			ds.getCountry(value, sb, true);			

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
	
	
	
	
	/////////////////////////////////////////////////////////////
	private String value="";//国家三字符代码	

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	private String name="";
	private String css="";
	
	private String onChange="";

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
}
