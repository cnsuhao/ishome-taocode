package com.mcookies.qxy.common.AlarmRule;
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
    private Long alarmruleId = null;
 
    /** 
     * 学校id
     */
    private Long sid = null;
 
    /** 
     * 规则名
     */
    private String alarmruleName = null;
 
    /** 
     * 学校设备id
     */
    private Long deviceId = null;
 
    /** 
     * 开始时间
     */
    private String startTime = null;
 
    /** 
     * 结束时间
     */
    private String endTime = null;
 
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
     * 获取规则id
     *
     * @return Alarmrule_id 规则id
     */
    public Long getAlarmruleId() {
        return this.alarmruleId;
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
     * @return Alarmrule_name 规则名
     */
    public String getAlarmruleName() {
        return this.alarmruleName;
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
     * 设置规则id
     *
     * @param Alarmrule_id 规则id
     */
    public void setAlarmruleId(Long alarmruleid) {
        this.alarmruleId = alarmruleid;
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
     * @param Alarmrule_name 规则名
     */
    public void setAlarmruleName(String alarmrulename) {
        this.alarmruleName = alarmrulename;
    }
 
    /** 
     * 设置学校设备id
     *
     * @param Device_id 学校设备id
     */
    public void setDeviceId(Long deviceid) {
        this.deviceId = deviceid;
    }
 
    public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
 
}
