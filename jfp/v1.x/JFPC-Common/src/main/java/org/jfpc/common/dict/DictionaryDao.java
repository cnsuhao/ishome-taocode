package org.jfpc.common.dict;

import java.util.List;

import org.jfpc.framework.support.ISDatabaseSupport;

/**
 * 数据字典数据访问接口
 * 
 * @author yangpeiliang
 * 
 */
public interface DictionaryDao extends ISDatabaseSupport{

	/**
	 * 根据中分类ID获取字典信息
	 * 
	 * @param k02_middle_type_id
	 * @return
	 */
	public List<DictionaryDBO> getDictionarByMiddleTypeId(String middle_type_id);
		
	/**
	 * 查询全体数据信息
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<DictionaryDBO> getDictionaryDefault(DictionaryDBO dbParamBean);
	
	
	/**
	 * 获得字典名称
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public String getDictionaryName(String dictionaryPuk);
	
//	/**
//	 * 任意表，查询全体数据信息
//	 * 
//	 * @param dbParamBean
//	 * @return
//	 */
//	public List<MyDataBaseObjectSupport> getDictionaryOnAnyTable(MyDataBaseObjectSupport dbParamBean);
//	
//	/**
//	 * 任意表，根据ID获得名称
//	 * 
//	 * @param dbParamBean
//	 * @return
//	 */
//	public String getNameOnAnyTable(MyDataBaseObjectSupport dbParamBean);
}
