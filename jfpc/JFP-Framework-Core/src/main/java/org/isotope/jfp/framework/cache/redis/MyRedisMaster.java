package org.isotope.jfp.framework.cache.redis;

import java.util.List;
import java.util.Map;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.support.IJedisSupport;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSON;

/**
 * Redis缓存实现<br>
 * 面向开发人员使用
 * 
 * @version 3.1.1 2016/3/17
 * @version 2.4.2 2015/12/10
 * @version 2.4.1 2015/11/9
 * @since 20150728
 *
 */
public class MyRedisMaster implements ICacheService, ISFrameworkConstants {

	public MyRedisMaster() {

	}

	public MyRedisMaster(IJedisSupport jedisSupport) {
		this.jedisSupport = jedisSupport;
	}

	public MyRedisMaster(IJedisSupport jedisSupport, int waitTime) {
		this.jedisSupport = jedisSupport;
		this.waitTime = waitTime;
	}

	private int waitTime = 5;

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	String jedisScope;
	
	public void setJedisScope(String jedisScope) {
		this.jedisScope = jedisScope;
	}

	IJedisSupport jedisSupport;

	public IJedisSupport getJedisSupport() {
		return jedisSupport;
	}

	public void setJedisSupport(IJedisSupport jedisSupport) {
		this.jedisSupport = jedisSupport;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// String str = "aa//bb//cc//dd";
		// System.out.println(str.substring(0,str.indexOf("//")));
		// System.out.println(str.substring(str.indexOf("//")+2));
		// List<Object> vs = new ArrayList<Object>();
		// Object value = new RESTResultBean();
		// vs.add(new RESTResultBean());
		// vs.add(new RESTResultBean());
		// vs.add(new RESTResultBean());
		// vs.add(new RESTResultBean());
		// System.out.println(getStringToRedis(value));
		// System.out.println(vs.getClass().getName() + BACKSLASH2 +
		// JSONArray.toJSONString(vs));
		//
		// System.out.println((getStringToRedis(value)).split(BACKSLASH2)[0]);
		// System.out.println((getStringToRedis(value)).split(BACKSLASH2)[1]);
		// String[] values = (vs.getClass().getName() + BACKSLASH2 +
		// JSONArray.toJSONString(vs)).split(BACKSLASH2);
		// try {
		// Object a = JSON.parseObject(values[1], Class.forName(values[0]));
		// System.out.println(a);
		// } catch (ClassNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	@Override
	public boolean putObject(String key, Object value) {
		getJedisSupport().add(key, getStringToRedis(value), waitTime);
		return true;
	}

	public boolean putObject(String key, Object value, int expireTimeWithSecond, boolean translation) {
		getJedisSupport().add(key, getStringToRedis(value, translation), expireTimeWithSecond);
		return true;
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
			return (String) value;
		if (translation)
			return value.getClass().getName() + BACKSLASH2 + JSON.toJSON(value);
		else
			return (String) value;
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
		return getClassFromRedis(getJedisSupport().get(key));
	}

	@Override
	public Object getObject(String key, boolean translation) {
		return getClassFromRedis(getJedisSupport().get(key), translation);
	}

	@Override
	public Object deleteObject(String key) {
		return getClassFromRedis(getJedisSupport().del(key));
	}

	@Override
	public Object deleteObject(String key, boolean translation) {
		return getClassFromRedis(getJedisSupport().del(key), translation);
	}

	@Override
	public Map<String, String> getAllObjectInMap(String key) {
		return getJedisSupport().hgetall(key);
	}

	/**
	 * 追加数据到已有的缓存数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public boolean addObjectInMap(String rkey, String mkey, Object value) {
		return getJedisSupport().hset(rkey, mkey, getStringToRedis(value));
	}

	@Override
	public boolean addObjectInMap(String rkey, String mkey, Object value, boolean translation) {
		return getJedisSupport().hset(rkey, mkey, getStringToRedis(value, translation));
	}

	/**
	 * 从已有的缓存数据里面删除一个数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public Object removeObjectInMap(String rkey, String mkey) {
		String value = getJedisSupport().hdel(rkey, mkey);
		return getClassFromRedis(value);
	}

	@Override
	public Object removeObjectInMap(String rkey, String mkey, boolean translation) {
		String value = getJedisSupport().hdel(rkey, mkey);
		return getClassFromRedis(value, translation);
	}

	/**
	 * 从已有的缓存数据里面获取一个数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public Object findObjectInMap(String rkey, String mkey) {
		String value = getJedisSupport().hget(rkey, mkey);
		return getClassFromRedis(value);
	}

	@Override
	public Object findObjectInMap(String rkey, String mkey, boolean translation) {
		String value = getJedisSupport().hget(rkey, mkey);
		return getClassFromRedis(value, translation);
	}

	@Override
	public long sizeOfMap(String key) {
		return getJedisSupport().hlen(key);
	}

	@Override
	public long sizeOfList(String key) {
		return getJedisSupport().llen(key);
	}

	@Override
	public boolean offerObjectInList(String key, Object value) {
		getJedisSupport().listAdd(key, getStringToRedis(value));
		return true;
	}

	@Override
	public boolean offerObjectInList(String key, Object value, boolean translation) {
		getJedisSupport().listAdd(key, getStringToRedis(value, translation));
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
		return getJedisSupport().blistPop(key, waitTime);
	}

	/**
	 * 从已有的缓存数据里面获取并移除第一个数据(阻塞模式) <br>
	 * （基于保存Key,List数据队列）
	 * 
	 * @return
	 */
	@Override
	public Object peekFirstObjectInList(String key, boolean translation) {
		String value = getJedisSupport().blistPop(key, waitTime);
		return getClassFromRedis(value, translation);
	}

	/**
	 * 从已有的缓存数据里面获取并移除第一个数据(阻塞模式) <br>
	 * （基于保存Key,List数据队列）
	 * 
	 * @return
	 */
	@Override
	public Object peekFirstObjectInList(String key, int waitTime, boolean translation) {
		String value = getJedisSupport().blistPop(key, waitTime);
		return getClassFromRedis(value, translation);
	}

	@Override
	public Object pollFirstObjectInList(String key) {
		String value = getJedisSupport().listPop(key);
		return getClassFromRedis(value);
	}

	@Override
	public Object pollFirstObjectInList(String key, boolean translation) {
		String value = getJedisSupport().listPop(key);
		return getClassFromRedis(value, translation);
	}

	/**
	 * @deprecated
	 */
	@Override
	public Object peekLastObjectInList(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @deprecated
	 */
	@Override
	public Object peekLastObjectInList(String key, boolean translation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @deprecated
	 */
	@Override
	public Object pollLastObjectInList(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @deprecated
	 */
	@Override
	public Object pollLastObjectInList(String key, boolean translation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void selectDB(int index) {
		getJedisSupport().selectDB(index);
	}

	@Override
	public void expire(String key, int timeOut) {
		getJedisSupport().expire(key, timeOut);
	}

	@Override
	public void init() {
		if(EmptyHelper.isNotEmpty(jedisScope)){
			IJedisSupport support = BeanFactoryHelper.getBean(jedisScope);
			if(support != null)
				jedisSupport = support;			
		}
		getJedisSupport().init();
	}

	@Override
	public boolean addMapAll(String key, Map<String, Object> value) {
		for (Map.Entry<String, Object> entry : value.entrySet()) {
			addObjectInMap(key, entry.getKey(), entry.getValue());
		}
		return true;
	}

	@Override
	public boolean offerListAll(String key, List<Object> value) {
		for (Object val : value)
			offerObjectInList(key, val);
		return true;
	}

	@Override
	public Object indexObjectInList(String key, int index) {
		return getJedisSupport().indexObjectInList(key, index);
	}

	@Override
	public long setnx(String key, Object value) {
		return getJedisSupport().setnx(key, getStringToRedis(value), waitTime);
	}

	@Override
	public boolean removeKey(String key) {
		return getJedisSupport().removeKey(key);
	}
}
