package com.ttnn.business.wm.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;

public abstract class RongBaoCommonService implements PayService {

	@Resource
	WMBM02Service wmbm02Service; // 充值信息

	@Resource
	WMBM01Service wmbm01Service; // 个人通道信息

	@Resource
	WMBMA1Service wmbma1Service; // 系统通道

	public Map<String, Object> readToPay(FrameworkDataBean paramBean,
			FrameworkDataBean xttdFrameworkDataBean) {
		return doProcess(paramBean, xttdFrameworkDataBean);
	}

	// 融宝 和融宝夜间去实现属于自己的准备页面
	public abstract Map<String, Object> doProcess(FrameworkDataBean paramBean,
			FrameworkDataBean xttdFrameworkDataBean);

	/**
	 * 融宝订单回调
	 */
	public CSPVOSupport callBack(HttpServletRequest request) throws Exception {
		CSPVOSupport bean = new CSPVOSupport();
		try {
			String order_no = request.getParameter("order_no"); // 商户交易号
			CSPVOSupport bean1 = new CSPVOSupport();
			bean1.setPuk(order_no);// 流水号
			FrameworkDataBean db = wmbm02Service.doRead(bean1); // 充值记录
			String puk_tid = db.getK02();
			CSPVOSupport bean2 = new CSPVOSupport();
			bean2.setPuk(puk_tid);
			FrameworkDataBean dbx = wmbm01Service.doRead(bean2); // 个人通道信息
			CSPVOSupport bean3 = new CSPVOSupport();
			bean3.setPuk(dbx.getK03());
			FrameworkDataBean db2 = wmbma1Service.doRead(bean3); // 系统通道信息
			// 获取融宝支付的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 获取融宝支付的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			// 获取融宝支付GET过来反馈信息

			String trade_no = request.getParameter("trade_no"); // 融宝支付交易号
			String total_fee = request.getParameter("total_fee"); // 获取总金额
			String title = request.getParameter("title"); // 商品名称、订单名称
			String body = request.getParameter("body");
			String buyer_email = request.getParameter("buyer_email"); // 买家融宝支付账号
			String trade_status = request.getParameter("trade_status"); // 交易状态
			getLogger().info(
					"融宝回调：订单号:[" + order_no + ",对方订单号:[" + trade_no + ",充值金额:["
							+ total_fee + "],交易状态:[" + trade_status + "]");

			RongpayFunction function = new RongpayFunction();
			Map params = function
					.transformRequestMap(request.getParameterMap());

			// 判断responsetTxt是否为ture，生成的签名结果mysign与获得的签名结果sign是否一致
			// responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
			// mysign与sign不等，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
			String mysign = function.BuildMysign(params, db2.getF02(), "gbk");

			String responseTxt = function.Verify(
					request.getParameter("notify_id"), db2.getF01());
			getLogger().info(
					"融宝通道验证信息为：" + responseTxt + "******，订单号为：" + order_no);
			String sign = request.getParameter("sign");
			bean.setPuk(order_no); // 流水号

			String verifyStatus = "";

			// 建议校验responseTxt，判读该返回结果是否由融宝发出
			if (mysign.equals(sign) && responseTxt.equals("true")) {

				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				if (trade_status.equals("TRADE_FINISHED")) {
					bean.setF06("2");

					// 支付成功，如果没有做过处理，根据订单号（out_trade_no）及金额（total_fee）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 一定要做金额判断，防止恶意窜改金额，只支付了小金额的订单。
					// 如果有做过处理，不执行商户的业务程序
				} else {
					// 支付失败
					bean.setF06("1");
					bean.setBbb(trade_status);
					// 支付失败处理相关订单
				}
				verifyStatus = "验证成功";
				// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			} else {
				verifyStatus = "验证失败";
				bean.setF06("1");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage() + ":"
					+ ExceptionUtils.getMessage(e.getCause()) + ":"
					+ ExceptionUtils.getFullStackTrace(e.fillInStackTrace()));
		}

		return bean;
	}

	@Override
	/**
	 * 融宝订单查询
	 */
	public Map<String, String> checkOrder(Map<String,String> pMap) throws Exception {
		String order_no = pMap.get("order_no"); // 商户订单号
		Map<String, String> map = new HashMap<String, String>();
		try {} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage() + ":"
					+ ExceptionUtils.getMessage(e.getCause()));
		}
		return map;

	}

	@Override
	public String getView() {
		return "WM/QT/RongBaoPost";
	}

	protected String mySign(String service, String payment_type,
			String merchant_ID, String seller_email, String return_url,
			String notify_url, String charset, String order_no, String title,
			String body, String total_fee, String buyer_email,
			String paymethod, String defaultbank, String key) {
		Map<String, String> sPara = new HashMap<String, String>();
		sPara.put("service", service);
		sPara.put("payment_type", payment_type);
		sPara.put("merchant_ID", merchant_ID);
		sPara.put("seller_email", seller_email);
		sPara.put("return_url", return_url);
		sPara.put("notify_url", notify_url);
		sPara.put("charset", charset);
		sPara.put("order_no", order_no);
		sPara.put("title", title);
		sPara.put("body", body);
		sPara.put("total_fee", total_fee);
		sPara.put("buyer_email", buyer_email);
		sPara.put("paymethod", paymethod);
		sPara.put("defaultbank", defaultbank);
		String mysign = new RongpayFunction().BuildMysign(sPara, key, charset);// 生成签名结果
		return mysign;
	}

	/**
	 * 功能：融宝支付接口公用函数 详细：该页面是请求、通知返回两个文件所调用的公用函数核心处理文件，不需要修改 版本：1.0
	 * 修改日期：2012-05-01 '说明：
	 * '以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
	 * '该代码仅供学习和研究融宝支付接口使用，只是提供一个参考。
	 */
	class RongpayFunction {

		/**
		 * 功能：生成签名结果
		 * 
		 * @param sArray
		 *            要签名的数组
		 * @param key
		 *            安全校验码
		 * @return 签名结果字符串
		 */
		private String BuildMysign(Map sArray, String key, String charset) {

			/**
			 * 功能：融宝支付MD5加密处理核心文件，不需要修改 版本：3.1 修改日期：2010-11-01 说明：
			 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
			 * 该代码仅供学习和研究融宝支付接口使用，只是提供一个
			 * */
			class Md5Encrypt {
				/**
				 * Used building output as Hex
				 */
				private final char[] DIGITS = { '0', '1', '2', '3', '4', '5',
						'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

				/**
				 * 对字符串进行MD5加密
				 * 
				 * @param text
				 *            明文
				 * 
				 * @return 密文
				 */
				private String md5(String text, String charset) {
					MessageDigest msgDigest = null;

					try {
						msgDigest = MessageDigest.getInstance("MD5");
					} catch (NoSuchAlgorithmException e) {
						throw new IllegalStateException(
								"System doesn't support MD5 algorithm.");
					}

					try {
						msgDigest.update(text.getBytes(charset)); // 注意改接口是按照指定编码形式签名

					} catch (UnsupportedEncodingException e) {

						throw new IllegalStateException(
								"System doesn't support your  EncodingException.");

					}

					byte[] bytes = msgDigest.digest();

					String md5Str = new String(encodeHex(bytes));

					return md5Str;
				}

				/**
				 * 十六进制转换
				 * */
				private char[] encodeHex(byte[] data) {

					int l = data.length;

					char[] out = new char[l << 1];

					// two characters form the hex value.
					for (int i = 0, j = 0; i < l; i++) {
						out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
						out[j++] = DIGITS[0x0F & data[i]];
					}

					return out;
				}

			}

			if (sArray != null && sArray.size() > 0) {
				StringBuilder prestr = CreateLinkString(sArray); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
				return new Md5Encrypt().md5(prestr.append(key).toString(),
						charset);// 把拼接后的字符串再与安全校验码直接连接起来,并且生成加密串
			}
			return null;
		}

		/**
		 * 功能：把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
		 * 
		 * @param params
		 *            需要排序并参与字符拼接的参数组
		 * @return 拼接后字符串
		 */
		private StringBuilder CreateLinkString(Map params) {
			List keys = new ArrayList(params.keySet());
			Collections.sort(keys);

			StringBuilder prestr = new StringBuilder();
			String key = "";
			String value = "";
			for (int i = 0; i < keys.size(); i++) {
				key = (String) keys.get(i);
				value = (String) params.get(key);
				if ("".equals(value) || value == null
						|| key.equalsIgnoreCase("sign")
						|| key.equalsIgnoreCase("sign_type")) {
					continue;
				}
				prestr.append(key).append("=").append(value).append("&");
			}
			return prestr.deleteCharAt(prestr.length() - 1);
		}

		/**
		 * 将融宝支付POST过来反馈信息转换一下
		 * 
		 * @param requestParams
		 *            返回参数信息
		 * @return Map 返回一个只有字符串值的MAP
		 * */
		private Map transformRequestMap(Map requestParams) {
			Map params = null;
			if (requestParams != null && requestParams.size() > 0) {
				params = new HashMap();
				String name = "";
				String[] values = null;
				for (Iterator iter = requestParams.keySet().iterator(); iter
						.hasNext();) {
					name = (String) iter.next();
					values = (String[]) requestParams.get(name);
					String valueStr = "";
					for (int i = 0; i < values.length; i++) {
						valueStr = (i == values.length - 1) ? valueStr
								+ values[i] : valueStr + values[i] + ",";
					}
					// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
					params.put(name, valueStr);
				}
			}
			return params;
		}

		/**
		 * *功能：获取远程服务器ATN结果,验证返回URL
		 * 
		 * @param notify_id
		 *            通知校验ID
		 * @return 服务器ATN结果 验证结果集： invalid命令参数不对
		 *         出现这个错误，请检测返回处理中merchant_ID和key是否为空 true 返回正确信息 false
		 *         请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
		 */
		private String Verify(String notify_id, String merchantID) {

			// 获取远程服务器ATN结果，验证是否是融宝支付服务器发来的请求
			String transport = "http";

			String merchant_ID = merchantID;

			StringBuilder veryfy_url = new StringBuilder();
			if (transport.equalsIgnoreCase("https")) {
				veryfy_url
						.append("https://interface.rongpay.com.cn/verify/notify?");
			} else {
				veryfy_url.append("http://interface.reapal.com/verify/notify?");
			}
			veryfy_url.append("merchant_ID=").append(merchant_ID)
					.append("&notify_id=").append(notify_id);
			String responseTxt = CheckUrl(veryfy_url.toString());
			return responseTxt;

		}

		/**
		 * *功能：获取远程服务器ATN结果
		 * 
		 * @param urlvalue
		 *            指定URL路径地址
		 * @return 服务器ATN结果 验证结果集： invalid命令参数不对
		 *         出现这个错误，请检测返回处理中merchant_ID和key是否为空 true 返回正确信息 false
		 *         请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
		 */
		private String CheckUrl(String urlvalue) {
			String inputLine = "";
			try {
				URL url = new URL(urlvalue);
				HttpURLConnection urlConnection = (HttpURLConnection) url
						.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						urlConnection.getInputStream()));
				if (in != null) {
					inputLine = in.readLine().toString();
				}
				in.close();
				urlConnection.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return inputLine;
		}

		/**
		 * 功能：XML报文解析函数
		 * */
		private HashMap GetMessage(String url) {
			HashMap hm = null;
			return hm;
		}
	}

	public Logger getLogger() {
		return LoggerFactory.getLogger(RongBaoService.class);
	}
	
	private String resultWrapper(String result){
		if(result.equals("wait")){
			return "等待支付";
		}
		if(result.equals("completed")){
			return "支付成功";
		}
		if(result.equals("failed")){
			return "支付失败";
		}
		return result;
	}

}
