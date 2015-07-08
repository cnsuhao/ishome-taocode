package org.jfpc.base.page;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.jfpc.base.bean.FrameworkDataBean;
import org.jfpc.base.mybatis.plugin.dialect.DefaultDialect;



/**
 * 分页数据实体超类
 * <p>
 * 需要定义@CacheKeyMethod标签来为实体指定唯一Key值，完成缓存
 * 
 * @since 0.1 2012-7-13
 * @version 0.1
 */
public class PageVOSupport {
	private int pageCurrent=0;
	private int pageCount=0;
	private int pageLimit=0;
	private int resultCount=0;	

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


	public List<FrameworkDataBean> getObjectData() {
		return objectData;
	}


	public void setObjectData(List<FrameworkDataBean> objectData) {
		this.objectData = objectData;
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public int getPageCount() {
		if (resultCount % pageLimit == 0)
			pageCount = resultCount / pageLimit;
		else //if (ResultCount % PageLimit != 0)
			pageCount = resultCount / pageLimit + 1;

		return pageCount;
	}
	/**
	 * 页面参数
	 */
	private FrameworkDataBean formParamBean;
	
	public FrameworkDataBean getFormParamBean() {
		return formParamBean;
	}


	public void setFormParamBean(FrameworkDataBean formParamBean) {
		this.formParamBean = formParamBean;
	}

	/**
	 * 当前页面提示信息
	 */
	//private List<PageMessage> PageMessages;
	
	private List<FrameworkDataBean> objectData;

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
	
	public DefaultDialect getDefaultDialect() {
		return defaultDialect;
	}

	public void setDefaultDialect(DefaultDialect defaultDialect) {
		this.defaultDialect = defaultDialect;
	}
}
