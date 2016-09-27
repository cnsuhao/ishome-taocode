package org.isotope.jfp.framework.utils;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 客户端请求工具类
 * 
 * @author Spook
 * @version 3.3.1 2016/8/8 增加默认ipAddress（0.0.0.0:1）
 * @version 3.1.0 2016/5/30
 * @since 3.1.0 2016/5/30
 *
 */
public class HttpRequestHelper {
	/**
	 * 获得当前服务器IP地址
	 * @return
	 */
	public static String getServerLocalIPAddr() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		byte[] ipAddr = addr.getAddress();
		String ipAddrStr = "";
		for (int i = 0; i < ipAddr.length; i++) {
			if (i > 0) {
				ipAddrStr += ".";
			}
			ipAddrStr += ipAddr[i] & 0xFF;
		}
		// System.out.println(ipAddrStr);
		return ipAddrStr;
	}
	/**
	 * 获得远程客户端IP地址
	 * @param request
	 * @return
	 */
	public static String getClientRemoteIPAddr(HttpServletRequest request) {
		String ipAddress = null;
		try {
			ipAddress = request.getHeader("X-Real-IP");
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("x-real-ip");
			}
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("x-forwarded-for");
			}
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getRemoteAddr();
				if (ipAddress.equals("127.0.0.1")) {
					// 根据网卡取本机配置的IP
					InetAddress inet = null;
					try {
						inet = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					ipAddress = inet.getHostAddress();
				}

			}

			// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
																// = 15
				if (ipAddress.indexOf(",") > 0) {
					ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
				}
			}
		} catch (Exception e) {
			ipAddress = "0.0.0.0:1";
		}

		return ipAddress;
	}

	/**
	 * 字符转码
	 * 
	 * @param source
	 * @param distCharSet
	 * @return
	 * @throws UnsupportedEncodingException
	 *             创建日期：2015年5月11日 修改说明：
	 * @author niezhegang
	 */
	public static String transcode(String source, String distCharSet) throws UnsupportedEncodingException {
		String ret = source;
		if (StringUtils.isNotBlank(ret)) {
			ret = URLDecoder.decode(source, distCharSet);
			ret = new String(ret.getBytes("iso-8859-1"), "UTF-8");
		}
		return ret;
	}

	/**
	 * 字符转码
	 * 
	 * @param request
	 * @param distCharSet
	 * @return
	 * @throws UnsupportedEncodingException
	 *             创建日期：2015年5月11日 修改说明：
	 * @author niezhegang
	 */
	public static String transcode(HttpServletRequest request, String distCharSet) throws UnsupportedEncodingException {
		// 获得 POST参数
		Map<String, String[]> params = request.getParameterMap();
		String queryString = "";
		try {
			for (String key : params.keySet()) {
				String[] values = params.get(key);
				for (int i = 0; i < values.length; i++) {
					String value = values[i];
					if ("".equals(queryString) == false)
						queryString = queryString + "&";
					queryString = queryString + key + "=" + transcode(value, distCharSet);
				}
			}
		} catch (Exception e) {

		}

		return queryString;
	}
}
