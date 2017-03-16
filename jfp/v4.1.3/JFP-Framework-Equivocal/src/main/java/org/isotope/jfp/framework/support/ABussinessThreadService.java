package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.biz.common.ISCheck;
import org.isotope.jfp.framework.biz.common.ISInit;
import org.isotope.jfp.framework.biz.common.ISProcess;
import org.isotope.jfp.framework.biz.common.ISSave;
import org.isotope.jfp.framework.biz.common.ISToken;
import org.isotope.jfp.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSON;

/**
 * 异步线程处理父类
 * 
 * @author 001745
 *
 */
public abstract class ABussinessThreadService extends MyBusinessSupport implements ISToken, ISProcess, ISInit, ISCheck, ISSave, Runnable {
	/**
	 * 业务请求版本号
	 */
	protected String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * 用户请求参数
	 */
	protected String paramValue;

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void run() {
		try {
			if (logger.isDebugEnabled())
				logger.debug("动作处理开始 >>>>>>>>>> run");
			// 执行线程处理
			if (doCheck()) {
				if (logger.isDebugEnabled())
					logger.debug("  2.动作条件校验成功 <<<<<=====doInit");
				if (doInit()) {
					if (logger.isDebugEnabled())
						logger.debug("    3.动作处理初始化成功 <<<<<=====doCheck");
					if (doProcess()) {
						if (logger.isDebugEnabled())
							logger.debug("      4.动作处理业务逻辑成功 <<<<<=====doProcess");
						if (doSave()) {
							if (logger.isDebugEnabled())
								logger.debug("动作处理保存成功 <<<<<<<<<< doSave");
							return;
						}
					}
				}
			}
			if (logger.isDebugEnabled())
				logger.debug("动作处理取消 xxxxxxxxxx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 业务逻辑处理
	 */
	public boolean doInit() throws Exception {
		// 参数解密
		if (mySecuritySupport != null && ISecuritySupport.ENCRYPTION.equals(tokenBean.getEncryType())) {
			setParamValue(mySecuritySupport.decryption(getToken(), getParamValue()));
		}

		return true;
	}

	public boolean doSave() throws Exception {
		// 变更Token
		chageToken();
		// 保存Token
		return saveToken();
	}

	@Override
	public boolean doCheck() throws Exception {
		if (logger.isDebugEnabled())
			logger.debug(" doCheck.token=====>>>>>" + tokenBean);
		if (EmptyHelper.isEmpty(tokenBean))
			return false;
		if (EmptyHelper.isEmpty(getMyCacheService()))
			return false;
		if (logger.isDebugEnabled())
			logger.debug(" doCheck.getParamValue()=====>>>>>" + getParamValue());
		if (EmptyHelper.isEmpty(getParamValue()))
			return false;

		return checkToken();
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public boolean checkToken() throws Exception {
		try {
			//获得历史Token
			String tokenCatch = (String) myCacheService.getObject(tokenBean.getUserId(), false);
			if (logger.isDebugEnabled())
				logger.debug(" doCheck.checkToken()=====tokenCatch>>>>>" + tokenCatch);
			//是否需要支持可变Token
			if (EmptyHelper.isNotEmpty(tokenCatch)){
				chageToken();
				return true;
			}
		} catch (Exception r) {

		}
		return false;
	}
	/**
	 * @see waitTimeSecond
	 */
	@Override
	public boolean saveToken() throws Exception {
		return myCacheService.putObject(tokenBean.getUserId(), JSON.toJSONString(tokenBean), waitTimeSecond, false);
	}

	/**
	 * 更新Token令牌内容
	 */
	public boolean chageToken() throws Exception {
		
		
		return saveToken();
	}

	/**
	 * 返回Token令牌内容
	 */
	public String getToken() throws Exception {
		return tokenBean.getToken();
	}

}
