package org.jfpc.common.db;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 内部初始化数据库模式
 * 
 * @since 0.1
 * @version 0.2 2012-9-17 标准化开发
 * @version 0.1
 */
@Service
public class DBService
{

	@Resource
	protected DBDao dao_;

	/**
	 * 创建空的数据库环境
	 */
	public boolean creat() throws Exception {
		try {
			dao_.creat();
		} catch (Exception e) {
			throw e;
		}
		return true;
	}

	/**
	 * 初始化数据库环境
	 */
	public boolean init() throws Exception {
		try {
			dao_.init();
		} catch (Exception e) {
			throw e;
		}
		return true;
	}

	/**
	 * 重构数据库环境
	 */
	public boolean build() throws Exception {
		try {
			dao_.build();
		} catch (Exception e) {
			throw e;
		}
		return true;
	}
	
	/**
	 * 数据库连接测试
	 */
	public boolean test() throws Exception{
		try {
			dao_.test();
		} catch (Exception e) {
			throw e;
		}
		return true;
	}
	
}
