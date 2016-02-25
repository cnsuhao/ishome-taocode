package org.isotope.jfp.dwc.business.job;

import java.util.ArrayList;

import org.isotope.jfp.dwc.business.DistributedServerController;
import org.isotope.jfp.framework.support.MyJobSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.utils.StringUtil;
import org.springframework.web.servlet.ModelAndView;

public abstract class AJobServiceSupport extends MyJobSupport {
	protected StringBuffer serverURL;

	ArrayList<Integer> pages = new ArrayList<Integer>();
	static int MAX = 63186722;// 63147989

	public void loadJobCongig(ModelAndView model) {
		model.addObject("FTP_PATH", myMqService.getObject("FTP:PATH:" + jobKey, false));
		model.addObject("TASK_INVEL", myMqService.getObject("TASK:INVEL:" + jobKey, false));
		model.addObject("FILE_NAME", "" + jobKey);

		getServerURL(model);
	}

	protected boolean stop = false;

	public void getServerURL(ModelAndView model) {
		String page_num = (String) myMqService.pollFirstObjectInList("PAGE:NUM:" + jobKey, false);
		if (EmptyHelper.isEmpty(page_num)) {
			model.addObject("HTTP_URL", "http://www.baidu.com");
			model.addObject("TASK_TYPE", "1");
			model.addObject("FILE_NAME", "");
			return;
		}
		int now = Integer.parseInt(page_num);
		if (stop == true) {
			model.addObject("HTTP_URL", "http://www.baidu.com");
			model.addObject("TASK_TYPE", "1");
		} else {
			if (now % DistributedServerController.NUM_SIZE == (DistributedServerController.NUM_SIZE - 1)) {
				model.addObject("HTTP_URL", getServerURL(now));
				model.addObject("TASK_TYPE", "1");
				stop = true;
			} else {
				model.addObject("HTTP_URL", getServerURL(now));
				model.addObject("TASK_TYPE", "0");
				myMqService.offerObjectInList("PAGE:NUM:" + jobKey, "" + (now + 1), false);
			}
		}
	}

	public String getServerURL(int page_num) {

		serverURL = new StringBuffer(50);
		serverURL.append("http://");
		serverURL.append(StringUtil.completion(8, "0", "" + page_num));
		serverURL.append(".11315.com");
		return serverURL.toString();
	}
}