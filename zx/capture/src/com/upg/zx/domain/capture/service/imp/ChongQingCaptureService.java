package com.upg.zx.domain.capture.service.imp;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upg.zx.capture.bean.CorpBase;
import com.upg.zx.capture.util.HttpClientUtil;
import com.upg.zx.domain.capture.bean.RamCache;
import com.upg.zx.domain.capture.bean.Token;
import com.upg.zx.domain.capture.exception.CaptureException;
import com.upg.zx.domain.entity.AnalysisTemplate;
import com.upg.zx.domain.entity.RequestHead;
import com.upg.zx.domain.entity.RequestInfo;
import com.upg.zx.domain.response.CorpBaseRes;
import com.upg.zx.web.utils.JsoupUtil;
import com.upg.zx.web.utils.StringUtil;

/**
 * 重庆工商网抓取实现类
 * 
 * @author yangtz
 * 
 */
public class ChongQingCaptureService extends CaptureServiceImp {
	
	private ObjectMapper objectMapper;
	
	
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	protected boolean judgeCode(String html) {
		return !JsoupUtil.isExistCss("alert-error", html);
	}

	@Override
	protected void setOtherParamFromValidateCode(Map<String, String> map) {
		map.put("stype", "");
		map.remove("t");
	}

	protected Map<String, String> getAuthCodeParams(Map<String, String> map) {
		return map;
	}

	protected Map<String, String> getAuthCodeParams(Map<String, String> map,
			String dateTime) {
		Map<String, String> newPap = new HashMap<String, String>();
		newPap.put("t", new Date().getTime() + "");

		return newPap;
	}

	@Override
	public List<CorpBase> paseHtmlToList(String html) {
 
		return null;
	}

	/**
	 * 榨取数据列表
	 * 
	 * @param companyName
	 * @param jsessionid
	 * @throws Exception 
	 */
	public CorpBaseRes captureCompany(String companyName, String authCode,
			String jsessionid) throws Exception {
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
			return corpBaseRes;
		} catch (CaptureException e) {
			// 验证码错误或token不存在，直接返回验证码
			if ("000001".equals(e.getErrorCode())
					|| "000002".equals(e.getErrorCode())) {
				String sessionId = getJsessionId(companyName);
				return getAuthCode(companyName, sessionId,
						Long.valueOf(new Date().getTime()).toString());
			}
		}
		return corpBaseRes;
	}

	/**
	 * 第一次获取验证码(重庆)
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
		List<Cookie> cookies = new ArrayList<Cookie>();
		try {
			imageByte = (byte[]) this.request(
					RamCache.requestMap.get(this.getAreaCode() + "_"
							+ this.AUTHCODE), map, this.BYTE_RESULT,
					this.getEncode(), cookies);
			token.setCookies(cookies);
		} catch (HttpException e) {
			throw new CaptureException(e.getMessage(), "http请求异常!");
		} catch (IOException e) {
			throw new CaptureException(e.getMessage(), "IO异常!");
		}
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
	 * 第二次获取验证码(重庆当错误或刷新页码时候)
	 * 
	 * @param companyName
	 * @param sessionId
	 * @return
	 * @throws Exception 
	 */
	protected CorpBaseRes getAuthCode(String companyName, String sessionId,
			String dateTime) throws Exception {
		Token token = tokenRegistry.getToken(sessionId);
		// token不存在或已过期
		if (token == null || token.isExpired()) {
			throw new CaptureException("token不存在", "请求验证码超时!");
		}
		byte[] imageByte = null;
		Map<String, String> map = getAuthCodeParams(token.getParam(), dateTime);
		List<Cookie> cookies = new ArrayList<Cookie>();
		try {
			imageByte = (byte[]) this.request(
					RamCache.requestMap.get(this.getAreaCode() + "_"
							+ this.AUTHCODE), map, this.BYTE_RESULT,
					this.getEncode(), cookies);
			token.setCookies(cookies);
		} catch (HttpException e) {
			throw new CaptureException(e.getMessage(), "http请求异常!");
		} catch (IOException e) {
			throw new CaptureException(e.getMessage(), "IO异常!");
		}
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
	 * 发起get请求
	 * 
	 * @param requestInfo
	 * @param param
	 * @param resultType
	 * @param encode
	 * @return
	 * @throws Exception 
	 */
	protected Object getMethodRequest(RequestInfo requestInfo,
			Map<String, String> param, String resultType, String encode,
			List<Cookie> cookies) throws Exception {
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
						getUrl = getUrl.replace("#" + entry.getKey(),
								URLEncoder.encode(entry.getValue(), encode));
						continue;
					}

					if (getUrl.indexOf("?") == -1) {
						getUrl += "?" + entry.getKey().trim() + "="
								+ URLEncoder.encode(entry.getValue(), encode);
					} else {
						getUrl += "&" + entry.getKey().trim() + "="
								+ URLEncoder.encode(entry.getValue(), encode);
					}
				}
			}
			HttpGet httpGet = new HttpGet(getUrl);
			httpGet.setConfig(RequestConfig.custom()
					.setSocketTimeout(SOCKET_TIME_OUT)
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
				List<Cookie> cookies_rs = cookieStore.getCookies();
				if (cookies_rs != null && cookies_rs.size() > 0) {
					for (Cookie cookie : cookies_rs) {
						cookies.add(cookie);
					}
				}
				return EntityUtils.toByteArray(response.getEntity());

			} else {
				throw new CaptureException(response.getStatusLine()
						.getStatusCode() + "", "", "请求" + getUrl + "失败!");
			}
		} finally {
			httpClient.close();
		}
	}
 
	@Override
	protected Map<String, String> getRequestParam(String corpId,RequestInfo requestinfo){
		String params = requestinfo.getParams();
		Map<String,String> corpIdMap = StringUtil.parseMap(corpId, ",", "=");
		Map<String,String>params_map = StringUtil.parseMap(params, ",", "=");
		if(params != null && !"".equals(params)){	
			for (Map.Entry<String, String> entry : params_map.entrySet()){
				String val = entry.getValue();
				String key = entry.getKey();
				if(val.indexOf("#") != -1){
					String corpKey = val.substring(val.indexOf("#")+1, val.length());
					if(corpIdMap.get(corpKey) != null){
						entry.setValue(corpIdMap.get(corpKey));
					}
				}
			} 
		}
		return params_map;
	}
	
	/**
	 * 获取指定的信息数据
	 */
	public String getSpecifiedInfo(String html,AnalysisTemplate analysisTemplate){
		if(html.length()>0&&html.substring(0, 7).indexOf(")]}',")>-1)
			html=html.substring(5, html.length());
		//基本做特殊获取
		if("1".equals(analysisTemplate.getType())){
			try {
				Map res_map = objectMapper.readValue(html, Map.class);
				if(res_map.get("base") != null){
					Map<String,Object>baseMap = (Map<String,Object>)res_map.get("base");
					baseMap.put("currency_code", "万元人民币");
					if(baseMap.get("regcap") != null){
						baseMap.put("regcap", baseMap.get("regcap")+"");
					}
					return objectMapper.writeValueAsString(res_map.get("base"));
				}
			} catch (Exception e) {
				throw new CaptureException(e.getMessage(), "基本信息解析异常!");
			}  
		}
		return html;
	}
	

}
