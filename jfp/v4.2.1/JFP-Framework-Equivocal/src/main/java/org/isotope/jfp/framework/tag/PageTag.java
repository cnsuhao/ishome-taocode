package org.isotope.jfp.framework.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.isotope.jfp.framework.beans.page.PageModel;
import org.isotope.jfp.framework.beans.page.PageModelHelper;
import org.isotope.jfp.framework.common.MyDataBaseExtSupport;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;

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
public class PageTag extends TagSupport implements ISFrameworkConstants {

	@Override
	public int doEndTag() throws JspException {
		return Tag.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = null;
		try {
			out = this.pageContext.getOut();
			if (page != null) {
				int currentPage = page.getPageCurrent(); // 当前页
				if (currentPage == 0) {
					currentPage = 1;
				}
				int totalcount = page.getResultCount(); // 总条数
				int totalPage = page.getPageCount(); // 总页数

				StringBuilder paramStringBuilder = PageModelHelper.preparePageModelFormParamBean(((HttpServletRequest) pageContext.getRequest()), page, url);

				paramStringBuilder.append("&pageCurrent=");

				String paramStr = paramStringBuilder.toString();
				StringBuilder sb = new StringBuilder();
				sb.append("<table>");
				sb.append("<tr>");
				///////////////////////////// 左侧统计///////////////////////////////////////////
				sb.append("<td align=\"left\">");
				sb.append("<table>");
				sb.append("<tr><td style=\"width:24px;\"></td>");
				sb.append("<td style=\"width:" + (("" + totalcount).length() * 14 + 66) + "px;\">共" + totalcount + "条记录</td>");
				sb.append("<td style=\"width:" + (("" + page.getPageLimit()).length() * 12 + 52) + "px\">每页" + page.getPageLimit() + "条</td>");
				sb.append("<td style=\"width:" + (("" + totalPage).length() * 14 + 38) + "px;\">共" + totalPage + "页</td>");
				sb.append("<td></td></tr>");
				sb.append("</table>");
				sb.append("</td>");
				//////////////////////////////////// 右侧分页//////////////////////////////////////
				sb.append("<td align=\"right\">");
				sb.append("<table>");
				sb.append("<tr><td></td>");

				// sb.append("<td>首页</td>");
				// sb.append("<td>上一页</td>");
				// sb.append("<td
				// onclick=\"parent.showPageForm('").append(url).append(paramStr).append("')\"
				// style=\"color:#fff; text-align:center; cursor:
				// pointer;background: #333333;\">1</td>");
				// sb.append("<td onclick=\"parent.showPageForm('www')\"
				// style=\"text-align:center; cursor: pointer;\">2</td>");
				// sb.append("<td>3</td>");
				// sb.append("<td>4</td>");
				// sb.append("<td>5</td>");
				// sb.append("<td>下一页</td>");
				// sb.append("<td>尾页</td>");

				// 页面总数在1
				if (totalPage == 1) {
					sb.append("<td style=\"color:#fff; text-align:center; cursor: pointer;background: #333333;width:24px;\">1</td>");
				}
				// 页面总数在5以内
				else if (totalPage > 1 && totalPage <= 5) {
					for (int i = 1; i <= totalPage; i++) {
						if (i == currentPage) {
							sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\" style=\"color:#fff; text-align:center; cursor: pointer;background: #333333;width:24px;\">").append(i).append("</td>");
						} else {
							sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\" style=\"text-align:center; cursor: pointer;width:24px;\">").append(i).append("</td>");
						}
					}
				}
				// 页面超过5页，让其保持剧中 类似于 // 23 4 56
				else if (totalPage > 5) {
					if (currentPage >= 3) {
						// 总页数
						sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append("1").append("')\" style=\"text-align:center; cursor: pointer;width:32px;\">").append("首页").append("</td>");
						sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage - 1).append("')\" style=\"text-align:center; cursor: pointer;width:48px;\">").append("上一页").append("</td>");
						int gap = totalPage - currentPage; // 当前页与尾页的距离
						if (gap >= 2) { // 23 4 56
							for (int i = currentPage - 2; i < currentPage; i++) {
								sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\" style=\"text-align:center; cursor: pointer;width:24px;\">").append(i).append("</td>");
							}
							sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage).append("')\" style=\"color:#fff; text-align:center; cursor: pointer;background: #333333;width:24px;\">").append(currentPage).append("</td>");
							for (int i = currentPage + 1; i <= currentPage + 2; i++) {
								sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\" style=\"text-align:center; cursor: pointer;width:24px;\">").append(i).append("</td>");
							}
						} else if (gap == 0) { // 3456 7
							for (int i = currentPage - 4; i < currentPage; i++) {
								sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\" style=\"text-align:center; cursor: pointer;width:24px;\">").append(i).append("</td>");
							}
							sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage).append("')\" style=\"color:#fff; text-align:center; cursor: pointer;background: #333333;width:24px;\">").append(currentPage).append("</td>");
						} else if (gap == 1) { // 345 6 7
							for (int i = currentPage - 3; i < currentPage; i++) {
								sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\" style=\"text-align:center; cursor: pointer;width:24px;\">").append(i).append("</td>");
							}

							sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage).append("')\" style=\"color:#fff; text-align:center; cursor: pointer;background: #333333;width:24px;\">").append(currentPage).append("</td>");
							sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage + 1).append("')\" style=\"text-align:center; cursor: pointer;width:24px;\">").append(currentPage + 1).append("</td>");
						}

						if (currentPage != totalPage) {
							sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage + 1).append("')\" style=\"text-align:center; cursor: pointer;width:48px;\">").append("下一页").append("</td>");
						}
						sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(totalPage).append("')\" style=\"text-align:center; cursor: pointer;width:32px\">").append("尾页").append("</td>");
					}
					// 总页数>5 但当前页在第1或者2页
					else if (currentPage < 3) {
						for (int i = 1; i <= 5; i++) {
							if (i == currentPage) {
								sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\" style=\"color:#fff; text-align:center; cursor: pointer;background: #333333;width:24px;\">").append(i).append("</td>");
							} else {
								sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(i).append("')\" style=\"text-align:center; cursor: pointer;width:24px;\">").append(i).append("</td>");
							}
						}
						if (currentPage != totalPage) {
							sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(currentPage + 1).append("')\" style=\"text-align:center; cursor: pointer;width:48px;\">").append("下一页").append("</td>");
						}
						sb.append("<td onclick=\"parent.showPageForm('").append(url).append(paramStr).append(totalPage).append("')\" style=\"text-align:center; cursor: pointer;width:32px;\">").append("尾页").append("</td>");
					}
				}

				sb.append("<td style=\"width:24px;\"></td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
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

		return Tag.EVAL_BODY_INCLUDE;
	}

	private static final long serialVersionUID = -436689146502854892L;

	// 分页实体
	private PageModel<MyDataBaseExtSupport> page;

	// 页面的超链接
	private String url;

	public PageModel<MyDataBaseExtSupport> getPage() {
		return page;
	}

	public void setPage(PageModel<MyDataBaseExtSupport> page) {
		this.page = page;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
