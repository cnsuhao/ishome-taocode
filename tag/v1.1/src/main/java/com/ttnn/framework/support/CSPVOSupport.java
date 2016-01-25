package com.ttnn.framework.support;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageMessage;

/**
 * 数据实体超类
 * <p>
 * 需要定义@CacheKeyMethod标签来为实体指定唯一Key值，完成缓存
 * 
 * @since 0.1 2012-7-13
 * @version 0.1
 */
public class CSPVOSupport extends FrameworkDataBean {
	private String pageCurrent="1";
	private String pagecount="0";
	private String pagelimit="0";
	private String totalcount="0";	

	/**
	 * 当前页面提示信息
	 */
	private List<PageMessage> PageMessages;
	
	private List<String> list;

	public List<String> getList() {
		return list;
	}


	public void setList(List<String> list) {
		this.list = list;
	}


	public String getPageCurrent() {
		return pageCurrent;
	}


	public void setPageCurrent(String pageCurrent) {
		this.pageCurrent = pageCurrent;
	}


	public String getPagecount() {
		return pagecount;
	}


	public void setPagecount(String pagecount) {
		this.pagecount = pagecount;
	}


	public String getPagelimit() {
		return pagelimit;
	}


	public void setPagelimit(String pagelimit) {
		this.pagelimit = pagelimit;
	}


	public String getTotalcount() {
		return totalcount;
	}


	public void setTotalcount(String totalcount) {
		this.totalcount = totalcount;
	}

	private String token = "";
	

	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4295790946358297004L;
}
