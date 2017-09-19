package org.isotope.jfp.framework.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;

/**
 * 分页标签菜单栏
 * 
 * @author Spook
 * @version 4.1.3 2017/07/12
 *
 */
public class TrLineTag extends TagSupport implements ISFrameworkConstants {
	private static final long serialVersionUID = -7022081226075145681L;

	@Override
	public int doEndTag() throws JspException {
		return Tag.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = null;
		try {
			out = this.pageContext.getOut();
			StringBuilder sb = new StringBuilder();
			if(EmptyHelper.isEmpty(height))
				height = "20";
			sb.append("<tr height=\""+height+"px\" />");
			out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.write("<span>分页标签渲染出错</span>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	/////////////////////////////////////////////////////////////////////////////
	private String height;

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}
}
