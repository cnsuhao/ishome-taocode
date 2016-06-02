package com.upg.zx.domain.capture.service.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpException;
import org.apache.http.cookie.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upg.zx.capture.bean.CorpBase;
import com.upg.zx.domain.capture.bean.RamCache;
import com.upg.zx.domain.capture.bean.RequestType;
import com.upg.zx.domain.capture.bean.Token;
import com.upg.zx.domain.capture.exception.CaptureException;
import com.upg.zx.domain.entity.AnalysisTemplate;
 
import com.upg.zx.domain.entity.RequestInfo;
import com.upg.zx.domain.response.CorpBaseRes;
import com.upg.zx.web.utils.JsoupUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 海南工商网抓取实现类
 * 
 * @author lujf
 * 
 */
public class HaiNanCaptureService extends CaptureServiceImp {

	private ObjectMapper objectMapper;

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	protected Map<String, String> setGetJsessionIdMap(Map<String, String> map) {
		return new HashMap<String, String>();
	}

	@Override
	protected boolean judgeCode(String html) {
		if ("1".equals(html)) {
			return true;
		}
		return false;
	}

	@Override
	protected CorpBaseRes validateAuthCode(String companyName, String authCode,
			String sessionId) throws Exception {
		Token token = tokenRegistry.getToken(sessionId);
		// token不存在或已过期
		if (token == null || token.isExpired()) {
			throw new CaptureException("000001", "token不存在", "请求验证码超时!");
		}

		CorpBaseRes corpBaseRes = new CorpBaseRes();
		Map<String, String> map = token.getParam();
		map.put(this.getAuthCodeParam(), authCode);
		try {
			// 提交验证码
			final String validate_rs = (String) this.request(
					RamCache.requestMap.get(this.getAreaCode() + "_" + "-2"),
					map, this.STRING_RESULT, this.getEncode(),
					token.getCookies());

			Map<String, String> returnMap = objectMapper.readValue(validate_rs,
					Map.class);

			// 判断验证码是否正确
			if (!judgeCode(returnMap.get("flag"))) {
				throw new CaptureException("000002", "验证码错误!", "验证码错误!");
			}
			//获取加密后的查询信息
			map.put(this.companyNameParam, returnMap.get("textfield"));

			final String html = (String) this.request(
					RamCache.requestMap.get(this.getAreaCode() + "_"
							+ this.LIST_MODE), map, this.STRING_RESULT,
					this.getEncode(), token.getCookies());

			corpBaseRes.setHtml(html);
			corpBaseRes.setType("list");
			corpBaseRes.setCorp_name(companyName);

		} catch (HttpException e) {
			throw new CaptureException(e.getMessage(), "http请求异常!");
		} catch (IOException e) {
			throw new CaptureException(e.getMessage(), "IO异常!");
		}

		return corpBaseRes;
	}

	@Override
	public List<CorpBase> paseHtmlToList(String html) {
		List<CorpBase> list = JsoupUtil.parseHtmlA("service",
				".GSpublicityList.html\\?service=.", html);
		return list;
	}

	@Override
	protected Map<String, String> getRequestParam(String corpId,
			RequestInfo requestinfo) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		Token token = tokenRegistry.getToken(corpId);
		// 基本信息请求
		if (BASE_INFO.equals(requestinfo.getModeType())) {
			return super.getRequestParam(corpId, requestinfo);
		}

		setGdRequestParam(map, corpId, requestinfo);
		
		return map;
	}

	/**
	 * 参数设置
	 * 
	 * @param map
	 * @param corpId
	 */
	protected void setGdRequestParam(Map<String, String> map, String corpId,
			RequestInfo requestinfo) {
		// 设置请求其他信息的参数
		Token token = tokenRegistry.getToken(corpId);
		if (token != null && token.getParam() != null) {
			map.put("entNo", token.getParam().get("entNo"));
			map.put("regOrg", token.getParam().get("regOrg"));
			map.put("pageNo", "1");
			if ("6".equals(requestinfo.getModeType())) {
				map.put("entType", token.getParam().get("entType"));
			}
		}
	}

	 

	@Override
	public List<Cookie> getRequestCookie(String corpId) {
		Token token = tokenRegistry.getToken(corpId);
		if (token != null) {
			return token.getCookies();
		}
		return new ArrayList<Cookie>();
	}
	 
	
	/**
	 * 获取参数
	 * @param html
	 * @param requestInfo
	 * @return
	 */
	public Map<String,String> getGdRequestParam(String html){
		Map<String,String>map = new HashMap<String,String>();
		String entNo = JsoupUtil.getValById("entNo", html);
		String entType = JsoupUtil.getValById("entType", html);
		String regOrg = JsoupUtil.getValById("regOrg", html);
		map.put("entNo", entNo);
		map.put("entType", entType);
		map.put("regOrg", regOrg);
		return map;
	}
	
	 

	/**
	 * 获取详细信息
	 * @throws Exception 
	 * 
	 * @throws IOException
	 * @throws HttpException
	 */
	public String getCompanyBaseInfo(String corpId, RequestInfo requestInfo,
			String templateHtml) throws Exception {
		List<Cookie> cookies = getRequestCookie(corpId);
		Map<String, String> param = getRequestParam(corpId, requestInfo);
		String encode = this.getEncode();
		String html = "";
		try {
			html = (String) this.request(requestInfo, param,
					this.COOKIE_RESULT, encode, cookies);
			// 广东基本信息获取请求参数
			if (BASE_INFO.equals(requestInfo.getModeType())) {
				Token token = tokenFactory.generateToken(null, corpId, getGdRequestParam(html));
				tokenRegistry.addToken(token);
			}
		} catch (HttpException e) {
			throw new CaptureException(e.getMessage(), "http请求异常");
		} catch (IOException e) {
			throw new CaptureException(e.getMessage(), "IO异常");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return html;
	}
}
