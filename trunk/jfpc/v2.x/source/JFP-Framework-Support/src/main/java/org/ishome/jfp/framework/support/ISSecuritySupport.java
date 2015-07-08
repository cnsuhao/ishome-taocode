package org.ishome.jfp.framework.support;

/**
 * 加密解密接口
 * @author Spook
 * @since 1.0.5
 * @version 1.0.5 2014/2/15
 */
public interface ISSecuritySupport {
	
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
