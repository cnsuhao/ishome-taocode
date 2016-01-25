package com.hundsun.his;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hundsun.his.utils.MyHospitalAPISupport;
import com.hundsun.med.framework.utils.DateHelper;

/**
 * 医院内部服务工程
 * @author fucy
 * @version 2.3.0
 * @since 2.3.0 2015/6/11
 */
@Controller
public class HospitalService extends MyHospitalAPISupport {

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
