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
 * 山东工商网抓取实现类
 * @author lujf
 *
 */
public class ShangDongCaptureService extends CaptureServiceImp {
	
	private final String INFO_PARAM_TYPE = "-2";
	
	protected  void getJsessionIdMap(Map<String,String>map,String html){
		String val = JsoupUtil.getValByQuery("input[name=_csrf]", html);
		map.put("_csrf", val);
	}
	
	@Override
	protected boolean judgeCode(String html) {
		return !JsoupUtil.isExistNode("div[class=input-center3] font", html);
	}

	@Override
	public List<CorpBase> paseHtmlToList(String html) {
		return JsoupUtil.parseHtmlA("gsgsdetail", "gsgsdetail/[^\"]+", html);
	}
	
	
	protected void setOtherParamFromValidateCode(Map<String,String>map) {
		String code = map.get(this.getAuthCodeParam());
		if(code != null){
			map.put(this.getAuthCodeParam(), StringUtil.passwdEncrypt(code));
		}
	}
	
	
	@Override
	protected Map<String, String> getRequestParam(String corpId,RequestInfo requestinfo){
		 Map<String,String> map = new HashMap<String,String>();
		 //基本信息
		 if(BASE_INFO.equals(requestinfo.getModeType())){
			 requestinfo.setRurl(requestinfo.getRurl()+corpId);
		 }else{
			 String[] param = corpId.split("/");
			 if(param.length == 2){
				 List<Cookie>list = this.getRequestCookie(corpId);
				 //非 主管部门（出资人）信息
				 if(!"7".equals(requestinfo.getModeType())){
					 requestinfo.setRurl(requestinfo.getRurl()+param[0]);
				 }
				 map.put("encrpripid", param[1]);
				 if(list == null || list.size() == 0){
					 try {
						String html =(String) this.request(RamCache.requestMap.get(this.getAreaCode()+"_"+this.FIRST_MODE),map,this.COOKIE_RESULT, this.getEncode(),list);
						if(html != null && !"".equals(html)){
							String val = JsoupUtil.getValByQuery("input[name=_csrf]", html);
							map.put("#X-CSRF-TOKEN", val);
							Token token =tokenFactory.generateToken(list, corpId, map);
							tokenRegistry.addToken(token);							
						}
					 } catch (HttpException e) {
						throw new CaptureException(e.getMessage(),"http请求异常!");
					} catch (IOException e) {
						throw new CaptureException(e.getMessage(),"IO异常!");
					}
				 }else{
					 Token token = tokenRegistry.getToken(corpId);
					 if(token != null && token.getParam() != null){
						 map.put("#X-CSRF-TOKEN", token.getParam().get("#X-CSRF-TOKEN"));
					 }
				 }

			 }
			
		 }
		 return map;
	}
	
	
	@Override
	public List<Cookie> getRequestCookie(String corpId){
		Token token = tokenRegistry.getToken(corpId);
		if(token != null){
			return token.getCookies();
		}
		return new ArrayList<Cookie>();
	}
	
	
	
	@Override
	public String getSpecifiedInfo(String html,AnalysisTemplate analysisTemplate){
		//股东信息
		if("2".equals(analysisTemplate.getType())){
			html = StringUtil.getMatchStr(html, "(?<=var\\sczxxliststr\\s=')[^']+(?=')");
		}else if("3".equals(analysisTemplate.getType())){
			//变更信息
			html = StringUtil.getMatchStr(html, "(?<=var\\sbgsxliststr\\s=')[^']+(?=')");
		}
		
		
		if(html == null){
			html = "" ;
		}
		return html; 
	}

}
