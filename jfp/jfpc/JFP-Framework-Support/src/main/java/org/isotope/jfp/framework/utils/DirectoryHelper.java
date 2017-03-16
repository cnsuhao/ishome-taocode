package org.isotope.jfp.framework.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 日期格式化
 * 
 * @author Spook
 * @since 3.3.1 2016/9/20
 * @version 3.3.1 2016/9/20
 */
public class DirectoryHelper {
	/**
	 * 创建目录
	 * @param path
	 */
	public static void checkDirectory(File path) {
		if (path.exists() == false){
			path.mkdirs();
		}
	}
	
	/**
	 * 删除日期格式目录下面的过期目录
	 * @param targetPath yyyyMMdd
	 * @param day 间隔天数
	 */
	public static void deleteDirectory(File targetPath, int day) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1 * day);
		String path = format.format(new Date(cal.getTimeInMillis()));
		for (File f : targetPath.listFiles()) {
			if (f.isDirectory() && (Integer.parseInt(f.getName()) < Integer.parseInt(path))) {
				deleteDirectory(f);
			}
		}
	}

	/**
	 * 强制删除目录
	 * @param path
	 */
	public static void deleteDirectory(File path) {
		if (!path.exists())
			return;
		if (path.isFile()) {
			path.delete();
			return;
		}
		File[] files = path.listFiles();
		for (int i = 0; i < files.length; i++) {
			deleteDirectory(files[i]);
		}
		path.delete();
	}
}
