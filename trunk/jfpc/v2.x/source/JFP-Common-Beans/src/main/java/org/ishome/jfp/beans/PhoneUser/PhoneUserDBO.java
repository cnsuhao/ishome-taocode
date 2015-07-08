package org.ishome.jfp.beans.PhoneUser;
import java.math.BigDecimal;
import java.util.Date;

import javax.inject.Named;

import org.ishome.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 手机注册信息用户表*/
public class PhoneUserDBO extends MyDataBaseObjectSupport
{
    /** 
     * 用户ID
     */
    private String usId = null;
 
    /** 
     * 医院ID，为空时表示公共用户
     */
    private String hosId = null;
 
    /** 
     * 用户名
     */
    private String userName = null;
 
    /** 
     * 密码
     */
    private String password = null;
 
    /** 
     * 手机号码
     */
    private String mobilePhone = null;
 
    /** 
     * 是否已婚 0否 1已
     */
    private BigDecimal isMarriage = BigDecimal.ZERO;
 
    /** 
     * 工作单位
     */
    private String company = null;
 
    /** 
     * 地址
     */
    private String address = null;
 
    /** 
     * 邮箱
     */
    private String email = null;
 
    /** 
     * 创建时间
     */
    private Date createTime = null;
 
    /** 
     * 手机IMei
     */
    private String imei = null;
 
    /** 
     * 终端类型：android:1,ios:2
     */
    private BigDecimal terminalType = BigDecimal.ZERO;
 
    /** 
     * 手机的唯一标识
     */
    private String token = null;
 
    /** 
     * 性别
     */
    private BigDecimal sex = BigDecimal.ZERO;
 
    /** 
     * 苹果手机的PUSH TOKEN
     */
    private String pushToken = null;
 
    /** 
     * 来自腾讯的QQ登录标识，支付宝，微信等，取经与THIRD_PARTY
     */
    private String openid = null;
 
    /** 
     * 近身照
     */
    private String bodyPhoto = null;
 
    /** 
     * 头像照
     */
    private String headPhoto = null;
 
    /** 
     * 昵称
     */
    private String nickname = null;
 
    /** 
     * 第三方用户登录认证标识，标识openid的来源
     */
    private BigDecimal thirdParty = BigDecimal.ZERO;
 
    /** 
     * 临时保存，用于将来升级，密码加密
     */
    private String forSalt = null;
 
    /** 
     * 身份证
     */
    private String identityCard = null;
 
    /** 
     * 姓名
     */
    private String realName = null;
 
    /** 
     * 获取用户ID
     *
     * @return US_ID 用户ID
     */
    public String getUsId() {
        return this.usId;
    }
 
    /** 
     * 获取医院ID，为空时表示公共用户
     *
     * @return HOS_ID 医院ID，为空时表示公共用户
     */
    public String getHosId() {
        return this.hosId;
    }
 
    /** 
     * 获取用户名
     *
     * @return USER_NAME 用户名
     */
    public String getUserName() {
        return this.userName;
    }
 
    /** 
     * 获取密码
     *
     * @return PASSWORD 密码
     */
    public String getPassword() {
        return this.password;
    }
 
    /** 
     * 获取手机号码
     *
     * @return MOBILE_PHONE 手机号码
     */
    public String getMobilePhone() {
        return this.mobilePhone;
    }
 
    /** 
     * 获取是否已婚 0否 1已
     *
     * @return IS_MARRIAGE 是否已婚 0否 1已
     */
    public BigDecimal getIsMarriage() {
        return this.isMarriage;
    }
 
    /** 
     * 获取工作单位
     *
     * @return COMPANY 工作单位
     */
    public String getCompany() {
        return this.company;
    }
 
    /** 
     * 获取地址
     *
     * @return ADDRESS 地址
     */
    public String getAddress() {
        return this.address;
    }
 
    /** 
     * 获取邮箱
     *
     * @return EMAIL 邮箱
     */
    public String getEmail() {
        return this.email;
    }
 
    /** 
     * 获取创建时间
     *
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return this.createTime;
    }
 
    /** 
     * 获取手机IMei
     *
     * @return IMEI 手机IMei
     */
    public String getImei() {
        return this.imei;
    }
 
    /** 
     * 获取终端类型：android:1,ios:2
     *
     * @return Terminal_type 终端类型：android:1,ios:2
     */
    public BigDecimal getTerminalType() {
        return this.terminalType;
    }
 
    /** 
     * 获取手机的唯一标识
     *
     * @return TOKEN 手机的唯一标识
     */
    public String getToken() {
        return this.token;
    }
 
    /** 
     * 获取性别
     *
     * @return SEX 性别
     */
    public BigDecimal getSex() {
        return this.sex;
    }
 
    /** 
     * 获取苹果手机的PUSH TOKEN
     *
     * @return PUSH_TOKEN 苹果手机的PUSH TOKEN
     */
    public String getPushToken() {
        return this.pushToken;
    }
 
    /** 
     * 获取来自腾讯的QQ登录标识，支付宝，微信等，取经与THIRD_PARTY
     *
     * @return Openid 来自腾讯的QQ登录标识，支付宝，微信等，取经与THIRD_PARTY
     */
    public String getOpenid() {
        return this.openid;
    }
 
    /** 
     * 获取近身照
     *
     * @return Body_photo 近身照
     */
    public String getBodyPhoto() {
        return this.bodyPhoto;
    }
 
    /** 
     * 获取头像照
     *
     * @return Head_photo 头像照
     */
    public String getHeadPhoto() {
        return this.headPhoto;
    }
 
    /** 
     * 获取昵称
     *
     * @return NICKNAME 昵称
     */
    public String getNickname() {
        return this.nickname;
    }
 
    /** 
     * 获取第三方用户登录认证标识，标识openid的来源
     *
     * @return THIRD_PARTY 第三方用户登录认证标识，标识openid的来源
     */
    public BigDecimal getThirdParty() {
        return this.thirdParty;
    }
 
    /** 
     * 获取临时保存，用于将来升级，密码加密
     *
     * @return FOR_SALT 临时保存，用于将来升级，密码加密
     */
    public String getForSalt() {
        return this.forSalt;
    }
 
    /** 
     * 获取身份证
     *
     * @return IDENTITY_CARD 身份证
     */
    public String getIdentityCard() {
        return this.identityCard;
    }
 
    /** 
     * 获取姓名
     *
     * @return REAL_NAME 姓名
     */
    public String getRealName() {
        return this.realName;
    }
 
    /** 
     * 设置用户ID
     *
     * @param US_ID 用户ID
     */
    public void setUsId(String usid) {
        this.usId = usid;
    }
 
    /** 
     * 设置医院ID，为空时表示公共用户
     *
     * @param HOS_ID 医院ID，为空时表示公共用户
     */
    public void setHosId(String hosid) {
        this.hosId = hosid;
    }
 
    /** 
     * 设置用户名
     *
     * @param USER_NAME 用户名
     */
    public void setUserName(String username) {
        this.userName = username;
    }
 
    /** 
     * 设置密码
     *
     * @param PASSWORD 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
 
    /** 
     * 设置手机号码
     *
     * @param MOBILE_PHONE 手机号码
     */
    public void setMobilePhone(String mobilephone) {
        this.mobilePhone = mobilephone;
    }
 
    /** 
     * 设置是否已婚 0否 1已
     *
     * @param IS_MARRIAGE 是否已婚 0否 1已
     */
    public void setIsMarriage(BigDecimal ismarriage) {
        this.isMarriage = ismarriage;
    }
 
    /** 
     * 设置工作单位
     *
     * @param COMPANY 工作单位
     */
    public void setCompany(String company) {
        this.company = company;
    }
 
    /** 
     * 设置地址
     *
     * @param ADDRESS 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }
 
    /** 
     * 设置邮箱
     *
     * @param EMAIL 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }
 
    /** 
     * 设置创建时间
     *
     * @param CREATE_TIME 创建时间
     */
    public void setCreateTime(Date createtime) {
        this.createTime = createtime;
    }
 
    /** 
     * 设置手机IMei
     *
     * @param IMEI 手机IMei
     */
    public void setImei(String imei) {
        this.imei = imei;
    }
 
    /** 
     * 设置终端类型：android:1,ios:2
     *
     * @param Terminal_type 终端类型：android:1,ios:2
     */
    public void setTerminalType(BigDecimal terminaltype) {
        this.terminalType = terminaltype;
    }
 
    /** 
     * 设置手机的唯一标识
     *
     * @param TOKEN 手机的唯一标识
     */
    public void setToken(String token) {
        this.token = token;
    }
 
    /** 
     * 设置性别
     *
     * @param SEX 性别
     */
    public void setSex(BigDecimal sex) {
        this.sex = sex;
    }
 
    /** 
     * 设置苹果手机的PUSH TOKEN
     *
     * @param PUSH_TOKEN 苹果手机的PUSH TOKEN
     */
    public void setPushToken(String pushtoken) {
        this.pushToken = pushtoken;
    }
 
    /** 
     * 设置来自腾讯的QQ登录标识，支付宝，微信等，取经与THIRD_PARTY
     *
     * @param Openid 来自腾讯的QQ登录标识，支付宝，微信等，取经与THIRD_PARTY
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }
 
    /** 
     * 设置近身照
     *
     * @param Body_photo 近身照
     */
    public void setBodyPhoto(String bodyphoto) {
        this.bodyPhoto = bodyphoto;
    }
 
    /** 
     * 设置头像照
     *
     * @param Head_photo 头像照
     */
    public void setHeadPhoto(String headphoto) {
        this.headPhoto = headphoto;
    }
 
    /** 
     * 设置昵称
     *
     * @param NICKNAME 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
 
    /** 
     * 设置第三方用户登录认证标识，标识openid的来源
     *
     * @param THIRD_PARTY 第三方用户登录认证标识，标识openid的来源
     */
    public void setThirdParty(BigDecimal thirdparty) {
        this.thirdParty = thirdparty;
    }
 
    /** 
     * 设置临时保存，用于将来升级，密码加密
     *
     * @param FOR_SALT 临时保存，用于将来升级，密码加密
     */
    public void setForSalt(String forsalt) {
        this.forSalt = forsalt;
    }
 
    /** 
     * 设置身份证
     *
     * @param IDENTITY_CARD 身份证
     */
    public void setIdentityCard(String identitycard) {
        this.identityCard = identitycard;
    }
 
    /** 
     * 设置姓名
     *
     * @param REAL_NAME 姓名
     */
    public void setRealName(String realname) {
        this.realName = realname;
    }
 
}
