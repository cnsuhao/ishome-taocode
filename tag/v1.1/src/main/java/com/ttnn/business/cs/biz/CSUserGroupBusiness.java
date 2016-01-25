package com.ttnn.business.cs.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ttnn.business.cs.service.CSSPR1Service;
import com.ttnn.business.cs.service.CSSR01Service;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/**
 * 用户关联到用户组
 * @author Administrator
 *
 */
@Service
public class CSUserGroupBusiness  extends MyServiceSupportImpl{

   @Resource	
   protected CSSR01Service CSSR01Service_;
   
   @Resource 
   protected CSSPR1Service CSSPR1Service_;
   
   
   
 //用户添加到用户组
   public int saveUserToGroup(CSPVOSupport bean,String[] groups){
   	try{
   		//插入允许用户登录用户表
   		CSSR01Service_.doInsert(bean);
       	//插入中间表
   		for(int i=0;groups!=null &&i<groups.length;i++){
   			CSPVOSupport pr1bean =  new CSPVOSupport();
   			pr1bean.setPuk(bean.getPuk());
   			pr1bean.setK01(groups[i]);
   			pr1bean.setEb5(super.getProductId());
   			CSSPR1Service_.doInsert(pr1bean);  
   		}
       	return 1;
   	}catch(Exception e){
   		e.printStackTrace();
   		return -1;	
   	}
   	 	
   }
   /**
    * 用户修改用户组
    * @return
    */
   public void updateUserToGroup(CSPVOSupport bean,String[] groups){
	   CSSR01Service_.doUpdate(bean); //修改用户数据
	   CSSPR1Service_.doDelete(bean); //删除用户所有组
	   	//插入中间表
  		for(int i=0;groups!=null &&i<groups.length;i++){
  			CSPVOSupport pr1bean =  new CSPVOSupport();
  			pr1bean.setPuk(bean.getPuk());
  			pr1bean.setK01(groups[i]);
  			pr1bean.setEb5(super.getProductId());
  			CSSPR1Service_.doInsert(pr1bean);  
  		}
   }
   
   
}
