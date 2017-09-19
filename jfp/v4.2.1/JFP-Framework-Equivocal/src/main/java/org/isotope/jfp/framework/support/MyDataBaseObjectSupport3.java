package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.cache.session.SessionHelper;
import org.isotope.jfp.framework.common.MyDataBaseExtSupport;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;

/**
 * 数据持久层超类
 * 
 * @author Spook
 * @version 4.1.3 2017/04/15
 * @version 4.1.1 2016/12/12
 * @version 3.2.1 2016/08/28
 * @since 3.2.1 2016/08/28
 */
public class MyDataBaseObjectSupport3 extends MyDataBaseExtSupport {

	/**
	 * @see <MyDataBaseOperateSupport#doInsert(MyDataBaseObjectSupport , int )>
	 * @deprecated
	 */
	public void loadDefauft() {
		this.preparePuk();
		this.prepareNumeric();
	}

	// 0无视1默认2拦截
	public void prepareHdp(int flag) {
		if (flag == 0) {
			return;
		}
		if (EmptyHelper.isEmpty(getHdp())){
			if (SessionHelper.getSessionAttribute() == null)
				this.setHdp(SYSTEM);
			else  {
				this.setHdp(currentUser().getHdp());
			}
		}
	}

	/**
	 * 拦截有效标记信息
	 */
	public void prepareDeleteFlag(int flag) {
		if (flag == 0) {
			return;
		}
		if (EmptyHelper.isEmpty(getDelFlag())) {
			if (flag == 1) {
				this.setDelFlag(ZERO);
			} else if (flag == 2) {
				this.setDelFlag(ONE);
			}
		}
	}

	/**
	 * 拦截创建信息
	 */
	@Override
	public void prepareCreator(int flag) {
		// Timestamp d = new Timestamp(System.currentTimeMillis());
		String t = DateHelper.currentTimeMillisCN1();

		if (EmptyHelper.isEmpty(getCreateTime()))
			setCreateTime(t);

		if (EmptyHelper.isEmpty(getCreator())) {
			if (flag == 0 || flag == 1)
				setCreator(SYSTEM);
			else if (flag == 2) {
				setCreator(currentUser().getUserId());
			}
		}
	}

	/**
	 * 拦截更新信息
	 */
	@Override
	public void prepareUpdator(int flag) {
		if (flag == 0) {
			return;
		}
		String t = DateHelper.currentTimeMillisCN1();

		setUuu(t);// 设置最新时间

		if (EmptyHelper.isEmpty(getUpdateTime())) {
			setUpdateTime(t);
		}

		if (EmptyHelper.isEmpty(getUpdator())) {
			if (flag == 1)
				setUpdator(SYSTEM);
			else if (flag == 2)
				setUpdator(currentUser().getUserId());
		}
	}

	/**
	 * 系统标识
	 */
	private String hdp = null;

	public String getHdp() {
		return hdp;
	}

	public void setHdp(String hdp) {
		this.hdp = hdp;
	}

	/**
	 * 备注
	 */
	private String meno = null;

	/**
	 * 获取备注
	 *
	 * @return Meno 备注
	 */
	public String getMeno() {
		return this.meno;
	}

	/**
	 * 设置备注
	 *
	 * @param Meno
	 *            备注
	 */
	public void setMeno(String meno) {
		this.meno = meno;
	}

	/**
	 * 有效标识
	 */
	private String delFlag = null;

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
	 * 获取有效标识
	 *
	 * @return Del_flag 有效标识
	 */
	public String getDelFlag() {
		return this.delFlag;
	}

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
	 * 设置有效标识
	 *
	 * @param Del_flag
	 *            有效标识
	 */
	public void setDelFlag(String delflag) {
		this.delFlag = delflag;
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
