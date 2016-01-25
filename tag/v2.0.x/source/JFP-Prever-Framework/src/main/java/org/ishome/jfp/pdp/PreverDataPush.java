package org.ishome.jfp.pdp;

import org.ishome.jfp.framework.utils.DateHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Hospital data push
 * 医院对接同步
 * @author Spook 
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 *
 */
@Controller
public class PreverDataPush {
	
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
