package org.isotope.jfp.framework.beans.message.info;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;

/**
 * 消息接收者
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 *
 */
public class UserReceverBean extends FrameworkDataBean {
	protected String receverType = this.getClass().getSimpleName();

	public static void main(String[] args) throws Exception {
		UserReceverBean a = new UserReceverBean();
		System.out.println(a.receverType);
	}

	public String getReceverType() {
		return receverType;
	}

}
