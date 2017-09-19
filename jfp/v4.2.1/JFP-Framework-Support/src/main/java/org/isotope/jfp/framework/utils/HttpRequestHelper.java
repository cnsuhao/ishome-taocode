package org.isotope.jfp.framework.utils;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
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
	public static void main(String[] args) throws Exception {
		ipFormat("192.168.0.1");
	}

	public static String ipFormat(String ip) {
		try{
			String[] strs = ip.split("\\.");
			int ip1 = Integer.valueOf(strs[0]).intValue();
			int ip2 = Integer.valueOf(strs[1]).intValue();
			int ip3 = Integer.valueOf(strs[2]).intValue();
			int ip4 = Integer.valueOf(strs[3]).intValue();
	
			return String.format("%1$03d%2$03d%3$03d%4$03d", ip1, ip2, ip3, ip4);
		}catch (Exception e) {
			return "127000000001";
		}		
	}

	/**
	 * 获得当前服务器IP地址
	 * 
	 * @return
	 */
	public static String getServerLocalIPAddr() {
		return getServerLocalIPAddr(false);
	}

	public static String getServerLocalIPAddr(boolean format) {
		String ipAddress = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			byte[] ipAddr = addr.getAddress();
			for (int i = 0; i < ipAddr.length; i++) {
				if (i > 0) {
					ipAddress += ".";
				}
				ipAddress += ipAddr[i] & 0xFF;
			}
			if (format)
				return ipFormat(ipAddress);
		} catch (Exception e) {
			ipAddress = "127.0.0.1";
		}
		return ipAddress;
	}

	public static String getClientRemoteIPAddr(HttpServletRequest request) {
		return getClientRemoteIPAddr(request, false);
	}

	/**
	 * 获得远程客户端IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientRemoteIPAddr(HttpServletRequest request, boolean format) {
		String ipAddress = null;
		try {
			ipAddress = request.getHeader("X-Real-IP");
			if (EmptyHelper.isEmpty(ipAddress)) {
				ipAddress = request.getHeader("X-Forwarded-For");
			}
			else if (EmptyHelper.isEmpty(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			}
			else if (EmptyHelper.isEmpty(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			}
			else if (EmptyHelper.isEmpty(ipAddress)) {
				ipAddress = request.getRemoteAddr();
			}
			// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			else if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
																// = 15
				if (ipAddress.indexOf(",") > 0) {
					ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
				}
			}
		} catch (Exception e) {
			ipAddress = "127.0.0.1";
		}
		if (format)
			ipAddress = ipFormat(ipAddress);

		return ipAddress;
	}

	/**
	 * 获得当前域名信息
	 * 
	 * @param request
	 * @return
	 */
	public static String getServerAddr(HttpServletRequest request) {
		try {
			StringBuffer url = request.getRequestURL();
			String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length())
					.append(request.getServletContext().getContextPath()).append("/").toString();

			return tempContextUrl;
		} catch (Exception e) {
			return "";
		}
	}
	/**
	 * 获得真实域名信息
	 * 
	 * @param request
	 * @return
	 */
	public static String getDomainName(HttpServletRequest request) {
		try {
			StringBuffer url = request.getRequestURL();
			String serverAddr = url.toString();
			serverAddr = serverAddr.substring(serverAddr.indexOf("//") + 2);
			serverAddr = serverAddr.substring(0, serverAddr.indexOf("/"));
			String[] aStrings = serverAddr.split("\\.");
			String domainName = aStrings[aStrings.length - 2] + "." + aStrings[aStrings.length - 1];
			return domainName;
		} catch (Exception e) {
			return "";
		}
	}
	/**
	 * Host域名地址转换，未发现的场合返回空白
	 * 
	 * @param request
	 * @param source
	 * @param target
	 * @return
	 */
	public static String changeServerHost(HttpServletRequest request, String source, String target) {
		return changeServerHost(request, source, target, true);
	}

	public static String changeServerHost(HttpServletRequest request, String source, String target, boolean def) {
		String host = HttpRequestHelper.getServerAddr(request);
		if (host.indexOf(source) >= 0) {
			host = host.replace(source, target);
		} else {
			if (def == false)
				host = "";
		}
		return host;
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
