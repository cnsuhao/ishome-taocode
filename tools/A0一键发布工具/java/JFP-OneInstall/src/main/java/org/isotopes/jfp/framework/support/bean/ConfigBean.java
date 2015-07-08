package org.isotopes.jfp.framework.support.bean;

import javax.inject.Named;

/**
 * 参数配置信息
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0.0
 */
@Named
public class ConfigBean {
	/**
	 * 配置使用关键字
	 */
	public String key = "";

	/**
	 * 配置对应的内容
	 */
	public String value = "";

	/**
	 * 配置关键字说明
	 */
	public String meno = "";
	/**
	 * 配置定义编号
	 */
	public String puk = "";

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMeno() {
		return meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

	public String getPuk() {
		return puk;
	}

	public void setPuk(String puk) {
		this.puk = puk;
	}

	@Override
	public String toString() {
		return "ConfigBean [key=" + key + ", value=" + value + ", meno=" + meno
				+ ", puk=" + puk + "]";
	}

}
