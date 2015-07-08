package org.ishome.jfp.framework.constants;

import org.ishome.jfp.framework.constants.ISJobConstants;

/**
 * 对接业务常量定义<br>
 * 下行大写开通，上行小写开头，KEY值全大写
 * @author Spook
 * @since v2.0.0
 * @version v2.0.0 2015/4/14
 */
public interface IAccessTypeConstants extends ISJobConstants{
	//********************************上行业务**************************************
	public static final String Clound_PreverBusiness="prever";//前置机版本更新（监控程序使用）
	public static final String Clound_HeartBusiness="heart";//心跳同步  
	public static final String Clound_SecurityBusiness="security";//加密测试
	
}
