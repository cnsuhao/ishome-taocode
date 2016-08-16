package com.mcookies.qxy.biz.device;

public class SendTimecardDataPVO {
	String method;
	Long DevID;// 设备序列号
	String EnterpriseID;// 企业id
	String Version;// 版本号

	String datas;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Long getDevID() {
		return DevID;
	}

	public void setDevID(Long devID) {
		DevID = devID;
	}

	public String getEnterpriseID() {
		return EnterpriseID;
	}

	public void setEnterpriseID(String enterpriseID) {
		EnterpriseID = enterpriseID;
	}

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public String getDatas() {
		return datas;
	}

	public void setDatas(String datas) {
		this.datas = datas;
	}

}
