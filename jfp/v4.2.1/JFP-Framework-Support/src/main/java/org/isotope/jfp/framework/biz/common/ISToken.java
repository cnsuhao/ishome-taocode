package org.isotope.jfp.framework.biz.common;
/**
 * 保存数据到数据中心
 * 
 * @author Spook
 * @version 3.1.2
 * @since 3.1.2 2016/5/24
 * 
 */
public interface ISToken {
	/**
	 * 更新Token令牌内容
	 */
	boolean chageToken() throws Exception;
	/**
	 * 检查Token令牌内容
	 */
	boolean checkToken() throws Exception;
	/**
	 *  返回加密后Token令牌内容
	 */
	String getToken() throws Exception;
	/**
	 * 缓存Token令牌内容
	 */
	boolean saveToken() throws Exception;
	/**
	 * 清除Token令牌内容
	 */
	boolean removeToken() throws Exception;
}
