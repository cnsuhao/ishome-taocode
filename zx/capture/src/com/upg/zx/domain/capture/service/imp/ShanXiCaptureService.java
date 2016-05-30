package com.upg.zx.domain.capture.service.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 


import org.apache.http.HttpException;

import com.upg.zx.capture.bean.CorpBase;
import com.upg.zx.domain.capture.bean.RamCache;
import com.upg.zx.domain.capture.bean.Token;
import com.upg.zx.domain.capture.exception.CaptureException;
import com.upg.zx.domain.entity.RequestInfo;
import com.upg.zx.domain.response.CorpBaseRes;
import com.upg.zx.web.utils.JsoupUtil;
import com.upg.zx.web.utils.StringUtil;

/**
 * 陕西工商网抓取实现类
 * 
 * @author 001552
 * 
 */
public class ShanXiCaptureService extends CaptureServiceImp {
	
	/**
	 * 设置获取sessionID时候设置参数
	 * 
	 * @param map
	 * @return
	 */
	@Override
	protected Map<String, String> setGetJsessionIdMap(Map<String, String> map) {
		map.put("random", new Date().getTime()+"");
		return map;
	}
	
	@Override
	protected Map<String,String>getAuthCodeParams(Map<String,String>map){
		String date_str = new Date().getTime()+"";
		map.put("dt", date_str);
		map.put("random", date_str);
		return map;
	}
	
	/**
	 * 设置获取验证时需要传递的参数
	 * 
	 * @param map
	 */
	@Override
	protected void setOtherParamFromValidateCode(Map<String, String> map) {
		map.remove("dt");
		map.put("random", new Date().getTime()+"");
		map.put("currentPageNo", "1");
		map.put("cxym", "cxlist");
//		map.put("pName", "请输入营业执照注册号");
//		map.put("marketName", "请输入企业、农民专业合作社名称、个体工商户名称字号"); 
	}
	/**
	 * 解析列表数据
	 */
	@Override
	public List<CorpBase> paseHtmlToList(String html) {
		List<String> hrefList = null;
		List<CorpBase> corpls = new ArrayList<CorpBase>();
		return corpls;
	}
	/**
	 * 判断验证码输入是否正确
	 */
	@Override
	protected boolean judgeCode(String html) {
//		System.out.println(html);
		if(html.indexOf("var flag = 'fail'")==-1){
			System.out.println("正确");
			return true;
		}else{
			System.out.println("错误");
			return false;
		}
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
		if("4".equals(requestinfo.getModeType())){
			params_map.put("method", "qyInfo");
			params_map.put("random", new Date().getTime()+"");
			params_map.put("djjg", "");
		}else if("5".equals(requestinfo.getModeType())){
			params_map.put("method", "baInfo");
			params_map.put("random", new Date().getTime()+"");
			params_map.put("czmk", "czmk2");
		}
		return params_map;
	}
	
	/**
	 * 解析请求参数
	 * @param html
	 * @param requestInfo
	 * @return
	 */
	public Map<String,String>paseParamFromHtml(String html,RequestInfo requestInfo){
		Map<String,String> params = new HashMap<String,String>();
		if("4".equals(requestInfo.getModeType())){
			params.put("method", "qyInfo");
			params.put("random", new Date().getTime()+"");
			params.put("djjg", "");
		}else if("5".equals(requestInfo.getModeType())){
			params.put("method", "baInfo");
			params.put("random", new Date().getTime()+"");
			params.put("czmk", "czmk2");
		}
		return params;
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
					String page_s = JsoupUtil.getTextBySelect(html,".list-a a");
					StringBuffer buf = new StringBuffer();
					if(!"".equals(page_s)){
						int page = Integer.parseInt(page_s);
						for(int i = 2 ; i <= page ; i++){
							Thread.sleep(500L);
							map.put("currentPageNo", "");
							map.put("yzmYesOrNo", "no");
							map.put("pageNum", i+"");
							map.put("yzm", "");
							map.put("cxym", "cxlist");
							map.put("random", new Date().getTime()+"");
							final String html_page = (String) this.request(
									RamCache.requestMap.get(this.getAreaCode() + "_"
											+ this.LIST_MODE), map, this.STRING_RESULT,
									this.getEncode(), token.getCookies());
							buf.append(JsoupUtil.getHtmlByClass(html_page, "center-1"));
						}
					} 
					if(buf.length() > 0){
						html = JsoupUtil.appHtmlToSelect("div.center-1", html, buf.toString());
					}
					corpBaseRes.setType("list");
					corpBaseRes.setCorp_name(companyName);
					corpBaseRes.setHtml(html);
					 
				} catch (HttpException e) {
					throw new CaptureException(e.getMessage(),"http请求异常!");
				} catch (IOException e) {
					throw new CaptureException(e.getMessage(),"IO异常!");
				} catch (CaptureException e) {
					throw e;
				}
				catch (Exception e){
					throw new CaptureException(e.getMessage(),e.getMessage());
				}
				
				return corpBaseRes;
		 }
}
