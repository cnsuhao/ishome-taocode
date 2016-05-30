package com.upg.zx.domain.capture.token;

import java.util.Collection;

import com.upg.zx.domain.capture.bean.Token;

/**
 * 抓取token注册
 * @author lujf
 *
 */
public interface TokenRegistry {
	/**
	 * 添加token
	 * @param token
	 */
	void addToken (Token token); 
	
	/**
	 * 获取token
	 * @param jsessionId
	 * @return
	 */
	Token getToken(String jsessionId);
	
	/**
	 * 删除token
	 * @param jsessionId
	 * @return
	 */
	boolean deleteToken(String jsessionId);
	
	/**
	 * 获取所有的tokens
	 * @return
	 */
	Collection<Token> getTokens();
	
}
