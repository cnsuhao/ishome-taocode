package org.ishome.jfp.framework.cache;

/**
 * 缓存队列
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 */
public interface ICatchService extends ISessionService {
	////////////////////数据缓存////////////////////////////////
	/**
	 * 保存数据到缓存中心
	 * <br>（基于保存Key）
	 * @return
	 */
	public boolean putObject(String key,Object value);
	/**
	 * 保存数据到缓存中心
	 * <br>（基于保存Key）
	 * @return
	 */
	public boolean putObject(String key,Object value, int expireTimeWithSecond, boolean translation);
	/**
	 * 从缓存中心获取数据
	 * <br>（基于保存Key）
	 * @return
	 */
	public Object getObject(String key);
	/**
	 * 从缓存中心获取数据
	 * <br>（基于保存Key）
	 * @return
	 */
	public Object getObject(String key, boolean translation);	
	/**
	 * 删除缓存中心里面的一个数据
	 * <br>（基于保存Key）
	 * @return
	 */
	public Object deleteObject(String key);
	/**
	 * 删除缓存中心里面的一个数据
	 * <br>（基于保存Key）
	 * @return
	 */
	public Object deleteObject(String key, boolean translation);	
	
	/////////////////////////////////消息队列/////////////////////////////////////
	/**
	 * 从缓存中心获取Map里面的全部数据
	 * <br>（基于保存Key）
	 * @return
	 */
	public Object getAllObjectInMap(String key);
	/**
	 * 返回名称为key的map的长度
	 * <br>（基于保存Key,List数据队列）
	 * <br>先进先出
	 * @return
	 */
	public long sizeOfMap(String key);
	/**
	 * 追加数据到已有的缓存数据
	 * <br>（基于保存Key,Map数据队列）
	 * @return
	 */
	public boolean addObjectInMap(String rkey,String mkey, Object value);
	/**
	 * 追加数据到已有的缓存数据
	 * <br>（基于保存Key,Map数据队列）
	 * @return
	 */
	public boolean addObjectInMap(String rkey,String mkey, Object value, boolean translation);	
	/**
	 * 从已有的缓存数据队列里面删除一个数据
	 * <br>（基于保存Key,Map数据队列）
	 * @return
	 */
	public Object removeObjectInMap(String rkey,String mkey);
	/**
	 * 从已有的缓存数据队列里面删除一个数据
	 * <br>（基于保存Key,Map数据队列）
	 * @return
	 */
	public Object removeObjectInMap(String rkey,String mkey, boolean translation);
	
	/**
	 * 从已有的缓存数据里面获取一个数据
	 * <br>（基于保存Key,Map数据队列）
	 * @return
	 */
	public Object findObjectInMap(String rkey,String mkey);
	/**
	 * 从已有的缓存数据里面获取一个数据
	 * <br>（基于保存Key,Map数据队列）
	 * @return
	 */
	public Object findObjectInMap(String rkey,String mkey, boolean translation);

	/////////////////////////////消息队列///////////////////////////////
	/**
	 * 返回名称为key的list的长度
	 * <br>（基于保存Key,List数据队列）
	 * <br>先进先出
	 * @return
	 */
	public long sizeOfList(String key);
	/**
	 * 将指定元素插入此双端队列的末尾
	 * <br>（基于保存Key,List数据队列）
	 * <br>先进先出
	 * @return
	 */
	public boolean offerObjectInList(String key, Object value);
	/**
	 * 将指定元素插入此双端队列的末尾
	 * <br>（基于保存Key,List数据队列）
	 * <br>先进先出
	 * @return
	 */
	public boolean offerObjectInList(String key, Object value, boolean translation);
	/**
	 * 从已有的缓存数据里面获取第一个数据
	 * <br>（基于保存Key,List数据队列）
	 * @return
	 */
	public Object peekFirstObjectInList(String key);
	/**
	 * 从已有的缓存数据里面获取第一个数据
	 * <br>（基于保存Key,List数据队列）
	 * @return
	 */
	public Object peekFirstObjectInList(String key, boolean translation);
	/**
	 * 从已有的缓存数据里面获取并移除第一个数据
	 * <br>（基于保存Key,List数据队列）
	 * @return
	 */
	public Object pollFirstObjectInList(String key);
	/**
	 * 从已有的缓存数据里面获取并移除第一个数据
	 * <br>（基于保存Key,List数据队列）
	 * @return
	 */
	public Object pollFirstObjectInList(String key, boolean translation);
	/**
	 * 从已有的缓存数据里面获取最后一个数据
	 * <br>（基于保存Key,List数据队列）
	 * @return
	 */
	public Object peekLastObjectInList(String key);
	/**
	 * 从已有的缓存数据里面获取最后一个数据
	 * <br>（基于保存Key,List数据队列）
	 * @return
	 */
	public Object peekLastObjectInList(String key, boolean translation);
	/**
	 * 从已有的缓存数据里面获取并移除最后一个数据
	 * <br>（基于保存Key,Deque数据队列）
	 * @return
	 */
	public Object pollLastObjectInList(String key);
	/**
	 * 从已有的缓存数据里面获取并移除最后一个数据
	 * <br>（基于保存Key,Deque数据队列）
	 * @return
	 */
	public Object pollLastObjectInList(String key, boolean translation);
	//////////////////////////////////////////////////////////////////////
}
