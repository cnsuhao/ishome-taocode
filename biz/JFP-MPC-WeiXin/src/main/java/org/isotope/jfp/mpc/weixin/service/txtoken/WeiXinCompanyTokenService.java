package org.isotope.jfp.mpc.weixin.service.txtoken;

import java.util.HashMap;
import java.util.List;

import org.isotope.jfp.common.weixin.WeiXinCompanyDBO;
import org.isotope.jfp.common.weixin.WeixinService;
import org.isotope.jfp.framework.common.CommonChannelConfig;

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
	public void loadCompanyConfig() {
		HashMap<String, String> comyany = new HashMap<String, String>();
		list = WeixinService_.loadCompany(comyany);
	}
}
