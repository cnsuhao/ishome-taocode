package com.upg.zx.domain.capture.service.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.stream.FileImageOutputStream;

import net.sf.json.JSONArray;

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
/**
 * 天津工商网抓取实现类
 * @author litaotao
 *
 */
public class TianJinCaptureService extends CaptureServiceImp {

	@Override
	protected void getJsessionIdMap(Map<String,String>map,String html){
		map.put("matchCondition", 1+"");
	}

	@Override
	protected Map<String,String>getAuthCodeParams(Map<String,String>map){
		long type = new Date().getTime();
		map.put("id", type+"");
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
		Document doc=Jsoup.parse(html); 
		Elements eles=doc.select("div.result-item");
		if(eles.size()>0){
			return true;
		}else{
			return false;
		}
	}

	
	protected Map<String, String> getRequestParam(String corpId,RequestInfo requestinfo){
		Map<String,String> param = new HashMap<String,String>();
		param.put("companyId", corpId);
		if(BASE_INFO.equals(requestinfo.getModeType())){
			param.put("departmentId", "scjgw");
			param.put("infoClassId", "dj");
		}else if("5".equals(requestinfo.getModeType())){
			param.remove("infoClassId");
			param.put("departmentId", "scjgw");
			param.put("infoClassId", "ba");
		}
		return param;
	}
	
	
	@Override
	public List<CorpBase> paseHtmlToList(String html) {
		List<CorpBase> corpbases =  JsoupUtil.parseHtmlA("entId", "/\\w+/\\w+/\\w+\\..+", html);
	 
		return corpbases;
	}
	
}
