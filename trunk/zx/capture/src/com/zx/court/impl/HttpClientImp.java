package com.zx.court.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.zx.court.pojo.CookiePojo;
import com.zx.court.pojo.HttpClientParam;

public class HttpClientImp implements IHttpClient {

	private static Integer SC_OK = 200;
	public static void main(String[] args) throws Exception{
		String str;
        //创建一个webclient
        WebClient webClient = new WebClient();
        //htmlunit 对css和javascript的支持不好，所以请关闭之
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        //获取页面
        HtmlPage page = webClient.getPage("http://www.baidu.com/");
        //获取页面的TITLE
        str = page.getTitleText();
        System.out.println(str);
        //获取页面的XML代码
        str = page.asXml();
        System.out.println(str);
        //获取页面的文本
        str = page.asText();
        System.out.println(str);
        //关闭webclient
        webClient.close();

	}

	/**
	 * GET方式获取网站内容
	 * 
	 * @throws IOException
	 * @throws HttpException
	 * 
	 * */
	public CookiePojo gethtmlByGet(String encode, String p_url,
			String p_referer, String host, String Proxy, int port,
			String strCookie, HttpClientParam param) {

		String entity = "";
		List<Cookie> cookies_rs = new ArrayList();
		boolean flag = true;
		CookiePojo pojo = new CookiePojo();

		CloseableHttpClient httpClient = null;

		if (!"".equals(Proxy) && Proxy != null) {
			HttpHost proxy = new HttpHost(Proxy, port);
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(
					proxy);
			httpClient = HttpClients.custom().setRoutePlanner(routePlanner)
					.build();
		} else {
			httpClient = HttpClients.createDefault();
		}

		HttpGet getMethod = null;
		getMethod = new HttpGet(p_url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(8000).build();// 设置请求和传输超时时间
		getMethod.setConfig(requestConfig);

		getMethod.setHeader("Host", host);

		// getMethod.setHeader("Cache-Control", "max-age=0");
		getMethod
				.setHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		// getMethod.setHeader("X-Requested-With", "XMLHttpRequest");
		getMethod
				.setHeader(
						"User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
		getMethod.setHeader("Accept-Encoding", "gzip, deflate, sdch");
		getMethod.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		getMethod.setHeader("Connection", "keep-alive");
		if (!"".equals(p_referer) && p_referer != null) {
			getMethod.setHeader("Referer", p_referer);
		}
		//

		// strCookie="JSESSIONID=D85AC7B7500916D0B3302C57827D03EE; _gscu_1049835508=33744202zpf7o015; __jsluid=626ab9455ddb6ae91facfaabbc8e6e7b; __jsl_clearance=2449020879.773|0|0eb5v0eODHrTiglWxbuZHTNNd3g%3D";
		if (strCookie != null && !"".equals(strCookie)) {
			getMethod.setHeader("Cookie", strCookie);
		}

		try {
			HttpResponse response = httpClient.execute(getMethod);
			Header[] aa = response.getHeaders(param.getParamName());
			if (aa.length > 0) {
				entity = aa[0].getValue().split(";")[0];
			}
			// strCook.contains("yunsuo_session_verify")
			// if (entity.contains("__jsluid")){
			if (entity.contains("yunsuo_session_verify")) {
				strCookie = entity;
				pojo.setStrCookie(strCookie);
			} else if (entity.contains("wzwsconfirm")) {
				strCookie = entity;
				pojo.setStrCookie(strCookie);
			} else if (entity.contains("ccpassport")) {
				strCookie = entity + "; wzwschallenge=-1";
				pojo.setStrCookie(strCookie);
			}

			System.out.println("Status:"
					+ response.getStatusLine().getStatusCode());
			pojo.setStrReturnCode(String.valueOf(response.getStatusLine()
					.getStatusCode()));
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				entity = EntityUtils.toString(response.getEntity(), "UTF-8");

			} else {
				entity = EntityUtils.toString(response.getEntity());

			}
			pojo.setStrCookie(strCookie);

			pojo.setStrResult(entity);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return pojo;
	}

	/**
	 * GET方式获取网站内容
	 * 
	 * @throws IOException
	 * @throws HttpException
	 * 
	 * */
	public CookiePojo gethtmlByRedirect(String encode, String p_url,
			String p_referer, String host, String Proxy, int port,
			String strCookie, HttpClientParam param) {

		String entity = "";
		List<Cookie> cookies_rs = new ArrayList();
		boolean flag = true;
		CookiePojo pojo = new CookiePojo();

		CloseableHttpClient httpClient = null;

		if (!"".equals(Proxy) && Proxy != null) {
			HttpHost proxy = new HttpHost(Proxy, port);
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(
					proxy);
			httpClient = HttpClients.custom().setRoutePlanner(routePlanner)
					.build();
		} else {
			httpClient = HttpClients.createDefault();
		}

		HttpPost getMethod = new HttpPost(p_url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(8000).build();// 设置请求和传输超时时间
		getMethod.setConfig(requestConfig);

		getMethod.setHeader("Host", host);

		// getMethod.setHeader("Cache-Control", "max-age=0");
		getMethod
				.setHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		// getMethod.setHeader("X-Requested-With", "XMLHttpRequest");
		getMethod
				.setHeader(
						"User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
		getMethod.setHeader("Accept-Encoding", "gzip, deflate, sdch");
		getMethod.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		getMethod.setHeader("Connection", "keep-alive");
		if (!"".equals(p_referer) && p_referer != null) {
			getMethod.setHeader("Referer", p_referer);
		}
		//

		// strCookie="JSESSIONID=D85AC7B7500916D0B3302C57827D03EE; _gscu_1049835508=33744202zpf7o015; __jsluid=626ab9455ddb6ae91facfaabbc8e6e7b; __jsl_clearance=2449020879.773|0|0eb5v0eODHrTiglWxbuZHTNNd3g%3D";
		if (strCookie != null && !"".equals(strCookie)) {
			getMethod.setHeader("Cookie", strCookie);
		}

		try {
			HttpResponse response = httpClient.execute(getMethod);
			Header[] aa = response.getHeaders(param.getParamName());
			if (aa.length > 0) {
				entity = aa[0].getValue().split(";")[0];
			}
			// strCook.contains("yunsuo_session_verify")
			// if (entity.contains("__jsluid")){
			if (entity.contains("yunsuo_session_verify")) {
				strCookie = entity;
				pojo.setStrCookie(strCookie);
			} else if (entity.contains("wzwsconfirm")) {
				strCookie = entity;
				pojo.setStrCookie(strCookie);
			} else if (entity.contains("ccpassport")) {
				strCookie = entity + "; wzwschallenge=-1";
				pojo.setStrCookie(strCookie);
			}

			System.out.println("Status:"
					+ response.getStatusLine().getStatusCode());
			pojo.setStrReturnCode(String.valueOf(response.getStatusLine()
					.getStatusCode()));
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				entity = EntityUtils.toString(response.getEntity(), "UTF-8");

			} else {
				entity = EntityUtils.toString(response.getEntity());

			}
			pojo.setStrCookie(strCookie);

			pojo.setStrResult(entity);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return pojo;
	}

	/**
	 * Post方式获取网站内容
	 * 
	 * @throws IOException
	 * @throws HttpException
	 * 
	 * */
	public CookiePojo gethtmlByPost(String encode, String p_url,
			String p_referer, String host, String Proxy, int port,
			String strCookie, String type, String idx) {

		String entity = "";
		List<Cookie> cookies_rs = new ArrayList();
		boolean flag = true;
		CookiePojo pojo = new CookiePojo();

		CloseableHttpClient httpClient = null;

		if (!"".equals(Proxy) && Proxy != null) {
			HttpHost proxy = new HttpHost(Proxy, port);
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(
					proxy);
			httpClient = HttpClients.custom().setRoutePlanner(routePlanner)
					.build();
		} else {
			httpClient = HttpClients.createDefault();
		}

		HttpPost getMethod = new HttpPost(p_url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(5000).build();
		getMethod.setConfig(requestConfig);
		getMethod.setHeader("Host", host);
		getMethod.setHeader("Connection", "keep-alive");
		getMethod.setHeader("Accept", "*/*");
		getMethod.setHeader("Accept-Encoding", "gzip, deflate");
		getMethod.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		getMethod.setHeader("Origin", "http://www.court.gov.cn");
		getMethod.setHeader("X-Requested-With", "XMLHttpRequest");
		getMethod
				.setHeader(
						"User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
		getMethod.setHeader("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		getMethod.setHeader("Referer", p_referer);

		if (strCookie != null && !"".equals(strCookie)) {
			getMethod.setHeader("Cookie", strCookie);
		}

		List formparams = new ArrayList();
		formparams.add(new BasicNameValuePair("Param", type));
		formparams.add(new BasicNameValuePair("Index", idx));
		formparams.add(new BasicNameValuePair("Page", "20"));
		formparams.add(new BasicNameValuePair("Order", "法院层级"));
		formparams.add(new BasicNameValuePair("Direction", "asc"));

		try {
			getMethod
					.setEntity(new UrlEncodedFormEntity(formparams, HTTP.UTF_8));
			HttpResponse response = httpClient.execute(getMethod);

			System.out.println("Status:"
					+ response.getStatusLine().getStatusCode());

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				entity = EntityUtils.toString(response.getEntity(), "UTF-8");

			} else {
				entity = EntityUtils.toString(response.getEntity());

			}

			pojo.setStrCookie(strCookie);

			pojo.setStrResult(entity);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return pojo;
	}

	public String getCookieByWebClient(String url, String ip, int port) {

		String cookie = "";
		String initCookie = "";
		String html = "";
		initCookie = "_gscu_482111853=496299504ske1d22; _gscu_1241932522=50261046iiejmb17; __utma=61363882.1451150868.1456899203.1456899203.1457003494.2; __utmz=61363882.1456899203.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _gscu_1049835508=33744202zpf7o015; Hm_lvt_9e03c161142422698f5b0d82bf699727=1461210524,1461898435,1462262294,1462343075; ";

		Set set = new HashSet();
		BrowserVersion bv = BrowserVersion.CHROME;
		bv.setBrowserLanguage("zh");
		WebClient webClient = null;
		if (!"".equals(ip) && ip != null) {
			webClient = new WebClient(bv, ip, port);
		} else {
			webClient = new WebClient(bv);
		}
		webClient.getCookieManager().setCookiesEnabled(true);// 开启cookie管理
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setTimeout(30000);
		webClient.waitForBackgroundJavaScript(10000);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = null;
		try {
			page = webClient.getPage(url);
		} catch (Exception e) {
			return "404";
		}

		// 得到cookie

		CookieManager CM = webClient.getCookieManager();
		Set cookies_ret = CM.getCookies();

		Iterator<Cookie> i = cookies_ret.iterator();
		String tmpPass = "";
		boolean cookieFlag = false;
		String tmpChallenge = "";
		String tmpgs = "";
		String tmpsession = "";
		String tmpsession2 = "";
		String tmpFirm = "";
		while (i.hasNext()) {

			String strCookie = String.valueOf(i.next());
			System.out.println(strCookie);
			if (strCookie.contains("ccpassport")) {
				tmpPass = StringUtils.substringBefore(strCookie, ";") + "; ";
			} else if (strCookie.contains("yunsuo_session_verify")) {
				if (!cookieFlag) {
					tmpsession = StringUtils.substringBefore(strCookie, ";")
							+ "; ";
					cookieFlag = true;
				} else {
					tmpsession2 = StringUtils.substringBefore(strCookie, ";");
				}
			} else if (strCookie.contains("wzwschallenge")) {
				tmpChallenge = StringUtils.substringBefore(strCookie, ";")
						+ "; ";
			} else if (strCookie.contains("_gsref_2116842793")
					|| strCookie.contains("_gscs_2116842793")
					|| strCookie.contains("_gscu_2116842793")
					|| strCookie.contains("_gscbrs_2116842793")) {
				if (!"".equals(tmpgs) && tmpgs != null) {
					tmpgs = tmpgs + "; "
							+ StringUtils.substringBefore(strCookie, ";");
				} else {
					tmpgs = StringUtils.substringBefore(strCookie, ";");
				}
			} else if (strCookie.contains("wzwsconfirm")
					|| strCookie.contains("wzwstemplate")) {
				if (!"".equals(tmpFirm) && tmpFirm != null) {
					tmpFirm = tmpFirm
							+ StringUtils.substringBefore(strCookie, ";")
							+ "; ";
				} else {
					tmpFirm = StringUtils.substringBefore(strCookie, ";")
							+ "; ";
				}
			}

		}
		initCookie = initCookie + tmpsession + tmpgs + tmpFirm + tmpChallenge
				+ tmpPass + tmpsession2;
		System.out.println("============" + initCookie);

		return initCookie;
	}

	/*
	 * httpClient4.3.2
	 */
	public void httpPost(String encode, String url,
			List<NameValuePair> formparams) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(100000).setConnectTimeout(100000).build();// 设置请求和传输超时时间
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		UrlEncodedFormEntity entity;
		try {
			entity = new UrlEncodedFormEntity(formparams, encode);
			httpPost.setEntity(entity);
			httpClient.execute(httpPost);
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("连接超时");
		} finally {
			try {
				httpClient.close();
			} catch (IOException e1) {
			}
		}
	}

	// public static void main(String str[]) {
	//
	// String test
	// ="Hm_lvt_692544717e4ca5489bff77b2e9b28e7b=1450088906,1450089482,1450091048,1450091068; _gscu_482111853=496299504ske1d22; _gscu_1049835508=33744202zpf7o015; _pk_id.1.cf4c=cc8352bbbac1502c.1448419507.7.1450178416.1450177914.; __jsl_clearance=1450261342.665|0|jiNhmxIq1%2BAeHbC%2Bl9QZD3aEXeI%3D; _gsref_1241932522=http://www.court.gov.cn/zgcpwsw; _gscu_1241932522=50261046iiejmb17; _gscs_1241932522=50261046fom38f17|pv:7";
	// HttpClientImp tc = new HttpClientImp();
	// HttpClientParam param = new HttpClientParam();
	// param.setParamName("Set-Cookie");
	// tc.gethtmlByGet("UTF-8", "http://www.court.gov.cn/zgcpwsw", "",
	// "www.court.gov.cn", "", 0, test,param);
	// }

}
