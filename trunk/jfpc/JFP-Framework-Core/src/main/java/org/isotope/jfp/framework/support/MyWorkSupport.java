package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.beans.net.HttpProxyBean;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.net.ISHttpProxy;
import org.isotope.jfp.framework.net.MyHttpServiceSupport;

/**
 * 异步线程超类
 * 
 * @author Spook
 * @version 3.1.2.20160505
 * @since 3.1.2
 */
public class MyWorkSupport implements ISFrameworkConstants {

	/**
	 * 缓存队列
	 */
	protected ICacheService myCacheService;

	public ICacheService getMyCacheService() {
		return myCacheService;
	}

	public void setMyCacheService(ICacheService myCacheService) {
		this.myCacheService = myCacheService;
	}

	/**
	 * 网络通信
	 */
	protected MyHttpServiceSupport myHttpService = new MyHttpServiceSupport();

	public MyHttpServiceSupport getHttpService() {
		return myHttpService;
	}

	public void setHttpService(MyHttpServiceSupport myHttpService) {
		this.myHttpService = myHttpService;
	}

	public void setHttpProxy(ISHttpProxy httpProxy) {
		myHttpService.setHttpProxy(httpProxy);
	}

	public ISHttpProxy getHttpProxy() {
		return myHttpService.getHttpProxy();
	}

	public boolean removeHttpProxy(HttpProxyBean httpProxy) throws Exception {
		if (myHttpService != null)
			return myHttpService.removeHttpProxy(httpProxy);
		return false;
	}

	/**
	 * 进程阻塞时间（分钟）
	 */
	protected int waitTimeMinute = 10;

	public int getWaitTimeMinute() {
		return waitTimeMinute;
	}

	public void setWaitTimeMinute(int waitTimeMinute) {
		this.waitTimeMinute = waitTimeMinute;
		this.waitTimeSecond = 60 * this.waitTimeMinute;
	}

	/**
	 * 进程阻塞时间（秒）
	 */
	protected int waitTimeSecond = 60 * waitTimeMinute;

	public int getWaitTimeSecond() {
		return waitTimeSecond;
	}

	public void setWaitTimeSecond(int waitTimeSecond) {
		this.waitTimeSecond = waitTimeSecond;
	}

}
