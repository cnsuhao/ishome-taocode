package com.upg.zx.domain.capture.service.imp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpException;
import org.apache.http.cookie.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

 

import com.upg.zx.capture.bean.CorpBase;
import com.upg.zx.domain.capture.bean.RamCache;
import com.upg.zx.domain.capture.bean.Token;
import com.upg.zx.domain.capture.exception.CaptureException;
import com.upg.zx.domain.entity.AnalysisTemplate;
 
import com.upg.zx.domain.entity.RequestInfo;
import com.upg.zx.web.utils.JsoupUtil;
import com.upg.zx.web.utils.StringUtil;

/**
 * 青海工商网抓取实现类
 * 
 * @author 001552
 * 
 */
public class QingHaiCaptureService extends CaptureServiceImp {
	private final String WORD_CHECK_MODE = "0";

	/**
	 * 设置获取验证时需要传递的参数
	 * 
	 * @param map
	 */
	@Override
	protected void setOtherParamFromValidateCode(Map<String, String> map) {
		map.put("id", Math.random() + "");
	}

	@Override
	public boolean checkWord(String companyName, String jsessionid) throws Exception {
		Token token = tokenRegistry.getToken(jsessionid);
		// token不存在或已过期
		if (token == null || token.isExpired()) {
			throw new CaptureException("token不存在", "请求验证码超时!");
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			map.put("entName", URLEncoder.encode(companyName, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		final String html;
		try {
			html = (String) this.request(
					RamCache.requestMap.get(this.getAreaCode() + "_"
							+ this.WORD_CHECK_MODE), map, this.STRING_RESULT,
					this.getEncode(), token.getCookies());
			// {success:false}
			if (html != null && html.indexOf("true") != -1) {
				//throw new CustomException("请输入更为详细的查询条件!");
			}
		} catch (HttpException e) {
			throw new CaptureException(e.getMessage(), "http请求异常!");
		} catch (IOException e) {
			throw new CaptureException(e.getMessage(), "IO异常!");
		}
		return true;
	}

	/**
	 * 判断验证码输入是否正确
	 */
	@Override
	protected boolean judgeCode(String html) {
		if(JsoupUtil.isExistCss("list", html)){
			return true;
		}else{
			//false时判断是否有查询结果
			if(html.contains("搜索的条件无查询结果")){
				return true;
			}else{
				return false;
			}
		}
	}
	
	@Override
	public List<CorpBase> paseHtmlToList(String html) {
		List<CorpBase> corpls = new ArrayList<CorpBase>();
	 
		return corpls;
	}
	
	//实现翻页的话，一定要配置新的地址
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
 
	
	@Override
	public String getSpecifiedInfo(String html,
			AnalysisTemplate analysisTemplate) {
		// 基本信息模板
		if ("1".equals(analysisTemplate.getType())) {
			return html;
		}
		if (html.indexOf("[CDATA[template_data]]") == -1) {
			return "";
		}
		// 获取头部的html
		String headHtml = JsoupUtil.getSpecifiedHtml(
				analysisTemplate.getTabId(), analysisTemplate.getTag(),
				analysisTemplate.getSelector(),
				html.substring(0, html.indexOf("[CDATA[template_data]]")));

		if (headHtml == null || "".equals(headHtml)) {
			return "";
		}
		String tableHtmle = JsoupUtil.mergeHeadTableAndBodyTable(
				headHtml,
				html.substring(html.indexOf("[CDATA[template_data]]")
						+ "[CDATA[template_data]]".length()));
		return tableHtmle;
	}
}
