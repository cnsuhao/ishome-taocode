package com.mcookies.qxy.common.Class;
import java.util.List;

import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

import com.mcookies.qxy.common.News.NewsPVO;

/** 班级表*/
@Service
public class ClassService extends MyServiceSupport {

    public ClassDao getDao(){
        return getMySqlSession().getMapper(ClassDao.class);
    }

    public PageVOSupport doSelectPageWorkTime(PageVOSupport formParamPageModel){
		formParamPageModel.setPageListData(getDao().doSelectPageWorkTime(formParamPageModel));
		return formParamPageModel;
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
	
	/**
	 * 分页 根据  termId tid cid gradelid 查询班级
	 * @param formParamPageModel
	 * @return
	 */
	public PageVOSupport doSelectPageClass2(PageVOSupport formParamPageModel) {
		formParamPageModel.setPageListData(getDao().doSelectPageClass2(formParamPageModel));
		return formParamPageModel;
	}

	/**
	 * 根据news的newsClasser查询，为空返回全部
	 * @param news
	 * @return
	 */
	public List<ClassDBO> findNewsClassers(NewsPVO news) {
		return getDao().findNewsClassers(news);
	}

	public List<ClassPVO> findByTermIdAndTid(ClassPVO clz) {
		return getDao().findByTermIdAndTid(clz);
	}
}
