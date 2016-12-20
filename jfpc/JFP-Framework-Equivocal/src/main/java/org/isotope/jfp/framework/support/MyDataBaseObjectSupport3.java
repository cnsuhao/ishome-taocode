package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.constants.ISDBConstants;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;

/**
 * 数据持久层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class MyDataBaseObjectSupport3 extends MyDataBaseObjectSupport implements ISDBConstants {
	/**
	 * 拦截创建信息
	 */
	public void prepareCreator(String creator) {
		// Timestamp d = new Timestamp(System.currentTimeMillis());
		String t = DateHelper.currentTimeMillis2();
		if (EmptyHelper.isEmpty(getCreateTime()))
			setCreateTime(t);
		if (EmptyHelper.isEmpty(getCreator()))
			setCreator(creator);
	}

	/**
	 * 拦截更新信息
	 */
	public void prepareUpdator(String updator) {
		String t = DateHelper.currentTimeMillis2();
		if (EmptyHelper.isEmpty(getUpdateTime()))
			setUpdateTime(t);
		if (EmptyHelper.isEmpty(getUpdator()))
			setUpdator(updator);
	}
	
	/**
	 * 创建时间
	 */
	private String createTime = null;

	/**
	 * 创建者
	 */
	private String creator = null;

	/**
	 * 更新时间
	 */
	private String updateTime = null;

	/**
	 * 最后更新者
	 */
	private String updator = null;

	/**
	 * 获取创建时间
	 *
	 * @return Create_time 创建时间
	 */
	public String getCreateTime() {
		return this.createTime;
	}

	/**
	 * 获取创建者
	 *
	 * @return Creator 创建者
	 */
	public String getCreator() {
		return this.creator;
	}

	/**
	 * 获取更新时间
	 *
	 * @return Update_time 更新时间
	 */
	public String getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 获取最后更新者
	 *
	 * @return Updator 最后更新者
	 */
	public String getUpdator() {
		return this.updator;
	}

	/**
	 * 设置创建时间
	 *
	 * @param Create_time
	 *            创建时间
	 */
	public void setCreateTime(String createtime) {
		this.createTime = createtime;
	}

	/**
	 * 设置创建者
	 *
	 * @param Creator
	 *            创建者
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * 设置更新时间
	 *
	 * @param Update_time
	 *            更新时间
	 */
	public void setUpdateTime(String updatetime) {
		this.updateTime = updatetime;
	}

	/**
	 * 设置最后更新者
	 *
	 * @param Updator
	 *            最后更新者
	 */
	public void setUpdator(String updator) {
		this.updator = updator;
	}

}
