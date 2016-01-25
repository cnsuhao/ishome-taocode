package org.ishome.jfp.intranet.beans.Department;
import javax.inject.Named;

import org.ishome.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 科室表*/
public class DepartmentDBO extends MyDataBaseObjectSupport
{
    /** 
     * 科室ID
     */
    private String deptId = null;
 
    /** 
     * 所属医院ID
     */
    private String hosId = null;
 
    /** 
     * 父科室ID
     */
    private String parDeptId = null;
 
    /** 
     * 科室名
     */
    private String name = null;
 
    /** 
     * 门诊电话
     */
    private String telNo = null;
 
    /** 
     * 备注
     */
    private String remark = null;
 
    /** 
     * 科室简介
     */
    private String resume = null;
 
    /** 
     * 科室详细介绍
     */
    private String detail = null;
 
    /** 
     * 科室位置
     */
    private String addr = null;
 
    /** 
     * 状态：冻结(0)，有效(1)，撤销(2)，删除(-1)等
     */
    private String state = null;
 
    /** 
     * 科室类型 0门诊科室 1 住院等其他科室
     */
    private String deptType = null;
 
    /** 
     * 温馨提醒
     */
    private String reminder = null;
 
    /** 
     * 更新时间，用于科室置顶排序
     */
    private String priority = null;
 
    /** 
     * HIS系统科室ID
     */
    private String accessDeptId = null;
    
    /**
     * 更新时间
     */
    private String updateTime = null;
 
    /** 
     * 获取科室ID
     *
     * @return DEPT_ID 科室ID
     */
    public String getDeptId() {
        return this.deptId;
    }
 
    /** 
     * 获取所属医院ID
     *
     * @return HOS_ID 所属医院ID
     */
    public String getHosId() {
        return this.hosId;
    }
 
    /** 
     * 获取父科室ID
     *
     * @return PAR_DEPT_ID 父科室ID
     */
    public String getParDeptId() {
        return this.parDeptId;
    }
 
    /** 
     * 获取科室名
     *
     * @return NAME 科室名
     */
    public String getName() {
        return this.name;
    }
 
    /** 
     * 获取门诊电话
     *
     * @return Tel_no 门诊电话
     */
    public String getTelNo() {
        return this.telNo;
    }
 
    /** 
     * 获取备注
     *
     * @return REMARK 备注
     */
    public String getRemark() {
        return this.remark;
    }
 
    /** 
     * 获取科室简介
     *
     * @return Resume 科室简介
     */
    public String getResume() {
        return this.resume;
    }
 
    /** 
     * 获取科室详细介绍
     *
     * @return DETAIL 科室详细介绍
     */
    public String getDetail() {
        return this.detail;
    }
 
    /** 
     * 获取科室位置
     *
     * @return ADDR 科室位置
     */
    public String getAddr() {
        return this.addr;
    }
 
    /** 
     * 获取状态：冻结(0)，有效(1)，撤销(2)，删除(-1)等
     *
     * @return STATE 状态：冻结(0)，有效(1)，撤销(2)，删除(-1)等
     */
    public String getState() {
        return this.state;
    }
 
    /** 
     * 获取科室类型 0门诊科室 1 住院等其他科室
     *
     * @return DEPT_TYPE 科室类型 0门诊科室 1 住院等其他科室
     */
    public String getDeptType() {
        return this.deptType;
    }
 
    /** 
     * 获取温馨提醒
     *
     * @return REMINDER 温馨提醒
     */
    public String getReminder() {
        return this.reminder;
    }
 
    /** 
     * 获取更新时间，用于科室置顶排序
     *
     * @return PRIORITY 更新时间，用于科室置顶排序
     */
    public String getPriority() {
        return this.priority;
    }
 
    /** 
     * 获取HIS系统科室ID
     *
     * @return ACCESS_DEPT_ID HIS系统科室ID
     */
    public String getAccessDeptId() {
        return this.accessDeptId;
    }
 
    /** 
     * 设置科室ID
     *
     * @param DEPT_ID 科室ID
     */
    public void setDeptId(String deptid) {
        this.deptId = deptid;
    }
 
    /** 
     * 设置所属医院ID
     *
     * @param HOS_ID 所属医院ID
     */
    public void setHosId(String hosid) {
        this.hosId = hosid;
    }
 
    /** 
     * 设置父科室ID
     *
     * @param PAR_DEPT_ID 父科室ID
     */
    public void setParDeptId(String pardeptid) {
        this.parDeptId = pardeptid;
    }
 
    /** 
     * 设置科室名
     *
     * @param NAME 科室名
     */
    public void setName(String name) {
        this.name = name;
    }
 
    /** 
     * 设置门诊电话
     *
     * @param Tel_no 门诊电话
     */
    public void setTelNo(String telno) {
        this.telNo = telno;
    }
 
    /** 
     * 设置备注
     *
     * @param REMARK 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
 
    /** 
     * 设置科室简介
     *
     * @param Resume 科室简介
     */
    public void setResume(String resume) {
        this.resume = resume;
    }
 
    /** 
     * 设置科室详细介绍
     *
     * @param DETAIL 科室详细介绍
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }
 
    /** 
     * 设置科室位置
     *
     * @param ADDR 科室位置
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }
 
    /** 
     * 设置状态：冻结(0)，有效(1)，撤销(2)，删除(-1)等
     *
     * @param STATE 状态：冻结(0)，有效(1)，撤销(2)，删除(-1)等
     */
    public void setState(String state) {
        this.state = state;
    }
 
    /** 
     * 设置科室类型 0门诊科室 1 住院等其他科室
     *
     * @param DEPT_TYPE 科室类型 0门诊科室 1 住院等其他科室
     */
    public void setDeptType(String depttype) {
        this.deptType = depttype;
    }
 
    /** 
     * 设置温馨提醒
     *
     * @param REMINDER 温馨提醒
     */
    public void setReminder(String reminder) {
        this.reminder = reminder;
    }
 
    /** 
     * 设置更新时间，用于科室置顶排序
     *
     * @param PRIORITY 更新时间，用于科室置顶排序
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }
 
    /** 
     * 设置HIS系统科室ID
     *
     * @param ACCESS_DEPT_ID HIS系统科室ID
     */
    public void setAccessDeptId(String accessdeptid) {
        this.accessDeptId = accessdeptid;
    }

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

 
}
