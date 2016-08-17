package com.upg.zx.capture.bean;

import java.util.Date;

public class HtmlCaptureRsJob {
	//任务ID
	private String jobId;
	//区域码
	private String areaCode;
	//任务创建时间
	private Date createTime;
	
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public HtmlCaptureRsJob(){}
	
	public HtmlCaptureRsJob(String jobId,String areaCode,Date createTime){
		this.jobId = jobId;
		this.areaCode = areaCode;
		this.createTime = createTime;
	}
	/**
	 * 判断是否超时
	 * @param timeOut
	 * @return
	 */
	public boolean expire(long timeOut){
		boolean flag = false;
		long beginTime = createTime.getTime();
		long now = new Date().getTime();
		if(beginTime + timeOut < now){
			flag = true;
		}
		return flag;
	}
	
}
