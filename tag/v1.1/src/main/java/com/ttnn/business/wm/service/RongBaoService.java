package com.ttnn.business.wm.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ttnn.framework.bean.FrameworkDataBean;

/**
 * 融宝支付服务接口->网关
 * 
 * @author duan.p
 * 
 */
@Service("rongBaoService_")
public class RongBaoService extends RongBaoCommonService implements PayService {



	@Override
	public Map<String, Object> doProcess(FrameworkDataBean paramBean,
			FrameworkDataBean xttdFrameworkDataBean) {
		String merchant_ID = xttdFrameworkDataBean.getF01(); // 商户号
		String order_no = paramBean.getPuk(); // 支付流水号
		String seller_email = xttdFrameworkDataBean.getF10(); // 客户邮箱
		// String title="";
		// String body="";
		String total_fee = new BigDecimal(paramBean.getF07()).toString(); // 支付金额
		String charset = "gbk";
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String notify_url = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getLocalPort()
				+ "/WMBM06/rbtd/HOME/U.go";
		String payment_type = "1"; //
		String paymethod = "bankPay"; // 网银支付
		String defaultbank = "";

		paymethod = "directPay";// 直连
		defaultbank = paramBean.getF04();// 支付渠道

		String return_url = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getLocalPort()
				+ "/WMBM06/rbtd/HOME/U.go";
		String key = xttdFrameworkDataBean.getF02(); // 签名
		String sign_type = "MD5"; // 签名方式
		String service = "online_pay"; //

		String sign = mySign(service, payment_type, merchant_ID, seller_email,
				return_url, notify_url, charset, order_no, " ", " ", total_fee,
				"", paymethod, defaultbank, key);
		Map<String, Object> getMap = new HashMap<String, Object>();
		getMap.put("merchant_ID", merchant_ID);
		getMap.put("notify_url", notify_url);
		getMap.put("charset", charset);
		getMap.put("total_fee", total_fee);
		getMap.put("order_no", order_no);
		getMap.put("return_url", return_url);
		getMap.put("service", service);
		getMap.put("paymethod", paymethod);
		getMap.put("seller_email", seller_email);
		getMap.put("payment_type", payment_type);
		getMap.put("sign", sign);
		getMap.put("sign_type", sign_type);
		getMap.put("defaultbank", defaultbank);
		return getMap;
	}



}
