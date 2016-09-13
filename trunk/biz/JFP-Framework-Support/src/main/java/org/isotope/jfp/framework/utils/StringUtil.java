package org.isotope.jfp.framework.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author lujf
 * 
 */
public class StringUtil {

	private static String[][] FilterChars = { { "%", "/%" }, { "/", "//;" }, { "'", "''" }, { "\\", "\\/" } };

	private static String specifiedregEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

	/**
	 * 数据补全
	 * @param length
	 * @param use
	 * @param had
	 * @return
	 */
	public static String completion(int length, String use, String had) {
		StringBuilder sb = new StringBuilder();
		sb.append(had);
		while (sb.length() < length) {
			sb.insert(0, use);
		}

		return sb.toString();
	}
	
	/**
	 * 特殊字符处理
	 * 
	 * @param content
	 * @return
	 */
	public static String replayStr(String content) {
		for (int i = 0; i < FilterChars.length; i++) {
			content = content.replace(FilterChars[i][0], FilterChars[i][1]);
		}
		return content;

	}

	/**
	 * 从url获取参数值
	 * 
	 * @param url
	 * @return
	 */
	public static String[] getUrlParamVal(String url) {
		String[] param_arr = null;
		String[] param_val = null;
		if (url.indexOf("?") != -1) {
			String params_str = url.substring(url.indexOf("?") + 1, url.length());
			if (params_str.indexOf("&") != -1) {
				param_arr = params_str.split("&");
			} else {
				param_arr = new String[] { params_str };
			}

			if (param_arr != null && param_arr.length > 0) {
				param_val = new String[param_arr.length];
				int i = 0;
				for (String key : param_arr) {
					if (key.indexOf("=") != -1) {
						String[] paramAndVal = key.split("=");
						if (paramAndVal.length > 1) {
							param_val[i] = paramAndVal[1];
						} else {
							param_val[i] = "";
						}

					}
					i++;
				}
			}
		}

		return param_val;
	}

	/**
	 * 字符串转换为特定的类型
	 * 
	 * @param value
	 * @param type
	 * @param format
	 * @return
	 */
	public static Object stringConvertSpecifiedType(String value, String type, String format) {
		if (EmptyHelper.isEmpty(value))
			return null;
		if ("Date".equals(type)) {
			String date = DateHelper.formatDateBySpecified(value, format);
			DateFormat dateform = new SimpleDateFormat(format);
			try {
				return dateform.parse(date);
			} catch (ParseException e) {
				return null;
			}
		}
		if ("Integer".equals(type)) {
			value = value.replace(",", "");
			return Integer.parseInt(value);
		}
		if ("Double".equals(type)) {
			value = value.replace(",", "");
			return Double.parseDouble(value);
		}
		if ("Boolean".equals(type)) {
			return Boolean.valueOf(type);
		}
		return value;
	}

	/**
	 * 字符串转换为特定的类型
	 * 
	 * @param value
	 * @param type
	 * @return
	 */
	public static Object stringConvertSpecifiedType(String value, String type) {
		return stringConvertSpecifiedType(value, type, null);
	}

	/**
	 * 首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String upperCaseFirst(String str) {
		if (str != null && str.length() > 0) {
			str = str.substring(0, 1).toUpperCase() + str.substring(1);
		}
		return str;
	}

	/**
	 * 判断字符串是否匹配
	 * 
	 * @param str
	 * @param reg
	 * @return
	 */
	public static boolean match(String str, String reg) {
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 获取匹配的数据
	 * 
	 * @param str
	 * @param reg
	 * @return
	 */
	public static String getMatchStr(String str, String reg) {
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;

	}

	/**
	 * 获取匹配的数组
	 * 
	 * @param str
	 * @param reg
	 * @return
	 */
	public static List<String> getMatchList(String str, String reg) {
		List<String> list = new ArrayList<String>();
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			list.add(matcher.group());
		}
		return list;
	}

	/**
	 * 将key value键值对的字符串解析到map中
	 * 
	 * @param str
	 *            字符串
	 * @param split
	 *            分隔符
	 * @param reg
	 *            key和值的分隔符
	 * @return
	 */
	public static Map<String, String> parseMap(String str, String split, String reg) {
		Map<String, String> map = new HashMap<String, String>();
		String[] key_values = str.split(split);
		if (key_values != null) {
			for (String key_value : key_values) {
				String[] keyval = key_value.split(reg);
				if (keyval != null && keyval.length > 0) {
					if (keyval.length == 2) {
						map.put(keyval[0].trim(), keyval[1].trim());
					} else {
						map.put(keyval[0].trim(), "");
					}

				}
			}
		}
		return map;
	}

	/**
	 * 将map转换为特殊字符隔开的字符串
	 * 
	 * @param map
	 * @param split
	 * @param reg
	 * @return
	 */
	public static String paseMapToStr(Map<String, String> map, String split, String reg) {
		String str = "";
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if ("".equals(str)) {
				str = entry.getKey() + reg + entry.getValue();
			} else {
				str += split + entry.getKey() + reg + entry.getValue();
			}
		}
		return str;
	}

	/**
	 * 将列表转换为制定的列表格式
	 * 
	 * @param title
	 * @param datarow
	 * @param dataColCount
	 * @param list
	 * @return
	 */
	public static List<Map<String, String>> getSpecifiedMapFromList(String title, Integer datarow, Integer dataColCount,
			List<Map<String, String>> list) {
		List<Map<String, String>> res = new ArrayList<Map<String, String>>();
		String titles[] = title.split(",");
		if (list != null && list.size() > 0) {
			for (Map<String, String> map : list) {
				// 数据条数
				int count = map.keySet().size() / dataColCount;
				for (int i = 0; i < count; i++) {
					Map<String, String> rsMap = new HashMap<String, String>();
					for (String tit : titles) {
						rsMap.put(tit.replace("${count}", ""), map.get(tit.replace("${count}", "" + (datarow + i))));
					}
					res.add(rsMap);
				}
			}
		}
		return res;
	}

	/**
	 * 给值为null的字符串赋默认值
	 * 
	 * @author LuoRuixian
	 */
	public static String trimNull(String str, String defaultStr) {
		return str == null ? defaultStr : str;
	}

	/**
	 * 给值为null的字符串赋值空字符串
	 * 
	 * @author LuoRuixian
	 */
	public static String trimNull(String str) {
		return trimNull(str, "");
	}

	/**
	 * md5加密
	 * 
	 * @param in
	 * @return
	 */
	public static String passwdEncrypt(String in) {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.update(in.getBytes());
			byte[] digest = algorithm.digest();
			return byte2Hex(digest);
		} catch (NoSuchAlgorithmException alge) {
			alge.printStackTrace();
		}
		return null;
	}

	/**
	 * 十六进制转换
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2Hex(byte[] b) {
		String hs = "";
		String stmp;
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs;
	}

	/**
	 * 判断是否包含特殊字符
	 * 
	 * @param word
	 * @return
	 */
	public static boolean specifiedWord(String word) {
		Pattern p = Pattern.compile(specifiedregEx);
		Matcher m = p.matcher(word);
		return m.find();
	}

	/**
	 * 判断数组中是否包含指定的值
	 * 
	 * @param array
	 * @param val
	 */
	public static boolean isContainInArray(String[] array, String val) {
		boolean flag = false;
		for (String t_val : array) {
			if (t_val.equals(val)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * Tokenize the given String into a String array via a StringTokenizer.
	 * Trims tokens and omits empty tokens.
	 * <p>The given delimiters string is supposed to consist of any number of
	 * delimiter characters. Each of those characters can be used to separate
	 * tokens. A delimiter is always a single character; for multi-character
	 * delimiters, consider using {@code delimitedListToStringArray}
	 * @param str the String to tokenize
	 * @param delimiters the delimiter characters, assembled as String
	 * (each of those characters is individually considered as delimiter).
	 * @return an array of the tokens
	 * @see java.util.StringTokenizer
	 * @see String#trim()
	 * @see #delimitedListToStringArray
	 */
	public static String[] tokenizeToStringArray(String str, String delimiters) {
		return tokenizeToStringArray(str, delimiters, true, true);
	}

	/**
	 * Tokenize the given String into a String array via a StringTokenizer.
	 * <p>The given delimiters string is supposed to consist of any number of
	 * delimiter characters. Each of those characters can be used to separate
	 * tokens. A delimiter is always a single character; for multi-character
	 * delimiters, consider using {@code delimitedListToStringArray}
	 * @param str the String to tokenize
	 * @param delimiters the delimiter characters, assembled as String
	 * (each of those characters is individually considered as delimiter)
	 * @param trimTokens trim the tokens via String's {@code trim}
	 * @param ignoreEmptyTokens omit empty tokens from the result array
	 * (only applies to tokens that are empty after trimming; StringTokenizer
	 * will not consider subsequent delimiters as token in the first place).
	 * @return an array of the tokens ({@code null} if the input String
	 * was {@code null})
	 * @see java.util.StringTokenizer
	 * @see String#trim()
	 * @see #delimitedListToStringArray
	 */
	public static String[] tokenizeToStringArray(
			String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {

		if (str == null) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(str, delimiters);
		List<String> tokens = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if (!ignoreEmptyTokens || token.length() > 0) {
				tokens.add(token);
			}
		}
		return toStringArray(tokens);
	}
	
	/**
	 * Copy the given Collection into a String array.
	 * The Collection must contain String elements only.
	 * @param collection the Collection to copy
	 * @return the String array ({@code null} if the passed-in
	 * Collection was {@code null})
	 */
	public static String[] toStringArray(Collection<String> collection) {
		if (collection == null) {
			return null;
		}
		return collection.toArray(new String[collection.size()]);
	}
}
