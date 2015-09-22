package com.ttnn.business.cs.dao;

import java.util.List;

import com.ttnn.framework.support.CSPVOSupport;

/**
 * 用户组
 * @author Administrator
 *
 */
public interface UserGroupDao {
	/**
	 * 查找上级用户
	 * @return
	 */
	public List<String>  findParentGroup(CSPVOSupport paramBean);
}
