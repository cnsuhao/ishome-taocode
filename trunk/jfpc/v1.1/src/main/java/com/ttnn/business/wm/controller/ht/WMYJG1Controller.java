package com.ttnn.business.wm.controller.ht;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ttnn.business.wm.biz.WMHTMXBusiness;
import com.ttnn.business.wm.biz.WMHTTotalBusiness;
import com.ttnn.business.wm.service.TotalService;
import com.ttnn.business.wm.service.WMBM04Service;
import com.ttnn.business.wm.service.WMCZG1Service;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;

@Controller
@RequestMapping("/WMYJG1")
public class WMYJG1Controller extends MyControllerSupportImpl {
	

	@Resource
	private WMHTMXBusiness WMHTMXBusiness;
	
	@Resource
	private WMHTTotalBusiness WMHTTotalBusiness;
	 /**
		 * 数据一览
		 */
	@RequestMapping(value = "/F.go")
	public String doFindList1(CSPVOSupport paramBean,Model model) {

		paramBean.setF05("%Y/%m/%d");
		paramBean.setEb5(super.getProductId());
		
		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		pageVO.setPageData(paramBean);	
		pageVO.setOrderby(" order by eb4 desc ");
		
		WMHTMXBusiness.doSelectPageYJMX(pageVO);
	    model.addAttribute(pageVO);

		model.addAttribute("param", paramBean);
		
		return "WM/HT/WMYJG1";
	}
	
		@RequestMapping(value = "/F.go",method = RequestMethod.POST)
		public String doFindList(CSPVOSupport paramBean,Model model) {

			paramBean.setF05("%Y/%m/%d");
			paramBean.setEb5(super.getProductId());
			
			pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
			pageVO.setPageData(paramBean);				   
			
			WMHTMXBusiness.doSelectPageYJMX(pageVO);
		    model.addAttribute(pageVO);
		    System.out.println(pageVO.getPageData());

//			model.addAttribute("page", pageVO);
			
			return "WM/HT/WMYJG1";
		}
		
		@RequestMapping(value = "/Q.go",method = RequestMethod.GET)
		public String doQuery(Model model) {

			
			return "WM/HT/WMYJG1Q";
		}
		@RequestMapping(value = "/T.go")
		public String recharge1(CSPVOSupport paramBean,Model model) {
			pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
			paramBean.setF05("%Y/%m");
			paramBean.setEb5(super.getProductId());
			pageVO.setPageData(paramBean);				   
			
//			toalService.doSelectCommission(pageVO);
			
			WMHTTotalBusiness.doSelectPageYJ(pageVO);
			
		    model.addAttribute(pageVO);
			
			return "WM/HT/WMYJG2";
		}
		@RequestMapping(value = "/T.go",method = RequestMethod.POST)
		public String recharge(CSPVOSupport paramBean,Model model) {
			pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
			paramBean.setF05("%Y/%m/%d");
			paramBean.setEb5(super.getProductId());
			pageVO.setPageData(paramBean);				   
			
//			toalService.doSelectCommission(pageVO);
			
			WMHTTotalBusiness.doSelectPageYJ(pageVO);
			
		    model.addAttribute(pageVO);
			
			return "WM/HT/WMYJG2";
		}
		

	

}
