package org.isotope.jfp.mcws.search;

/**
 * 企业信息检索
 * 
 * @author 001745
 *
 */
public interface ISCompanyInfoSearch {

	/**
	 * 检索Key队列
	 */
	public static final String COMPANY_INFO = "COMPANY:INFO";

	/**
	 * 保存网页
	 * 
	 * @return
	 */
	boolean saveCompanyInfo(String jobId, String html);

	/**
	 * 获得一个检索企业
	 * 
	 * @return
	 */
	Object getCompanyName();

	/**
	 * 获得一个区域URL
	 * 
	 * @return
	 */
	Object getAreaUrl(String areaCode);
}
