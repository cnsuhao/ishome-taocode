package org.isotope.jfp.framework.beands.page;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.ishome.jfp.framework.beands.ObjectBean;
import org.ishome.jfp.framework.beands.common.FrameworkDataBean;
import org.isotope.jfp.framework.mybatis.plugin.dialect.DefaultDialect;
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
// @Scope("prototype")
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PageVOSupport extends ObjectBean {
	/**
	 * 当前页码
	 */
	protected int pageCurrent;
	/**
	 * 每页显示条数
	 */
	protected int pageLimit;
	//////////////////////////////////////////////////////////////
	/**
	 * 总页数(计算获得)
	 */
	private int pageCount;
	/**
	 * 总条数(计算获得)
	 */
	private int resultCount;

	/**
	 * 页面一览数据
	 */
	private List<FrameworkDataBean> pageListData;

	/**
	 * 页面检索参数
	 */
	private FrameworkDataBean formParamBean;

	/**
	 * 自定义排序
	 */
	private String orderby = "";

	public PageVOSupport() {

	}

	public void config() {
		pageCurrent = 0;
		pageCount = 0;
		pageLimit = Integer.MAX_VALUE;
		resultCount = 0;
		pageListData = null;
		formParamBean = new FrameworkDataBean();
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
		if (defaultDialect == null) {
			defaultDialect = new DefaultDialect();
		}
		return defaultDialect;
	}

	public void setDefaultDialect(DefaultDialect defaultDialect) {
		this.defaultDialect = defaultDialect;
	}
}
