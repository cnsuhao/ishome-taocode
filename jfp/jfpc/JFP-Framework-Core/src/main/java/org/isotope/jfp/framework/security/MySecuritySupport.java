package org.isotope.jfp.framework.security;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.security.value.PBESecurityHelper;
import org.isotope.jfp.framework.support.ISecuritySupport;
import org.isotope.jfp.framework.utils.TokenBusinessHelper;

/**
 * 数据加密与解密处理
 * 
 * @author fucy
 * 
 */
public class MySecuritySupport implements ISecuritySupport, ISFrameworkConstants {

	/**
	 * 获得企业加密Key
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String loadSecurityKey(String CompanyId, String version, String ipAdress) throws Exception {
		return TokenBusinessHelper.getBizTokenData(CompanyId, version, ipAdress.replace(".", "")).substring(0, 8);
	}

	@Override
	public String decryption(String token, String data) {
		try {
			return PBESecurityHelper.decrypt(token, data);
		} catch (Exception e) {
			return data;
		}
	}

	@Override
	public String encryption(String token, String data) {
		try {
			return PBESecurityHelper.encrypt(token, data);
		} catch (Exception e) {
			return data;
		}
	}

}
