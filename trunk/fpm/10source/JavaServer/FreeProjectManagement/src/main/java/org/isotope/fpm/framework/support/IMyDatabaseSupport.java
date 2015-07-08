package org.isotope.fpm.framework.support;


/**
 * 数据库管理环境<br>
 * 初始化接口
 * <p>
 * xxx.sql 标准数据库语句<br>
 * xxx.dat 标准数据库数据操作语句
 * 
 * @since 0.1
 * @version 0.2 2012-9-19 标准化命名
 * @version 0.1
 */
// ACID 是 Atomicity（原子性）、Consistency（一致性）、
// Isolation（隔离性）和 Durability（持久性）的缩略词
public interface IMyDatabaseSupport extends IMyBusinessSupport{

	/**
	 * 创建空的数据库环境
	 */
	public boolean creat() throws Exception;

	/**
	 * 重构数据库环境
	 */
	public boolean build() throws Exception;
}
