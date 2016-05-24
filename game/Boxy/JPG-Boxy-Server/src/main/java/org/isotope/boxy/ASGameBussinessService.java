package org.isotope.boxy;

import org.isotope.jfp.framework.support.ASBussinessThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 游戏超级接口
 * 
 * @author 001745
 *
 */
public abstract class ASGameBussinessService extends ASBussinessThreadService implements ISGameAction {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 线程启动入口
	 */
	@Override
	public final void run() {
		doInit();
	}

	/**
	 * 内部启动入口
	 */
	@Override
	public final boolean doProcess() throws Exception {
		return doInit();
	}

	/**
	 * 业务逻辑处理
	 */
	public final boolean doInit() {
		try {

			if (logger.isDebugEnabled())
				logger.debug("1.动作处理开始 >>>>>>>>>>");
			// 执行线程处理
			if (doCheck()) {
				if (logger.isDebugEnabled())
					logger.debug("  2.动作条件检查成功 <<<<<=====doCheck");
				if (doGameAction()) {
					if (logger.isDebugEnabled())
						logger.debug("    3.动作处理业务逻辑成功 <<<<<=====doGameAction");
					if (doSave()) {
						if (logger.isDebugEnabled())
							logger.debug("      4.动作处理保存成功 <<<<<===== doSave");
						return true;
					}
				}
			}
			if (logger.isDebugEnabled())
				logger.debug("1.动作处理取消 xxxxxxxxxx");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
