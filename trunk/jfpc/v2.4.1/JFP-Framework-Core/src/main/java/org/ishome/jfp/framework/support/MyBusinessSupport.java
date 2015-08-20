package org.ishome.jfp.framework.support;

import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.constants.pub.ISModelConstants;
import org.ishome.jfp.framework.utils.EmptyHelper;

/**
 * 数据业务操作接口定义超类<br>
 * 定义通用8个操作方法<br>
 * 缓存参考SSM配置
 * 
 * @author Spook
 * @since 0.1.0 2013-8-21
 * @version 0.1.0
 */
public class MyBusinessSupport implements ISFrameworkConstants, ISModelConstants {

	protected Object message;// 业务请求参数数据

	/**
	 * 返回结果(0：成功、其他：失败（业务系统提示码）)
	 */
	protected String returnCode = ZERO;//

	/**
	 * 提示信息
	 */
	protected String returnMessage = MESSAGE_ERROR_SYNC;//
	/**
	 * 接口返回的数据
	 */
	protected Object returnObject = EMPTY;

	/**
	 * 业务请求API key 名称
	 */
	private String bizName;

	/**
	 * 业务请求版本号
	 */
	private String version;

	/**
	 * 业务请求ID
	 * 
	 * @return
	 */
	private String operationId;

	////////////////////////////////////////////////////////////////////////
	private String companyId;

	private String userId;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private boolean encryType = false;

	public boolean isEncryType() {
		return encryType;
	}

	public void setEncryType(boolean encryType) {
		this.encryType = encryType;
	}

	////////////////////////////////////////////////////////////////////////

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public Object getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(Object returnObject) {
		if (EmptyHelper.isEmpty(returnObject)) {
			this.returnObject = EMPTY;
		} else {
			this.returnObject = returnObject;
		}
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public String getBizName() {
		if (EmptyHelper.isEmpty(bizName))
			bizName = this.getClass().getSimpleName();
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

}
