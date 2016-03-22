package org.isotope.jfp.mcws.captcha;

/**
 * 人工打码接口业务
 * @author 001745
 *
 */
public interface ISCaptcha {

	/**
	 * 验证码队列
	 */
	public static final String CODE_LIST = "CODE:LIST";

	////////////////////////////////////////////////////////////
	/**
	 * 添加一个人工识别码到任务队列
	 * @return
	 */
	boolean addCodeInJobList(String jobid,String filename);
	
	//********************************************************//
	/**
	 * 获得一个任务的数据结果
	 * @return
	 */
	Object getRestltWithJob(String jobid);
	/**
	 * 保存一个任务的数据结果
	 * @return
	 */
	boolean putRestltWithJob(String jobid,String result);
	//********************************************************//
	/**
	 * 锁定一个识别任务
	 * @return
	 */
	boolean lockJobOnList(String jobid,String ipAdress);
	/**
	 * 获得任务队列
	 * @return
	 */
	Object loadJobWithList();
}
