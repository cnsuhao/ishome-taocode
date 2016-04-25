package org.isotope.jfp.framework.net.proxy;

import org.isotope.jfp.framework.utils.RedisHelper;

import redis.clients.jedis.Jedis;

public class A {

	public static void main(String[] args) {
		Jedis oldJedis = new Jedis("172.16.2.201",6379);
		oldJedis.auth("123456");
		
		Jedis newJedis = new Jedis("172.16.2.201",6379);
		newJedis.auth("123456");
		newJedis.select(3);
		
		RedisHelper.copy(oldJedis, "HTTP_PROXY:GOOD", newJedis, "HTTP_PROXY:GOOD", true);
	}

}
