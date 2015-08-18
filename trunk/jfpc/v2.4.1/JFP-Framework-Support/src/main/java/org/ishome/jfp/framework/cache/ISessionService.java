package org.ishome.jfp.framework.cache;

/**
 * 缓存队列
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 */
public interface ISessionService {
	////////////////////数据缓存////////////////////////////////
	/**
	 * 保存数据到缓存中心
	 * <br>（基于保存Key）
	 * @return
	 */
	public boolean putObject(String key,Object value);
	/**
	 * 从缓存中心获取数据
	 * <br>（基于保存Key）
	 * @return
	 */
	public Object getObject(String key);
	/**
	 * 删除缓存中心里面的一个数据
	 * <br>（基于保存Key）
	 * @return
	 */
	public Object deleteObject(String key);

}
