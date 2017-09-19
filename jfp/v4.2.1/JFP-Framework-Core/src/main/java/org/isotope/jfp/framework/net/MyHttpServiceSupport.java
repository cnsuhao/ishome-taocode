package org.isotope.jfp.framework.net;

import java.io.IOException;
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
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.isotope.jfp.framework.utils.EmptyHelper;
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
 * @see <HttpServiceHelper><ISHttpHost>
 * 
 */
public class MyHttpServiceSupport {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public MyHttpServiceSupport() {
		this("", "");
	}

	public MyHttpServiceSupport(String host) {
		this(host, "");
	}

	public MyHttpServiceSupport(String host, String encode) {
		currentHeaders.put("Accept-Encoding", "gzip, deflate, sdch");
		currentHeaders.put("Upgrade-Insecure-Requests", "1");
		currentHeaders.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		currentHeaders.put("Accept-Language", "Accept-Language:zh-CN,zh;q=0.8");
		currentHeaders.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.2372.400 QQBrowser/9.5.10548.400");
		// currentHeaders.put("Content-Type",
		// "application/x-www-form-urlencoded; charset=UTF-8");
		if (EmptyHelper.isNotEmpty(host))
			currentHeaders.put("Host", host);
		if (EmptyHelper.isNotEmpty(encode))
			currentEncode = encode;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	private int waitTimeMinute = 15;

	public int getWaitTimeMinute() {
		return waitTimeMinute;
	}

	public void setWaitTimeMinute(int waitTimeMinute) {
		this.waitTimeMinute = waitTimeMinute;
	}

	////////////////////////////////////////////////////////////////////////////
	public ISHttpHost myHttpHost;

	public void setMyHttpHost(ISHttpHost myHttpHost) {
		this.myHttpHost = myHttpHost;
	}

	private HttpHost currentHttpHost;

	public HttpHost getCurrentHttpHost() throws Exception {
		if(myHttpHost!=null)
			currentHttpHost = myHttpHost.loadHttpProxy();
		if (checkProxy() == false)
			currentHttpHost = null;

		return currentHttpHost;
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public boolean checkProxy() throws Exception {
		try {
			if (currentHttpHost == null)
				return false;
			doHttpGET("http://www.baidu.com");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void setCurrentHttpHost(HttpHost currentHttpHost) {
		this.currentHttpHost = currentHttpHost;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	// public static final String currentEncode = "UTF-8";
	public String currentEncode = "UTF-8";

	public void setCurrentEncode(String currentEncode) {
		this.currentEncode = currentEncode;
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
	 * @throws Exception
	 */
	public HttpClientContext getHttpContext() throws Exception {
		HttpClientContext context = HttpClientContext.create();

		if (getCurrentHttpHost() != null) {
			BasicScheme proxyAuth = new BasicScheme();
			proxyAuth.processChallenge(new BasicHeader(AUTH.PROXY_AUTH, "BASIC realm=default"));
			BasicAuthCache authCache = new BasicAuthCache();
			authCache.put(getCurrentHttpHost(), proxyAuth);
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
			requestConfigBuilder.setProxy(getCurrentHttpHost());
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
					return EntityUtils.toString(entity, currentEncode);
			} else {
				throw new Exception("服务请求异常: " + status + ",【URL=" + serviceURL + "】");
			}
		} finally {
			httpclient.close();
		}
		return "";
	}

	public static void main(String[] args) throws Exception {
		MyHttpServiceSupport mh = new MyHttpServiceSupport();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json, text/plain, */*");
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 QQBrowser/9.3.6874.400");
		headers.put("Accept-Language", "zh-CN,zh;q=0.8");
		headers.put("Accept-Encoding", "gzip, deflate, sdch");
		headers.put("Tyc-From", "normal");
		headers.put("Connection", "keep-alive");

		System.out.println(mh.doHttpGET("http://www.tianyancha.com/company/2338440666", headers));
		System.out.println(mh.doHttpGET("http://www.tianyancha.com/company/2338440666.json", headers));
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
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, currentEncode));
			CloseableHttpResponse response;
			// 创建上下文环境
			HttpContext context = getHttpContext();

			context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
			response = httpclient.execute(httpPost, context);
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					return EntityUtils.toString(entity, currentEncode);
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
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, currentEncode));

			CloseableHttpResponse response;
			// 创建上下文环境
			HttpContext context = getHttpContext();

			context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
			response = httpclient.execute(httpPost, context);

			int status = response.getStatusLine().getStatusCode();

			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					return EntityUtils.toString(entity, currentEncode);
			} else {
				throw new Exception("服务请求异常: " + status + ",【URL=" + serviceURL + "】");
			}
		} finally {
			httpclient.close();
		}
		return "";
	}

}
