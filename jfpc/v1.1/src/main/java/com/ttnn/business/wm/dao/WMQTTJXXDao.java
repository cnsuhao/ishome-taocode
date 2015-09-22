package com.ttnn.business.wm.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.ISSQLDaoSupport;
/** 添加下线*/
@Repository
public interface WMQTTJXXDao extends ISSQLDaoSupport{
	
	
	/**
	 * 查询用户类型
	 * @param paramPageVO
	 * @return
	 */
	public List<FrameworkDataBean> doFind(FrameworkDataBean dbParamBean);
	/**
	 * 分配支付通道
	 * @param paramPageVO
	 * @return
	 */
	public List<FrameworkDataBean> doFindMyzfTD(FrameworkDataBean dbParamBean);
	/**
	 * 分配支付通道
	 * @param paramPageVO
	 * @return
	 */
	public List<FrameworkDataBean> doFind2(FrameworkDataBean dbParamBean);
	
	
	/**
	 * 查询未分配的支付通道
	 * @param paramPageVO
	 * @return
	 */
	public List<FrameworkDataBean> doSelectUnDis(FrameworkDataBean dbParamBean);
	

}
