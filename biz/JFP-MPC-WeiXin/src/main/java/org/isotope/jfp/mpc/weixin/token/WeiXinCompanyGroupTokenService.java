package org.isotope.jfp.mpc.weixin.token;

import java.util.List;

import org.isotope.jfp.common.weixin.WeiXinCompanyDBO;
import org.isotope.jfp.common.weixin.WeixinService;
import org.isotope.jfp.framework.common.CommonChannelConfig;
import org.isotope.jfp.mpc.weixin.beans.WeiXinCompanyGroupReceverBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinCompanyGroupTokenBean;
import org.springframework.stereotype.Service;

/**
 * 微信企业Token对接
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
@Service
public class WeiXinCompanyGroupTokenService extends CommonChannelConfig implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	WeixinService WeixinService_;
	List<WeiXinCompanyDBO> list;

	/**
	 * 加载企业Token
	 */
	public WeiXinCompanyGroupTokenBean loadCompanyGroupToken(WeiXinCompanyGroupReceverBean group) {
		if(group==null)return null;
		WeiXinCompanyGroupTokenBean comanyGroupToken = null;
		return comanyGroupToken;
	}
}
