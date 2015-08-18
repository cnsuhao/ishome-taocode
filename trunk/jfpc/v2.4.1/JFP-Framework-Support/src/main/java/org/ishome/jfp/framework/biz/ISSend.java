package org.ishome.jfp.framework.biz;

/**
 * 接收数据
 * 
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public interface ISSend {

	/**
	 * 数据发送
	 */
	boolean doSend() throws Exception;
//	/**
//	 * 数据发送
//	 */
//	void doSend(String hosId, Object data) throws Exception;
}
