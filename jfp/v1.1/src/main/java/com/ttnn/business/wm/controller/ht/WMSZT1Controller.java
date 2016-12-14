package com.ttnn.business.wm.controller.ht;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ttnn.business.cs.service.CSSPR1Service;
import com.ttnn.business.cs.service.CSSR01Service;
import com.ttnn.business.cs.service.CSSS01Service;
import com.ttnn.business.wm.service.UserService;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.business.wm.service.WMUIP1Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;

@Controller
@RequestMapping("/WMSZT1")
public class WMSZT1Controller extends MyControllerSupportImpl {
	
	@Autowired
	private CSSS01Service csss01service;
	@Autowired
	private CSSR01Service CSSR01Service;
	
	@Autowired
	private WMUI01Service WMUI01Service;
	@Autowired
	private UserService  userService;
	@RequestMapping(value="/U.go",method=RequestMethod.POST)
	public String setAccountclose(FrameworkDataBean data,Model model){
		CSPVOSupport param = new CSPVOSupport();
		param.setPuk(data.getPuk());
		param.setFb1(data.getFb1());
		param.setFb2(data.getFb2());
	    param.setUu1(data.getUu1());
	   csss01service.doUpdate(param);

		
		return "WM/HT/success";
		
	}
	@RequestMapping(value="/R.go",method=RequestMethod.GET)
	public String setedAccountclose(CSPVOSupport param,Model model){
		
		param.setPuk(getProductId());
		FrameworkDataBean data = csss01service.doRead(param);
		
		model.addAttribute("systeminfo",data);		
		return "WM/HT/WMSZT1";
	}
	
	@RequestMapping(value = "/{userid}/K.go",method = RequestMethod.POST)
	public String doupdateuser(@PathVariable String userid,CSPVOSupport param,FrameworkDataBean data,Model model){
		//上级会员ID转换
		CSPVOSupport sj = new CSPVOSupport();
		List<FrameworkDataBean> lists = null;
		if(data.getK01() != null && !"".equals(data.getK01())){
			sj.setF03(data.getK01());
			sj.setEb5(super.getProductId());
			pageVO.setPageData(sj);
            lists = (List<FrameworkDataBean>) CSSR01Service.doSelectPage(pageVO).getPageData();
			if(lists.size() < 1){
				model.addAttribute("message", "上级会员不存在");
				pageVO.setPageData(param);
				pageVO.setPageLimit(Integer.MAX_VALUE);
				model.addAttribute("user",data);
				// 获得会员组信息
				FrameworkDataBean fdb = new FrameworkDataBean();
				fdb.setEb5(super.getProductId());// 产品ID
				fdb.setTablename("wmuip1");
				model.addAttribute("vipinfo", getDictionaryOnTable(fdb));
				return "WM/HT/WMSHU3";
			}
		}
		param.setPuk(userid);
		if(lists != null && lists.size()>0){
			param.setK01(lists.get(0).getPuk());
		 }else{
			 param.setK01("");
		 }
		param.setK02(data.getK02());
		param.setK03(data.getK03());
		param.setFb4("1");
		
		WMUI01Service.doUpdate(param);
		
//		修改所属用户组
//		终端会员
		
		CSPVOSupport groupinfo = new CSPVOSupport();
		groupinfo.setPuk(userid);
		
		if(data.getK02().equals("DFB_QT_VIP_0")){
			
			 groupinfo.setK01("DFB_QT_VIP_GROUP");
		}else{
			 groupinfo.setK01("DFB_QT_ADMIN_GROUP");
		}
		
		 
		 userService.doupdateUsergroup(groupinfo);
		
				
				 
		return "redirect:/WMKLH1/"+userid+"/R.go";
		

	}
	

	

}
