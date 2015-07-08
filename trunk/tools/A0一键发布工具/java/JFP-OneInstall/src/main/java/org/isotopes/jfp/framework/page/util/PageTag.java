package org.isotopes.jfp.framework.page.util;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.isotopes.jfp.framework.page.bean.PageVO;



/**
 * 分页标签菜单栏
 * @author duan.p
 *
 */
public class PageTag extends TagSupport {
    
	//样式
	private Integer style  = 1 ;
	
	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}
	
	
	@Override
	public  int doEndTag() throws JspException {	
		return this.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = null;
		try {
			out = this.pageContext.getOut();
			if (pageVo != null) {
				int currentPage = pageVo.getPageCurrent(); // 当前页
				if(currentPage==0){
					currentPage = 1;
				}
				int totalcount = pageVo.getResultCount(); // 总条数
				int totalPage = pageVo.getPageCount();  //总页数
				
				
				Map<String, String[]> map =  ((HttpServletRequest)pageContext.getRequest()).getParameterMap();  //请求的其他参数
				
				StringBuilder stb = new StringBuilder();
				int inx = 0;
				for(Map.Entry<String, String[]> entry: map.entrySet()){
					if(!entry.getKey().equals("pageCurrent")){
						 for(String value:entry.getValue()){
							 if(inx!=0){
								 stb.append("&");
							 }
							 stb.append(entry.getKey()).append("=").append(value);
						     inx++;
						 }
					}
				}
				String s = "?pageCurrent=";
                if(StringUtils.isNotEmpty(stb.toString())){
                	s="?"+stb.toString()+"&pageCurrent=";
                }
                StringBuilder sb = new StringBuilder();
				switch(style){
				case 1: // 样式1
				     sb.append("<div style=\"margin:0;\" class=\"pagination\">");
				     sb.append("<ul>");
					produceAHref(currentPage, totalPage, s, sb);				
					sb.append("</ul>");
					sb.append("</div>");
					break;
				case 2: //云端	
					 sb.append("<td valign='center' style='padding:0;' colspan='5'><div class='main-table-text'>共")
					 .append(totalcount).append("条信息，共计").append(totalPage).append("页，每页")
					 .append(pageVo.getPageLimit()).append("条").append("。</div>")
					 .append("<ul class='main-table-ul pagination pagination-sm'>");
						produceAHref(currentPage, totalPage, s, sb);
						sb.append("</ul></td>");

					 break;
				case 3: //样式3
					sb.append(
							"<div class=\"pagination pagination-right\"> <div style=\"float:left; line-height:34px;\">");
					sb.append("共").append(totalcount).append("条记录,").append("每页")
							.append(pageVo.getPageLimit()).append("条,共")
							.append(totalPage).append("页").append("</div>");
					sb.append("<ul>");
					produceAHref(currentPage, totalPage, s, sb);				
					sb.append("</ul>");
					sb.append("</div>");
				   break; 
				 
				case 4:   //样式4
				      sb.append("<ul class='pagination pagination-sm' style='margin:0;'> ");
				      produceAHref(currentPage, totalPage, s, sb);
				      sb.append("</ul>");
					 break;
				case 5:	 //样式5
					
					 break;
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

		return this.EVAL_BODY_INCLUDE;
	}
    
	/**
	 * 生成超链接
	 * @param currentPage 当前页
	 * @param totalPage 总页数
	 * @param s
	 * @param sb
	 */
	private void produceAHref(int currentPage, int totalPage, String s,
			StringBuilder sb) {
		// 页面总数在1
		if (currentPage == 1 && totalPage == 1) {
			sb.append("<li class=\"active\"><a href=\"").append(url).append("\">1</a></li>");
		}

		// 页面总数在5以内

		if (totalPage > 1 && totalPage <= 5) {
			for (int i = 1; i <= totalPage; i++) {

				if (i == currentPage) {
					sb.append("<li class=\"active\"><a href=\"").append(url).append(s)
							.append(i).append("\">").append(i)
							.append("</a></li>");

				} else {
					sb.append("<li><a href=\"").append(url).append(s).append(i).append("\">")
							.append(i).append("</a></li>");
				}

			}
		}

		// 页面超过5页，让其保持剧中  类似于 // 23 4 56

		if (totalPage > 5 && currentPage >= 3) {
			

			// 总页数
		    
			sb.append("<li><a href=\"").append(url).append(s).append("1").append("\">")
			.append("首页").append("</a></li>");
			sb.append("<li><a href=\"").append(url).append(s).append(currentPage-1).append("\">")
			.append("上一页").append("</a></li>"); 
			int gap = totalPage - currentPage; // 当前页与尾页的距离

			if (gap >= 2) {  // 23 4 56

				for (int i = currentPage - 2; i < currentPage; i++) {
					sb.append("<li><a href=\"").append(url).append(s).append(i).append("\">")
							.append(i).append("</a></li>");
				}

				sb.append("<li class=\"active\" ><a href=\"").append(url).append(s)
						.append(currentPage).append("\">")
						.append(currentPage).append("</a></li>");
				for (int i = currentPage + 1; i <= currentPage + 2; i++) {
					sb.append("<li><a href=\"").append(url).append(s).append(i).append("\">")
							.append(i).append("</a></li>");
				}
			} else if (gap == 0) {  // 3456 7
				for (int i = currentPage - 4; i < currentPage; i++) {
					sb.append("<li  ><a href=\"").append(url).append(s).append(i).append("\">")
							.append(i).append("</a></li>");
				}
				sb.append("<li class=\"active\" ><a href=\"").append(url).append(s).append(currentPage).append("\">")
				.append(currentPage).append("</a></li>");
			} else if (gap == 1) {   //345 6 7
				for (int i = currentPage - 3; i < currentPage; i++) {
					sb.append("<li><a href=\"").append(url).append(s).append(i).append("\">")
							.append(i).append("</a></li>");
				}

				sb.append("<li class=\"active\" ><a href=\"").append(url).append(s)
						.append(currentPage).append("\">")
						.append(currentPage).append("</a></li>");
				sb.append("<li><a href=\"").append(url).append(s).append(currentPage + 1)
						.append("\">").append(currentPage + 1)
						.append("</a></li>");
			}
			
			
			if(currentPage!=totalPage){
				sb.append("<li><a href=\"").append(url).append(s).append(currentPage+1).append("\">")
				.append("下一页").append("</a></li>"); 
			}
			
			sb.append("<li><a href=\"").append(url).append(s).append(totalPage).append("\">")
			.append("尾页").append("</a></li>"); 

		}
		
		//总页数>5 但当前页在第1或者2页
		if(totalPage>5&&currentPage<3){
			for (int i = 1; i <= 5; i++) {

				if (i == currentPage) {
					sb.append("<li class=\"active\"><a href=\"").append(url).append(s)
							.append(i).append("\">").append(i)
							.append("</a></li>");

				} else {
					sb.append("<li><a href=\"").append(url).append(s).append(i).append("\">")
							.append(i).append("</a></li>");
				}

			}
			if(currentPage!=totalPage){
				sb.append("<li><a href=\"").append(url).append(s).append(currentPage+1).append("\">")
				.append("下一页").append("</a></li>"); 
			}
			
			sb.append("<li><a href=\"").append(url).append(s).append(totalPage).append("\">")
			.append("尾页").append("</a></li>");
		}
	}

	

	
	private static final long serialVersionUID = -436689146502854892L;
	
	
	//分页实体
	private PageVO pageVo;
    
	//页面的超链接
	private String url;


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public PageVO getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVO pageVo) {
		this.pageVo = pageVo;
	}

}
