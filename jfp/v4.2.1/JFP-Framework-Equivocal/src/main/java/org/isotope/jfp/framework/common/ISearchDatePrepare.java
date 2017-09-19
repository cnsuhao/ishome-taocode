package org.isotope.jfp.framework.common;

/**
 * 日期属性补全接口定义
 * @author Spook
 * @version 4.1.3 2017/04/15
 * @version 4.1.1 2016/12/12
 * @version 3.2.1 2016/08/28
 * @since 3.2.1 2016/08/28
 *
 */
public interface ISearchDatePrepare {

	public String getStartDate();

	public void setStartDate(String startDate);

	public String getEndDate();

	public void setEndDate(String endDate);
}
