package org.ishome.jfp.framework.biz;

/**
 * 定时作业任务处理
 * 
 * @author Spook
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public interface ISCheck {

	/**
	 * 业务处理
	 */
	boolean doCheck() throws Exception;
}
