package org.isotope.jfp.mpc.weixin.txapi;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.chanjar.weixin.cp.api.WxCpConfigStorage;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;

/**
 * 腾讯微信对接服务
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 * 
 */
public class TxWeixinService extends WxCpServiceImpl implements ISFrameworkConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public TxWeixinService(WxCpConfigStorage wxConfigProvider) {
		super.setWxCpConfigStorage(wxConfigProvider);
	}

}
