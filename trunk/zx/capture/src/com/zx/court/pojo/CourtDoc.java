package com.zx.court.pojo;

import java.io.Serializable;

public class CourtDoc implements Serializable {
	
	/**
	 * 法院
	 */
	private String strCourt;
	
	/**
	 * Title
	 */
	private String strTitle;
	
	/**
	 * Link
	 */
	private String strLink;
	
	/**
	 * 案号
	 */
	private String caseNo;
	
	/**
	 * 内容
	 */
	private String strContent;
	
	/**
	 * 抓取日期
	 */
	private String createTime ;
	
	/**
	 * 上传日期
	 */
	private String depolyTime ;
	

	/**
	 * 来源
	 */
	private String dataFrom ;
	
	/**
	 * 文书案号
	 */
	private String docId;
	
	/**
	 * 省
	 */
	private String province;
	
	private String docJson;
	
	
	public String getDocJson() {
		return docJson;
	}

	public void setDocJson(String docJson) {
		this.docJson = docJson;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 页数
	 */
	private String page ;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getStrCourt() {
		return strCourt;
	}

	public void setStrCourt(String strCourt) {
		this.strCourt = strCourt;
	}

	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

	public String getStrLink() {
		return strLink;
	}

	public void setStrLink(String strLink) {
		this.strLink = strLink;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public String getStrContent() {
		return strContent;
	}

	public void setStrContent(String strContent) {
		this.strContent = strContent;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}
	
	public String getDepolyTime() {
		return depolyTime;
	}

	public void setDepolyTime(String depolyTime) {
		this.depolyTime = depolyTime;
	}
	
}
