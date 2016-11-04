package org.isotope.jfp.framework.net;

import java.util.HashMap;

import org.isotope.jfp.framework.utils.HttpServiceHelper;

public class AAAA {

	public static void main(String[] args) throws Exception {
		String rs = "";
		HashMap<String, String> param = new HashMap<String, String>();
		{
			for(int i=0;i<100;i++){
				param.clear();
				param.put("agentId", "24");	
				param.put("message", "微信消息通知测试100次，第"+i+"次");	
				param.put("mediaType", "text");	
				rs = HttpServiceHelper.doHttpPOST("http://127.0.0.1:8668/weixin/message/users/123456789?userIds=28,29,30,58,59&token=1222813_64_15_06_27_68_19_5__4__4__0__9",param);
				Thread.sleep(1000);
			}
		}
		{
			param.clear();
			param.put("companyName", "9");
			param.put("configKey", "CAPTURE:CONFIG:330000");
			//rs = HttpServiceHelper.doHttpPOST("http://127.0.0.1:8080/captureOL/companyinto/fcy001745",param);
		}
		{
			param.clear();
			param.put("index", "9");
			param.put("configKey", "CAPTURE:CONFIG:330000");
//			rs = HttpServiceHelper.doHttpPOST("http://127.0.0.1:8080/captureMR/config/getSystemConfig",param);
		}
		
		
		System.out.println(rs);
	}

}
