package org.jfpc.base;

import org.jfpc.base.helper.Constants;

/**
 * 系统常量
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/2/8
 */
public interface ISFrameworkConstants extends Constants{
	public final static String PKG_JFPC = "org.jfpc";
	
	public final static String SYSTEM_ERROR_CODE = "9999";
	public final static String SYSTEM_ERROR_MESSAGE = "系统繁忙，请稍后再试！";
	public final static String TREE_LEVEL_TAG = "@@";
	
	
	/**
	 * 用户TONKEN
	 */
	public final static String CONSTANT_USER_TOKEN = "token";
	
	
	/**
	 * 防伪码
	 */
	public final static String CONSTANT_SECURITY_CODE = "securityCode";
	
	/**
	 * 缓存有效时间
	 */
	public final static int CACHE_EXPIRATION = 3600;
	/**
	 * 空字符串
	 */
	public final static String EMPTY = "";
	/**
	 * 半角空格
	 */
	public final static String BLANK = " ";
	public final static String SEMICOLON = ";";//semicolon 	

	public static final String BACKSLASH = "/";//backslash
	
	public final static String DOT = ".";

	/* 数据基本操作 */
	public enum DB_MANAGER_TYPE {
		CREAT, INIT, BUILD, NONE;
	}
}
