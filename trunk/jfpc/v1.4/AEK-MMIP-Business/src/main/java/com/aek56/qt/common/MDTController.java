package com.aek56.qt.common;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aek56.atm.master.MD2_GYSXX.MD2_GYSXXDBO;
import com.aek56.atm.master.MD2_GYSXX.MD2_GYSXXService;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXDBO;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXService;
import com.aek56.atm.master.MD4_CSXX.MD4_CSXXDBO;
import com.aek56.atm.master.MD4_CSXX.MD4_CSXXService;
import com.aek56.atm.master.MDG_ZCZXX.MDG_ZCZXXDBO;
import com.aek56.atm.master.MDG_ZCZXX.MDG_ZCZXXService;
import com.aek56.atm.master.md.MasterDataService;

@Controller
public class MDTController extends MyControllerSupport {

	@Resource
	MD2_GYSXXService MD2_GYSXXService_;
	@Resource
	MD3_YYXXService MD3_YYXXService_;
	@Resource
	MD4_CSXXService MD4_CSXXService_;
	@Resource
	MDG_ZCZXXService MDG_ZCZXXService_;
	@Resource
	MasterDataService MasterDataService_;

	/**
	 * 获得主数据记录数目
	 * 
	 * @param q
	 *            参数
	 * @param t
	 *            类型
	 * @param l
	 *            返回记录数目
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "/222", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
	public RESTResultBean m2001040POST() throws Exception {
		RESTResultBean rs = new RESTResultBean();
		MyDataBaseObjectSupport dbo;
		MD2_GYSXXService_.setMySqlSession(super.loginService.getMySqlSession());
		MD3_YYXXService_.setMySqlSession(super.loginService.getMySqlSession());
		MD4_CSXXService_.setMySqlSession(super.loginService.getMySqlSession());
		MDG_ZCZXXService_.setMySqlSession(super.loginService.getMySqlSession());
		for (int i = 1; i < 11; i++) {
			for (Map<String, Object> md : MasterDataService_.getMasterAll(11, i, 1000)) {
				dbo = new MD2_GYSXXDBO();
				BeanUtils.copyProperties(dbo, md);
				MD2_GYSXXService_.doInsert(dbo);
			}
			for (Map<String, Object> md : MasterDataService_.getMasterAll(12, i, 1000)) {
				dbo = new MD3_YYXXDBO();
				BeanUtils.copyProperties(dbo, md);
				MD3_YYXXService_.doInsert(dbo);
			}
			for (Map<String, Object> md : MasterDataService_.getMasterAll(13, i, 1000)) {
				dbo = new MD4_CSXXDBO();
				BeanUtils.copyProperties(dbo, md);
				MD4_CSXXService_.doInsert(dbo);
			}
			for (Map<String, Object> md : MasterDataService_.getMasterAll(30, i, 1000)) {
				dbo = new MDG_ZCZXXDBO();
				BeanUtils.copyProperties(dbo, md);
				MDG_ZCZXXService_.doInsert(dbo);
			}
		}
		// 根据名称，从数据库里面查询最新5条
		return rs;
	}
}
