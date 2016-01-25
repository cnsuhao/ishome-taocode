package org.isotope.jfp.center.monitor;

import org.ishome.jfp.framework.beands.common.RedisChannelConfigBean;
import org.ishome.jfp.framework.beands.pub.SMSBean;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.constants.pub.ISJobConstants;
import org.ishome.jfp.framework.support.ISSMSSupport;
import org.ishome.jfp.framework.utils.DateHelper;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;

/**
 * SMSCenter<br> 
 * 短信中心
 * 
 * @version 2.0.0
 * @see <RedisMonitorThread>
 * @since 2.0.0 2015/1/22
 */
@Controller
public class MonitorCenter implements ISFrameworkConstants, ISJobConstants {

	public String getVersion() {
		return "v2.1.0 at 2015/4/1";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(String t) {
		ModelAndView model = new ModelAndView("index");
		model.addObject("ver", getVersion());
		model.addObject("now", DateHelper.currentTimeMillisCN1());
		if(EmptyHelper.isNotEmpty(t))
			testSMS();
		return model;
	}
	
	public void testSMS(){
		Jedis jedis = RedisChannelConfigBean.getJedis("127.0.0.1", 6379, "hundsun-med-redis", 0);
		String keyList = ISSMSSupport.CONFIG_KEY;// + COLON + "YZM";
			
			SMSBean message = new SMSBean();
			
			message.setPhoneNum("15057177411");
		
			message.setMessage("【恒生芸泰】尊敬的用户1123，aaa掌医APP提醒您，您的手机挂号记录rrrrrrrrrrrr，因（专家停诊）已被取消xxxxxxxxxx。如有疑问请联系热线12345。");
			message.setMessage("【恒生芸泰】验证码1111，1111掌医APP提醒您，短信验证码有效时间为1111，22222。如有疑问请联系热线11111。");
			message.setMessage("【恒生芸泰】尊敬的用户1111，1111掌医APP提醒您，由于您的账户11111，系统根据手机挂号规则对您的APP挂号行为进行限制，1111后将自动解除限制。如有疑问请联系热线1111。");
			message.setMessage("【恒生芸泰】尊敬的用户222，111掌医APP提醒您，您已成功预约222科111医生，12313。如有疑问请联系热线1111。");
			message.setMessage("【恒生芸泰】尊敬的用户111，1111掌医APP提醒您，您的预约记录111111111111111111因未能在规定时间内完成支付，导致本次挂号失败。如有疑问请联系热线11111。");
			message.setMessage("【恒生芸泰】尊敬的用户222，111掌医APP提醒您，您的手机挂号记录22222已成功取消11111。如有疑问请联系热线111。");
			//message.setMessage("【恒生芸泰】尊敬的用户，aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa。退订回N");
		
			jedis.rpush(keyList, JSON.toJSONString(message));
	}

}
