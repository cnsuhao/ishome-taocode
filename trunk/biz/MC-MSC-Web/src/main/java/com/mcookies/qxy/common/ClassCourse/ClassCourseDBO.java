package com.mcookies.qxy.common.ClassCourse;

import java.sql.Date;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 班级课程教师关联表 */
public class ClassCourseDBO extends MyDataBaseObjectSupport {
	/**
	 * 自增id
	 */
	private Long id = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 班级id
	 */
	private Long cid = null;

	/**
	 * 日期
	 */
	private Date date = null;

	/**
	 * 课程1id
	 */
	private Long courseId1 = null;

	/**
	 * 课程1名称
	 */
	private String courseName1 = null;

	/**
	 * 教师1id
	 */
	private Long tid1 = null;

	/**
	 * 教师1姓名
	 */
	private String teacherName1 = null;

	/**
	 * 课程2id
	 */
	private Long courseId2 = null;

	/**
	 * 课程2名称
	 */
	private String courseName2 = null;

	/**
	 * 教师2id
	 */
	private Long tid2 = null;

	/**
	 * 教师2姓名
	 */
	private String teacherN2 = null;

	/**
	 * 课程3id
	 */
	private Long courseId3 = null;

	/**
	 * 课程3名称
	 */
	private String courseName3 = null;

	/**
	 * 教师3id
	 */
	private Long tid3 = null;

	/**
	 * 教师3姓名
	 */
	private String teacherName3 = null;

	/**
	 * 课程4id
	 */
	private Long courseId4 = null;

	/**
	 * 课程4名称
	 */
	private String courseName4 = null;

	/**
	 * 教师4id
	 */
	private Long tid4 = null;

	/**
	 * 教师4姓名
	 */
	private String teacherName4 = null;

	/**
	 * 课程5id
	 */
	private Long courseId5 = null;

	/**
	 * 课程5名称
	 */
	private String courseName5 = null;

	/**
	 * 教师5id
	 */
	private Long tid5 = null;

	/**
	 * 教师5姓名
	 */
	private String teacherName5 = null;

	/**
	 * 课程6id
	 */
	private Long courseId6 = null;

	/**
	 * 课程6名称
	 */
	private String courseName6 = null;

	/**
	 * 教师6id
	 */
	private Long tid6 = null;

	/**
	 * 教师6姓名
	 */
	private String teacherName6 = null;

	/**
	 * 课程7id
	 */
	private Long courseId7 = null;

	/**
	 * 课程7名称
	 */
	private String courseName7 = null;

	/**
	 * 教师7id
	 */
	private Long tid7 = null;

	/**
	 * 教师7姓名
	 */
	private String teacherName7 = null;

	/**
	 * 课程8id
	 */
	private Long courseId8 = null;

	/**
	 * 课程8名称
	 */
	private String courseName8 = null;

	/**
	 * 教师8id
	 */
	private Long tid8 = null;

	/**
	 * 教师8姓名
	 */
	private String teacherName8 = null;

	/**
	 * 课程9id
	 */
	private Long courseId9 = null;

	/**
	 * 课程9名称
	 */
	private String courseName9 = null;

	/**
	 * 教师9id
	 */
	private Long tid9 = null;

	/**
	 * 教师9姓名
	 */
	private String teacherName9 = null;

	/**
	 * 课程10id
	 */
	private Long courseId10 = null;

	/**
	 * 课程10名称
	 */
	private String courseName10 = null;

	/**
	 * 教师10id
	 */
	private Long tid10 = null;

	/**
	 * 教师10姓名
	 */
	private String teacherName10 = null;

	/**
	 * 课程11id
	 */
	private Long courseId11 = null;

	/**
	 * 课程11名称
	 */
	private String courseName11 = null;

	/**
	 * 教师11id
	 */
	private Long tid11 = null;

	/**
	 * 教师11姓名
	 */
	private String teacherName11 = null;

	/**
	 * 课程12id
	 */
	private Long courseId12 = null;

	/**
	 * 课程12名称
	 */
	private String courseName12 = null;

	/**
	 * 教师12id
	 */
	private Long tid12 = null;

	/**
	 * 教师12姓名
	 */
	private String teacherName12 = null;

	/**
	 * 课程13id
	 */
	private Long courseId13 = null;

	/**
	 * 课程13名称
	 */
	private String courseName13 = null;

	/**
	 * 教师13id
	 */
	private Long tid13 = null;

	/**
	 * 教师13姓名
	 */
	private String teacherName13 = null;

	/**
	 * 课程14id
	 */
	private Long courseId14 = null;

	/**
	 * 课程14名称
	 */
	private String courseName14 = null;

	/**
	 * 教师14id
	 */
	private Long tid14 = null;

	/**
	 * 教师14姓名
	 */
	private String teacherName14 = null;

	/**
	 * 课程15id
	 */
	private Long courseId15 = null;

	/**
	 * 课程15名称
	 */
	private String courseName15 = null;

	/**
	 * 教师15id
	 */
	private Long tid15 = null;

	/**
	 * 教师15姓名
	 */
	private Long teacherName15 = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取自增id
	 *
	 * @return Id 自增id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * 获取学校id
	 *
	 * @return Sid 学校id
	 */
	public Long getSid() {
		return this.sid;
	}

	/**
	 * 获取班级id
	 *
	 * @return Cid 班级id
	 */
	public Long getCid() {
		return this.cid;
	}

	/**
	 * 获取日期
	 *
	 * @return Date 日期
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * 获取课程1id
	 *
	 * @return Course_id1 课程1id
	 */
	public Long getCourseId1() {
		return this.courseId1;
	}

	/**
	 * 获取课程1名称
	 *
	 * @return Course_name1 课程1名称
	 */
	public String getCourseName1() {
		return this.courseName1;
	}

	/**
	 * 获取教师1id
	 *
	 * @return Tid1 教师1id
	 */
	public Long getTid1() {
		return this.tid1;
	}

	/**
	 * 获取教师1姓名
	 *
	 * @return Teacher_name1 教师1姓名
	 */
	public String getTeacherName1() {
		return this.teacherName1;
	}

	/**
	 * 获取课程2id
	 *
	 * @return Course_id2 课程2id
	 */
	public Long getCourseId2() {
		return this.courseId2;
	}

	/**
	 * 获取课程2名称
	 *
	 * @return Course_name2 课程2名称
	 */
	public String getCourseName2() {
		return this.courseName2;
	}

	/**
	 * 获取教师2id
	 *
	 * @return Tid2 教师2id
	 */
	public Long getTid2() {
		return this.tid2;
	}

	/**
	 * 获取教师2姓名
	 *
	 * @return Teacher_n2 教师2姓名
	 */
	public String getTeacherN2() {
		return this.teacherN2;
	}

	/**
	 * 获取课程3id
	 *
	 * @return Course_id3 课程3id
	 */
	public Long getCourseId3() {
		return this.courseId3;
	}

	/**
	 * 获取课程3名称
	 *
	 * @return Course_name3 课程3名称
	 */
	public String getCourseName3() {
		return this.courseName3;
	}

	/**
	 * 获取教师3id
	 *
	 * @return Tid3 教师3id
	 */
	public Long getTid3() {
		return this.tid3;
	}

	/**
	 * 获取教师3姓名
	 *
	 * @return Teacher_name3 教师3姓名
	 */
	public String getTeacherName3() {
		return this.teacherName3;
	}

	/**
	 * 获取课程4id
	 *
	 * @return Course_id4 课程4id
	 */
	public Long getCourseId4() {
		return this.courseId4;
	}

	/**
	 * 获取课程4名称
	 *
	 * @return Course_name4 课程4名称
	 */
	public String getCourseName4() {
		return this.courseName4;
	}

	/**
	 * 获取教师4id
	 *
	 * @return Tid4 教师4id
	 */
	public Long getTid4() {
		return this.tid4;
	}

	/**
	 * 获取教师4姓名
	 *
	 * @return Teacher_name4 教师4姓名
	 */
	public String getTeacherName4() {
		return this.teacherName4;
	}

	/**
	 * 获取课程5id
	 *
	 * @return Course_id5 课程5id
	 */
	public Long getCourseId5() {
		return this.courseId5;
	}

	/**
	 * 获取课程5名称
	 *
	 * @return Course_name5 课程5名称
	 */
	public String getCourseName5() {
		return this.courseName5;
	}

	/**
	 * 获取教师5id
	 *
	 * @return Tid5 教师5id
	 */
	public Long getTid5() {
		return this.tid5;
	}

	/**
	 * 获取教师5姓名
	 *
	 * @return Teacher_name5 教师5姓名
	 */
	public String getTeacherName5() {
		return this.teacherName5;
	}

	/**
	 * 获取课程6id
	 *
	 * @return Course_id6 课程6id
	 */
	public Long getCourseId6() {
		return this.courseId6;
	}

	/**
	 * 获取课程6名称
	 *
	 * @return Course_name6 课程6名称
	 */
	public String getCourseName6() {
		return this.courseName6;
	}

	/**
	 * 获取教师6id
	 *
	 * @return Tid6 教师6id
	 */
	public Long getTid6() {
		return this.tid6;
	}

	/**
	 * 获取教师6姓名
	 *
	 * @return Teacher_name6 教师6姓名
	 */
	public String getTeacherName6() {
		return this.teacherName6;
	}

	/**
	 * 获取课程7id
	 *
	 * @return Course_id7 课程7id
	 */
	public Long getCourseId7() {
		return this.courseId7;
	}

	/**
	 * 获取课程7名称
	 *
	 * @return Course_name7 课程7名称
	 */
	public String getCourseName7() {
		return this.courseName7;
	}

	/**
	 * 获取教师7id
	 *
	 * @return Tid7 教师7id
	 */
	public Long getTid7() {
		return this.tid7;
	}

	/**
	 * 获取教师7姓名
	 *
	 * @return Teacher_name7 教师7姓名
	 */
	public String getTeacherName7() {
		return this.teacherName7;
	}

	/**
	 * 获取课程8id
	 *
	 * @return Course_id8 课程8id
	 */
	public Long getCourseId8() {
		return this.courseId8;
	}

	/**
	 * 获取课程8名称
	 *
	 * @return Course_name8 课程8名称
	 */
	public String getCourseName8() {
		return this.courseName8;
	}

	/**
	 * 获取教师8id
	 *
	 * @return Tid8 教师8id
	 */
	public Long getTid8() {
		return this.tid8;
	}

	/**
	 * 获取教师8姓名
	 *
	 * @return Teacher_name8 教师8姓名
	 */
	public String getTeacherName8() {
		return this.teacherName8;
	}

	/**
	 * 获取课程9id
	 *
	 * @return Course_id9 课程9id
	 */
	public Long getCourseId9() {
		return this.courseId9;
	}

	/**
	 * 获取课程9名称
	 *
	 * @return Course_name9 课程9名称
	 */
	public String getCourseName9() {
		return this.courseName9;
	}

	/**
	 * 获取教师9id
	 *
	 * @return Tid9 教师9id
	 */
	public Long getTid9() {
		return this.tid9;
	}

	/**
	 * 获取教师9姓名
	 *
	 * @return Teacher_name9 教师9姓名
	 */
	public String getTeacherName9() {
		return this.teacherName9;
	}

	/**
	 * 获取课程10id
	 *
	 * @return Course_id10 课程10id
	 */
	public Long getCourseId10() {
		return this.courseId10;
	}

	/**
	 * 获取课程10名称
	 *
	 * @return Course_name10 课程10名称
	 */
	public String getCourseName10() {
		return this.courseName10;
	}

	/**
	 * 获取教师10id
	 *
	 * @return Tid10 教师10id
	 */
	public Long getTid10() {
		return this.tid10;
	}

	/**
	 * 获取教师10姓名
	 *
	 * @return Teacher_name10 教师10姓名
	 */
	public String getTeacherName10() {
		return this.teacherName10;
	}

	/**
	 * 获取课程11id
	 *
	 * @return Course_id11 课程11id
	 */
	public Long getCourseId11() {
		return this.courseId11;
	}

	/**
	 * 获取课程11名称
	 *
	 * @return Course_name11 课程11名称
	 */
	public String getCourseName11() {
		return this.courseName11;
	}

	/**
	 * 获取教师11id
	 *
	 * @return Tid11 教师11id
	 */
	public Long getTid11() {
		return this.tid11;
	}

	/**
	 * 获取教师11姓名
	 *
	 * @return Teacher_name11 教师11姓名
	 */
	public String getTeacherName11() {
		return this.teacherName11;
	}

	/**
	 * 获取课程12id
	 *
	 * @return Course_id12 课程12id
	 */
	public Long getCourseId12() {
		return this.courseId12;
	}

	/**
	 * 获取课程12名称
	 *
	 * @return Course_name12 课程12名称
	 */
	public String getCourseName12() {
		return this.courseName12;
	}

	/**
	 * 获取教师12id
	 *
	 * @return Tid12 教师12id
	 */
	public Long getTid12() {
		return this.tid12;
	}

	/**
	 * 获取教师12姓名
	 *
	 * @return Teacher_name12 教师12姓名
	 */
	public String getTeacherName12() {
		return this.teacherName12;
	}

	/**
	 * 获取课程13id
	 *
	 * @return Course_id13 课程13id
	 */
	public Long getCourseId13() {
		return this.courseId13;
	}

	/**
	 * 获取课程13名称
	 *
	 * @return Course_name13 课程13名称
	 */
	public String getCourseName13() {
		return this.courseName13;
	}

	/**
	 * 获取教师13id
	 *
	 * @return Tid13 教师13id
	 */
	public Long getTid13() {
		return this.tid13;
	}

	/**
	 * 获取教师13姓名
	 *
	 * @return Teacher_name13 教师13姓名
	 */
	public String getTeacherName13() {
		return this.teacherName13;
	}

	/**
	 * 获取课程14id
	 *
	 * @return Course_id14 课程14id
	 */
	public Long getCourseId14() {
		return this.courseId14;
	}

	/**
	 * 获取课程14名称
	 *
	 * @return Course_name14 课程14名称
	 */
	public String getCourseName14() {
		return this.courseName14;
	}

	/**
	 * 获取教师14id
	 *
	 * @return Tid14 教师14id
	 */
	public Long getTid14() {
		return this.tid14;
	}

	/**
	 * 获取教师14姓名
	 *
	 * @return Teacher_name14 教师14姓名
	 */
	public String getTeacherName14() {
		return this.teacherName14;
	}

	/**
	 * 获取课程15id
	 *
	 * @return Course_id15 课程15id
	 */
	public Long getCourseId15() {
		return this.courseId15;
	}

	/**
	 * 获取课程15名称
	 *
	 * @return Course_name15 课程15名称
	 */
	public String getCourseName15() {
		return this.courseName15;
	}

	/**
	 * 获取教师15id
	 *
	 * @return Tid15 教师15id
	 */
	public Long getTid15() {
		return this.tid15;
	}

	/**
	 * 获取教师15姓名
	 *
	 * @return Teacher_name15 教师15姓名
	 */
	public Long getTeacherName15() {
		return this.teacherName15;
	}

	/**
	 * 获取是否启用
	 *
	 * @return Is_use 是否启用
	 */
	public Integer getIsUse() {
		return this.isUse;
	}

	/**
	 * 设置自增id
	 *
	 * @param Id
	 *            自增id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置学校id
	 *
	 * @param Sid
	 *            学校id
	 */
	public void setSid(Long sid) {
		this.sid = sid;
	}

	/**
	 * 设置班级id
	 *
	 * @param Cid
	 *            班级id
	 */
	public void setCid(Long cid) {
		this.cid = cid;
	}

	/**
	 * 设置日期
	 *
	 * @param Date
	 *            日期
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * 设置课程1id
	 *
	 * @param Course_id1
	 *            课程1id
	 */
	public void setCourseId1(Long courseid1) {
		this.courseId1 = courseid1;
	}

	/**
	 * 设置课程1名称
	 *
	 * @param Course_name1
	 *            课程1名称
	 */
	public void setCourseName1(String coursename1) {
		this.courseName1 = coursename1;
	}

	/**
	 * 设置教师1id
	 *
	 * @param Tid1
	 *            教师1id
	 */
	public void setTid1(Long tid1) {
		this.tid1 = tid1;
	}

	/**
	 * 设置教师1姓名
	 *
	 * @param Teacher_name1
	 *            教师1姓名
	 */
	public void setTeacherName1(String teachername1) {
		this.teacherName1 = teachername1;
	}

	/**
	 * 设置课程2id
	 *
	 * @param Course_id2
	 *            课程2id
	 */
	public void setCourseId2(Long courseid2) {
		this.courseId2 = courseid2;
	}

	/**
	 * 设置课程2名称
	 *
	 * @param Course_name2
	 *            课程2名称
	 */
	public void setCourseName2(String coursename2) {
		this.courseName2 = coursename2;
	}

	/**
	 * 设置教师2id
	 *
	 * @param Tid2
	 *            教师2id
	 */
	public void setTid2(Long tid2) {
		this.tid2 = tid2;
	}

	/**
	 * 设置教师2姓名
	 *
	 * @param Teacher_n2
	 *            教师2姓名
	 */
	public void setTeacherN2(String teachern2) {
		this.teacherN2 = teachern2;
	}

	/**
	 * 设置课程3id
	 *
	 * @param Course_id3
	 *            课程3id
	 */
	public void setCourseId3(Long courseid3) {
		this.courseId3 = courseid3;
	}

	/**
	 * 设置课程3名称
	 *
	 * @param Course_name3
	 *            课程3名称
	 */
	public void setCourseName3(String coursename3) {
		this.courseName3 = coursename3;
	}

	/**
	 * 设置教师3id
	 *
	 * @param Tid3
	 *            教师3id
	 */
	public void setTid3(Long tid3) {
		this.tid3 = tid3;
	}

	/**
	 * 设置教师3姓名
	 *
	 * @param Teacher_name3
	 *            教师3姓名
	 */
	public void setTeacherName3(String teachername3) {
		this.teacherName3 = teachername3;
	}

	/**
	 * 设置课程4id
	 *
	 * @param Course_id4
	 *            课程4id
	 */
	public void setCourseId4(Long courseid4) {
		this.courseId4 = courseid4;
	}

	/**
	 * 设置课程4名称
	 *
	 * @param Course_name4
	 *            课程4名称
	 */
	public void setCourseName4(String coursename4) {
		this.courseName4 = coursename4;
	}

	/**
	 * 设置教师4id
	 *
	 * @param Tid4
	 *            教师4id
	 */
	public void setTid4(Long tid4) {
		this.tid4 = tid4;
	}

	/**
	 * 设置教师4姓名
	 *
	 * @param Teacher_name4
	 *            教师4姓名
	 */
	public void setTeacherName4(String teachername4) {
		this.teacherName4 = teachername4;
	}

	/**
	 * 设置课程5id
	 *
	 * @param Course_id5
	 *            课程5id
	 */
	public void setCourseId5(Long courseid5) {
		this.courseId5 = courseid5;
	}

	/**
	 * 设置课程5名称
	 *
	 * @param Course_name5
	 *            课程5名称
	 */
	public void setCourseName5(String coursename5) {
		this.courseName5 = coursename5;
	}

	/**
	 * 设置教师5id
	 *
	 * @param Tid5
	 *            教师5id
	 */
	public void setTid5(Long tid5) {
		this.tid5 = tid5;
	}

	/**
	 * 设置教师5姓名
	 *
	 * @param Teacher_name5
	 *            教师5姓名
	 */
	public void setTeacherName5(String teachername5) {
		this.teacherName5 = teachername5;
	}

	/**
	 * 设置课程6id
	 *
	 * @param Course_id6
	 *            课程6id
	 */
	public void setCourseId6(Long courseid6) {
		this.courseId6 = courseid6;
	}

	/**
	 * 设置课程6名称
	 *
	 * @param Course_name6
	 *            课程6名称
	 */
	public void setCourseName6(String coursename6) {
		this.courseName6 = coursename6;
	}

	/**
	 * 设置教师6id
	 *
	 * @param Tid6
	 *            教师6id
	 */
	public void setTid6(Long tid6) {
		this.tid6 = tid6;
	}

	/**
	 * 设置教师6姓名
	 *
	 * @param Teacher_name6
	 *            教师6姓名
	 */
	public void setTeacherName6(String teachername6) {
		this.teacherName6 = teachername6;
	}

	/**
	 * 设置课程7id
	 *
	 * @param Course_id7
	 *            课程7id
	 */
	public void setCourseId7(Long courseid7) {
		this.courseId7 = courseid7;
	}

	/**
	 * 设置课程7名称
	 *
	 * @param Course_name7
	 *            课程7名称
	 */
	public void setCourseName7(String coursename7) {
		this.courseName7 = coursename7;
	}

	/**
	 * 设置教师7id
	 *
	 * @param Tid7
	 *            教师7id
	 */
	public void setTid7(Long tid7) {
		this.tid7 = tid7;
	}

	/**
	 * 设置教师7姓名
	 *
	 * @param Teacher_name7
	 *            教师7姓名
	 */
	public void setTeacherName7(String teachername7) {
		this.teacherName7 = teachername7;
	}

	/**
	 * 设置课程8id
	 *
	 * @param Course_id8
	 *            课程8id
	 */
	public void setCourseId8(Long courseid8) {
		this.courseId8 = courseid8;
	}

	/**
	 * 设置课程8名称
	 *
	 * @param Course_name8
	 *            课程8名称
	 */
	public void setCourseName8(String coursename8) {
		this.courseName8 = coursename8;
	}

	/**
	 * 设置教师8id
	 *
	 * @param Tid8
	 *            教师8id
	 */
	public void setTid8(Long tid8) {
		this.tid8 = tid8;
	}

	/**
	 * 设置教师8姓名
	 *
	 * @param Teacher_name8
	 *            教师8姓名
	 */
	public void setTeacherName8(String teachername8) {
		this.teacherName8 = teachername8;
	}

	/**
	 * 设置课程9id
	 *
	 * @param Course_id9
	 *            课程9id
	 */
	public void setCourseId9(Long courseid9) {
		this.courseId9 = courseid9;
	}

	/**
	 * 设置课程9名称
	 *
	 * @param Course_name9
	 *            课程9名称
	 */
	public void setCourseName9(String coursename9) {
		this.courseName9 = coursename9;
	}

	/**
	 * 设置教师9id
	 *
	 * @param Tid9
	 *            教师9id
	 */
	public void setTid9(Long tid9) {
		this.tid9 = tid9;
	}

	/**
	 * 设置教师9姓名
	 *
	 * @param Teacher_name9
	 *            教师9姓名
	 */
	public void setTeacherName9(String teachername9) {
		this.teacherName9 = teachername9;
	}

	/**
	 * 设置课程10id
	 *
	 * @param Course_id10
	 *            课程10id
	 */
	public void setCourseId10(Long courseid10) {
		this.courseId10 = courseid10;
	}

	/**
	 * 设置课程10名称
	 *
	 * @param Course_name10
	 *            课程10名称
	 */
	public void setCourseName10(String coursename10) {
		this.courseName10 = coursename10;
	}

	/**
	 * 设置教师10id
	 *
	 * @param Tid10
	 *            教师10id
	 */
	public void setTid10(Long tid10) {
		this.tid10 = tid10;
	}

	/**
	 * 设置教师10姓名
	 *
	 * @param Teacher_name10
	 *            教师10姓名
	 */
	public void setTeacherName10(String teachername10) {
		this.teacherName10 = teachername10;
	}

	/**
	 * 设置课程11id
	 *
	 * @param Course_id11
	 *            课程11id
	 */
	public void setCourseId11(Long courseid11) {
		this.courseId11 = courseid11;
	}

	/**
	 * 设置课程11名称
	 *
	 * @param Course_name11
	 *            课程11名称
	 */
	public void setCourseName11(String coursename11) {
		this.courseName11 = coursename11;
	}

	/**
	 * 设置教师11id
	 *
	 * @param Tid11
	 *            教师11id
	 */
	public void setTid11(Long tid11) {
		this.tid11 = tid11;
	}

	/**
	 * 设置教师11姓名
	 *
	 * @param Teacher_name11
	 *            教师11姓名
	 */
	public void setTeacherName11(String teachername11) {
		this.teacherName11 = teachername11;
	}

	/**
	 * 设置课程12id
	 *
	 * @param Course_id12
	 *            课程12id
	 */
	public void setCourseId12(Long courseid12) {
		this.courseId12 = courseid12;
	}

	/**
	 * 设置课程12名称
	 *
	 * @param Course_name12
	 *            课程12名称
	 */
	public void setCourseName12(String coursename12) {
		this.courseName12 = coursename12;
	}

	/**
	 * 设置教师12id
	 *
	 * @param Tid12
	 *            教师12id
	 */
	public void setTid12(Long tid12) {
		this.tid12 = tid12;
	}

	/**
	 * 设置教师12姓名
	 *
	 * @param Teacher_name12
	 *            教师12姓名
	 */
	public void setTeacherName12(String teachername12) {
		this.teacherName12 = teachername12;
	}

	/**
	 * 设置课程13id
	 *
	 * @param Course_id13
	 *            课程13id
	 */
	public void setCourseId13(Long courseid13) {
		this.courseId13 = courseid13;
	}

	/**
	 * 设置课程13名称
	 *
	 * @param Course_name13
	 *            课程13名称
	 */
	public void setCourseName13(String coursename13) {
		this.courseName13 = coursename13;
	}

	/**
	 * 设置教师13id
	 *
	 * @param Tid13
	 *            教师13id
	 */
	public void setTid13(Long tid13) {
		this.tid13 = tid13;
	}

	/**
	 * 设置教师13姓名
	 *
	 * @param Teacher_name13
	 *            教师13姓名
	 */
	public void setTeacherName13(String teachername13) {
		this.teacherName13 = teachername13;
	}

	/**
	 * 设置课程14id
	 *
	 * @param Course_id14
	 *            课程14id
	 */
	public void setCourseId14(Long courseid14) {
		this.courseId14 = courseid14;
	}

	/**
	 * 设置课程14名称
	 *
	 * @param Course_name14
	 *            课程14名称
	 */
	public void setCourseName14(String coursename14) {
		this.courseName14 = coursename14;
	}

	/**
	 * 设置教师14id
	 *
	 * @param Tid14
	 *            教师14id
	 */
	public void setTid14(Long tid14) {
		this.tid14 = tid14;
	}

	/**
	 * 设置教师14姓名
	 *
	 * @param Teacher_name14
	 *            教师14姓名
	 */
	public void setTeacherName14(String teachername14) {
		this.teacherName14 = teachername14;
	}

	/**
	 * 设置课程15id
	 *
	 * @param Course_id15
	 *            课程15id
	 */
	public void setCourseId15(Long courseid15) {
		this.courseId15 = courseid15;
	}

	/**
	 * 设置课程15名称
	 *
	 * @param Course_name15
	 *            课程15名称
	 */
	public void setCourseName15(String coursename15) {
		this.courseName15 = coursename15;
	}

	/**
	 * 设置教师15id
	 *
	 * @param Tid15
	 *            教师15id
	 */
	public void setTid15(Long tid15) {
		this.tid15 = tid15;
	}

	/**
	 * 设置教师15姓名
	 *
	 * @param Teacher_name15
	 *            教师15姓名
	 */
	public void setTeacherName15(Long teachername15) {
		this.teacherName15 = teachername15;
	}

	/**
	 * 设置是否启用
	 *
	 * @param Is_use
	 *            是否启用
	 */
	public void setIsUse(Integer isuse) {
		this.isUse = isuse;
	}

}
