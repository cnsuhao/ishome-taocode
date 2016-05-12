package org.isotope.jfp.framework.log;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;
import org.isotope.jfp.framework.cache.redis.master.RedisPoolUtil;
import org.isotope.jfp.framework.constants.pub.ISLogConstants;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.DateHelper;

import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;

/**
 * 日志输出模型
 * 
 * @author Spook
 * 
 */
public class MyLoggerAppender extends AppenderSkeleton implements ISLogConstants {
	String redisKey;
	public String getRedisKey() {
		return redisKey;
	}

	public void setRedisKey(String redisKey) {
		this.redisKey = redisKey;
	}
	int redisIndex = 1;
	
	public int getRedisIndex() {
		return redisIndex;
	}

	public void setRedisIndex(int redisIndex) {
		this.redisIndex = redisIndex;
	}
	protected Layout layout;

	public MyLoggerAppender() {
	}

	public MyLoggerAppender(Layout layout) {
		this.layout = layout;
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public void close() {
		if (this.closed)
			return;
		this.closed = true;
	}

	public boolean requiresLayout() {
		return false;
	}

	Jedis jedis = null;
	public Jedis getJedis() {
		if (jedis == null) {
			RedisPoolUtil rpu = (RedisPoolUtil) BeanFactoryHelper.getBean("redisPoolUtil");
			jedis = rpu.getJedis();
			jedis.select(redisIndex);
		}
		return jedis;
	}
	// 推送到消息队列
	protected void append(LoggingEvent event) {
		// 获知当前方法、行号的
		LocationInfo locationInfo = event.getLocationInformation();
		JSONObject ml = new JSONObject();
		ml.put(LOG_LEVEL, event.getLevel().toString());
		ml.put(LOG_UPD, DateHelper.currentTimeMillisCN1());
		ml.put(LOG_FMN, locationInfo.fullInfo);
		ml.put(LOG_MSG, event.getMessage());
		if(event.getThrowableStrRep()!=null)
			ml.put(LOG_ERR, event.getThrowableStrRep());
		try {
			getJedis().rpush(redisKey, ml.toString());
		} catch (Exception e) {
			System.out.println(ml.toString());
			if (jedis != null) {
				jedis.close();
			}
		}
	}

}
