package org.ishome.jfp.mobile.beans.Demo;
import java.math.BigDecimal;

import javax.inject.Named;

import org.ishome.jfp.framework.support.MyDataBaseObjectSupport;


/**
 * 业务处理类实体
 * @author Spook
 *
 */
@Named
public class DemoDBO extends MyDataBaseObjectSupport
{
    /** 
     * 主键
     */
    private String errorId = null;
 
    /** 
     * 错误类型ID,从元数据表(metadata)里选择
     */
    private String errorMetaId = null;
 
    /** 
     * 科室医生类型：0科室 1医生
     */
    private BigDecimal type = BigDecimal.ZERO;
 
    /** 
     * 错误描述
     */
    private String description = null;
 
    /** 
     * 错误来源,0 本地检索 1 对接
     */
    private BigDecimal state = BigDecimal.ZERO;
 
    /** 
     * 医院ID
     */
    private String hosId = null;
 
    /** 
     * 创建时间
     */
    private String createTime = null;
 
    /** 
     * 获取主键
     *
     * @return ERROR_ID 主键
     */
    public String getErrorId() {
        return this.errorId;
    }
 
    /** 
     * 获取错误类型ID,从元数据表(metadata)里选择
     *
     * @return ERROR_META_ID 错误类型ID,从元数据表(metadata)里选择
     */
    public String getErrorMetaId() {
        return this.errorMetaId;
    }
 
    /** 
     * 获取科室医生类型：0科室 1医生
     *
     * @return TYPE 科室医生类型：0科室 1医生
     */
    public BigDecimal getType() {
        return this.type;
    }
 
    /** 
     * 获取错误描述
     *
     * @return DESCRIPTION 错误描述
     */
    public String getDescription() {
        return this.description;
    }
 
    /** 
     * 获取错误来源,0 本地检索 1 对接
     *
     * @return STATE 错误来源,0 本地检索 1 对接
     */
    public BigDecimal getState() {
        return this.state;
    }
 
    /** 
     * 获取医院ID
     *
     * @return HOS_ID 医院ID
     */
    public String getHosId() {
        return this.hosId;
    }
 
    /** 
     * 获取创建时间
     *
     * @return CREATE_TIME 创建时间
     */
    public String getCreateTime() {
        return this.createTime;
    }
 
    /** 
     * 设置主键
     *
     * @param ERROR_ID 主键
     */
    public void setErrorId(String errorid) {
        this.errorId = errorid;
    }
 
    /** 
     * 设置错误类型ID,从元数据表(metadata)里选择
     *
     * @param ERROR_META_ID 错误类型ID,从元数据表(metadata)里选择
     */
    public void setErrorMetaId(String errormetaid) {
        this.errorMetaId = errormetaid;
    }
 
    /** 
     * 设置科室医生类型：0科室 1医生
     *
     * @param TYPE 科室医生类型：0科室 1医生
     */
    public void setType(BigDecimal type) {
        this.type = type;
    }
 
    /** 
     * 设置错误描述
     *
     * @param DESCRIPTION 错误描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
 
    /** 
     * 设置错误来源,0 本地检索 1 对接
     *
     * @param STATE 错误来源,0 本地检索 1 对接
     */
    public void setState(BigDecimal state) {
        this.state = state;
    }
 
    /** 
     * 设置医院ID
     *
     * @param HOS_ID 医院ID
     */
    public void setHosId(String hosid) {
        this.hosId = hosid;
    }
 
    /** 
     * 设置创建时间
     *
     * @param CREATE_TIME 创建时间
     */
    public void setCreateTime(String createtime) {
        this.createTime = createtime;
    }
 
}
