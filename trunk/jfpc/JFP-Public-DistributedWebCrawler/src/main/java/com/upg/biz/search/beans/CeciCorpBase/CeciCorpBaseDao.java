package com.upg.biz.search.beans.CeciCorpBase;

import org.isotope.jfp.framework.support.ISDatabaseSupport;
import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

/** 企业基本信息(数据采集)*/
public interface CeciCorpBaseDao extends ISDatabaseSupport{
	
	/**
	 * 重命名当前表
	 * 
	 * @param paramBean
	 */
	int reNameTable(MyDataBaseObjectSupport paramBean);
	
	/**
	 * 创建新表
	 * 
	 * @param paramBean
	 */
	int creatTable(MyDataBaseObjectSupport paramBean);
	
	/**
	 * 数据整理
	 * @param paramBean
	 * @return
	 */
	int changeTable(MyDataBaseObjectSupport paramBean);
}
