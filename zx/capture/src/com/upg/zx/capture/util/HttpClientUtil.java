package com.upg.zx.capture.util;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import com.upg.zx.capture.bean.ServiceConfig;

import net.sf.json.JSONObject;

public class HttpClientUtil {
	public static ServiceConfig serviceConfig;

	public static ServiceConfig getServiceConfig() {
		return serviceConfig;
	}

	public static void setServiceConfig(ServiceConfig serviceConfig) {
		HttpClientUtil.serviceConfig = serviceConfig;
	}

	public static CloseableHttpClient getHttpclient() throws Exception {
		return getHttpclient("", 30, null);
	}

	public static CloseableHttpClient getHttpclient(String requestURL) throws Exception {
		return getHttpclient(requestURL, 30, null);
	}

	public static HttpHost getHttpProxy() throws Exception {
		JSONObject proxy = JSONObject.fromObject(doHttpGET(serviceConfig.getServiceConfig("useProxy")));		
		return (HttpHost) JSONObject.toBean(proxy, HttpHost.class);
	}

	protected static CloseableHttpClient getHttpclient(String requestURL, int waitTimeMinute) throws Exception {
		try {
			if (serviceConfig.getServiceConfig().containsKey("useProxy")) {
				return getHttpclient(requestURL, waitTimeMinute, getHttpProxy());
			}
		} catch (Exception e) {
		}

		return getHttpclient(requestURL, waitTimeMinute, null);
	}

	protected static CloseableHttpClient getHttpclient(String requestURL, int waitTimeMinute, HttpHost httpProxy)
			throws Exception {
		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

		RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
		requestConfigBuilder.setSocketTimeout(waitTimeMinute * 1000);
		requestConfigBuilder.setConnectTimeout(waitTimeMinute * 1000);
		requestConfigBuilder.setConnectionRequestTimeout(waitTimeMinute * 1000);
		// TODO PoolingHttpClientConnectionManager
		requestConfigBuilder.setStaleConnectionCheckEnabled(true);
		// 代理httpProxy
		if (httpProxy != null) {
			requestConfigBuilder.setProxy(httpProxy);
		}

		httpClientBuilder.setDefaultRequestConfig(requestConfigBuilder.build());

		if (requestURL != null && requestURL.length() != 0 && requestURL.indexOf("https") != -1) {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有证书
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

			httpClientBuilder.setSSLSocketFactory(sslsf);
		}

		return httpClientBuilder.build();
	}

	/**
	 * post请求
	 * 
	 * @param url
	 * @param content
	 * @return
	 * @throws IOException
	 */
	public static String postRequest(String url, String content) throws Exception {
		System.out.println("=====>>>>>"+url);
		CloseableHttpClient client = getHttpclient(url);
		HttpPost post = new HttpPost(url);
		post.setConfig(RequestConfig.custom().setSocketTimeout(30 * 1000).setConnectTimeout(30 * 1000).build());
		StringEntity stringEntity = new StringEntity(content, ENCODE_DEFAULT);
		post.setEntity(stringEntity);
		post.setHeader("Content-Type", "application/json; charset=UTF-8");
		post.setHeader("Connection", "keep-alive");
		try {
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e) {
			throw e;
		} finally {
			client.close();
		}
		return "";

	}

	public static String postRequest(String url, Map<String, String> content) throws Exception {
		System.out.println("=====>>>>>"+url);
		
		CloseableHttpClient client = getHttpclient(url);
		HttpPost post = new HttpPost(url);
		post.setConfig(RequestConfig.custom().setSocketTimeout(30 * 1000).setConnectTimeout(30 * 1000).build());
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// 设置参数
		if (content != null && content.size() > 0) {
			for (Map.Entry<String, String> entry : content.entrySet()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		try {

			post.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			post.setHeader("Connection", "keep-alive");
			post.setEntity(new UrlEncodedFormEntity(params, ENCODE_DEFAULT));
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e) {
			throw e;
		} finally {
			client.close();
		}
		return "";

	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getRequest(String url) throws Exception {
		System.out.println("=====>>>>>"+url);
		CloseableHttpClient client = getHttpclient(url);
		HttpGet get = new HttpGet(url);
		get.setConfig(RequestConfig.custom().setSocketTimeout(30 * 1000).setConnectTimeout(30 * 1000).build());
		get.setHeader("Content-Type", "application/json; charset=UTF-8");
		try {
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}

		return "";
	}

	public static final String ENCODE_DEFAULT = "UTF-8";

	public static String doHttpGET(String serviceURL)
			throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(serviceURL);

			// 设定传输编码
			CloseableHttpResponse response;
			response = httpClient.execute(httpGet);
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					return EntityUtils.toString(entity, ENCODE_DEFAULT);
			} else {
				throw new Exception("服务请求异常: " + status + ",【URL=" + serviceURL + "】");
			}
		} finally {
			httpClient.close();
		}
		return "";
	}

	public static void main(String[] arg) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(format.format(new Date(1457778679000l)));
	}

}
