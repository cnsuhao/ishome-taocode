package com.mcookies.qxy.common.SLabel;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 教工标签表 */
@Service
public class SLabelService extends MyServiceSupport {

	public SLabelDao getDao() {
		return getMySqlSession().getMapper(SLabelDao.class);
	}
	
	public List<? extends FrameworkDataBean> doselectTeacherLabel(MyDataBaseObjectSupport paramBean){
		return getDao().doselectTeacherLabel(paramBean);
	}

}
