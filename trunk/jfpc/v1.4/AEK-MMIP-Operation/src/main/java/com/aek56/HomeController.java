package com.aek56;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jfpc.beans.common.CS0D1.CS0D1Service;
import org.jfpc.beans.common.MS0C1.MS0C1DBO;
import org.jfpc.beans.common.MS0C1.MS0C1Service;
import org.jfpc.beans.common.MS0M2.MS0M2DBO;
import org.jfpc.beans.common.MS0M2.MS0M2Service;
import org.jfpc.common.menu.MenuBean;
import org.jfpc.common.menu.MenuService;
import org.jfpc.framework.bean.LoginerBean;
import org.jfpc.framework.helper.TokenHelper;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aek56.constants.ISSystemConstants;

/**
 * 用户登录后页面
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/5/30
 */
@Controller
public class HomeController extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Resource
	MenuService MenuService_;

	/**
	 * 默认首页
	 * 
	 * @return
	 */
	public MyModelAndViewSupport getModelAndView() {
		return new MyModelAndViewSupport("home");
	}
//	@Resource
//    MS1A1Service MS1A1Service_;
	@Resource
    MS0C1Service MS0C1Service_;
	@Resource
	MS0M2Service MS0M2Service_;
//	@Resource
//	CMPBusiness CMPBusiness_;
//	@Resource
//	MZSX_GYS_ZJXXService MZSX_GYS_ZJXXService_;
//	@Resource
//	MYG0_YYGYSXXService MYG0_YYGYSXXService_;
	
	/**
	 * 公告详情
	 * @param puk
	 * @return
	 */
	@RequestMapping(value="/31402010",method = RequestMethod.POST)
	public MyModelAndViewSupport m31402010GET(MS0C1DBO dbo){
		MyModelAndViewSupport mv =  getModelAndView();
		mv.setViewName("WU/WU01");
		mv.addObject(USER_DATA, MS0C1Service_.doRead(dbo));
		return mv;
	}
	
	/**
	 * 帮助详情
	 * @param puk
	 * @return
	 */
	@RequestMapping(value="/31402011",method = RequestMethod.POST)
	public MyModelAndViewSupport m31402011GET(MS0C1DBO dbo){
		MyModelAndViewSupport mv =  getModelAndView();
		mv.setViewName("WU/WU02");
		mv.addObject(USER_DATA, MS0M2Service_.doRead(dbo));
		
		return mv;
	}
	
	@Resource
	CS0D1Service CS0D1Service_;
	
	/**
	 * 用户订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/3140314/{puk}/{type}", method = RequestMethod.GET)
	@ResponseBody
	public String orderGET(@PathVariable String puk,
			@PathVariable String type) {
		
		return "{\"sysId\":169}";
	}
	
	/**
	 * 用户首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/3000000", method = RequestMethod.POST)
	public MyModelAndViewSupport homePOST(String menuType) {
		MyModelAndViewSupport mv = new MyModelAndViewSupport("home/home9");// +loginer.getUserType());
		LoginerBean loginer = (LoginerBean)getSessionAttribute(CONSTANT_LOGINER);
		//登录失败校验
		if(loginer == null) {
			mv.setViewName("/error/403");
			return mv; 
		}
		if (logger.isDebugEnabled())
			logger.debug("loginer====///loginer////loginer=======>>>>>=========>>>" + loginer);
		
		// 用户类型判断
		// 0运维人员1供应商2医院3厂商9客服
		if ("0".equals(loginer.getUserType())) {
			mv.setViewName("home/home0");
			// 检索菜单
			List<?> menus = MenuService_.loadMenu(loginer.getUserType());
			mv.addObject(MENU, menus);
			//SESSION数据缓存
			setSessionAttribute(MENU, menus);
		} else if ("1".equals(loginer.getUserType())) {
			mv.setViewName("home/home1");
			//首页查询
			//我的证书，过期提醒
			//获得注册证信息
			//供应商证件信息
//			MyDataBaseObjectSupport formParamBean = new MyDataBaseObjectSupport();
//			formParamBean.setPuk(TokenHelper.getCompanyId(loginer.getToken()));
//			mv.addObject(USER_DATA, MZSX_GYS_ZJXXService_.doRead(formParamBean));
			
			//医院公告
//			pageModel.config();
//			pageModel.setPageCurrent(1);
//			pageModel.setPageLimit(5);
//			CS0D1DBO cmp = new CS0D1DBO();
//			cmp.setF02_sjrnc(super.getCompanyId());//接收企业ID
//			pageModel.setFormParamBean(cmp);
//			pageModel = CS0D1Service_.doSelectPage(pageModel);
//			mv.addObject("YYGG", pageModel);
			
		} else if ("2".equals(loginer.getUserType())) {
			mv.setViewName("home/home2");
			
//			//首页查询
//			//我的供应商(12)  我要添加        三个月内过期(12)  短信提醒        已过期(12)  短信提醒
//			
//			//获得注册证信息
//			//医院供应商信息
//			mv.addObject(USER_DATA, CMPBusiness_.loadMyCredentialsByHospital(TokenHelper.getCompanyId(loginer.getToken())));
//
//			//我的供应商
//			MYG0_YYGYSXXDBO formParamBean = new MYG0_YYGYSXXDBO();			
//			formParamBean.setPuk(TokenHelper.getCompanyId(loginer.getToken()));
//			formParamBean.setGx2(ZERO);
//			pageModel.config();
//			pageModel.setPageCurrent(1);
//			pageModel.setPageLimit(5);
//			pageModel.setFormParamBean(formParamBean);
//			pageModel = MYG0_YYGYSXXService_.doSelectPage(pageModel,false);			
//			mv.addObject("WDGYS", pageModel.getPageListData());
			
		} else if ("3".equals(loginer.getUserType())) {
			mv.setViewName("home/home3");
		} else {
			mv.setViewName("home/home9");
			// 检索菜单
			List<?> menus = MenuService_.loadMenu(loginer.getUserType());
			mv.addObject(MENU, menus);
			//SESSION数据缓存
			setSessionAttribute(MENU, menus);
		}
		
		if (StringUtils.isEmpty(menuType)) {
			MenuBean user = new MenuBean();	
			user.setPuk(TokenHelper.getUserID(loginer.getToken()));
			user.setF06_cddj("1");
			List<?> menus = MenuService_.loadMenu(user);
			mv.addObject(MENU, menus);
			//SESSION数据缓存
			setSessionAttribute(MENU, menus);
		} 	
		
		//查询系统公告
		pageModel.config();
		pageModel.setPageCurrent(0);
		pageModel.setPageLimit(5);
		MS0C1DBO xt = new MS0C1DBO();
		xt.setPpp(ISSystemConstants.SYSTEM);
		pageModel.setFormParamBean(xt);		
		mv.addObject("gg", MS0C1Service_.doSelectPage(pageModel,false).getPageListData());
		//帮助中心
		pageModel.config();
		pageModel.setPageCurrent(0);
		pageModel.setPageLimit(5);
		MS0M2DBO bz = new MS0M2DBO();
		pageModel.setFormParamBean(bz);		
		mv.addObject("bz", MS0M2Service_.doSelectPage(pageModel,false).getPageListData());
		
		return mv;
	}
	
//	/**
//	 * 医院点击首页 三个月内过期  短信提醒
//	 * @return
//	 * @throws Exception 
//	 */
//	@RequestMapping(value = "/31403141", method = RequestMethod.POST)
//	public MyModelAndViewSupport m31403141POST() throws Exception {
//		CMPBusiness_.sendSmsOnCredentialsWith3MByHospital();
//		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_WEB, "短信发送成功", "/3140314?menuType=MS0A1");
//	}
//	
//	/**
//	 * 医院点击首页 已过期  短信提醒
//	 * @return
//	 * @throws Exception 
//	 */
//	@RequestMapping(value = "/31403142", method = RequestMethod.POST)
//	public MyModelAndViewSupport m31403142POST() throws Exception {
//		CMPBusiness_.sendSmsOnCredentialsNOWByHospital();
//		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_WEB, "短信发送成功", "/3140314?menuType=MS0A1");
//	}
//	
//	/**
//	 * 医院点击首页提醒供应商
//	 * @return
//	 * @throws Exception 
//	 */
//	@RequestMapping(value = "/31403143", method = RequestMethod.POST)
//	public MyModelAndViewSupport m31403143POST(String puk) throws Exception {
//		CMPBusiness_.sendSmsOnCredentialsNOWByHospital(puk);
//		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_WEB, "短信发送成功", "/3140314?menuType=MS0A1");
//	}
}
