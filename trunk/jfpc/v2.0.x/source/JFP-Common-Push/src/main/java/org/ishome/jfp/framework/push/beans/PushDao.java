package org.ishome.jfp.framework.push.beans;

import org.ishome.jfp.beans.PhoneUser.PhoneUserDBO;
import org.ishome.jfp.beans.PhoneUserTerminal.PhoneUserTerminalDBO;
import org.ishome.jfp.framework.support.ISDatabaseSupport;


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
