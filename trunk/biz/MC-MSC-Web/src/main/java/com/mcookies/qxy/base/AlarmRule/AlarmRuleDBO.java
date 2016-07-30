package com.mcookies.qxy.base.AlarmRule;
import java.sql.Date;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 报警规则表*/
public class AlarmRuleDBO extends MyDataBaseObjectSupport
{
    /** 
     * 规则id
     */
    private Long alarmId = null;
 
    /** 
     * 学校id
     */
    private Long sid = null;
 
    /** 
     * 规则名
     */
    private String name = null;
 
    /** 
     * 学校设备id
     */
    private Long deviceId = null;
 
    /** 
     * 开始时间
     */
    private Date startTime = null;
 
    /** 
     * 结束时间
     */
    private Date endTime = null;
 
    /** 
     * 行为状态
     */
    private Integer flag = null;
 
    /** 
     * 生效时间设置类型
     */
    private Integer timeType = null;
 
    /** 
     * 指定重复时间
     */
    private String timeRepeat = null;
 
    /** 
     * 指定特定日期
     */
    private String timeDate = null;
 
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
     * 获取规则id
     *
     * @return Alarm_id 规则id
     */
    public Long getAlarmId() {
        return this.alarmId;
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
     * 获取规则名
     *
     * @return Name 规则名
     */
    public String getName() {
        return this.name;
    }
 
    /** 
     * 获取学校设备id
     *
     * @return Device_id 学校设备id
     */
    public Long getDeviceId() {
        return this.deviceId;
    }
 
    /** 
     * 获取开始时间
     *
     * @return Start_time 开始时间
     */
    public Date getStartTime() {
        return this.startTime;
    }
 
    /** 
     * 获取结束时间
     *
     * @return End_time 结束时间
     */
    public Date getEndTime() {
        return this.endTime;
    }
 
    /** 
     * 获取行为状态
     *
     * @return Flag 行为状态
     */
    public Integer getFlag() {
        return this.flag;
    }
 
    /** 
     * 获取生效时间设置类型
     *
     * @return Time_type 生效时间设置类型
     */
    public Integer getTimeType() {
        return this.timeType;
    }
 
    /** 
     * 获取指定重复时间
     *
     * @return Time_repeat 指定重复时间
     */
    public String getTimeRepeat() {
        return this.timeRepeat;
    }
 
    /** 
     * 获取指定特定日期
     *
     * @return Time_date 指定特定日期
     */
    public String getTimeDate() {
        return this.timeDate;
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
     * 设置规则id
     *
     * @param Alarm_id 规则id
     */
    public void setAlarmId(Long alarmid) {
        this.alarmId = alarmid;
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
     * 设置规则名
     *
     * @param Name 规则名
     */
    public void setName(String name) {
        this.name = name;
    }
 
    /** 
     * 设置学校设备id
     *
     * @param Device_id 学校设备id
     */
    public void setDeviceId(Long deviceid) {
        this.deviceId = deviceid;
    }
 
    /** 
     * 设置开始时间
     *
     * @param Start_time 开始时间
     */
    public void setStartTime(Date starttime) {
        this.startTime = starttime;
    }
 
    /** 
     * 设置结束时间
     *
     * @param End_time 结束时间
     */
    public void setEndTime(Date endtime) {
        this.endTime = endtime;
    }
 
    /** 
     * 设置行为状态
     *
     * @param Flag 行为状态
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }
 
    /** 
     * 设置生效时间设置类型
     *
     * @param Time_type 生效时间设置类型
     */
    public void setTimeType(Integer timetype) {
        this.timeType = timetype;
    }
 
    /** 
     * 设置指定重复时间
     *
     * @param Time_repeat 指定重复时间
     */
    public void setTimeRepeat(String timerepeat) {
        this.timeRepeat = timerepeat;
    }
 
    /** 
     * 设置指定特定日期
     *
     * @param Time_date 指定特定日期
     */
    public void setTimeDate(String timedate) {
        this.timeDate = timedate;
    }
 
    /** 
     * 设置是否启用
     *
     * @param Is_use 是否启用
     */
    public void setIsUse(Integer isuse) {
        this.isUse = isuse;
    }
 
    /** 
     * 设置创建时间
     *
     * @param Create_time 创建时间
     */
    public void setCreateTime(String createtime) {
        this.createTime = createtime;
    }
 
    /** 
     * 设置创建者
     *
     * @param Creator 创建者
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }
 
    /** 
     * 设置更新时间
     *
     * @param Update_time 更新时间
     */
    public void setUpdateTime(String updatetime) {
        this.updateTime = updatetime;
    }
 
    /** 
     * 设置最后更新者
     *
     * @param Updator 最后更新者
     */
    public void setUpdator(Long updator) {
        this.updator = updator;
    }
 
}
