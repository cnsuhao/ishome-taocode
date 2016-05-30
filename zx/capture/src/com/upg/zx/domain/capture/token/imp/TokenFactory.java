package com.upg.zx.domain.capture.token.imp;

import java.util.List;
import java.util.Map;

import com.upg.zx.domain.capture.bean.Token;
/**
 * 生成Token
 * @author lujf
 *
 */
public class TokenFactory {
	//过期时间(单位 mm)
	private long timeOut;
	
	public long getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(long timeOut) {
		this.timeOut = timeOut;
	}
	
	/**
	 * 生成Token
	 * @param cookies
	 * @param jesssionId
	 * @param param
	 * @return
	 */
	public synchronized Token  generateToken(List cookies,String jesssionId,Map<String,String>param){
		return new Token(cookies, jesssionId, param, timeOut);

	}
}
