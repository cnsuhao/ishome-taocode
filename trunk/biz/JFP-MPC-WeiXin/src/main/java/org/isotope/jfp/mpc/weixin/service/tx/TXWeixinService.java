package org.isotope.jfp.mpc.weixin.service.tx;

import java.io.File;
import java.util.List;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.mpc.weixin.beans.WeiXinMessageValueBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
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
public class TXWeixinService extends WxCpServiceImpl implements ISFrameworkConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	public void setWxCpConfigStorage(WxCpConfigStorage wxConfigProvider) {
		super.setWxCpConfigStorage(wxConfigProvider);
	}
	
	public WxMediaUploadResult mediaUpload(String mediaType, File file) throws WxErrorException {
		return super.mediaUpload( mediaType,  file);
	}
	
}
