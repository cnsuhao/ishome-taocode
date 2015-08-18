package org.ishome.jfp.framework.constants;

/**
 * 系统常量
 * 
 * @since 0.1
 * @version 0.2 2012-9-14 标准化命名
 * @version 0.1 2012-7-13
 */
public interface ISDBConstants {

	public final static String FILE_EXTENSION_NAME_SQL = ".sql";
	public final static String DROP_TABLE = "DROP TABLE ";
	public final static String MESSAGE_STATEMENT_START = ">>>>>数据库连接获得成功>>>>>";
	public final static String MESSAGE_STATEMENT_END = "<<<<<数据库处理结束<<<<<";
	public final static String MESSAGE_SQLLOCATIONS_NULL = "没有找到任何SQL配置文件";

	public final static String MESSAGE_DATALOCATIONS_NULL = "没有找到任何初始化数据文件";
	public final static String TABLE_NAME_TEST = "cssb03";
	public final static String MESSAGE_TEST = "数据库连接测试，操作表" + TABLE_NAME_TEST;

	public final static String DB_TCASSANDRA = "Cassandra";
	public final static String DB_MYSQL = "MySql";
	public final static String DB_DEFAULT = "MySql";
	public final static String DB_TYPE_CASSANDRA = "C";
	public final static String DB_TYPE_MYSQL = "M";

	public final static int DB_SELECT = 0;
	public final static int DB_INSERT = 1;
	public final static int DB_UPDATE = 2;
	public final static int DB_DELETE = 3;
}
