package org.jfpc.common.dict;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.jfpc.constants.ISFrameworkConstants;
import org.jfpc.framework.helper.BeanFactoryHelper;
import org.jfpc.framework.support.MyFrameworkSupport;

/**
 * 数据持久层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class DictionaryService extends MyFrameworkSupport implements ISFrameworkConstants {
	
	/**
	 * 数据库连接
	 */
	private SqlSession mySqlSession;	
	public SqlSession getMySqlSession() {
		if(mySqlSession == null)
			mySqlSession = BeanFactoryHelper.getBean("mySqlSession");
		return mySqlSession;
	}
	/**
	 * 设定SQL连接<br>
	 * 多个Service调用的时候，建议使用手动设定
	 * @param mySqlSession
	 */
	public void setMySqlSession(SqlSession mySqlSession) {
		this.mySqlSession = mySqlSession;
	}

	/**
	 * 标准字典查询(根据中分类ID获取字典信息)
	 */
	public List<DictionaryDBO> getDictionaryDefault(DictionaryDBO dbParamBean) {
		return getMySqlSession().getMapper(DictionaryDao.class).getDictionaryDefault(dbParamBean);
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
		return  getMySqlSession().getMapper(DictionaryDao.class).getDictionaryName(dbPuk);
	}
	
}
