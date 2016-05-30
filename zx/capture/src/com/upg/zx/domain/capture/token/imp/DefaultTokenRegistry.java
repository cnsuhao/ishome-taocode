package com.upg.zx.domain.capture.token.imp;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.upg.zx.domain.capture.bean.Token;
import com.upg.zx.domain.capture.token.TokenRegistry;

import org.springframework.util.Assert;

/**
 * token注册管理类
 * @author lujf
 *
 */
public class DefaultTokenRegistry implements TokenRegistry {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	private final Map<String, Token> cache ; 
	
	public DefaultTokenRegistry(){
		this.cache = new ConcurrentHashMap<String, Token>();
	}
	
	@Override
	/**
	 * 将token添加到缓存中
	 */
	public void addToken(Token token) {
		Assert.notNull(token, "token cannot be null");
		cache.put(token.getJesssionId(), token);
		logger.debug("added token["+token.getJesssionId()+"]");

	}
    /**
     * 从缓存中获取token
     */
	@Override
	public Token getToken(String jsessionId) {
		if(jsessionId == null){
			return null;
		}
		final Token token = cache.get(jsessionId);
		if(token != null){
			logger.debug("token["+jsessionId+"] found");
		}
		return token;
	}
	
 
	
	/**
	 * 获取所有的token
	 */
	@Override
	public Collection<Token> getTokens() {
		 return Collections.unmodifiableCollection(this.cache.values());
	}
	
	/**
	 * 删除token
	 */
	@Override
	public boolean deleteToken(String jsessionId) {
		if(jsessionId == null){
			return false;
		}
		return (this.cache.remove(jsessionId) != null);
	}
}
