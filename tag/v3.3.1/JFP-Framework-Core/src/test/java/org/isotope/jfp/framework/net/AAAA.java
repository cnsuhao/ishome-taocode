package org.isotope.jfp.framework.net;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class AAAA {

	public static void main(String[] args) throws Exception {
		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.BEST_MATCH).build();

		CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();

		RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY).build();
		{
			HttpGet get = new HttpGet("http://www.creditchina.gov.cn/search_all#keyword=&searchtype=0&departmentId=&creditType=&areas=&objectType=2&page=1");
			get.setConfig(localConfig);
			CloseableHttpResponse response = client.execute(get);
			String weatherDetail = EntityUtils.toString(response.getEntity());
			//System.out.println(weatherDetail);
		}
		{
			HttpGet get = new HttpGet("http://www.creditchina.gov.cn/search_all#keyword=&searchtype=0&departmentId=&creditType=&areas=&objectType=2&page=1");
			get.setConfig(localConfig);
			CloseableHttpResponse response = client.execute(get);
			String weatherDetail = EntityUtils.toString(response.getEntity());
			System.out.println(weatherDetail);
		}
	}

}
