package org.isotope.boxy;

import org.isotope.boxy.common.entity.CommonEntityDBO;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.sync.ABussinessThreadService;

import com.alibaba.fastjson.JSONObject;

/**
 * 游戏超级接口
 * 
 * @author 001745
 *
 */
public abstract class AGameBussinessService extends ABussinessThreadService implements IGameAction {

	/**
	 * 线程启动入口
	 */
	@Override
	public final void run() {
		try {
			doProcess();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 内部启动入口
	 */
	@Override
	public final boolean doProcess() throws Exception {
		try {
			logger.debug("动作处理开始 >>>>>>>>>>");
			// 执行线程处理
			if (doCheck()) {
				if (logger.isDebugEnabled())
					logger.debug("1.动作条件检查成功  <<<<<=====doInit");
				if (doInit()) {
					if (logger.isDebugEnabled())
						logger.debug("  2.动作初始化成功 <<<<<=====doCheck");
					if (doGameAction()) {
						if (logger.isDebugEnabled())
							logger.debug("    3.动作业务逻辑处理成功 <<<<<=====doGameAction");
						if (doSave()) {
							if (logger.isDebugEnabled())
								logger.debug("动作结果保存成功 <<<<<===== doSave");
							return true;
						}
					}
				}
			}
			if (logger.isDebugEnabled())
				logger.debug("动作处理取消 xxxxxxxxxx");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	protected CommonEntityDBO paramBean;
	public boolean doInit() throws Exception {
		boolean init = super.doInit();
		if(init== true)
			paramBean = JSONObject.parseObject(getParamValue(), CommonEntityDBO.class);
		return init;
	}

	public boolean doSave() throws Exception {
		// 开启加密
		//token.setEncryType(ISecuritySupport.ENCRYPTION);
		// 保存Token
		//return super.doSave();
		return false;
	}
}
