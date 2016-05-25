package org.isotope.jfp.framework.constants;

/**
 * 缓存中心关键字
 * 
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 */
public interface ISRedisConstants {
	// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	/**
	 * 云端服务
	 */
	public static final String API_CLOUND_BIZ = "clound";
	/**
	 * 对接业务
	 */
	public static final String API_SYNC_NORMAL = "sync";
	public static final String API_SYNC_CHECK = "synccheck";
	/**
	 * 日常业务
	 */
	public static final String API_BIZ_NORMAL = "biz";
	public static final String API_BIZ_RESULT = "bizresult";

	// 手机业务请求
	public static final String TYPE_Service = ":msr";
	// 对接业务请求
	public static final String TYPE_Bussiness = ":biz";
	// 数据同步请求
	public static final String TYPE_Synchronization_Request = ":sync";
	// 数据同步结果
	public static final String TYPE_Synchronization_Result = ":result";

	// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 缓存Key定义
	public static final String MQ_KEY_BIZ = "Business";// 日常业务
	public static final String MQ_KEY_HOS = "Company";// 企业
	public static final String MQ_KEY_HEART = "Heart";// 心跳
	public static final String MQ_KEY_SEC = "Secyrity";
	
	public static final String CONFIG_KEY = "COMPANY:CONFIG";
	public static final String COMPANY = "COMPANY";
	
}
