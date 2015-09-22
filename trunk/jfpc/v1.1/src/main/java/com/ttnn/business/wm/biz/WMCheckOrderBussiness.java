package com.ttnn.business.wm.biz;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;

import com.ttnn.business.cs.fvo.UserBean;
import com.ttnn.business.wm.service.PayService;
import com.ttnn.business.wm.service.PayServiceFactory;
import com.ttnn.business.wm.service.WMBM01Service;
import com.ttnn.business.wm.service.WMBM02Service;
import com.ttnn.business.wm.service.WMBMA1Service;
import com.ttnn.common.util.DateUtil;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSModelAndViewSupport;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Service
public class WMCheckOrderBussiness extends MyServiceSupportImpl {
	@Resource
	WMBM02Service WMBM02Service_;

	@Resource
	WMBM01Service WMBM01Service_;

	@Resource
	WMBMA1Service WMBMA1Service_;
	
	@Resource
	PayServiceFactory payServiceFactory;

	@Resource
	WMQTCZTXBussiness WMQTCZTXBussiness_;

	/**
	 * 订单核对
	 * @param db 充值记录
	 * @throws Exception
	 */
	public Map<String,Object> doCheckOrder(FrameworkDataBean db) throws Exception{
		Map<String,Object> retmap = new HashMap<String, Object>();
		try {
			String cid = db.getK02(); // 个人通道ID
			CSPVOSupport csp = new CSPVOSupport();
			csp.setPuk(cid);
			FrameworkDataBean user_channel = WMBM01Service_.doRead(csp); // 个人通道
			CSPVOSupport csp2 = new CSPVOSupport();
			if (user_channel != null) {
				csp2.setPuk(user_channel.getK03());
				FrameworkDataBean system_channel = WMBMA1Service_
						.doRead(csp2); // 系统通道
				if (system_channel != null) {
					PayService payService = payServiceFactory
							.getPayService(system_channel.getFb2());
					Map<String,String> pMap= new HashMap<String,String>();
					pMap.put("merchant_code", system_channel.getF01()); //商户号
					pMap.put("md5Key", system_channel.getF02()); //密钥
					pMap.put("order_no", db.getPuk()); //订单号
					pMap.put("amount", db.getF07()); //订单金额
					pMap.put("status", db.getF06()); //支付状态
					
					Map<String, String> map = payService.checkOrder(pMap);

					// 0,查询失败 -》订单部存在
					// 查询成功下:
					// 对账成功: 1，我方为支付成功，且对账成功 //2.对方为支付成功，我方为失败 //
					// 对账不成功：3. 账目不一致
					// 支付失败: 4,我方成功，对方为失败 5.支付不成功

					if (map.containsKey("status")) {
						if (map.get("status").equals("0")) {
							// 更新状态
							CSPVOSupport asb = new CSPVOSupport();
							asb.setPuk(db.getPuk());
							asb.setUu1(db.getUu1());
							asb.setF11("2");
							if(map.containsKey("errorCode")){
								asb.setF17(map.get("errorCode"));
							}else{
								asb.setF17(map.get("tradeStatus"));	
							}
							WMBM02Service_.doUpdate1(asb);
						} else if (map.get("status").equals("1")) {
							CSPVOSupport asb = new CSPVOSupport();
							asb.setPuk(db.getPuk());
							asb.setUu1(db.getUu1());
							asb.setF11("2");
							asb.setF17(map.get("tradeStatus"));	
							int k = WMBM02Service_.doUpdate1(asb);
							if (k == 0) {
								getLogger().error(
										"更新充值记录失败:" + db.getPuk());
							}
						} else if (map.get("status").equals("2")) {
							// 加钱
							db.setPuk(db.getPuk());
							db.setF06("2");// 设为充值成功
							db.setF11("2");// 设为对账成功
							if (map.containsKey("trade_no")) {
								db.setF08(map.get("trade_no"));// 第三方公司支付订单号
							}
							if (map.containsKey("bank_code")) {
								db.setF04(map.get("bank_code")); // 银行号
							}
							
							db.setF17(map.get("tradeStatus"));	// 银行返回说明
							
							//
							getLogger().warn(
									"通道对账成功!原来是充值状态为" + db.getF06()
											+ "的，对账后，现在更新为充值成功,订单号:"
											+ db.getPuk());
							View view = null;
							/*UserBean user = new UserBean();
							user.setProductId(db.getEb5()); //当前数据的EB5
							user.setUserId("TTNN_SYSTEM");
							user.setLoginDateTime(DateUtil
									.currentTimestamp());
							WMQTCZTXBussiness_.init(user);*/
							db.setDdd("P");
							WMQTCZTXBussiness_.doCZUpdate(db,
									new CSModelAndViewSupport(view));
						} else if (map.get("status").equals("3")) {

							getLogger().error(
									"通道对账失败!把个人通道ddd属性设置为1本系统订单充值金额为:"
											+ db.getF07() + ",而第三方支付公司为"
											+ map.get("order_amount")
											+ ",本系统订单号:" + db.getPuk());
							// 同条交易与外部对账出现问题
							// 锁定通道
							CSPVOSupport support = new CSPVOSupport();
							support.setPuk(db.getK02());// 个人通道ID
							support.setUu1(db.getUu1()); // 时间
							support.setDdd("9"); // 提现suo
							int k = WMBM01Service_.doUpdate1(support);
							if (k == 0) {
								getLogger().error(
										"锁定通道失败:通道ID为:" + db.getK02());
							}
						} else if (map.get("status").equals("4")) {
							getLogger().error(
									"在第三方系统中为失败," + ",订单号:" + db.getPuk()
											+ ",而在我方系统中为充值成功,故进行锁定通道");
							// 锁定通道
							CSPVOSupport support = new CSPVOSupport();
							support.setPuk(db.getK02());// 个人通道ID
							support.setUu1(db.getUu1()); // 时间
							support.setDdd("9"); // 提现锁定
							int k = WMBM01Service_.doUpdate1(support);
							if (k == 0) {
								getLogger().error(
										"锁定通道失败:通道ID为:" + db.getK02());
							}
						} else if (map.get("status").equals("5")) {
							// 支付失败,更新状态
							CSPVOSupport asb = new CSPVOSupport();
							asb.setPuk(db.getPuk());
							asb.setUu1(db.getUu1());
							asb.setF11("2");
							if(map.containsKey("errorCode")){
								asb.setF17(map.get("errorCode"));
							}else{
								asb.setF17(map.get("tradeStatus"));	
							}
							int k = WMBM02Service_.doUpdate1(asb);
							if (k == 0) {
								getLogger().error(
										"更新充值记录失败:" + db.getPuk());
							}
						}

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
			
		}
		return retmap;
	}
	
	
}
