package org.isotopes.jfp.framework.db.controller;

import java.util.List;

import javax.annotation.Resource;

import org.isotopes.jfp.framework.db.bean.SQLVersionBean;
import org.isotopes.jfp.framework.db.biz.SQLListBuiness;
import org.isotopes.jfp.framework.db.service.DBService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 外部初始化数据库模式
 * 
 * @since 0.1
 * @version 0.1
 */
@Controller
@RequestMapping("/DBC")
public class DBController
{
	@Resource
	protected SQLListBuiness SQLListBuiness_;
	
	@Resource
	protected DBService  DBService_;

	@ResponseBody
	@RequestMapping(value = "/List", method = RequestMethod.GET)
	public List<SQLVersionBean> list() {
		
		//解析XML获得全部SQL清单
		return SQLListBuiness_.readSQLNameListFromXML();
	}

	@ResponseBody
	@RequestMapping(value = "/Creat", method = RequestMethod.GET)
	public boolean creat() throws Exception {
		try {
			DBService_.creat();
		} catch (Exception e) {
			throw e;
		}
		return true;
	}

	/**
	 * 初始化数据库环境
	 */
	@ResponseBody
	@RequestMapping(value = "/Init", method = RequestMethod.GET)
	public boolean init() throws Exception {
		try {
			DBService_.creat();
		} catch (Exception e) {
			throw e;
		}
		return true;
	}

	@ResponseBody
	@RequestMapping(value = "/Build", method = RequestMethod.GET)
	public boolean build() throws Exception {
		try {
			DBService_.build();
		} catch (Exception e) {
			throw e;
		}
		return true;
	}

	@ResponseBody
	@RequestMapping(value = "/Test", method = RequestMethod.GET)
	public boolean test() throws Exception {
		try {
			DBService_.build();
		} catch (Exception e) {
			throw e;
		}
		return true;
	}
}
