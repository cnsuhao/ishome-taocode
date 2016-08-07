package com.mcookies.qxy.common.UStudent;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 学生表 */
@Service
public class UStudentService extends MyServiceSupport {

	public UStudentDao getDao() {
		return getMySqlSession().getMapper(UStudentDao.class);
	}

	public List<? extends FrameworkDataBean> doSelectListBycid(FrameworkDataBean formParam){
		return getDao().doSelectListBycid(formParam);
	}
}
