package com.upg.zx.domain.capture.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

 

import com.upg.zx.capture.bean.CorpBase;
import com.upg.zx.domain.entity.RequestInfo;
import com.upg.zx.web.utils.JsoupUtil;
import com.upg.zx.web.utils.StringUtil;

/**
 * 甘肃工商网抓取实现类
 * 
 * @author 001552
 * 
 */
public class GanSuCaptureService extends CaptureServiceImp {

	private final int TEMPLATE_REQUEST = -1;

	/**
	 * 设置获取验证时需要传递的参数
	 * 
	 * @param map
	 */
	@Override
	protected void setOtherParamFromValidateCode(Map<String, String> map) {
		map.remove("random");
		map.put("v", new Date().getTime() + "");
	}

	/**
	 * 判断验证码输入是否正确
	 */
	@Override
	protected boolean judgeCode(String html) {
		// 只有配置了列表请求，断点才会打在这，否则会报nullpointexception
		// 先判断验证码是否正确，在判断查询条件
		if (html.contains("验证码输入错误,请重新输入")) {
			return false;
		} else if (html.contains("请您输入更精确的查询条件")) {
			// 条件不精确 ，返回true，但是会没有结果
			return true;
		} else if (html.contains("您输入的查询条件有误，请重新输入查询条件")) {
			return true;
		}
		// 这个判断没有必要 ，只是为了防止网页提示信息发生变化时走最后一个判断
		return JsoupUtil.isExistCss("list", html);
	}

	@Override
	public List<CorpBase> paseHtmlToList(String html) {
		List<CorpBase> corpls = new ArrayList<CorpBase>();
		 
		return corpls;
	}

	@Override
	protected Map<String, String> getRequestParam(String corpId,
			RequestInfo requestinfo) {
		String params = requestinfo.getParams();
		Map<String, String> corpIdMap = StringUtil.parseMap(corpId, ",", "=");
		Map<String, String> params_map = StringUtil.parseMap(params, ",", "=");
		if (params != null && !"".equals(params)) {
			for (Map.Entry<String, String> entry : params_map.entrySet()) {
				String val = entry.getValue();
				String key = entry.getKey();
				if (val.indexOf("#") != -1) {
					String corpKey = val.substring(val.indexOf("#") + 1,
							val.length());
					if (corpIdMap.get(corpKey) != null) {
						entry.setValue(corpIdMap.get(corpKey));
					}
				}
			}
		}
		return params_map;
	}
}
