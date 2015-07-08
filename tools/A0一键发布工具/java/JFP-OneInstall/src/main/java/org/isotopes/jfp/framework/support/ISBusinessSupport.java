package org.isotopes.jfp.framework.support;

import org.isotopes.jfp.framework.ISFrameworkConstants;

/**
 * 业务规则超级接口(系统超级接口)
 * @since 0.1
 * @version 0.1 2012-7-13
 * @see <ISFrameworkConstants>
 */
public interface ISBusinessSupport extends ISFrameworkConstants{
	/**
	 * 检查用户登录帐号
	 * @return
	 */
	public boolean doCheckLogin();
	/**
	 * 检查用户Token有效期
	 * @return
	 */
	public boolean doCheckToken();
	
	/**
	 * 检查用户登录机器
	 * @return
	 */
	public boolean doCheckComputer();

}
