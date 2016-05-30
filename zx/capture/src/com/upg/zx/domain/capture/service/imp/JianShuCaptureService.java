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
 
import com.upg.zx.domain.entity.RequestInfo;
import com.upg.zx.domain.response.CorpBaseRes;
import com.upg.zx.web.utils.JsoupUtil;
import com.upg.zx.web.utils.StringUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
/**
 * 江苏工商网抓取实现类
 * @author lujf
 *
 */
public class JianShuCaptureService extends CaptureServiceImp {
	private final String WORD_CHECK_MODE = "0";	
	
	private ObjectMapper objectMapper;
	
	
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	protected boolean judgeCode(String html) {
		try {
			List<Map<String,String>> list = (List) objectMapper.readValue(html, List.class);
			for(Map<String,String> map : list){
				String tips = map.get("TIPS");
				if(tips == null || "".equals(tips.trim())){
					return true;
				}
			}
		} catch (Exception e) {
			throw new CaptureException(e.getMessage(), "解析验证码返回值异常");
		}  
		return false;
	}

	@Override
	public List<CorpBase> paseHtmlToList(String html) {
		return null;
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
	
}
