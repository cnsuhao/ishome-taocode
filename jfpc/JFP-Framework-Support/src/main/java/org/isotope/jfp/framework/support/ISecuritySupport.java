package org.isotope.jfp.framework.support;

/**
 * 加密解密接口
 * @author fucy
 * @since 2.0.5
 * @version 2.0.5 2014/2/15
 */
public interface ISecuritySupport {
	/**
	 * 加密
	 */
	public static final String ENCRYPTION = "1";
	
	/**
	 * 未加密
	 */
	public static final String DECRYPTION= "0";
	
	/**
	 * 数据解密
	 * @param companyKey         企业密钥
	 * @param data               数据
	 * @return
	 */
	public String decryption(String companyKey,String data);
	
	/**
	 * 数据加密
	 * @param companyKey         企业密钥
	 * @param data               数据
	 * @return
	 */
	public String encryption(String companyKey,String data);
}
