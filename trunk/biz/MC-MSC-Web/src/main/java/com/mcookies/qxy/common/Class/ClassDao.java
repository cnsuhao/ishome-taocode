package com.mcookies.qxy.common.Class;

import java.util.List;

import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 班级表*/
public interface ClassDao extends IDatabaseSupport{
	
	List<ClassPVO> doSelectWorkTime(ClassDBO dbo);

}
