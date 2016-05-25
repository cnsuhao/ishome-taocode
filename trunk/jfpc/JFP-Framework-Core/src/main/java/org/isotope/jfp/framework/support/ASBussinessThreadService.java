package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.biz.common.ISCheck;
import org.isotope.jfp.framework.biz.common.ISInit;
import org.isotope.jfp.framework.biz.common.ISProcess;
import org.isotope.jfp.framework.biz.common.ISSave;
import org.isotope.jfp.framework.utils.EmptyHelper;

public abstract class ASBussinessThreadService extends MyBusinessSupport implements ISProcess, ISInit, ISCheck, ISSave, Runnable {
	
	@Override
	public void run() {
		try {
			if (logger.isDebugEnabled())
				logger.debug("1.动作处理开始 >>>>>>>>>>");
			// 执行线程处理
			if (doInit()) {
				if (logger.isDebugEnabled())
					logger.debug("  2.动作处理初始化成功 <<<<<=====doInit");
				if (doCheck()) {
					if (logger.isDebugEnabled())
						logger.debug("    3.动作处理初始化成功 <<<<<=====doCheck");
					if (doProcess()) {
						if (logger.isDebugEnabled())
							logger.debug("      4.动作处理业务逻辑成功 <<<<<=====doProcess");
						if (doSave()) {
							if (logger.isDebugEnabled())
								logger.debug("        5.动作处理保存成功 <<<<<===== doSave");
							return;
						}
					}
				}
			}
			if (logger.isDebugEnabled())
				logger.debug("1.动作处理取消 xxxxxxxxxx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean doSave() throws Exception {
		//变更Token
		super.chageToken();
		//保存Token
		return super.saveToken();
	}

	@Override
	public boolean doCheck() throws Exception {
		if (logger.isDebugEnabled())
			logger.debug("    doCheck.getToken()=====>>>>>"+getToken());
		if (EmptyHelper.isEmpty(getToken()))
			return false;
		if (EmptyHelper.isEmpty(getMyCacheService()))
			return false;
		if (logger.isDebugEnabled())
			logger.debug("    doCheck.getParamValue()=====>>>>>"+getParamValue());
		if (EmptyHelper.isEmpty(getParamValue()))
			return false;
		
		return super.checkToken();
	}
}
