package com.upg.zx.domain.capture.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.upg.zx.capture.bean.CorpBase;
import com.upg.zx.capture.bean.ServiceConfig;
import com.upg.zx.capture.task.CompanyTask;
import com.upg.zx.capture.util.HttpClientUtil;
import com.upg.zx.domain.capture.Capture;
import com.upg.zx.domain.capture.bean.RamCache;
import com.upg.zx.domain.capture.service.CaptureService;
import com.upg.zx.domain.entity.AnalysisTemplate;
import com.upg.zx.domain.entity.RequestInfo;
import com.upg.zx.domain.response.CorpBaseRes;
import com.upg.zx.parser.URLParser;
import com.upg.zx.web.utils.JsoupUtil;
import com.upg.zx.web.utils.StringUtil;
import com.zxlh.comm.async.service.AsyncService;

/**
 * 数据抓取超类
 * 
 * @author lujf
 * 
 */
public class CaptureImp implements Capture {
	// 日志
	protected final Logger log = LoggerFactory.getLogger(CaptureImp.class);

	private List<CaptureService> captureServices;

	private AsyncService asyncService;

	private URLParser urlParser;

	// 公司基本信息类型
	private final static String COMPANY_BASEINFO_TYPE = "4";

	public void setCaptureServices(List<CaptureService> captureServices) {
		this.captureServices = captureServices;
	}

	public void setAsyncService(AsyncService asyncService) {
		this.asyncService = asyncService;
	}

	public void setUrlParser(URLParser urlParser) {
		this.urlParser = urlParser;
	}

	/**
	 * 获取公司列表信息
	 * 
	 * @param code
	 * @param companyName
	 * @param areaCode
	 * @param jsessionid
	 * @throws Exception 
	 */
	public CorpBaseRes findCompanyByName(String companyName, String authCode, String jsessionid, String areaCode) throws Exception {
		CorpBaseRes corpBaseRes = new CorpBaseRes();
		if (captureServices == null || captureServices.size() == 0) {
			log.error("capturels 为空!");
		}
		for (CaptureService captureService : captureServices) {
			if (captureService.supports(areaCode))
				try {
					{
						// 抓取数据
						log.info(companyName + "||" + captureService.getClass().getName());
						corpBaseRes = captureService.captureCompany(companyName, authCode, jsessionid);
						break;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return corpBaseRes;
	}

	/**
	 * @param companyId
	 * @param corpId
	 * @param areaCode
	 *            获取公司信息html
	 * @throws Exception 
	 */

	public String getCompanyInfoHtml(String companyId, String areaCode, String corpId, String subId) throws Exception {
		CorpBase corpBase = null;
		String html = "";
		for (CaptureService captureService : captureServices) {
			if (captureService.supports(areaCode)) {
				// 获取请求信息
				// 获取请求信息
				List<RequestInfo> rels;
				if (subId != null && !"".equals(subId)) {
					rels = RamCache.requestlistMap.get(areaCode + "_" + subId);
				} else {
					rels = RamCache.requestlistMap.get(areaCode);
				}
				for (int i = 0; i < rels.size(); i++) {
					RequestInfo requestInfo = rels.get(i);
					if ("0".equals(requestInfo.getInfoType())) {

					} else {
						// 基本信息
						if (COMPANY_BASEINFO_TYPE.equals(requestInfo.getModeType())) {
							RequestInfo copyRequestInfo = new RequestInfo();
							try {
								PropertyUtils.copyProperties(copyRequestInfo, requestInfo);
							} catch (Exception e) {
								e.printStackTrace();
							}
							html = captureService.getCompanyBaseInfo(companyId, copyRequestInfo, null);

							break;
						}
					}
				}

				try {
					String proxy_addr = CompanyTask.ipAddressForList.get();
					int proxy_port = CompanyTask.portForList.get();
					asyncService.runTask(this, "uploadOtherHtml",
							new Object[] { rels, companyId, corpId, areaCode, html, proxy_addr, proxy_port }, null,
							null, 5000, true);
				} catch (InterruptedException e) {
					log.error("uploadOtherHtml异步调用失败!" + e.getMessage());
				}
				break;
			}
		}
		return html;
	}

	/**
	 * 上传其他信息的html
	 * 
	 * @param rels
	 * @param companyId
	 * @param corpId
	 * @param areaCode
	 */
	public void uploadOtherHtml(List<RequestInfo> rels, String companyId, String corpId, String areaCode,
			String templateHtml, String proxy_addr, int proxy_port) {
		CompanyTask.ipAddressForList.set(proxy_addr);
		CompanyTask.portForList.set(proxy_port);
		for (CaptureService captureService : captureServices) {
			if (captureService.supports(areaCode)) {
				for (RequestInfo requestInfo : rels) {
					if ("0".equals(requestInfo.getInfoType())) {
						continue;
					}
					if (COMPANY_BASEINFO_TYPE.equals(requestInfo.getModeType())) {
						continue;
					}

					RequestInfo copyRequestInfo = new RequestInfo();
					String html = "";
					try {
						PropertyUtils.copyProperties(copyRequestInfo, requestInfo);
						html = captureService.getCompanyBaseInfo(companyId, copyRequestInfo, templateHtml);
					} catch (Exception e) {
						e.printStackTrace();
					}

					String url = serviceConfig.getServiceConfig("postOtherInfoUrl") + "?corpId=" + corpId + "&areaCode="
							+ areaCode + "&headId=" + requestInfo.getId();

					if ("".equals(html)) {
						return;
					}
					try {
						asyncService.runTask(this, "uploadCompanly", new Object[] { url, html }, null, null, 5000,
								true);
					} catch (InterruptedException e) {
						log.error("uploadOtherHtml异步调用失败!" + e.getMessage());
					}
				}
				break;
			}
		}
	}

	/**
	 * 获取公司信息（公司数据类型属于html模板+json 类型 只用于江苏）
	 * 
	 * @param companyId
	 * @param areaCode
	 * @param corpId
	 * @return
	 * @throws Exception 
	 */
	public String[] getCompanyInfoByHtmlTemplate(String companyId, String areaCode, String corpId) throws Exception {
		CorpBase corpBase = null;
		String[] restArray = new String[2];
		for (CaptureService captureService : captureServices) {
			if (captureService.supports(areaCode)) {
				// 获取请求信息
				List<RequestInfo> rels = RamCache.requestlistMap.get(areaCode);
				// 获取模板
				String template_html = "";
				// json格式字符串
				String json_str = "";
				// js内容
				String js_str = "";
				final List<RequestInfo> requestToRemove = new ArrayList<RequestInfo>();
				// 解析
				List<AnalysisTemplate> analysisTemplatels = new ArrayList<AnalysisTemplate>();
				// 请求需要传递的参数
				Map<String, Map<String, String>> modeParams = null;

				Map<String, String> map = StringUtil.parseMap(companyId, ",", "=");
				// 获取参数信息
				for (int i = 0; i < rels.size(); i++) {
					RequestInfo requestInfo = rels.get(i);
					RequestInfo copyRequestInfo = new RequestInfo();

					try {
						PropertyUtils.copyProperties(copyRequestInfo, requestInfo);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// 获取模板
					if ("0".equals(copyRequestInfo.getInfoType()) && "-1".equals(copyRequestInfo.getModeType())) {
						// 获取请求地址
						copyRequestInfo.setRurl("http://" + copyRequestInfo.getHost() + map.get("1"));
						template_html = captureService.getCompanyBaseInfo(companyId, copyRequestInfo, null);
						String fileName = copyRequestInfo.getRurl()
								.substring(copyRequestInfo.getRurl().lastIndexOf("/") + 1);
						String js_url = JsoupUtil.parseJSURL(fileName, template_html);
						copyRequestInfo.setRurl("http://" + copyRequestInfo.getHost() + js_url);
						copyRequestInfo.setParams("");
						copyRequestInfo.setRequestType("get");
						js_str = captureService.getCompanyBaseInfo(companyId, copyRequestInfo, null);
						modeParams = urlParser.parserForURL(template_html, js_str);
					}
				}
				for (int i = 0; i < rels.size(); i++) {
					RequestInfo requestInfo = rels.get(i);
					RequestInfo copyRequestInfo = new RequestInfo();

					try {
						PropertyUtils.copyProperties(copyRequestInfo, requestInfo);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// 解析参数js以及html转换为参数
					if (!"0".equals(copyRequestInfo.getInfoType())) {
						// 基本信息
						if (COMPANY_BASEINFO_TYPE.equals(copyRequestInfo.getModeType())) {
							Map<String, String> param = modeParams.get("基本信息");
							// 设置基本信息请求地址
							copyRequestInfo.setRurl(
									"http://" + copyRequestInfo.getHost() + "/" + map.get("6") + param.get("json_url"));
							param.remove("json_url");
							copyRequestInfo.setParams(StringUtil.paseMapToStr(param, ",", "="));
							json_str = captureService.getCompanyBaseInfo(companyId, copyRequestInfo, null);
							restArray[0] = json_str;
							restArray[1] = template_html;
						}

					}
				}
				try {
					asyncService.runTask(this, "getCompanyInfoByHtmlTemplateOther",
							new Object[] { template_html, modeParams, rels, areaCode, companyId, corpId }, null, null,
							5000, true);
				} catch (InterruptedException e) {
					log.error("uploadOtherHtml异步调用失败!" + e.getMessage());
				}
				break;
			}
		}

		return restArray;

	}

	/**
	 * 获取公司其他信息(只用于江苏)
	 * 
	 * @param template_html
	 * @param modeParams
	 * @param rels
	 * @param areaCode
	 * @param companyId
	 * @param corpId
	 * @return
	 * @throws Exception 
	 */
	public void getCompanyInfoByHtmlTemplateOther(String template_html, Map<String, Map<String, String>> modeParams,
			List<RequestInfo> rels, String areaCode, String companyId, String corpId) throws Exception {
		List<Object> rsls = new ArrayList<Object>();
		Map<String, String> map = StringUtil.parseMap(companyId, ",", "=");
		String tag = "items";
		for (CaptureService captureService : captureServices) {
			if (captureService.supports(areaCode)) {
				for (RequestInfo requestInfo : rels) {
					Map<String, String> param_map = null;
					// 股东信息
					if ("5".equals(requestInfo.getModeType())) {
						param_map = modeParams.get("出资信息");
					} else if ("6".equals(requestInfo.getModeType())) {
						param_map = modeParams.get("变更信息");
					} else if ("7".equals(requestInfo.getModeType())) {
						param_map = modeParams.get("主要人员信息");
					} else if ("8".equals(requestInfo.getModeType())) {
						param_map = modeParams.get("分公司信息");
					}

					if (param_map == null) {
						continue;
					}

					RequestInfo copyRequestInfo = new RequestInfo();
					try {
						PropertyUtils.copyProperties(copyRequestInfo, requestInfo);
					} catch (Exception e) {
						e.printStackTrace();
					}

					copyRequestInfo.setRurl(
							"http://" + copyRequestInfo.getHost() + "/" + map.get("6") + param_map.get("json_url"));
					param_map.remove("json_url");
					copyRequestInfo.setParams(StringUtil.paseMapToStr(param_map, ",", "="));
					String json_str = captureService.getCompanyBaseInfo(companyId, copyRequestInfo, null);
					if (!"".equals(template_html) && !"".equals(json_str)) {
						Map<String, String> param = new HashMap<String, String>();
						param.put("htmlTemplate", template_html);
						param.put("json", json_str);
						param.put("areaCode", areaCode);
						param.put("headId", copyRequestInfo.getId().toString());
						param.put("corpId", corpId.toString());
						param.put("modeType", copyRequestInfo.getModeType());
						try {
							asyncService.runTask(this, "uploadCompany",
									new Object[] { serviceConfig.getServiceConfig("postOtherInfoJsUrl"), param }, null,
									null, 5000, true);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

	}

	ServiceConfig serviceConfig;

	public ServiceConfig getServiceConfig() {
		return serviceConfig;
	}

	public void setServiceConfig(ServiceConfig serviceConfig) {
		this.serviceConfig = serviceConfig;
	}

	/**
	 * 上传公司信息
	 * 
	 * @param url
	 * @param html
	 * @throws Exception
	 */
	public void uploadCompanly(String url, String html) throws Exception {
		HttpClientUtil.postRequest(url, html);
	}

	/**
	 * 上传公司信息
	 * 
	 * @param url
	 * @param map
	 * @throws Exception
	 */
	public void uploadCompany(String url, Map<String, String> map) throws Exception {
		HttpClientUtil.postRequest(url, map);
	}

}
