package org.ishome.jfp.framework.job;

import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.constants.ISJobConstants;
import org.ishome.jfp.framework.utils.EmptyHelper;


/**
 * 基于处理多线程业务，用于获取接口数据内容
 * 
 * @author Spook
 * @version 2.0.1 2015/6/23
 * @since 2.0.1 2015/6/23
 * 
 */
public class HospitalMobileKeyService implements ISFrameworkConstants, ISJobConstants {
	protected static final String System_Default_Version = "911";

	/**
	 * 获得对接业务名称(客户数据)
	 * 
	 * @param hosId
	 *            医院ID
	 * @return
	 */
	public static String getMsrKeyName(String bizName,String version) {
		// Key = 医院ID:业务名称(英字):对接程序所属版本号
		StringBuffer key = new StringBuffer();
		key.append(bizName);
		key.append(Service_Start);
		key.append(COLON);
		key.append(version);
		return key.toString();
	}

	/**
	 * 获得对接程序使用版本
	 * 
	 * @param version
	 * @return
	 */
	public static String getUseVersion(String version) {
		if(EmptyHelper.isEmpty(version))
			return System_Default_Version;
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
