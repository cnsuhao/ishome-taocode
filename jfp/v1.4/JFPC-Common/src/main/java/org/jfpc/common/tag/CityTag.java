package org.jfpc.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.jfpc.common.dict.DictionaryService;
import org.jfpc.framework.helper.BeanFactoryHelper;

/**
 * 省市区下拉列表
 * 
 * @author Administrator
 * 
 */
public class CityTag extends TagSupport {

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

			// 创建省
			{
				sb.append("<select");

				sb.append(" id=\"").append(provinceId).append("\"");
				sb.append(" name=\"").append(provinceId).append("\"");
				sb.append(" css=\"").append(css).append("\"");
				sb.append(" onChange=\"").append(onChange).append("\"");
				sb.append(">");

				DictionaryService ds = BeanFactoryHelper.getBean("myDictionaryService");
				ds.getProvince(province, provinceId, sb);

				sb.append("</select>");
			}
			// 创建市
			{
				sb.append("<select");

				sb.append(" id=\"").append(provinceId).append("\"");
				sb.append(" name=\"").append(provinceId).append("\"");
				sb.append(" css=\"").append(css).append("\"");
				sb.append(" onChange=\"").append(onChange).append("\"");
				sb.append(">");

				DictionaryService ds = BeanFactoryHelper.getBean("myDictionaryService");
				ds.getCity(province, provinceId,city,cityId, sb);

				sb.append("</select>");
			}
			// 创建区
			{
				sb.append("<select");

				sb.append(" id=\"").append(provinceId).append("\"");
				sb.append(" name=\"").append(provinceId).append("\"");
				sb.append(" css=\"").append(css).append("\"");
				sb.append(" onChange=\"").append(onChange).append("\"");
				sb.append(">");

				DictionaryService ds = BeanFactoryHelper.getBean("myDictionaryService");
				ds.getArea(province, provinceId,city,cityId,area,areaId, sb);

				sb.append("</select>");
			}
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

	private String name = "";
	private String css = "";
	private String onChange = "";

	private String province = "";// 省
	private String provinceId = "";// 省
	private String city = "";// 市
	private String cityId = "";// 市
	private String area = "";// 区
	private String areaId = "";// 区
}
