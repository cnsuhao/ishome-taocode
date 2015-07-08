package org.jfpc.framework.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.jfpc.constants.ISFrameworkConstants;

/**
 * FTP资源中心路径管理工具
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 * 
 */
public class FilePathHelper implements ISFrameworkConstants {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] s = makeFilePath("spook.jpg");
		System.out.println((s[0]));
		System.out.println(getFilePath("11490235274336401266419211_5_0_._0",false));
		System.out.println((s[1]));
		System.out.println((s[2]));
	}

	/**
	 * 根据当前时间获得一个存储路径
	 */
	public static String getFilePath(String filePathId, boolean change) {
		String pathId = getPathId(filePathId);
		String fileId = getFileId(filePathId);
		if (change == true) {
			String ext = StringUtils.trimToEmpty(StringUtils.substring(fileId, StringUtils.lastIndexOf(fileId, DOT)));
			for (String[] image : IMAGE) {
				if (image[1].equals(ext))
					fileId = fileId.replace(image[1], image[0]);
			}
		}
		// String[] file = StringUtils.split(filePathId,DOT);
		return makeLocalFilePath(pathId) + fileId;
	}

	/**
	 * 根据当前时间获得一个存储路径
	 */
	public static String getPathId(String filePathId) {
		if (StringUtils.isEmpty(filePathId)) {
			return StringUtils.EMPTY;
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
		if (StringUtils.isEmpty(filePathId)) {
			return StringUtils.EMPTY;
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
		String[] filePath = { "", "", "" };
		// 图片识别路径
		filePath[0] = makeFilePathId(pathId, fileId + getExtension(fileName, true));
		// 图片物理路径
		filePath[1] = makeLocalFilePath(pathId);
		// 图片文件名
		filePath[2] = fileId + getExtension(fileName, true);
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

	final static String[][] IMAGE = { { ".jpg", ".0" }, { ".png", ".1" }, { ".gif", ".2" }, { ".bmp", ".3" }, { ".jpeg", ".4" } };

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
			for (String[] image : IMAGE) {
				if (image[0].equals(ext))
					return image[1];
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
