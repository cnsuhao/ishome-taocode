package com.ttnn.business.wm.dao;

import org.springframework.stereotype.Repository;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.ISSQLDaoSupport;
@Repository
public interface WMQTCZTXDao extends ISSQLDaoSupport{
	
	/**
	 * 根据提现更新充值信息
	 * @param paramPageVO
	 * @return
	 */	
	public FrameworkDataBean doRead(FrameworkDataBean dbParamBean);
	
	
	
	/**
	 * 根据提现更新充值信息
	 * @param paramPageVO
	 * @return
	 */	
	public int doUpdateCZWithTX(FrameworkDataBean dbParamBean);
	

	
	/**
	 * 根据佣金结算更新佣金信息
	 * @param paramPageVO
	 * @return
	 */	
	public int doUpdateYJWithJS(FrameworkDataBean dbParamBean);
}
