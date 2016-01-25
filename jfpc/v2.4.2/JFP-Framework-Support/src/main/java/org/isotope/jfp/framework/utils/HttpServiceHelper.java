package org.isotope.jfp.framework.utils;

import java.lang.reflect.Method;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie2;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.isotope.jfp.framework.beans.ObjectBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * API请求通信
 * User-Agent: Apache-HttpClient/4.5 (Java/1.7.0_51)
 * @author Spook
 * @since 0.1.0
 * @version 2.4.1.2014/11/10
 * @version 0.1.0 2014/2/8
 * 
 */
public class HttpServiceHelper {
	private static Logger logger = LoggerFactory.getLogger(HttpServiceHelper.class);
	public static int waitTimeMinute = 15;

	public static int getWaitTimeMinute() {
		return waitTimeMinute;
	}

	public static void setWaitTimeMinute(int waitTimeMinute) {
		HttpServiceHelper.waitTimeMinute = waitTimeMinute;
	}

	/**
	 * 获取client对象
	 * 
	 * @return
	 */
	protected CloseableHttpClient getClient(String url) {
		if (url.indexOf("https") != -1) {
			try {
				SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
					// 信任所有证书
					public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
						return true;
					}
				}).build();
				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
				CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
				return httpclient;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return HttpClients.createDefault();
	}

	/**
	 * JOSN字符串形式发送参数名的常量定义
	 */
	public static final String POST_PARAM = "jsonData";

	public static final String ENCODE_DEFAULT = "UTF-8";

	public static String doHttpGET(String serviceURL) throws Exception {
		return doHttpGET(serviceURL, null, null);
	}

	public static String doHttpGET(String serviceURL, Map<String, String> headers) throws Exception {
		return doHttpGET(serviceURL, headers, null);
	}

	public static String doHttpGET(String serviceURL, Map<String, String> headers, Map<String, String> cookies)
			throws Exception {
		logger.debug("=====>>>>>接口请求<<<<<=====" + serviceURL);
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(waitTimeMinute * 1000)
				.setConnectTimeout(waitTimeMinute * 1000).setConnectionRequestTimeout(waitTimeMinute * 1000)
				.setStaleConnectionCheckEnabled(true).build();
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
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

			// 设置cookie
			if (cookies != null && cookies.size() > 0) {
				// 创建上下文环境
				HttpContext context = new BasicHttpContext();
				CookieStore cookieStore = new BasicCookieStore();
				for (Map.Entry<String, String> entry : cookies.entrySet()) {
					cookieStore.addCookie(new BasicClientCookie2(entry.getKey(), entry.getValue()));
				}
				context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
				response = httpclient.execute(httpGet, context);
			} else {
				response = httpclient.execute(httpGet);
			}

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
	 * 以简单属性参数请求提交服务
	 * 
	 * @param serviceURL
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static String doHttpPOST(String serviceURL, ObjectBean param) throws Exception {
		// CloseableHttpClient httpclient = HttpClients.createDefault();

		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(waitTimeMinute * 1000)
				.setConnectTimeout(waitTimeMinute * 1000).setConnectionRequestTimeout(waitTimeMinute * 1000)
				.setStaleConnectionCheckEnabled(true).build();
		// TODO
		// PoolingHttpClientConnectionManager cm = new
		// PoolingHttpClientConnectionManager();
		// // Increase max total connection to 200
		// cm.setMaxTotal(200);
		// // Increase default max connection per route to 20
		// cm.setDefaultMaxPerRoute(20);
		// // Increase max connections for localhost:80 to 50
		// HttpHost localhost = new HttpHost("locahost", 80);
		// cm.setMaxPerRoute(new HttpRoute(localhost), 50);

		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

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
	 * @param serviceURL
	 * @param jsonString
	 * @return
	 * @throws Exception
	 */
	public static String doHttpPOST(String serviceURL, String jsonString) throws Exception {
		logger.debug("=====>>>>>接口请求<<<<<=====" + serviceURL);
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(waitTimeMinute * 1000)
				.setConnectTimeout(waitTimeMinute * 1000).setConnectionRequestTimeout(waitTimeMinute * 1000)
				.setStaleConnectionCheckEnabled(true).build();
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

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
	public static String doHttpPOST(String serviceURL, String jsonString, Map<String, String> param) throws Exception {
		param.put(POST_PARAM, jsonString);
		return doHttpPOST(serviceURL, param, null, null);
	}

	public static String doHttpPOST(String serviceURL, Map<String, String> param) throws Exception {
		return doHttpPOST(serviceURL, param, null, null);
	}

	public static String doHttpPOST(String serviceURL, Map<String, String> param, Map<String, String> headers)
			throws Exception {
		return doHttpPOST(serviceURL, param, null, null);
	}

	public static String doHttpPOST(String serviceURL, Map<String, String> param, Map<String, String> headers,
			Map<String, String> cookies) throws Exception {
		logger.debug("=====>>>>>接口请求<<<<<=====" + serviceURL);
		// CloseableHttpClient httpclient = HttpClients.createDefault();
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(waitTimeMinute * 1000)
				.setConnectTimeout(waitTimeMinute * 1000).setConnectionRequestTimeout(waitTimeMinute * 1000)
				.setStaleConnectionCheckEnabled(true).build();
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

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

			// 设置cookie
			if (cookies != null && cookies.size() > 0) {
				// 创建上下文环境
				HttpContext context = new BasicHttpContext();
				CookieStore cookieStore = new BasicCookieStore();
				for (Map.Entry<String, String> entry : param.entrySet()) {
					cookieStore.addCookie(new BasicClientCookie2(entry.getKey(), entry.getValue()));
				}
				context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
				response = httpclient.execute(httpPost, context);
			} else {
				response = httpclient.execute(httpPost);
			}
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

}
