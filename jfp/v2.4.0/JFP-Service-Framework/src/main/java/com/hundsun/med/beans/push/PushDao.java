package com.hundsun.med.beans.push;

import org.ishome.jfp.framework.support.ISDatabaseSupport;

import com.hundsun.med.beans.PhoneUser.PhoneUserDBO;
import com.hundsun.med.beans.PhoneUserTerminal.PhoneUserTerminalDBO;

/** APP推送*/
public interface PushDao extends ISDatabaseSupport{
	
	/**
	 * 获得用户ID
	 * @param paramBean
	 * @return
	 */
	PhoneUserDBO getPhoneUserId(PhoneUserDBO paramBean);
	
	/**
	 * 获得用户终端类型
	 * @param paramBean
	 * @return
	 */
	PhoneUserTerminalDBO getPhoneUserTerminalType(PhoneUserTerminalDBO paramBean);
	
}
