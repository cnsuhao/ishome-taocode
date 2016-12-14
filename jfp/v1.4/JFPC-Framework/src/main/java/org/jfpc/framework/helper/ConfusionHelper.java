package org.jfpc.framework.helper;
/**
 * 数据混淆算法
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class ConfusionHelper {

	/**
	 * 交叉混淆，可以正序或者倒序，可以奇数和偶数
	 * 
	 * @param ss1
	 * @param ss2
	 * @return
	 */
	public static String getObscureString(String ss1, String ss2) {
		StringBuilder tonkenKey = new StringBuilder(36);
		int length = ss1.length() > ss2.length() ? ss1.length() : ss2.length();
		for (int i = 0; i < length; i++) {
			if (i < ss1.length())
				tonkenKey.append(ss1.charAt(i));
			else
				tonkenKey.append(",");
			if (i < ss2.length())
				tonkenKey.append(ss2.charAt(i));
			else
				tonkenKey.append(",");
		}
		return tonkenKey.toString();// username+"|"+ver;
	}

	public static String[] getObscureSss(String obscureString) {
		// not login in
		if (obscureString == null) {
			return null;
		}

		StringBuilder ss1 = new StringBuilder(36);
		StringBuilder ss2 = new StringBuilder(36);
		for (int i = 0; i < obscureString.length(); i++) {
			if (i % 2 == 0 && obscureString.charAt(i) != ',')
				ss1.append(obscureString.charAt(i));
			if (i % 2 == 1 && obscureString.charAt(i) != ',')
				ss2.append(obscureString.charAt(i));
		}

		String[] sss = { ss1.toString(), ss2.toString() };
		return sss;
	}

	public static String getObscureSs1(String obscureString) {
		// not login in
		if (obscureString == null) {
			return null;
		}
		StringBuilder ss1 = new StringBuilder(36);
		for (int i = 0; i < obscureString.length(); i++) {
			if (i % 2 == 0 && obscureString.charAt(i) != ',')
				ss1.append(obscureString.charAt(i));
		}
		return ss1.toString();
	}

	public static String getObscureSs2(String obscureString) {
		// not login in
		if (obscureString == null) {
			return null;
		}
		StringBuilder ss2 = new StringBuilder(36);
		for (int i = 0; i < obscureString.length(); i++) {
			if (i % 2 == 1 && obscureString.charAt(i) != ',')
				ss2.append(obscureString.charAt(i));
		}
		return ss2.toString();
	}

}
