package com.mcookies.qxy.common.UParent;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

import com.mcookies.qxy.common.UStudent.UStudentDBO;

/** 家长表 */
@Service
public class UParentService extends MyServiceSupport {

	public UParentDao getDao() {
		return getMySqlSession().getMapper(UParentDao.class);
	}

	/**
	 * 根据studentId和isUse查找学生家长信息
	 * @param student
	 * @return
	 */
	public List<UParentPVO> findByStudentId(UStudentDBO student) {
		return getDao().findByStudentId(student);
	}
	
	
	@SuppressWarnings("unused")
	public PageVOSupport doSelectPageByTermIdAndCidAndStudentId(PageVOSupport pageModel) {
		FrameworkDataBean formParamBean = pageModel.getFormParamBean();
		pageModel.setPageListData(getDao().doSelectPageByTermIdAndCidAndStudentId(pageModel));
		return pageModel;
	}

	public UParentDBO doReadByUParentUid(UParentDBO uParentDBO) {
		return getDao().doReadByUParentUid(uParentDBO);
	}
	
	public UParentPVO findByParentId(UParentPVO pvo) {
		return getDao().findByParentId(pvo);
	}
	
}
