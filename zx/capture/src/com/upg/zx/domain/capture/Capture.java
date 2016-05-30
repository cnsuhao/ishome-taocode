package com.upg.zx.domain.capture;

import java.util.List;
import java.util.Map;

import com.upg.zx.domain.response.CorpBaseRes;

/**
 * 数据抓取接口
 * 
 * @author lujf
 * 
 */
public interface Capture {
	/**
	 * 抓取公司列表信息
	 * 
	 * @param companyName
	 * @param authCode
	 * @param jsessionid
	 * @param areaCode
	 * @return
	 */
	CorpBaseRes findCompanyByName(String companyName, String authCode,
			String jsessionid, String areaCode);
	
	/**
	 * 获取公司信息html
	 * @param companyId
	 * @param areaCode
	 * @param corpId
	 * @return
	 */
	public String getCompanyInfoHtml(String companyId, String areaCode,
			String corpId,String subId);
	
	/**
	 * 获取公司信息（公司数据类型属于html模板+json 类型 只用于江苏）
	 * 
	 * @param companyId
	 * @param areaCode
	 * @param corpId
	 * @return
	 */
	public String[] getCompanyInfoByHtmlTemplate(String companyId,
			String areaCode, String corpId);
}
