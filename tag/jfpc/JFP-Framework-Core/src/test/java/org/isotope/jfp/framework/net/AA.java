package org.isotope.jfp.framework.net;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class AA {

	public String httpPost(String encode, String url, String host, List<NameValuePair> formparams) {
		String result = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();// 设置请求和传输超时时间
		CloseableHttpResponse response = null;
		BasicCookieStore basicCookieStore = new BasicCookieStore();
		httpClient = HttpClients.custom().setDefaultCookieStore(basicCookieStore).build();
		if (StringUtils.isNotEmpty(host)) {
			// httpPost.addHeader("Host", host);
		}
		UrlEncodedFormEntity entity;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			entity = new UrlEncodedFormEntity(formparams, encode);
			httpPost.setEntity(entity);
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = response.getEntity();
				result = EntityUtils.toString(httpEntity, encode);
			}
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
			System.out.println("连接超时");
		} finally {
			try {
				httpClient.close();
			} catch (IOException e1) {

			}
		}
		return result;
	}

	private List<NameValuePair> getFormparams(int page) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("searchtype", 0 + ""));
		list.add(new BasicNameValuePair("objectType", 2 + ""));
		list.add(new BasicNameValuePair("dataType", 1 + ""));
		list.add(new BasicNameValuePair("exact", 0 + ""));
		list.add(new BasicNameValuePair("page", page + ""));
		return list;
	}

	public static void main(String[] args) {
		AA x = new AA();
		Date date1 = new Date(1466585050944L);
		Date date = new Date();
		long time = date.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time1 = sdf.format(date1);
		String url = "http://www.creditchina.gov.cn/credit_info_search?t=" + time;

		for (int page = 1; page <= 5; page++) {
			List<NameValuePair> formparams = x.getFormparams(page);
			String msg = x.httpPost("utf-8", url, "www.creditchina.gov.cn", formparams);
			try {
				Thread.sleep((long) (Math.random() * 3000 + 2000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(msg);
		}
	}

}
