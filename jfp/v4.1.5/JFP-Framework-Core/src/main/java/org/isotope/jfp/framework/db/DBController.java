package org.isotope.jfp.framework.db;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 外部初始化数据库模式
 * 
 * @since 0.1
 * @version 0.1
 */
//@Controller
//@RequestMapping("/DBC")
public class DBController
{
	@Resource
	protected DBDao Mapper_;

	@RequestMapping(value = "/Creat", method = RequestMethod.GET)
	public boolean creat() throws Exception {
		try {
			Mapper_.creat();
		} catch (Exception e) {
			throw e;
		}
		return true;
	}

	/**
	 * 初始化数据库环境
	 */
	@RequestMapping(value = "/Init", method = RequestMethod.GET)
	public boolean init() throws Exception {
		try {
			// Mapper_.init();
		} catch (Exception e) {
			throw e;
		}
		return true;
	}

	@RequestMapping(value = "/Build", method = RequestMethod.GET)
	public boolean build() throws Exception {
		try {
			Mapper_.build();
		} catch (Exception e) {
			throw e;
		}
		return true;
	}
	
	@RequestMapping(value = "/Test", method = RequestMethod.GET)
	public boolean test() throws Exception {
		try {
			Mapper_.build();
		} catch (Exception e) {
			throw e;
		}
		return true;
	}
}
