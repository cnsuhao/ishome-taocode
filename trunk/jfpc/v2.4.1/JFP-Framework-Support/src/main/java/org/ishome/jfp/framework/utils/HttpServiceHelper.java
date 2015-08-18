package org.ishome.jfp.framework.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.ishome.jfp.framework.beands.ObjectBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * API请求通信
 * 
 * @author Spook
 * @since 0.1.0
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
	 * JOSN字符串形式发送参数名的常量定义
	 */
	public static final String POST_PARAM = "jsonData";

	public static final String ENCODE_DEFAULT = "UTF-8";

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

		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(waitTimeMinute * 1000).setConnectTimeout(waitTimeMinute * 1000).setConnectionRequestTimeout(waitTimeMinute * 1000).setStaleConnectionCheckEnabled(true).build();
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
				throw new Exception("服务请求异常: " + status+",【URL="+serviceURL+"】");
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
		logger.info("=====>>>>>接口请求<<<<<=====" + serviceURL);
		// CloseableHttpClient httpclient = HttpClients.createDefault();
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(waitTimeMinute * 1000).setConnectTimeout(waitTimeMinute * 1000).setConnectionRequestTimeout(waitTimeMinute * 1000).setStaleConnectionCheckEnabled(true).build();
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

		try {
			HttpPost httpPost = new HttpPost(serviceURL);

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			// 参数翻转
			nvps.add(new BasicNameValuePair(POST_PARAM, jsonString));

			// 设定传输编码
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODE_DEFAULT));

			// FileBody fileBody = new FileBody(new File("/home/sendpix0.jpg"));
			// StringBody stringBody = new StringBody("文件的描述");
			// MultipartEntity entity = new MultipartEntity();
			// entity.addPart("file", fileBody);
			// entity.addPart("desc", stringBody);
			// post.setEntity(entity);

			CloseableHttpResponse response = httpclient.execute(httpPost);
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					return EntityUtils.toString(entity, ENCODE_DEFAULT);
			} else {
				throw new Exception("服务请求异常: " + status+",【URL="+serviceURL+"】");
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
	 * @param param 额外扩展参数定义
	 * @return
	 * @throws Exception
	 */
	public static String doHttpPOST(String serviceURL, String jsonString, Map<String, String> param) throws Exception {
		logger.info("=====>>>>>接口请求<<<<<=====" + serviceURL);
		// CloseableHttpClient httpclient = HttpClients.createDefault();
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(waitTimeMinute * 1000).setConnectTimeout(waitTimeMinute * 1000).setConnectionRequestTimeout(waitTimeMinute * 1000).setStaleConnectionCheckEnabled(true).build();
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

		try {
			HttpPost httpPost = new HttpPost(serviceURL);

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			// 参数翻转
			nvps.add(new BasicNameValuePair(POST_PARAM, jsonString));

			// 额外参数
			if (param != null) {
				for (Map.Entry<String, String> entry : param.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}

			// 设定传输编码
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODE_DEFAULT));

			// FileBody fileBody = new FileBody(new File("/home/sendpix0.jpg"));
			// StringBody stringBody = new StringBody("文件的描述");
			// MultipartEntity entity = new MultipartEntity();
			// entity.addPart("file", fileBody);
			// entity.addPart("desc", stringBody);
			// post.setEntity(entity);

			CloseableHttpResponse response = httpclient.execute(httpPost);
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					return EntityUtils.toString(entity, ENCODE_DEFAULT);
			} else {
				throw new Exception("服务请求异常: " + status+",【URL="+serviceURL+"】");
			}
		} finally {
			httpclient.close();
		}
		return "";
	}
}
