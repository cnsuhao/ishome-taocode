package org.isotope.jfp.mpc.weixin.token;

import java.util.List;

import org.isotope.jfp.common.weixin.WeiXinCompanyDBO;
import org.isotope.jfp.common.weixin.WeixinService;
import org.isotope.jfp.framework.common.CommonChannelConfig;
import org.isotope.jfp.mpc.weixin.beans.WeiXinCompanySenderBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinCompanyTokenBean;

/**
 * 微信企业Token对接
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
public class WeiXinCompanyTokenService extends CommonChannelConfig implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	WeixinService WeixinService_;
	List<WeiXinCompanyDBO> list;

	/**
	 * 加载企业Token
	 */
	public WeiXinCompanyTokenBean loadCompanyConfig(WeiXinCompanySenderBean comany) {
		WeiXinCompanyTokenBean comanyToken = null;
		return comanyToken;
	}
}
