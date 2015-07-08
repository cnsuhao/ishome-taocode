package org.jfpc.base.support;

import org.jfpc.base.bean.FrameworkDataBean;
import org.jfpc.base.helper.PKHelper;


/**
 * 页面数据持久层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class MyPageViewObjectSupport extends FrameworkDataBean {
	
	 /** 
     * 获取数据ID
     *
     * @return PUK_ID 数据ID
     */
    public String getPuk_id() {
    	if("".equals(super.getPuk()))
    		super.setPuk(PKHelper.creatPUKey());
        return super.getPuk();
    }
	
}
