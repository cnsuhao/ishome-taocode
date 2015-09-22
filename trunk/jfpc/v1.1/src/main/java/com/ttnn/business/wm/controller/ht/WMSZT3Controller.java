package com.ttnn.business.wm.controller.ht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ttnn.business.wm.service.UserService;
import com.ttnn.business.wm.service.WMSZT3Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;

@Controller
@RequestMapping("/WMSZT3")
public class WMSZT3Controller extends MyControllerSupportImpl {
	
	@Autowired
	private WMSZT3Service WMSZT3Service;
	
	@Autowired
	private UserService userService;
	
	 /**
		 * 数据一览
		 */
	
	
		@RequestMapping(value = "/F.go",method = RequestMethod.GET)
		public String doFindList(CSPVOSupport paramBean,Model model) {
			pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
			pageVO.setPageData(paramBean);				   
			
			try {
				WMSZT3Service.doSelectPage(pageVO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		    model.addAttribute(pageVO);
		    model.addAttribute("param", paramBean);
			
			return "WM/HT/WMSZT3";
		}
		
		/**
		 * 分页显示  有问题
		 * 
		 * @param paramPageModel
		 * @return
		 * @see ISSQLDaoSupport#doSelectList(PageVO)
		 */
		
		@RequestMapping(value = "/{rateid}/U.go", method = RequestMethod.GET)
		public String goupdate(@PathVariable String rateid,CSPVOSupport paramBean,Model model) {
			paramBean.setPuk(rateid);
			FrameworkDataBean rateinfo = WMSZT3Service.doRead(paramBean);
			model.addAttribute("rateinfo", rateinfo);
		    return "WM/HT/WMSZT3C1";
		}
		
		
		
		@RequestMapping(value = "/{rateid}/U.go", method = RequestMethod.POST)
		public String doupdate(CSPVOSupport paramBean,FrameworkDataBean data,Model model) {
			paramBean.setPuk(data.getPuk());
			paramBean.setUu1(data.getUu1());
			paramBean.setF15(data.getF15());
			paramBean.setF16(data.getF16());
			paramBean.setF17(data.getF17());
			WMSZT3Service.doUpdate(paramBean);
			
		    return "redirect:/WMSZT3/F.go";
		}
		
		

		@RequestMapping(value = "/{userid}/X.go", method = RequestMethod.GET)
		public String doShowrealname(@PathVariable String userid,CSPVOSupport paramBean,FrameworkDataBean data,Model model) {
			paramBean.setPuk(userid);
			data = userService.showDetailRealname(paramBean);
			model.addAttribute("user", data);
			
		    return "WM/HT/SMRZXQ";
		}
		

		@RequestMapping(value = "/{userid}/X.go", method = RequestMethod.POST)
		public String doCheckreal(@PathVariable String userid,CSPVOSupport paramBean,FrameworkDataBean data,Model model) {
			paramBean.setPuk(userid);
			paramBean.setBbb(data.getBbb());
			paramBean.setF18("1");
			userService.checkReal(paramBean);
			
		    return "redirect:/WMSZT3/"+userid+"/X.go";
		}
		
		@RequestMapping(value = "/{userid}/P.go", method = RequestMethod.GET)
		public String doCheckrealpass(@PathVariable String userid,CSPVOSupport paramBean,FrameworkDataBean data,Model model) {
			paramBean.setPuk(userid);
			paramBean.setBbb(data.getBbb());
			paramBean.setF18("2");
			userService.checkReal(paramBean);
			
		    return "redirect:/WMSZT3/"+userid+"/X.go";
		}
		
		
		
		@RequestMapping(value = "/N.go",method = RequestMethod.GET)
		public String doFindRealname(CSPVOSupport paramBean,Model model) {
			pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
			pageVO.setPageData(paramBean);				   
			userService.doSelectPageUserRealname(pageVO);
						
		    model.addAttribute(pageVO);
		    model.addAttribute("param", paramBean);
			
			return "WM/HT/SMRZLB";
		}

	

}
