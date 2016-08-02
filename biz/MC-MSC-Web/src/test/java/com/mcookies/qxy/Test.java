package com.mcookies.qxy;

import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.mcookies.qxy.common.User.UserDBO;

public class Test {
	private static final String APPLICATION_JSON = "application/json";

	private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

	public static void main(String[] args) throws Exception {
		UserDBO user = new UserDBO();
		user.setAccount("fcy");
		user.setPassword("123456");

		System.out.println(httpPostWithJSON("http://127.0.0.1:6666/ttt", JSON.toJSONString(user)));
	}
	public static final String ENCODE_DEFAULT = "UTF-8";
	public static String httpPostWithJSON(String url, String json) throws Exception {
		// 将JSON进行UTF-8编码,以便传输中文
		String encoderJson = json;
		System.out.println("====>>>>>"+encoderJson);
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

		StringEntity se = new StringEntity(encoderJson);
		System.out.println("====>>>>>"+se);
		se.setContentType(CONTENT_TYPE_TEXT_JSON);
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
		
		
		httpPost.setEntity(se);
		CloseableHttpResponse response = httpClient.execute(httpPost);
		int status = response.getStatusLine().getStatusCode();
		if (status >= 200 && status < 300) {
			HttpEntity entity = response.getEntity();
			if (entity != null)
				return EntityUtils.toString(entity, ENCODE_DEFAULT);
		} else {
			throw new Exception("服务请求异常: " + status + ",【URL=" + url + "】");
		}
		return "请求失败";
	}
}
