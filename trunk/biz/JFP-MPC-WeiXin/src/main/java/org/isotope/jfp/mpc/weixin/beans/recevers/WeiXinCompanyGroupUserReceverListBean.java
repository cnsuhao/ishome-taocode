package org.isotope.jfp.mpc.weixin.beans.recevers;

import java.util.ArrayList;

import org.isotope.jfp.framework.beans.message.info.UserReceverBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyGroupUserReceverBean;

/**
 * 微信用户信息
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 *
 */
public class WeiXinCompanyGroupUserReceverListBean extends UserReceverBean {
	ArrayList<WeiXinCompanyGroupUserReceverBean> recevers = new ArrayList<WeiXinCompanyGroupUserReceverBean>();

	public ArrayList<WeiXinCompanyGroupUserReceverBean> getRecevers() {
		return recevers;
	}

	public void setRecevers(ArrayList<WeiXinCompanyGroupUserReceverBean> recevers) {
		this.recevers = recevers;
	}

	public void addRecevers(WeiXinCompanyGroupUserReceverBean recever) {
		this.recevers.add(recever);
	}
}
