package org.isotope.jfp.mpc.weixin.token;

import java.util.List;

import org.isotope.jfp.common.weixin.WeixinService;
import org.isotope.jfp.common.weixin.WeixinUserDBO;
import org.isotope.jfp.framework.common.CommonChannelConfig;
import org.isotope.jfp.mpc.weixin.beans.WeiXinUserReceverBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinUserTokenBean;

/**
 * 微信用户Token对接
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
public class WeiXinUserTokenService extends CommonChannelConfig implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	WeixinService WeixinService_;
	List<WeixinUserDBO> list;

	/**
	 * 加载用户Token
	 */
	public WeiXinUserTokenBean loadWeiXinUserToken(WeiXinUserReceverBean user) {
		WeiXinUserTokenBean userToken = null;
		return userToken;
	}
}
