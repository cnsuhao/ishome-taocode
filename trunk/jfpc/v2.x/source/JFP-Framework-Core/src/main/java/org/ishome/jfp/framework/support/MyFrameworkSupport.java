package org.ishome.jfp.framework.support;

import org.ishome.jfp.framework.beands.LoginerBean;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.constants.ISModelConstants;
import org.ishome.jfp.framework.support.ISSessionSupport;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.ishome.jfp.framework.utils.RandomHelper;
import org.ishome.jfp.framework.utils.token.UserTokenHelper;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;


/**
 * 业务框架超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.1 2014/8/27 增加自定义提示信息页面{getMessageMAV}
 * @version 0.1.0 2014/2/8
 */
public class MyFrameworkSupport implements ISSessionSupport, ISFrameworkConstants, ISModelConstants {

	// //////////////////////////////////////////////////////////////
	public void setSessionAttribute(String key, Object value) {
		RequestContextHolder.getRequestAttributes().setAttribute(key, value, RequestAttributes.SCOPE_SESSION);
		// CatchUtils.setAttribute(key, value);
	}

	protected Object getSessionAttribute(String key) {
		return RequestContextHolder.getRequestAttributes().getAttribute(key, RequestAttributes.SCOPE_SESSION);
		// 基于缓存服务器
		// return CatchUtils.getAttribute(key);
	}

	protected void removeSessionAttribute(String key) {
		RequestContextHolder.getRequestAttributes().removeAttribute(key, RequestAttributes.SCOPE_SESSION);
		// CatchUtils.removeAttribute(key);
	}

	// //////////////////////////////////////////////////////////////

	/**
	 * 校验图片验证码
	 * 
	 * @param verCode
	 * @return
	 */
	public boolean checkRandomCode(String verCode) {
		if(EmptyHelper.isEmpty(verCode))
			return false;

		boolean rs = verCode.toUpperCase().equals((String) getSessionAttribute(RANDOM_CODE));
		if(rs){
			//清除验证码缓存
			//removeAttribute(RANDOM_CODE);
		}
		return rs;
	}

	/**
	 * 图片验证码
	 * 
	 * @param verCode
	 * @return
	 */
	public String sendRandomCode(int length) {
		String code = RandomHelper.nextCode(length);
		System.out.println("RANDOM_CODE======>>>>>>>>>>>>>>>>>>>>>>>>>>" + code);
		setSessionAttribute(RANDOM_CODE, code);
		return code;
	}

	/**
	 * 校验短信验证码
	 * 
	 * @param phone
	 * @param bizId
	 * @param code
	 * @return 核对手机验证码0正确1失败2过期
	 */
	// TODO
	public int validateSMSCode(String phone, String bizId, String code) {
		if (getSessionAttribute(phone + bizId) == null) {
			return 2;
		}
		if (!code.equals(getSessionAttribute(phone + bizId))) {
			return 1;
		}
		return 0;
	}

	// ////////////////处理线程安全/////////////////////////
	/**
	 * Session ID获得
	 * 
	 * @return
	 */
	public String getSessionid() {
		return (String) getSessionAttribute(CONSTANT_SESSION_ID);
	}

	/**
	 * Session ID持久化
	 * 
	 * @param sessionid
	 */
	public void setSessionid(String sessionid) {
		setSessionAttribute(CONSTANT_SESSION_ID, sessionid);
	}

	/**
	 * 获得登录者信息
	 * 
	 * @return
	 */
	public LoginerBean getLoginerBean() {
		LoginerBean loginerBean = (LoginerBean) getSessionAttribute(CONSTANT_LOGINER);
		if (loginerBean == null)
			loginerBean = new LoginerBean();
		return loginerBean;
	}

	/**
	 * 缓存登录者信息
	 */
	public void setLoginerBean(LoginerBean loginer) {
		// Session保存
		setSessionAttribute(CONSTANT_LOGINER, loginer);
	}

	public String getLoginerId() {
		return UserTokenHelper.getUserID(getToken());
	}

	public String getCompanyId() {
		return UserTokenHelper.getCompanyId(getToken());
	}

	public String getToken() {
		return getLoginerBean().getToken();
	}

	public void setToken(String token) {
		// Session保存
		setSessionAttribute(CONSTANT_USER_TOKEN, token);
	}
}
