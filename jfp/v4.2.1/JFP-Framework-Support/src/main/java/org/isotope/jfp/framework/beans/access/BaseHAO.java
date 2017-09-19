package org.isotope.jfp.framework.beans.access;

import org.isotope.jfp.framework.beans.pub.MobileBean;
import org.isotope.jfp.framework.utils.PKHelper;

/**
 * Hospital Access Object <br>
 * 对接属性父类
 * 
 * @author Spook
 * @version 2.0.2
 * @since 2.0.2 2015/02/06
 */
public class BaseHAO extends MobileBean {

	/**
	 * 当前页
	 */
	private Integer pageCurrent = 0;

	/**
	 * 每页条数
	 */
	private Integer pageLimit = 0;

	public BaseHAO() {
		makePuk();
	}

	/**
	 * 创建一个默认的主键
	 */
	public void makePuk() {
		super.setPuk(PKHelper.creatPUKey());
	}

	/**
	 * 企业id
	 */
	private String companyId;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * 内部业务名称（用于在接口不变的场合驱动其他业务）
	 */
	private String serviceName;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(Integer pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public Integer getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(Integer pageLimit) {
		this.pageLimit = pageLimit;
	}

}
