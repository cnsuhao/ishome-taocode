package org.isotope.jfp.mcws.multisource;

/**
 * 多源检索
 * @author 001745
 *
 */
public interface ISMultiSource {

	/**
	 * 添加一个关键字到任务队列
	 * @return
	 */
	boolean addKeywordInJobList(String keyword);
	
	/**
	 * 获得一个任务的数据结果
	 * @return
	 */
	boolean getKeywordOnJob();
	
}
