package org.isotopes.jfp.framework.db.service;

import javax.annotation.Resource;

import org.isotopes.jfp.framework.db.dao.DBDao;
import org.springframework.stereotype.Service;

/**
 * 内部初始化数据库模式
 * 
 * @since 0.1
 * @version 0.2 2012-9-17 标准化开发
 * @version 0.1
 */
public class DBService
{

	@Resource
	protected DBDao DBDao_;

	/**
	 * 创建空的数据库环境
	 */
	public boolean creat() throws Exception {
		try {
			DBDao_.creat();
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
			DBDao_.init();
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
			DBDao_.build();
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
			DBDao_.test();
		} catch (Exception e) {
			throw e;
		}
		return true;
	}
	
}
