package com.mcookies.qxy.common.School;

import java.util.List;
import java.util.Map;

import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 学校表 */
@Service
public class SchoolService extends MyServiceSupport {

	public SchoolDao getDao() {
		return getMySqlSession().getMapper(SchoolDao.class);
	}

	/**
	 * 根据isUse(可以不传)进行查询
	 * 
	 * @param school
	 * @return
	 */
	public List<SchoolDBO> doSelectByIsUse(SchoolDBO school) {
		return getDao().doSelectByIsUse(school);
	}
	
	public List<SchoolDBO> doSelectSchoolByTypeAndUid(Map<String,Object> param){
		return getDao().doSelectSchoolByTypeAndUid(param);
	}

}
