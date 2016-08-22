package com.upg.zx.domain.capture.service;

public interface IAppService {
	//人工打码接口(打码服务器接口)
	public static String ARTIFICIAL_CODE = "/C310/011";
	//人工列表以及详细信息获取接口(打码服务器接口)
	public static String ARTIFICIAL_CAPTURE = "/C310/030";
	//从量子接口检索列表获取待打码企业接口(打码服务器)COMPANY:JobList
	public static String GET_FOR_CODE_COMPANY = "/M310/0101";
	//打码客户端上传列表html(了解服务接口)
	public static String LIST_UPLOAD = "/Zheng/corp/uploadCompanyListHtmlForClient";
	//本地服务获取的上传列表html(了解接口)
	public static String LIST_UPLOAD_Capture = "/Zheng/corp/uploadCompanyListHtml";
	
}
