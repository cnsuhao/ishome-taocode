package org.jfpc.constants;

/**
 * 系统常量
 * 
 * @author Spook
 * @since 0.1.0 2014/2/8
 * @version 0.2.1 2014/11/05
 * @version 0.1
 */
public interface ISFrameworkConstants extends ISUserConstants {
	
	public static final String FOLDER_SEPARATOR = "/";

	public static final String WINDOWS_FOLDER_SEPARATOR = "\\";

	public static final String TOP_PATH = "..";

	public static final String CURRENT_PATH = ".";

	public static final char EXTENSION_SEPARATOR = '.';
	
	public static String MAIL = "M";
	public static String SMS = "S";
	
	public static String ZERO = "0";
	public static String ONE = "1";
	public static String TWO = "2";
	public static String THREE = "3";
	public static String FOUR = "4";
	
	public static String Public_Key_Cryptography = "";

	public final static String PKG_JFPC = "org.jfpc";

	public final static String SYSTEM_ERROR_CODE = "9999";
	public final static String SYSTEM_ERROR_MESSAGE = "系统繁忙，请稍后再试！";
	public final static String TREE_LEVEL_TAG = "@@";

	public final static String undefined = "undefined";

	/**
	 * 用户TONKEN
	 */
	public final static String CONSTANT_SESSION_ID = "sessionid";
	public final static String CONSTANT_USER_TOKEN = "token";
	public final static String CONSTANT_LOGINER = "loginer";
	//public final static String CONSTANT_COMPANY = "company";
	public final static String CONSTANT_USER_MAIN = "USERMAIN";
	public final static String CONSTANT_SQL_SESSION = "sqlsession";

	/**
	 * 系统根路径
	 */
	public final static String SYSTEM_ROOT = "/resources/psad.html";
	
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
	public final static String SEMICOLON = ";";// semicolon

	public static final String BACKSLASH = "/";// backslash
	public static final String BACKSLASH2 = "//";// backslash
	public static final String FILE_URI = "file://";// FILE_URI

	public final static String DOT = ".";
	public final static String COMMA  = ",";
	public static final String DOWN_LINE = "_";// backslash
	public static final char DOWN_LINE2 = '_';// backslash

	/* 数据基本操作 */
	public enum DB_MANAGER_TYPE {
		CREAT, INIT, BUILD, NONE;
	}
}