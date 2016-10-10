package org.isotope.jfp.framework.cache.redis;

import java.util.List;
import java.util.Map;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.support.IJedisSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSON;

/**
 * Redis缓存实现（集群）
 * 
 * @version 2.4.2 2015/12/9
 * @since 20150728
 *
 */
public class MyRedisCluster implements ICacheService, ISFrameworkConstants {

	public MyRedisCluster() {

	}

	public MyRedisCluster(IJedisSupport jedisUtil) {
		this.jedisUtil = jedisUtil;
	}

	public MyRedisCluster(IJedisSupport jedisUtil, int waitTime) {
		this.jedisUtil = jedisUtil;
		this.waitTime = waitTime;
	}

	private int waitTime = 5;

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	IJedisSupport jedisUtil;

	public IJedisSupport getjedisUtil() {
		return jedisUtil;
	}

	public void setjedisUtil(IJedisSupport jedisUtil) {
		this.jedisUtil = jedisUtil;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// String str = "aa//bb//cc//dd";
		//System.out.println(str.substring(0,str.indexOf("//")));
		//System.out.println(str.substring(str.indexOf("//")+2));
		// List<Object> vs = new ArrayList<Object>();
		// Object value = new RESTResultBean();
		// vs.add(new RESTResultBean());
		// vs.add(new RESTResultBean());
		// vs.add(new RESTResultBean());
		// vs.add(new RESTResultBean());
		//System.out.println(getStringToRedis(value));
		//System.out.println(vs.getClass().getName() + BACKSLASH2 +
		// JSONArray.toJSONString(vs));
		//
		//System.out.println((getStringToRedis(value)).split(BACKSLASH2)[0]);
		//System.out.println((getStringToRedis(value)).split(BACKSLASH2)[1]);
		// String[] values = (vs.getClass().getName() + BACKSLASH2 +
		// JSONArray.toJSONString(vs)).split(BACKSLASH2);
		// try {
		// Object a = JSON.parseObject(values[1], Class.forName(values[0]));
		//System.out.println(a);
		// } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	@Override
	public boolean removeKey(String key) {
		return jedisUtil.removeKey(key);
	}
	
	@Override
	public boolean putObject(String key, Object value) {
		jedisUtil.add(key, getStringToRedis(value), waitTime);
		return false;
	}

	public boolean putObject(String key, Object value, int expireTimeWithSecond, boolean translation) {
		jedisUtil.add(key, getStringToRedis(value, translation), expireTimeWithSecond);
		return false;
	}

	/**
	 * 实例化对象
	 * 
	 * @param value
	 * @return
	 */
	public static String getStringToRedis(Object value) {
		return getStringToRedis(value, true);
	}

	public static String getStringToRedis(Object value, boolean translation) {
		if (EmptyHelper.isEmpty(value))
			return null;
		if (value instanceof String)
			return String.valueOf(value);
		if (translation)
			return value.getClass().getName() + BACKSLASH2 + JSON.toJSON(value);
		else
			return String.valueOf(value);
	}

	public static final String CLASS_NAME = "className";

	/**
	 * 实例化对象
	 * 
	 * @param value
	 * @return
	 */
	public static Object getClassFromRedis(String value) {
		return getClassFromRedis(value, true);
	}

	public static Object getClassFromRedis(String value, boolean translation) {
		if (EmptyHelper.isEmpty(value))
			return null;
		if (translation) {
			if (value.indexOf(BACKSLASH2) < 0)
				return (String) value;
			String[] values = new String[2];
			values[0] = value.substring(0, value.indexOf("//"));
			values[1] = value.substring(value.indexOf("//") + 2);
			try {
				return JSON.parseObject(values[1], Class.forName(values[0]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return values[1];
		} else {
			return value;
		}
	}

	@Override
	public Object getObject(String key) {
		return getClassFromRedis(jedisUtil.get(key));
	}

	@Override
	public Object getObject(String key, boolean translation) {
		return getClassFromRedis(jedisUtil.get(key), translation);
	}

	@Override
	public Object deleteObject(String key) {
		return getClassFromRedis(jedisUtil.del(key));
	}

	@Override
	public Object deleteObject(String key, boolean translation) {
		return getClassFromRedis(jedisUtil.del(key), translation);
	}

	@Override
	public Map<String, String> getAllObjectInMap(String key) {
		return jedisUtil.hgetall(key);
	}

	/**
	 * 追加数据到已有的缓存数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public boolean addObjectInMap(String rkey, String mkey, Object value) {
		return jedisUtil.hset(rkey, mkey, getStringToRedis(value));
	}

	@Override
	public boolean addObjectInMap(String rkey, String mkey, Object value, boolean translation) {
		return jedisUtil.hset(rkey, mkey, getStringToRedis(value, translation));
	}

	/**
	 * 从已有的缓存数据里面删除一个数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public Object removeObjectInMap(String rkey, String mkey) {
		String value = jedisUtil.hdel(rkey, mkey);
		return getClassFromRedis(value);
	}

	@Override
	public Object removeObjectInMap(String rkey, String mkey, boolean translation) {
		String value = jedisUtil.hdel(rkey, mkey);
		return getClassFromRedis(value, translation);
	}

	/**
	 * 从已有的缓存数据里面获取一个数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public Object findObjectInMap(String rkey, String mkey) {
		String value = jedisUtil.hget(rkey, mkey);
		return getClassFromRedis(value);
	}

	@Override
	public Object findObjectInMap(String rkey, String mkey, boolean translation) {
		String value = jedisUtil.hget(rkey, mkey);
		return getClassFromRedis(value, translation);
	}

	@Override
	public long sizeOfMap(String key) {
		return jedisUtil.hlen(key);
	}

	@Override
	public long sizeOfList(String key) {
		return jedisUtil.llen(key);
	}

	@Override
	public boolean offerObjectInList(String key, Object value) {
		jedisUtil.listAdd(key, getStringToRedis(value));
		return true;
	}

	@Override
	public boolean offerObjectInList(String key, Object value, boolean translation) {
		jedisUtil.listAdd(key, getStringToRedis(value, translation));
		return true;
	}

	/**
	 * 从已有的缓存数据里面获取并移除第一个数据(阻塞模式) <br>
	 * （基于保存Key,List数据队列）
	 * 
	 * @return
	 */
	@Override
	public Object peekFirstObjectInList(String key) {
		return jedisUtil.blistPop(key, waitTime);
	}

	/**
	 * 从已有的缓存数据里面获取并移除第一个数据(阻塞模式) <br>
	 * （基于保存Key,List数据队列）
	 * 
	 * @return
	 */
	@Override
	public Object peekFirstObjectInList(String key, boolean translation) {
		String value = jedisUtil.blistPop(key, waitTime);
		return getClassFromRedis(value, translation);
	}

	@Override
	public Object peekFirstObjectInList(String key, int waitTime, boolean translation) {
		String value = jedisUtil.blistPop(key, waitTime);
		return getClassFromRedis(value, translation);
	}

	@Override
	public Object pollFirstObjectInList(String key) {
		String value = jedisUtil.listPop(key);
		return getClassFromRedis(value);
	}

	@Override
	public Object pollFirstObjectInList(String key, boolean translation) {
		String value = jedisUtil.listPop(key);
		return getClassFromRedis(value, translation);
	}

	@Override
	public Object peekLastObjectInList(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object peekLastObjectInList(String key, boolean translation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object pollLastObjectInList(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object pollLastObjectInList(String key, boolean translation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void selectDB(int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void expire(String key, int timeOut) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean addMapAll(String key, Map<String, Object> value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerListAll(String key, List<Object> value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object indexObjectInList(String key, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long setnx(String key, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

}
