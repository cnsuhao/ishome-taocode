package org.isotope.jfp.framework.common;

import org.isotope.jfp.framework.biz.common.ISCheck;
import org.isotope.jfp.framework.biz.common.ISInit;
import org.isotope.jfp.framework.biz.common.ISProcess;
import org.isotope.jfp.framework.biz.common.ISSave;
import org.isotope.jfp.framework.support.MyBusinessSupport;

/**
 * 异步线程处理父类
 * 
 * @author Spook
 * @version 4.1.3 2017/04/15
 * @version 4.1.1 2016/12/12
 * @version 3.2.1 2016/08/28
 * @since 3.2.1 2016/08/28
 *
 */
public abstract class ABussinessThreadService<T> extends MyBusinessSupport<T> implements ISProcess, ISInit, ISCheck, ISSave, Runnable {
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

//	/**
//	 * 业务逻辑处理
//	 */
//	public boolean doInit() throws Exception {
//		// 参数解密
//		if (mySecuritySupport != null && ISecuritySupport.ENCRYPTION.equals(tokenBean.getEncryType())) {
//			setParamValue(mySecuritySupport.decryption(tokenBean.getToken(), getParamValue()));
//		}
//
//		return true;
//	}
//
//	public boolean doSave() throws Exception {
//		// 变更Token
//		tokenBean.chageToken();
//		// 保存Token
//		return tokenBean.saveToken();
//	}
//
//	@Override
//	public boolean doCheck() throws Exception {
//		if (logger.isDebugEnabled())
//			logger.debug(" doCheck.token=====>>>>>" + tokenBean);
//		if (EmptyHelper.isEmpty(tokenBean))
//			return false;
//		if (EmptyHelper.isEmpty(getMyCacheService()))
//			return false;
//		if (logger.isDebugEnabled())
//			logger.debug(" doCheck.getParamValue()=====>>>>>" + getParamValue());
//		if (EmptyHelper.isEmpty(getParamValue()))
//			return false;
//
//		return tokenBean.checkToken();
//	}

}
