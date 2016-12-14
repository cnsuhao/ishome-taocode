package com.ttnn.business.wm.service;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;

/**
 * 宝付服务接口
 * 
 * @author duan.p
 * 
 */
@Service("baoFuService_")
public class BaoFuService implements PayService {

	@Resource
	WMBM02Service wmbm02Service; // 充值信息

	@Resource
	WMBM01Service wmbm01Service; // 个人通道信息

	@Resource
	WMBMA1Service wmbma1Service; // 系统通道

	@Override
	/**
	 * 宝付订单准备工作
	 */
	public Map<String, Object> readToPay(FrameworkDataBean paramBean,
			FrameworkDataBean xttdFrameworkDataBean) {
		String transId = paramBean.getPuk(); // 支付流水号
		String orderMoney = new BigDecimal(paramBean.getF07()).multiply(
				new BigDecimal(100)).toString(); // 支付金额
		String merchantId = xttdFrameworkDataBean.getF01();// 商户号
		String md5Key = xttdFrameworkDataBean.getF02(); // MD5key -> 商户密钥
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss",
				Locale.CHINA);
		String tradeDate = formatter.format(new Date()); // 时间
		String payId = paramBean.getF04(); // 支付渠道
		String noticeType = "1"; // 通知方式
		// 通知方式 Int类型
		// 1：服务器通知和页面通知。支付成功后，自动重定向到“通知商户地址”
		// 0：只发服务器端通知，不跳转
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String merchantUrl = request.getScheme() + "://"+ request.getServerName() + ":"
				+ request.getLocalPort() + "/WMBM06/" + "bftd" + "/HOME/U.go";
		String returnUrl = request.getScheme() + "://"+ request.getServerName() + ":"
				+ request.getLocalPort() + "/WMBM06/" + "bftd" + "/HOME/U.go";
		String md5 = new String(merchantId + payId + tradeDate + transId
				+ orderMoney + merchantUrl + returnUrl + noticeType + md5Key);// MD5签名格式
		String md5Sign = getMD5ofStr(md5); // md5签名
		Map<String, Object> postMap = new HashMap<String, Object>(); // 第三方支付POST
		postMap.put("merchantId", merchantId); // 商户号 ->动态
		postMap.put("payId", payId);
		postMap.put("transId", transId);
		postMap.put("tradeDate", tradeDate);
		postMap.put("orderMoney", orderMoney);
		postMap.put("merchantUrl", merchantUrl);
		postMap.put("returnUrl", returnUrl);
		postMap.put("md5Sign", md5Sign);
		postMap.put("noticeType", noticeType);
		return postMap;
	}

	public String getView() {
		return "/WM/QT/BAOFUPOST";
	}

	/**
	 * 宝付订单查询并核对
	 */
	@Override
	public Map<String, String> checkOrder(Map<String,String> pMap) throws Exception {
		String merchant_code = pMap.get("merchant_code");// 商户号
		Map<String,String> map = new HashMap<String, String>();
		try {
			String md5key =pMap.get("md5Key");// 密钥
			String order_no = pMap.get("order_no"); // 订单号
			String md5 = new String(merchant_code + order_no + md5key);// MD5签名格式
			String Md5Sign = getMD5ofStr(md5);// 计算MD5值
			// 抓取OrderQuery.aspx页面提交后的数据
			String sCurrentLine = new String();
			String sTotalString = new String();
			String url = "http://paygate.baofoo.com/Check/OrderQuery.aspx?MerchantID="
					+ merchant_code
					+ "&TransID="
					+ order_no
					+ "&Md5Sign="
					+ Md5Sign;
			HttpGet get = new HttpGet(url);
			HttpClient client = new DefaultHttpClient();
			HttpResponse res = client.execute(get);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				java.io.BufferedReader l_reader = new java.io.BufferedReader(
						new java.io.InputStreamReader(entity.getContent()));
				while ((sCurrentLine = l_reader.readLine()) != null) {
					sTotalString += sCurrentLine;
				}

				// 根据分割符把数据提取到数组
				String[] s;
				StringTokenizer st = new StringTokenizer(sTotalString, "\\|");
				s = new String[st.countTokens()];
				for (int i = 0; st.hasMoreTokens(); i++) {
					s[i] = st.nextToken();
				}
				getLogger().debug("返回结果:["+sTotalString+"]");
				if (s.length >= 6) {
					String MerchantID = s[0]; // 商户号
					String TransID = s[1]; // 商户流水号
					String CheckResult = s[2]; // 支付结果
					String factMoney = s[3];// 实际成功金额,分为单位
					Float Money = Float.parseFloat(factMoney) / 100; // 成功金额，使用元单位
					String SuccTime = s[4];// 支付完成时间
					String WaitSign = s[5];// MD5签名
					String MD5 = new String(MerchantID + TransID + CheckResult
							+ factMoney + SuccTime + md5key);// MD5签名格式
					map.put("tradeStatus", orderQueryResultWrapper(CheckResult));
					String MD5Sign = getMD5ofStr(MD5);// 计算MD5值
					if (WaitSign.compareTo(MD5Sign) == 0) {
						// Y：成功 F：失败 P：处理中 N：没有订单 
						//成功
						if(CheckResult.equals("Y")){
							map.put("trade_time", SuccTime);
							map.put("order_amount", Money.toString());
							// 充值成功，且对账成功的
	                        getLogger().error("订单对账:"+factMoney+":"+String.valueOf((int)(Float.parseFloat(pMap.get("amount"))*100)));
							 if(factMoney.equals(String.valueOf((int)(Float.parseFloat(pMap.get("amount"))*100)))){
								 if (pMap.get("status").equals("0")
											|| pMap.get("status").equals("1")) {
										//我们失败，而第三方公司为成功的
										// 支付成功且对账成功,且原来为申请中或者申请失败的
										map.put("status", "2");
									}else if(pMap.get("status").equals("2")){
									   map.put("status", "1"); //支付成功，且对账成功
								   }
							 }else{
								 //对账不成功 
								 map.put("status", "3");
							 }
						} else {
							if(pMap.get("status").equals("2")){
							   map.put("status", "4");								
							}else{
								if(CheckResult.equals("N")){
									// N：没有订单 
									map.put("status","0");
								}else if(CheckResult.equals("P")||(CheckResult.equals("F"))){
									// P：处理中 N：没有订单 
									map.put("status","5");
								}
							}
						
							
						} 						
						// MD5校验通过
					} else {
						// MD5校验失败，订单信息不显示
					}
				}
			}else{
				getLogger().error(
						"请求宝付不成功!请求状态为:" + res.getStatusLine().getStatusCode()
								+ ",订单号:" + order_no);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage() + ":"
					+ ExceptionUtils.getMessage(e.getCause()) + ":"
					+ ExceptionUtils.getFullStackTrace(e.fillInStackTrace()));
		}
		return map;
	}

	@Override
	/**
	 * 宝付订单支付后回掉
	 */
	public CSPVOSupport callBack(HttpServletRequest request) throws Exception {
		String transID = request.getParameter("TransID"); // 流水号
		CSPVOSupport bean = new CSPVOSupport(); // 返回结果
		try {
			String MerchantID = request.getParameter("MerchantID");// 商户号
			String TransID = request.getParameter("TransID");// 商户流水号
			String Result = request.getParameter("Result");// 支付结果
			String resultDesc = request.getParameter("resultDesc");// 支付结果描述
			String factMoney = request.getParameter("factMoney");// 实际成功金额
			int a = Integer.parseInt(factMoney) / 100; // 使用元显示
			getLogger().debug("宝付通道回调信息:订单号["+transID+"],充值金额["+factMoney+"],支付结果["+Result+"],支付结果描述:["+resultDesc+"]");
			CSPVOSupport bean1 = new CSPVOSupport();
			bean1.setPuk(transID);// 流水号
			FrameworkDataBean db = wmbm02Service.doRead(bean1); // 充值记录
			String puk_tid = db.getK02();
			CSPVOSupport bean2 = new CSPVOSupport();
			bean2.setPuk(puk_tid);
			FrameworkDataBean dbx = wmbm01Service.doRead(bean2); // 个人通道信息
			CSPVOSupport bean3 = new CSPVOSupport();
			bean3.setPuk(dbx.getK03());
			FrameworkDataBean db2 = wmbma1Service.doRead(bean3); // 系统通道信息
			String FactMoney = String.valueOf(a);
			String additionalInfo = request.getParameter("additionalInfo");// 订单附加消息
			String SuccTime = request.getParameter("SuccTime");// 支付完成时间
			String Md5Sign = request.getParameter("Md5Sign");// MD5签名
			String Md5key = db2.getF02(); // /////////md5密钥（KEY）
			String md5 = new String(MerchantID + TransID + Result + resultDesc
					+ factMoney + additionalInfo + SuccTime + Md5key);// MD5签名格式
			String WaitSign = getMD5ofStr(md5);// 计算MD5值
			String lbresultDesc = getErrorInfo(Result, resultDesc);// 支付结果文字描述
			String OrderMoney = db.getF07();// 充值金额
			String lbOrderMoney = String.valueOf(OrderMoney);
			if (WaitSign.compareTo(Md5Sign) == 0) {
				// 校验通过开始处理订单
				if (OrderMoney.compareTo(factMoney) == 0) {
					// 卡面金额与用户提交金额一致
					// 提示：这个int类型的 如果存在小数的话，这个 除法算出的金额可能会不正确，如果存在小数
					// 推荐使用BigDecimal
				} else {

				}
			} else {

			}
			bean.setPuk(TransID); // 流水号
			if (Result.equals("0")) {
				// 失败
				bean.setF06("1");
			} else {
				// 成功
				bean.setF06("2");
			}
			bean.setBbb(lbresultDesc); // 返回描述
			bean.setPuk(TransID);
		} catch (Exception e) {
			throw new Exception(e.getMessage() + ":"
					+ ExceptionUtils.getMessage(e.getCause()) + ":"
					+ ExceptionUtils.getFullStackTrace(e.fillInStackTrace()));
		}
		return bean;
	}

	private String getErrorInfo(String result, String resultDesc) {
		String retInfo = "";
		int a;
		int b;
		if (!"".equals(result)) {
			a = Integer.parseInt(result);
		} else {
			a = 0;
		}
		if (!"".equals(resultDesc)) {
			b = Integer.parseInt(resultDesc);
		} else {
			b = 0;
		}

		if (a == 1) {
			retInfo = "支付成功";
			return retInfo;
		} else {
			switch (b) {
			case 0:
				retInfo = "充值失败";
				break;
			case 1:
				retInfo = "系统错误";
				break;
			case 2:
				retInfo = "订单超时";
				break;
			case 11:
				retInfo = "系统维护";
				break;
			case 12:
				retInfo = "无效商户";
				break;
			case 13:
				retInfo = "余额不足";
				break;
			case 14:
				retInfo = "超过支付限额";
				break;
			case 15:
				retInfo = "卡号或卡密错误";
				break;
			case 16:
				retInfo = "不合法的IP地址";
				break;
			case 17:
				retInfo = "重复订单金额不符";
				break;
			case 18:
				retInfo = "卡密已被使用";
				break;
			case 19:
				retInfo = "订单金额错误";
				break;
			case 20:
				retInfo = "支付的类型错误";
				break;
			case 21:
				retInfo = "卡类型有误";
				break;
			case 22:
				retInfo = "卡信息不完整";
				break;
			case 23:
				retInfo = "卡号，卡密，金额不正确";
				break;
			case 24:
				retInfo = "不能用此卡继续做交易";
				break;
			case 25:
				retInfo = "订单无效";
				break;
			case 26:
				retInfo = "卡无效";
				break;
			default:
				retInfo = "支付失败";
				break;
			}

			return retInfo;
		}
	}
	
	private String orderQueryResultWrapper(String result){
		if(result.equals("Y")){
			return "成功";
		}
		if(result.equals("F")){
			return "失败";
		}
		if(result.equals("P")){
			return "处理中";
		}
		if(result.equals("N")){
			return "没有订单";
		}
		return result;
	}

	private String getMD5ofStr(String str) {
		return this.getMD5ofStr(str, "utf-8");
	}

	private String getMD5ofStr(String str, String encode) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes(encode));
			byte[] digest = md5.digest();

			StringBuffer hexString = new StringBuffer();
			String strTemp;
			for (int i = 0; i < digest.length; i++) {
				// byteVar &
				// 0x000000FF的作用是，如果digest[i]是负数，则会清除前面24个零，正的byte整型不受影响。
				// (...) | 0xFFFFFF00的作用是，如果digest[i]是正数，则置前24位为一，
				// 这样toHexString输出一个小于等于15的byte整型的十六进制时，倒数第二位为零且不会被丢弃，这样可以通过substring方法进行截取最后两位即可。
				strTemp = Integer.toHexString(
						(digest[i] & 0x000000FF) | 0xFFFFFF00).substring(6);
				hexString.append(strTemp);
			}
			return hexString.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public Logger getLogger() {
		return LoggerFactory.getLogger(RongBaoService.class);
	}

}
