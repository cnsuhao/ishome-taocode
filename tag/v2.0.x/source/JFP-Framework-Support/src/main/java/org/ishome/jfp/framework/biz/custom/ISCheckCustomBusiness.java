package org.ishome.jfp.framework.biz.custom;

/**
 * 业务检查过程
 * 
 * @author Spook
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public interface ISCheckCustomBusiness {

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
