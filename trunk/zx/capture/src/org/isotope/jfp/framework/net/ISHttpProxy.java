package org.isotope.jfp.framework.net;

import org.isotope.jfp.framework.beans.net.HttpProxyBean;

public interface ISHttpProxy {
	public HttpProxyBean loadHttpProxy();

	public boolean removeHttpProxy(String httpProxy);
}
