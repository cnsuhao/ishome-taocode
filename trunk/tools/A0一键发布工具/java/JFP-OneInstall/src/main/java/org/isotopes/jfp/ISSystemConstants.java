package org.isotopes.jfp;

/**
 * 系统常量
 * @since 0.1 
 * @version 0.2 2012-9-14 标准化命名
 */
public interface ISSystemConstants {
	/**
	 * 空字符串
	 */
	public final static String CONSTANT_USER_TOKEN = "usertoken";
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
	public final static String BASE_SPLIT = ";";
	public final static String DBKEY_SPLIT = ".";

	/* 数据基本操作 */
	public enum DB_MANAGER_TYPE {
		CREAT, INIT, BUILD, NONE;
	}
}
