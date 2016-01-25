package org.ishome.jfp.bds.service.biz.utils;

import org.ishome.jfp.framework.beands.RESTResultBean;
import org.ishome.jfp.framework.constants.IAccessTypeConstants;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.ishome.jfp.framework.utils.JedisUtil;

import redis.clients.jedis.Jedis;


public class MobileServiceHelper implements IAccessTypeConstants, ISFrameworkConstants {
	public final static String MESSAGE_SUCCESS = "处理成功。";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 新接口调用方法
	 * 
	 * @param hosId
	 * @param bizName
	 * @param hao
	 * @param timeout
	 * @return
	 */
	public static RESTResultBean getMobileServiceReult(String operationId, String key, String value, int timeout) {
		Jedis jedis = JedisUtil.getJedis();
		// ////////////////////////////////////////////////
		RESTResultBean rs = new RESTResultBean();
		// 发送数据
		jedis.rpush(key, value);
		long t1 = System.currentTimeMillis();
		try {
			while (true) {
				Thread.sleep(300);
				// ////////////////////////////////////////////////
				// 处理结果返回
				String result = jedis.get(operationId);
				if (EmptyHelper.isNotEmpty(result)) {
					jedis.del(operationId);
					rs.setMessage(MESSAGE_SUCCESS);
					rs.setResult(result);
					return rs;
				}
				// ////////////////////////////////////////////////
				if (System.currentTimeMillis() - t1 > timeout * 1000) {// 3 second
					rs.setCode(ONE);
					rs.setResult(JOB_MESSAGE_ERROR);
					return rs;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JedisUtil.returnJedis(jedis);
		}
		rs = new RESTResultBean();
		rs.setCode(SYSTEM_ERROR_CODE);
		rs.setMessage(SYSTEM_ERROR_MESSAGE);
		return rs;
	}
}
