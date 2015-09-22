package com.ttnn.framework.ept.bean;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

import com.ttnn.framework.ept.util.Message;

/**
 * 
 * @author TTNN
 *
 */
public class ReportDataBean {
	String templateName;

	HashMap<String,Object> dateContainer;
	
	FileOutputStream fileOutputStream;
	FileInputStream fileInputStream;
	Message message;
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public HashMap<String, Object> getDateContainer() {
		return dateContainer;
	}

	public void setDateContainer(HashMap<String, Object> dateContainer) {
		this.dateContainer = dateContainer;
	}

	public FileOutputStream getFileOutputStream() {
		return fileOutputStream;
	}

	public void setFileOutputStream(FileOutputStream fileOutputStream) {
		this.fileOutputStream = fileOutputStream;
	}

	public FileInputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
	/**
	 * EXCEL读取操作
	 * @param templateName 模版ID
	 * @param dateContainer 返回数据容器
	 * @param fileInputStream 目标读取文件
	 */
	public void doRead(String templateName,HashMap<String,Object> dateContainer,FileInputStream fileInputStream){
		
	}
	
	/**
	 * EXCEL读取操作
	 */
	public void doRead(){
		doRead(templateName,dateContainer,fileInputStream);
	}
	
	/**
	 * EXCEL输出操作
	 * @param templateName  模版ID
	 * @param dateContainer 写入数据容器
	 * @param fileOutputStream 输出目标文件
	 */
	public void doWright(String templateName,HashMap<String,Object> dateContainer,FileOutputStream fileOutputStream){
		
	}
	/**
	 * EXCEL输出操作
	 */
	public void doWright(){
		doWright(templateName,dateContainer,fileOutputStream);
	}
}
