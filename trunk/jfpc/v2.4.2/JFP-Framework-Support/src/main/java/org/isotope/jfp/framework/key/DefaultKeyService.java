package org.isotope.jfp.framework.key;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISServiceConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;

/**
 * 业务key名称定义
 * 
 * @author fucy
 * @version 2.4.1 2015/8/17
 * @since 2.4.1 2015/8/17
 * 
 */
public class DefaultKeyService implements ISFrameworkConstants, ISServiceConstants {

	/**
	 * 获得对接业务名称(客户数据)
	 * 
	 * @param hosId
	 *            医院ID
	 * @return
	 */
	public static String getKeysName(String version, String... keys) {
		// Key = 企业ID:业务名称(英字):对接程序所属版本号
		StringBuffer key = new StringBuffer();
		for (String k : keys) {
			key.append(k);
			key.append(COLON);
		}
		key.append(getUseVersion(version));
		return key.toString();
	}

	/**
	 * 获得对接程序使用版本
	 * 
	 * @param version
	 * @return
	 */
	public static String getUseVersion(String version) {
		if (EmptyHelper.isEmpty(version))
			return getDefaultUseVersion();
		else
			return version;
	}

	/**
	 * 获得对接程序使用版本
	 * 
	 * @param version
	 * @return
	 */
	public static String getDefaultUseVersion() {
		return System_Default_Version;
	}
}
