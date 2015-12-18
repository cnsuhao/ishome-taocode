package org.isotope.jfp.framework.net;

import org.isotope.jfp.framework.beans.net.HttpProxyBean;

/**
 * 网络代理
 * @author fucy
 * @version 2.4.2 2015/12/17
 * @since 2.4.2 2015/12/17
 *
 */
public interface ISHttpProxy {
	/**
	 * 获得一个代理
	 * @return
	 */
	public HttpProxyBean loadHttpProxy() ;
}
