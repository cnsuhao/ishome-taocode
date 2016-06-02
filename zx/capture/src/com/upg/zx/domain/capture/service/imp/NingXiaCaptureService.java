package com.upg.zx.domain.capture.service.imp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
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
import com.upg.zx.domain.capture.token.imp.TokenFactory;
import com.upg.zx.domain.entity.AnalysisTemplate;
 
import com.upg.zx.domain.entity.RequestInfo;
import com.upg.zx.web.utils.JsoupUtil;
import com.upg.zx.web.utils.StringUtil;

/**
 * 宁夏工商网抓取实现类
 * 
 * @author 001552
 * 
 */
public class NingXiaCaptureService extends CaptureServiceImp {

	private final int TEMPLATE_REQUEST = -1;

	@Override
	protected Map<String, String> getAuthCodeParams(Map<String, String> map) {
		String date_str = new Date().getTime() + "";
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
		map.remove("random");
		map.put("isEntRecord", "");
		map.put("loginInfo.regno", "");
		map.put("loginInfo.entname", "");
		map.put("loginInfo.idNo", "");
		map.put("loginInfo.mobile", "");
		map.put("loginInfo.password", "");
		map.put("loginInfo.verificationCode", "");
		map.put("otherLoginInfo.name", "");
		map.put("otherLoginInfo.cardId", "");
		map.put("otherLoginInfo.mobile", "");
		map.put("otherLoginInfo.unit", "");
		map.put("otherLoginInfo.dept", "");
		map.put("otherLoginInfo.password", "");
		map.put("otherLoginInfo.verificationCode", "");
	}

	/**
	 * 判断验证码输入是否正确
	 */
	@Override
	protected boolean judgeCode(String html) {
//		System.out.println(html);
		return JsoupUtil.isExistCss("list", html);
	}

	@Override
	public List<CorpBase> paseHtmlToList(String html) {
		List<CorpBase> corpls = new ArrayList<CorpBase>();
	 
		return corpls;
	}

	/**
	 * cordId:在列表获取到的详细信息Id requestinfo:请求信息表对象
	 */
	// 两个参数类型 params:得到请求
	@Override
	protected Map<String, String> getRequestParam(String corpId,
			RequestInfo requestinfo) {
		// 得到参数 qylx=#1,nbxh=#2,qylxFlag=#3,zch=#4
		String params = requestinfo.getParams();
		// 将corpId转换为Map
		//
		Map<String, String> corpIdMap = StringUtil.parseMap(corpId, ",", "=");
		// 将qylx=#1,nbxh=#2,qylxFlag=#3,zch=#4转换为Map
		// {nbxh=UErqPHSaf3MUag2BhE9YZqKiEXl1a7xwSE1lbURJA0U%3D 2,
		// zch=640106600203538 4, qylxFlag=2 3, qylx=9999 1}

		Map<String, String> params_map = StringUtil.parseMap(params, ",", "=");
		// BASE_INFO =4 getModeType()得到请求模块类型
		if (BASE_INFO.equals(requestinfo.getModeType())) {
			// 基本信息如果qylxFlag=2即corpIdMap中key是5 value=2的情况
			// 请求地址为http://gsxt.jxaic.gov.cn/ECPS/qyxxgsAction_initGtjbqk.action
			// 其他请求地址为http://gsxt.jxaic.gov.cn/ECPS/qyxxgsAction_initQyjbqk.action

			// 将请求地址按照,拆分（因为有两个地址）
			String[] urls = requestinfo.getRurl().split(",");
			// 判断flag参数等于2
			if ("2".equals(corpIdMap.get("3"))) {
				requestinfo.setRurl(urls[1]);
			} else {
				requestinfo.setRurl(urls[0]);
			}
		}

		if (params != null && !"".equals(params)) {
			for (Map.Entry<String, String> entry : params_map.entrySet()) {
				String val = entry.getValue();
				String key = entry.getKey();
				if (val.indexOf("#") != -1) {
					String corpKey = val.substring(val.indexOf("#") + 1,
							val.length());// 1 2 3 4
					entry.setValue(corpIdMap.get(corpKey));
				}
			}
		}
		try {
			String nbxh = URLDecoder.decode(params_map.get("nbxh"), "UTF-8");
			params_map.put("nbxh", nbxh);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return params_map;
	}

	/**
	 * 获取详细信息
	 * @throws Exception 
	 * 
	 * @throws IOException
	 * @throws HttpException
	 */
	public String getCompanyBaseInfo(String corpId, RequestInfo requestInfo,
			String templateHtml) throws Exception {
		String getNbxh="";
		// 基本信息
		if (BASE_INFO.equals(requestInfo.getModeType())) {
			Map<String, String> param = getRequestParam(corpId, requestInfo);
			Map<String, String> param_tmp = StringUtil.parseMap(corpId, ",",
					"=");
			List<Cookie> cookies = getRequestCookie(corpId);

			String base_html = super.getCompanyBaseInfo(corpId,
			// this.getAreaCode() +
			// "_"+this.TEMPLATE_REQUEST做为Map的key传入数据库
					RamCache.requestMap.get(this.getAreaCode() + "_"
							+ this.TEMPLATE_REQUEST), templateHtml);
			//取到nbxh参数加密之前的字符串，对于主要人员等信息翻页的时候需要用到
			getNbxh=JsoupUtil.getValById("nbxh", base_html);
			//保存原始nbxh
			HashMap<String,String> nbxhMap = new HashMap<String,String>();
			nbxhMap.put("nbxh", getNbxh);
			Token token = tokenFactory.generateToken(cookies, corpId, nbxhMap);
			tokenRegistry.addToken(token);
			// 当flag等于2的时候，需要使用模板(-1)请求，取到nbxh加密之前的字符串，并传入参数param（Map）
			if ("2".equals(param_tmp.get("3"))
					&& this.BASE_INFO.endsWith(requestInfo.getModeType())) {
				param.put("nbxh", getNbxh);
			}
			String encode = this.getEncode();

			String html = "";
			try {
				html = (String) this.request(requestInfo, param,
						this.COOKIE_RESULT, encode, cookies);
			} catch (HttpException e) {
				throw new CaptureException(e.getMessage(), "http请求异常");
			} catch (IOException e) {
				throw new CaptureException(e.getMessage(), "IO异常");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return html;
		}
		// 其他信息考虑分页问题（pno超出时候，默认设置为最大，以此来判断是否还有分页）
		// buf 用于增加翻页的html,(拼成一整页的html：包含一个表头和所有页的内容的html并返回)
		StringBuffer buf = new StringBuffer();
		Map<String, String> param = getRequestParam(corpId, requestInfo);
		// pageNo(页数)
		int pno = 1;
		List<Cookie> cookies = new ArrayList<Cookie>();
		// 设置currpage=1,取第一页
		param.put("currpage", pno + "");
		String html = "";
		String tableHtml = "";
		StringBuffer tableOutHead = new StringBuffer();
		// 判断是不是主要人员信息，是：取出加密前的字符串并传入参数（因为需要加密前字符串实现翻页）
		if ("7".equals(requestInfo.getModeType()) || "8".equals(requestInfo.getModeType()) || "9".equals(requestInfo.getModeType())) {
			Token token = tokenRegistry.getToken(corpId);
			if(token != null && token.getParam() != null){
				param.put("nbxh", token.getParam().get("nbxh"));
			}
		}
		try {
			// 发起个请求 ，得到总的页数
			html = (String) this.request(requestInfo, param,
					this.COOKIE_RESULT, encode, cookies);
			String pageNum = parseHtmlToGetPage(html);
			
			tableOutHead.append(html);
			// 循环请求剩下的几页
				
			for (int i = 2; i <= Integer.parseInt(pageNum); i++) {
				param.put("currpage", i + "");
				String fc = (String) this.request(requestInfo, param,
						this.COOKIE_RESULT, encode, cookies);
					//将除第一页以外的信息拼接
				tableOutHead.append("pageData_split"+fc);
			}
	
		} catch (HttpException e) {
			throw new CaptureException(e.getMessage(), "http请求异常");
		} catch (IOException e) {
			throw new CaptureException(e.getMessage(), "IO异常");
		} catch(Exception e){
			e.printStackTrace();
		}

		return  tableOutHead.toString();
	}



	// 返回总的页数,不用翻页 ：返回1或者“”；用翻页：返回总的页数
	public static String parseHtmlToGetPage(String html) {
		String pageNo = "";
		// 判断是否需要翻页
		Document doc = Jsoup.parse(html);
		Elements links = doc.select("table");
		int table_sp = 0;
		if(links.size() > 1){
			table_sp = 1;
		}
		if (links.size() > 0) {
			links = links.get(table_sp).select("a");
			if(links.size()>2){
				// 取第2个table的倒数第2个a标签的文本
				pageNo = links.get(links.size() - 2).text();
				return pageNo;
			}
		}

		return pageNo;
	}
	
 
}
