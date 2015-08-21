package com.hundsun.med.hdp.service.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

public class WebServiceUtil {
	
	/*
	 * 
	 */
	public static String getResponseXml(String request_xml,String webservice_url) throws HttpException, IOException{
		PostMethod postMethod = new PostMethod(webservice_url);

		// 然后把Soap请求数据添加到PostMethod中
		byte[] b = request_xml.getBytes("utf-8");
//		byte[] b = request_xml.getBytes("utf-8");
		InputStream is = new ByteArrayInputStream(b, 0, b.length);
		RequestEntity re = new InputStreamRequestEntity(is, b.length,"application/soap+xml; charset=utf-8");
		postMethod.setRequestEntity(re);

		// 最后生成一个HttpClient对象，并发出postMethod请求
		HttpClient httpClient = new HttpClient();
		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode == 200) {
			System.out.println("调用成功！");
			String soapResponseData = postMethod.getResponseBodyAsString();
			System.out.println(soapResponseData);
			return soapResponseData.toString();
		} else {
			System.out.println("调用失败！错误码：" + statusCode);
		}
		return null;
	}
}
