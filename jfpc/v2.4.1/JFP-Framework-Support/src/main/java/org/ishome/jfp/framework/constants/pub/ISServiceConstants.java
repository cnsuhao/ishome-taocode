package org.ishome.jfp.framework.constants.pub;

/**
 * 后台服务常量定义
 * 
 * @author fucy
 * @version 2.0.1
 * @since 2.0.0 2015/2/3
 *
 */
public interface ISServiceConstants {

	//****************************↓↓↓↓接口配置文件关键字↓↓↓↓↓↓*************************************
	public static final String System_Default_Version = "911";

	// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
	// 错误消息类型
	public static final String ACCESSS_RTN_CODE_SUCCESS = "0";
	public static final String ACCESSS_RTN_CODE_ERROR_NODATA = "-1";// 对接医院无对应数据
	public static final String ACCESSS_RTN_CODE_ERROR_TIMEOUT = "-2";// 调用对接接口超时
	public static final String ACCESSS_RTN_CODE_ERROR_PARAM = "-3";// 参数不正确
	public static final String ACCESSS_RTN_CODE_ERROR_JDBC = "-4";// 数据操作出差
	public static final String ACCESSS_RTN_CODE_ERROR_WS = "-5";// webservice接口调用出错
	public static final String ACCESSS_RTN_CODE_ERROR_NOBEAN = "-6";// 找不到对应的模块的bean
	public static final String ACCESSS_RTN_CODE_ERROR_NOPAT = "-7";// 找不到患者
	public static final String ACCESSS_RTN_CODE_OPERATION_FAIL = "-8";// 操作失败
	public static final String ACCESSS_RTN_CODE_ERROR_HOSPITAL = "-999";// 医院对接取消

	public static final String ACCESSS_RTN_MESSAGE_SUCCESS = "操作成功";
	public static final String ACCESSS_RTN_MESSAGE_ERROR_NODATA = "没有找到对应的信息";
	public static final String ACCESSS_RTN_MESSAGE_ERROR_TIMEOUT = "网络请求超时";
	public static final String ACCESSS_RTN_MESSAGE_ERROR_PARAM = "请求参数错误";
	public static final String ACCESSS_RTN_MESSAGE_ERROR_JDBC = "数据库连接失败";
	public static final String ACCESSS_RTN_MESSAGE_ERROR_WS = "医院服务请求失败";
	public static final String ACCESSS_RTN_MESSAGE_ERROR_NOBEAN = "不能找到对应的实体类";
	public static final String ACCESSS_RTN_MESSAGE_ERROR_NOPAT = "查询信息不匹配";
	public static final String ACCESSS_RTN_MESSAGE_ERROR_VERIFY = "患者校验失败";
	public static final String ACCESSS_RTN_MESSAGE_ERROR_HOSPITAL = "云端服务停止处理数据";

}
