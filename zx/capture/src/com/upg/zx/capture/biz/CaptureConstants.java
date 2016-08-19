package com.upg.zx.capture.biz;

/**
 * 常量定义
 * @author 001745
 *
 */
public interface CaptureConstants {
//	/**
//	 * 验证码抓取队列
//	 */
//	public static final String COMPANY_CAP = "COMPANY:CAP";
//	////////////////////////////静态抓取///////////////////////////////////
//	//抓取库数据保存
//	/**
//	 * 关键字队列
//	 */
//	public static final String COMPANY_COM = "COMPANY:COM";
//	/**
//	 * 待抓取企业队列
//	 */
//	public static final String COMPANY_INFO = "COMPANY:INFO";
//	////////////////////////////动态抓取///////////////////////////////////
//	//生产库数据保存
//	/**
//	 * 量子数据同步保存队列
//	 */
//	public final static String TASK_KEY = "COMPANY:JobList";
//	/**
//	 * 企业信息查看队列：企业名字检索
//	 */
//	public static final String COMPANY_INFO_EXT = "COMPANY:EXT";
//	/**
//	 * 企业名单检索队列：关键字检索<br>
//	 * 同步用户动作，用于增加关键字产生数据量<br>
//	 * 该关键字有可能是企业名称
//	 */
//	public static final String COMPANY_KEYWORD_SEARCH = "COMPANY:SEARCH";
//	
//	/**
//	 * 同步检索平台业务服务类
//	 */
//	public static final String CompanyCapture = "CompanyCaptureBusiness";

	//打码服务器地址
	public static String ARTIFICIAL_SERVICE_HOST = "http://127.0.0.1:8888/Zheng";
	
	/**
	 * 等待人工打码任务完成作业队列
	 */
	public static final String ARTIFICIAL_JOBS_KEY = "COMPANY:INFO:JOBS";
	/**
	 *  企业失败队列
	 */
	public static final String ARTIFICIAL_CODE_FAILD = "COMPANY:CODE:FAILD";
	/**
	 * 人工识别验证码队列
	 */
	public static final String ARTIFICIAL_CODE_LIST = "ARTIFICIAL:CodeList";	
	/**
	 * 人工抓取网页信息队列
	 */
	public static final String ARTIFICIAL_CAP = "ARTIFICIAL:CAP";
	////////////////////////////静态抓取///////////////////////////////////
	//抓取库数据保存(静态)
	/**
	 * 关键字队列
	 */
	public static final String COMPANY_COM = "COMPANY:COM";
	/**
	 * 待抓取企业队列(共通)
	 */
	public static final String COMPANY_INFO = "COMPANY:INFO";
	////////////////////////////动态抓取///////////////////////////////////
	//生产库数据保存
	/**
	 * 量子数据同步保存队列
	 */
	public final static String TASK_KEY = "COMPANY:JobList";
	/**
	 * 企业信息查看队列：企业名字检索
	 */
	public static final String COMPANY_INFO_EXT = "COMPANY:EXT";
	/**
	 * 企业名单检索队列：关键字检索<br>
	 * 同步用户动作，用于增加关键字产生数据量<br>
	 * 该关键字有可能是企业名称
	 */
	public static final String COMPANY_KEYWORD_SEARCH = "COMPANY:SEARCH";
	
	/**
	 * 同步检索平台业务服务类
	 */
	public static final String CompanyCapture = "CompanyCaptureBusiness";
	

}

