package com.mcookies.qxy.biz.device;

/**
 * 卡片关联学生学习班级学校信息内容
 * 
 * @author 001745
 *
 */
public class CardInfoPVO {
	Long rfid;
	Long studentId;
	Long termId;
	Long cid;
	Long sid;

	public Long getRfid() {
		return rfid;
	}

	public void setRfid(Long rfid) {
		this.rfid = rfid;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

}
