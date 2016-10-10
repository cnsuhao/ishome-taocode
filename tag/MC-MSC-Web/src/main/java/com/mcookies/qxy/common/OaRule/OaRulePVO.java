package com.mcookies.qxy.common.OaRule;

import java.util.List;

import javax.inject.Named;

import com.mcookies.qxy.common.UTeacher.UTeacherDBO;

@Named
/** OA规则表 */
public class OaRulePVO extends OaRuleDBO {

	private String oatagsName = null;
	
	private List<UTeacherDBO> teachers = null;

	public String getOatagsName() {
		return oatagsName;
	}

	public void setOatagsName(String oatagsName) {
		this.oatagsName = oatagsName;
	}

	public List<UTeacherDBO> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<UTeacherDBO> teachers) {
		this.teachers = teachers;
	}

}
