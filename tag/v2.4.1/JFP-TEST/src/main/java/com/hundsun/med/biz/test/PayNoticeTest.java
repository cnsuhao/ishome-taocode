package com.hundsun.med.biz.test;

import com.alibaba.fastjson.JSON;
import com.hundsun.med.access.hao.pay.PayNoticeHAO;
import com.hundsun.med.framework.utils.HttpServiceHelper;

public class PayNoticeTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// 调用实际业务
		PayNoticeHAO param = new PayNoticeHAO();
		param.setActionType(1);//退款
		param.setHosId("2c9082834cc77afb014ccad1c68b0015");
		param.setBizType("1");//预约挂号
		param.setAcsBizId("115070319170426244357");//对接预约挂号ID
		param.setRefundAmount(7d);//退款金额
//http://123.56.3.239:7777/
		String response = HttpServiceHelper.doHttpPOST("http://123.56.3.239:7777/v210/hos/clound/2c9082834cc77afb014ccad1c68b0015/PayNotice/D", JSON.toJSONString(param));
		//String response = HttpServiceHelper.doHttpPOST("http://123.56.4.7:8888/v210/hos/clound/2c9082834cc77afb014ccad1c68b0015/PayNotice/D", JSON.toJSONString(param));
		//String response = HttpServiceHelper.doHttpPOST("http://127.0.0.1:8888/v210/hos/clound/2c9082834cc77afb014ccad1c68b0015/PayNotice/D", JSON.toJSONString(param));
System.out.print(response);
	}

}
