package org.isotope.jfp.mcws.proxy;

/**
 * 代理服务地址
 * @author 001745
 *
 */
public interface ISProxy {

	/**
	 * 添加一个代理地址
	 * @param proxy 代理地址描述（127.0.0.1:8080@user/pwd）
	 * @return
	 */
	boolean addProxyInList(String proxy);

	/**
	 * 获得一个代理地址
	 * @return
	 */
	boolean getProxyOnList();
	
	/**
	 * 获得并删除一个代理地址
	 * @return
	 */
	boolean peekProxyOnList();
}
