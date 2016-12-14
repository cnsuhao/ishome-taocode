package com.hundsun.med.biz.test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.hundsun.med.access.hao.scheduling.StopSchedulingHAO;
import com.hundsun.med.framework.utils.HttpServiceHelper;

public class StopSchedulingTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// 调用实际业务
		StopSchedulingHAO param = new StopSchedulingHAO();
		List<String> schedulings = new ArrayList<String>();
		
		schedulings.add("1vsche15303||97");
		
		param.setSchedulings(schedulings);
		
//http://123.56.3.239:7777/
//		String response = HttpServiceHelper.doHttpPOST("http://123.56.3.239:7777/v210/hos/clound/2c9082834cc77afb014ccad1c68b0015/StopScheduling/D", JSON.toJSONString(param));
		//String response = HttpServiceHelper.doHttpPOST("http://123.56.4.7:8888/v210/hos/clound/2c9082834cc77afb014ccad1c68b0015/StopScheduling/D", JSON.toJSONString(param));
		String response = HttpServiceHelper.doHttpPOST("http://127.0.0.1:8888/v210/hos/clound/2c9082834cc77afb014ccad1c68b0015/StopScheduling/D", JSON.toJSONString(param));
System.out.print(response);
	}

}
