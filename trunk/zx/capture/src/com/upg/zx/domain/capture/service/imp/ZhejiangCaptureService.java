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
 
import com.upg.zx.domain.entity.RequestInfo;
import com.upg.zx.domain.response.CorpBaseRes;
import com.upg.zx.web.utils.JsoupUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
/**
 * 浙江工商网抓取实现类
 * @author lujf
 *
 */
public class ZhejiangCaptureService extends CaptureServiceImp {

	@Override
	protected boolean judgeCode(String html) {
		if("".equals(JsoupUtil.getTextById("codeValidator", html))){
			return true;
		}
		return false;
	}
	
	@Override
	protected void getJsessionIdMap(Map<String, String> map, String html) {
		String tokenName = JsoupUtil.getValByNameOne("struts.token.name", html);
		String token = JsoupUtil.getValByNameOne("token", html);
		if(tokenName != null){
			map.put("struts.token.name", tokenName);
		}
		if(token != null){
			map.put("token", token);
		}
	}

	@Override
	public List<CorpBase> paseHtmlToList(String html) {
		// TODO Auto-generated method stub
		return null;
	} 
}
