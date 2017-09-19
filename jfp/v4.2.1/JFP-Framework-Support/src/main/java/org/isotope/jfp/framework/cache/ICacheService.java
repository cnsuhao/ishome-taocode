package org.isotope.jfp.framework.cache;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 缓存队列
 * 
 * @author Spook
 * @version 3.1.1 2016/3/17 增加数据库切换和Key的时间设定
 * @version 2.4.1 2015/11/9 增加清空动作
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 */
public interface ICacheService extends ISessionService {

	////////// 数据缓存////////////////
	/**
	 * 初始化
	 */
	public void init();
	
	/**
	 * 返回满足给定pattern的所有key
	 * @param pattern
	 */
	public Collection<String> keys(String pattern);
	/**
	 * 如果不存在名称为key的string，则向库中添加string，名称为key，值为value
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public long setnx(String key, Object value);
	
	/**
	 * 删除一个Key
	 * @param key
	 * @return
	 */
	public boolean removeKey(String key);
	
	/**
	 * 将key由oldKey重命名为newKey，若oldKey存在则删除newKey表示的key
	 * @param oldKey
	 * @param newKey
	 * @return
	 */
	public boolean renameKey(String oldKey,String newKey);

	/**
	 * 对已存在的key设置超时时间
	 * 
	 * @param key
	 * @param timeOut
	 */
	public void expire(String key, int timeOut);

	/**
	 * 选择使用DB
	 * 
	 * @param index
	 */
	public void selectDB(int index);

	/**
	 * 保存数据到缓存中心 <br>
	 * （基于保存Key）
	 * 
	 * @return
	 */
	public boolean putObject(String key, Object value);

	/**
	 * 保存数据到缓存中心 <br>
	 * （基于保存Key）
	 * 
	 * @return
	 */
	public boolean putObject(String key, Object value, int expireTimeWithSecond, boolean translation);

	/**
	 * 从缓存中心获取数据 <br>
	 * （基于保存Key）
	 * 
	 * @return
	 */
	public Object getObject(String key);

	/**
	 * 从缓存中心获取数据 <br>
	 * （基于保存Key）
	 * 
	 * @return
	 */
	public Object getObject(String key, boolean translation);

	/**
	 * 删除缓存中心里面的一个数据 <br>
	 * （基于保存Key）
	 * 
	 * @return
	 */
	public Object deleteObject(String key);

	/**
	 * 删除缓存中心里面的一个数据 <br>
	 * （基于保存Key）
	 * 
	 * @return
	 */
	public Object deleteObject(String key, boolean translation);

	///////////////// 消息队列///////////////////
	/**
	 * 从缓存中心获取Map里面的全部数据 <br>
	 * （基于保存Key）
	 * 
	 * @return
	 */
	public Object getAllObjectInMap(String key);

	/**
	 * 返回名称为key的map的长度 <br>
	 * （基于保存Key,List数据队列） <br>
	 * 先进先出
	 * 
	 * @return
	 */
	public long sizeOfMap(String key);

	/**
	 * 将指定Map内元素插入缓存数据 <br>
	 * （基于保存Key,Map数据队列） <br>
	 * 先进先出
	 * 
	 * @return
	 */
	public boolean addMapAll(String key, Map<String, Object> value);

	/**
	 * 追加数据到已有的缓存数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public boolean addObjectInMap(String rkey, String mkey, Object value);

	/**
	 * 追加数据到已有的缓存数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public boolean addObjectInMap(String rkey, String mkey, Object value, boolean translation);

	/**
	 * 从已有的缓存数据队列里面删除一个数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public Object removeObjectInMap(String rkey, String mkey);

	/**
	 * 从已有的缓存数据队列里面删除一个数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public Object removeObjectInMap(String rkey, String mkey, boolean translation);

	/**
	 * 从已有的缓存数据里面获取一个数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public Object findObjectInMap(String rkey, String mkey);

	/**
	 * 从已有的缓存数据里面获取一个数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public Object findObjectInMap(String rkey, String mkey, boolean translation);

	/////////////// 消息队列/////////////////
	/**
	 * 返回名称为key的list的长度 <br>
	 * （基于保存Key,List数据队列） <br>
	 * 先进先出
	 * 
	 * @return
	 */
	public long sizeOfList(String key);

	/**
	 * 将指定List内元素插入此双端队列的末尾 <br>
	 * （基于保存Key,List数据队列） <br>
	 * 先进先出
	 * 
	 * @return
	 */
	public boolean addListAll(String key, List<Object> value);

	/**
	 * 将指定元素插入此双端队列的末尾 <br>
	 * （基于保存Key,List数据队列） <br>
	 * 先进先出
	 * 
	 * @return
	 */
	public boolean addObjectInList(String key, Object value);

	/**
	 * 将指定元素插入此双端队列的末尾 <br>
	 * （基于保存Key,List数据队列） <br>
	 * 先进先出
	 * 
	 * @return
	 */
	public boolean addObjectInList(String key, Object value, boolean translation);

	/**
	 * 返回名称为key的list中index位置的元素 <br>
	 * （基于保存Key,List数据队列） <br>
	 * 先进先出
	 * 
	 * @return
	 */
	public Object indexObjectInList(String key, int index);

	/**
	 * 从已有的缓存数据里面获取并移除第一个数据(阻塞模式) <br>
	 * （基于保存Key,List数据队列）
	 * 
	 * @return
	 */
	public Object peekFirstObjectInList(String key);

	/**
	 * 从已有的缓存数据里面获取并移除第一个数据(阻塞模式) <br>
	 * （基于保存Key,List数据队列）
	 * 
	 * @return
	 */
	public Object peekFirstObjectInList(String key, boolean translation);

	/**
	 * 从已有的缓存数据里面获取并移除第一个数据(阻塞模式) <br>
	 * （基于保存Key,List数据队列）
	 * 
	 * @return
	 */
	public Object peekFirstObjectInList(String key, int waitTime, boolean translation);

	/**
	 * 从已有的缓存数据里面获取并移除第一个数据 <br>
	 * （基于保存Key,List数据队列）
	 * 
	 * @return
	 */
	public Object pollFirstObjectInList(String key);

	/**
	 * 从已有的缓存数据里面获取并移除第一个数据 <br>
	 * （基于保存Key,List数据队列）
	 * 
	 * @return
	 */
	public Object pollFirstObjectInList(String key, boolean translation);

	/**
	 * 从已有的缓存数据里面获取并移除最后一个数据(阻塞模式) <br>
	 * （基于保存Key,List数据队列）
	 * 
	 * @return
	 */
	public Object peekLastObjectInList(String key);

	/**
	 * 从已有的缓存数据里面获取并移除最后一个数据 <br>
	 * （基于保存Key,List数据队列）
	 * 
	 * @return
	 */
	public Object peekLastObjectInList(String key, boolean translation);

	/**
	 * 从已有的缓存数据里面获取并移除最后一个数据 <br>
	 * （基于保存Key,Deque数据队列）
	 * 
	 * @return
	 */
	public Object pollLastObjectInList(String key);

	/**
	 * 从已有的缓存数据里面获取并移除最后一个数据 <br>
	 * （基于保存Key,Deque数据队列）
	 * 
	 * @return
	 */
	public Object pollLastObjectInList(String key, boolean translation);
	////////////////////////////////////

}
