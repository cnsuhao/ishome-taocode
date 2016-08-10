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
	 * 学期
	 */
	private Long term = null;

	/**
	 * 课次
	 */
	private Integer classTime = null;

	/**
	 * 星期
	 */
	private Integer weekDay = null;

	/**
	 * 日期
	 */
	private String useDay = null;

	/**
	 * 课程id
	 */
	private Long courseId = null;

	/**
	 * 课程名称
	 */
	private String courseName = null;

	/**
	 * 教师id
	 */
	private Long tid = null;

	/**
	 * 教师姓名
	 */
	private String teacherName = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 创建时间
	 */
	private String createTime = null;

	/**
	 * 创建者
	 */
	private Long creator = null;

	/**
	 * 更新时间
	 */
	private String updateTime = null;

	/**
	 * 最后更新者
	 */
	private Long updator = null;

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
	 * 获取学期
	 *
	 * @return Term 学期
	 */
	public Long getTerm() {
		return this.term;
	}

	/**
	 * 获取课次
	 *
	 * @return Class_time 课次
	 */
	public Integer getClassTime() {
		return this.classTime;
	}

	/**
	 * 获取星期
	 *
	 * @return Week_day 星期
	 */
	public Integer getWeekDay() {
		return this.weekDay;
	}


	/**
	 * 获取课程id
	 *
	 * @return Course_id 课程id
	 */
	public Long getCourseId() {
		return this.courseId;
	}

	/**
	 * 获取课程名称
	 *
	 * @return Course_name 课程名称
	 */
	public String getCourseName() {
		return this.courseName;
	}

	/**
	 * 获取教师id
	 *
	 * @return Tid 教师id
	 */
	public Long getTid() {
		return this.tid;
	}

	/**
	 * 获取教师姓名
	 *
	 * @return Teacher_name 教师姓名
	 */
	public String getTeacherName() {
		return this.teacherName;
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
	 * 获取创建时间
	 *
	 * @return Create_time 创建时间
	 */
	public String getCreateTime() {
		return this.createTime;
	}

	/**
	 * 获取创建者
	 *
	 * @return Creator 创建者
	 */
	public Long getCreator() {
		return this.creator;
	}

	/**
	 * 获取更新时间
	 *
	 * @return Update_time 更新时间
	 */
	public String getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 获取最后更新者
	 *
	 * @return Updator 最后更新者
	 */
	public Long getUpdator() {
		return this.updator;
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
	 * 设置学期
	 *
	 * @param Term
	 *            学期
	 */
	public void setTerm(Long term) {
		this.term = term;
	}

	/**
	 * 设置课次
	 *
	 * @param Class_time
	 *            课次
	 */
	public void setClassTime(Integer classtime) {
		this.classTime = classtime;
	}

	/**
	 * 设置星期
	 *
	 * @param Week_day
	 *            星期
	 */
	public void setWeekDay(Integer weekday) {
		this.weekDay = weekday;
	}


	/**
	 * 设置课程id
	 *
	 * @param Course_id
	 *            课程id
	 */
	public void setCourseId(Long courseid) {
		this.courseId = courseid;
	}

	/**
	 * 设置课程名称
	 *
	 * @param Course_name
	 *            课程名称
	 */
	public void setCourseName(String coursename) {
		this.courseName = coursename;
	}

	/**
	 * 设置教师id
	 *
	 * @param Tid
	 *            教师id
	 */
	public void setTid(Long tid) {
		this.tid = tid;
	}

	/**
	 * 设置教师姓名
	 *
	 * @param Teacher_name
	 *            教师姓名
	 */
	public void setTeacherName(String teachername) {
		this.teacherName = teachername;
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

	/**
	 * 设置创建时间
	 *
	 * @param Create_time
	 *            创建时间
	 */
	public void setCreateTime(String createtime) {
		this.createTime = createtime;
	}

	/**
	 * 设置创建者
	 *
	 * @param Creator
	 *            创建者
	 */
	public void setCreator(Long creator) {
		this.creator = creator;
	}

	/**
	 * 设置更新时间
	 *
	 * @param Update_time
	 *            更新时间
	 */
	public void setUpdateTime(String updatetime) {
		this.updateTime = updatetime;
	}

	/**
	 * 设置最后更新者
	 *
	 * @param Updator
	 *            最后更新者
	 */
	public void setUpdator(Long updator) {
		this.updator = updator;
	}

	public String getUseDay() {
		return useDay;
	}

	public void setUseDay(String useDay) {
		this.useDay = useDay;
	}
	
}
