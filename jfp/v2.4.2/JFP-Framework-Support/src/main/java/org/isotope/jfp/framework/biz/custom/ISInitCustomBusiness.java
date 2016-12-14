package org.isotope.jfp.framework.biz.custom;

import org.isotope.jfp.framework.biz.common.ISInit;

/**
 * 业务初始化过程
 * 
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public interface ISInitCustomBusiness extends ISInit {

	/**
	 * 业务初始化前
	 */
	boolean beforeInit() throws Exception;

	/**
	 * 业务初始化中
	 */
	boolean doInit() throws Exception;

	/**
	 * 业务初始化后
	 */
	boolean afterInit() throws Exception;
}
