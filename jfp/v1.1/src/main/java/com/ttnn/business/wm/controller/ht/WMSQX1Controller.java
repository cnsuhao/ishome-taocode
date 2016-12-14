package com.ttnn.business.wm.controller.ht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ttnn.business.cs.service.CSSS01Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;

@Controller
@RequestMapping("/WMSQX1")
public class WMSQX1Controller extends MyControllerSupportImpl {
	
	@Autowired
	private CSSS01Service csss01service;
	
	
	@RequestMapping(value="/H.go",method=RequestMethod.GET)
	public String setedAccountclose(Model model){
		CSPVOSupport param = new CSPVOSupport();
		param.setPuk(getProductId());
						
		
		FrameworkDataBean data = csss01service.doRead(param);
		
		model.addAttribute("systeminfo",  data);		
		return "WM/HT/WMSQX1";
	}
	

	

}
