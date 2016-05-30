package com.upg.zx.domain.capture.service.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.stream.FileImageOutputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpException;
import org.apache.http.cookie.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
/**
 *辽宁工商网抓取实现类
 * @author litaotao
 *
 */
public class LiaoNingCaptureService extends CaptureServiceImp {

	@Override
	protected void getJsessionIdMap(Map<String,String>map,String html){
		
	}

	@Override
	protected Map<String,String>getAuthCodeParams(Map<String,String>map){
		//Math.round(Math.random(100)*100000);
		//map.put("type", type+"");
		map.put("id", Math.round(Math.random()*100000)+"");
		return map;
	}
	

	/**
	 * 设置获取验证时需要传递的参数
	 * 
	 * @param map
	 */
	@Override
	protected void setOtherParamFromValidateCode(Map<String, String> map) {
		map.remove("id");
	}
	
	/**
	 * 判断验证码输入是否正确
	 */
	@Override
	protected boolean judgeCode(String html) {
		return !JsoupUtil.isExistNode("div#listContent", html);
	}

	
	@Override
	public List<CorpBase> paseHtmlToList(String html) {
		List<CorpBase> corpbases =new ArrayList<CorpBase>();
 
		return corpbases;
	}	 
	
	protected Map<String, String> getRequestParam(String corpId,RequestInfo requestinfo) {
		String params = requestinfo.getParams();
		Map<String, String> corpIdMap = StringUtil.parseMap(corpId, ",", "=");
		Map<String, String> params_map = StringUtil.parseMap(params, ",", "=");
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
	
	@Override
	public String getSpecifiedInfo(String html,AnalysisTemplate analysisTemplate){
		//基本信息模板
		if("1".equals(analysisTemplate.getType())){
			return html;
		}
		
		if(html.indexOf("[{")!=-1&&html.indexOf("}]")!=-1){
			String msg=html.substring(html.indexOf("[{"),html.indexOf("}]")+2);
			JSONArray dataJson = JSONArray.fromObject(msg);	
			return dataJson.toString();
		}
		return "";
	}
	
}
