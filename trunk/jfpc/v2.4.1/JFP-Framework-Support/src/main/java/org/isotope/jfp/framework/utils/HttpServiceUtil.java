package org.isotope.jfp.framework.utils;

import java.lang.reflect.Method;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AUTH;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.isotope.jfp.framework.beans.ObjectBean;
import org.isotope.jfp.framework.beans.net.HttpProxyBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * API请求通信
 * 
 * @author Spook
 * @since 0.1.0
 * @version 2.4.1.2014/11/10
 * @version 0.1.0 2014/2/8
 * 
 */
@Service("HttpService")
public class HttpServiceUtil {
	private Logger logger = LoggerFactory.getLogger(HttpServiceUtil.class);
	public int waitTimeMinute = 15;

	public int getWaitTimeMinute() {
		return waitTimeMinute;
	}

	public void setWaitTimeMinute(int waitTimeMinute) {
		this.waitTimeMinute = waitTimeMinute;
	}
	
	private List<HttpProxyBean> proxys;

	public List<HttpProxyBean> getProxys() {
		return proxys;
	}

	public void setProxys(List<HttpProxyBean> proxys) {
		this.proxys = proxys;
	}

	/**
	 * 请求过程中使用的cookies内容
	 */
	List<Cookie> curCookies = new ArrayList<Cookie>();

	public List<Cookie> getCookies() {
		return curCookies;
	}

	public void setCookies(List<Cookie> cookies) {
		this.curCookies = cookies;
	}

	/**
	 * JOSN字符串形式发送参数名的常量定义
	 */
	public final String POST_PARAM = "jsonData";

	public static final String ENCODE_DEFAULT = "UTF-8";

	public String doHttpGET(String serviceURL) throws Exception {
		return doHttpGET(serviceURL, null);
	}

	public String doHttpGET(String serviceURL, Map<String, String> headers) throws Exception {
		logger.debug("=====>>>>>接口请求<<<<<=====" + serviceURL);
		CloseableHttpClient httpclient = getCloseableHttpClient(serviceURL);
		try {
			HttpGet httpGet = new HttpGet(serviceURL);
			// 设定传输编码
			// 设定请求头
			if (headers != null) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpGet.setHeader(entry.getKey(), entry.getValue());
				}
			}
			// 设定传输编码
			CloseableHttpResponse response;
			// 创建上下文环境
			HttpContext context = new BasicHttpContext();
			CookieStore cookieStore = new BasicCookieStore();
			// 设置cookie
			if (curCookies != null && curCookies.size() > 0) {
				for (Cookie cookie : curCookies) {
					cookieStore.addCookie(cookie);
				}
			}

			context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
			response = httpclient.execute(httpGet, context);

			int status = response.getStatusLine().getStatusCode();

			List<Cookie> cookies_rs = cookieStore.getCookies();
			curCookies = new ArrayList<Cookie>();
			for (Cookie cookie : cookies_rs) {
				curCookies.add(cookie);
			}
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					return EntityUtils.toString(entity, ENCODE_DEFAULT);
			} else {
				throw new Exception("服务请求异常: " + status + ",【URL=" + serviceURL + "】");
			}
		} finally {
			httpclient.close();
		}
		return "";
	}

	/**
	 * 以简单属性参数请求提交服务
	 * 
	 * @param serviceURL
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String doHttpPOST(String serviceURL, ObjectBean param) throws Exception {
		CloseableHttpClient httpclient = getCloseableHttpClient(serviceURL);
		try {

			HttpPost httpPost = new HttpPost(serviceURL);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			// 参数翻转
			{
				// 获得所有属性名称
				for (Method m : param.getClass().getMethods()) {
					String methodName = m.getName();
					if (methodName.startsWith("get")) {// &&
														// methodName.indexOf("_")
														// > 0
						try {
							Object value = m.invoke(param);
							String name = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);

							if (value == null)
								nvps.add(new BasicNameValuePair(name, ""));
							else
								nvps.add(new BasicNameValuePair(name, "" + value));
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}
					}
				}
			}

			// 设定传输编码
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODE_DEFAULT));

			CloseableHttpResponse response = httpclient.execute(httpPost);
			// 创建上下文环境
			HttpContext context = new BasicHttpContext();
			CookieStore cookieStore = new BasicCookieStore();
			// 设置cookie
			if (curCookies != null && curCookies.size() > 0) {
				for (Cookie cookie : curCookies) {
					cookieStore.addCookie(cookie);
				}
			}

			context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
			response = httpclient.execute(httpPost, context);
			int status = response.getStatusLine().getStatusCode();
			List<Cookie> cookies_rs = cookieStore.getCookies();
			curCookies = new ArrayList<Cookie>();
			for (Cookie cookie : cookies_rs) {
				curCookies.add(cookie);
			}
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					return EntityUtils.toString(entity, ENCODE_DEFAULT);
			} else {
				throw new Exception("服务请求异常: " + status + ",【URL=" + serviceURL + "】");
			}
		} finally {
			httpclient.close();
		}
		return "";
	}

	public static void main(String[] args) throws Exception {

		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

		HttpHost proxy = new HttpHost("27.221.31.66", 8080, "http");
		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
		HttpGet httpPost = new HttpGet("http://news.163.com");
		httpPost.setConfig(config);

		HttpClientContext context = HttpClientContext.create();
		BasicScheme proxyAuth = new BasicScheme();
		proxyAuth.processChallenge(new BasicHeader(AUTH.PROXY_AUTH, "BASIC realm=default"));
		BasicAuthCache authCache = new BasicAuthCache();
		authCache.put(proxy, proxyAuth);
		
//		CredentialsProvider credsProvider = new BasicCredentialsProvider();
//		credsProvider.setCredentials(new AuthScope(proxy), new UsernamePasswordCredentials("fcy", "fcy"));
//		context.setAuthCache(authCache);
//		context.setCredentialsProvider(credsProvider);

		CloseableHttpResponse response = closeableHttpClient.execute(httpPost, context);
		int status = response.getStatusLine().getStatusCode();
		if (status >= 200 && status < 300) {
			HttpEntity entity = response.getEntity();
			if (entity != null)
				System.out.println(EntityUtils.toString(entity, ENCODE_DEFAULT));
		} else {
			throw new Exception("服务请求异常: " + status);
		}

	}

	/**
	 * 获取client对象
	 * 
	 * @return
	 */
	protected CloseableHttpClient getCloseableHttpClient(String serviceURL) {

		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(waitTimeMinute * 1000)
				.setConnectTimeout(waitTimeMinute * 1000).setConnectionRequestTimeout(waitTimeMinute * 1000)
				.setStaleConnectionCheckEnabled(true).build();
		if (serviceURL.indexOf("https") != -1) {
			try {
				SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
					// 信任所有证书
					public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
						return true;
					}
				}).build();
				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
				CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig)
						.setSSLSocketFactory(sslsf).build();
				return httpclient;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
	}

	/**
	 * 将全部参数以JSON字符串形式发送，接口如直接接受
	 * 
	 * @see #POST_PARAM
	 * @param serviceURL
	 * @param jsonString
	 * @return
	 * @throws Exception
	 */
	public String doHttpPOST(String serviceURL, String jsonString) throws Exception {
		logger.debug("=====>>>>>接口请求<<<<<=====" + serviceURL);
		CloseableHttpClient httpclient = getCloseableHttpClient(serviceURL);
		try {
			HttpPost httpPost = new HttpPost(serviceURL);

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			// 参数翻转
			nvps.add(new BasicNameValuePair(POST_PARAM, jsonString));

			// 设定传输编码
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODE_DEFAULT));

			CloseableHttpResponse response = httpclient.execute(httpPost);
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					return EntityUtils.toString(entity, ENCODE_DEFAULT);
			} else {
				throw new Exception("服务请求异常: " + status + ",【URL=" + serviceURL + "】");
			}
		} finally {
			httpclient.close();
		}
		return "";
	}

	/**
	 * 将全部参数以JSON字符串形式发送，接口如直接接受
	 * 
	 * @see #POST_PARAM
	 * @param serviceid
	 * @param jsonString
	 * @param param
	 *            额外扩展参数定义
	 * @return
	 * @throws Exception
	 */
	public String doHttpPOST(String serviceURL, String jsonString, Map<String, String> param) throws Exception {
		param.put(POST_PARAM, jsonString);
		return doHttpPOST(serviceURL, param, null);
	}

	public String doHttpPOST(String serviceURL, Map<String, String> param) throws Exception {
		return doHttpPOST(serviceURL, param, null);
	}

	public String doHttpPOST(String serviceURL, Map<String, String> param, Map<String, String> headers) throws Exception {
		logger.debug("=====>>>>>接口请求<<<<<=====" + serviceURL);
		CloseableHttpClient httpclient = getCloseableHttpClient(serviceURL);
		try {
			HttpPost httpPost = new HttpPost(serviceURL);

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			// 额外参数
			if (param != null) {
				for (Map.Entry<String, String> entry : param.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}

			// 设定传输编码
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODE_DEFAULT));

			// 设定请求头
			if (headers != null) {
				for (Map.Entry<String, String> entry : param.entrySet()) {
					httpPost.setHeader(entry.getKey(), entry.getValue());
				}
			}

			CloseableHttpResponse response;
			// 创建上下文环境
			HttpContext context = new BasicHttpContext();
			CookieStore cookieStore = new BasicCookieStore();
			// 设置cookie
			if (curCookies != null && curCookies.size() > 0) {
				for (Cookie cookie : curCookies) {
					cookieStore.addCookie(cookie);
				}
			}

			context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
			response = httpclient.execute(httpPost, context);

			int status = response.getStatusLine().getStatusCode();
			List<Cookie> cookies_rs = cookieStore.getCookies();
			curCookies = new ArrayList<Cookie>();
			for (Cookie cookie : cookies_rs) {
				curCookies.add(cookie);
			}
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					return EntityUtils.toString(entity, ENCODE_DEFAULT);
			} else {
				throw new Exception("服务请求异常: " + status + ",【URL=" + serviceURL + "】");
			}
		} finally {
			httpclient.close();
		}
		return "";
	}

}
