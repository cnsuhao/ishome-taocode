package com.ttnn.business.cs.dao;

import java.util.List;

import com.ttnn.framework.page.util.MyMap;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;

/**
 * 菜单
 * @author Administrator
 *
 */
public interface MenuDao extends ISSQLDaoSupport{
 
	public List<MyMap> findTree(CSPVOSupport paramBean);
	
	/**
	 * 删除权限
	 * @param paramBean
	 */
	public int deteteUserGroupRight(CSPVOSupport paramBean);
	
	
	/**
	 * 添加权限
	 * @param paramBean
	 */
	public int modifyUserGroupRight(CSPVOSupport paramBean);
	
	/**
	 * 添加权限
	 * @param paramBean
	 */
	public void insertUserGroupRight(CSPVOSupport paramBean);
	
	/**
	 * 用户所属用户组
	 * @param paramBean
	 */
	public List<String> selectUserInGroup(CSPVOSupport paramBean);
	
}
