package com.aek56.atm.company.cmp;

import java.util.List;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.page.PageVOSupport;
import org.jfpc.framework.support.ISDatabaseSupport;

/**
 * 企业公共信息
 * @author Administrator
 *
 */
public interface CompanyDao extends ISDatabaseSupport {
	/**
	 * 我的客户总数（供应商）
	 * 
	 * @param usr
	 * @return
	 */
	public FrameworkDataBean loadMyMessage(String puk);
	/**
	 * 全体查询（医院查看证件一览）
	 * 
	 * @param formParam
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageHospitalCredentialsList(PageVOSupport formParam);

}
