package org.isotope.jfp.framework.security;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.security.value.MD5SecurityHelper;
import org.isotope.jfp.framework.security.value.PBESecurityHelper;
import org.isotope.jfp.framework.support.ISSecuritySupport;

/**
 * 数据加密与解密处理
 * 
 * @author fucy
 * 
 */
public class MySecuritySupport implements ISSecuritySupport,ISFrameworkConstants {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	private String getKey(String companyId, String currentTimestamp, String companyKey, String companyToken ) throws Exception{
		StringBuffer sb = new  StringBuffer();
		sb.append(companyId);
		sb.append(currentTimestamp);
		sb.append(companyKey);
		sb.append(companyToken);
		
		return MD5SecurityHelper.encrypt(sb.toString());
	}
	
	@Override
	public String decryption(String companyId, String data) {
		try {
			return PBESecurityHelper.decrypt(companyId, data);
		} catch (Exception e) {
			return data;
		}
	}
	
	@Override
	public String encryption(String companyId, String data) {
		try {
			return PBESecurityHelper.encrypt(getKey(companyId,null,null,null), data);
		} catch (Exception e) {
			return data;
		}
	}

}
