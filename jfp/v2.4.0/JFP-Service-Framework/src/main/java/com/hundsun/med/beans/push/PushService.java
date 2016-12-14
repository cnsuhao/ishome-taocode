package com.hundsun.med.beans.push;

import org.ishome.jfp.framework.support.MyDataBaseObjectSupport;
import org.ishome.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hundsun.med.beans.PhoneUser.PhoneUserDBO;
import com.hundsun.med.beans.PhoneUserTerminal.PhoneUserTerminalDBO;

/** APP推送 */
@Service
public class PushService extends MyServiceSupport {
	private Logger logger = LoggerFactory.getLogger(PushService.class);

	public PushDao getDao() {
		return getMySqlSession().getMapper(PushDao.class);
	}

	/**
	 * 数据库分表
	 * 
	 * @param data
	 */
	public void changeTable(MyDataBaseObjectSupport data, int dbType) {

	}

	/**
	 * 获得用户ID
	 * @param paramBean
	 * @return
	 */
	public PhoneUserDBO getPhoneUserId(PhoneUserDBO paramBean){
		return getDao().getPhoneUserId(paramBean);
	}
	
	/**
	 * 获得用户终端类型
	 * @param paramBean
	 * @return
	 */
	public PhoneUserTerminalDBO getPhoneUserTerminalType(PhoneUserTerminalDBO paramBean){
		return getDao().getPhoneUserTerminalType(paramBean);
	}
}
