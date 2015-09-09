package org.isotope.jfp.framework.beands;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.utils.PKHelper;

/**
 * 参数配置信息
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/2/8
 */
public class ObjectBean {

    /** 
     * 系统数据唯一识别ID（固定主键）
     */
    private String puk;

    /** 
     * 获取系统数据唯一识别ID（固定主键）
     *
     * @return PUK 系统数据唯一识别ID（固定主键）
     */
    public String getPuk() {
    	if(EmptyHelper.isEmpty(puk))
    		puk = PKHelper.creatPUKey();
        return this.puk;
    }
 
    /** 
     * 设置系统数据唯一识别ID（固定主键）
     *
     * @param PUK 系统数据唯一识别ID（固定主键）
     */
    public void setPuk(String PUK) {
        this.puk = PUK;
    }
 
	/**
	 * 时间戳
	 */
	private String uuu = DateHelper.currentTimeMillisCN1();
	public void updateUuu() {
		 uuu = DateHelper.currentTimeMillisCN1();
	}
	public String getUuu() {
		return uuu;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
