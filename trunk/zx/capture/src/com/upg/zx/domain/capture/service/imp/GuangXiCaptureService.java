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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URLEncoder;
/**
 * 广西工商网抓取实现类
 * @author lujf
 *
 */
public class GuangXiCaptureService extends CaptureServiceImp {
	
	private final String WORD_CHECK_MODE = "0"; 
	@Override
	protected void getJsessionIdMap(Map<String,String>map,String html){
		//Math.random()
		  //(Math.floor(Math.random()*10))%3;
		Double type = (Math.floor(Math.random()*10))%3;
		map.put("type", "1");
		map.put("id", Math.random()+"");		
	}
	
	@Override
	protected void  setOtherParamFromValidateCode(Map<String,String>map){
		map.remove("type");
		map.remove("id");
	}
	
	@Override
	protected Map<String,String>getAuthCodeParams(Map<String,String>map){
		getJsessionIdMap(map,"");
		return map;
	}
//	
//    @Override
//	public boolean checkWord(String companyName,String jsessionid){
//		Token  token = tokenRegistry.getToken(jsessionid);
//		//token不存在或已过期
//		if(token == null || token.isExpired()){
//			throw new CaptureException("token不存在","请求验证码超时!");
//		}
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("entName", URLEncoder.encode(companyName));
//		final String html;
//		try{
//			html =(String) this.request(RamCache.requestMap.get(this.getAreaCode()+"_"+this.WORD_CHECK_MODE),map,this.STRING_RESULT, this.getEncode(),token.getCookies());
//			//{success:false}
//			if(html != null && html.indexOf("true") != -1){
//				throw new CustomException("请输入更为详细的查询条件!");
//			}
//		} catch (HttpException e) {
//			throw new CaptureException(e.getMessage(),"http请求异常!");
//		} catch (IOException e) {
//			throw new CaptureException(e.getMessage(),"IO异常!");
//		}
//		return true;
//	}
	
	@Override
	protected boolean judgeCode(String html) {
		//false验证码错误
		return (JsoupUtil.isExistNode(".list", html) || JsoupUtil.isExistNode(".list-a", html));
	}

	@Override
	public List<CorpBase> paseHtmlToList(String html) {
		List<CorpBase> corpbases = JsoupUtil.parseHtmlA("id", ".businessPublicity.jspx\\?id=.", html);
		return corpbases;
	}	 
	
	
	@Override
	public String getSpecifiedInfo(String html,AnalysisTemplate analysisTemplate){
		//基本信息模板
		if("1".equals(analysisTemplate.getType())){
			return html;
		}
		
		if(html.indexOf("[CDATA[template_data]]") == -1){
			return "";
		}
		//获取头部的html
		String headHtml = JsoupUtil.getSpecifiedHtml(
				analysisTemplate.getTabId(), analysisTemplate.getTag(),
				analysisTemplate.getSelector(), html.substring(0,html.indexOf("[CDATA[template_data]]")));
		
		if(headHtml == null || "".equals(headHtml)){
			return "";
		}
		String tableHtmle = JsoupUtil.mergeHeadTableAndBodyTable(headHtml, html.substring(html.indexOf("[CDATA[template_data]]")+"[CDATA[template_data]]".length()));
		return tableHtmle;
	}
	
	
	@Override
	public String getCompanyBaseInfo(String corpId, RequestInfo requestInfo,String templateHtml){
		//基本信息
		if(BASE_INFO.equals(requestInfo.getModeType())){
			return super.getCompanyBaseInfo(corpId, requestInfo, templateHtml);
		}
		//其他信息考虑分页问题（pno超出时候，默认设置为最大，以此来判断是否还有分页）
		StringBuffer buf = new StringBuffer();
		Map<String, String> param = getRequestParam(corpId,requestInfo);
		String str = "";
		int pno = 1;
		List<Cookie> cookies = new ArrayList<Cookie>();
		List<AnalysisTemplate> ls = RamCache.analysisTemplateMap.get(requestInfo.getId()+"");
		int pageSize = 0 ;
		if(ls != null && ls.size() > 0){
			for(AnalysisTemplate analysisTemplate : ls){
				String rs = JsoupUtil.parseHtmlToGetPage(templateHtml,analysisTemplate.getSelector());
				if(rs != null){
					pageSize = Integer.parseInt(rs);
					break;
				}
			}
		}
		
		for(int i = 0 ; i < pageSize ; i ++){
			param.put("pno", pno+"");
			final String html;
			try {
				html = (String) this.request(requestInfo, param,
						this.COOKIE_RESULT, encode, cookies);
			} catch (HttpException e) {
				throw new CaptureException(e.getMessage(), "http请求异常");
			} catch (IOException e) {
				throw new CaptureException(e.getMessage(), "IO异常");
			}
			if(str.equals(html)|| "".equals(html)){
				break;
			}
			pno++;
			str = html;
			buf.append(html);
		}
		
		return templateHtml+"[CDATA[template_data]]"+buf.toString();
	}
}
