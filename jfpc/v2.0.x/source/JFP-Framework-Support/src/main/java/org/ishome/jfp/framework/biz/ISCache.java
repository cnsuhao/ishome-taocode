package org.ishome.jfp.framework.biz;

/**
 * 缓存数据到缓存中心
 * 
 * @author Spook
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public interface ISCache {

	/**
	 * 数据保存
	 */
	boolean doCache() throws Exception;
}
