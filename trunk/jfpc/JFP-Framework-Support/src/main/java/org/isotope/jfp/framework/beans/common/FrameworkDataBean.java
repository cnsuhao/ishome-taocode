package org.isotope.jfp.framework.beans.common;

import javax.inject.Named;

import org.isotope.jfp.framework.beans.ObjectBean;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.utils.PKHelper;

/**
 * 基底共通
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/2/8
 */
@Named
public class FrameworkDataBean extends ObjectBean {

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
 
	
}
