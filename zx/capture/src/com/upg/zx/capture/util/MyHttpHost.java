package com.upg.zx.capture.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class MyHttpHost {

	public static final String ENCODE_DEFAULT = "UTF-8";
	public static String serviceURL = "http://testcapture.wzyrz.cn/Zheng/proxy/newProtool";

	public static String doHttpProxyGET(String serviceURL) throws Exception {
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
}
