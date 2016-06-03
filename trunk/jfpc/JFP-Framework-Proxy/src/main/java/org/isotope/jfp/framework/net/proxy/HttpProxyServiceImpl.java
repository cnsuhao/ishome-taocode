package org.isotope.jfp.framework.net.proxy;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.net.HttpProxyBean;
import org.isotope.jfp.framework.biz.common.ISInit;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.net.IHttpProxy;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

/**
 * 网络代理管理
 * 
 * @author fucy
 * @version 3.1.2.2016/04/25
 * @since 3.1.2.2016/04/25
 */
@Service
public class HttpProxyServiceImpl implements IHttpProxy, ISFrameworkConstants, ISInit {

	// 缓存队列
	@Resource
	protected ICacheService cache;

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param file
	 *            文件内容
	 * @throws Exception
	 */
	public void loadHttpProxys(String file) throws Exception {
		// 一次读入一行，直到读入null为文件结束
		if (EmptyHelper.isNotEmpty(file))
			for (String proxy : file.split("\r\n")) {
				if (EmptyHelper.isEmpty(proxy)) {
					continue;
				}
				addHttpProxy(proxy);
			}
	}

	/**
	 * 
	 * @param proxyList
	 * @throws Exception
	 */
	public void loadHttpProxys(ArrayList<String> proxyList) throws Exception {
		// 一次读入一行，直到读入null为文件结束
		if (proxyList != null && proxyList.size() > 0)
			for (String proxy : proxyList) {
				if (EmptyHelper.isEmpty(proxy)) {
					continue;
				}
				addHttpProxy(proxy);
			}
	}

	public void addHttpProxy(String proxy) throws Exception {
		String[] h;
		// 14.199.105.36:80@HTTP#【匿】香港 城市电讯有限公司
		HttpProxyBean bean = new HttpProxyBean();
		// 1.数据解析
		{
			// 地址14.199.105.36
			h = proxy.split(":");
			bean.setHost(h[0]);

			// 端口80
			h = h[1].split("@");
			bean.setPort(Integer.parseInt(h[0]));

			// 服务类型（Http,Https，Socket）
			h = h[1].split("#");
			bean.setType(h[0]);

			// 属性设定
			if (h[1].indexOf("【匿】") > -1) {
				bean.setAuth(ZERO);
				cache.offerObjectInList(HTTP_PROXY_GOOD, JSON.toJSONString(bean));
			} else {
				bean.setAuth(ONE);
				cache.offerObjectInList(HTTP_PROXY_LIST, JSON.toJSONString(bean));
			}
		}
	}

	public void addHttpProxy(HttpProxyBean proxy) throws Exception {
		// 属性设定
		if (ZERO.equals(proxy.getType())) {
			cache.offerObjectInList(HTTP_PROXY_GOOD, JSON.toJSONString(proxy));
		} else {
			cache.offerObjectInList(HTTP_PROXY_LIST, JSON.toJSONString(proxy));
		}

	}

	/**
	 * 获得一个代理
	 */
	public HttpProxyBean getHttpProxy() throws Exception {
		// 获得 一个代理地址
		String proxy = (String) cache.pollFirstObjectInList(HTTP_PROXY_GOOD, false);
		if (EmptyHelper.isEmpty(proxy)) {
			proxy = (String) cache.pollFirstObjectInList(HTTP_PROXY_LIST, false);
			if (EmptyHelper.isEmpty(proxy)) {
				return null;
			} else {
				return getOneHttpProxy(proxy, HTTP_PROXY_LIST);
			}
		} else {
			return getOneHttpProxy(proxy, HTTP_PROXY_GOOD);
		}
	}

	private HttpProxyBean getOneHttpProxy(String proxy, String key) {
		HttpProxyBean hpb = JSON.parseObject(proxy, HttpProxyBean.class);
		if (badProxy.contains(hpb.getHost()) == false)
			cache.offerObjectInList(key, proxy, false);
		return hpb;
	}

	/**
	 * 标记一个代理地址为低频
	 */
	@Override
	public boolean removeHttpProxy(HttpProxyBean httpProxy) throws Exception {
		badProxy.add(httpProxy.getHost());
		return cache.offerObjectInList(HTTP_PROXY_BAD, httpProxy.getHost(), false);
	}

	///////////////////////////////////////////
	/**
	 * 保存代理到个人队列
	 * 
	 * @param key
	 *            区域标识
	 * @param httpProxy
	 * @return
	 */
	public boolean saveMyHttpProxy(String key, HttpProxyBean httpProxy) {
		return cache.offerObjectInList(HTTP_PROXY + key, JSON.toJSONString(httpProxy), false);
	}

	/**
	 * 获得一个代理
	 */
	public HttpProxyBean getMyHttpProxy(String key) {
		// 获得 一个代理地址
		String proxy = (String) cache.peekFirstObjectInList(HTTP_PROXY + key, false);
		if (EmptyHelper.isEmpty(proxy)) {
			return null;
		}
		HttpProxyBean hpb = JSON.parseObject(proxy, HttpProxyBean.class);
		cache.offerObjectInList(HTTP_PROXY + key, proxy, false);
		return hpb;
	}

	protected ArrayList<String> badProxy = new ArrayList<String>();

	@Override
	public boolean doInit() throws Exception {
		badProxy.clear();
		return false;
	}

}
