package com.ttnn.framework.page.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.ttnn.framework.bean.FrameworkDataBean;

/**
 * 菜单栏目生成
 * 
 * @author duan.p
 * 
 */
public class MenuTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5715516227020927541L;

	private List<Object> menuVO; //菜单数据

	private Integer lgstyle; // 菜单样式



	public Integer getLgstyle() {
		return lgstyle;
	}

	public void setLgstyle(Integer lgstyle) {
		this.lgstyle = lgstyle;
	}

	public List<Object> getMenuVO() {
		return menuVO;
	}

	public void setMenuVO(List<Object> menuVO) {
		this.menuVO = menuVO;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter writer = this.pageContext.getOut();
			StringBuilder sb = new StringBuilder();
			if (menuVO.size() > 0) {
				switch (lgstyle) {
				case 1: // 贷付宝管理后台样式1
					sb.append("<div class=\"sidebar\"><div class=\"sidebar-wrap\"><ul class=\"nav\">	");
					for (int i = 0; i < menuVO.size(); i++) {
						FrameworkDataBean databean = (FrameworkDataBean) menuVO
								.get(i);
						if (databean.getFb1().equals("1")) {
							sb.append("<li class=\"on dropdown-submenu\" onmouseover=\"onover('").append(databean.getPuk()).append("')\" onmouseout=\"onout('").append(databean.getPuk()).append("')\">");
							sb.append(
									"<a tabindex=\"-1\" class=\"nav-main\" href=\"#\"")
									.append(" onclick=\"showForm(\'").append(databean.getPuk()).append("\','")
									.append(databean.getF03()).append("')\">")
									.append(databean.getK02()).append("</a>");
							boolean flag = false;
							
							
							
							
							
							for (int k = i + 1; k < menuVO.size(); k++) {
								FrameworkDataBean dataBean2 = (FrameworkDataBean) menuVO
										.get(k);
								if (dataBean2.getK03()
										.equals(databean.getK01())) {
									if (k == i + 1) {
										flag = true;
										sb.append("<ul class=\"dropdown-menu\" id=\"").append(databean.getPuk()).append("\" name=\"").append(databean.getPuk()).append("\" style=\"display: none;\">");
									}

									sb.append(
											"<li><a class=\"nav-main-a\" href=\"#\" onclick=\"showForm('").append(databean.getPuk()).append("','")
											.append(dataBean2.getF03())
											.append("')\">")
											.append(dataBean2.getK02())
											.append("</a></li>");
								} else {
									if (flag) {
										sb.append("</ul>");
									}
									break;
								}

							}

							sb.append("</li>");

						}
					}
					sb.append("</ul></div></div>");
					break;
				case 2: // 贷付宝样式菜单2
					sb.append("<div id=\"masterdiv\">");
                    int f=0;
					for (int i = 0; i < menuVO.size(); i++) {
						FrameworkDataBean databean = (FrameworkDataBean) menuVO
								.get(i);
						if (databean.getFb1().equals("1")) {
							sb.append(
									"<div class=\"menutitle\" onclick=\"SwitchMenu('sub")
									.append(f + 1).append("')\">")
									.append(databean.getK02()).append("</div>")
									.append("<span class=\"submenu\" id=\"sub")
									.append(f + 1)
									.append("\" style=\"display: none;\">");
                             f++; 
                             
                             for(int k=0;k<menuVO.size();k++){
                            		FrameworkDataBean dataBean2 = (FrameworkDataBean) menuVO
    										.get(k);
    								if (dataBean2.getK03()
    										.equals(databean.getK01())) {

    									sb.append("<a href=\"")
    											.append(dataBean2.getF03())
    											.append("\"  target=\"BizFrame\"  >")
    											.append(dataBean2.getK02())
    											.append("</a>"); 
                             }
    						 if(k==menuVO.size()-1){

    								sb.append("</span>");		
    						 }
                             
						/*	for (int k = i + 1; k < menuVO.size(); k++) {
								FrameworkDataBean dataBean2 = (FrameworkDataBean) menuVO
										.get(k);
								if (dataBean2.getK03()
										.equals(databean.getK01())) {

									sb.append("<a href=\"")
											.append(dataBean2.getF03())
											.append("\"  target=\"BizFrame\"  >")
											.append(dataBean2.getK02())
											.append("</a>");
								} else {

									break;
								}

							}*/


						}
					}
					}
					sb.append("</div>");
                    break;
				case 3: // 共通样式 - 菜单
					sb.append("<div class=\"head_m\"><ul id=\"first\">");
					for (int i = 0; i < menuVO.size(); i++) {
						FrameworkDataBean databean = (FrameworkDataBean) menuVO
								.get(i);
						if (databean.getFb1().equals("1")) {
							sb.append("<li><a href=\"#\">")
									.append(databean.getF01()).append("</a>");
							for (int k = i + 1; k < menuVO.size(); k++) {
								boolean flag = false;
								FrameworkDataBean dataBean2 = (FrameworkDataBean) menuVO
										.get(k);
								if (dataBean2.getK03()
										.equals(databean.getK01())) {
									if (k == i + 1) {
										flag = true;
										sb.append("<ul>");
									}

									sb.append("<li><a href=\"")
											.append(dataBean2.getF03())
											.append("\">")
											.append(dataBean2.getK02())
											.append("</a></li>");
								} else {
									if (flag) {
										sb.append("</ul>");
									}
									break;
								}

							}

							sb.append("</li>");

						}
					}

					sb.append("</ul></div>");

					break;
				case 4: //总控样式菜单
					sb.append("<div class=\"head\"><div class=\"header_zzjs\"><ul>");
					int m=1;
				    StringBuilder allSecondlevel = new StringBuilder(); 
					for (int i = 0; i < menuVO.size(); i++) {
						FrameworkDataBean databean = (FrameworkDataBean) menuVO
								.get(i);
						if (databean.getFb1().equals("1")) {
						     sb.append("<li onmouseover=\"javascript:toggle_nav(").append(m).append(")\"><a href=\"javascript:void()\">").append(databean.getF01()).append("</a></li>");
						     m++;
						}
						StringBuilder secondLevel = new StringBuilder();
						int s = 0 ;
						boolean flag = false;
						for(int k=0;k<menuVO.size();k++){
							FrameworkDataBean dataBean2 = (FrameworkDataBean) menuVO
									.get(k);
							
							if (dataBean2.getK03()
									.equals(databean.getK01())) {	
								s++;
								if(s==1){
									    flag = true;
										secondLevel.append("<div id='zzjs_nav").append(m-1).append("' class='headt' style='display: none;'>");
									
								}
								
								
									
								
								secondLevel.append("<a href='#'  onclick='showForm(\\\"").append(databean.getPuk()).append("\\\",\\\"")
								.append(dataBean2.getF03())
								.append("\\\")'>")
								.append(dataBean2.getK02())
								.append("</a>").append("&nbsp;|&nbsp;");
								
							}
							if(k==menuVO.size()-1&& flag){
								secondLevel.append("</div>");
							}
							
							
							
							
						}
						
				/*		for (int k = i + 1; k < menuVO.size(); k++) {
							boolean flag = false;
							FrameworkDataBean dataBean2 = (FrameworkDataBean) menuVO
									.get(k);
							if (dataBean2.getK03()
									.equals(databean.getK01())) {
								if (k == i + 1) {
									flag = true;
									secondLevel.append("<div id=\"zzjs_nav").append(m-1).append("\" class=\"headt\" style=\"display: none;\">");
								}

								secondLevel.append("<a href=\"")
										.append(dataBean2.getF03())
										.append("\">")
										.append(dataBean2.getK02())
										.append("</a>").append("|");
							} else {
								if (flag) {
									secondLevel.append("</div>");
								}
								break;
							}

						}*/
						
						allSecondlevel.append(secondLevel);
						
						}
					
					sb.append("</ul></div></div></div>");
					
				    sb.append("<script type=\"text/javascript\">").append(" var levelhtml=\"").append("<div class='clear'></div> <div class='head'> <div id='zzjs_nav0' class='headt' style='display:block'>欢迎信息</div>").append(allSecondlevel).append("\"; </script>");
					
					
					break ;
					
				case 5:
					
					break;
					
				default:
					sb.append("请填写正确的样式定义");
					break;
				}
				writer.write(sb.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

}
