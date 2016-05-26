package org.isotope.jfp.framework.beans.net;

import org.apache.http.HttpHost;
import org.apache.http.auth.AUTH;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.isotope.jfp.framework.beans.ObjectBean;
import org.isotope.jfp.framework.utils.EmptyHelper;

/**
 * HTTP代理
 * 
 * @author Spook
 * @version 2.4.2
 * @since 2.4.2 2015/12/17
 *
 */

public class HttpProxyBean extends ObjectBean {
	/**
	 * 识别ID
	 */
	protected String id;
	/**
	 * 主机地址
	 */
	protected String host;
	/**
	 * 端口
	 */
	protected int port;
	/**
	 * 类别（http/https）
	 */
	protected String type;
	/**
	 * 权限使用(1不使用0使用)
	 */
	protected String auth;
	/**
	 * 用户名
	 */
	protected String user;
	/**
	 * 密码
	 */
	protected String pwd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * 获得一个代理
	 * 
	 * @return
	 */
	public HttpHost getHttpProxy() {
		HttpHost proxy = new HttpHost(host, port, type);
		return proxy;
	}

	/**
	 * 获得一个代理
	 * 
	 * @return
	 * @throws MalformedChallengeException
	 */
	public HttpClientContext getHttpContext() throws MalformedChallengeException {
		HttpClientContext context = HttpClientContext.create();

		if ("0".equals(auth) && EmptyHelper.isNotEmpty(user) && EmptyHelper.isNotEmpty(pwd)) {
			HttpHost proxy = new HttpHost(host, port, type);
			BasicScheme proxyAuth = new BasicScheme();
			proxyAuth.processChallenge(new BasicHeader(AUTH.PROXY_AUTH, "BASIC realm=default"));
			BasicAuthCache authCache = new BasicAuthCache();
			authCache.put(proxy, proxyAuth);
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(new AuthScope(proxy), new UsernamePasswordCredentials(user, pwd));
			context.setAuthCache(authCache);
			context.setCredentialsProvider(credsProvider);
		}
		return context;
	}
}
