package org.isotopes.jfp.framework.common.dao;

import java.util.List;

import org.isotopes.jfp.framework.support.bean.FrameworkDataBean;


/**
 * 字典调用
 */
public interface DictionaryDao {

	/**
	 * 查询全体数据信息
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> getDictionaryOnTable(FrameworkDataBean dbParamBean);
	
	/**
	 * 查询全体数据信息
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> getDictionaryDefault(FrameworkDataBean dbParamBean);
	
	
	/**
	 * 获得字典名称
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public String getDictionaryName(String dbPuk);
	
	/**
	 * 任意表，根据ID获得名称
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public String getNameOnTable(FrameworkDataBean dbParamBean);
	
}
