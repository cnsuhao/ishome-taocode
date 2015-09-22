package org.jfpc.framework.page;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

/**
 * 分页标签栏
 * @since 0.0.11 2012-9-16
 * @version 0.1
 */
public class PageTag extends TagSupport {

	private static final long serialVersionUID = -436689146502854892L;

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = null;
		try {
			out = this.pageContext.getOut();
			if (pageVo != null && pageVo.getResultCount()>0) {
				int currentPage = pageVo.getPageCurrent(); // 当前页
				if (currentPage == 0) {
					currentPage = 1;
				}
				int totalcount = pageVo.getResultCount(); // 总条数
				int totalPage = pageVo.getPageCount(); // 总页数
				StringBuilder sb = new StringBuilder();
				if (totalcount>0) {
					sb.append("<div class=\"pagination-count\">共计：");
					sb.append(totalcount).append("条&nbsp;&nbsp;第");
					sb.append(currentPage).append("/").append(totalPage);
					sb.append("页</div>");
					sb.append("<ul class=\"pagination pagination-sm\">\r\n");
					producePageDiv(currentPage, totalPage, sb);
					sb.append("</ul>\r\n");
				}
				sb.append("<input type=\"hidden\" name=\"pageCurrent\" id=\"pageCurrent\" value=\"1\"/>\r\n");	
				if (StringUtils.isEmpty(pageLimit))
					sb.append("<input type=\"hidden\" name=\"pageLimit\" id=\"pageLimit\" value=\""+pageVo.getPageLimit()+"\"/>\r\n");			
				else
					sb.append("<input type=\"hidden\" name=\"pageLimit\" id=\"pageLimit\" value=\""+pageLimit+"\"/>\r\n");			

				if (StringUtils.isEmpty(onClick)) {
					sb.append("<script type=\"text/javascript\">\r\n");
					sb.append("function doSubmitPageTag(current){\r\n");
					sb.append("	$(\"#pageCurrent\").val(current);\r\n");
					sb.append("	$(\"form\").eq(0).submit();\r\n");
					sb.append("}\r\n");
					sb.append("</script>\r\n");
				}
				out.println(sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.write("<span>分页标签渲染出错</span>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return SKIP_BODY;
		}

		return EVAL_BODY_INCLUDE;
	}

	// <ul class="pagination">
	// <li class="disabled">
	// <a href="#">&laquo;</a>
	// </li>
	// <li class="active"><a href="#">1</a></li>
	// <li><a href="#">2</a></li>
	// <li><a href="#">3</a></li>
	// <li><a href="#">4</a></li>
	// <li><a href="#">5</a></li>
	// <li><a href="#">&raquo;</a></li>
	// </ul>
	
	// 页面的超链接
	private void produceAction(StringBuilder sb, int page, String name, boolean select) {
		sb.append("<li");
		if (select) {
			sb.append(" class=\"active\"");
		}
		sb.append(">");
		sb.append("<a href=\"javascript:");
		{
			if (StringUtils.isNotEmpty(onClick)) {
				sb.append(onClick+page+")");
			} else {
				if(!select)
					sb.append("doSubmitPageTag("+page+");");
				else
					sb.append("void(0);");
			}
			sb.append("\">").append(name).append("</a>");
		}
		sb.append("</li>\r\n");
	}

	/**
	 * 生成超链接
	 * 
	 * @param currentPage
	 *            当前页
	 * @param totalPage
	 *            总页数
	 * @param s
	 * @param sb
	 */
	// 首页 上一页 1 2 3 4 5 下一页 末页 5/35
	private void producePageDiv(int currentPage, int totalPage, StringBuilder sb) {
		// 页面总数在1
		if (currentPage == 1 && totalPage == 1) {
			produceAction(sb, 1, "1", true);
		}
		// 页面总数在5以内
		else if (totalPage > 1 && totalPage <= 5) {
			for (int i = 1; i <= totalPage; i++) {
				if (i == currentPage) {
					produceAction(sb, i, "" + i, true);
				} else {
					produceAction(sb, i, "" + i, false);
				}
			}
		}
		// 页面超过5页，让其保持剧中 类似于 // 23 4 56
		else if (totalPage > 5 && currentPage >= 3) {
			produceAction(sb, 1, "首页", false);
			// 总页数
			produceAction(sb, currentPage - 1, "上一页", false);
			int gap = totalPage - currentPage; // 当前页与尾页的距离
			if (gap >= 2) { // 23 4 56
				for (int i = currentPage - 2; i < currentPage; i++) {
					produceAction(sb, i, "" + i, false);
				}
				produceAction(sb,currentPage, "" + currentPage, true);
				for (int i = currentPage + 1; i <= currentPage + 2; i++) {
					produceAction(sb, i, "" + i, false);
				}
			} else if (gap == 0) { // 3456 7
				for (int i = currentPage - 4; i < currentPage; i++) {
					produceAction(sb, i, "" + i, false);
				}
				produceAction(sb, currentPage, ""+currentPage, true);
			} else if (gap == 1) { // 345 6 7
				for (int i = currentPage - 3; i < currentPage; i++) {
					produceAction(sb, i, "" + i, false);
				}
				produceAction(sb, currentPage, ""+currentPage, true);
				produceAction(sb, currentPage+1, ""+(currentPage+1), true);
			}

			if (currentPage != totalPage) {
				produceAction(sb, currentPage+1, "下一页", false);
			}
			produceAction(sb, totalPage, "末页", false);
		}
		// 总页数>5 但当前页在第1或者2页
		else if (totalPage > 5 && currentPage < 3) {
			for (int i = 1; i <= 5; i++) {
				if (i == currentPage) {
					produceAction(sb, i, "" + i, true);
				} else {
					produceAction(sb, i, "" + i, false);
				}
			}
			if (currentPage != totalPage) {
				produceAction(sb, currentPage+1, "下一页", false);
			}
			produceAction(sb, totalPage, "末页", false);
		}
	}
	// 分页实体
	private PageVOSupport pageVo;
	private String onClick;
	private String pageLimit;
	
	public String getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(String pageLimit) {
		this.pageLimit = pageLimit;
	}

	public PageVOSupport getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVOSupport pageVo) {
		this.pageVo = pageVo;
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}
	
}
