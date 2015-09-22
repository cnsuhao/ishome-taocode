package com.ttnn.business.cs.dao;
import java.util.List;

import com.ttnn.framework.support.ISSQLDaoSupport;
/** 用户组定义*/
public interface CSSR03Dao extends ISSQLDaoSupport{
   
	/**
	 * 查找上级用户
	 * @return
	 */
	public List<String>  findParentGroup();
	
}
