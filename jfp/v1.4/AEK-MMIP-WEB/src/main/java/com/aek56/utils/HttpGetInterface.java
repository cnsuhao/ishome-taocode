package com.aek56.utils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpGetInterface {
	private static Logger logger = Logger.getLogger(HttpGetInterface.class);
	public static final String ENCODE_DEFAULT = "UTF-8";

	public static String callServicePost(String httpPath,SmsAek56Bean bean){
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String result="";
			HttpPost httpPost = new HttpPost(httpPath);
			CloseableHttpResponse response = null;
			try {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();

				{
					// 获得所有属性名称
					for (Method m : bean.getClass().getMethods()) {
						String methodName = m.getName();
						if (methodName.startsWith("get")) {// &&
															// methodName.indexOf("_")
															// > 0
							try {
								Object value = m.invoke(bean);
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
				response = httpclient.execute(httpPost);
				HttpEntity entity = response.getEntity();
//		        InputStream s = entity.getContent();
		        result = EntityUtils.toString(entity);
		        System.out.println(result);
			} catch (Exception e) {
				e.printStackTrace();

			}finally{
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error("调用接口失败：", e);
					e.printStackTrace();
				}
			}	

		return result;
	}
}
