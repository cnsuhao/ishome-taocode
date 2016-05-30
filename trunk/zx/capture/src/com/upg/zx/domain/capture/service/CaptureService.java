package com.upg.zx.domain.capture.service;

import java.util.List;
import java.util.Map;

 

import com.upg.zx.capture.bean.CorpBase;
import com.upg.zx.domain.entity.RequestInfo;
import com.upg.zx.domain.response.CorpBaseRes;
/**
 * 数据抓取接口
 * @author lujf
 *
 */
public interface CaptureService {
	  /**
	   * 判断调用具体的实现类
	   * @param areaCode
	   * @return
	   */
	   boolean supports(String areaCode);
	  
	  /**
	   * 抓取数列表
	   * @param code
	   * @param companyName
	   * @param jsessionid
	   * @return
	   */
	   CorpBaseRes captureCompany(String companyName,String authCode,String jsessionid);

	  /**
	   * 获取公司信息
	   * @param corpId
	   * @param areaCode
	   * @param templateHtml
	   * @return
	   */
	  String getCompanyBaseInfo(String corpId,RequestInfo requestInfo,String templateHtml);
	  
	  
	  
		/**
		 * 解析列表数据
		 * @param html
		 * @return
		 */
		List<CorpBase> paseHtmlToList(String html);
}
