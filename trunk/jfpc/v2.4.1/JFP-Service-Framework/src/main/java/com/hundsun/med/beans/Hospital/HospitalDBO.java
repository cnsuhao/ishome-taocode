package com.hundsun.med.beans.Hospital;
import java.math.BigDecimal;
import java.util.Date;

import javax.inject.Named;

import org.ishome.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 医院表 */
public class HospitalDBO extends MyDataBaseObjectSupport
{
    /** 
     * 医院ID
     */
    private String hosId = null;
 
    /** 
     * 父医院ID
     */
    private String parHosId = null;
 
    /** 
     * 医院名称
     */
    private String name = null;
 
    /** 
     * 用于权限判断字段前缀为RESC_*
     */
    private String rescCode = null;
 
    /** 
     * 由卫生局统一下发编号
     */
    private String unifyCode = null;
 
    /** 
     * 医院地址
     */
    private String addr = null;
 
    /** 
     * 医院等级(三甲医院)
     */
    private String nature = null;
 
    /** 
     * 医院行政级别
     */
    private String hosLevel = null;
 
    /** 
     * 联系电话
     */
    private String telPhone = null;
 
    /** 
     * 医院网址
     */
    private String webSite = null;
 
    /** 
     * 医院简介
     */
    private String summary = null;
 
    /** 
     * 备注
     */
    private String remark = null;
 
    /** 
     * 联系人
     */
    private String contacter = null;
 
    /** 
     * 联系人电话
     */
    private String contactPhone = null;
 
    /** 
     * 附近交通
     */
    private String traffic = null;
 
    /** 
     * 图片ID
     */
    private String picId = null;
 
    /** 
     * 医院平面图ID
     */
    private String planegraphId = null;
 
    /** 
     * 创建或更新时间
     */
    private Date createTime = null;
 
    /** 
     * 是否电话预约：在线预约(0)，电话预约(1)
     */
    private BigDecimal regType = BigDecimal.ZERO;
 
    /** 
     * 预约电话
     */
    private String regTel = null;
 
    /** 
     * 经度
     */
    private String longitude = null;
 
    /** 
     * 纬度
     */
    private String latitude = null;
 
    /** 
     * 所在城市
     */
    private String location = null;
 
    /** 
     * 标识该医院是否集团医院
     */
    private BigDecimal isGroup = BigDecimal.ZERO;
 
    /** 
     * LOGO的图片文件URL
     */
    private String logo = null;
 
    /** 
     * HIS系统医院ID
     */
    private String hosCode = null;
 
    /** 
     * 获取医院ID
     *
     * @return HOS_ID 医院ID
     */
    public String getHosId() {
        return this.hosId;
    }
 
    /** 
     * 获取父医院ID
     *
     * @return PAR_HOS_ID 父医院ID
     */
    public String getParHosId() {
        return this.parHosId;
    }
 
    /** 
     * 获取医院名称
     *
     * @return NAME 医院名称
     */
    public String getName() {
        return this.name;
    }
 
    /** 
     * 获取用于权限判断字段前缀为RESC_*
     *
     * @return RESC_CODE 用于权限判断字段前缀为RESC_*
     */
    public String getRescCode() {
        return this.rescCode;
    }
 
    /** 
     * 获取由卫生局统一下发编号
     *
     * @return UNIFY_CODE 由卫生局统一下发编号
     */
    public String getUnifyCode() {
        return this.unifyCode;
    }
 
    /** 
     * 获取医院地址
     *
     * @return ADDR 医院地址
     */
    public String getAddr() {
        return this.addr;
    }
 
    /** 
     * 获取医院等级(三甲医院)
     *
     * @return NATURE 医院等级(三甲医院)
     */
    public String getNature() {
        return this.nature;
    }
 
    /** 
     * 获取医院行政级别
     *
     * @return HOS_LEVEL 医院行政级别
     */
    public String getHosLevel() {
        return this.hosLevel;
    }
 
    /** 
     * 获取联系电话
     *
     * @return TEL_PHONE 联系电话
     */
    public String getTelPhone() {
        return this.telPhone;
    }
 
    /** 
     * 获取医院网址
     *
     * @return WEB_SITE 医院网址
     */
    public String getWebSite() {
        return this.webSite;
    }
 
    /** 
     * 获取医院简介
     *
     * @return SUMMARY 医院简介
     */
    public String getSummary() {
        return this.summary;
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
     * 获取联系人
     *
     * @return CONTACTER 联系人
     */
    public String getContacter() {
        return this.contacter;
    }
 
    /** 
     * 获取联系人电话
     *
     * @return CONTACT_PHONE 联系人电话
     */
    public String getContactPhone() {
        return this.contactPhone;
    }
 
    /** 
     * 获取附近交通
     *
     * @return TRAFFIC 附近交通
     */
    public String getTraffic() {
        return this.traffic;
    }
 
    /** 
     * 获取图片ID
     *
     * @return Pic_id 图片ID
     */
    public String getPicId() {
        return this.picId;
    }
 
    /** 
     * 获取医院平面图ID
     *
     * @return Planegraph_id 医院平面图ID
     */
    public String getPlanegraphId() {
        return this.planegraphId;
    }
 
    /** 
     * 获取创建或更新时间
     *
     * @return CREATE_TIME 创建或更新时间
     */
    public Date getCreateTime() {
        return this.createTime;
    }
 
    /** 
     * 获取是否电话预约：在线预约(0)，电话预约(1)
     *
     * @return REG_TYPE 是否电话预约：在线预约(0)，电话预约(1)
     */
    public BigDecimal getRegType() {
        return this.regType;
    }
 
    /** 
     * 获取预约电话
     *
     * @return REG_TEL 预约电话
     */
    public String getRegTel() {
        return this.regTel;
    }
 
    /** 
     * 获取经度
     *
     * @return LONGITUDE 经度
     */
    public String getLongitude() {
        return this.longitude;
    }
 
    /** 
     * 获取纬度
     *
     * @return LATITUDE 纬度
     */
    public String getLatitude() {
        return this.latitude;
    }
 
    /** 
     * 获取所在城市
     *
     * @return LOCATION 所在城市
     */
    public String getLocation() {
        return this.location;
    }
 
    /** 
     * 获取标识该医院是否集团医院
     *
     * @return IS_GROUP 标识该医院是否集团医院
     */
    public BigDecimal getIsGroup() {
        return this.isGroup;
    }
 
    /** 
     * 获取LOGO的图片文件URL
     *
     * @return LOGO LOGO的图片文件URL
     */
    public String getLogo() {
        return this.logo;
    }
 
    /** 
     * 获取HIS系统医院ID
     *
     * @return HOS_CODE HIS系统医院ID
     */
    public String getHosCode() {
        return this.hosCode;
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
     * 设置父医院ID
     *
     * @param PAR_HOS_ID 父医院ID
     */
    public void setParHosId(String parhosid) {
        this.parHosId = parhosid;
    }
 
    /** 
     * 设置医院名称
     *
     * @param NAME 医院名称
     */
    public void setName(String name) {
        this.name = name;
    }
 
    /** 
     * 设置用于权限判断字段前缀为RESC_*
     *
     * @param RESC_CODE 用于权限判断字段前缀为RESC_*
     */
    public void setRescCode(String resccode) {
        this.rescCode = resccode;
    }
 
    /** 
     * 设置由卫生局统一下发编号
     *
     * @param UNIFY_CODE 由卫生局统一下发编号
     */
    public void setUnifyCode(String unifycode) {
        this.unifyCode = unifycode;
    }
 
    /** 
     * 设置医院地址
     *
     * @param ADDR 医院地址
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }
 
    /** 
     * 设置医院等级(三甲医院)
     *
     * @param NATURE 医院等级(三甲医院)
     */
    public void setNature(String nature) {
        this.nature = nature;
    }
 
    /** 
     * 设置医院行政级别
     *
     * @param HOS_LEVEL 医院行政级别
     */
    public void setHosLevel(String hoslevel) {
        this.hosLevel = hoslevel;
    }
 
    /** 
     * 设置联系电话
     *
     * @param TEL_PHONE 联系电话
     */
    public void setTelPhone(String telphone) {
        this.telPhone = telphone;
    }
 
    /** 
     * 设置医院网址
     *
     * @param WEB_SITE 医院网址
     */
    public void setWebSite(String website) {
        this.webSite = website;
    }
 
    /** 
     * 设置医院简介
     *
     * @param SUMMARY 医院简介
     */
    public void setSummary(String summary) {
        this.summary = summary;
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
     * 设置联系人
     *
     * @param CONTACTER 联系人
     */
    public void setContacter(String contacter) {
        this.contacter = contacter;
    }
 
    /** 
     * 设置联系人电话
     *
     * @param CONTACT_PHONE 联系人电话
     */
    public void setContactPhone(String contactphone) {
        this.contactPhone = contactphone;
    }
 
    /** 
     * 设置附近交通
     *
     * @param TRAFFIC 附近交通
     */
    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }
 
    /** 
     * 设置图片ID
     *
     * @param Pic_id 图片ID
     */
    public void setPicId(String picid) {
        this.picId = picid;
    }
 
    /** 
     * 设置医院平面图ID
     *
     * @param Planegraph_id 医院平面图ID
     */
    public void setPlanegraphId(String planegraphid) {
        this.planegraphId = planegraphid;
    }
 
    /** 
     * 设置创建或更新时间
     *
     * @param CREATE_TIME 创建或更新时间
     */
    public void setCreateTime(Date createtime) {
        this.createTime = createtime;
    }
 
    /** 
     * 设置是否电话预约：在线预约(0)，电话预约(1)
     *
     * @param REG_TYPE 是否电话预约：在线预约(0)，电话预约(1)
     */
    public void setRegType(BigDecimal regtype) {
        this.regType = regtype;
    }
 
    /** 
     * 设置预约电话
     *
     * @param REG_TEL 预约电话
     */
    public void setRegTel(String regtel) {
        this.regTel = regtel;
    }
 
    /** 
     * 设置经度
     *
     * @param LONGITUDE 经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
 
    /** 
     * 设置纬度
     *
     * @param LATITUDE 纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
 
    /** 
     * 设置所在城市
     *
     * @param LOCATION 所在城市
     */
    public void setLocation(String location) {
        this.location = location;
    }
 
    /** 
     * 设置标识该医院是否集团医院
     *
     * @param IS_GROUP 标识该医院是否集团医院
     */
    public void setIsGroup(BigDecimal isgroup) {
        this.isGroup = isgroup;
    }
 
    /** 
     * 设置LOGO的图片文件URL
     *
     * @param LOGO LOGO的图片文件URL
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }
 
    /** 
     * 设置HIS系统医院ID
     *
     * @param HOS_CODE HIS系统医院ID
     */
    public void setHosCode(String hoscode) {
        this.hosCode = hoscode;
    }
 
}
