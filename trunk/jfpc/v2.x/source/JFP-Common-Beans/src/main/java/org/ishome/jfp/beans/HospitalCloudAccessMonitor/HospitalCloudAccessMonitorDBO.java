package org.ishome.jfp.beans.HospitalCloudAccessMonitor;

import javax.inject.Named;

import org.ishome.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 医院云平台对接监控表*/
public class HospitalCloudAccessMonitorDBO extends MyDataBaseObjectSupport {

	/**
	 * 医院ID
	 */
	private String hosId = null;

	/**
	 * 医院名称
	 */
	private String hosName = null;

	/**
	 * 所在省
	 */
	private String addrProvince = null;

	/**
	 * 所在市
	 */
	private String addrCity = null;

	/**
	 * 管理员姓名
	 */
	private String adminName = null;

	/**
	 * 管理员电话
	 */
	private String adminPhone = null;

	/**
	 * 运行版本
	 */
	private String appVersion = null;

	/**
	 * 版本发行日期
	 */
	private String appUseDate = null;

	/**
	 * 安全等级
	 */
	private String appSecLevel = null;

	/**
	 * 加密状态
	 */
	private String encryptFlag = null;

	/**
	 * 异常次数
	 */
	private String failedCnt = null;

	/**
	 * 心跳检查状态
	 */
	private String accessFlag = null;

	/**
	 * 业务运行状态
	 */
	private String status = null;

	/**
	 * 对接验证KEY(私钥）
	 */
	private String primaryKey = null;

	/**
	 * 当前令牌值
	 */
	private String appToken = null;

	/**
	 * 对接程序包文件下载完整路径
	 */
	private String pkgUrl = null;

	/**
	 * 令牌值最后更新时间
	 */
	private String fb1 = null;

	/**
	 * 备用2
	 */
	private String fb2 = null;

	/**
	 * 数据同步运行状态
	 */
	private String fb3 = null;

	/**
	 * 备用4
	 */
	private String fb4 = null;

	/**
	 * 备用5
	 */
	private String fb5 = null;

	/**
	 * 扩展1
	 */
	private String eb1 = null;

	/**
	 * 扩展2
	 */
	private String eb2 = null;

	/**
	 * 扩展3
	 */
	private String eb3 = null;

	/**
	 * 扩展4
	 */
	private String eb4 = null;

	/**
	 * 扩展5
	 */
	private String eb5 = null;

	/**
	 * 产品ID
	 */
	private String goodId = null;

	/**
	 * 企业ID
	 */
	private String cmyId = null;

	/**
	 * 有效标识
	 */
	private String isDeleted = null;

	/**
	 * 开通日期
	 */
	private String createTime = null;

	/**
	 * 创建者
	 */
	private String creator = null;

	/**
	 * 最后更新者
	 */
	private String updator = null;


	/**
	 * 获取医院ID
	 * 
	 * @return HOS_ID 医院ID
	 */
	public String getHosId() {
		return this.hosId;
	}

	/**
	 * 获取医院名称
	 * 
	 * @return HOS_NAME 医院名称
	 */
	public String getHosName() {
		return this.hosName;
	}

	/**
	 * 获取所在省
	 * 
	 * @return ADDR_PROVINCE 所在省
	 */
	public String getAddrProvince() {
		return this.addrProvince;
	}

	/**
	 * 获取所在市
	 * 
	 * @return ADDR_CITY 所在市
	 */
	public String getAddrCity() {
		return this.addrCity;
	}

	/**
	 * 获取管理员姓名
	 * 
	 * @return ADMIN_NAME 管理员姓名
	 */
	public String getAdminName() {
		return this.adminName;
	}

	/**
	 * 获取管理员电话
	 * 
	 * @return ADMIN_PHONE 管理员电话
	 */
	public String getAdminPhone() {
		return this.adminPhone;
	}

	/**
	 * 获取运行版本
	 * 
	 * @return APP_VERSION 运行版本
	 */
	public String getAppVersion() {
		return this.appVersion;
	}

	/**
	 * 获取版本发行日期
	 * 
	 * @return APP_USE_DATE 版本发行日期
	 */
	public String getAppUseDate() {
		return this.appUseDate;
	}

	/**
	 * 获取安全等级
	 * 
	 * @return APP_SEC_LEVEL 安全等级
	 */
	public String getAppSecLevel() {
		return this.appSecLevel;
	}

	/**
	 * 获取加密状态
	 * 
	 * @return ENCRYPT_FLAG 加密状态
	 */
	public String getEncryptFlag() {
		return this.encryptFlag;
	}

	/**
	 * 获取异常次数
	 * 
	 * @return FAILED_CNT 异常次数
	 */
	public String getFailedCnt() {
		return this.failedCnt;
	}

	/**
	 * 获取心跳检查状态
	 * 
	 * @return ACCESS_FLAG 心跳检查状态
	 */
	public String getAccessFlag() {
		return this.accessFlag;
	}

	/**
	 * 获取业务运行状态
	 * 
	 * @return STATUS 业务运行状态
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * 获取对接验证KEY(私钥）
	 * 
	 * @return PRIMARY_KEY 对接验证KEY(私钥）
	 */
	public String getPrimaryKey() {
		return this.primaryKey;
	}

	/**
	 * 获取当前令牌值
	 * 
	 * @return APP_TOKEN 当前令牌值
	 */
	public String getAppToken() {
		return this.appToken;
	}

	/**
	 * 获取对接程序包文件下载完整路径
	 * 
	 * @return PKG_URL 对接程序包文件下载完整路径
	 */
	public String getPkgUrl() {
		return this.pkgUrl;
	}

	/**
	 * 获取令牌值最后更新时间
	 * 
	 * @return FB1 令牌值最后更新时间
	 */
	public String getFb1() {
		return this.fb1;
	}

	/**
	 * 获取备用2
	 * 
	 * @return FB2 备用2
	 */
	public String getFb2() {
		return this.fb2;
	}

	/**
	 * 获取数据同步运行状态
	 * 
	 * @return FB3 数据同步运行状态
	 */
	public String getFb3() {
		return this.fb3;
	}

	/**
	 * 获取备用4
	 * 
	 * @return FB4 备用4
	 */
	public String getFb4() {
		return this.fb4;
	}

	/**
	 * 获取备用5
	 * 
	 * @return FB5 备用5
	 */
	public String getFb5() {
		return this.fb5;
	}

	/**
	 * 获取扩展1
	 * 
	 * @return EB1 扩展1
	 */
	public String getEb1() {
		return this.eb1;
	}

	/**
	 * 获取扩展2
	 * 
	 * @return EB2 扩展2
	 */
	public String getEb2() {
		return this.eb2;
	}

	/**
	 * 获取扩展3
	 * 
	 * @return EB3 扩展3
	 */
	public String getEb3() {
		return this.eb3;
	}

	/**
	 * 获取扩展4
	 * 
	 * @return EB4 扩展4
	 */
	public String getEb4() {
		return this.eb4;
	}

	/**
	 * 获取扩展5
	 * 
	 * @return EB5 扩展5
	 */
	public String getEb5() {
		return this.eb5;
	}

	/**
	 * 获取产品ID
	 * 
	 * @return GOOD_ID 产品ID
	 */
	public String getGoodId() {
		return this.goodId;
	}

	/**
	 * 获取企业ID
	 * 
	 * @return CMY_ID 企业ID
	 */
	public String getCmyId() {
		return this.cmyId;
	}

	/**
	 * 获取有效标识
	 * 
	 * @return IS_DELETED 有效标识
	 */
	public String getIsDeleted() {
		return this.isDeleted;
	}

	/**
	 * 获取开通日期
	 * 
	 * @return CREATE_TIME 开通日期
	 */
	public String getCreateTime() {
		return this.createTime;
	}

	/**
	 * 获取创建者
	 * 
	 * @return CREATOR 创建者
	 */
	public String getCreator() {
		return this.creator;
	}

	/**
	 * 获取最后更新者
	 * 
	 * @return UPDATOR 最后更新者
	 */
	public String getUpdator() {
		return this.updator;
	}


	/**
	 * 设置医院ID
	 * 
	 * @param HOS_ID
	 *            医院ID
	 */
	public void setHosId(String hosid) {
		this.hosId = hosid;
	}

	/**
	 * 设置医院名称
	 * 
	 * @param HOS_NAME
	 *            医院名称
	 */
	public void setHosName(String hosname) {
		this.hosName = hosname;
	}

	/**
	 * 设置所在省
	 * 
	 * @param ADDR_PROVINCE
	 *            所在省
	 */
	public void setAddrProvince(String addrprovince) {
		this.addrProvince = addrprovince;
	}

	/**
	 * 设置所在市
	 * 
	 * @param ADDR_CITY
	 *            所在市
	 */
	public void setAddrCity(String addrcity) {
		this.addrCity = addrcity;
	}

	/**
	 * 设置管理员姓名
	 * 
	 * @param ADMIN_NAME
	 *            管理员姓名
	 */
	public void setAdminName(String adminname) {
		this.adminName = adminname;
	}

	/**
	 * 设置管理员电话
	 * 
	 * @param ADMIN_PHONE
	 *            管理员电话
	 */
	public void setAdminPhone(String adminphone) {
		this.adminPhone = adminphone;
	}

	/**
	 * 设置运行版本
	 * 
	 * @param APP_VERSION
	 *            运行版本
	 */
	public void setAppVersion(String appversion) {
		this.appVersion = appversion;
	}

	/**
	 * 设置版本发行日期
	 * 
	 * @param APP_USE_DATE
	 *            版本发行日期
	 */
	public void setAppUseDate(String appusedate) {
		this.appUseDate = appusedate;
	}

	/**
	 * 设置安全等级
	 * 
	 * @param APP_SEC_LEVEL
	 *            安全等级
	 */
	public void setAppSecLevel(String appseclevel) {
		this.appSecLevel = appseclevel;
	}

	/**
	 * 设置加密状态
	 * 
	 * @param ENCRYPT_FLAG
	 *            加密状态
	 */
	public void setEncryptFlag(String encryptflag) {
		this.encryptFlag = encryptflag;
	}

	/**
	 * 设置异常次数
	 * 
	 * @param FAILED_CNT
	 *            异常次数
	 */
	public void setFailedCnt(String failedcnt) {
		this.failedCnt = failedcnt;
	}

	/**
	 * 设置心跳检查状态
	 * 
	 * @param ACCESS_FLAG
	 *            心跳检查状态
	 */
	public void setAccessFlag(String accessflag) {
		this.accessFlag = accessflag;
	}

	/**
	 * 设置业务运行状态
	 * 
	 * @param STATUS
	 *            业务运行状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 设置对接验证KEY(私钥）
	 * 
	 * @param PRIMARY_KEY
	 *            对接验证KEY(私钥）
	 */
	public void setPrimaryKey(String primarykey) {
		this.primaryKey = primarykey;
	}

	/**
	 * 设置当前令牌值
	 * 
	 * @param APP_TOKEN
	 *            当前令牌值
	 */
	public void setAppToken(String apptoken) {
		this.appToken = apptoken;
	}

	/**
	 * 设置对接程序包文件下载完整路径
	 * 
	 * @param PKG_URL
	 *            对接程序包文件下载完整路径
	 */
	public void setPkgUrl(String pkgurl) {
		this.pkgUrl = pkgurl;
	}

	/**
	 * 设置令牌值最后更新时间
	 * 
	 * @param FB1
	 *            令牌值最后更新时间
	 */
	public void setFb1(String fb1) {
		this.fb1 = fb1;
	}

	/**
	 * 设置备用2
	 * 
	 * @param FB2
	 *            备用2
	 */
	public void setFb2(String fb2) {
		this.fb2 = fb2;
	}

	/**
	 * 设置数据同步运行状态
	 * 
	 * @param FB3
	 *            数据同步运行状态
	 */
	public void setFb3(String fb3) {
		this.fb3 = fb3;
	}

	/**
	 * 设置备用4
	 * 
	 * @param FB4
	 *            备用4
	 */
	public void setFb4(String fb4) {
		this.fb4 = fb4;
	}

	/**
	 * 设置备用5
	 * 
	 * @param FB5
	 *            备用5
	 */
	public void setFb5(String fb5) {
		this.fb5 = fb5;
	}

	/**
	 * 设置扩展1
	 * 
	 * @param EB1
	 *            扩展1
	 */
	public void setEb1(String eb1) {
		this.eb1 = eb1;
	}

	/**
	 * 设置扩展2
	 * 
	 * @param EB2
	 *            扩展2
	 */
	public void setEb2(String eb2) {
		this.eb2 = eb2;
	}

	/**
	 * 设置扩展3
	 * 
	 * @param EB3
	 *            扩展3
	 */
	public void setEb3(String eb3) {
		this.eb3 = eb3;
	}

	/**
	 * 设置扩展4
	 * 
	 * @param EB4
	 *            扩展4
	 */
	public void setEb4(String eb4) {
		this.eb4 = eb4;
	}

	/**
	 * 设置扩展5
	 * 
	 * @param EB5
	 *            扩展5
	 */
	public void setEb5(String eb5) {
		this.eb5 = eb5;
	}

	/**
	 * 设置产品ID
	 * 
	 * @param GOOD_ID
	 *            产品ID
	 */
	public void setGoodId(String goodid) {
		this.goodId = goodid;
	}

	/**
	 * 设置企业ID
	 * 
	 * @param CMY_ID
	 *            企业ID
	 */
	public void setCmyId(String cmyid) {
		this.cmyId = cmyid;
	}

	/**
	 * 设置有效标识
	 * 
	 * @param IS_DELETED
	 *            有效标识
	 */
	public void setIsDeleted(String isdeleted) {
		this.isDeleted = isdeleted;
	}

	/**
	 * 设置开通日期
	 * 
	 * @param CREATE_TIME
	 *            开通日期
	 */
	public void setCreateTime(String createtime) {
		this.createTime = createtime;
	}

	/**
	 * 设置创建者
	 * 
	 * @param CREATOR
	 *            创建者
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * 设置最后更新者
	 * 
	 * @param UPDATOR
	 *            最后更新者
	 */
	public void setUpdator(String updator) {
		this.updator = updator;
	}
}
