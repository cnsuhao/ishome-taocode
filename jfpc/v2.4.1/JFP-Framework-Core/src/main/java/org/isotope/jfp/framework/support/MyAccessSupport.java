package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.beans.access.BaseHAO;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.key.common.BussinessKeyService;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.utils.RedisHelper;

import com.alibaba.fastjson.JSON;

//import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

/**
 * 旧系统对接拦截器<br>
 * 临时性使用，不适合M2
 * 
 * @author fucy
 * @version 2.3.1 2015/7/6
 * @version 2.2.2 2015/5/13
 * @since 2.0.0 2015/1/19
 * 
 */
public class MyAccessSupport implements ISFrameworkConstants {

	public static final String TIMEOUT_MESSAGE = "网络访问超时";
	public static final String SUCCESS_MESSAGE = "处理成功";
	public int waitTimeMinute = 15;

	public int getWaitTimeMinute() {
		return waitTimeMinute;
	}

	public void setWaitTimeMinute(int waitTimeMinute) {
		this.waitTimeMinute = waitTimeMinute;
	}

	// /////////////////////////////////////////////////////////////////////////////
	/**
	 * 对接调用
	 * 
	 * @param compantId
	 *            医院ID
	 * @param bizName
	 *            业务ID
	 * @param hao
	 *            请求参数HAO实体
	 * @return
	 * @see <IAccessTypeConstants>
	 */
	public RESTResultBean getAccessReult(String compantId, String bizName, BaseHAO hao) {
		return getAccessReult(hao.getPuk(), compantId, bizName, RedisHelper.getStringToRedis(hao, true), waitTimeMinute);
	}

	/**
	 * 对接接口调用
	 * 
	 * @param compantId
	 * @param bizName
	 * @param hao
	 * @param timeout
	 * @return
	 */
	public RESTResultBean getAccessReult(String compantId, String bizName, BaseHAO hao, int timeout) {
		return getAccessReult(hao.getPuk(), compantId, bizName, RedisHelper.getStringToRedis(hao, true), timeout);
	}

	/**
	 * 对接接口调用
	 * 
	 * @param compantId
	 * @param bizName
	 * @param hao
	 * @param timeout
	 * @return
	 */
	public RESTResultBean getAccessReult(String operationId, String compantId, String bizName, String jsonData) {
		return getAccessReult(operationId, compantId, bizName, jsonData, waitTimeMinute);
	}

	/**
	 * 对接接口调用
	 * 
	 * @param operationId
	 * @param compantId
	 * @param bizName
	 * @param jsonData
	 * @param timeout
	 * @return
	 */
	public RESTResultBean getAccessReult(String operationId, String compantId, String bizName, String jsonData, int timeout) {
		Jedis jedis = null;//JedisUtil.getJedis();
		// ////////////////////////////////////////////////
		RESTResultBean rs;
		// TODO 发送数据
		jedis.rpush(BussinessKeyService.getBizServiceKeyName(compantId, bizName), jsonData);
		long resquestTime = System.currentTimeMillis();
		try {
			while (true) {
				Thread.sleep(2000);
				// ////////////////////////////////////////////////
				// 处理结果返回
				String result = jedis.get(operationId);
				if (EmptyHelper.isNotEmpty(result)) {
					jedis.del(operationId);
					rs = JSON.parseObject(result, RESTResultBean.class);
					rs.setResult(EMPTY + rs.getResult());
					return rs;
				}
				// ///////////////////超时判断/////////////////////////////
				if (System.currentTimeMillis() - resquestTime > timeout * 1000) {
					rs = new RESTResultBean();
					rs.setCode(ONE);
					rs.setMessage(MESSAGE_ERROR_SYNC);
					return rs;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//JedisUtil.returnJedis(jedis);
		}
		rs = new RESTResultBean();
		rs.setCode(SYSTEM_ERROR_CODE);
		rs.setMessage(MESSAGE_ERROR_SYSTEM);
		return rs;
	}

}
