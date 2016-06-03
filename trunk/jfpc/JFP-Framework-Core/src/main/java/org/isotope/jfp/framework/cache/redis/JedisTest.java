package org.isotope.jfp.framework.cache.redis;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class JedisTest {
    public static void main(String[] args) {

        Jedis jedis = new Jedis("172.16.2.200", 6379);
        jedis.connect();
        int i = 0;
        boolean a = true;
        while (a) {
            try {
                Thread.sleep(100);
                jedis.set("" + i, "" + i);
                //System.out.println(jedis.get("" + i));
                i++;
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    jedis = new Jedis("172.16.2.200", 6379);
                    jedis.connect();
                } catch (Exception ex) {

                }
            }
        }

        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("172.16.2.201", 7000));
        jedisClusterNodes.add(new HostAndPort("172.16.2.201", 7001));
        jedisClusterNodes.add(new HostAndPort("172.16.2.201", 7002));
        jedisClusterNodes.add(new HostAndPort("172.16.2.202", 7000));
        jedisClusterNodes.add(new HostAndPort("172.16.2.202", 7001));
        jedisClusterNodes.add(new HostAndPort("172.16.2.201", 7002));
        jedisClusterNodes.add(new HostAndPort("172.16.2.203", 7000));
        jedisClusterNodes.add(new HostAndPort("172.16.2.203", 7001));

        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);

        jedisCluster.set("1111", "1111");
        //System.out.println(jedisCluster.get("1111"));
    }

}
