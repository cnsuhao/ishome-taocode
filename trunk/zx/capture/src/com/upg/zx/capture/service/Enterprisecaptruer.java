package com.upg.zx.capture.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.upg.zx.capture.util.HttpClientUtil;

/**
 * 抓取工商网站数据信息
 * 
 * @author lujf
 * 
 */
public class Enterprisecaptruer {

	private static final String url = "http://gsxt.zjaic.gov.cn/search/doEnGeneralQueryPage.do";

	private static final String BASE_URL = "http://gsxt.zjaic.gov.cn/appbasicinfo/doReadAppBasicInfo.do";

	private static final String REFERER_RUL = "http://gsxt.zjaic.gov.cn/appbasicinfo/doViewAppBasicInfo.do";

	private static final String HOST_URL = "http://gsxt.zjaic.gov.cn/";

	private static final String HOST = "gsxt.zjaic.gov.cn";

	public Enterprisecaptruer() {

	}

	/**
	 * 解析公司信息
	 * 
	 * @param table
	 * @return
	 */
	public Map paseCompanyInfo(Element table) {
		Map<String, String> map = new HashMap<String, String>();
		// 基础信息
		Elements baseTrs = table.select("tr");
		for (int i = 0; i < baseTrs.size(); i++) {
			// 第一行为标题
			if (i == 0) {
				continue;
			}
			// 获取TR
			Element tr = baseTrs.get(i);
			// 获取TH
			Elements ths = tr.select("th");
			for (int th_count = 0; th_count < ths.size(); th_count++) {
				Element th = ths.get(th_count);
				String val = "";
				if (tr.select("td") != null && tr.select("td").get(th_count) != null) {
					val = tr.select("td").get(th_count).text().trim();
				}
				map.put(th.text().trim(), val);
			}

		}
		return map;
	}

	/**
	 * 解析股东信息以及变更信息
	 * 
	 * @param table
	 * @return
	 */
	public List paseShareholderOrChange(Element table) {
		Elements trElements = table.select("tr");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String titles = "";
		for (int tr_count = 0; tr_count < trElements.size(); tr_count++) {
			Element trElement = trElements.get(tr_count);
			if (tr_count == 1) {
				// 获取表头
				Elements tdElements = trElement.select("th");
				for (int td_count = 0; td_count < tdElements.size(); td_count++) {
					Element tdElement = tdElements.get(td_count);
					if ("".equals(titles)) {
						titles = tdElement.text().trim();
					} else {
						titles = titles + "," + tdElement.text().trim();
					}
				}
			} else if (tr_count > 1) {

				Map<String, String> ext_map = new HashMap<String, String>();
				Elements tdElements = trElement.select("td");
				// 最后一行是分页
				if (tdElements == null || tdElements.size() == 0) {
					break;
				}
				String[] _titles = titles.split(",");
				for (int j = 0; j < tdElements.size(); j++) {
					Element tdElement = tdElements.get(j);
					String val = "";
					// 判断是否有a标签(存在a标签则内容获取span里面的信息)
					Elements als = tdElement.select("a");
					if (als != null && als.size() > 0) {
						for (int a = 0; a < als.size(); a++) {
							Element a_element = als.get(a);
							a_element.remove();
						}
						// 如果有a标签，则获取最后一个span里面的内容
						Elements spanls = tdElement.select("span");
						if (spanls != null && spanls.size() > 0) {
							val = spanls.get(spanls.size() - 1).text().trim();
						}

					} else {
						val = tdElement.text().trim();
					}
					ext_map.put(_titles[j], val);
				}
				if (tdElements != null && tdElements.size() > 0) {
					list.add(ext_map);
				}

			}
		}
		return list;
	}

	/**
	 * 解析合伙人信息
	 * 
	 * @param talbe
	 * @return
	 */
	public List<Map<String, String>> pasePartner(Element talbe) {
		Elements trElements = talbe.select("tr");
		String titles = "";
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (int tr_count = 0; tr_count < trElements.size(); tr_count++) {
			Element trElement = trElements.get(tr_count);
			if (tr_count == 1) {
				// 获取表头
				Elements tdElements = trElement.select("th");
				for (int td_count = 0; td_count < tdElements.size(); td_count++) {
					Element tdElement = tdElements.get(td_count);
					String title = tdElement.text().trim();
					if ("合伙人类型".equals(title)) {
						title = "股东类型";
					} else if ("合伙人".equals(title)) {
						title = "股东";
					}
					if ("".equals(titles)) {
						titles = title;
					} else {
						titles = titles + "," + title;
					}
				}
			} else if (tr_count > 1) {
				// 最后一行是分页
				Elements tdElements = trElement.select("td");
				if (tdElements == null || tdElements.size() == 0) {
					break;
				}
				Map<String, String> ext_map = new HashMap<String, String>();
				String[] _titles = titles.split(",");
				for (int j = 0; j < tdElements.size(); j++) {
					Element tdElement = tdElements.get(j);
					String val = "";
					// 判断是否有a标签(存在a标签则内容获取span里面的信息)
					Elements als = tdElement.select("a");
					if (als != null && als.size() > 0) {
						for (int a = 0; a < als.size(); a++) {
							Element a_element = als.get(a);
							a_element.remove();
						}
						// 如果有a标签，则获取span里面的内容
						Elements spanls = tdElement.select("span");
						if (spanls != null && spanls.size() > 0) {
							val = spanls.get(spanls.size() - 1).text().trim();
						}

					} else {
						val = tdElement.text().trim();
					}
					ext_map.put(_titles[j], val);
				}
				if (tdElements != null && tdElements.size() > 0) {
					list.add(ext_map);
				}

			}
		}
		return list;
	}

	public List<Map<String, String>> paseInfo(Element talbe, String[] titles) {
		Elements trElements = talbe.select("tr");

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (int tr_count = 0; tr_count < trElements.size(); tr_count++) {
			Element trElement = trElements.get(tr_count);
			if (tr_count > 1) {
				// 最后一行是分页
				Elements tdElements = trElement.select("td");
				if (tdElements == null || tdElements.size() == 0) {
					break;
				}
				Map<String, String> ext_map = new HashMap<String, String>();
				for (int j = 0; j < tdElements.size(); j++) {
					Element tdElement = tdElements.get(j);
					String val = "";
					// 判断是否有a标签(存在a标签则内容获取span里面的信息)
					Elements als = tdElement.select("a");
					if (als != null && als.size() > 0) {
						for (int a = 0; a < als.size(); a++) {
							Element a_element = als.get(a);
							a_element.remove();
						}
						// 如果有a标签，则获取span里面的内容
						Elements spanls = tdElement.select("span");
						if (spanls != null && spanls.size() > 0) {
							val = spanls.get(spanls.size() - 1).text().trim();
						}

					} else {
						val = tdElement.text().trim();
					}
					ext_map.put(titles[j], val);
				}
				if (tdElements != null && tdElements.size() > 0) {
					list.add(ext_map);
				}

			}
		}
		return list;
	}

	/**
	 * 抓取公司信息，包括基本信息、股东信息(合伙人信息)、变更信息。(优化方法)
	 * 
	 * @param companyId
	 * @return
	 * @throws Exception 
	 */
	public Map getCompanyInfo_(String companyId, int currentPage, int pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String param = "&entInvestorPagination.currentPage=" + currentPage + "&entInvestorPagination.pageSize="
				+ pageSize + "&checkAlterPagination.currentPage=" + currentPage + "&checkAlterPagination.pageSize="
				+ pageSize;
		String html = gethtml(BASE_URL + "?corpid=" + companyId + param, REFERER_RUL + "?corpid=" + companyId, HOST,
				"UTF-8");
		Document companyInfo_doc = Jsoup.parse(html);
		Elements tables = companyInfo_doc.getElementsByClass("detailsList");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (tables == null || tables.size() == 1) {
			return map;
		}

		for (int tb = 0; tb < tables.size(); tb++) {
			if (tb == 0) {
				// 公司信息
				Map companMap = paseCompanyInfo(tables.get(tb));
				companMap.put("company_ID", companyId);
				map.put("companyDetails", companMap);
				continue;
			}
			Element tableElement = tables.get(tb);
			Elements trElements = tableElement.select("tr");
			for (int tr_count = 0; tr_count < trElements.size(); tr_count++) {
				Element trElement = trElements.get(tr_count);
				// 获取类别
				if (tr_count == 0) {
					if (trElement.select("th") != null && trElement.select("th").size() > 0) {
						String tr_title = trElement.select("th").get(0).text();
						if (tr_title.indexOf("股东信息") != -1) {
							map.put("infoList", paseShareholderOrChange(tables.get(tb)));
						} else if (tr_title.indexOf("变更信息") != -1) {
							map.put("changeList", paseShareholderOrChange(tables.get(tb)));
						} else if (tr_title.indexOf("合伙人信息") != -1) {
							// 合伙人信息加入到股东信息中
							map.put("infoList", pasePartner(tables.get(tb)));
						} else if (tr_title.indexOf("投资人信息") != -1) {
							map.put("infoList", paseInfo(tables.get(tb), new String[] { "股东", "股东类型" }));
						}
					}
					break;

				}
			}

		}

		return map;

	}

	/**
	 * 备案信息抓取
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public Map getregisterInfo(String id) throws Exception {
		Map returnMap = new HashMap();
		String param = "&entInvestorPagination.currentPage=1&entInvestorPagination.pageSize=100&branchInfoPagination.currentPage=1&branchInfoPagination.pageSize=50";
		String html = gethtml(HOST_URL + "/filinginfo/doViewFilingInfo.do?corpid=" + id + param,
				REFERER_RUL + "?corpid=" + id, HOST, "UTF-8");
		Document registerDoc = Jsoup.parse(html);

		Elements tables = registerDoc.getElementsByClass("detailsList");
		String key = "";
		String title = "";
		for (int i = 0; i < tables.size(); i++) {
			List list = new ArrayList();
			Element tableElement = tables.get(i);
			Elements trElements = tableElement.select("tr");
			for (int j = 0; j < trElements.size(); j++) {
				HashMap<String, String> map = new HashMap<String, String>();
				Element trElement = trElements.get(j);
				Elements tdElements = trElement.select("td");
				// 标题
				if (j == 0) {
					tdElements = trElement.select("th");
					if (tdElements.size() > 0) {
						String tc = tdElements.get(0).text();
						if (tc.indexOf("主要人员信息") != -1) {
							key = "ryxx";
						} else if (tc.indexOf("分支机构信息") != -1) {
							key = "fzjgxx";
						} else if (tc.indexOf("清算信息") != -1) {
							key = "qsxx";
						}
					}
					break;
				}
			}

			// 主要人员信息
			if ("ryxx".equals(key)) {
				for (int rs = 0; rs < trElements.size(); rs++) {
					Element tr_elemet = trElements.get(rs);
					if (rs == 1) {
						title = tr_elemet.select("th").get(0).text().trim() + ","
								+ tr_elemet.select("th").get(1).text().trim() + ","
								+ tr_elemet.select("th").get(2).text().trim();
					} else if (rs > 1) {
						String[] title_array = title.split(",");
						Elements td_element = tr_elemet.select("td");
						// 判断是否含有分页
						if (td_element.size() < 2) {
							continue;
						}
						HashMap map1 = new HashMap();
						HashMap map2 = new HashMap();
						map1.put(title_array[0], td_element.get(0).text().trim());
						map1.put(title_array[1], td_element.get(1).text().trim());
						map1.put(title_array[2], td_element.get(2).text().trim());
						list.add(map1);
						// 判断后面是否有数据
						if (td_element.size() < 4) {
							continue;
						}

						map2.put(title_array[0], td_element.get(3).text().trim());
						map2.put(title_array[1], td_element.get(4).text().trim());
						map2.put(title_array[2], td_element.get(5).text().trim());

						list.add(map2);
					}
				}
				returnMap.put(key, list);
				// 行政机构信息
			} else if ("fzjgxx".equals(key)) {
				title = "";
				for (int rs = 0; rs < trElements.size(); rs++) {
					Element tr_elemet = trElements.get(rs);
					if (rs == 1) {
						Elements td_elements = tr_elemet.select("th");
						for (int fz_size = 0; fz_size < td_elements.size(); fz_size++) {
							Element td_element = td_elements.get(fz_size);
							if ("".equals(title)) {
								title = td_element.text().trim();
							} else {
								title = title + "," + td_element.text().trim();
							}
						}
					} else if (rs > 1) {
						Elements td_elements = tr_elemet.select("td");
						// 最后一行如果是分页的情况
						if (td_elements == null || td_elements.size() == 0) {
							continue;
						}
						String[] tltle_error = title.split(",");
						HashMap map1 = new HashMap();
						for (int fz_size = 0; fz_size < tltle_error.length; fz_size++) {
							Element td_element = td_elements.get(fz_size);
							map1.put(tltle_error[fz_size], td_elements.get(fz_size).text().trim());
						}
						list.add(map1);
					}
				}

				returnMap.put(key, list);
				// 清算信息(目前占无用)
			} else if ("qsxx".equals(key)) {
				HashMap map1 = new HashMap();
				map1.put(trElements.get(1).select("th").get(0).text().trim(),
						trElements.get(1).select("td").get(0).text().trim());
				map1.put(trElements.get(2).select("th").get(0).text().trim(),
						trElements.get(2).select("td").get(0).text().trim());
				returnMap.put(key, map1);
			}

		}
		return returnMap;
	}

	/**
	 * 获取其他相关信息公用方法
	 * 
	 * @param id
	 * @param getUrl
	 * @return
	 * @throws Exception 
	 */
	public List getOtherInfo(String id, String getUrl) throws Exception {
		List list = new ArrayList();
		String html = gethtml(HOST_URL + getUrl + "?corpid=" + id, REFERER_RUL + "?corpid=" + id, HOST, "UTF-8");
		Document storkDoc = Jsoup.parse(html);
		Elements elements = storkDoc.getElementsByClass("detailsList");
		String title = "";
		for (int s = 0; s < elements.size(); s++) {
			Element tableElement = elements.get(s);
			Elements trElement = tableElement.select("tr");
			for (int i = 0; i < trElement.size(); i++) {
				if (i == 1) {
					Elements ths_element = trElement.select("th");
					for (int j = 0; j < ths_element.size(); j++) {
						Element th_element = ths_element.get(j);
						if ("".equals(title)) {
							title = th_element.text().trim();
						} else {
							title = title + "," + th_element.text().trim();
						}
					}
				} else if (i > 1) {
					Elements ths_element = trElement.select("td");
					String[] title_array = title.split(",");
					if (ths_element.size() > 1) {
						Map<String, String> map = new HashMap<String, String>();
						for (int j = 0; j < title_array.length; j++) {
							map.put(title_array[j], ths_element.get(j).text().trim());
						}
						list.add(map);
					}
				}
			}
		}
		return list;
	}

	/**
	 * 获取行政处罚信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public List getAdministrativePenalty(String id) throws Exception {
		return getOtherInfo(id, "punishment/doViewPunishmentFromPV.do");
	}

	/**
	 * 股权出质信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public List getGqczInfo(String id) throws Exception {
		return getOtherInfo(id, "equityall/doReadEquityAllListFromPV.do");
	}

	/**
	 * 经营异常信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public List getJyycinfo(String id) throws Exception {
		return getOtherInfo(id, "catalogapply/doReadCatalogApplyList.do");
	}

	/**
	 * 严重违法
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public List getYzwf(String id) throws Exception {
		return getOtherInfo(id, "blacklist/doViewBlackListInfo.do");
	}

	/**
	 * 抽出检查
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public List getCcjc(String id) throws Exception {
		return getOtherInfo(id, "pubcheckresult/doViewPubCheckResultList.do");
	}

	/**
	 * 获取指定网站的html信息
	 * @throws Exception 
	 * 
	 */
	public String gethtml(String p_url, String p_referer, String host, String p_encode) throws Exception {
		String return_str = "";
		CloseableHttpClient httpClient = HttpClientUtil.getHttpclient(p_url);
		try {
			String encode = p_encode != null ? p_encode : "UTF-8";

			HttpGet getMethod = new HttpGet(p_url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60 * 1000)
					.setConnectTimeout(60 * 1000).build();

			getMethod.setConfig(requestConfig);

			if (p_referer != null && !"".equals(p_referer)) {
				getMethod.setHeader("Referer", p_referer);
			}

			getMethod.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			getMethod.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0");
			getMethod.setHeader("Host", host);
			getMethod.setHeader("Connection", "keep-alive");
			HttpResponse response = httpClient.execute(getMethod);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return_str = EntityUtils.toString(response.getEntity(), p_encode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return return_str;
	}

	/**
	 * 获取jsessionId
	 * 
	 * @param companyName
	 * @param authCode
	 * @param jsessionid
	 * @throws Exception 
	 */
	public String getJsessionId(String companyName) throws Exception {
		CloseableHttpClient httpClient = HttpClientUtil.getHttpclient();
		Map map = new HashMap();
		HttpGet getMethod = new HttpGet("http://gsxt.zjaic.gov.cn/search/doGetAppSearchResultIn.do");

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60 * 1000).setConnectTimeout(60 * 1000)
				.build();
		getMethod.setConfig(requestConfig);
		String jsessionid = "";
		getMethod.setHeader("Accept", "text/html, application/xhtml+xml, */*");
		getMethod.setHeader("Accept-Language", "zh-CN");
		getMethod.setHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)");
		getMethod.setHeader("Host", "gsxt.zjaic.gov.cn");
		// postMethod1.addRequestHeader("Accept-Encoding", "gzip, deflate");

		getMethod.setHeader("Connection", "Keep-Alive");
		getMethod.setHeader("DNT", "1");

		HttpContext httpContext = new BasicHttpContext();
		CookieStore cookieStore = new BasicCookieStore();
		httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);

		try {
			HttpResponse response = httpClient.execute(getMethod, httpContext);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				List<Cookie> cookies = cookieStore.getCookies();
				if (!cookies.isEmpty()) {
					for (int i = cookies.size(); i > 0; i--) {
						Cookie cookie = cookies.get(i - 1);
						if (cookie.getName().equalsIgnoreCase("jsessionid")) {
							jsessionid = cookie.getValue();
						}
					}
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jsessionid;

	}

	/**
	 * 获取公司列表信息
	 * 
	 * @param companyName
	 * @param authCode
	 * @param jsessionid
	 * @return
	 * @throws Exception 
	 */
	public Map<String, Object> findCompanyByName(String companyName, String authCode, String jsessionid) throws Exception {

		// 没有验证码的情况下先获取验证码
		String sessionId = "";
		if (authCode == null || "".equals(authCode)) {
			sessionId = getJsessionId(companyName);
			return this.getAuthCode(companyName, sessionId);
		} else {
			sessionId = jsessionid;
		}
		// 进行查询请求
		CloseableHttpClient httpClient = HttpClientUtil.getHttpclient();

		HttpPost postMethod = new HttpPost("http://gsxt.zjaic.gov.cn/search/doGetAppSearchResultIn.do");

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60 * 1000).setConnectTimeout(60 * 1000)
				.build();
		postMethod.setConfig(requestConfig);

		postMethod.setHeader("Referer", "http://gsxt.zjaic.gov.cn/search/doGetAppSearchResultIn.do");
		postMethod.setHeader("Accept", "textml,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		postMethod.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		postMethod.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0");

		postMethod.setHeader("Host", "gsxt.zjaic.gov.cn");
		postMethod.setHeader("Connection", "keep-alive");
		postMethod.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", companyName));
		params.add(new BasicNameValuePair("verifyCode", authCode));

		HttpContext context = new BasicHttpContext();
		CookieStore cookieStore = new BasicCookieStore();
		context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
		BasicClientCookie jsessionCookie = new BasicClientCookie("JSESSIONID", jsessionid);
		jsessionCookie.setDomain("gsxt.zjaic.gov.cn");
		jsessionCookie.setPath("/");
		cookieStore.addCookie(jsessionCookie);
		final String html;
		Map<String, Object> return_map = new HashMap<String, Object>();
		try {
			postMethod.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			HttpResponse response = httpClient.execute(postMethod, context);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				html = EntityUtils.toString(response.getEntity());
				return_map = paseCompanyHtml(html);
			}
			// 如果验证码验证失败重新获取验证码
			if (return_map.get("validate_error") != null) {
				return getAuthCode(companyName, sessionId);
			}
			return return_map;
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * 
	 * 获取验证码
	 * @throws Exception 
	 * 
	 */
	public Map<String, Object> getAuthCode(String companyName, String jsessionid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		CloseableHttpClient httpClient = HttpClientUtil.getHttpclient();

		HttpGet getMethod = new HttpGet("http://gsxt.zjaic.gov.cn/common/captcha/doReadKaptcha.do");
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60 * 1000).setConnectTimeout(60 * 1000)
				.build();

		getMethod.setConfig(requestConfig);

		getMethod.setHeader("Referer",
				"http://gsxt.zjaic.gov.cn/appbasicinfo/doViewAppBasicInfo.do?corpid=FA2F7F94ADE0BEFE5A685A5358CE21DAB51C4B8DFB0EADE299B9AE470E367B72");

		getMethod.setHeader("Accept", "text/html, application/xhtml+xml, */*");
		getMethod.setHeader("Accept-Language", "zh-CN");
		getMethod.setHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)");
		getMethod.setHeader("Host", "gsxt.zjaic.gov.cn");
		// postMethod.addRequestHeader("Accept-Encoding", "gzip, deflate");

		getMethod.setHeader("Connection", "Keep-Alive");
		getMethod.setHeader("DNT", "1");

		HttpContext context = new BasicHttpContext();
		CookieStore cookieStore = new BasicCookieStore();
		context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
		BasicClientCookie jsessionCookie = new BasicClientCookie("JSESSIONID", jsessionid);
		jsessionCookie.setDomain("gsxt.zjaic.gov.cn");
		jsessionCookie.setPath("/");
		cookieStore.addCookie(jsessionCookie);

		try {
			HttpResponse response = httpClient.execute(getMethod, context);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				map.put("image_code", EntityUtils.toByteArray(response.getEntity()));
				map.put("code", "0");
			}
		} catch (IOException e1) {
			map.put("code", "10");
			map.put("message", "数据流异常！");
			e1.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		map.put("jsessionid", jsessionid);
		map.put("companyName", companyName);
		return map;
	}

	/**
	 * 解析返回公司列表的HTML信息
	 * 
	 * @param html
	 * @return
	 */
	public Map<String, Object> paseCompanyHtml(final String html) {
		Map<String, Object> map = new HashMap<String, Object>();
		Document doc = Jsoup.parse(html);
		Element validate = doc.getElementById("codeValidator");
		if (validate == null) {
			return map;
		}
		// 判断验证码是否正确
		if (validate.text() != null && !"".equals(validate.text().trim())) {
			map.put("validate_error", "验证码错误!");
			return map;
		}
		Elements elements = doc.getElementsByClass("entLink");
		List<Map<String, Object>> dateList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < elements.size(); i++) {
			Map<String, Object> datemap = new HashMap<String, Object>();
			Element aElement = elements.get(i);
			String href = aElement.attr("href").trim();
			String name = aElement.attr("entName").trim();
			datemap.put("name", name);
			if (href != null && href.indexOf("corpid") != -1) {
				String id = href.substring(href.indexOf("corpid") + 7, href.length());
				datemap.put("id", id);
			}
			dateList.add(datemap);
		}
		map.put("dateList", dateList);
		return map;

	}

	public static void main(String[] arg) throws Exception {
		Enterprisecaptruer test = new Enterprisecaptruer();
		test.getregisterInfo("D724B4681801DFBAFDB0D25107215D638D03692CBD83C2D724E836FCAADC88BE");
		// String title="你,w哦";
		// String[] tltle_error = title.split(",");
		// HashMap map1 = new HashMap();
		// for (int fz_size = 0; fz_size < tltle_error.length; fz_size++) {
		// System.out.println(tltle_error[fz_size]);
		//
		// }
		// Map<String, Object> map = test.findCompanyByName("浙江电力报","","");
		// String jsessionId = (String)map.get("jsessionid");
		// Scanner sca = new Scanner(System.in);
		// test.findCompanyByName("浙江电力报",sca.nextLine(),jsessionId);
		//
		// YXNkZmRzZmRzZmRzZg==
		// String html =
		// "<dl class=\"list\" style=\"height:
		// 360px;\">sdf<dl>fd</dl>sdfsreweruworuweoirwsdfsdf.smfsdfsdfipsdfisdfipiopsdf<dl>";
		// String
		// regex="(^<dl class=\"list\" style=\"height: 360px;\">)(.*)<dl>";
		// Pattern p=Pattern.compile(regex);
		// Matcher m=p.matcher(html);
		// while(m.find()){
		// System.out.println(m.group());
		// }

	}
}
