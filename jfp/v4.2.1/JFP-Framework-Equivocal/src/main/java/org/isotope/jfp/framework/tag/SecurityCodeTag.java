package org.isotope.jfp.framework.tag;

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
 * @version 4.1.3 2017/04/15
 * @version 4.1.1 2016/12/12
 * @version 3.2.1 2016/08/28
 * @since 3.2.1 2016/08/28
 *
 */
public class SecurityCodeTag extends TagSupport implements ISFrameworkConstants {
	private static final long serialVersionUID = -5037377433501297479L;

	@Override
	public int doEndTag() throws JspException {
		return Tag.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = null;
		try {
			out = this.pageContext.getOut();
			if (EmptyHelper.isNotEmpty(token)) {
				StringBuilder sb = new StringBuilder();

				sb.append("<table align=\"center\" border=\"0\" width=\"100%\" >");
				sb.append("<tr>");
				if (EmptyHelper.isEmpty(id)) {
					id = " ";
				} else {
					id = " id=\"" + id + "\" ";
				}
				sb.append("<td><input type=\"text\" class=\"form-control\" name=\""+name+"\" " + id + "placeholder=\"安全码\" /></td>");
				sb.append("<td width=\"" + size + "%\" >&nbsp;</td>");
				sb.append("<td><img src=\"/99993030?token=" + token + "\" width=\"65\" height=\"22\" onclick=\"this.src='/99993030?token=" + token + "&d='+Math.random();\"/></td>");
				sb.append("</tr>");
				sb.append("</table>");

				out.println(sb.toString());

			}
		} catch (Exception e) {
			return SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	private String name;
	private int size = 5;
	private String token;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
