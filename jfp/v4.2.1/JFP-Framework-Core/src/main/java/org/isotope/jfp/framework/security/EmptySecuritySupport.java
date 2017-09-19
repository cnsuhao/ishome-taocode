package org.isotope.jfp.framework.security;

import org.isotope.jfp.framework.security.value.PBESecurityHelper;
import org.isotope.jfp.framework.support.ISecuritySupport;
import org.isotope.jfp.framework.utils.EmptyHelper;

/**
 * 数据安全处理（非加密）
 * 
 * @author Spook
 * @version 2.0.5 2015/2/16
 * 
 */
public class EmptySecuritySupport implements ISecuritySupport {

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
