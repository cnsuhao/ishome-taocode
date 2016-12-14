package com.aek56.qt.credentials.dao;

import java.util.List;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.page.PageVOSupport;
import org.jfpc.framework.support.ISDatabaseSupport;
import org.jfpc.framework.support.MyDataBaseObjectSupport;

/**
 * 供应商厂家维护
 * 
 * @author zhengxw
 * 
 */
public interface CredentialsDao extends ISDatabaseSupport {
	/**
	 * 手动统计（客户关系）
	 * 
	 * @param formParam
	 * @return
	 */
	public int totalCustomCredentials(MyDataBaseObjectSupport formParam);
	
	/**
	 * 供应商查看证件总数
	 * 
	 * @param puk
	 * @return
	 */
	public FrameworkDataBean countNumBySupplier(String puk);	
	
	/**
	 * 医院查看证件总数
	 * 
	 * @param puk
	 * @return
	 */
	public FrameworkDataBean countNumByHospital(String puk);
	
	/**
	 * 全体查询（供应商查看证件一览）
	 * 
	 * @param formParam
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageCompanyCredentialsList(PageVOSupport formParam);

	/**
	 * 全体查询（供应商查看证件一览）
	 * 
	 * @param formParam
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageCompanyCredentialsList(MyDataBaseObjectSupport paramBean);

	/**
	 * 全体查询（医院查看证件一览）
	 * 
	 * @param formParam
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageHospitalCredentialsList(PageVOSupport formParam);

	/**
	 * 全体查询（医院查看证件一览）
	 * 
	 * @param formParam
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageHospitalCredentialsList(MyDataBaseObjectSupport paramBean);
	
	/**
	 * 医疗器械注册证 已过期 短信提醒(手动)
	 * 
	 * @param usr
	 * @return
	 */
	public List<FrameworkDataBean> sendSmsOnCredentialsBySystem(MyDataBaseObjectSupport paramBean);
	
	/**
	 * 短信提醒忽略（医院）
	 * 
	 * @param usr
	 * @return
	 */
	public int doUpdateCredentials(MyDataBaseObjectSupport paramBean);

	/**
	 * 我的客户总数（医院）
	 * 
	 * @param usr
	 * @return
	 */
	public FrameworkDataBean countNumWithHospital(String puk);
	
	/**
	 * 我的客户总数（供应商）
	 * 
	 * @param usr
	 * @return
	 */
	public FrameworkDataBean countNumWithSupplier(String puk);
	
	
	
	/**
	 * 证件统计（医院）
	 * 
	 * @param usr
	 * @return
	 */
	public FrameworkDataBean countCredentialsNumWithHospital(String puk);
	
	/**
	 * 证件统计（供应商）
	 * 
	 * @param usr
	 * @return
	 */
	public FrameworkDataBean countCredentialsNumWithSupplier(String puk);

	/**
	 * 供应商授权书关联注册证信息
	 * 
	 * @param cid 注册证ID（业务主键）
	 * @return
	 */
	public List<FrameworkDataBean> loadCredentialsHistory(MyDataBaseObjectSupport paramBean);
	
	public List<FrameworkDataBean> getZczByIds(MyDataBaseObjectSupport paramBean);
	
	public List<FrameworkDataBean> getSqsByIds(MyDataBaseObjectSupport paramBean);
	
	public List<FrameworkDataBean> getCjyyzzByIds(MyDataBaseObjectSupport paramBean);
    
    public List<FrameworkDataBean> getCjscxkzByIds(MyDataBaseObjectSupport paramBean);

}
