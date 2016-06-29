package org.isotope.jfp.framework.biz;

/**
 * 定时作业任务处理
 * 
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public interface ISTask {
	
	/**
	 * 业务处理(重复运行)
	 */
	boolean doProcessRepeat() throws Exception;
	
	/**
	 * 业务处理(运行一次)
	 */
	boolean doProcessOnce(Object param) throws Exception;
}
