package org.isotope.jfp.dwc.biz.job.impl;

import java.util.Date;

import org.isotope.jfp.dwc.biz.job.AJobServiceSupport;
import org.isotope.jfp.dwc.control.DistributedServerController;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service("QiChaCha")
public class QiChaChaJobServiceSupport extends AJobServiceSupport {

	public static void main(String[] args) throws Exception {

		System.out.println(new Date(1453959635533l));

		// String KEY = "PAGE11315:NUM";
		// Jedis jedis = new Jedis("120.27.199.218", 6379);
		// jedis.auth("ImxV@ly1D4bBtGwv");
		// // Jedis jedis = new Jedis("127.0.0.1", 6379);
		// for (int i = 0; i < MAX; i = i + size)
		// jedis.rpush(KEY, "" + i);
		// jedis.set("FTP:PATH", "11315");
		// jedis.set("TASK:INVEL", "1000");
	}

	
}
