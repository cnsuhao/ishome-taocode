package org.isotope.jfp.framework.net;

import org.isotope.jfp.framework.beans.net.HttpProxyBean;

/**
 * 网络代理
 * @author fucy
 * @version 3.1.2 2016/04/25
 * @version 2.4.2 2015/12/17
 * @since 2.4.2 2015/12/17
 *
 */
public interface ISHttpProxy {
	
	public static String HTTP_PROXY = "HTTP_PROXY:";
	public static String HTTP_PROXY_BAD = "HTTP_PROXY:BAD";
	public static String HTTP_PROXY_GOOD = "HTTP_PROXY:GOOD";
	public static String HTTP_PROXY_LIST = "HTTP_PROXY:LIST";
	
	/**
	 * 加载代理到代理队列<br>
	 * 代理地址描述（127.0.0.1:8080@user/pwd）
	 */
	public void addHttpProxy(String proxy) throws Exception;
	
	/**
	 * 获得一个代理
	 * @return
	 */
	public HttpProxyBean getHttpProxy() throws Exception;
	
	/**
	 * 删除一个代理
	 * @param httpProxy
	 * @return
	 */
	public boolean removeHttpProxy(HttpProxyBean httpProxy) throws Exception;
}
