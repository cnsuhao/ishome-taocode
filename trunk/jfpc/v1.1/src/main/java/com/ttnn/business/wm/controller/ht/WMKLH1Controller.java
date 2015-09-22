package com.ttnn.business.wm.controller.ht;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ttnn.business.cs.service.CSSPR1Service;
import com.ttnn.business.cs.service.CSSR01Service;
import com.ttnn.business.wm.biz.WMHTZHYWBussiness;
import com.ttnn.business.wm.service.CardService;
import com.ttnn.business.wm.service.UserService;
import com.ttnn.business.wm.service.WMBM01Service;
import com.ttnn.business.wm.service.WMBMA1Service;
import com.ttnn.business.wm.service.WMBS01Service;
import com.ttnn.business.wm.service.WMSZT3Service;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.business.wm.service.WMUIP1Service;
import com.ttnn.common.util.PKUtil;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;

@Controller
@RequestMapping("/WMKLH1")
public class WMKLH1Controller extends MyControllerSupportImpl {
	
	@Autowired
	private WMUI01Service WMUI01Service;
	@Autowired
	private CSSR01Service CSSR01Service;
	@Autowired
	private WMBS01Service WMBS01Service;
	
	@Autowired
	private WMBMA1Service WMBMA1Service;
	
	@Autowired
	private WMBM01Service WMBM01Service;  
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WMUIP1Service WMUIP1Service;
	
	@Autowired
	private CSSPR1Service CSSPR1Service;
	
	@Autowired
	private CardService CardService;
	
	
	@Autowired
	private WMSZT3Service WMSZT3Service;
	 /**
		 * 数据一览
		 */
	
	
		@RequestMapping(value = "/F.go",method = RequestMethod.GET)
		public String doFindList(CSPVOSupport paramBean,Model model) {
			pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
			pageVO.setPageData(paramBean);				   
			
			userService.doSelectPageUser(pageVO);
			
		    model.addAttribute(pageVO);
		    model.addAttribute("param", paramBean);
			
			return "WM/HT/WMKLH1";
		}
		
		@RequestMapping(value = "{userid}/A.go",method = RequestMethod.GET)
		public String doFindfeilv(@PathVariable String userid,CSPVOSupport paramBean,Model model) {
			pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
			pageVO.setPageData(paramBean);	
			paramBean.setK01(userid);
			
			WMSZT3Service.doSelectPage(pageVO);
			
		    model.addAttribute(pageVO);
			
			return "WM/HT/WMSZT3";
		}
		
		
		@RequestMapping(value = "/S.go",method = RequestMethod.GET)
		public String doFindListFromqt(CSPVOSupport paramBean,Model model) {
			pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
			paramBean.setFb4("0");
			paramBean.setDdd("1");
			paramBean.setEb5(super.getProductId());
			pageVO.setPageData(paramBean);				   
			
			userService.doSelectPageUser(pageVO);
			
		    model.addAttribute(pageVO);
		    model.addAttribute("param", paramBean);
			
			return "WM/HT/WMSHU1";
		}
		
		@RequestMapping(value = "/Z.go",method = RequestMethod.GET)
		public String doFindBankList(CSPVOSupport paramBean,Model model) {
			pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
			pageVO.setPageData(paramBean);	
			paramBean.setF09("0");
			
			userService.doSelectPageBank(pageVO);
			
		    model.addAttribute(pageVO);
			
			return "WM/HT/WMSHK1";
		}
		
		@RequestMapping(value = "/{cardid}/M.go",method = RequestMethod.GET)
		public String checkCard(@PathVariable String cardid,CSPVOSupport paramBean) {
			paramBean.setPuk(cardid);
			paramBean.setF09("2");
			CardService.doUpdate(paramBean);
			
			return "redirect:/WMKLH1/Z.go";
		}
		
		
		@RequestMapping(value = "/{cardid}/K.go",method = RequestMethod.GET)
		public String checkCard(@PathVariable String cardid,CSPVOSupport paramBean,FrameworkDataBean data,Model model) {
			paramBean.setPuk(cardid);
			data= WMBS01Service.doRead(paramBean);
	
			model.addAttribute("check", data);
			
			return "WM/HT/WMSHK2";
		}
		
		
		@RequestMapping(value = "/{cardid}/K.go",method = RequestMethod.POST)
		public String checkCard(@PathVariable String cardid,CSPVOSupport paramBean,FrameworkDataBean data) {
			paramBean.setPuk(cardid);
			paramBean.setF10(data.getF10());
			paramBean.setF09("1");
			paramBean.setUu1(data.getUu1());
			CardService.doUpdate(paramBean);		
			return "redirect:/WMKLH1/Z.go";
		}
		

		@RequestMapping(value = "/{userid}/X.go",method = RequestMethod.GET)
		public String cancelUser(@PathVariable String userid,CSPVOSupport paramBean) {
			paramBean.setPuk(userid);
			paramBean.setDdd("1");			
			userService.cancelUser(paramBean);
			
			return "redirect:/WMKLH1/"+userid+"/R.go";
		}
		
		@RequestMapping(value = "/{userid}/I.go",method = RequestMethod.GET)
		public String userUser(@PathVariable String userid,CSPVOSupport paramBean) {
			paramBean.setPuk(userid);
			paramBean.setDdd("0");			
			userService.cancelUser(paramBean);
			paramBean.setFb4("1");
			userService.checkUser(paramBean);
			return "redirect:/WMKLH1/"+userid+"/R.go";
		}
		
		
		
		
		@RequestMapping(value = "/{userid}/G.go",method = RequestMethod.GET)
		public String checkPass(@PathVariable String userid,CSPVOSupport paramBean) {
			paramBean.setPuk(userid);
			paramBean.setFb4("1");
			userService.checkUser(paramBean);
			
			paramBean.setDdd("0");
			userService.cancelUser(paramBean);
			
			return "redirect:/WMKLH1/S.go";
		}
		
		@RequestMapping(value = "/{userid}/R.go",method = RequestMethod.GET)
		public String showDetail(@PathVariable String userid,CSPVOSupport paramBean,Model model) {
			paramBean.setPuk(userid);
			FrameworkDataBean data= userService.showDetail(paramBean);
			model.addAttribute("user", data);
			return "WM/HT/WMSHU3";
		}
		
		
		@RequestMapping(value = "/{userid}/W.go",method = RequestMethod.GET)
		public String checkPass(@PathVariable String userid,CSPVOSupport paramBean,FrameworkDataBean data,Model model) {
			paramBean.setPuk(userid);
			data= userService.doRead(paramBean);
	
			model.addAttribute("check", data);
			
			return "WM/HT/WMSHU2";
		}
		
		
		@RequestMapping(value = "/{userid}/W.go",method = RequestMethod.POST)
		public String checkPass(@PathVariable String userid,CSPVOSupport paramBean,FrameworkDataBean data) {
			paramBean.setPuk(userid);
			paramBean.setFb3(data.getFb3());
			paramBean.setFb4("2");
			paramBean.setUu1(data.getUu1());
			userService.doUpdate(paramBean);		
			return "redirect:/WMKLH1/S.go";
		}
		
		@RequestMapping(value = "/C.go",method = RequestMethod.GET)
		public String docreate(CSPVOSupport param,Model model){
			pageVO.setPageData(param);
			pageVO.setPageLimit(Integer.MAX_VALUE);
			model.addAttribute("userinfo",new FrameworkDataBean());
			model.addAttribute("vipinfo",WMUIP1Service.doSelectPage(pageVO).getPageData());
		
			return "WM/HT/WMAKU1";
		}
		
	
		
		
		@RequestMapping(value = "/C.go",method = RequestMethod.POST)
		public String doopen(CSPVOSupport param,FrameworkDataBean data,Model model){
			//TODO
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
					model.addAttribute("userinfo",data);
					// 获得会员组信息
					FrameworkDataBean fdb = new FrameworkDataBean();
					fdb.setEb5(super.getProductId());// 产品ID
					fdb.setTablename("wmuip1");
					model.addAttribute("vipinfo", getDictionaryOnTable(fdb));
					return "WM/HT/WMAKU1";
				}
			}
			
			String username = data.getF03();
			if(username != null && !"".equals(username)){
				int count = userService.countUsername(username);
				if(count >= 1){
					model.addAttribute("message", "用户名已存在");
					pageVO.setPageData(param);
					pageVO.setPageLimit(Integer.MAX_VALUE);
					model.addAttribute("userinfo",data);
					// 获得会员组信息
					FrameworkDataBean fdb = new FrameworkDataBean();
					fdb.setEb5(super.getProductId());// 产品ID
					fdb.setTablename("wmuip1");
					model.addAttribute("vipinfo", getDictionaryOnTable(fdb));
					return "WM/HT/WMAKU1";
				}else{
//					 param.setBbb(data.getBbb());
				     String puk = PKUtil.getPUKey();
				     param.setPuk(puk);
					 param.setF03(data.getF03());
					 param.setF04(new Md5PasswordEncoder().encodePassword(data.getF04(), null));
					 param.setF02("Y");
					 CSSR01Service.doInsert(param);
					 CSPVOSupport userinfo = new CSPVOSupport();
					 userinfo.setPuk(puk);
//					 插入puk
//					 userinfo.setK01(data.getK01());
					 if(lists != null && lists.size()>0){
						 userinfo.setK01(lists.get(0).getPuk());
					 }else{
						 userinfo.setK01("");
					 }
					
					 userinfo.setK02(data.getK02());
					 userinfo.setF04(data.getBbb());
					 userinfo.setFb5(data.getFb5());
					 userinfo.setK03(data.getK03());
					 userinfo.setFb4("1");
					 userinfo.setF01("0");
					 userinfo.setF02("0");
					 userinfo.setF07("0");
					 userinfo.setF08("0");
					 userinfo.setF09("0");
					 userinfo.setF10("0");
					 WMUI01Service.doInsert(userinfo);
					 CSPVOSupport groupinfo = new CSPVOSupport();
					 groupinfo.setPuk(puk);
//					 f02的值存进来
					 CSPVOSupport newpa =  new CSPVOSupport();
					 newpa.setPuk(data.getK02());
					 
					 FrameworkDataBean vip =  WMUIP1Service.doRead(newpa);
					 String f02 = vip.getF02();
					 groupinfo.setK01(f02);
					 CSSPR1Service.doInsert(groupinfo);
					 
					return "redirect:/WMKLH1/"+puk+"/P.go";
				}
			}else{
				model.addAttribute("message", "用户名不能为空");
				return "WM/HT/WMAKU1";
			}

		}
		
		
		@RequestMapping(value = "/{userid}/B.go",method = RequestMethod.GET)
		public String docard(@PathVariable String userid,FrameworkDataBean data,Model model){
			data.setK01(userid);
			model.addAttribute("cardinfo",data);
			return "WM/HT/WMAKK1";
		}
		
//		添加银行卡
		@RequestMapping(value = "/{userid}/B.go",method = RequestMethod.POST)
		public String doCard(@PathVariable String userid,FrameworkDataBean data,Model model){
			CSPVOSupport param = new CSPVOSupport();
			param.setPuk(PKUtil.getPUKey());
			param.setF01(data.getF01());
			param.setF04(data.getF04());
			param.setF05(data.getF05());
			param.setF01(data.getF01());
			param.setF02(data.getF02());
			param.setF03(data.getF03());
			param.setK01(data.getK01());
			param.setK02(data.getK02());
			param.setFb3(data.getFb3());
			param.setF09("2");

			WMBS01Service.doInsert(param);
			return "redirect:/WMKLH1/F.go";
		}
//个人银行卡管理		
		@RequestMapping(value = "/{userid}/Y.go")
		public String doShowcard(@PathVariable String userid,CSPVOSupport paramBean,FrameworkDataBean data,Model model){
			pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
			pageVO.setPageData(paramBean);	
			paramBean.setK01(userid);
			
			userService.doSelectPageBank(pageVO);
			
		    model.addAttribute(pageVO);
			
			return "WM/HT/WMSHK1_CARD";
		}

		@Resource
		WMHTZHYWBussiness WMHTZHYWBussiness_;
		 
//		通道分配
		@RequestMapping(value = "/{userid}/P.go",method = RequestMethod.GET)
		public String doRoad(CSPVOSupport paramBean,@PathVariable String userid,FrameworkDataBean data,Model model){
			
			paramBean = new CSPVOSupport();
			paramBean.setPuk(userid);
			FrameworkDataBean userinfo = WMUI01Service.doRead(paramBean);
			model.addAttribute("userinfo",userinfo);
			
//			分配过的通道去掉
			//param.setK01(userid);
			pageVO.setPageData(paramBean);
			pageVO.setPageLimit(Integer.MAX_VALUE);
			
			
			paramBean.setEb5(super.getProductId());
			
			pageVO.setPageData(paramBean);
			
			String sjuserid = userinfo.getK01();
			
////			查看上级代理商是否有通道
//			CSPVOSupport para = new CSPVOSupport();
//			para.setK01(sjuserid);
//			para.setEb5(super.getProductId());
//			
//			List<FrameworkDataBean> yfpdt = userService.doFindgrtd(para);
			
			if(sjuserid != null && !"".equals(sjuserid)){
				paramBean.setK01(userid);
				paramBean.setK02(sjuserid);
				WMHTZHYWBussiness_.doSelectPageSjtd(pageVO);
			}else{
				paramBean.setK01(userid);
				WMHTZHYWBussiness_.doSelectPageFPTD(pageVO);
			}
						
//			model.addAttribute("systemRoad", pageVO.getPageData());
			model.addAttribute("list", pageVO.getPageData());
			model.addAttribute(pageVO);
			return "WM/HT/WMFPTD";//WMAKK2";
		}
		
		

		@RequestMapping(value = "/{userid}/{roadid}/P.go",method = RequestMethod.GET)
		public String doRoadfp(@PathVariable String userid,@PathVariable String roadid,CSPVOSupport param,Model model){
//			区分系统通道和个人通道
			CSPVOSupport paramBean = new CSPVOSupport();
			paramBean.setPuk(userid);
			FrameworkDataBean userinfo = WMUI01Service.doRead(paramBean);
			
			String sjuserid = userinfo.getK01();
			
			
			param.setPuk(roadid);
			FrameworkDataBean data = null;
			FrameworkDataBean ymdata = new FrameworkDataBean();
			if(sjuserid != null &&!"".equals(sjuserid)){
//			个人通道	
				data = WMBM01Service.doRead(param);
				ymdata.setEb3(data.getEb3());
				ymdata.setEb4(data.getEb4());
				ymdata.setK03(data.getK03());
				ymdata.setF06(data.getF06());
				ymdata.setFb1(data.getFb1());
			}else{
//				系统通道
				data = WMBMA1Service.doRead(param);
				
				ymdata.setEb3(data.getEb1());
				ymdata.setEb4(data.getEb2());
				ymdata.setK03(data.getPuk());
				ymdata.setF06(data.getF14());
				ymdata.setFb1(data.getF03());
			}
			
			
			
			
			 
			
			
			ymdata.setK01(userid);
//			ymdata.setK03(data.getPuk());
//			ymdata.setF05(data.getF13());
//			ymdata.setF06(data.getF14());
			ymdata.setF15(data.getF15());
			ymdata.setF16(data.getF16());
			ymdata.setF17(data.getF17());
			ymdata.setF18(data.getF18());
			ymdata.setF19(data.getF19());
//			ymdata.setFb1(data.getF03());

//			ymdata.setEb3(data.getEb1());
//			ymdata.setEb4(data.getEb2());
			
			model.addAttribute("roadinfo", ymdata);
			

			return "WM/HT/WMAKK2";
		}
		
		
		

		@RequestMapping(value = "/{userid}/{roadid}/P.go",method = RequestMethod.POST)
		public String doRoadfp(@PathVariable String userid,CSPVOSupport param,FrameworkDataBean data,Model model){
			String roadid = data.getK03();
//			param.setPuk(roadid);
//			FrameworkDataBean systemRoad = WMBMA1Service.doRead(param);
			
			CSPVOSupport paramuser = new CSPVOSupport();
			paramuser.setPuk(userid);
			FrameworkDataBean userinfo = WMUI01Service.doRead(paramuser);
			String sjuserid = userinfo.getK01();
			
			CSPVOSupport insertParam = new CSPVOSupport();
			String newroadid = PKUtil.getPUKey();
			insertParam.setPuk(newroadid);
			insertParam.setK01(data.getK01());
			insertParam.setK03(roadid);
//			insertParam.setF05(systemRoad.getF13());
			insertParam.setF06(data.getF06());
			insertParam.setFb2("0");
			insertParam.setFb3("0");
			insertParam.setFb4("0");
			insertParam.setF05("%");
			if(sjuserid != null && !"".equals(sjuserid)){
				insertParam.setK02(sjuserid);
			}
//			个人通道名称
			insertParam.setFb1(data.getFb1());
			insertParam.setF15(data.getF15());
			insertParam.setF16(data.getF16());
			insertParam.setF17(data.getF17());
			/*if(data.getF15()!= null && !"".equals(data.getF15())){
				insertParam.setF15(data.getF15());
			}else{
				insertParam.setF15(systemRoad.getF15());
			}
			
			
			if(data.getF16()!= null && !"".equals(data.getF16())){
				insertParam.setF16(data.getF16());
			}else{
				insertParam.setF16(systemRoad.getF16());
			}
			
			if(data.getF17()!= null && !"".equals(data.getF17())){
				insertParam.setF17(data.getF17());
			}else{
				insertParam.setF17(systemRoad.getF17());
			}*/
			
//			insertParam.setF16(systemRoad.getF16());
//			insertParam.setF17(systemRoad.getF17());
			insertParam.setF18(data.getF18());
			insertParam.setF19(data.getF19());
			
			insertParam.setEb3(data.getEb3());
			insertParam.setEb4(data.getEb4());
			
			WMBM01Service.doInsert(insertParam);
			
			
			
			CSPVOSupport cardparam = new CSPVOSupport();
			cardparam.setPuk(PKUtil.getPUKey());
			cardparam.setF01(data.getF01());
			cardparam.setK03(newroadid);
			cardparam.setF04(data.getF04());
			cardparam.setF05(data.getF05());
			cardparam.setF01(data.getF01());
			cardparam.setF02(data.getF02());
			cardparam.setF03(data.getF03());
			cardparam.setK01(data.getK01());
			cardparam.setK02(data.getK02());
			cardparam.setFb3(data.getFb3());
			cardparam.setF09("2");

			WMBS01Service.doInsert(cardparam);
			
			

			return "redirect:/WMKLH1/F.go";
		}
		
		
		
		
		
		@RequestMapping(value = "/{userid}/P.go",method = RequestMethod.POST)
		public String doRoad(CSPVOSupport param,FrameworkDataBean data,Model model){
			String roadid = data.getK03();
			param.setPuk(roadid);
			FrameworkDataBean systemRoad = WMBMA1Service.doRead(param);
			CSPVOSupport insertParam = new CSPVOSupport();
			insertParam.setPuk(PKUtil.getPUKey());
			insertParam.setK01(data.getK01());
			insertParam.setK03(systemRoad.getPuk());
			insertParam.setF05(systemRoad.getF13());
			insertParam.setF06(systemRoad.getF14());
			insertParam.setFb2("0");
			insertParam.setFb3("0");
			insertParam.setFb4("0");
			insertParam.setF05("%");
//			个人通道名称
			insertParam.setFb1(systemRoad.getF03());
			if(data.getF15()!= null && !"".equals(data.getF15())){
				insertParam.setF15(data.getF15());
			}else{
				insertParam.setF15(systemRoad.getF15());
			}
			
			
			if(data.getF16()!= null && !"".equals(data.getF16())){
				insertParam.setF16(data.getF16());
			}else{
				insertParam.setF16(systemRoad.getF16());
			}
			
			if(data.getF17()!= null && !"".equals(data.getF17())){
				insertParam.setF17(data.getF17());
			}else{
				insertParam.setF17(systemRoad.getF17());
			}
			
//			insertParam.setF16(systemRoad.getF16());
//			insertParam.setF17(systemRoad.getF17());
			insertParam.setF18(systemRoad.getF18());
			insertParam.setF19(systemRoad.getF19());
			
			WMBM01Service.doInsert(insertParam);
			
			
			
			CSPVOSupport cardparam = new CSPVOSupport();
			cardparam.setPuk(PKUtil.getPUKey());
			cardparam.setF01(data.getF01());
			cardparam.setF04(data.getF04());
			cardparam.setF05(data.getF05());
			cardparam.setF01(data.getF01());
			cardparam.setF02(data.getF02());
			cardparam.setF03(data.getF03());
			cardparam.setK01(data.getK01());
			cardparam.setK02(data.getK02());
			cardparam.setF09("2");

			WMBS01Service.doInsert(cardparam);
			
			

			return "redirect:/WMKLH1/F.go";
		}
		
//		实名认证
		@RequestMapping(value = "/{userid}/N.go",method = RequestMethod.GET)
		public String doName(Model model){
			
			model.addAttribute("userinfo",new FrameworkDataBean());
			return "WM/HT/WMAKU1";
		}
		
		
		@RequestMapping(value = "/{userid}/N.go",method = RequestMethod.POST)
		public String doName(CSPVOSupport param,FrameworkDataBean data,Model model){
			
			model.addAttribute("userinfo",new FrameworkDataBean());
			return "redirect:/WMKLH1/F.go";
		}
		
		
		@RequestMapping(value = "/{userid}/{cardid}/D.go",method = RequestMethod.GET)
		public String doDeletecard(@PathVariable String userid,@PathVariable String cardid,CSPVOSupport paramBean,Model model) {
			paramBean.setPuk(cardid);
			
			WMBS01Service.doDelete(paramBean);
			
			return "redirect:/WMKLH1/"+userid+"/Y.go";
		}
		@RequestMapping(value = "/{userid}/{cardid}/U.go",method = RequestMethod.GET)
		public String doupdate(CSPVOSupport param,@PathVariable String cardid,Model model){
			param.setPuk(cardid);
			
			FrameworkDataBean data = WMBS01Service.doRead(param);
			model.addAttribute("cardinfo",data);
			return "WM/HT/Card_UPDATE";
		}
		
		@RequestMapping(value = "/{userid}/{cardid}/U.go",method = RequestMethod.POST)
		public String doupdate(CSPVOSupport param,@PathVariable String cardid,@PathVariable String userid,FrameworkDataBean data,Model model){
			 param.setPuk(data.getPuk());
			 param.setUu1(data.getUu1());
			 param.setK01(data.getK01());
			 param.setK02(param.getK02());
			 param.setF01(param.getF01());
			 param.setF02(data.getF02());
			 param.setF03(data.getF03());
			 param.setF04(data.getF04());
			 param.setF05(data.getF05());
			 param.setF07(data.getF07());
			 param.setFb3(data.getFb3());
			 WMBS01Service.doUpdate(param);
			return "redirect:/WMKLH1/"+userid+"/Y.go";
		}
		

	

}
