package org.jfpc.common.menu;

import java.util.List;

import org.jfpc.constants.ISFrameworkConstants;

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
