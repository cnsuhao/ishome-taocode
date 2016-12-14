package com.aek56;

import java.util.List;

import javax.annotation.Resource;

import org.jfpc.beans.common.CS0D1.CS0D1DBO;
import org.jfpc.beans.common.CS0D1.CS0D1Service;
import org.jfpc.common.menu.MenuService;
import org.jfpc.framework.bean.LoginerBean;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXDBO;
import com.aek56.atm.company.MYY0_JBXX.MYY0_JBXXDBO;
import com.aek56.atm.company.cmp.CompanyService;
import com.aek56.qt.credentials.CredentialsBusiness;

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
	@Resource
	CredentialsBusiness CredentialsBusiness_;
	@Resource
	CompanyService CompanyService_;
	/**
	 * 默认首页
	 * 
	 * @return
	 */
	public MyModelAndViewSupport getModelAndView() {
		return new MyModelAndViewSupport("home");
	}

	//内部消息
		@Resource
		CS0D1Service CS0D1Service_;
	/**
	 * 获取用户信息显示在右上角
	 * 
	 * @return
	 */
	@RequestMapping(value = "/3000003", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m3000003POST() {
		RESTResultBean rb = new RESTResultBean();
		LoginerBean loginer = super.getLoginerBean();		
		{
			pageModel.config();
			CS0D1DBO dbo = new CS0D1DBO();
			dbo.setFb4(ONE);
			dbo.setEb5(super.getCompanyId());
			pageModel.setFormParamBean(dbo);
			loginer.setDdd(""+CS0D1Service_.doSelectPage(pageModel, false).getResultCount());
		}
		rb.setResult(loginer);
		return rb;
	}
	
//	/**
//	 * 公告详情
//	 * @param puk
//	 * @return
//	 */
//	@RequestMapping(value="/31402010",method = RequestMethod.POST)
//	public MyModelAndViewSupport m31402010GET(MS0C1DBO dbo){
//		MyModelAndViewSupport mv =  getModelAndView();
//		mv.setViewName("WU/WU01");
//		mv.addObject(USER_DATA, MS0C1Service_.doRead(dbo));
//		return mv;
//	}
//	
//	/**
//	 * 帮助详情
//	 * @param puk
//	 * @return
//	 */
//	@RequestMapping(value="/31402011",method = RequestMethod.POST)
//	public MyModelAndViewSupport m31402011GET(MS0C1DBO dbo){
//		MyModelAndViewSupport mv =  getModelAndView();
//		mv.setViewName("WU/WU02");
//		mv.addObject(USER_DATA, MS0M2Service_.doRead(dbo));
//		
//		return mv;
//	}
	
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
	 * 用户基本信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/3000001", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m3000001POST(String token) {
		RESTResultBean rs = new RESTResultBean();
		LoginerBean loginer = (LoginerBean)getSessionAttribute(CONSTANT_LOGINER);
		rs.setResult(loginer);
		return rs;
	}
	
	/**
	 * 用户左侧菜单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/30000002", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m3000002POST(String token) {
		RESTResultBean rs = new RESTResultBean();
		LoginerBean loginer = (LoginerBean)getSessionAttribute(CONSTANT_LOGINER);
		// 检索菜单
		List<?> menus = MenuService_.loadMenu(ONE,EMPTY,loginer.getUserType());
		rs.setResult(menus);
		return rs;
	}
	
	
	/**
	 * 根据一级菜单ID获取二级菜单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/30000002/{menuId}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m30000002menuIdPOST(@PathVariable String menuId,String token) {
		RESTResultBean rs = new RESTResultBean();
		LoginerBean loginer = (LoginerBean)getSessionAttribute(CONSTANT_LOGINER);
		// 检索二级菜单
		List<?> menus = MenuService_.loadMenu(TWO,menuId,loginer.getUserType());
		rs.setResult(menus);
		return rs;
	}
	
	/**
	 * 我的采购单数据
	 * @Title: m1000233POST 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param year
	 * @param @return
	 * @return RESTResultBean
	 * @throws
	 */
	@RequestMapping(value = "/10233", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m1000233POST(String year){
		RESTResultBean rs = new RESTResultBean();
		
		rs.setResult(new double[]{7,6.9,9.5,14.5,18.2,21.5,25.2,26.5,23.3,18.3,13.9,9.6});
		
		return rs;
	}
	
	/**
	 * 用户首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/32100123", method = RequestMethod.POST)
	public MyModelAndViewSupport m32100123POST(String token) {
		MyModelAndViewSupport mv = new MyModelAndViewSupport("home/home9");
		LoginerBean loginer = (LoginerBean)getSessionAttribute(CONSTANT_LOGINER);
		// 用户类型判断
		// 0运维人员1供应商2医院3厂商9客服
		if ("1".equals(loginer.getUserType())) {			
			//证件总数(左1)
			mv.addObject("WDKHZSZ1", CredentialsBusiness_.countNumBySupplier(super.getCompanyId()));
			//客户总数(左2)
			mv.addObject("WDKHZSZ2", CredentialsBusiness_.countNumWithSupplier(super.getCompanyId()));
			//证件统计(右1)
			mv.addObject("ZJTJY1", CredentialsBusiness_.countCredentialsNumWithSupplier(super.getCompanyId()));
			mv.setViewName("home/home1");
		} else if ("2".equals(loginer.getUserType())) {
			//证件总数(左1)
			mv.addObject("WDKHZSZ1", CredentialsBusiness_.countNumByHospital(super.getCompanyId()));
			//客户总数(左2)
			mv.addObject("WDKHZSZ2", CredentialsBusiness_.countNumWithHospital(super.getCompanyId()));
			//证件统计(右1)
			mv.addObject("ZJTJY1", CredentialsBusiness_.countCredentialsNumWithHospital(super.getCompanyId()));
			mv.setViewName("home/home2");
		}
		return mv;		
	}	
	
	/**
	 * 用户首页
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/3000000", method = RequestMethod.POST)
	public MyModelAndViewSupport homePOST(String menuType) throws Exception {
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
		} else if ("1".equals(loginer.getUserType())) {
			MGYS0_JBXXDBO minf = (MGYS0_JBXXDBO) CompanyService_.myInfo(super.getCompanyId(), "1");
			if(minf == null) {
				logger.error("企业资料不存在");
				mv.setViewName("/");
				return mv; 
			}
			CredentialsBusiness_.totalCustomCredentials(EMPTY,super.getCompanyId());
			loginer.setCompanyNameCN(minf.getF01_qyqc());
			loginer.setCompanyPhone(minf.getF16_lxdh());
			loginer.setCompanyLogo(minf.getF19_logo_url());
			loginService.doUpdateByToken(loginer);
			mv.setViewName("home/main");
		} else if ("2".equals(loginer.getUserType())) {
			MYY0_JBXXDBO minf = (MYY0_JBXXDBO) CompanyService_.myInfo(super.getCompanyId(), "2");
			if(minf == null) {
				logger.error("企业资料不存在");
				mv.setViewName("/");
				return mv; 
			}
			CredentialsBusiness_.totalCustomCredentials(super.getCompanyId(),EMPTY);
			loginer.setCompanyNameCN(minf.getF01_qyqc());
			loginer.setCompanyPhone(minf.getF16_lxdh());
			loginer.setCompanyLogo(minf.getF19_logo_url());
			loginService.doUpdateByToken(loginer);
			mv.setViewName("home/main");
		} else if ("3".equals(loginer.getUserType())) {
			mv.setViewName("home/main");
		} else {
			
		}
		
		return mv;
	}
	
}
