package org.jfpc.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.jfpc.common.dict.DictionaryService;
import org.jfpc.framework.helper.BeanFactoryHelper;
import org.jfpc.framework.helper.EmptyHelper;

/**
 * 数据字典下拉列表
 * 
 * @author Spook
 * @since 0.2.1
 * @version 0.2.1 2014/12/1
 *
 */
public class DictionaryValueTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6474357359461589464L;

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

			//<label class="col-sm-4 control-label label-content">修改前内容</label>
			sb.append("<label");
			
			sb.append(" id=\"").append(id).append("\"");
			sb.append(" name=\"").append(name).append("\"");
			sb.append(" css=\"").append(css).append("\"");
			sb.append(">");
			
			//获取字典名称
			DictionaryService ds = BeanFactoryHelper.getBean("myDictionaryService");
			String dt = ds.getDictionaryName(value);
			
			if(EmptyHelper.isEmpty(dt))
				sb.append(value);
			else
				sb.append(dt);
			sb.append("</label>");

			sb.append("</select>");
			
			out.println(sb.toString());			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.println("<span>标签渲染出错</span>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return SKIP_BODY;
		}
		return EVAL_BODY_INCLUDE;
	}
	
	/////////////////////////////////////////////////////
	private String value="";//字典ID
	private String name="";
	private String css="";

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
}
