package org.jfpc.common.dict.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.jfpc.common.dict.dbo.DictionaryDBO;

/**
 * 数据字典数据访问接口
 * 
 * @author yangpeiliang
 * 
 */
public class DictionaryService {
	protected SqlSession mySqlSession;
	
	public SqlSession getMySqlSession() {
		return mySqlSession;
	}

	public void setMySqlSession(SqlSession mySqlSession) {
		this.mySqlSession = mySqlSession;
	}	
	
	/**
	 * 标准字典查询(根据中分类ID获取字典信息)
	 */
	public List<DictionaryDBO> getDictionaryDefault(DictionaryDBO dbParamBean) {
		return ((DictionaryDao) mySqlSession.getMapper(DictionaryDao.class)).getDictionaryDefault(dbParamBean);
	}

	/**
	 * 获取字典名称
	 * 
	 * @param middleTypeId
	 * @param smallTypeId
	 * @return
	 */
	public String getDictNameBySmallTypeId(String middleTypeId, String smallTypeId) {
		DictionaryDBO dictionaryDBO = new DictionaryDBO();
		dictionaryDBO.setK02_middle_type_id(middleTypeId);
		dictionaryDBO.setK03_small_type_id(smallTypeId);
		List<DictionaryDBO> dictionaryDBOList = this.getDictionaryDefault(dictionaryDBO);
		if (!dictionaryDBOList.isEmpty()) {
			return dictionaryDBOList.get(0).getF04_small_type_name();
		} else {
			return "";
		}
	}

	/**
	 * 获得字典名称
	 */
	public String getDictionaryName(String dbPuk) {
		return ((DictionaryDao) mySqlSession.getMapper(DictionaryDao.class)).getDictionaryName(dbPuk);
	}
}
