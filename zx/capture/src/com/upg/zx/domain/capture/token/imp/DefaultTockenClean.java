package com.upg.zx.domain.capture.token.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.upg.zx.domain.capture.bean.Token;
import com.upg.zx.domain.capture.token.TokenClean;
import com.upg.zx.domain.capture.token.TokenRegistry;

/**
 * token清理实现
 * @author lujf
 *
 */
public class DefaultTockenClean implements TokenClean {
	protected Logger logger = Logger.getLogger(this.getClass());
 
	private TokenRegistry tokenRegistry;
	
	
	public TokenRegistry getTokenRegistry() {
		return tokenRegistry;
	}


	public void setTokenRegistry(TokenRegistry tokenRegistry) {
		this.tokenRegistry = tokenRegistry;
	}


	@Override
	/**
	 * 清除过期的token
	 */
	public void clean() {
		 this.logger.info("Beginning ticket cleanup.");
		 final List<Token> tokensToRemove = new ArrayList<Token>();
		 final Collection<Token> tokensInCache;
		 tokensInCache = this.tokenRegistry.getTokens();
		 for (final Token token : tokensInCache) {
             if (token.isExpired()) {
            	 tokensToRemove.add(token);
             }
         }
		 logger.info(tokensToRemove.size()+"发现已经过期，需要移除!");
		 for(final Token token :tokensToRemove){
			 this.tokenRegistry.deleteToken(token.getJesssionId());
		 }
		 logger.info("完成过期的Token移除!");
		 
	}
	
}
