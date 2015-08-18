package org.ishome.jfp.framework.biz.common;

/**
 * 定时作业任务处理
 * 
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public interface ISInit {

	/**
	 * 业务处理
	 */
	boolean doInit() throws Exception;
}
