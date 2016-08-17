package com.upg.zx.capture.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	/**
	 * post请求
	 * 
	 * @param url
	 * @param content
	 * @return
	 * @throws IOException
	 */
	public static String postRequest(String url, String content)
			throws IOException {
		CloseableHttpClient client = getHttpclient(url);
		HttpPost post = new HttpPost(url);
		post.setConfig(RequestConfig.custom().setSocketTimeout(30 * 1000)
				.setConnectTimeout(30 * 1000).build());
		StringEntity stringEntity = new StringEntity(content, "UTF-8");
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

	public static String postRequest(String url, Map<String, String> content)
			throws IOException {
		CloseableHttpClient client = getHttpclient(url);
		HttpPost post = new HttpPost(url);
		post.setConfig(RequestConfig.custom().setSocketTimeout(30 * 1000)
				.setConnectTimeout(30 * 1000).build());
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// 设置参数
		if (content != null && content.size() > 0) {
			for (Map.Entry<String, String> entry : content.entrySet()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));

			}
		}
		try {

			post.setHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=UTF-8");
			post.setHeader("Connection", "keep-alive");
			post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
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
	public static String getRequest(String url) throws IOException {
		CloseableHttpClient client = getHttpclient(url);
		HttpGet get = new HttpGet(url);
		get.setConfig(RequestConfig.custom().setSocketTimeout(60 * 1000)
				.setConnectTimeout(60 * 1000).build());
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

	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getRequest(String url, Map<String, String> content)
			throws IOException {
		CloseableHttpClient client = getHttpclient(url);
		HttpGet get = new HttpGet(url);
		get.setConfig(RequestConfig.custom().setSocketTimeout(60 * 1000)
				.setConnectTimeout(60 * 1000).build());
		get.setHeader("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		if (content != null && content.size() > 0) {
			for (Map.Entry<String, String> entry : content.entrySet()) {
				if (url.indexOf("?") == -1) {
					url += "?" + entry.getKey().trim() + "="
							+ URLEncoder.encode(entry.getValue(), "UTF-8");
				} else {
					url += "&" + entry.getKey().trim() + "="
							+ URLEncoder.encode(entry.getValue(), "UTF-8");
				}
			}
		}
		try {
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toString(response.getEntity(),"UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}

		return "";
	}

	public static CloseableHttpClient getHttpclient(String url) {
		if (url.indexOf("https") != -1) {
			try {
				SSLContextBuilder builder = new SSLContextBuilder();
				builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
						builder.build());
				CloseableHttpClient httpclient = HttpClients.custom()
						.setSSLSocketFactory(sslsf).build();
				return httpclient;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return HttpClients.createDefault();

	}

	public static void main(String[] arg) {
		// System.out.println(HttpClientUtil.getRequest("http://localhost:8080/Zheng/corp/info/update/sedCompany"));
	}

}
