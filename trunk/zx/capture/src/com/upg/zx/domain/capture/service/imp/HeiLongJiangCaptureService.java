package com.upg.zx.domain.capture.service.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.stream.FileImageOutputStream;

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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
/**
 * 黑龙江工商网抓取实现类
 * @author litaotao
 *
 */
public class HeiLongJiangCaptureService extends CaptureServiceImp {

	@Override
	protected void getJsessionIdMap(Map<String,String>map,String html){
		
	}

	@Override
	protected Map<String,String>getAuthCodeParams(Map<String,String>map){
		Double type = (Math.floor(Math.random()*10))%3;
		map.put("type", "1");
		map.put("id", Math.random()+"");
		return map;
	}
	

	/**
	 * 设置获取验证时需要传递的参数
	 * 
	 * @param map
	 */
	@Override
	protected void setOtherParamFromValidateCode(Map<String, String> map) {
		map.remove("type");
		map.remove("id");
	}
	
	/**
	 * 判断验证码输入是否正确
	 */
	@Override
	protected boolean judgeCode(String html) {
		Document doc=Jsoup.parse(html);
		Elements eles=doc.select("div.list-a");
		Elements eles2=doc.select("div.list");
		if(eles.size()>0||eles2.size()>0){
			return true;
		}else{
			return false;
		}
	}

	
	@Override
	public List<CorpBase> paseHtmlToList(String html) {
		List<CorpBase> corpbases =  JsoupUtil.parseHtmlA("id", ".businessPublicity.jspx\\?id=.", html);
	 
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
	public String getCompanyBaseInfo(String corpId, RequestInfo requestInfo,String templateHtml) throws Exception{
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
				String rs = parseHtmlToGetPageForHLJ(templateHtml,analysisTemplate.getSelector(),requestInfo);
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
 

	//黑龙江专用分页
	public static String parseHtmlToGetPageForHLJ(String html,String select,RequestInfo requestInfo){
		Document doc = Jsoup.parse(html);
		Elements elements = doc.getElementsByTag("table");
		if("5".equals(requestInfo.getModeType())){
			Elements invDivEles=doc.select("div#invPagination").select("table.detailsList");
			Elements spans = invDivEles.select("a");
			ArrayList<String> list=new ArrayList<String>();
			for(int i=0;i<spans.size();i++){
				String href=spans.get(i).attr("href");
				if(href.contains("goPage")){
					list.add(spans.get(i).getElementsByTag("span").text());
				}
				
			}
			if(list != null && list.size() > 0){
				return list.get(list.size()-1);
			}
			return null;
		}
		Element element = null;
		for(int i = 0 ; i < elements.size() ; i++){
			Element tableElement = elements.get(i);
			Elements temp = tableElement.select(select);
			if(temp != null && temp.size() > 0){
				//头部table
				element = tableElement;
				break;
			}
		}
		if(element != null){
			Element pageElement = element.nextElementSibling().nextElementSibling();
			if(pageElement != null && "TABLE".equals(pageElement.nodeName().toUpperCase())){
				//获取a标签下的span
				Elements spans = pageElement.select("a span");
				if(spans != null && spans.size() > 0){
					return spans.last().text();
				}
			}
		}	
		return null;
	}
}
