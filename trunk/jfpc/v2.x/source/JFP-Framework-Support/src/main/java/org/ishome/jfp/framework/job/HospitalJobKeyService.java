package org.ishome.jfp.framework.job;

import java.util.Random;

import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.constants.ISJobConstants;
import org.ishome.jfp.framework.mq.IMedMqService;
import org.ishome.jfp.framework.utils.BeanFactoryHelper;


/**
 * 定时任务Key定义
 * 
 * @author Spook
 * @version 2.0.1 2015/6/23
 * @since 2.0.1 2015/6/23
 * 
 */
public class HospitalJobKeyService implements ISFrameworkConstants, ISJobConstants {
	private static final String Version = "911";

	public static final String JOB_KEY = "JOB";
	public static final String FLAG_KEY = "FLAG";
	public static final String SEC_KEY = "SEC";
	public static final String TOKEN_KEY = "TOKEN";

	public static String getJobKey(String bizName) {
		return getKeyName(HOSPITAL, bizName+COLON+FLAG_KEY);
	}

	/**
	 * 获取范围内随机数
	 * 
	 * @return
	 */
	public static long getRandomMS() {
		int max = 10000;
		int min = 1000;
		Random random = new Random();
		return random.nextInt(max) % (max - min + 1) + min;
	}

	/**
	 * 获得消息队列名称(运行状态)
	 * 
	 * @param hosId
	 *            医院ID
	 * @return
	 */
	public static String getJobFlag(String bizName) {
		IMedMqService mq = BeanFactoryHelper.getMqService();
		return (String) mq.getObject(getJobKey(bizName));
	}

	public static boolean setJobFlag(String bizName, String jobType) {
		IMedMqService mq = BeanFactoryHelper.getMqService();
		mq.putObject(getJobKey(bizName), jobType, 60 * 1, false);//保存一分钟
		return true;
	}

	/**
	 * 设定业务队列状态
	 * 
	 * @param hosId
	 * @param bizName
	 * @return
	 */
	public static boolean setSyncJobType(String hosId, String bizName, String type) {
		IMedMqService mq = BeanFactoryHelper.getMqService();
		mq.putObject(getKeyName(hosId, bizName + Synchronization_Start), type, 60 * 180, true);//保存3个小时
		return true;
	}

	/**
	 * 获取业务队列状态
	 * 
	 * @param hosId
	 * @param bizName
	 * @return
	 */
	public static String getSyncJobType(String hosId, String bizName) {
		IMedMqService mq = BeanFactoryHelper.getMqService();
		return (String) mq.getObject(getKeyName(hosId, bizName + Synchronization_Start));
	}

	/**
	 * 获得对接业务名称(客户数据)
	 * 
	 * @param hosId
	 *            医院ID
	 * @return
	 */
	public static String getKeyName(String hosId, String bizName) {
		// Key = 医院ID:业务名称(英字):对接程序所属版本号
		StringBuffer key = new StringBuffer();
		key.append(hosId);
		key.append(COLON);
		key.append(bizName);
		key.append(COLON);
		key.append(getUseVersion(hosId, bizName));
		return key.toString();
	}

	/**
	 * 获得对接程序使用版本
	 * 
	 * @param hosId
	 * @param bizName
	 * @return
	 */
	public static String getUseVersion(String hosId, String bizName) {
		return Version;
	}

	/**
	 * 获得日常业务名称(客户数据)
	 * 
	 * @param hosId
	 *            医院ID
	 * @return
	 */
	public static String getBizJobDataKeyName(String hosId, String bizName) {
		return getKeyName(hosId, bizName);
	}

	/**
	 * 获得对接业务名称(同步数据)
	 * 
	 * @param hosId
	 *            医院ID
	 * @return
	 */
	public static String getSyncJobDataKeyName(String hosId, String bizName) {
		return getKeyName(hosId, bizName);
	}

}
