package org.ishome.jfp.framework.biz.custom;

import org.ishome.jfp.framework.biz.common.ISProcess;

/**
 * 业务处理过程
 * 
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public interface ISProcessCustomBusiness extends ISProcess {

	/**
	 * 业务处理前
	 */
	boolean beforeProcess() throws Exception;

	/**
	 * 业务处理中
	 */
	boolean doProcess() throws Exception;

	/**
	 * 业务处理后
	 */
	boolean afterProcess() throws Exception;
}
