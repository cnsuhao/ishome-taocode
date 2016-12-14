package com.ttnn.business.cs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ttnn.business.cs.dao.MenuDao;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/**
 * 用户组赋权
 * @author Administrator
 *
 */
@Service
public class MenuService extends MyServiceSupportImpl {
    
	
	
	@Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(MenuDao.class);
    }
	
	
	/**
	 * 删除权限
	 * @param paramBean
	 */
	public int deteteUserGroupRight(CSPVOSupport paramBean){
		return mySqlSession.getMapper(MenuDao.class).deteteUserGroupRight(paramBean);
	}
	
	
	
	/**
	 * 添加权限
	 * @param paramBean
	 */
	public void insertUserGroupRight(CSPVOSupport paramBean){
	  	mySqlSession.getMapper(MenuDao.class).insertUserGroupRight(paramBean);
	}
	
	/**
	 * 修改权限
	 * 
	 */
	public int modifyUserGroupRight(CSPVOSupport paramBean){
		return mySqlSession.getMapper(MenuDao.class).modifyUserGroupRight(paramBean);

	}
	
	/**
	 * 查询用户所在用户组
	 * @param paramBean
	 * @return
	 */
	public List<String> selectUserInGroup(CSPVOSupport paramBean){
	 return	mySqlSession.getMapper(MenuDao.class).selectUserInGroup(paramBean);
	}
	
}
