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

 


import com.upg.zx.capture.bean.CorpBase;
import com.upg.zx.domain.capture.bean.RamCache;
import com.upg.zx.domain.capture.bean.RequestType;
import com.upg.zx.domain.capture.bean.Token;
import com.upg.zx.domain.capture.exception.CaptureException;
import com.upg.zx.domain.entity.AnalysisTemplate;
 
import com.upg.zx.domain.entity.RequestInfo;
import com.upg.zx.domain.response.CorpBaseRes;
import com.upg.zx.web.utils.JsoupUtil;
import com.upg.zx.web.utils.StringUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 北京工商网抓取实现类
 * 
 * @author lujf
 * 
 */
public class BeiJingCaptureService extends CaptureServiceImp {
	private final String WORD_CHECK_MODE = "0";

	@Override
	protected Map<String, String> setGetJsessionIdMap(Map<String, String> map) {
		return new HashMap<String, String>();
	}

	@Override
	protected void getJsessionIdMap(Map<String, String> map, String html) {
		String val = JsoupUtil.getValByNameOne("credit_ticket", html);
		String currentTimeMillis = JsoupUtil.getValByNameOne(
				"currentTimeMillis", html);
		map.put("credit_ticket", val);
		map.put("currentTimeMillis", currentTimeMillis);
	}

	@Override
	protected Map<String, String> getAuthCodeParams(Map<String, String> map) {
		Map<String, String> param = new HashMap<String, String>();
		String currentTimeMillis = new Date().getTime() + "";
		param.put("currentTimeMillis", currentTimeMillis);
		param.put("num", Math.round(Math.random() * 100000) + "");
		map.put("currentTimeMillis", currentTimeMillis);
		return param;
	}

	@Override
	protected boolean judgeCode(String html) {
		return JsoupUtil.isExistCss("list", html);
	}

	@Override
	public List<CorpBase> paseHtmlToList(String html) {
		List<CorpBase> corpbases = new ArrayList<CorpBase>();
	 
		return corpbases;
	}

	public CorpBaseRes captureCompany(String companyName, String authCode,
			String jsessionid) {
		CorpBaseRes corpBaseRes = super.captureCompany(companyName, authCode,
				jsessionid);
		List<CorpBase> corpBasels = corpBaseRes.getList();
		if (corpBaseRes != null && "list".equals(corpBaseRes.getType())) {
			if (corpBasels != null) {
				for (CorpBase corpbase : corpBasels) {
					String companyId = corpbase.getCompany_ID() + ",9999="
							+ jsessionid;
					corpbase.setCompany_ID(companyId);
				}
			}
		}
		return corpBaseRes;
	}

	protected void setOtherParamFromValidateCode(Map<String, String> map) {
		map.remove("num");
	}

	@Override
	protected Map<String, String> getRequestParam(String corpId,
			RequestInfo requestInfo) {
		String params = requestInfo.getParams();
		Map<String, String> corpIdMap = StringUtil.parseMap(corpId, ",", "=");

		Map<String, String> params_map = StringUtil.parseMap(params, ",", "=");

		if (params != null && !"".equals(params)) {
			for (Map.Entry<String, String> entry : params_map.entrySet()) {
				String val = entry.getValue();
				String key = entry.getKey();
				if (val.indexOf("#") != -1) {
					String corpKey = val.substring(val.indexOf("#") + 1,
							val.length());
					if (corpIdMap.get(corpKey) != null) {
						entry.setValue(corpIdMap.get(corpKey));
					}
				}
			}
		}
		params_map.put("timeStamp", (new Date().getTime()) + "");

		return params_map;
	}

	public List<Cookie> getRequestCookie(String corpId) {
		Map<String, String> corpIdMap = StringUtil.parseMap(corpId, ",", "=");
		Token token = null;
		if (corpIdMap.get("9999") == null
				|| (token = tokenRegistry.getToken(corpIdMap.get("9999"))) == null) {
			throw new CaptureException("北京抓取数据cookie过期", "北京抓取数据cookie过期");
		}
		return token.getCookies();
	}

	/**
	 * 获取详细信息
	 * 
	 * @throws IOException
	 * @throws HttpException
	 */
	public String getCompanyBaseInfo(String corpId, RequestInfo requestInfo,
			String templateHtml) {
		String encode = this.getEncode();
		List<Cookie> cookies = getRequestCookie(corpId);
		String html = "";
		StringBuffer rsbuf = new StringBuffer();
		try {
			Map<String, String> param = getRequestParam(corpId, requestInfo);
			Thread.sleep(500L);
			html = (String) this.request(requestInfo, param,
					this.COOKIE_RESULT, encode, cookies);
			rsbuf.append(html);
			if (!"4".equals(requestInfo.getModeType())) {
				String pagescount = JsoupUtil.getValById("pagescount", html);
				if (pagescount != null && !"".equals(pagescount)
						&& !"1".equals(pagescount)) {
					// 页数
					int count = Integer.parseInt(pagescount);
					for (int i = 2; i <= count; i++) {
						param.put("pageNos", i + "");
						String html_page = (String) this.request(requestInfo,
								param, this.COOKIE_RESULT, encode, cookies);
						if (html_page != null && !"".equals(html_page)) {
							rsbuf.append("pageData_split" + html_page);
						}
					}
				}
			}
		} catch (HttpException e) {
			throw new CaptureException(e.getMessage(), "http请求异常");
		} catch (IOException e) {
			throw new CaptureException(e.getMessage(), "IO异常");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsbuf.toString();
	}
	
	 protected CorpBaseRes validateAuthCode(String companyName,
				String authCode, String sessionId){
				Token  token = tokenRegistry.getToken(sessionId);
				//token不存在或已过期
				if(token == null || token.isExpired()){
					throw new CaptureException("000001","token不存在","请求验证码超时!");
				}
				
				CorpBaseRes corpBaseRes = new CorpBaseRes();
				Map<String,String> map = token.getParam();
				map.put(this.getAuthCodeParam(), authCode);
				
				setOtherParamFromValidateCode(map);
				
				try {
					String html =(String) this.request(RamCache.requestMap.get(this.getAreaCode()+"_"+this.LIST_MODE),map,this.STRING_RESULT, this.getEncode(),token.getCookies());
					//判断是否存在数据节点,不存在说明验证码错误，页面进行了跳转
					
				
					if(!judgeCode(html)){
						throw new  CaptureException("000002","验证码错误!","验证码错误!");
					}
					
					html = JsoupUtil.appHtmlToSelect("", html, "<input id='sessionId' value='"+sessionId+"' >");
					System.out.println(html);
					//List<CorpBase> list=  paseHtmlToList(html);
					corpBaseRes.setType("list");
					corpBaseRes.setCorp_name(companyName);
					corpBaseRes.setHtml(html);
					 
				} catch (HttpException e) {
					throw new CaptureException(e.getMessage(),"http请求异常!");
				} catch (IOException e) {
					throw new CaptureException(e.getMessage(),"IO异常!");
				}
				
				return corpBaseRes;
		 }


}
