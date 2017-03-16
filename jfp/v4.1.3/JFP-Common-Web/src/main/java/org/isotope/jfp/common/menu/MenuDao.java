package org.isotope.jfp.common.menu;

import java.util.List;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;

/**
 * 获得当前用户操作菜单
 * @author Administrator
 *
 */
public interface MenuDao extends ISFrameworkConstants{	
	
	/**
	 * 获得当前用户操作菜单
	 * @param loginer
	 * @return
	 */
	public List<MenuBean> loadMenu(MenuBean user);
	
}
