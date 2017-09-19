package org.isotope.jfp.center.sms;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISJobConstants;
import org.isotope.jfp.framework.utils.DateHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * SMSCenter<br>
 * 短信中心
 * 
 * @version 3.3.1 at 2016/09/01
 * @version 2.0.0
 * @see <JFP-Common-SMS>
 * @since 2.0.0 2015/1/22
 */
@Controller
public class SMSCenter implements ISFrameworkConstants, ISJobConstants {

	public String getVersion() {
		return "v3.3.1 at 2016/09/01";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(String t) {
		ModelAndView model = new ModelAndView("index");
		model.addObject("ver", getVersion());
		model.addObject("now", DateHelper.currentTimeMillisCN1());
		
		return model;
	}

}
