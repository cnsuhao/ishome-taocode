package org.ishome.jfp.framework.job;

import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.constants.ISJobConstants;

/**
 * 接口控制层超类
 * 
 * @author Spook 
 * @version 2.0.1 2015/7/3
 * @since 2.0.1 2015/7/3
 */
public class HospitalCloundKeyService implements ISFrameworkConstants, ISJobConstants {
	protected static final String System_Default_Version = "911";
	/**
	 * 获得对接业务名称(客户数据)
	 * 
	 * @param hosId
	 *            医院ID
	 * @return
	 */
	public static String getHospitalSynchronizationKeyName(String hosId, String bizName) {
		// Key = 医院ID:业务名称(英字):对接程序所属版本号
		StringBuffer key = new StringBuffer();
		key.append(hosId);
		key.append(COLON);
		key.append(bizName);
		key.append(Bussiness_Start);
		key.append(COLON);
		key.append(System_Default_Version);
		return key.toString();
	}


}
