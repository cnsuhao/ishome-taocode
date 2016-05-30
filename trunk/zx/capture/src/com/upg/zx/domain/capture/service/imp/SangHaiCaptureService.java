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
 * 上海工商网抓取实现类
 * @author lujf
 *
 */
public class SangHaiCaptureService extends CaptureServiceImp {
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
		return JsoupUtil.parseHtmlA("uuid", ".view\\?uuid=.", html);
	}
	
	
	protected void setOtherParamFromValidateCode(Map<String,String>map) {
		map.put("condition.pageNo", "1");
		map.put("condition.insType", "");
	}
 
}
