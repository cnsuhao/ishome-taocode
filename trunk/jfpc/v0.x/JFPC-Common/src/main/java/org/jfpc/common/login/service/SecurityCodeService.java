package org.jfpc.common.login.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.jfpc.base.bean.LoginerBean;
import org.jfpc.base.support.MyFrameworkSupport;
import org.jfpc.common.login.dao.LoginDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 产品应用安全码验证
 * @author Spook
 *
 */

public class SecurityCodeService extends MyFrameworkSupport {
	private static final Logger logger = LoggerFactory.getLogger(SecurityCodeService.class);
	/**
	 * 数据库连接
	 */
	@Resource
	protected SqlSession mySqlSession;
	
	public LoginDao getLoginDao() {
		return mySqlSession.getMapper(LoginDao.class);
	}

	protected HashMap<String,HashMap<?, ?>> SecurityCode_ = new HashMap<String,HashMap<?, ?>> ();
	public void initialization(){
		//TODO
		try{
			List<HashMap<?, ?>> scs = getLoginDao().loadSecurityCode();
			
			for(HashMap<?, ?> lb : scs)
				SecurityCode_.put(lb.get("securityCode").toString(), lb);
			
			if(logger.isDebugEnabled())
				logger.debug(SecurityCode_.toString());
		}catch(Exception e){
			
		}
	}
	
	/**
	 * MD5验证机制
	 * @param loginer
	 */
	public boolean checkSecurityCode(LoginerBean loginer) {	
		return SecurityCode_.containsKey(loginer.getSecurityCode());
	}
}
