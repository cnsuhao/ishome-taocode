package com.ttnn.business.wm.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class PayServiceFactory {   
	
	@Resource  BaoFuService baoFuService_;//宝付通道
	@Resource  RongBaoService rongBaoService_; //融宝通道
	@Resource  ZhiFuService zhiFuService_; //智付通道
	@Resource  RongBaoLinkService rongBaoLinkService_; //融宝夜间
	
	/**
	 * @param args
	 */
	public  PayService getPayService(String key) throws IllegalArgumentException{
		if(key.equals("bftd")){
			 // 宝付通道
		    // PayService  baoFuService_ = new BaoFuService();
		     
		       return baoFuService_;
		}else if(key.equals("rbtd")){
			// 融宝通道
			// PayService rongBaoService_ = new RongBaoService();
			 return rongBaoService_;
		}else if(key.equals("zftd")){
			// 智付通道
			// PayService zhiFuService_ = new ZhiFuService();
			 return zhiFuService_;
			 
		}else if(key.equals("rbyjtd")){
			 return  rongBaoLinkService_;
		}else{ 
			throw new IllegalArgumentException("针对该通道的接口没有实现");
		}
		
		
	}

}
