package org.isotope.jfp.framework.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * 基于文件的缓存，使用MD5的文件名称，按照年月日进行数据存放
 * @author Spook
 * @version 2.3.1 2015/7/15
 * @since 2.3.1 2015/7/15
 * 
 */
public class FileCacheHelper {

	/**
	 * 文件缓存路径
	 */
	public String fileCacheFolder = "";

	public String getFileCacheFolder() {
		return fileCacheFolder;
	}

	public void setFileCacheFolder(String fileCacheFolder) {
		this.fileCacheFolder = fileCacheFolder;
	}

	/**
	 * 
	 * @param accessPatientId
	 *            对接患者ID
	 * @param accessSchId
	 *            对接排班ID
	 * @return
	 * @throws Exception
	 */
	public FrameworkDataBean load(String accessPatientId, String accessSchId) {

		BufferedReader reader = null;
		try {
			String key = getMD5Key(accessPatientId + accessSchId);

			File file = new File(getPath() + "/" + key + ".txt");
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8")); // 指定读取文件的编码格式，要和写入的格式一致，以免出现中文乱码,
			String str = null;
			StringBuffer value = new StringBuffer();
			while ((str = reader.readLine()) != null) {
				value.append(str);
			}
			return (FrameworkDataBean) RedisHelper.getClassFromRedis(value.toString());
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			try {
				if(reader!=null)
					reader.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}

		return null;
	}

	public File getPath() {
		File path = new File(getFileCacheFolder()+DateHelper.currentDate4());
		path.mkdirs();
		return path;
	}

	/**
	 * 
	 * @param accessPatientId
	 *            对接患者ID
	 * @param accessSchId
	 *            对接排班ID
	 * @return
	 * @throws Exception
	 */
	public boolean save(String accessPatientId, String accessSchId, FrameworkDataBean hao) {

		BufferedWriter fw = null;
		try {
			String key = getMD5Key(accessPatientId + accessSchId);
			File file = new File(getPath() + "/" + key + ".txt");
			file.delete();
			file.createNewFile();
			fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8")); // 指定编码格式，以免读取时中文字符异常
			fw.append(RedisHelper.getStringToRedis(hao));
			fw.flush(); // 全部写入缓存中的内容
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
				}
			}
		}
		return true;
	}

	/**
	 * 加密
	 * 
	 * @param plainText
	 *            需加密内容
	 * @return
	 * @throws Exception
	 */
	private static String getMD5Key( String plainText) throws Exception {
		return new Md5PasswordEncoder().encodePassword(plainText, null);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
	}
}
