package org.isotope.jfp.framework.constants.pub;


/**
 * 定时作业常量定义
 * @author fucy
 * @version 2.0.1
 * @since 2.0.0 2015/2/3
 *
 */
public interface ISJobConstants {

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
	// //////////////////////////////////////////////////////////////
	/**
	 * 医院发送数据
	 */
	public static final String HDP = "Hospital data push...";
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

	public static final String JOB_NAME_CLIENT = "ClientService";
	public static final String JOB_NAME_SERVER = "ServerService";	
}
