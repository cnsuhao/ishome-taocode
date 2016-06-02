package com.upg.zx.domain.capture.service.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.http.HttpException;
import org.apache.http.cookie.Cookie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upg.zx.capture.bean.CorpBase;
import com.upg.zx.domain.capture.bean.RamCache;
import com.upg.zx.domain.capture.bean.Token;
import com.upg.zx.domain.capture.exception.CaptureException;
import com.upg.zx.domain.entity.RequestInfo;
import com.upg.zx.domain.response.CorpBaseRes;
import com.upg.zx.web.utils.JsoupUtil;
import com.upg.zx.web.utils.StringUtil;

public class JianXi2CaptureService extends CaptureServiceImp{
	private ObjectMapper objectMapper;
	
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	
	 

	@Override
	protected Map<String, String> setGetJsessionIdMap(Map<String, String> map) {
		return new HashMap<String, String>();
	}

	
	
	@Override
	public List<CorpBase> paseHtmlToList(String html) {
		List<CorpBase> list = JsoupUtil.parseHtmlA(null,".ccjcgs_ccjcgsIndexDetail.pt\\?qylx=.", html);
		 
		return list;
	}

	@Override
	protected boolean judgeCode(String html) {
		if("false".equals(html)){
			return false;
		}
		return true;
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
			if (!judgeCode(returnMap.get("msg"))) {
				throw new CaptureException("000002", "验证码错误!", "验证码错误!");
			}
			map.put("pageFlag", "1");
			final String html = (String) this.request(
					RamCache.requestMap.get(this.getAreaCode() + "_"
							+ this.LIST_MODE), map, this.STRING_RESULT,
					this.getEncode(), token.getCookies());
			 
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
	
	
	@Override
	protected Map<String, String> getRequestParam(String corpId,RequestInfo requestinfo){
		String params = requestinfo.getParams();
		Map<String,String> corpIdMap = StringUtil.parseMap(corpId, ",", "=");
		Map<String,String>params_map = StringUtil.parseMap(params, ",", "=");
		
		if(BASE_INFO.equals(requestinfo.getModeType())){
			//兼容老版本2=9999模式
			if(corpIdMap.get("2") == null && "9999".equals(corpIdMap.get("2"))){
				corpIdMap.put("2","");
			}
 
		}
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
	 * 列表抓取
	 */
	public CorpBaseRes captureCompany(String companyName, String authCode,
			String jsessionid) {
		// 获取验证码
		CorpBaseRes corpBaseRes = new CorpBaseRes();
		if (authCode == null || "".equals(authCode)) {
			corpBaseRes.setCorp_name(companyName);
			return corpBaseRes;
		}
		try {
			RequestInfo requestInfo = RamCache.requestMap.get(this.getAreaCode() + "_"
					+ this.LIST_MODE);
			
			RequestInfo copyRequestInfo = new RequestInfo();
			try {
				PropertyUtils.copyProperties(copyRequestInfo, requestInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}  
			copyRequestInfo.setRurl("http://gsxt.jxaic.gov.cn/ECPS/home/home_homeSearch.pt?searchPage=yes");
			copyRequestInfo.setRequestType("post");
			Map<String,String> map = new HashMap<String,String>();
			map.put("searchtext", companyName);
			map.put("pageFlag", "");
			map.put("limit", "50");
			map.put("page", "1");
			map.put("yzm", "1");
			
			final String html = (String) this.request(copyRequestInfo, map, this.STRING_RESULT,
					this.getEncode(), new ArrayList<Cookie>());
			
			corpBaseRes.setType("list");
			corpBaseRes.setCorp_name(companyName);
			corpBaseRes.setHtml(html);
			
		} catch (Exception e) {	
				e.printStackTrace();
		}
		return corpBaseRes;
	}
}
