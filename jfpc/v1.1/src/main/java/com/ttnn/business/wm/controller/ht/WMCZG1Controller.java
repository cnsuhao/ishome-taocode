package com.ttnn.business.wm.controller.ht;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ttnn.business.wm.biz.WMHTMXBusiness;
import com.ttnn.business.wm.biz.WMHTTotalBusiness;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;

@Controller
@RequestMapping("/WMCZG1")
public class WMCZG1Controller extends MyControllerSupportImpl {
	
	
	@Resource
	private WMHTTotalBusiness WMHTTotalBusiness;

	@Resource
	private WMHTMXBusiness WMHTMXBusiness;
	
	 /**
		 * 数据一览
		 */
	
	
		@RequestMapping(value = "/F.go")
		public String doFindList(CSPVOSupport paramBean,Model model) {
			pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));

			paramBean.setF05("%Y/%m/%d");
			paramBean.setEb5(super.getProductId());
			pageVO.setPageData(paramBean);	
			pageVO.setOrderby(" order by eb4 desc ");
			
			WMHTMXBusiness.doSelectPageCZMX(pageVO);
			
		    model.addAttribute(pageVO);
		    model.addAttribute("param", paramBean);
			
			return "WM/HT/WMCZG1";
		}
		

		@RequestMapping(value = "/Q.go",method = RequestMethod.GET)
		public String doQuery(Model model) {

			
			return "WM/HT/WMCZG1Q";
		}
		
		
		
		@RequestMapping(value = "/T.go")
		public String recharge(CSPVOSupport paramBean,Model model) {
			pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
			paramBean.setF05("%Y/%m");
			paramBean.setEb5(super.getProductId());
			pageVO.setPageData(paramBean);				   
			
//			toalService.doSelectRecharge(pageVO);
			WMHTTotalBusiness.doSelectPageCZ(pageVO);
			
		    model.addAttribute(pageVO);
			
			return "WM/HT/WMCZG2";
		}

}
