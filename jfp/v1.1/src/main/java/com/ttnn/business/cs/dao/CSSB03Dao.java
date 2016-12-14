package com.ttnn.business.cs.dao;
import java.util.List;

import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
/** 系统画面菜单*/
public interface CSSB03Dao extends ISSQLDaoSupport{
   
	public List findTree(CSPVOSupport paramBean);
}
