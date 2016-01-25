package org.jfpc.framework.page;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.mybatis.plugin.dialect.DefaultDialect;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * 分页数据实体超类
 * <p>
 * 需要定义@CacheKeyMethod标签来为实体指定唯一Key值，完成缓存
 * 
 * @since 0.1 2012-7-13
 * @version 0.1
 */
@Component
//@Scope("prototype")
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PageVOSupport {
	/**
	 * 当前页码
	 */
	private int pageCurrent = 0;
	/**
	 * 总页数
	 */
	private int pageCount = 0;
	/**
	 * 每页显示条数
	 */
	private int pageLimit = Integer.MAX_VALUE;
	/**
	 * 总条数
	 */
	private int resultCount = 0;
	
	/**
	 * 页面一览数据
	 */
	private List<FrameworkDataBean> pageListData;

	/**
	 * 页面检索参数
	 */
	private FrameworkDataBean formParamBean = new MyDataBaseObjectSupport();


	/**
	 * 当前页面提示信息
	 */
	// private List<PageMessage> PageMessages;

	private String token = "";


	/**
	 * 自定义排序
	 */
	private String orderby = "";

	public void config(){
		pageCurrent = 0;
		pageCount = 0;
		pageLimit = Integer.MAX_VALUE;
		resultCount = 0;
		pageListData = null;
		formParamBean = new FrameworkDataBean();
		token = "";
		orderby = "";
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

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public List<FrameworkDataBean> getPageListData() {
		return pageListData;
	}

	public void setPageListData(List<FrameworkDataBean> objectData) {
		this.pageListData = objectData;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageCount() {
		if (resultCount % pageLimit == 0)
			pageCount = resultCount / pageLimit;
		else
			// if (ResultCount % PageLimit != 0)
			pageCount = resultCount / pageLimit + 1;

		return pageCount;
	}
	
	public FrameworkDataBean getFormParamBean() {
		return formParamBean;
	}

	public void setFormParamBean(FrameworkDataBean formParamBean) {
		this.formParamBean = formParamBean;
	}
	
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
	 * 自定义排序
	 * 
	 * @return
	 */
	public String getOrderby() {
		return orderby;
	}

	/**
	 * 自定义排序
	 * 
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

	public DefaultDialect getDefaultDialect() {
		return defaultDialect;
	}

	public void setDefaultDialect(DefaultDialect defaultDialect) {
		this.defaultDialect = defaultDialect;
	}
}
