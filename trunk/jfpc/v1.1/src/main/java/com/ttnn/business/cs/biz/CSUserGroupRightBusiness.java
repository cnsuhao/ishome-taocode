package com.ttnn.business.cs.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ttnn.business.cs.service.MenuService;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyBusinessSupportImpl;

/**
 * 用户组赋权
 * @author duan.p
 *
 */
@Service
public class CSUserGroupRightBusiness extends MyBusinessSupportImpl{
     
	 @Resource
	 protected MenuService menuService;
	 
	 public void saveGrant(CSPVOSupport paramBean){	
	    	//删除已经废弃的权限
		  menuService.modifyUserGroupRight(paramBean);
		  menuService.deteteUserGroupRight(paramBean);   
		 menuService.insertUserGroupRight(paramBean);
	    }
	    
}
