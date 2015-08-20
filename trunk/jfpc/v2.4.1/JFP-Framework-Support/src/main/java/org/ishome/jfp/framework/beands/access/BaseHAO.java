package org.ishome.jfp.framework.beands.access;

import org.ishome.jfp.framework.beands.pub.MobileBean;
import org.ishome.jfp.framework.utils.PKHelper;

/**
 * Hospital Access Object <br>
 * 对接属性父类
 * 
 * @author fucy
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
	 * 医院id
	 */
	private String hosId;

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
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
