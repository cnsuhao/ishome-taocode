package org.isotope.jfp.framework.beans.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.isotope.jfp.framework.beans.user.TokenBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.token.BusinessTokenHelper;

/**
 * 业务请求Token数据算法
 * 
 * @author Spook
 * @since 2.3.1
 * @version 2.3.1 2015/6/23
 * @see BusinessTokenBean
 */
public class BusinessTokenBean extends TokenBean implements ISFrameworkConstants {

	/**
	 * MMddHHmm
	 * 
	 * @return
	 */
	public static String loginTime() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmm");
		return format.format(new Date());
	}

	public static String getBizToken(BusinessTokenBean loginer) {
		return BusinessTokenHelper.getBizTokenData("" + loginer.getSchoolId(), "" + loginer.getUserId(), loginer.getUserType() + loginTime());
	}
	
	//
	///{companyId}														18
	//--------/{userId}													18
	//-----------------/{bizName}										8
	//---------------------------/{encryType}							1
	//---------------------------------------/{clientTimestamp}			8 (MMDDH24)	
	public static BusinessTokenBean build(String bizToken) {
		BusinessTokenBean tokenBean = new BusinessTokenBean();
		String[] ds = BusinessTokenHelper.getBizTokenData(bizToken);
		tokenBean.setSchoolId(Long.parseLong(ds[0]));
		tokenBean.setUserId(Long.parseLong(ds[1]));
		try{
			tokenBean.setUserType(ds[2].substring(0, 1));
			tokenBean.setLogintime(ds[2].substring(1));
			return tokenBean;
		}catch(Exception e){
			
		}
		tokenBean.setLogintime(ds[2]);
		return tokenBean;
	}

	public String getBizToken() {
		return BusinessTokenHelper.getBizTokenData("" + schoolId, "" + userId, logintime);
	}

	/**
	 * 登录用户Id
	 */
	Long userId;
	/**
	 * 企业ID
	 */
	Long schoolId;
	/**
	 * 用户分类,用于区分数据来源<br>
	 * 1:教师,2:家长,3:学生
	 */
	String userType;
	/**
	 * 最后请求时间
	 */
	String logintime = loginTime();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

}
