package org.isotope.jfp.mpc.weixin.business;

import javax.annotation.Resource;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.mpc.weixin.beans.WeiXinMessageValueBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinCompanyGroupTokenBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinCompanyTokenBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinUserTokenBean;
import org.isotope.jfp.mpc.weixin.txapi.TxWeixinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 腾讯微信对接服务
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 * 
 */
public class MyWeixinBusiness implements ISFrameworkConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	TxWeixinService txWeixinService;

	public void sendText(WeiXinMessageValueBean message, WeiXinCompanyTokenBean comany, WeiXinCompanyGroupTokenBean group, WeiXinUserTokenBean user) {
		// TODO Auto-generated method stub
		
	}

	public void sendImage(WeiXinMessageValueBean message, WeiXinCompanyTokenBean comany, WeiXinCompanyGroupTokenBean group, WeiXinUserTokenBean user) {
		// TODO Auto-generated method stub
		
	}

	public void sendVoice(WeiXinMessageValueBean message, WeiXinCompanyTokenBean comany, WeiXinCompanyGroupTokenBean group, WeiXinUserTokenBean user) {
		// TODO Auto-generated method stub
		
	}

	public void sendVideo(WeiXinMessageValueBean message, WeiXinCompanyTokenBean comany, WeiXinCompanyGroupTokenBean group, WeiXinUserTokenBean user) {
		// TODO Auto-generated method stub
		
	}

	public void sendThumb(WeiXinMessageValueBean message, WeiXinCompanyTokenBean comany, WeiXinCompanyGroupTokenBean group, WeiXinUserTokenBean user) {
		// TODO Auto-generated method stub
		
	}

	public void sendFile(WeiXinMessageValueBean message, WeiXinCompanyTokenBean comany, WeiXinCompanyGroupTokenBean group, WeiXinUserTokenBean user) {
		// TODO Auto-generated method stub
		
	}
	
}
