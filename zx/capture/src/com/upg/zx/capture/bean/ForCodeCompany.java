package com.upg.zx.capture.bean;
/**
 * 待打码企业
 * @author 001362
 *
 */
public class ForCodeCompany {
	//企业名称
	private String corpName;
	//区域码
	private String areaCode;
	//失败次数
	private int failCount;
	
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public int getFailCount() {
		return failCount;
	}
	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}
	
	
}
