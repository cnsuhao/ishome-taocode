package com.mcookies.qxy.common.UTeacher;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.IDatabaseSupport;

import com.mcookies.qxy.common.ClassTeacher.ClassTeacherDBO;
import com.mcookies.qxy.common.News.NewsPVO;
import com.mcookies.qxy.common.OaRule.OaRulePVO;
import com.mcookies.qxy.common.SDutyScheduling.SDutySchedulingPVO;

 /** 教师表 */
public interface UTeacherDao extends IDatabaseSupport {
	List<? extends FrameworkDataBean> doSelectPageTeacherOnLabel(PageVOSupport formParamPageModel);
	UTeacherDBO findClassLeader(ClassTeacherDBO classTeacher);
	List<UTeacherDBO> findSchedulingTeachers(SDutySchedulingPVO s);
	List<UTeacherDBO> findSchedulingLeaders(SDutySchedulingPVO s);
	List<UTeacherDBO> findNewsReaders(NewsPVO news);
	List<UTeacherPVO> findByTermIdAndCid(UTeacherPVO teacher);
	List<UTeacherDBO> findByTids(OaRulePVO oaRule);
	UTeacherDBO doReadByUid(UTeacherDBO u);
}
