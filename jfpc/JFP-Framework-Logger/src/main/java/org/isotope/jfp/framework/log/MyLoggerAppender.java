package org.isotope.jfp.framework.log;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;
import org.isotope.jfp.framework.constants.pub.ISLogConstants;
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
			jedis = new Jedis(redisHost, redisPort);
			jedis.auth(redisAuth);
			jedis.select(redisIndex);
		}
		return jedis;
	}

	public void closeJedis() {
		if (jedis != null) {
			jedis.close();
		}
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
		if (event.getThrowableStrRep() != null)
			ml.put(LOG_ERR, event.getThrowableStrRep());
		try {
			getJedis().rpush(redisKey, ml.toString());
		} catch (Exception e) {
			System.out.println(ml.toString());			
		}finally{
			closeJedis();
		}
	}

	////////////////////////////////////////////////////////
	String redisKey;
	String redisHost;
	int redisPort;
	String redisAuth;
	int redisIndex = 1;

	public String getRedisKey() {
		return redisKey;
	}

	public void setRedisKey(String redisKey) {
		this.redisKey = redisKey;
	}

	public String getRedisHost() {
		return redisHost;
	}

	public void setRedisHost(String redisHost) {
		this.redisHost = redisHost;
	}

	public int getRedisPort() {
		return redisPort;
	}

	public void setRedisPort(int redisPort) {
		this.redisPort = redisPort;
	}

	public String getRedisAuth() {
		return redisAuth;
	}

	public void setRedisAuth(String redisAuth) {
		this.redisAuth = redisAuth;
	}

	public int getRedisIndex() {
		return redisIndex;
	}

	public void setRedisIndex(int redisIndex) {
		this.redisIndex = redisIndex;
	}
}
