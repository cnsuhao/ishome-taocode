package com.mcookies.qxy.common.SchoolWeixin;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 学校微信企业号表*/
public class SchoolWeixinDBO extends MyDataBaseObjectSupport
{
    /** 
     * 学校id
     */
    private Long sid = null;
 
    /** 
     * 应用ID
     */
    private String appId = null;
 
    /** 
     * 用用密钥
     */
    private String appSecret = null;
 
    /** 
     * 应用识别Token
     */
    private String token = null;
 
    /** 
     * 加密key
     */
    private String aesKey = null;
 
    /** 
     * 支付商户号
     */
    private String partnerId = null;
 
    /** 
     * 商户支付密钥
     */
    private String partnerKey = null;
 
    /** 
     * 是否启用
     */
    private Integer isUse = null;
 
    /** 
     * 获取学校id
     *
     * @return Sid 学校id
     */
    public Long getSid() {
        return this.sid;
    }
 
    /** 
     * 获取应用ID
     *
     * @return App_id 应用ID
     */
    public String getAppId() {
        return this.appId;
    }
 
    /** 
     * 获取用用密钥
     *
     * @return App_secret 用用密钥
     */
    public String getAppSecret() {
        return this.appSecret;
    }
 
    /** 
     * 获取应用识别Token
     *
     * @return Token 应用识别Token
     */
    public String getToken() {
        return this.token;
    }
 
    /** 
     * 获取加密key
     *
     * @return Aes_key 加密key
     */
    public String getAesKey() {
        return this.aesKey;
    }
 
    /** 
     * 获取支付商户号
     *
     * @return Partner_id 支付商户号
     */
    public String getPartnerId() {
        return this.partnerId;
    }
 
    /** 
     * 获取商户支付密钥
     *
     * @return Partner_key 商户支付密钥
     */
    public String getPartnerKey() {
        return this.partnerKey;
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
     * 设置学校id
     *
     * @param Sid 学校id
     */
    public void setSid(Long sid) {
        this.sid = sid;
    }
 
    /** 
     * 设置应用ID
     *
     * @param App_id 应用ID
     */
    public void setAppId(String appid) {
        this.appId = appid;
    }
 
    /** 
     * 设置用用密钥
     *
     * @param App_secret 用用密钥
     */
    public void setAppSecret(String appsecret) {
        this.appSecret = appsecret;
    }
 
    /** 
     * 设置应用识别Token
     *
     * @param Token 应用识别Token
     */
    public void setToken(String token) {
        this.token = token;
    }
 
    /** 
     * 设置加密key
     *
     * @param Aes_key 加密key
     */
    public void setAesKey(String aeskey) {
        this.aesKey = aeskey;
    }
 
    /** 
     * 设置支付商户号
     *
     * @param Partner_id 支付商户号
     */
    public void setPartnerId(String partnerid) {
        this.partnerId = partnerid;
    }
 
    /** 
     * 设置商户支付密钥
     *
     * @param Partner_key 商户支付密钥
     */
    public void setPartnerKey(String partnerkey) {
        this.partnerKey = partnerkey;
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
