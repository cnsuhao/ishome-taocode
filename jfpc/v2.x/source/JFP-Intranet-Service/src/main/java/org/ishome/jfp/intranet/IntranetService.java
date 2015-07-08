package org.ishome.jfp.intranet;

import org.ishome.jfp.framework.utils.DateHelper;
import org.ishome.jfp.intranet.utils.MyIntranetAPISupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 医院内部服务工程
 * @author Spook
 * @version 2.0.1
 * @since 2.0.1 2015/7/7 2015/6/11
 */
@Controller
public class IntranetService extends MyIntranetAPISupport {

	public String getVersion(){
		return "v2.1.0 at 2015/4/1";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView doPatientNoticeSend() {
		ModelAndView model = new ModelAndView("index");
		model.addObject("ver", getVersion());
		model.addObject("now", DateHelper.currentTimeMillisCN1());
		return model;
	}
}
