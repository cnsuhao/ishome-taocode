package org.isotope.fpm.framework.support;

import org.isotope.fpm.common.util.DateUtil;

/**
 * 数据库实体超类
 * 
 * @since 0.1 2012-8-22
 * @version 0.1
 * @see MyBeanSupport
 */
public class MyDaoSupport extends MyBeanSupport {

	/**
     * 
     */
	private static final long serialVersionUID = -6095023798965115598L;
	
	/**
	 * 获取当前表名
	 * 
	 * @return BB100 备注说明
	 */
	public String getTableName() {
		return this.getClass().getSimpleName();
	}

	/**
	 * 备注说明
	 */
	private String BB100 = "";

	/**
	 * 备用1
	 */
	private String FB1Z1 = "";

	/**
	 * 备用2
	 */
	private String FB1Z2 = "";

	/**
	 * 备用3
	 */
	private String FB1Z3 = "";

	/**
	 * 备用4
	 */
	private String FB1Z4 = "";

	/**
	 * 备用5
	 */
	private String FB1Z5 = "";

	/**
	 * 扩展1
	 */
	private String EB1B1 = "";

	/**
	 * 扩展2
	 */
	private String EB102 = "";

	/**
	 * 扩展3
	 */
	private String EB103 = "";

	/**
	 * 扩展4
	 */
	private String EB104 = "";

	/**
	 * 扩展5
	 */
	private String EB105 = "";

	/**
	 * 有效标识 <br>0:有效1:删除
	 */
	private String BB1B1 = "0";

	/**
	 * 创建时间
	 * @see DateUtil#currentTimeMillis()
	 */
	private String BB102 = "";

	/**
	 * 创建者
	 */
	private String BB103 = "";

	/**
	 * 更新时间
	 * @see DateUtil#currentTimeMillis()
	 */
	private String BB104 = "";

	/**
	 * 更新者
	 */
	private String BB105 = "";

	/**
	 * 获取备注说明
	 * 
	 * @return BB100 备注说明
	 */
	public String getBB100() {
		return this.BB100;
	}

	/**
	 * 获取备用1
	 * 
	 * @return FB1Z1 备用1
	 */
	public String getFB1Z1() {
		return this.FB1Z1;
	}

	/**
	 * 获取备用2
	 * 
	 * @return FB1Z2 备用2
	 */
	public String getFB1Z2() {
		return this.FB1Z2;
	}

	/**
	 * 获取备用3
	 * 
	 * @return FB1Z3 备用3
	 */
	public String getFB1Z3() {
		return this.FB1Z3;
	}

	/**
	 * 获取备用4
	 * 
	 * @return FB1Z4 备用4
	 */
	public String getFB1Z4() {
		return this.FB1Z4;
	}

	/**
	 * 获取备用5
	 * 
	 * @return FB1Z5 备用5
	 */
	public String getFB1Z5() {
		return this.FB1Z5;
	}

	/**
	 * 获取扩展1
	 * 
	 * @return EB1B1 扩展1
	 */
	public String getEB1B1() {
		return this.EB1B1;
	}

	/**
	 * 获取扩展2
	 * 
	 * @return EB102 扩展2
	 */
	public String getEB102() {
		return this.EB102;
	}

	/**
	 * 获取扩展3
	 * 
	 * @return EB103 扩展3
	 */
	public String getEB103() {
		return this.EB103;
	}

	/**
	 * 获取扩展4
	 * 
	 * @return EB104 扩展4
	 */
	public String getEB104() {
		return this.EB104;
	}

	/**
	 * 获取扩展5
	 * 
	 * @return EB105 扩展5
	 */
	public String getEB105() {
		return this.EB105;
	}

	/**
	 * 获取有效标识
	 * 
	 * @return BB1B1 有效标识
	 */
	public String getBB1B1() {
		return this.BB1B1;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return BB102 创建时间
	 */
	public String getBB102() {
		return this.BB102;
	}

	/**
	 * 获取创建者
	 * 
	 * @return BB103 创建者
	 */
	public String getBB103() {
		return this.BB103;
	}

	/**
	 * 获取更新时间
	 * 
	 * @return BB104 更新时间
	 */
	public String getBB104() {
		return this.BB104;
	}

	/**
	 * 获取更新者
	 * 
	 * @return BB105 更新者
	 */
	public String getBB105() {
		return this.BB105;
	}

	/**
	 * 设置备注说明
	 * 
	 * @param bB100
	 *            备注说明
	 */
	public void setBB100(String bB100) {
		this.BB100 = bB100;
	}

	/**
	 * 设置备用1
	 * 
	 * @param fB1Z1
	 *            备用1
	 */
	public void setFB1Z1(String fB1Z1) {
		this.FB1Z1 = fB1Z1;
	}

	/**
	 * 设置备用2
	 * 
	 * @param fB1Z2
	 *            备用2
	 */
	public void setFB1Z2(String fB1Z2) {
		this.FB1Z2 = fB1Z2;
	}

	/**
	 * 设置备用3
	 * 
	 * @param fB1Z3
	 *            备用3
	 */
	public void setFB1Z3(String fB1Z3) {
		this.FB1Z3 = fB1Z3;
	}

	/**
	 * 设置备用4
	 * 
	 * @param fB1Z4
	 *            备用4
	 */
	public void setFB1Z4(String fB1Z4) {
		this.FB1Z4 = fB1Z4;
	}

	/**
	 * 设置备用5
	 * 
	 * @param fB1Z5
	 *            备用5
	 */
	public void setFB1Z5(String fB1Z5) {
		this.FB1Z5 = fB1Z5;
	}

	/**
	 * 设置扩展1
	 * 
	 * @param eB1B1
	 *            扩展1
	 */
	public void setEB1B1(String eB1B1) {
		this.EB1B1 = eB1B1;
	}

	/**
	 * 设置扩展2
	 * 
	 * @param eB102
	 *            扩展2
	 */
	public void setEB102(String eB102) {
		this.EB102 = eB102;
	}

	/**
	 * 设置扩展3
	 * 
	 * @param eB103
	 *            扩展3
	 */
	public void setEB103(String eB103) {
		this.EB103 = eB103;
	}

	/**
	 * 设置扩展4
	 * 
	 * @param eB104
	 *            扩展4
	 */
	public void setEB104(String eB104) {
		this.EB104 = eB104;
	}

	/**
	 * 设置扩展5
	 * 
	 * @param eB105
	 *            扩展5
	 */
	public void setEB105(String eB105) {
		this.EB105 = eB105;
	}

	/**
	 * 设置有效标识
	 * 
	 * @param bB1B1
	 *            有效标识
	 */
	public void setBB1B1(String bB1B1) {
		this.BB1B1 = bB1B1;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param bB102
	 *            创建时间
	 */
	public void setBB102(String bB102) {
		this.BB102 = bB102;
	}

	/**
	 * 设置创建者
	 * 
	 * @param bB103
	 *            创建者
	 */
	public void setBB103(String bB103) {
		this.BB103 = bB103;
	}

	/**
	 * 设置更新时间
	 * 
	 * @param bB104
	 *            更新时间
	 */
	public void setBB104(String bB104) {
		this.BB104 = bB104;
	}

	/**
	 * 设置更新者
	 * 
	 * @param bB105
	 *            更新者
	 */
	public void setBB105(String bB105) {
		this.BB105 = bB105;
	}
}
