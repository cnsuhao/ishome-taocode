package org.isotope.jfp.framework.beans.page;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.isotope.jfp.framework.beans.ObjectBean;
import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.mybatis.plugin.dialect.DefaultDialect;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 分页数据实体超类
 * <p>
 * 需要定义@CacheKeyMethod标签来为实体指定唯一Key值，完成缓存
 * 
 * @author Spook
 * @since 0.1 2012-7-13
 * @version 0.1
 */
@Component("PageModel")
// @Scope("prototype")
// @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PageModel<T> extends ObjectBean implements ISFrameworkConstants {
	/**
	 * 当前页码
	 */
	protected int pageCurrent;
	/**
	 * 每页显示条数
	 */
	protected int pageLimit;
	////////////////////////////////
	/**
	 * 总页数(计算获得)
	 */
	protected int pageCount;
	/**
	 * 总条数(计算获得)
	 */
	protected int resultCount;
	protected boolean resultCountFlag = false;
	protected String countSQL = "count(1)";

	/**
	 * 页面一览数据
	 */
	protected List<T> pageListData;

	/**
	 * 页面检索参数
	 */
	protected FrameworkDataBean formParamBean;

	/**
	 * 自定义排序
	 */
	protected String orderby = EMPTY;

	public PageModel() {
		config();
	}

	public void config() {
		pageCurrent = 0;
		pageCount = 0;
		pageLimit = Integer.MAX_VALUE;
		resultCount = 0;
		pageListData = null;
		formParamBean = new FrameworkDataBean();
		defaultDialect = BeanFactoryHelper.getBean("IDialectInterceptor");
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

	public boolean currentResultCountFlag() {
		return resultCountFlag;
	}

	public void setResultCountFlag(boolean resultCountFlag) {
		this.resultCountFlag = resultCountFlag;
	}

	public String currentCountSQL() {
		return countSQL;
	}

	public void setCountSQL(String countSQL) {
		this.countSQL = countSQL;
	}

	public List<T> getPageListData() {
		return pageListData;
	}

	public void setPageListData(List<T> objectData) {
		this.pageListData = objectData;
	}

	public int getPageCount() {
		if (pageLimit == 0)
			return 0;
		if (resultCount % pageLimit == 0)
			pageCount = resultCount / pageLimit;
		else
			// if (ResultCount % PageLimit != 0)
			pageCount = resultCount / pageLimit + 1;

		return pageCount;
	}

	public FrameworkDataBean currentFormParamBean() {
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
	public String currentOrderby() {
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

	public DefaultDialect currentDefaultDialect() {
		if (defaultDialect == null) {
			defaultDialect = BeanFactoryHelper.getBean("IDialectInterceptor");// new
																				// DefaultDialect();
		}
		return defaultDialect;
	}

	public void setDefaultDialect(DefaultDialect defaultDialect) {
		this.defaultDialect = defaultDialect;
	}
}
