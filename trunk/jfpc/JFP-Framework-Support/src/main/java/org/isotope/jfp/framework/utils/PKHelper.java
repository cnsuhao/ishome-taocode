package org.isotope.jfp.framework.utils;

import java.util.UUID;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;

/**
 * 系统唯一识别ID
 * 
 * @author Spook
 * @version 2.4.1 2015/11/10
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class PKHelper implements ISFrameworkConstants {
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 5; i++) {
				String puk = creatPUKey();
				//System.out.println("puk=="+puk.length());
				//System.out.println(UUID.randomUUID());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得一个UUID<br>
	 * 
	 * @return 全局唯一主键
	 */
	public synchronized static String creatPUKey() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
