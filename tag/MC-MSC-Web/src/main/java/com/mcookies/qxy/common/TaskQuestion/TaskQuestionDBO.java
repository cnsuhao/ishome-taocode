package com.mcookies.qxy.common.TaskQuestion;
import java.sql.Date;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 作业题目表*/
public class TaskQuestionDBO extends MyDataBaseObjectSupport
{
    /** 
     * 题目id
     */
    private Long questionId = null;
 
    /** 
     * 作业id
     */
    private Long taskId = null;
 
    /** 
     * 作业图片
     */
    private String pic = null;
 
    /** 
     * 锚点序号
     */
    private Long number = null;
 
    /** 
     * 锚点坐标
     */
    private String coordinate = null;
 
    /** 
     * 答案类型
     */
    private Integer answerType = null;
 
    /** 
     * 答案内容
     */
    private String answer = null;
 
    /** 
     * 是否启用
     */
    private Integer isUse = null;
 
   
    /** 
     * 获取题目id
     *
     * @return Question_id 题目id
     */
    public Long getQuestionId() {
        return this.questionId;
    }
 
    /** 
     * 获取作业id
     *
     * @return Task_id 作业id
     */
    public Long getTaskId() {
        return this.taskId;
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
     * 获取锚点序号
     *
     * @return Number 锚点序号
     */
    public Long getNumber() {
        return this.number;
    }
 
    /** 
     * 获取锚点坐标
     *
     * @return Coordinate 锚点坐标
     */
    public String getCoordinate() {
        return this.coordinate;
    }
 
    /** 
     * 获取答案类型
     *
     * @return Answer_type 答案类型
     */
    public Integer getAnswerType() {
        return this.answerType;
    }
 
    /** 
     * 获取答案内容
     *
     * @return Answer 答案内容
     */
    public String getAnswer() {
        return this.answer;
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
     * 设置题目id
     *
     * @param Question_id 题目id
     */
    public void setQuestionId(Long questionid) {
        this.questionId = questionid;
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
     * 设置作业图片
     *
     * @param Pic 作业图片
     */
    public void setPic(String pic) {
        this.pic = pic;
    }
 
    /** 
     * 设置锚点序号
     *
     * @param Number 锚点序号
     */
    public void setNumber(Long number) {
        this.number = number;
    }
 
    /** 
     * 设置锚点坐标
     *
     * @param Coordinate 锚点坐标
     */
    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
 
    /** 
     * 设置答案类型
     *
     * @param Answer_type 答案类型
     */
    public void setAnswerType(Integer answertype) {
        this.answerType = answertype;
    }
 
    /** 
     * 设置答案内容
     *
     * @param Answer 答案内容
     */
    public void setAnswer(String answer) {
        this.answer = answer;
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
