package org.isotope.jfp.dwc.business.job;

import java.util.ArrayList;
import java.util.Date;

import org.isotope.jfp.framework.support.MyJobSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.utils.StringUtil;
import org.springframework.web.servlet.ModelAndView;

public class AJobServiceSupport extends MyJobSupport {
	StringBuffer serverURL;

	ArrayList<Integer> pages = new ArrayList<Integer>();
	static int MAX = 63186722;// 63147989
	static int size = 678;

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

	boolean stop = false;

	public String getServerURL(int page_num) {
		serverURL = new StringBuffer(50);
		serverURL.append("http://");
		serverURL.append(StringUtil.completion(8, "0", "" + page_num));
		serverURL.append(".11315.com");
		return serverURL.toString();
	}

	public void loadJobCongig(ModelAndView model) {
		model.addObject("FTP:PATH", myMqService.getObject(jobKey + ":FTP:PATH", false));
		model.addObject("TASK:INVEL", myMqService.getObject(jobKey + ":TASK:INVEL", false));
		String page_num = (String) myMqService.pollFirstObjectInList(jobKey + ":PAGE:NUM", false);
		if (EmptyHelper.isEmpty(page_num))
			return;
		int now = Integer.parseInt(page_num);
		if (stop == true) {
			model.addObject(jobKey + ":HTTP:URL", "" + "http://www.baidu.com");
		} else {
			if (now % size == (size - 1)) {
				stop = true;
			}

			model.addObject(jobKey + ":HTTP:URL", "" + getServerURL(now));
		}
		myMqService.offerObjectInList(jobKey + ":PAGE:NUM", "" + (now + 1), false);
		model.addObject(jobKey + ":FILE:NAME", "" + jobKey);

	}
}
