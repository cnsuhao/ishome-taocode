package org.isotope.fpm.framework.support;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.isotope.fpm.common.util.PKUtil;

/**
 * 数据实体超类
 * <p>
 * 需要定义@CacheKeyMethod标签来为实体指定唯一Key值，完成缓存
 * 
 * @since 0.1 2012-7-13
 * @version 0.1
 */
public class MyBeanSupport extends MyBusinessSupport implements Serializable {
	
	/**
	 * 数据表记录ID，缓存唯一识别Key
	 */
	private String PUKEY = PKUtil.getPUKey();

	public String getPUKEY() {
    	return PUKEY;
    }

	public void setUniqueKey(String pUKEY) {
		this.PUKEY = pUKEY;
    }

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -2602500764642998243L;


	/**
	 * 输出实体数据内容
	 */
	// 在eclipse的源代码出右键–>source->Generate toString()…
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
		// return new ReflectionToStringBuilder(this).toString();
	}

	// -----------------------------------------------------------------
	public static void main(String[] args) {
		System.out.println(new MyBeanSupport());
	}
	
	////////////////////////////////////////////
	/**
	 * 备注说明
	 */
	private String B0100 = "";

	/**
	 * 备用1
	 */
	private String F01Z1 = "";

	/**
	 * 备用2
	 */
	private String F01Z2 = "";

	/**
	 * 备用3
	 */
	private String F01Z3 = "";

	/**
	 * 备用4
	 */
	private String F01Z4 = "";

	/**
	 * 备用5
	 */
	private String F01Z5 = "";

	/**
	 * 扩展1
	 */
	private String E0101 = "";

	/**
	 * 扩展2
	 */
	private String E0102 = "";

	/**
	 * 扩展3
	 */
	private String E0103 = "";

	/**
	 * 扩展4
	 */
	private String E0104 = "";

	/**
	 * 扩展5
	 */
	private String E0105 = "";

	/**
	 * 有效标识
	 */
	private String B0101 = "";

	/**
	 * 创建时间
	 */
	private String B0102 = "";

	/**
	 * 创建者
	 */
	private String B0103 = "";

	/**
	 * 更新时间
	 */
	private String B0104 = "";

	/**
	 * 更新者
	 */
	private String B0105 = "";
	

	/**
	 * 获取备注说明
	 * 
	 * @return B0100 备注说明
	 */
	public String getB0100() {
		return this.B0100;
	}

	/**
	 * 获取备用1
	 * 
	 * @return F01Z1 备用1
	 */
	public String getF01Z1() {
		return this.F01Z1;
	}

	/**
	 * 获取备用2
	 * 
	 * @return F01Z2 备用2
	 */
	public String getF01Z2() {
		return this.F01Z2;
	}

	/**
	 * 获取备用3
	 * 
	 * @return F01Z3 备用3
	 */
	public String getF01Z3() {
		return this.F01Z3;
	}

	/**
	 * 获取备用4
	 * 
	 * @return F01Z4 备用4
	 */
	public String getF01Z4() {
		return this.F01Z4;
	}

	/**
	 * 获取备用5
	 * 
	 * @return F01Z5 备用5
	 */
	public String getF01Z5() {
		return this.F01Z5;
	}

	/**
	 * 获取扩展1
	 * 
	 * @return E0101 扩展1
	 */
	public String getE0101() {
		return this.E0101;
	}

	/**
	 * 获取扩展2
	 * 
	 * @return E0102 扩展2
	 */
	public String getE0102() {
		return this.E0102;
	}

	/**
	 * 获取扩展3
	 * 
	 * @return E0103 扩展3
	 */
	public String getE0103() {
		return this.E0103;
	}

	/**
	 * 获取扩展4
	 * 
	 * @return E0104 扩展4
	 */
	public String getE0104() {
		return this.E0104;
	}

	/**
	 * 获取扩展5
	 * 
	 * @return E0105 扩展5
	 */
	public String getE0105() {
		return this.E0105;
	}

	/**
	 * 获取有效标识
	 * 
	 * @return B0101 有效标识
	 */
	public String getB0101() {
		return this.B0101;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return B0102 创建时间
	 */
	public String getB0102() {
		return this.B0102;
	}

	/**
	 * 获取创建者
	 * 
	 * @return B0103 创建者
	 */
	public String getB0103() {
		return this.B0103;
	}

	/**
	 * 获取更新时间
	 * 
	 * @return B0104 更新时间
	 */
	public String getB0104() {
		return this.B0104;
	}

	/**
	 * 获取更新者
	 * 
	 * @return B0105 更新者
	 */
	public String getB0105() {
		return this.B0105;
	}
	


	/**
	 * 设置备注说明
	 * 
	 * @param b0100
	 *            备注说明
	 */
	public void setB0100(String b0100) {
		this.B0100 = b0100;
	}

	/**
	 * 设置备用1
	 * 
	 * @param f01Z1
	 *            备用1
	 */
	public void setF01Z1(String f01Z1) {
		this.F01Z1 = f01Z1;
	}

	/**
	 * 设置备用2
	 * 
	 * @param f01Z2
	 *            备用2
	 */
	public void setF01Z2(String f01Z2) {
		this.F01Z2 = f01Z2;
	}

	/**
	 * 设置备用3
	 * 
	 * @param f01Z3
	 *            备用3
	 */
	public void setF01Z3(String f01Z3) {
		this.F01Z3 = f01Z3;
	}

	/**
	 * 设置备用4
	 * 
	 * @param f01Z4
	 *            备用4
	 */
	public void setF01Z4(String f01Z4) {
		this.F01Z4 = f01Z4;
	}

	/**
	 * 设置备用5
	 * 
	 * @param f01Z5
	 *            备用5
	 */
	public void setF01Z5(String f01Z5) {
		this.F01Z5 = f01Z5;
	}

	/**
	 * 设置扩展1
	 * 
	 * @param e0101
	 *            扩展1
	 */
	public void setE0101(String e0101) {
		this.E0101 = e0101;
	}

	/**
	 * 设置扩展2
	 * 
	 * @param e0102
	 *            扩展2
	 */
	public void setE0102(String e0102) {
		this.E0102 = e0102;
	}

	/**
	 * 设置扩展3
	 * 
	 * @param e0103
	 *            扩展3
	 */
	public void setE0103(String e0103) {
		this.E0103 = e0103;
	}

	/**
	 * 设置扩展4
	 * 
	 * @param e0104
	 *            扩展4
	 */
	public void setE0104(String e0104) {
		this.E0104 = e0104;
	}

	/**
	 * 设置扩展5
	 * 
	 * @param e0105
	 *            扩展5
	 */
	public void setE0105(String e0105) {
		this.E0105 = e0105;
	}

	/**
	 * 设置有效标识
	 * 
	 * @param b0101
	 *            有效标识
	 */
	public void setB0101(String b0101) {
		this.B0101 = b0101;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param b0102
	 *            创建时间
	 */
	public void setB0102(String b0102) {
		this.B0102 = b0102;
	}

	/**
	 * 设置创建者
	 * 
	 * @param b0103
	 *            创建者
	 */
	public void setB0103(String b0103) {
		this.B0103 = b0103;
	}

	/**
	 * 设置更新时间
	 * 
	 * @param b0104
	 *            更新时间
	 */
	public void setB0104(String b0104) {
		this.B0104 = b0104;
	}

	/**
	 * 设置更新者
	 * 
	 * @param b0105
	 *            更新者
	 */
	public void setB0105(String b0105) {
		this.B0105 = b0105;
	}	
	
}
