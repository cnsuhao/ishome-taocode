package org.isotopes.jfp.framework.support;

import org.isotopes.jfp.framework.ISFrameworkConstants;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;

public class CSModelAndViewSupport extends ModelAndView implements
		ISFrameworkConstants {

	/**
	 * 页面版本
	 */
	private String pageVerssion = "";

	/**
	 * 获得当前画面的版本号，根据产品ID
	 * 
	 * @return
	 */
	public String getPageVerssion() {
		return pageVerssion;
	}

	public void setPageVerssion(String pageVerssion) {
		// TODO 根据画面ID、产品ID获得实际操作页面
		//this.pageVerssion = pageVerssion;
	}

	/**
	 * 设定返回画面名称
	 * 
	 * @param viewName
	 *            画面名称
	 * @param verssion
	 *            版本ID
	 */
	public CSModelAndViewSupport(CSPVOSupport fVO) {
		super();
		setPageVerssion(fVO.getPageID());
	}

	/**
	 * 设定返回画面名称
	 * 
	 * @param viewName
	 *            画面名称
	 */
	public void setViewName(String viewName) {
		if (StringUtils.isNullOrEmpty(pageVerssion))
			super.setViewName(viewName);
		else
			super.setViewName(viewName + pageVerssion);

	}

}
