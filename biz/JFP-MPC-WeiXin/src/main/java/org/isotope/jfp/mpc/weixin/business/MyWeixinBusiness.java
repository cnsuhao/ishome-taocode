package org.isotope.jfp.mpc.weixin.business;

import javax.annotation.Resource;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.mpc.weixin.beans.WeiXinCompanyGroupReceverBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinCompanySenderBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinMessageValueBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinUserReceverBean;
import org.isotope.jfp.mpc.weixin.token.WeiXinCompanyGroupTokenService;
import org.isotope.jfp.mpc.weixin.token.WeiXinCompanyTokenService;
import org.isotope.jfp.mpc.weixin.token.WeiXinUserTokenService;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinCompanyGroupTokenBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinCompanyTokenBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinUserTokenBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeixinMeassageTokenBean;
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
	WeiXinCompanyTokenService weixinCompanyTokenService;// 企业Token
	@Resource
	WeiXinCompanyGroupTokenService weixinCompanyGroupTokenService;
	@Resource
	WeiXinUserTokenService weixinUserTokenService;// 用户Token

	public void init() {
	}

	/**
	 * 消息发送
	 * 
	 * @param messageValue
	 * @param comany
	 * @param group
	 *            接收用户组，与User是二选一关系
	 * @param user
	 *            接收用户，与Group是二选一关系
	 */

	public String sendText(WeiXinMessageValueBean messageValue, WeiXinCompanySenderBean sender, WeiXinCompanyGroupReceverBean groupRecever, WeiXinUserReceverBean userRecever) {
		// 获得微信Token信息
		WeixinMeassageTokenBean message = new WeixinMeassageTokenBean();
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		WeiXinCompanyGroupTokenBean group = weixinCompanyGroupTokenService.loadCompanyGroupToken(groupRecever);
		if (group != null) {

		}
		WeiXinUserTokenBean user = weixinUserTokenService.loadUserToken(userRecever);
		if (user != null) {

		}

		return ZERO;
	}

	public String sendImage(WeiXinMessageValueBean messageValue, WeiXinCompanySenderBean sender, WeiXinCompanyGroupReceverBean groupRecever, WeiXinUserReceverBean userRecever) {
		// 获得微信Token信息
		WeixinMeassageTokenBean message = new WeixinMeassageTokenBean();
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		WeiXinCompanyGroupTokenBean group = weixinCompanyGroupTokenService.loadCompanyGroupToken(groupRecever);
		TxWeixinService txWeixinService = new TxWeixinService(comany);
		if (group != null) {

		}
		WeiXinUserTokenBean user = weixinUserTokenService.loadUserToken(userRecever);
		if (user != null) {

		}

		return ZERO;
	}

	public String sendVoice(WeiXinMessageValueBean messageValue, WeiXinCompanySenderBean sender, WeiXinCompanyGroupReceverBean groupRecever, WeiXinUserReceverBean userRecever) {
		// 获得微信Token信息
		WeixinMeassageTokenBean message = new WeixinMeassageTokenBean();
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		WeiXinCompanyGroupTokenBean group = weixinCompanyGroupTokenService.loadCompanyGroupToken(groupRecever);
		if (group != null) {

		}
		WeiXinUserTokenBean user = weixinUserTokenService.loadUserToken(userRecever);
		if (user != null) {

		}

		return ZERO;
	}

	public String sendVideo(WeiXinMessageValueBean messageValue, WeiXinCompanySenderBean sender, WeiXinCompanyGroupReceverBean groupRecever, WeiXinUserReceverBean userRecever) {
		// 获得微信Token信息
		WeixinMeassageTokenBean message = new WeixinMeassageTokenBean();
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		WeiXinCompanyGroupTokenBean group = weixinCompanyGroupTokenService.loadCompanyGroupToken(groupRecever);
		if (group != null) {

		}
		WeiXinUserTokenBean user = weixinUserTokenService.loadUserToken(userRecever);
		if (user != null) {

		}

		return ZERO;
	}

	public String sendThumb(WeiXinMessageValueBean messageValue, WeiXinCompanySenderBean sender, WeiXinCompanyGroupReceverBean groupRecever, WeiXinUserReceverBean userRecever) {
		// 获得微信Token信息
		WeixinMeassageTokenBean message = new WeixinMeassageTokenBean();
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		WeiXinCompanyGroupTokenBean group = weixinCompanyGroupTokenService.loadCompanyGroupToken(groupRecever);
		if (group != null) {

		}
		WeiXinUserTokenBean user = weixinUserTokenService.loadUserToken(userRecever);
		if (user != null) {

		}

		return ZERO;
	}

	public String sendFile(WeiXinMessageValueBean messageValue, WeiXinCompanySenderBean sender, WeiXinCompanyGroupReceverBean groupRecever, WeiXinUserReceverBean userRecever) {
		// 获得微信Token信息
		WeixinMeassageTokenBean message = new WeixinMeassageTokenBean();
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		WeiXinCompanyGroupTokenBean group = weixinCompanyGroupTokenService.loadCompanyGroupToken(groupRecever);
		if (group != null) {

		}
		WeiXinUserTokenBean user = weixinUserTokenService.loadUserToken(userRecever);
		if (user != null) {

		}

		return ZERO;
	}

}
