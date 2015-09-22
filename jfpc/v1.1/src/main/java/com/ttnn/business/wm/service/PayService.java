package com.ttnn.business.wm.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;

public interface PayService {
    
	/**
	 * 准备支付
	 */
	Map<String,Object>   readToPay(FrameworkDataBean paramBean,FrameworkDataBean xttdFrameworkDataBean);
	
	
	
	
	/**
	 * 解析回调参数  
	 * 
	 */
	CSPVOSupport   callBack(HttpServletRequest request) throws Exception;
	 
	
	
	/**
	 * 准备支付返回的视图
	 */
	String getView();
	
	
	/**
	 * 核对订单
	 *
	 */
    Map<String,String>  checkOrder(Map<String,String> pMap) throws Exception;



	
    
    

}
