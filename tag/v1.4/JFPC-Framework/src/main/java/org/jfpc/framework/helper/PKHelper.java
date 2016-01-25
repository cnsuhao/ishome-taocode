package org.jfpc.framework.helper;

import java.io.InputStream;
import java.util.Properties;

import org.jfpc.constants.ISFrameworkConstants;


/**
 * 系统唯一识别ID
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class PKHelper implements ISFrameworkConstants {
	private static final String MACHINE_FILE = "/machine.properties";
	private static final String MACHINE_CODE = "machinecode";
	
	private static String machinecode = "";
	private static long systemTimeMillis = 0l;
	private static long synchronizedNum = 0l;
	
	private static StringBuilder primaryUniqueKey;

	static {
		try {
			Properties props = new Properties();
			InputStream in = PKHelper.class.getResourceAsStream(MACHINE_FILE);
			props.load(in);
			machinecode = props.getProperty(MACHINE_CODE);
		} catch (Exception e) {
			System.out.println("It's not exit File (machine.properties)!");
		}
	}

	public static void main(String[] args) {
		try {
			// System.out.println(getPUKey(getTokenKey("6534234df457ghdfg4543")));
			int length = 0;
			for (int i = 0; i < 1; i++) {
				String puk = creatPUKey();
				System.out.println(puk);
				if (puk.length() != length) {
					System.out.println(puk.length());
					length = puk.length();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得一个数据记录的主键Key<br>
	 * 时间+序号
	 * 
	 * @return 全局唯一主键
	 */
	public synchronized static String creatPUKey() {
		primaryUniqueKey = new StringBuilder(25);
		long currentTimeMillis = System.nanoTime();// currentTimeMillis
		// 同步判定
		if (currentTimeMillis > systemTimeMillis) {
			synchronizedNum = 0l;
			systemTimeMillis = currentTimeMillis;
		} else {
			synchronizedNum++;
		}
		primaryUniqueKey.append(systemTimeMillis);
		primaryUniqueKey.append(machinecode);
		primaryUniqueKey.append(synchronizedNum);
		return primaryUniqueKey.toString();
	}

}
