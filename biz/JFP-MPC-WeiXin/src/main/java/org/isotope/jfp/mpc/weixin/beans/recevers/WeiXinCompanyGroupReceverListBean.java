package org.isotope.jfp.mpc.weixin.beans.recevers;

import java.util.ArrayList;

import org.isotope.jfp.framework.beans.message.info.UserReceverBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyGroupReceverBean;

/**
 * 微信企业号部门信息
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 *
 */
public class WeiXinCompanyGroupReceverListBean extends UserReceverBean {
	ArrayList<WeiXinCompanyGroupReceverBean> recevers = new ArrayList<WeiXinCompanyGroupReceverBean>();

	public ArrayList<WeiXinCompanyGroupReceverBean> getRecevers() {
		return recevers;
	}

	public void setRecevers(ArrayList<WeiXinCompanyGroupReceverBean> recevers) {
		this.recevers = recevers;
	}
	public void addRecevers(WeiXinCompanyGroupReceverBean recever) {
		this.recevers.add(recever);
	}
}
