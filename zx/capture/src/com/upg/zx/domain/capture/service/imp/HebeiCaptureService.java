package com.upg.zx.domain.capture.service.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpException;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

 

import com.upg.zx.capture.bean.CorpBase;
import com.upg.zx.domain.capture.bean.RamCache;
import com.upg.zx.domain.capture.bean.Token;
import com.upg.zx.domain.capture.exception.CaptureException;
 
import com.upg.zx.web.utils.JsoupUtil;
/**
 * 河北工商网抓取实现类
 * @author Fucy
 *
 */
public class HebeiCaptureService extends CaptureServiceImp {


	private final String WORD_CHECK_MODE = "0";
	protected  void getJsessionIdMap(Map<String,String>map,String html){
		String val = JsoupUtil.getValByNameOne("session.token", html);
		map.put("session.token", val);
	}
	
	@Override
	protected boolean judgeCode(String html) {
		return JsoupUtil.isExistCss("list-info", html);
	}

	@Override
	public List<CorpBase> paseHtmlToList(String html) {
		List<CorpBase> corpbases = JsoupUtil.parseHtmlA("uuid", ".view\\?uuid=.", html);
	 
		return corpbases;
	}
	
	
	protected void setOtherParamFromValidateCode(Map<String,String>map) {
		map.put("condition.pageNo", "1");
		map.put("condition.insType", "");
	}
	
	
	/**
	 * 验证查询企业
	 * @param companyName
	 * @param jsessionid
	 * @return
	 */
	public boolean checkWord(String companyName,String jsessionid){
		Token  token = tokenRegistry.getToken(jsessionid);
		//token不存在或已过期
		if(token == null || token.isExpired()){
			throw new CaptureException("token不存在","请求验证码超时!");
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("keyword", companyName);
		final String html;
		try{
			html =(String) this.request(RamCache.requestMap.get(this.getAreaCode()+"_"+this.WORD_CHECK_MODE),map,this.STRING_RESULT, this.getEncode(),token.getCookies());
			if(html != null && "0".equals(html.toString())){
				//throw new CustomException("请输入更为详细的查询条件!");
			}
		} catch (HttpException e) {
			throw new CaptureException(e.getMessage(),"http请求异常!");
		} catch (IOException e) {
			throw new CaptureException(e.getMessage(),"IO异常!");
		}
		return true;
	}


}
