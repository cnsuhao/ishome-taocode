package com.aek56.constants;

/**
 * 网站常量定义
 * @author Administrator
 *
 */
public interface ISWUConstants {
	public static final String Version ="v0.0.1 at 2014.8.21";
	
	/**
	 * 审核通过
	 */
	String AUDIT_STATE_SUCCESS = "0";
	 
	 /**
	  * 审核失败
	  */
	 String AUDIT_STATE_FAIL = "1";
	 
	 /**
	  * 待审
	  */
	 String AUDIT_STATE_WAIT = "2";
	 
	 /**
	  * 审核中
	  */
	 String AUDIT_STATE_ING = "3";
	 
	 /**
	  * 操作类型：新增
	  */
	 String OPT_TYPE_ADD = "1";
	 
	 /**
	  * 操作类型：修改
	  */
	 String OPT_TYPE_EDIT = "2";
	 
	 /**
	  * 企业类别：医院
	  */
	 String QYLB_YIYUAN = "2";
	 
	 /**
	  * 企业类别：供应商
	  */
	 String QYLB_GONGYINGSHANG = "1";
	 
	 /**
	  * 企业类别：厂商
	  */
	 String QYLB_CHANGSHANG = "3";
}
