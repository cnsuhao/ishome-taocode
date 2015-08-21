package com.hundsun.med.hdp.constants;

import com.hundsun.med.access.constants.IAccessTypeConstants;

/**
 * 常量定义
 * 
 * @author fucy
 * @version 2.3.1 2015/7/3
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public interface IHDPConstants extends IAccessTypeConstants {
	

	public static final String INIT_MESSAGE 	= "初始化前置机对接(0_0)";
	public static final String SUCCESS_MESSAGE 	= "前置机对接处理成功(^o^)";
	public static final String ERROR_MESSAGE 	= "前置机对接处理失败(>_<)";
	public static final String FAILED_MESSAGE 	= "前置机对接处理异常(T＿T)";
	
	
	public static final String SERVICE_TDEPT 		= "departmentDataSyncService";// 科室
	public static final String SERVICE_TDOC 		= "doctorDataSyncService";// 医生
	public static final String SERVICE_TSCHEDULE 	= "schedulingDataSyncService";// 排班
	
}
