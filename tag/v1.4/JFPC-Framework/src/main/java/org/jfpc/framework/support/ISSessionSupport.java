package org.jfpc.framework.support;

import org.jfpc.framework.bean.LoginerBean;

/**
 * 模拟session业务的全部动作接口
 * @author Administrator
 *
 */
public interface ISSessionSupport {
	/**
	 * 设定Token
	 * @return
	 */
	public String getToken() ;
	
	/**
	 * Session ID获得
	 * 
	 * @return
	 */
	public String getSessionid() ;

	/**
	 * Session ID持久化
	 * 
	 * @param sessionid
	 */
	public void setSessionid(String sessionid) ;
	
	/**
	 * 获得登录者信息
	 * 
	 * @return
	 */
	public LoginerBean getLoginerBean() ;

	/**
	 * 缓存登录者信息
	 */
	public void setLoginerBean(LoginerBean loginer) ;
	/**
	 * 获得登录者信息
	 * @return
	 */
	public String getLoginerId() ;

	/**
	 * 获得企业ID
	 * @return
	 */
	public String getCompanyId();
}
