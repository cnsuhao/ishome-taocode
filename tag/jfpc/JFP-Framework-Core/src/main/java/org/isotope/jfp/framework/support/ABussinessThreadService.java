package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.biz.common.ISCheck;
import org.isotope.jfp.framework.biz.common.ISInit;
import org.isotope.jfp.framework.biz.common.ISProcess;
import org.isotope.jfp.framework.biz.common.ISSave;
import org.isotope.jfp.framework.utils.EmptyHelper;

public abstract class ABussinessThreadService extends MyBusinessSupport implements ISProcess, ISInit, ISCheck, ISSave, Runnable {

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
		if (mySecuritySupport != null && ISecuritySupport.ENCRYPTION.equals(token.getEncryType())) {
			setParamValue(mySecuritySupport.decryption(getToken(), getParamValue()));
		}

		return true;
	}

	public boolean doSave() throws Exception {
		// 变更Token
		super.chageToken();
		// 保存Token
		return super.saveToken();
	}

	@Override
	public boolean doCheck() throws Exception {
		if (logger.isDebugEnabled())
			logger.debug("    doCheck.token=====>>>>>" + token);
		if (EmptyHelper.isEmpty(token))
			return false;
		if (EmptyHelper.isEmpty(getMyCacheService()))
			return false;
		if (logger.isDebugEnabled())
			logger.debug("    doCheck.getParamValue()=====>>>>>" + getParamValue());
		if (EmptyHelper.isEmpty(getParamValue()))
			return false;

		return super.checkToken();
	}
}
