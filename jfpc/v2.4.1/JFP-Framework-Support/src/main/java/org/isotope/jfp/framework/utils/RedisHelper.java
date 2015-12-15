package org.isotope.jfp.framework.utils;

import java.util.HashSet;
import java.util.Set;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * 缓存数据对象与字符串转换
 * 
 * @author Spook
 * @since 0.2.1
 * @version 0.1.0 2014/11/28
 * 
 */
public class RedisHelper implements ISFrameworkConstants {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
	    jedisClusterNodes.add(new HostAndPort("172.16.2.201", 7000));  
        jedisClusterNodes.add(new HostAndPort("172.16.2.201", 7001));  
        jedisClusterNodes.add(new HostAndPort("172.16.2.201", 7002));  
        jedisClusterNodes.add(new HostAndPort("172.16.2.202", 7000));  
        jedisClusterNodes.add(new HostAndPort("172.16.2.202", 7001));  
        jedisClusterNodes.add(new HostAndPort("172.16.2.201", 7002));  
        jedisClusterNodes.add(new HostAndPort("172.16.2.203", 7000));  
        jedisClusterNodes.add(new HostAndPort("172.16.2.203", 7001)); 
	    
	    JedisCluster jc = new JedisCluster(jedisClusterNodes);  
	    System.out.println( DateHelper.currentTimestamp());
	    for(int i=0;i<100;i++){
	        System.out.println(jc.get("EITSP:EITSP"+i));	    
	    }
	    System.out.println( DateHelper.currentTimestamp());
	    
//		String str = "aa//bb//cc//dd";
//		System.out.println(str.substring(0,str.indexOf("//")));
//		System.out.println(str.substring(str.indexOf("//")+2));
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
		return getClassFromRedis(value,true);
	}

	public static Object getClassFromRedis(String value, boolean translation) {
		if (EmptyHelper.isEmpty(value))
			return null;
		if (translation) {
			if (value.indexOf(BACKSLASH2) < 0)
				return (String) value;
			String[] values = new String[2];
			values[0] = value.substring(0,value.indexOf("//"));
			values[1] = value.substring(value.indexOf("//")+2);
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


}
