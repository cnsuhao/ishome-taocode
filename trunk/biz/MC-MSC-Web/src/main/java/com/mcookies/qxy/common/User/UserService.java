package com.mcookies.qxy.common.User;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 用户表 */
@Service
public class UserService extends MyServiceSupport {

	public UserDao getDao() {
		return getMySqlSession().getMapper(UserDao.class);
	}

}
