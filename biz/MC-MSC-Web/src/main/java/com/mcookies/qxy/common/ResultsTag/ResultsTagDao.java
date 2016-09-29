package com.mcookies.qxy.common.ResultsTag;

import java.util.List;

import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 成绩标签表*/
public interface ResultsTagDao extends IDatabaseSupport{
	List<ResultsTagDBO> doSelectDataList(ResultsTagDBO resultsTagDBO);

}
