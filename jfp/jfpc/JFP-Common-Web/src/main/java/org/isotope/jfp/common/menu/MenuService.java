package org.isotope.jfp.common.menu;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.isotope.jfp.common.login.LoginService;
import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 用户菜单
 * @author Administrator
 *
 */
@Service
public class MenuService extends MyServiceSupport implements ISFrameworkConstants {
	
	private static final Logger logger = LoggerFactory.getLogger(MenuService.class);
	@Resource
	private LoginService LoginService_;
	//用户分页
	@Resource
	private PageVOSupport pageModel;
	
	public MenuDao getMenuDao() {
		return getMySqlSession().getMapper(MenuDao.class);
	}

	/**
	 * 用户登录(WEB页面)
	 * 
	 * @param productId
	 * @return
	 */
	public List<MenuBean> loadMenu(MenuBean user) {
		// 设定返回
		//用户所属角色MSSRR
		//角色菜单权限MSSRR
		if (logger.isDebugEnabled())
			logger.debug("loadMenu====///loadMenu////loadMenu=======>>>>>=========>>>" + user);
		
		return getMenuDao().loadMenu(user);
	}

//	@Resource
//	private MS0A1Service MS0A1Service_;
//	private void loadMenus() {
//		// 菜单
//		MenuBean menu;
//		pageModel.config();
//		pageModel.setFormParamBean(new MS0A1DBO());
//		pageModel.setOrderby("f05_plsx asc");
//		for(FrameworkDataBean item : MS0A1Service_.doSelectPage(pageModel, false).getPageListData())
//		{
//			menu = new MenuBean();
//			BeanUtils.copyProperties(item, menu);
//			menus.add(menu);
//		}
//	}
//
//	private ArrayList<MenuBean> menus = new ArrayList<MenuBean>();
//
//	public List<MenuBean> loadMenu() {
//		return menus;
//	}
//	
//	/**
//	 * 获得需要显示的全部菜单
//	 * 
//	 * @param userType 用户类别
//	 * @return
//	 */
//	public List<MenuBean> loadMenu(String userType) {
//		if(menus==null||menus.size()==0) loadMenus();
//		ArrayList<MenuBean> rs = new ArrayList<MenuBean>();
//		try {
//			for (MenuBean menu : menus) {
//				if (userType.equals(""+menu.getPuk().charAt(0))){
//					rs.add(menu);
//				}
//			}
//		} catch (Exception e) {
//		}
//		return rs;
//	}
//	
//	/**
//	 * 获得需要显示的菜单
//	 * 
//	 * @param level
//	 * @param userType
//	 * @return
//	 */
//	public List<MenuBean> loadMenu(String level, String menuid, String userType) {
//		if(menus==null||menus.size()==0) loadMenus();
//		ArrayList<MenuBean> rs = new ArrayList<MenuBean>();
//		try {
//			for (MenuBean menu : menus) {
//				//有效性
//				if(ONE.equals(menu.getDdd()))
//					continue;
//				//用户类别
////				if(StringUtils.isEmpty(userType)){
////					if(StringUtils.isEmpty(menuid)){
////						if(StringUtils.isEmpty(level)){
////							rs.add(menu);
////						}else if (menu.getF06_cddj().equals(level)){
////							rs.add(menu);							
////						}						
////					}
////					else if(menu.getK01_xsfcdid().equals(menuid)){
////						if(StringUtils.isEmpty(level))
////						{
////							rs.add(menu);
////						}else if (menu.getF06_cddj().equals(level)){
////							rs.add(menu);							
////						}
////					}
////				}else 
//				if (userType.equals(""+menu.getPuk().charAt(0))){//包含的场合
//					if(StringUtils.isEmpty(menuid)){
//						if(StringUtils.isEmpty(level)){
//							rs.add(menu);
//						}else if (menu.getF06_cddj().equals(level)){
//							rs.add(menu);							
//						}						
//					}
//					else if(menu.getK01_xsfcdid().equals(menuid)){
//						if(StringUtils.isEmpty(level))
//						{
//							rs.add(menu);
//						}else if (menu.getF06_cddj().equals(level)){
//							rs.add(menu);							
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//		}
//		return rs;
//	}
}
