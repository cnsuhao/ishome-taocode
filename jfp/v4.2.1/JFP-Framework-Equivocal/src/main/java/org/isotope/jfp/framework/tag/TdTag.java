package org.isotope.jfp.framework.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.isotope.jfp.framework.beans.page.PageModel;
import org.isotope.jfp.framework.common.MyDataBaseExtSupport;
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
public class TdTag extends TagSupport implements ISFrameworkConstants {
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

			String orderby = null;
			if (page != null) {
				orderby = page.currentOrderby();
			}
			String ico = "";
			//1.没有排序
			//2.升序
			//3.降序
			if (EmptyHelper.isEmpty(orderby)) {
				//orderby = "ORDER BY " + order + " ASC";
			} else 
			{
				if(orderby.indexOf(order) >= 0){
					if (orderby.indexOf("DESC") > 0) {
						order = "ORDER BY " + order + " ASC";						
						ico = "&nbsp;<span class=\"glyphicon glyphicon-menu-up\"></span>";
					}
					else {
						order = "ORDER BY " + order + " DESC";
						ico = "&nbsp;<span class=\"glyphicon glyphicon-menu-down\"></span>";
					}  
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append("<td style=\"cursor: pointer; " + style + "\" onclick=\"" + target + "('" + order + "')\">");
			sb.append(value);// 添加展示内容
			sb.append(ico);
			sb.append("</td>");
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
	// 页面的超链接
	private String target;
	private String value;
	private String style;
	private String order;
	// 分页实体
	private PageModel<MyDataBaseExtSupport> page;

	public PageModel<MyDataBaseExtSupport> getPage() {
		return page;
	}

	public void setPage(PageModel<MyDataBaseExtSupport> page) {
		this.page = page;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
}
