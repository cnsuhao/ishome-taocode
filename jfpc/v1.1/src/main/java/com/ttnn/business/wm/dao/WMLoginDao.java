package com.ttnn.business.wm.dao;
import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.ISSQLDaoSupport;

/** 用户首页*/
public interface WMLoginDao extends ISSQLDaoSupport{

	
	/**
	 * 根据用户ID获得功能快捷菜单（顶部）
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public FrameworkDataBean inMySystem(FrameworkDataBean dbParamBean);
	
	/**
	 * 根据用户ID获得功能快捷菜单（顶部）
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public FrameworkDataBean getMySystem(FrameworkDataBean dbParamBean);

	/**
	 * 根据用户ID获得功能快捷菜单（顶部）
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> getMyTopMenu(FrameworkDataBean dbParamBean);
	
	/**
	 * 根据用户ID获得全部业务菜单（左上侧）
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> getMyRool(FrameworkDataBean dbParamBean);

	/**
	 * 超级管理员获得全部业务菜单（左上侧）
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> getAllMyRool(FrameworkDataBean dbParamBean);
	
	/**
	 * 根据用户ID获得应用菜单（左下侧）
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> getMyApp(FrameworkDataBean dbParamBean);
}
