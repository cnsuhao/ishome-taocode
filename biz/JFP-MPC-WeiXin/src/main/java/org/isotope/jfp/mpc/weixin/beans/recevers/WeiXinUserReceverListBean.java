package org.isotope.jfp.mpc.weixin.beans.recevers;

import java.util.ArrayList;

import org.isotope.jfp.framework.beans.message.info.UserReceverBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinUserReceverBean;

/**
 * 微信用户信息
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 *
 */
public class WeiXinUserReceverListBean extends UserReceverBean {
	ArrayList<WeiXinUserReceverBean> recevers = new ArrayList<WeiXinUserReceverBean>();

	public ArrayList<WeiXinUserReceverBean> getRecevers() {
		return recevers;
	}

	public void setRecevers(ArrayList<WeiXinUserReceverBean> recevers) {
		this.recevers = recevers;
	}
	public void addRecevers(WeiXinUserReceverBean recever) {
		this.recevers.add(recever);
	}
}
