package org.isotope.jfp.framework.net;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.isotope.jfp.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSONObject;

/**
 * API请求通信
 * 
 * @author Spook
 * @since 3.2.1
 * @version 3.2.1.2016/06/29
 * @see <MyHttpServiceSupport>
 * 
 */
public class MyHttpHost {

	public final String ENCODE_DEFAULT = "UTF-8";
	private String serviceURL = "";

	public String getServiceURL() {
		return serviceURL;
	}

	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}

	private HttpHost httpHostProxy;
	private long lastTime = System.currentTimeMillis();

	public HttpHost getHttpProxy() throws Exception {
		try {
			if (httpHostProxy == null) {
				loadHttpProxy();
			} else {
				long nowTime = System.currentTimeMillis();
				if ((nowTime - lastTime) > 120 * 1000) {
					lastTime = nowTime;
					loadHttpProxy();
				}
			}
		} catch (Exception e) {
			System.out.println("getHttpProxy =====>>>>>" + e.getMessage());
		}
		return httpHostProxy;
	}

	private HttpHost loadHttpProxy() throws Exception {
		httpHostProxy = null;
		if (EmptyHelper.isNotEmpty(serviceURL)) {
			JSONObject proxy = JSONObject.parseObject(doHttpProxyGET(serviceURL));

			System.out.println("useing proxy =====>>>>>" + proxy);
			proxy = JSONObject.parseObject(proxy.getString("data"));
			
			httpHostProxy = new HttpHost(proxy.getString("hostName"), proxy.getIntValue("port"));
		}
		return httpHostProxy;
	}

	public String doHttpProxyGET(String serviceURL) throws Exception {
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
