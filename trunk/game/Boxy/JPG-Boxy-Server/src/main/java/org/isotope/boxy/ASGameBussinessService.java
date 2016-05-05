package org.isotope.boxy;

import org.isotope.jfp.framework.biz.common.ISCheck;
import org.isotope.jfp.framework.biz.common.ISInit;
import org.isotope.jfp.framework.biz.common.ISSave;
import org.isotope.jfp.framework.support.MyWorkSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 游戏超级接口
 * 
 * @author 001745
 *
 */
public abstract class ASGameBussinessService extends MyWorkSupport implements ISGameAction, ISInit, ISCheck, ISSave, Runnable {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void run() {
		try {
			if (logger.isDebugEnabled())
				logger.debug("1.动作处理开始 >>>>>>>>>>");
			// 执行线程处理
			if (doInit()) {
				if (logger.isDebugEnabled())
					logger.debug("  2.动作处理初始化成功 <<<<<=====doInit");
				if (doGameAction()) {
					if (logger.isDebugEnabled())
						logger.debug("    3.动作处理业务逻辑成功 <<<<<=====doGameAction");
					if (doSave()) {
						if (logger.isDebugEnabled())
							logger.debug("      4.动作处理保存成功 <<<<<===== doSave");
						return;
					}
				}
			}
			if (logger.isDebugEnabled())
				logger.debug("1.动作处理取消 xxxxxxxxxx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
