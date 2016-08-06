package com.mcookies.qxy.common.Class;
import java.util.List;

import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 班级表*/
@Service
public class ClassService extends MyServiceSupport {

    public ClassDao getDao(){
        return getMySqlSession().getMapper(ClassDao.class);
    }

    public List<ClassPVO> doSelectWorkTime(ClassDBO dbo){
    	return  getDao().doSelectWorkTime(dbo);
    }
    /**
     * 分页 根据  termId tid gradelid 查询班级
     * @param formParamPageModel
     * @return
     */
	public PageVOSupport doSelectPageClass(PageVOSupport formParamPageModel) {
		formParamPageModel.setPageListData(getDao().doSelectPageClass(formParamPageModel));
		return formParamPageModel;
	}
}
