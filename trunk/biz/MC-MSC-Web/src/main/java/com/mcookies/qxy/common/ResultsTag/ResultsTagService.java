package com.mcookies.qxy.common.ResultsTag;

import org.springframework.stereotype.Service;

import com.mcookies.qxy.common.ClassTeacher.ClassTeacherDBO;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherPVO;

import java.util.List;

import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 成绩标签表*/
@Service
public class ResultsTagService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(ResultsTagService.class);

    public ResultsTagDao getDao(){
        return getMySqlSession().getMapper(ResultsTagDao.class);
    }
	public List<ResultsTagDBO> doSelectDataList(ResultsTagDBO resultsTagDBO) {
		return getDao().doSelectDataList(resultsTagDBO);
	}
}
