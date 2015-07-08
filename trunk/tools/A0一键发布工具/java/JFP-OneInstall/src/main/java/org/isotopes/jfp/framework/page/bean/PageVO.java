package org.isotopes.jfp.framework.page.bean;

import java.util.List;

import javax.annotation.Resource;

import org.isotopes.jfp.framework.mybatis.plugin.dialect.DefaultDialect;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


/**
 * 分页对象Bean
 * 
 * @author TTNN
 */
@Component
@Scope(value = "request",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class PageVO {
	/**
	 * 自定义排序
	 */
	private String orderby ="";
	
	/**
	 * 自定义排序
	 * @return
	 */
	public String getOrderby() {
		return orderby;
	}
	/**
	 * 自定义排序
	 * @return
	 */
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	/**
	 * 当前使用分页功能
	 */
	@Resource
	private DefaultDialect defaultDialect;
	// -------------------------------------------
	/**
	 * 总页数
	 */
	private int pageCount;
	/**
	 * 当前页码
	 */
	private int pageCurrent = 1;
	/**
	 * 每页数目
	 */
	private int pageLimit = 10;
	/**
	 * 总记录数
	 */
	private int resultCount;
	
	// -----------页面相关-----------------------
	/**
	 * 当前页面提示信息
	 */
	private List<PageMessage> PageMessages;

	// -----------数据相关-----------------------
	/**
	 * 当前页面查询结果数据/查询操作参数
	 */
	private Object pageData;	
	
	public List<PageMessage> getPageMessages() {
		return PageMessages;
	}

	public void setPageMessages(List<PageMessage> pageMessages) {
		PageMessages = pageMessages;
	}

	public void addPageMessage(PageMessage pageMessage) {
		PageMessages.add(pageMessage);
	}

	public int getPageCount() {
		if (resultCount % pageLimit == 0)
			pageCount = resultCount / pageLimit;
		else //if (ResultCount % PageLimit != 0)
			pageCount = resultCount / pageLimit + 1;	

		return pageCount;
	}
	
	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public int getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public DefaultDialect getDefaultDialect() {
		return defaultDialect;
	}

	public void setDefaultDialect(DefaultDialect defaultDialect) {
		this.defaultDialect = defaultDialect;
	}

	public Object getPageData() {
		return pageData;
	}

	public void setPageData(Object pageData) {
		this.pageData = pageData;
	}


}
