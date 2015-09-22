package com.aek56.atm.master.md;

import java.util.List;
import java.util.Map;

import org.jfpc.framework.support.ISDatabaseSupport;

/**
 * 主数据检索
 * @author Administrator
 *
 */
public interface MasterDataDao extends ISDatabaseSupport {

	/**
	 * 获得近似数据
	 * @param p
	 * @return
	 */
	public List<Map<String, Object>> getTop10(Map<String, Object> p);
	
	/**
	 * 查询一条记录(主键)
	 * 
	 * @param paramBean
	 */
	public Map<String, Object> getMaster(Map<String, Object> p);
	
	/**
	 * 查询主数据
	 * @param p
	 * @return
	 */
	public Map<String, Object> getMasterData(Map<String, Object> p);

	/**
	 * 统计主数据记录数目
	 * @return
	 */
	public Map<String, Object> getMasterCount(Map<String, Object> p);
	
	/**
	 * 查询所有记录
	 * 
	 * @param paramBean
	 */
	public List<Map<String, Object>> getMasterAll(Map<String, Object> p);
	
}
