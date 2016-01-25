package org.isotope.jfp.framework.security;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * IP安全策略
 * 
 * @author fucy
 * @since 2.4.2 2016/1/6
 * @version 2.4.2 2016/1/6
 *
 */
public class SafeStrategySupport extends HandlerInterceptorAdapter implements ISFrameworkConstants {
	/**
	 * Redis缓存
	 */
	@Resource
	protected ICacheService cacheService;
	protected int size = 600;
	/**
	 * 进程阻塞时间（秒）
	 */
	protected int waitTimeSecond = 1;

	public int getWaitTimeSecond() {
		return waitTimeSecond;
	}

	public void setWaitTimeSecond(int waitTimeSecond) {
		this.waitTimeSecond = waitTimeSecond;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String ipAddress = getIpAddr(request);
		String lastTime = (String) cacheService.getObject(ipAddress);
		if (EmptyHelper.isEmpty(lastTime)) {
			// IP:频率:拉黑次数
			cacheService.putObject(ipAddress, System.currentTimeMillis() + DOWN_LINE + 1 + DOWN_LINE + 1, size, false);
		} else {
			String[] last = lastTime.split(DOWN_LINE);
			long lsp = Long.parseLong(last[0]);
			int nsp = Integer.parseInt(last[1]);
			int hsp = Integer.parseInt(last[2]);
			long sp = System.currentTimeMillis() - lsp;

			// 策略1、1秒内不能访问2次
			if (sp <= 1000 * waitTimeSecond * hsp) {
				hsp = hsp + 1;
				cacheService.putObject(ipAddress, System.currentTimeMillis() + DOWN_LINE + (1) + DOWN_LINE + hsp,
						size * hsp, false);
				// throw new CustomException("It'sAjoke!!!");
				goBack(request, response);
				return false;
			}
			// 策略2、5秒内不能访问3次
			if (nsp >= 3 && sp <= 5000 * waitTimeSecond * hsp) {
				// 黑名单检测
				hsp = hsp + 1;
				cacheService.putObject(ipAddress, System.currentTimeMillis() + DOWN_LINE + (1) + DOWN_LINE + hsp,
						size * hsp, false);
				// throw new CustomException("It'sAjoke!!!");
				goBack(request, response);
				return false;
			}
			cacheService.putObject(ipAddress, System.currentTimeMillis() + DOWN_LINE + (nsp + 1) + DOWN_LINE + hsp,
					size * hsp, false);
		}
		return true;
	}

	public void goBack(HttpServletRequest request, HttpServletResponse response) {
		try {

			// 销毁session
			request.getSession().invalidate();
			response.sendRedirect("/It'sAjoke!!!");
			response.sendError(200);
			// 形式： sendError(int errnum
			// )说明：用来向客户端发送错误信息，这对调试程序有很大帮助。常用的常量级错误代码有：
			// SC_CONTINUE， 状态码是100，表示客户端无法连接。
			// SC_SWITHING_PROTOCOLS,状态码是101，表示服务器正向报头中注明的协议切换。
			// SC_OK，状态码是200.表示请求被成功处理。
			// SC_CREATED，状态码是201，表示请求被成功处理，并在服务器方创建了一个新的资源。
			// SC_ACCEPTED，状态码是202，表示请求正在被处理，但尚未完成。
			// SC_NON_AUTHORITATIVE_INFORMATION，状态码是203，表示客户端所表达的mate信息并非来自服务器。
			// SC_NO_CONTENT，状态码是204，表示请求被成功处理，但没有新的信息返回。
			// SC_RESET_CONTENT,状态码是205，表示导致请求被发送的文档视图应该重置。
			// SC_PARTIAL_CONTENT,状态码是206，表示服务器已经完成对资源的GET请求。
			// SC_MULTI_CHOICES，状态码是300，表示对应于一系列表述的被请求资源都有明确的位置。
			// SC_MOVED_PERMANENTLY，状态码是301，表示请求所申请的资源已经被移到一个新的地方，并且将来的参考点在请求中应当使用一个新的URL.
			// SC_MOVED_TEMPORARILY,状态码是302，表示请求所申请的资源已经被移到一个新的地方，并且将来的参考点在请求中仍使用原来的URL.
			// SC_SEE_OTHER,状态码是303，表示请求的响应可以在一个不同的URL中找到。
			// SC_NOT_MODIFIED，状态码是304，表示一个有条件的GET操作发现资源可以利用，且没有被改变。
			// SC_USE_PROXY，状态码是305，表示被请求的资源必须通过特定位置的代理来访问。
			// SC_BAD_REQUEST，状态码是400，表示客户发出的请求句法不正确。
			// SC_UNAUTHORIZED,状态码是401，表示请求HTTP认证。
			// SC_PAYMENT_REQUIRED，状态码是402，表示为以后的使用保留。
			// SC_FORBIDDEN，状态码是403，表示服务器明白客户的请求，但拒绝响应。
			// SC_NOT_FAND，状态码是404，表示所请求的资源不可用。
			// SC_METHOD_NOT_ALLOWED,状态码是405，表示在请求行中标示的方法不允许对请求URL所标明的资源使用。
			// SC_NOT_ACCEPTTABLE,状态码是406，表示被请求的资源只能响应实体，而且此符合请求所发送的可接受头部域的实体的确包含不可接受的内容。
			// SC_PHOXY_AUTHENTICATION_REQUIRED,状态码是407，表示客户端必须先向代理验证。
		} catch (Exception e) {
		}
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		try {
			ipAddress = request.getHeader("X-Real-IP");
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("x-real-ip");
			} else if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("x-forwarded-for");
			} else if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			} else if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			} else if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
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
	private String transcode(String source, String distCharSet) throws Exception {
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
	public String transcode(HttpServletRequest request, String distCharSet) throws Exception {
		// 获得 POST参数
		Map<String, String[]> params = request.getParameterMap();
		String queryString = "";
		try {
			for (String key : params.keySet()) {
				String[] values = params.get(key);
				for (int i = 0; i < values.length; i++) {
					String value = values[i];
					queryString = queryString + key + "=" + transcode(value, distCharSet);
					if (i != 0)
						queryString = queryString + ",";
				}
			}
		} catch (Exception e) {

		}

		return queryString;
	}
}
