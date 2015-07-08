package org.ishome.jfp.framework.beands.ext;

import org.ishome.jfp.framework.beands.FrameworkDataBean;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.utils.PKHelper;


/**
 * Hospital Access Object <br>
 * 对接属性父类
 * 
 * @author Spook
 * @version 2.0.0
 * @since 2.0.0 2015/02/06
 */
public class BaseHAO extends FrameworkDataBean implements ISFrameworkConstants {
	public BaseHAO(){
		makePuk();
	}
	
	/**
	 * 创建一个默认的主键
	 */
	public void makePuk(){
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

}
