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
 * 广东工商网抓取实现类
 * 
 * @author lujf
 * 
 */
public class GuangDongCaptureService extends CaptureServiceImp {

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
		List<CorpBase> list = new ArrayList<CorpBase>();
		// 广东
		List<CorpBase> gDlist = JsoupUtil.parseHtmlA("service",
				".GSpublicityList.html\\?service=.", html);
		for (CorpBase gdbase : gDlist) {
			gdbase.setZhejiang_id("0");
			list.add(gdbase);
		}
		// 广州
		List<CorpBase> gZlist = JsoupUtil.parseHtmlA("entityVo.pripid",
				".search!entityShow\\?entityVo\\.pripid=.", html);

		for (CorpBase gzbase : gZlist) {
			gzbase.setZhejiang_id("1");
			list.add(gzbase);
		}
		// 深圳
		List<CorpBase> sZlist = JsoupUtil.parseHtmlA("rid",
				".QyxyDetail.aspx\\?rid=.", html);

		for (CorpBase szbase : sZlist) {
			szbase.setZhejiang_id("2");
			list.add(szbase);
		}

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
		if ("0".equals(requestinfo.getSubId())) {
			setGdRequestParam(map, corpId, requestinfo);
		} else if ("2".equals(requestinfo.getSubId())) {
			setSzRequestParam(map, corpId, requestinfo);
		} else {
			map.put("companyId", corpId);
			map.put("entityVo.curPage", "1");
		}
		return map;
	}

	/**
	 * 广东参数设置
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

	/**
	 * 深圳参数设置
	 * 
	 * @param map
	 * @param corpId
	 * @param requestinfo
	 */
	protected void setSzRequestParam(Map<String, String> map, String corpId,
			RequestInfo requestinfo) {
		Token token = tokenRegistry.getToken(corpId);
		if (token != null && token.getParam() != null) {
			map.put("companyId", corpId);
			map.put("ScriptManager1", token.getParam().get("ScriptManager1"));
			map.put("__EVENTTARGET", token.getParam().get("__EVENTTARGET"));

			map.put("__EVENTARGUMENT", token.getParam().get("__EVENTARGUMENT"));
			map.put("__VIEWSTATE", token.getParam().get("__VIEWSTATE"));

			map.put("__VIEWSTATEGENERATOR",
					token.getParam().get("__VIEWSTATEGENERATOR"));
			map.put("__ASYNCPOST", token.getParam().get("__ASYNCPOST"));
			tokenRegistry.deleteToken(token.getJesssionId());
		}
	}

	@Override
	public String getSpecifiedInfo(String html,
			AnalysisTemplate analysisTemplate) {

		if (html.indexOf("CDATA_CONNECT") == -1) {
			return html;
		}
		
		String[] array_str = html.split("CDATA_CONNECT");
		List  list = new ArrayList();
		Map<String, Object> col_map = new HashMap<String,Object>();
		try {
			for (int i = 0; i < array_str.length; i++) {
				Map<String, Object> res_map = objectMapper.readValue(
						array_str[i], Map.class);
				if (i == 0) {
					col_map = res_map;
					list = (List)col_map.get(analysisTemplate.getTag());
				}else{
					List templist = (List)res_map.get(analysisTemplate.getTag());
					if(templist != null && templist.size() > 0){
						list.addAll(templist);
					}
				}
				
			}
			return objectMapper.writeValueAsString(col_map);
		} catch (Exception e) {

		}
		return "";
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
	 * 获取广东参数
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
	 * 获取深圳参数
	 * @param html
	 * @return
	 */
	public Map<String,String> getSzRequestParam(String html){
		Map<String, String> map = new HashMap<String, String>();
		map.put("ScriptManager1", "biangengxinxi|Timer2");
		map.put("__EVENTTARGET", "Timer2");
		map.put("__EVENTARGUMENT", "");
		map.put("__VIEWSTATE",
				JsoupUtil.getValById("__VIEWSTATE", html));
		map.put("__VIEWSTATEGENERATOR",
				JsoupUtil.getValById("__VIEWSTATEGENERATOR", html));
		map.put("__ASYNCPOST", "true");
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
			if ("1".equals(requestInfo.getSubId())
					&& !BASE_INFO.equals(requestInfo.getModeType())) {
				int pageSize = 1;
				if (html != null && !"".equals(html)) {
					Map<String, Map<String, Object>> return_map = objectMapper
							.readValue(html, Map.class);
					if (return_map.get("baseVo") != null) {
						Map<String, Object> baseVo = (Map<String, Object>) return_map
								.get("baseVo");
						if (baseVo.get("totalPage") != null
								&& !"".equals(baseVo.get("totalPage"))) {
							pageSize = (Integer)baseVo.get("totalPage");
						}
					}
				}
				for (int i = 2; i <= pageSize; i++) {
					param.put("entityVo.curPage", i+"");
					String temp_html = (String) this.request(requestInfo,
							param, this.COOKIE_RESULT, encode, cookies);
					if (temp_html != null && !"".equals(temp_html)) {
						html += "CDATA_CONNECT" + temp_html;
					}
				}
			}

			// 广东基本信息获取请求参数
			if (BASE_INFO.equals(requestInfo.getModeType())
					&& "0".equals(requestInfo.getSubId())) {
				Token token = tokenFactory.generateToken(null, corpId, getGdRequestParam(html));
				tokenRegistry.addToken(token);
			} else if ("2".equals(requestInfo.getSubId())
					&& BASE_INFO.equals(requestInfo.getModeType())) {
				// 深圳的股东信息需要设置参数
				Token token = tokenFactory.generateToken(cookies, corpId, getSzRequestParam(html));
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
