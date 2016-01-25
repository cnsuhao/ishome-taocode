package org.ishome.jfp.mobile.constants;

import org.ishome.jfp.framework.constants.ISJobConstants;

/**
 * 常量定义
 * 
 * @author Spook
 * @version 2.3.1 2015/6/23
 * @since 2.3.1 2015/6/23
 * 
 */
public interface IMSRConstants extends ISJobConstants {
	//云端服务后缀
	public static final String Mobile_Service_Name = "BusinessImpl";
	
	/**
	 * 开始业务处理数据
	 */
	public static final String MOBILE_SERVICE_START = "Start service =====>>>>>  ";
	/**
	 * 拒绝业务处理数据
	 */
	public static final String MOBILE_SERVICE_CANCEL = "Cancel service xxxxxx ";
	/**
	 * 结束业务处理数据
	 */
	public static final String MOBILE_SERVICE_END = "End   service <<<<<===== ";
}
