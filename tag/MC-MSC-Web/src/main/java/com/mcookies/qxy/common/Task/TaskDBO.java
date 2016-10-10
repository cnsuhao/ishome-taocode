package com.mcookies.qxy.common.Task;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 作业表*/
public class TaskDBO extends MyDataBaseObjectSupport
{
    /** 
     * 作业id
     */
    private Long taskId = null;
 
    /** 
     * 学校id
     */
    private Long sid = null;
    /** 
     * 学期id
     */
    private Long termId = null;
 
    public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	/** 
     * 课程id
     */
    private Long courseId = null;
 
    /** 
     * 作业标题
     */
    private String taskName = null;
 
    /** 
     * 作业内容
     */
    private String content = null;
 
    /** 
     * 作业音频
     */
    private String video = null;
 
    /** 
     * 作业图片
     */
    private String pic = null;
 
    /** 
     * 作业布置班级
     */
    private String taskClasser = null;
 
    /** 
     * 作业开始时间
     */
    private String startTime = null;
 
    /** 
     * 作业结束时间
     */
    private String endTime = null;
 
    /** 
     * 是否置顶
     */
    private Integer isTop = null;
 
    /** 
     * 作业发布时间
     */
    private String publishTime = null;
 
    /** 
     * 审核状态
     */
    private Integer isAudit = null;
 
    /** 
     * 阅读次数
     */
    private Long num = null;
 
    /** 
     * 作者（教师tid）
     */
    private Long author = null;
 
    /** 
     * 是否启用
     */
    private Integer isUse = null;
 
 
    /** 
     * 获取作业id
     *
     * @return Task_id 作业id
     */
    public Long getTaskId() {
        return this.taskId;
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
     * 获取课程id
     *
     * @return Course_id 课程id
     */
    public Long getCourseId() {
        return this.courseId;
    }
 
    /** 
     * 获取作业标题
     *
     * @return Task_name 作业标题
     */
    public String getTaskName() {
        return this.taskName;
    }
 
    /** 
     * 获取作业内容
     *
     * @return Content 作业内容
     */
    public String getContent() {
        return this.content;
    }
 
    /** 
     * 获取作业音频
     *
     * @return Video 作业音频
     */
    public String getVideo() {
        return this.video;
    }
 
    /** 
     * 获取作业图片
     *
     * @return Pic 作业图片
     */
    public String getPic() {
        return this.pic;
    }
 
    /** 
     * 获取作业布置班级
     *
     * @return Task_classer 作业布置班级
     */
    public String getTaskClasser() {
        return this.taskClasser;
    }
 
    /** 
     * 获取作业开始时间
     *
     * @return Start_time 作业开始时间
     */
    public String getStartTime() {
        return this.startTime;
    }
 
    /** 
     * 获取作业结束时间
     *
     * @return End_time 作业结束时间
     */
    public String getEndTime() {
        return this.endTime;
    }
 
    /** 
     * 获取是否置顶
     *
     * @return Is_top 是否置顶
     */
    public Integer getIsTop() {
        return this.isTop;
    }
 
    /** 
     * 获取作业发布时间
     *
     * @return Publish_time 作业发布时间
     */
    public String getPublishTime() {
        return this.publishTime;
    }
 
    /** 
     * 获取审核状态
     *
     * @return Is_audit 审核状态
     */
    public Integer getIsAudit() {
        return this.isAudit;
    }
 
    /** 
     * 获取阅读次数
     *
     * @return Num 阅读次数
     */
    public Long getNum() {
        return this.num;
    }
 
    /** 
     * 获取作者（教师tid）
     *
     * @return Author 作者（教师tid）
     */
    public Long getAuthor() {
        return this.author;
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
     * 设置作业id
     *
     * @param Task_id 作业id
     */
    public void setTaskId(Long taskid) {
        this.taskId = taskid;
    }
 
    /** 
     * 设置学校id
     *
     * @param Sid 学校id
     */
    public void setSid(Long sid) {
        this.sid = sid;
    }
 
    /** 
     * 设置课程id
     *
     * @param Course_id 课程id
     */
    public void setCourseId(Long courseid) {
        this.courseId = courseid;
    }
 
    /** 
     * 设置作业标题
     *
     * @param Task_name 作业标题
     */
    public void setTaskName(String taskname) {
        this.taskName = taskname;
    }
 
    /** 
     * 设置作业内容
     *
     * @param Content 作业内容
     */
    public void setContent(String content) {
        this.content = content;
    }
 
    /** 
     * 设置作业音频
     *
     * @param Video 作业音频
     */
    public void setVideo(String video) {
        this.video = video;
    }
 
    /** 
     * 设置作业图片
     *
     * @param Pic 作业图片
     */
    public void setPic(String pic) {
        this.pic = pic;
    }
 
    /** 
     * 设置作业布置班级
     *
     * @param Task_classer 作业布置班级
     */
    public void setTaskClasser(String taskclasser) {
        this.taskClasser = taskclasser;
    }
 
    /** 
     * 设置作业开始时间
     *
     * @param Start_time 作业开始时间
     */
    public void setStartTime(String starttime) {
        this.startTime = starttime;
    }
 
    /** 
     * 设置作业结束时间
     *
     * @param End_time 作业结束时间
     */
    public void setEndTime(String endtime) {
        this.endTime = endtime;
    }
 
    /** 
     * 设置是否置顶
     *
     * @param Is_top 是否置顶
     */
    public void setIsTop(Integer istop) {
        this.isTop = istop;
    }
 
    /** 
     * 设置作业发布时间
     *
     * @param Publish_time 作业发布时间
     */
    public void setPublishTime(String publishtime) {
        this.publishTime = publishtime;
    }
 
    /** 
     * 设置审核状态
     *
     * @param Is_audit 审核状态
     */
    public void setIsAudit(Integer isaudit) {
        this.isAudit = isaudit;
    }
 
    /** 
     * 设置阅读次数
     *
     * @param Num 阅读次数
     */
    public void setNum(Long num) {
        this.num = num;
    }
 
    /** 
     * 设置作者（教师tid）
     *
     * @param Author 作者（教师tid）
     */
    public void setAuthor(Long author) {
        this.author = author;
    }
 
    /** 
     * 设置是否启用
     *
     * @param Is_use 是否启用
     */
    public void setIsUse(Integer isuse) {
        this.isUse = isuse;
    }
 
   
}
