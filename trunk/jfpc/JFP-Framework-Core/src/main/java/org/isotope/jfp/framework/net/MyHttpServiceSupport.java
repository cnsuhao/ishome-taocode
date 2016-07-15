package org.isotope.jfp.framework.net;

import java.lang.reflect.Method;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AUTH;
import org.apache.http.auth.MalformedChallengeException;
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
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.isotope.jfp.framework.beans.ObjectBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;

/**
 * API请求通信
 * 
 * @author Spook
 * @since 0.1.0
 * @version 2.4.1.2014/11/10
 * @version 0.1.0 2014/2/8
 * @see <MyHttpServiceSupport>
 * 
 */
public class MyHttpServiceSupport {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public MyHttpServiceSupport() {
		currentHeaders.put("Accept", "text/plain, */*; q=0.01");
		currentHeaders.put("Accept-Language", "Accept-Language:zh-CN,zh;q=0.8");
		currentHeaders.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 QQBrowser/9.3.6874.400");
		currentHeaders.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	}

	private int waitTimeMinute = 15;

	public int getWaitTimeMinute() {
		return waitTimeMinute;
	}

	public void setWaitTimeMinute(int waitTimeMinute) {
		this.waitTimeMinute = waitTimeMinute;
	}

	private HttpHost currentHttpHost;

	public HttpHost getCurrentHttpHost() {
		return currentHttpHost;
	}

	public void setCurrentHttpHost(HttpHost currentHttpHost) {
		this.currentHttpHost = currentHttpHost;
	}

	private Map<String, String> currentHeaders = new HashMap<String, String>();

	public Map<String, String> getCurrentHeaders() {
		return currentHeaders;
	}

	public void setCurrentHeaders(Map<String, String> currentHeaders) {
		this.currentHeaders = currentHeaders;
	}

	/**
	 * 请求过程中使用的cookies内容
	 */
	private BasicCookieStore cookieStore = new BasicCookieStore();

	public BasicCookieStore getCookieStore() {
		return cookieStore;
	}

	public void setCookieStore(BasicCookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}

	/**
	 * 获得一个代理
	 * 
	 * @return
	 * @throws MalformedChallengeException
	 */
	public HttpClientContext getHttpContext() throws MalformedChallengeException {
		HttpClientContext context = HttpClientContext.create();

		if (currentHttpHost != null) {
			BasicScheme proxyAuth = new BasicScheme();
			proxyAuth.processChallenge(new BasicHeader(AUTH.PROXY_AUTH, "BASIC realm=default"));
			BasicAuthCache authCache = new BasicAuthCache();
			authCache.put(currentHttpHost, proxyAuth);
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			// TODO
			// credsProvider.setCredentials(new AuthScope(currentHttpHost), new
			// UsernamePasswordCredentials(user, pwd));
			context.setAuthCache(authCache);
			context.setCredentialsProvider(credsProvider);
		}
		return context;
	}

	/**
	 * JOSN字符串形式发送参数名的常量定义
	 */
	public final String POST_PARAM = "jsonData";

	public static final String ENCODE_DEFAULT = "UTF-8";

	public boolean loadCookieByWebClient(String serviceURL) {
		BrowserVersion bv = BrowserVersion.CHROME;
		bv.setBrowserLanguage("zh");
		WebClient webClient = new WebClient(bv);
		webClient.getCookieManager().setCookiesEnabled(true);// 开启cookie管理
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setTimeout(30000);
		webClient.waitForBackgroundJavaScript(10000);
		webClient.getOptions().setThrowExceptionOnScriptError(false);

		try {
			webClient.getPage(serviceURL);
		} catch (Exception e) {
			return false;
		} finally {
			webClient.close();
		}

		// 得到cookie
		CookieManager CM = webClient.getCookieManager();
		cookieStore.addCookies((Cookie[]) com.gargoylesoftware.htmlunit.util.Cookie.toHttpClient(CM.getCookies()).toArray());

		return true;
	}

	/**
	 * 获取client对象
	 * 
	 * @return
	 * @throws Exception
	 */
	protected CloseableHttpClient getCloseableHttpClient(String serviceURL) throws Exception {
		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

		RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
		requestConfigBuilder.setSocketTimeout(waitTimeMinute * 1000);
		requestConfigBuilder.setConnectTimeout(waitTimeMinute * 1000);
		requestConfigBuilder.setConnectionRequestTimeout(waitTimeMinute * 1000);
		// TODO PoolingHttpClientConnectionManager
		// requestConfigBuilder.setStaleConnectionCheckEnabled(true);
		// 代理httpProxy
		if (getCurrentHttpHost() != null) {
			requestConfigBuilder.setProxy(currentHttpHost);
		}

		httpClientBuilder.setDefaultRequestConfig(requestConfigBuilder.build());
		if (serviceURL.indexOf("https") != -1) {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有证书
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

			httpClientBuilder.setSSLSocketFactory(sslsf);
		}
		return httpClientBuilder.setDefaultCookieStore(cookieStore).build();
	}

	public String doHttpGET(String serviceURL) throws Exception {
		return doHttpGET(serviceURL, this.currentHeaders);
	}

	public String doHttpGET(String serviceURL, Map<String, String> headers) throws Exception {
		// logger.debug("=====>>>>>接口请求<<<<<=====" + serviceURL);
		CloseableHttpClient httpclient = getCloseableHttpClient(serviceURL);
		try {
			HttpGet httpGet = new HttpGet(serviceURL);
			// 设定请求头
			if (headers != null) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpGet.setHeader(entry.getKey(), entry.getValue());
				}
			}
			// 设定传输编码
			CloseableHttpResponse response;
			// 创建上下文环境
			HttpContext context = getHttpContext();

			context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
			response = httpclient.execute(httpGet, context);

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
	public String doHttpPOST(String serviceURL, ObjectBean param) throws Exception {
		CloseableHttpClient httpclient = getCloseableHttpClient(serviceURL);
		try {

			HttpPost httpPost = new HttpPost(serviceURL);
			// 设定请求头
			if (currentHeaders != null) {
				for (Map.Entry<String, String> entry : currentHeaders.entrySet()) {
					httpPost.setHeader(entry.getKey(), entry.getValue());
				}
			}
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
							logger.error(e.getMessage());
						}
					}
				}
			}

			// 设定传输编码
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODE_DEFAULT));

			CloseableHttpResponse response;
			// 创建上下文环境
			HttpContext context = getHttpContext();

			context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
			response = httpclient.execute(httpPost, context);
			// 创建上下文环境
			context = new BasicHttpContext();

			context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
			response = httpclient.execute(httpPost, context);
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

	public static void main(String[] args) throws Exception {
		MyHttpServiceSupport mss = new MyHttpServiceSupport();
		MyHttpHost mhh = new MyHttpHost();
		mhh.setServiceURL("http://testcapture.wzyrz.cn/Zheng/proxy/newProtool");
		mss.setCurrentHttpHost(mhh.getHttpProxy());
		System.out.println(mss.doHttpGET("http://www.baidu.com"));
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
		// logger.debug("=====>>>>>接口请求<<<<<=====" + serviceURL);
		CloseableHttpClient httpclient = getCloseableHttpClient(serviceURL);
		try {
			HttpPost httpPost = new HttpPost(serviceURL);
			// 设定请求头
			if (currentHeaders != null) {
				for (Map.Entry<String, String> entry : currentHeaders.entrySet()) {
					httpPost.setHeader(entry.getKey(), entry.getValue());
				}
			}
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			// 参数翻转
			nvps.add(new BasicNameValuePair(POST_PARAM, jsonString));

			// 设定传输编码
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODE_DEFAULT));
			CloseableHttpResponse response;
			// 创建上下文环境
			HttpContext context = getHttpContext();

			context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
			response = httpclient.execute(httpPost, context);
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
		return doHttpPOST(serviceURL, param, currentHeaders);
	}

	public String doHttpPOST(String serviceURL, Map<String, String> param) throws Exception {
		return doHttpPOST(serviceURL, param, currentHeaders);
	}

	public String doHttpPOST(String serviceURL, Map<String, String> param, Map<String, String> headers) throws Exception {
		// logger.debug("=====>>>>>接口请求<<<<<=====" + serviceURL);
		CloseableHttpClient httpclient = getCloseableHttpClient(serviceURL);
		try {
			HttpPost httpPost = new HttpPost(serviceURL);
			// 设定请求头
			if (headers != null) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpPost.setHeader(entry.getKey(), entry.getValue());
				}
			}
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			// 额外参数
			if (param != null) {
				for (Map.Entry<String, String> entry : param.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}

			// 设定传输编码
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODE_DEFAULT));

			CloseableHttpResponse response;
			// 创建上下文环境
			HttpContext context = getHttpContext();

			context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
			response = httpclient.execute(httpPost, context);

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
