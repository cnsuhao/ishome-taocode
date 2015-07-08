package org.ishome.jfp.framework.security;

import org.ishome.jfp.framework.security.value.PBESecurityHelper;
import org.ishome.jfp.framework.support.ISSecuritySupport;
import org.ishome.jfp.framework.utils.EmptyHelper;


/**
 * 数据安全处理（基于PBE加密）
 * 
 * @author Spook
 * @version 2.0.1 2015/7/7
 * @since 2.0.1
 * 
 */
public class EmptySecuritySupport implements ISSecuritySupport {

	/**
	 * 数据解密
	 */
	@Override
	public String decryption(String companyKey, String data) {
		if (EmptyHelper.isBlank(companyKey))
			return data;
		try {
			return PBESecurityHelper.decrypt(companyKey, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 
	 */
	@Override
	public String encryption(String companyKey, String data) {
		if (EmptyHelper.isBlank(companyKey))
			return data;
		try {
			return PBESecurityHelper.encrypt(companyKey, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
}
