package com.ttnn.business.wm.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ttnn.business.wm.biz.WMQTCZTXBussiness;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;

/**
 * 智付通道
 * 
 * @author duan.p
 * 
 */
@Service("zhiFuService_")
public class ZhiFuService implements PayService {

	@Resource
	WMBM01Service wmbm01Service; // 个人支付通道
	@Resource
	WMBM02Service wmbm02Service; // 充值记录
	
	@Resource
	WMBMA1Service wmbma1Service; // 系统通道
	
	@Resource
	protected WMQTCZTXBussiness WMQTCZTXBussiness_; // 充值核对

	/**
	 * 智付实现
	 */
	@Override
	public Map<String, Object> readToPay(FrameworkDataBean paramBean,
			FrameworkDataBean xttdFrameworkDataBean) {
		String order_no = paramBean.getPuk(); // 支付流水号
		String order_amount = new BigDecimal(paramBean.getF07()).toString(); // 支付金额
		String merchant_code = xttdFrameworkDataBean.getF01();// 商户号
		String md5Key = xttdFrameworkDataBean.getF02(); // MD5key -> 商户密钥
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String notify_url = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getLocalPort()
				+ "/WMBM06/" + "zftd" + "/HOME/U.go";
		String return_url = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getLocalPort()
				+ "/WMBM06/" + "zftd" + "/HOME/U.go";
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		String order_time = formatter.format(new Date()); // 时间
		String input_charset = "UTF-8";
		String interface_version = "V3.0";
		String sign_type = "MD5";
		String service_type = "direct_pay"; // 支付方式
		String product_name = xttdFrameworkDataBean.getEb5();
		String bank_code = paramBean.getF04(); // 支付渠道
		/*
		 * *
		 * * 签名顺序按照参数名a到z的顺序排序，若遇到相同首字母，则看第二个字母，以此类推，同时将商家支付密钥key放在最后参与签名，*
		 * 组成规则如下：* 参数名1=参数值1&参数名2=参数值2&……&参数名n=参数值n&key=key值
		 */
		StringBuffer signSrc = new StringBuffer();
		// 组织订单信息
		if (!"".equals(bank_code)) {
			signSrc.append("bank_code=").append(bank_code).append("&");
		}
		if (!"".equals(input_charset)) {
			signSrc.append("input_charset=").append(input_charset).append("&");
		}
		if (!"".equals(interface_version)) {
			signSrc.append("interface_version=").append(interface_version)
					.append("&");
		}
		if (!"".equals(merchant_code)) {
			signSrc.append("merchant_code=").append(merchant_code).append("&");
		}
		if (!"".equals(notify_url)) {
			signSrc.append("notify_url=").append(notify_url).append("&");
		}
		if (!"".equals(order_amount)) {
			signSrc.append("order_amount=").append(order_amount).append("&");
		}
		if (!"".equals(order_no)) {
			signSrc.append("order_no=").append(order_no).append("&");
		}
		if (!"".equals(order_time)) {
			signSrc.append("order_time=").append(order_time).append("&");
		}

		if (!"".equals(product_name)) {
			signSrc.append("product_name=").append(product_name).append("&");
		}

		if (!"".equals(return_url)) {
			signSrc.append("return_url=").append(return_url).append("&");
		}

		if (!"".equals(service_type)) {
			signSrc.append("service_type=").append(service_type).append("&");
		}

		signSrc.append("key=").append(md5Key);
		String singInfo = signSrc.toString();
		String sign = "";
		try {
			sign = DigestUtils.md5Hex(singInfo.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, Object> postMap = new HashMap<String, Object>(); // 第三方支付POST
		postMap.put("merchant_code", merchant_code);
		postMap.put("sign", sign);
		postMap.put("order_no", order_no);
		postMap.put("order_amount", order_amount);
		postMap.put("order_time", order_time);
		postMap.put("notify_url", notify_url);
		postMap.put("interface_version", interface_version);
		postMap.put("service_type", service_type);
		postMap.put("sign_type", sign_type);
		postMap.put("product_name", product_name);
		postMap.put("input_charset", input_charset);
		postMap.put("bank_code", bank_code);
		postMap.put("return_url", return_url);
		return postMap;
	}

	/**
	 * 智付支付回调函数
	 * 
	 * @throws Exception
	 */
	@Override
	public CSPVOSupport callBack(HttpServletRequest request) throws Exception {
		// 商家订单号
		String order_no = request.getParameter("order_no");
		CSPVOSupport bean = new CSPVOSupport();
		try {
			// 通知类型
			String notify_type = request.getParameter("notify_type"); // 页面跳转通知：page_notify
																		// 服务器异步通知：offline_notify
			// 通知校验ID
			String notify_id = request.getParameter("notify_id");
			// 交易状态 SUCCESS 成功 FAILED 失败
			String trade_status = request.getParameter("trade_status");
			// 商家订单金额
			String order_amount = request.getParameter("order_amount"); // 以元为单位，精确到小数点后两位
			getLogger().warn(
					"智付通道订单回调:订单号为[" + order_no + "],订单金额:[" + order_amount
							+ "],订单状态:[" + trade_status + "],页面跳转类型:["
							+ notify_type + "],通知校验ID:[" + notify_id + "]");
			// 商户号
			String merchant_code = request.getParameter("merchant_code");
			// 接口版本
			String interface_version = request
					.getParameter("interface_version");
			// 签名方式
			String sign_type = request.getParameter("sign_type");
			// 签名
			String dinpaySign = request.getParameter("sign");
			// 商家订单时间
			String order_time = request.getParameter("order_time");

			// 回传参数
			String extra_return_param = request
					.getParameter("extra_return_param");
			// 智付交易定单号
			String trade_no = request.getParameter("trade_no");
			// 智付交易时间
			String trade_time = request.getParameter("trade_time");

			// 银行交易流水号
			String bank_seq_no = request.getParameter("bank_seq_no");
			// 银行码
			String bank_code = request.getParameter("bank_code");
			
			
			
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
			
			
			 /*签名顺序按照参数名a到z的顺序排序，若遇到相同首字母，则看第二个字母，以此类推，
				*同时将商家支付密钥key放在最后参与签名，组成规则如下：
				*参数名1=参数值1&参数名2=参数值2&……&参数名n=参数值n&key=key值
				**/


				//组织订单信息
				StringBuilder signStr = new StringBuilder();
				if(StringUtils.isNotEmpty(bank_code)){
					signStr.append("bank_code=").append(bank_code).append("&");
				}
				if(StringUtils.isNotEmpty(bank_seq_no)) {
					signStr.append("bank_seq_no=").append(bank_seq_no).append("&");
				}
				if(StringUtils.isNotEmpty(extra_return_param)) {
					signStr.append("extra_return_param=").append(extra_return_param).append("&");
				}
				signStr.append("interface_version=").append(interface_version).append("&");
				signStr.append("merchant_code=").append(merchant_code).append("&");
				if(StringUtils.isNotEmpty(notify_id)) {
					signStr.append("notify_id=").append(notify_id).append("&notify_type=").append(notify_type).append("&");
				}

				signStr.append("order_amount=").append(order_amount).append("&");
				signStr.append("order_no=").append(order_no).append("&");
				signStr.append("order_time=").append(order_time).append("&");
				signStr.append("trade_no=").append(trade_no).append("&");
				signStr.append("trade_status=").append(trade_status).append("&");
				if(StringUtils.isNotEmpty(trade_time)) {
					signStr.append("trade_time=").append(trade_time).append("&");
				}
				String key=db2.getF02(); //密钥
				signStr.append("key=").append(key);
				String signInfo = signStr.toString();
				//将组装好的信息MD5签名
				String sign = DigestUtils.md5Hex(signInfo); //注意与支付签名不同  此处对String进行加密
				//比较智付返回的签名串与商家这边组装的签名串是否一致
				if(dinpaySign.equals(sign)){
					if (trade_status.equals("SUCCESS")) {
						// 交易成功
						bean.setF06("2");
						if (StringUtils.isNotEmpty(bank_seq_no)) {
							bean.setF08(trade_no); // 第三方支付流水号
						}
						if (StringUtils.isNotEmpty(bank_code)) {
							bean.setF04(bank_code); // 银行码
						}
						if (StringUtils.isNotEmpty(notify_id)) {
							bean.setF10(notify_id); // 通知校验ID
						}
					} else {
						// 交易失败
						bean.setF06("1");
					}
				}else{
					getLogger().error("签名失败!");
					bean.setF06("1");
				} 
			bean.setPuk(order_no);
		} catch (Exception e) {
			bean.setF06("1");
			throw new Exception(e.getMessage() + ":"
					+ ExceptionUtils.getMessage(e.getCause()));
		}
		return bean;
	}

	@Override
	public String getView() {
		return "WM/QT/ZIFUPOST";
	}

	@Override
	/**
	 * db1 系统通道   db2 个人充值记录
	 * 智付订单查询并核对
	 */
	public Map<String, String> checkOrder(Map<String,String> paramMap) throws Exception {
		String order_no =paramMap.get("order_no"); // 订单号 
		Map<String, String> map = new HashMap<String, String>();
		try {
			String url = "https://query.dinpay.com/query"; // 智付查询接口
			String interface_version = "V3.0"; // 接口文档
			String merchant_code = paramMap.get("merchant_code");// 商户号
			String service_type = "single_trade_query";
			String sign_type = "MD5";
			String trade_no = "";
			String md5key = paramMap.get("md5Key");// 密钥
			// 组织订单信息
			StringBuilder signStr = new StringBuilder();
			signStr.append("interface_version=V3.0");
			signStr.append("&merchant_code=").append(merchant_code);
			signStr.append("&order_no=").append(order_no);
			signStr.append("&service_type=").append(service_type);
			signStr.append("&key=").append(md5key);
			// 将组装好的信息MD5签名
			String sign = DigestUtils.md5Hex(signStr.toString()); // 注意与支付签名不同
																	// 此处对String进行加密
			HttpPost get = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("interface_version", "V3.0"));
			nvps.add(new BasicNameValuePair("sign_type", "MD5"));
			nvps.add(new BasicNameValuePair("merchant_code", merchant_code));
			nvps.add(new BasicNameValuePair("service_type", service_type));
			nvps.add(new BasicNameValuePair("order_no", order_no));
			nvps.add(new BasicNameValuePair("sign", sign));
			get.setEntity(new UrlEncodedFormEntity(nvps));
			HttpClient client = new DefaultHttpClient();
			HttpResponse res = client.execute(get);

			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				// SAXReader reader = new SAXReader();
				InputStream is = entity.getContent();
				StringBuffer sbstr = new StringBuffer();
				BufferedReader bufferferreader = new BufferedReader(
						new InputStreamReader(is));
				String line;
				while ((line = bufferferreader.readLine()) != null) {
					sbstr.append(line);
				}
				bufferferreader.close();
				String rtn = sbstr.toString();
				String success = rtn
						.substring(
								rtn.indexOf("<is_success>")
										+ "<is_success>".length(),
								rtn.indexOf("</is_success>"));
				// 订单查询结果
				if (success.trim().equals("F")) {
					// 查询失败
					String error_code = rtn.substring(
							rtn.indexOf("<error_code>")
									+ "<error_code>".length(),
							rtn.indexOf("</error_code>"));
					getLogger().warn("智付通道订单查询失败，失败原因为:[" + error_code + "]");
			        map.put("status", "0");
			        map.put("errorCode", error_code);
				} else {
					// 查询成功
					String trade_status = rtn.substring(
							rtn.indexOf("<trade_status>")
									+ "<trade_status>".length(),
							rtn.indexOf("</trade_status>")); // 交易状态
					getLogger().warn("智付通道订单查询成功，交易状态为:[" + trade_status + "]");
					map.put("tradeStatus", getOrderStatus(trade_status.trim()));
					// 订单交易成功
					if (trade_status.trim().equals("SUCCESS")) {
						String order_amount = rtn.substring(
								rtn.indexOf("<order_amount>")
										+ "<order_amount>".length(),
								rtn.indexOf("</order_amount>")); // 订单金额
						String trade_time = rtn.substring(
								rtn.indexOf("<trade_time>")
										+ "<trade_time>".length(),
								rtn.indexOf("</trade_time>")); // 第三方公司支付交易时间
						String bank_code = ""; // 银行码
						if (rtn.indexOf("<bank_code>") != -1) {
							bank_code = rtn.substring(
									rtn.indexOf("<bank_code>")
											+ "<bank_code>".length(),
									rtn.indexOf("</bank_code>"));
							;
						}
						 trade_no = rtn.substring(
								rtn.indexOf("<trade_no>")
										+ "<trade_no>".length(),
								rtn.indexOf("</trade_no>")); // 第三方公司支付订单号
						
						map.put("trade_time", trade_time);
						map.put("bank_code", bank_code);
						map.put("trade_no", trade_no);
						map.put("order_amount", order_amount);
						// 支付成功
						// 如果库中实际支付金额 与 查询支付金额相等
						// 刚插入进去 -》 未支付 -》 轮训 -》 支付完成 - 》对账这边则为未支付，发现那边确是支付完成
                        getLogger().error("订单对账:"+paramMap.get("amount").trim()+":"+order_amount.trim());
						if (paramMap.get("amount").trim().equals(order_amount.trim())) {
                                                                        
							if (paramMap.get("status").trim().equals("0")
									|| paramMap.get("status").trim().equals("1")) {
								//我们失败，而第三方公司为成功的
								
								// 支付成功且对账成功,且原来为申请中或者申请失败的
								map.put("status", "2");
							} else if (paramMap.get("status").trim().equals("2")) {
								map.put("status", "1"); //支付成功，且对账成功
							}
						} else {
							// 支付成功但对账时金钱数目不相等
                            map.put("status", "3");
						}
					} else {	
					    //	根据订单查询为支付失败或者未支付
						if (paramMap.get("status").trim().equals("2")) {
							// 而本系统却意外为查询已经支付成功的 -> 暂时锁定通道
							map.put("status", "4");
						} else {
							map.put("status", "5");
						}

					}
				}
			} else {
				getLogger().error(
						"请求智付不成功!请求状态为:" + res.getStatusLine().getStatusCode()
								+ ",订单号:" + order_no);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage() + ":"
					+ ExceptionUtils.getMessage(e.getCause()));
		}
		return map;

	}
    
	

	
	private String getOrderStatus(String code) {
		String description = null;
		if (code.equals("SUCCESS")) {
			description = "交易成功";

		} else if (code.equals("FAILED")) {
			description = "交易失败";
		} else if (code.equals("UNPAY")) {
			description = "未支付";
		} else {
			description = code;
		}
		return description;

	}

	public Logger getLogger() {
		return LoggerFactory.getLogger(ZhiFuService.class);
	}

}
