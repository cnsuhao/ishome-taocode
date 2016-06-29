package org.isotope.jfp.framework.biz.custom;

import org.isotope.jfp.framework.biz.common.ISCheck;

/**
 * 业务检查过程
 * 
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public interface ICheckCustomBusiness extends ISCheck {

	/**
	 * 业务检查前
	 */
	boolean beforeCheck() throws Exception;
	/**
	 * 业务检查中
	 */
	boolean doCheck() throws Exception;
	/**
	 * 业务检查后
	 */
	boolean afterCheck() throws Exception;
}
