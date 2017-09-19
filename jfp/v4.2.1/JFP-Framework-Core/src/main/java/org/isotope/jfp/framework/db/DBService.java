package org.isotope.jfp.framework.db;

import javax.annotation.Resource;

import org.isotope.jfp.framework.cache.ICacheService;

/**
 * 内部初始化数据库模式
 * 
 * @since 0.1
 * @version 0.2 2012-9-17 标准化开发
 * @version 0.1
 */
public class DBService {

	@Resource
	protected DBDao dao_;
	@Resource
	protected ICacheService _myCacheService_;

	/**
	 * 创建空的数据库环境
	 */
	public boolean creat() throws Exception {
		try {
			dao_.creat(true, true);
			_myCacheService_.putObject("DBO", "creat");
		} catch (Exception e) {
			e.printStackTrace();
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
			_myCacheService_.putObject("DBO", "init");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	/**
	 * 创建数据库索引环境
	 */
	public boolean index() throws Exception {
		try {
			dao_.index();
			_myCacheService_.putObject("DBO", "index");
		} catch (Exception e) {
			e.printStackTrace();
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
			_myCacheService_.putObject("DBO", "build");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	/**
	 * 数据库连接测试
	 */
	public boolean test() throws Exception {
		try {
			dao_.test();
			_myCacheService_.putObject("DBO", "test");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}

}
