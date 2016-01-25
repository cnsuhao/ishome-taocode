package org.isotope.jfp.framework.biz.common;

/**
 * 定时作业任务处理
 * 
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public interface ISProcess {

	/**
	 * 业务 数据准备
	 */
	boolean doProcess() throws Exception;
}
