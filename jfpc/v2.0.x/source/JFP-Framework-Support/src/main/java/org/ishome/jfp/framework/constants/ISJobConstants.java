package org.ishome.jfp.framework.constants;


/**
 * 定时作业常量定义
 * @author Spook
 * @version 2.0.0 2015/2/3
 * @since 2.0.0 2015/2/3
 *
 */
public interface ISJobConstants {
	
	/**
	 * 加密
	 */
	public static final String ENCRYPTION = "E";
	/**
	 * 解密
	 */
	public static final String DECRYPTION= "D";

//	/**
//	 * 作业开启状态
//	 */
//	public static final String JOB_FLAG_KEY = "JOB_FLAG_";
//
//	/**
//	 * 数据存储状态
//	 */
//	public static final String API_DATA_KEY = "API_DATA_";

	// 表明有线程正在处理
	public static final String JOB_FLAG_RUNNING = "running";
	// 表明的等待云端等待处理
	public static final String JOB_FLAG_WAITING = "waiting";
	// 表明的云端停止处理（对接取消）
	public static final String JOB_FLAG_STOP = "stop";
	// 表明云端处理失败,医院端判断后重新发送数据
	public static final String JOB_FLAG_ERROR = "error";
	// 表明云端处理成功
	public static final String JOB_FLAG_SUCCESS = "success";
	// /////////////////////////////////////////////////////////////////////
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
	// /////////////////////////////////////////////////////////////////////
	// 操作类型
	// //////////////////////////////////////////////////////////////
	/**
	 * 医院发送数据
	 */
	public static final String HDP = "Hospital data push...";

	/**
	 * 开始接收数据
	 */
	public static final String JOB_SEND_START = "Start send =====>>>>>  ";
	/**
	 * 拒绝接收数据
	 */
	public static final String JOB_SEND_CANCEL = "Cancel send xxxxxx ";
	/**
	 * 结束接收数据
	 */
	public static final String JOB_SEND_END = "End   send <<<<<===== ";

	/**
	 * 开始监控数据
	 */
	public static final String JOB_MONITOR_START = "Start monitor =====>>>>>  ";
	/**
	 * 拒绝监控数据
	 */
	public static final String JOB_MONITOR_CANCEL = "Cancel monitor xxxxxx ";
	/**
	 * 结束监控数据
	 */
	public static final String JOB_MONITOR_END = "End   monitor <<<<<===== ";

	/**
	 * 云端数据处理
	 */
	public static final String CDP = "Clound Data Processing...";
	/**
	 * 医院对接同步
	 */
	public static final String HDS = "Hospital docking synchronization...";
	/**
	 * 开始接收数据
	 */
	public static final String JOB_RECEIVE_START = "Start receive =====>>>>>  ";
	/**
	 * 拒绝接收数据
	 */
	public static final String JOB_RECEIVE_CANCEL = "Cancel receive xxxxxx ";
	/**
	 * 结束接收数据
	 */
	public static final String JOB_RECEIVE_END = "End   receive <<<<<===== ";

	/**
	 * 开始处理作业
	 */
	public static final String JOB_START = "Start job =====>>>>> ";
	/**
	 * 拒绝处理作业
	 */
	public static final String JOB_CANCEL = "Cancel JOB xxxxxx ";
	/**
	 * 结束处理作业
	 */
	public static final String JOB_END = "End   JOB <<<<<===== ";

	/**
	 * 开始同步处理数据
	 */
	public static final String JOB_SAVE_START = "Start save =====>>>>>  ";
	/**
	 * 拒绝同步处理数据
	 */
	public static final String JOB_SAVE_CANCEL = "Cancel save xxxxxx ";
	/**
	 * 结束同步处理数据
	 */
	public static final String JOB_SAVE_END = "End   save <<<<<===== ";

	public static final String JOB_CLIENT_NAME = "ClientService";
	public static final String JOB_SERVER_NAME = "ServerService";

	// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓V2.0.5↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	//缓存Key定义
	public static final String MQ_KEY_BIZ = "Business";//日常业务
	public static final String MQ_KEY_HOS = "Hospital";//医院
	public static final String MQ_KEY_HEART = "Heart";//心跳
	public static final String MQ_KEY_SEC = "Secyrity";	
	
	//****************************↓↓↓↓接口配置文件关键字↓↓↓↓↓↓*************************************
	//HS-MED-Hospital-SendData
	//spring-task.xml
	//cloundApiConfig   云端服务接口地址

	public static final String CONFIG_KEY = "HOSPITAL:CONFIG";
	public static final String HOSPITAL = "HOSPITAL";
	//手机业务请求
	public static final String Service_Start = ":msr";
	//对接业务请求
	public static final String Bussiness_Start = ":biz";
	//数据同步请求
	public static final String Synchronization_Start = ":sync";
	//数据同步结果
	public static final String Synchronization_Result = ":result";

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
	// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
}
