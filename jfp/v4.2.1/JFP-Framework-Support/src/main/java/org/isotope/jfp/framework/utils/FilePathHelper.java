package org.isotope.jfp.framework.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;

/**
 * FTP资源中心路径管理工具
 * 
 * @author Spook
 * @since 0.1.4
 * @version 0.1 2014/11/3
 * @version 0.2 2014/10/8
 * 
 */
// TODO
public class FilePathHelper implements ISFrameworkConstants {

	/**
	 * 图片种类
	 */
	public static String IMAG_TYPE = "";

	public static String CONTENT_TYPE_FILE = "/fileContentType.xml";

	private static ArrayList<ArrayList<String>> FileTypes = new ArrayList<ArrayList<String>>();

	static {
		String[][] IMAGE = { { ".jpg", ".0", "image/jpeg", "0" },
				{ ".png", ".1", "image/png", "0" }, 
				{ ".gif", ".2", "image/gif", "0" }, 
				{ ".bmp", ".3", "application/x-bmp", "0" }, 
				{ ".jpeg", ".4", "image/jpeg", "0" }, 
				{ ".html", ".5", "text/html", "1" }, 
				{ ".pdf", ".6", "application/pdf", "1" }, 
				{ ".htm", ".7", "text/html", "1" }, 
				{ ".doc", ".8", "application/msword", "1" } };

		StringBuffer its = new StringBuffer();

		for (String[] item : IMAGE) {
			ArrayList<String> is = new ArrayList<String>();
			is.add(item[0]);
			is.add(item[1]);
			is.add(item[2]);
			if (ZERO.equals(item[3]))
				its.append("||" + item[0]);
			FileTypes.add(is);
		}

		IMAG_TYPE = its.toString();

		// try {
		// Properties props = new Properties();
		// InputStream in =
		// FilePathHelper.class.getResourceAsStream(CONTENT_TYPE_FILE);
		// props.load(in);
		// machinecode = props.getProperty(MACHINE_CODE);
		// } catch (Exception e) {
		// //System.out.println("It's not exit File (fileContentType.xml)!");
		// }
	}

	// public final static String[][] IMAGE = {
	// { ".jpg", ".0", "image/jpeg" },
	// { ".png", ".1", "image/png" },
	// { ".gif", ".2", "image/gif" },
	// { ".bmp", ".3", "application/x-bmp" },
	// { ".jpeg", ".4", "image/jpeg" },
	// { ".html", ".5", "text/html" },
	// { ".pdf", ".6", "application/pdf" },
	// { ".htm", ".7", "text/html" }
	// };
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] s = makeFilePath("spook.jpg");
		 System.out.println("=="+(s[0]));
		// System.out.println("=="+(s[1]));
		// System.out.println("=="+(s[2]));
		// System.out.println("=="+(s[3]));
		// System.out.println("==111=="+getFilePath("11451642260080981322426024_3_7_6_1_0_0_0_._6",true)[0]);
		// System.out.println("==111=="+getFilePath("11451642260080981322426024_3_7_6_1_0_0_0_._6",true)[1]);
		// System.out.println("==111=="+getFilePath("11451642260080981322426024_3_7_6_1_0_0_0_._6",true)[2]);
		// System.out.println("==111=="+getFilePath("11451642260080981322426024_3_7_6_1_0_0_0_._6",true)[3]);
		// System.out.println("==111=="+getFilePath("11451642260080981322426024_3_7_6_1_0_0_0_._6",true)[4]);
	}

	/**
	 * 根据当前时间获得一个存储路径
	 * 
	 * @return String[0] 文件全路径 String[1] 文件路径 String[2] 文件名称（全） String[3] 文件扩展名
	 */
	public static String[] getFilePath(String filePathId, boolean change) {
		String[] filePath = new String[5];
		filePath[1] = makeLocalFilePath(getPathId(filePathId));
		filePath[2] = getFileId(filePathId);
		String ext = StringUtils.trimToEmpty(StringUtils.substring(filePath[2], StringUtils.lastIndexOf(filePath[2], DOT)));
		if (change == true) {
			for (ArrayList<String> item : FileTypes) {
				if (item.get(1).equals(ext)) {
					filePath[2] = filePath[2].replace(item.get(1), item.get(0));
					filePath[3] = item.get(0);
					filePath[4] = item.get(2);
					break;
				}
			}
		} else {
			filePath[3] = ext;
			filePath[4] = "";
		}
		filePath[0] = filePath[1] + filePath[2];
		return filePath;
	}

	/**
	 * 根据当前时间获得一个存储路径
	 */
	public static String getPathId(String filePathId) {
		if (EmptyHelper.isEmpty(filePathId)) {
			return EmptyHelper.EMPTY;
		}
		StringBuilder userId = new StringBuilder(54);
		for (int i = 0; i < filePathId.length(); i++) {
			if (i % 2 == 0 && filePathId.charAt(i) != DOWN_LINE2)
				userId.append(filePathId.charAt(i));
		}
		return userId.toString();
	}

	/**
	 * 根据当前时间获得一个存储路径
	 */
	public static String getFileId(String filePathId) {
		if (EmptyHelper.isEmpty(filePathId)) {
			return EmptyHelper.EMPTY;
		}
		StringBuilder userId = new StringBuilder(54);
		for (int i = 0; i < filePathId.length(); i++) {
			if (i % 2 == 1 && filePathId.charAt(i) != DOWN_LINE2)
				userId.append(filePathId.charAt(i));
		}
		return userId.toString();
	}

	/**
	 * 获得上传文件ID与存储路径
	 */
	public static String[] makeFilePath(String fileName) {
		return makeFilePath("" + System.currentTimeMillis(), PKHelper.creatPUKey(), fileName);
	}

	/**
	 * 获得上传文件ID与存储路径
	 */
	public static String[] makeFilePath(String pathId, String fileId, String fileName) {
		String[] filePath = new String[4];
		// 图片识别路径
		filePath[0] = makeFilePathId(pathId, fileId + getExtension(fileName, true));
		// 图片物理路径
		filePath[1] = makeLocalFilePath(pathId);
		// 图片文件名
		filePath[2] = fileId + getExtension(fileName, false);
		// 图片扩展名
		filePath[3] = StringUtils.trimToEmpty(StringUtils.substring(fileName, StringUtils.lastIndexOf(fileName, DOT)));
		return filePath;
	}

	/**
	 * 根据日期创建文件保存路径 （年/月/日）
	 * 
	 * @param pathId
	 * @return
	 */
	public static String makeLocalFilePath(String pathId) {
		StringBuilder path = new StringBuilder(20);
		{
			SimpleDateFormat formatYyyy = new SimpleDateFormat("yyyy");
			SimpleDateFormat formatMm = new SimpleDateFormat("MM");
			SimpleDateFormat formatDd = new SimpleDateFormat("dd");
			Date date = new Date(Long.parseLong(pathId));
			path.append(BACKSLASH);
			path.append(formatYyyy.format(date));
			path.append(BACKSLASH);
			path.append(formatMm.format(date));
			path.append(BACKSLASH);
			path.append(formatDd.format(date));
			path.append(BACKSLASH);
		}
		return path.toString();
	}

	/**
	 * 获取扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getExtension(String fileName, boolean change) {
		if (StringUtils.INDEX_NOT_FOUND == StringUtils.indexOf(fileName, DOT))
			return StringUtils.EMPTY;
		String ext = StringUtils.trimToEmpty(StringUtils.substring(fileName, StringUtils.lastIndexOf(fileName, DOT)));
		if (change == true) {
			for (ArrayList<String> item : FileTypes) {
				if (item.get(0).equals(ext.toLowerCase()))
					return item.get(1);
			}
		}
		return ext;
	}

	/**
	 * 混淆生成图片识别ID，保存于数据库内
	 * 
	 * @param filePathId
	 * @param fileId
	 * @return
	 */
	public static String makeFilePathId(String filePathId, String fileId) {
		StringBuilder tonkenKey = new StringBuilder(54);
		int length = filePathId.length() > fileId.length() ? filePathId.length() : fileId.length();
		for (int i = 0; i < length; i++) {
			if (i < filePathId.length())
				tonkenKey.append(filePathId.charAt(i));
			else
				tonkenKey.append(DOWN_LINE);

			if (i < fileId.length())
				tonkenKey.append(fileId.charAt(i));
			else
				tonkenKey.append(DOWN_LINE);
		}
		return tonkenKey.toString();
	}
}
