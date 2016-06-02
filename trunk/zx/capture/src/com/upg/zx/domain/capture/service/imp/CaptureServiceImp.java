package com.upg.zx.domain.capture.service.imp;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
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
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.upg.zx.capture.util.HttpClientUtil;
import com.upg.zx.domain.capture.bean.RamCache;
import com.upg.zx.domain.capture.bean.RequestType;
import com.upg.zx.domain.capture.bean.Token;
import com.upg.zx.domain.capture.exception.CaptureException;
import com.upg.zx.domain.capture.service.CaptureService;
import com.upg.zx.domain.capture.token.TokenRegistry;
import com.upg.zx.domain.capture.token.imp.TokenFactory;
import com.upg.zx.domain.entity.AnalysisTemplate;
import com.upg.zx.domain.entity.RequestHead;
import com.upg.zx.domain.entity.RequestInfo;
import com.upg.zx.domain.response.CorpBaseRes;
import com.upg.zx.web.utils.StringUtil;

/**
 * 数据抓取基础类
 * 
 * @author lujf
 * 
 */
public abstract class CaptureServiceImp implements CaptureService {

	protected TokenRegistry tokenRegistry;

	protected TokenFactory tokenFactory;
	// cookie参数
	public final static String COOK_PARAM = "1";
	// 头部参数
	public final static String HEAD_PARAM = "0";
	// 返回类型是STRING
	public final static String STRING_RESULT = "String";
	// 返回类型是BYTE
	public final static String BYTE_RESULT = "Byte";
	// 获取cookieID
	public final static String COOKIE_RESULT = "Cookie";

	// 请求sessionID
	public final String FIRST_MODE = "1";
	// 列表
	public final String LIST_MODE = "3";
	// 验证码
	public final String AUTHCODE = "2";

	public final String BASE_INFO = "4";
	// socket超时时间
	public final int SOCKET_TIME_OUT = 20 * 1000;
	// 连接超时时间
	public final int CONNECT_TIME_OUT = 8 * 1000;
	// 区域码
	protected String areaCode;
	// 编码
	protected String encode;
	// 公司名称参数
	protected String companyNameParam;

	protected String authCodeParam;

	protected int value = -1, port = 0;
	protected String ipAddress = "";

	public void setTokenRegistry(TokenRegistry tokenRegistry) {
		this.tokenRegistry = tokenRegistry;
	}

	public void setTokenFactory(TokenFactory tokenFactory) {
		this.tokenFactory = tokenFactory;
	}

	public String getAuthCodeParam() {
		return authCodeParam;
	}

	public void setAuthCodeParam(String authCodeParam) {
		this.authCodeParam = authCodeParam;
	}

	public String getCompanyNameParam() {
		return companyNameParam;
	}

	public void setCompanyNameParam(String companyNameParam) {
		this.companyNameParam = companyNameParam;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public String getEncode() {
		return encode;
	}

	/**
	 * 匹配区域码
	 */
	public boolean supports(String areaCode) {
		if (this.areaCode.equals(areaCode)) {
			return true;
		}
		return false;
	}

	/**
	 * 设置获取sessionID时候设置参数
	 * 
	 * @param map
	 * @return
	 */
	protected Map<String, String> setGetJsessionIdMap(Map<String, String> map) {
		return map;
	}

	/**
	 * 获取指定的信息数据
	 */
	public String getSpecifiedInfo(String html, AnalysisTemplate analysisTemplate) {
		return html;
	}

	/**
	 * 请求数据
	 * 
	 * @param requestInfo
	 * @param requestHeadls
	 * @param param
	 * @param resultType
	 * @return
	 * @throws Exception 
	 */
	protected Object request(RequestInfo requestInfo, Map<String, String> param, String resultType, String encode,
			List<Cookie> cookies) throws Exception {
		if (RequestType.get.toString().equals(requestInfo.getRequestType())) {
			return getMethodRequest(requestInfo, param, resultType, encode, cookies);
		}
		return postMethodRequest(requestInfo, param, resultType, encode, cookies);
	}

	/**
	 * 获取jsessionid时的参数
	 * 
	 * @param map
	 * @param html
	 * @return
	 */
	protected void getJsessionIdMap(Map<String, String> map, String html) {

	}

	/**
	 * 设置验证压制码时需要传递的参数
	 * 
	 * @param map
	 */
	protected void setOtherParamFromValidateCode(Map<String, String> map) {

	}

	/**
	 * 获取验证码设置参数
	 * 
	 * @param map
	 * @return
	 */
	protected Map<String, String> getAuthCodeParams(Map<String, String> map) {
		return null;
	}

	/**
	 * 判断验证码验证是否通过
	 * 
	 * @param html
	 * @return
	 */
	abstract protected boolean judgeCode(String html);

	/**
	 * 获取sessionId
	 * 
	 * @param companyName
	 * @return
	 * @throws Exception 
	 */
	protected String getJsessionId(String companyName) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put(this.getCompanyNameParam(), companyName);
		final String jessionId;
		List<Cookie> cookies = new ArrayList();
		;
		try {
			String html = (String) this.request(RamCache.requestMap.get(this.getAreaCode() + "_" + this.FIRST_MODE),
					map, this.COOKIE_RESULT, this.getEncode(), cookies);
			// 从html中获取需要的参数
			getJsessionIdMap(map, html);
			Token token = tokenFactory.generateToken(cookies, this.getSessionIdByCookie(cookies), map);
			tokenRegistry.addToken(token);
			jessionId = this.getSessionIdByCookie(cookies);
		} catch (HttpException e) {
			throw new CaptureException(e.getMessage(), "http请求异常!");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CaptureException(e.getMessage(), "IO异常!");
		}
		return jessionId;
	}

	/**
	 * 获取验证码
	 * 
	 * @param companyName
	 * @param sessionId
	 * @return
	 * @throws Exception 
	 */
	protected CorpBaseRes getAuthCode(String companyName, String sessionId) throws Exception {
		Token token = tokenRegistry.getToken(sessionId);
		// token不存在或已过期
		if (token == null || token.isExpired()) {
			throw new CaptureException("token不存在", "请求验证码超时!");
		}
		byte[] imageByte = null;
		Map<String, String> map = getAuthCodeParams(token.getParam());
		try {
			imageByte = (byte[]) this.request(RamCache.requestMap.get(this.getAreaCode() + "_" + this.AUTHCODE), map,
					this.BYTE_RESULT, this.getEncode(), token.getCookies());
		} catch (HttpException e) {
			throw new CaptureException(e.getMessage(), "http请求异常!");
		} catch (IOException e) {
			throw new CaptureException(e.getMessage(), "IO异常!");
		}
		// byte[] data = imageByte;
		// FileImageOutputStream imageOutput;
		// try {
		// imageOutput = new FileImageOutputStream(new File("F:/a.jpeg"));
		// imageOutput.write(data, 0, data.length);
		// imageOutput.close();
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		CorpBaseRes corpBaseRes = new CorpBaseRes();
		corpBaseRes.setBitmap(imageByte);
		corpBaseRes.setType("bitmap");
		corpBaseRes.setSessionId(sessionId);
		corpBaseRes.setCorp_name(companyName);
		if (imageByte != null && imageByte.length > 0) {
			corpBaseRes.setBitmapBate64(Base64.encodeBase64String(imageByte));
		}
		return corpBaseRes;
	}

	/**
	 * 验证验证码
	 * 
	 * @param companyName
	 * @param authCode
	 * @param sessionId
	 * @return
	 * @throws Exception 
	 */
	protected CorpBaseRes validateAuthCode(String companyName, String authCode, String sessionId) throws Exception {
		Token token = tokenRegistry.getToken(sessionId);
		// token不存在或已过期
		if (token == null || token.isExpired()) {
			throw new CaptureException("000001", "token不存在", "请求验证码超时!");
		}

		CorpBaseRes corpBaseRes = new CorpBaseRes();
		Map<String, String> map = token.getParam();
		map.put(this.getAuthCodeParam(), authCode);

		setOtherParamFromValidateCode(map);

		try {
			final String html = (String) this.request(
					RamCache.requestMap.get(this.getAreaCode() + "_" + this.LIST_MODE), map, this.STRING_RESULT,
					this.getEncode(), token.getCookies());
			// 判断是否存在数据节点,不存在说明验证码错误，页面进行了跳转

			if (!judgeCode(html)) {
				throw new CaptureException("000002", "验证码错误!", "验证码错误!");
			}

			// List<CorpBase> list= paseHtmlToList(html);
			corpBaseRes.setType("list");
			corpBaseRes.setCorp_name(companyName);
			corpBaseRes.setHtml(html);

		} catch (HttpException e) {
			throw new CaptureException(e.getMessage(), "http请求异常!");
		} catch (IOException e) {
			throw new CaptureException(e.getMessage(), "IO异常!");
		}

		return corpBaseRes;
	}

	/**
	 * 详细信息参数设置
	 * 
	 * @return
	 * @throws Exception 
	 */
	protected Map<String, String> getRequestParam(String corpId, RequestInfo requestinfo) throws Exception {
		Map<String, String> param = new HashMap<String, String>();
		param.put("companyId", corpId);
		return param;
	}

	/**
	 * 验证查询企业
	 * 
	 * @param companyName
	 * @param jsessionid
	 * @return
	 * @throws Exception 
	 */
	public boolean checkWord(String companyName, String jsessionid) throws Exception {
		return true;
	}

	/**
	 * 榨取数据列表
	 * 
	 * @param companyName
	 * @param jsessionid
	 * @throws Exception 
	 */
	public CorpBaseRes captureCompany(String companyName, String authCode, String jsessionid) throws Exception {
		// 获取验证码
		if (authCode == null || "".equals(authCode)) {
			String sessionId = getJsessionId(companyName);
			// 验证查询内容
			if (checkWord(companyName, sessionId)) {
				return this.getAuthCode(companyName, sessionId);
			}
		}
		CorpBaseRes corpBaseRes = null;
		try {
			// 验证验证码(验证码通过返回列表，否则返回验证码)
			corpBaseRes = validateAuthCode(companyName, authCode, jsessionid);
			tokenRegistry.deleteToken(jsessionid);
			return corpBaseRes;
		} catch (CaptureException e) {
			// 验证码错误或token不存在，直接返回验证码
			if ("000001".equals(e.getErrorCode()) || "000002".equals(e.getErrorCode())) {
				return getAuthCode(companyName, jsessionid);
			} else {
				throw e;
			}
		}
	}

	/**
	 * 获取详细信息
	 * @throws Exception 
	 * 
	 * @throws IOException
	 * @throws HttpException
	 */
	public String getCompanyBaseInfo(String corpId, RequestInfo requestInfo, String templateHtml) throws Exception {
		Map<String, String> param = getRequestParam(corpId, requestInfo);
		String encode = this.getEncode();
		List<Cookie> cookies = getRequestCookie(corpId);
		String html = "";

		try {
			html = (String) this.request(requestInfo, param, this.COOKIE_RESULT, encode, cookies);
		} catch (HttpException e) {
			throw new CaptureException(e.getMessage(), "http请求异常");
		} catch (IOException e) {
			throw new CaptureException(e.getMessage(), "IO异常");
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("getCompanyBaseInfo, other exception CaptureException.");
		}
		return html;
	}

	/**
	 * 获取请求的cookie
	 * 
	 * @return
	 */
	public List<Cookie> getRequestCookie(String corpId) {
		return new ArrayList<Cookie>();
	}

	/**
	 * 发起get请求
	 * 
	 * @param requestInfo
	 * @param param
	 * @param resultType
	 * @param encode
	 * @return
	 * @throws Exception 
	 */
	protected Object getMethodRequest(RequestInfo requestInfo, Map<String, String> param, String resultType,
			String encode, List<Cookie> cookies) throws Exception {
		System.out.println("=====>>>>>" + requestInfo.getRurl());
		CloseableHttpClient httpClient = HttpClientUtil.getHttpclient(requestInfo.getRurl());
		try {
			String getUrl = requestInfo.getRurl();
			// 设置参数
			if (param != null && param.size() > 0) {
				for (Map.Entry<String, String> entry : param.entrySet()) {
					// cookie中的参数
					if (entry.getKey().indexOf("#") != -1) {
						continue;
					}
					// 判断url中是否带有#号的参数

					if (getUrl.indexOf("#" + entry.getKey()) != -1) {
						getUrl = getUrl.replace("#" + entry.getKey(), URLEncoder.encode(entry.getValue(), encode));
						continue;
					}

					if (getUrl.indexOf("?") == -1) {
						getUrl += "?" + entry.getKey().trim() + "=" + URLEncoder.encode(entry.getValue(), encode);
					} else {
						getUrl += "&" + entry.getKey().trim() + "=" + URLEncoder.encode(entry.getValue(), encode);
					}
				}
			}
			HttpGet httpGet = new HttpGet(getUrl);
			httpGet.setConfig(RequestConfig.custom().setSocketTimeout(SOCKET_TIME_OUT)
					.setConnectTimeout(CONNECT_TIME_OUT).build());
			HttpContext context = new BasicHttpContext();
			CookieStore cookieStore = new BasicCookieStore();
			context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
			// 设置头部信息
			List<RequestHead> requestHeadls = requestInfo.getHeadList();

			setHead(context, cookies, requestHeadls, param, httpGet);

			HttpResponse response = httpClient.execute(httpGet, context);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				if (COOKIE_RESULT.equals(resultType)) {
					List<Cookie> cookies_rs = cookieStore.getCookies();
					if (cookies_rs != null && cookies_rs.size() > 0) {
						for (Cookie cookie : cookies_rs) {
							cookies.add(cookie);
						}
					}
					return EntityUtils.toString(response.getEntity(), encode);
				}

				if (STRING_RESULT.equals(resultType)) {
					return EntityUtils.toString(response.getEntity(), encode);
				}

				return EntityUtils.toByteArray(response.getEntity());

			} else {
				throw new CaptureException(response.getStatusLine().getStatusCode() + " ---访问---  ", "",
						"请求" + getUrl + "失败!");
			}
		} finally {
			httpClient.close();
		}

	}

	/**
	 * 发起post请求
	 * 
	 * @param requestInfo
	 * @param param
	 * @param resultType
	 * @param encode
	 * @return
	 * @throws Exception 
	 */
	protected Object postMethodRequest(RequestInfo requestInfo, Map<String, String> param, String resultType,
			String encode, List<Cookie> cookies) throws Exception {
		CloseableHttpClient httpClient = HttpClientUtil.getHttpclient(requestInfo.getRurl());
		try {
			String postUrl = requestInfo.getRurl();

			List<String> removeParamKey = new ArrayList<String>();
			// 设置url带参数的请求
			if (postUrl.indexOf("#") != -1) {
				if (param != null && param.size() > 0) {
					for (Map.Entry<String, String> entry : param.entrySet()) {
						// 判断url中是否带有#号的参数
						if (!"".equals(entry.getKey()) && postUrl.indexOf("#" + entry.getKey()) != -1) {
							postUrl = postUrl.replace("#" + entry.getKey(),
									URLEncoder.encode(entry.getValue(), encode));
							removeParamKey.add(entry.getKey());
							continue;
						}
					}
				}
			}

			for (String remove_key : removeParamKey) {
				param.remove(remove_key);
			}

			HttpPost httpPost = new HttpPost(postUrl);
			httpPost.setConfig(RequestConfig.custom().setSocketTimeout(SOCKET_TIME_OUT)
					.setConnectTimeout(CONNECT_TIME_OUT).build());
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// 设置参数
			if (param != null && param.size() > 0) {
				for (Map.Entry<String, String> entry : param.entrySet()) {
					// 带#号的key是cookie与head中的值
					if (entry.getKey().indexOf("#") != -1) {
						continue;
					}
					params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			HttpContext context = new BasicHttpContext();
			CookieStore cookieStore = new BasicCookieStore();
			context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
			// 设置头部信息
			List<RequestHead> requestHeadls = requestInfo.getHeadList();
			if (requestHeadls != null && requestHeadls.size() > 0) {
				setHead(context, cookies, requestHeadls, param, httpPost);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(params, encode));
			HttpResponse response = httpClient.execute(httpPost, context);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				if (COOKIE_RESULT.equals(resultType)) {
					List<Cookie> cookies_rs = cookieStore.getCookies();
					if (cookies_rs != null && cookies_rs.size() > 0) {
						for (Cookie cookie : cookies_rs) {
							cookies.add(cookie);
						}
					}
					return EntityUtils.toString(response.getEntity(), encode);
				}

				if (STRING_RESULT.equals(resultType)) {
					return EntityUtils.toString(response.getEntity(), encode);
				}

				return EntityUtils.toByteArray(response.getEntity());
			} else {
				throw new CaptureException(response.getStatusLine().getStatusCode() + "", "", "请求" + postUrl + "失败!");
			}
		} finally {
			httpClient.close();
		}
	}

	/**
	 * 设置头部信息
	 * 
	 * @param httpMehtodBase
	 * @param requestHeadls
	 * @param requestInfo
	 * @param httpClient
	 */
	protected void setHead(HttpContext context, List<Cookie> cookies, List<RequestHead> requestHeadls,
			Map<String, String> map, HttpRequest request) {
		RequestHead requestHead = null;
		String val = "";
		if (requestHeadls != null && requestHeadls.size() > 0) {
			for (int j = 0; j < requestHeadls.size(); j++) {
				requestHead = requestHeadls.get(j);
				val = requestHead.getVal();
				// cookie的参数不设置
				if ("1".equals(requestHead.getType())) {
					continue;
				}
				// 判断是否含有输入参数
				if (val.indexOf("#") != -1) {
					// 如果是http带的参数
					if (val.indexOf("http") != -1 || val.indexOf("https") != -1) {
						String[] params = StringUtil.getUrlParamVal(val);
						for (String param : params) {
							val = val.replace(param, map.get(param) == null ? "" : map.get(param));
						}
					} else {
						// 如果是单个参数
						val = map.get(val) == null ? "" : map.get(val);
					}
				}

				request.setHeader(requestHead.getHeadName(), val);

			}
		}

		// 设置cookie
		if (cookies != null && cookies.size() > 0) {
			CookieStore cookieStore = new BasicCookieStore();
			context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
			for (Cookie cookie : cookies) {
				cookieStore.addCookie(cookie);
			}
		}
	}

	/**
	 * 从cookie中获取sessionId
	 * 
	 * @param cookies
	 * @return
	 */
	protected String getSessionIdByCookie(final List<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			if ("JSESSIONID".equals(cookie.getName().toUpperCase())) {
				return cookie.getValue();
			}
		}
		return "";
	}

}
