package com.aek56.qt.WUD;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jfpc.beans.common.MSSUU.MSSUUDBO;
import org.jfpc.beans.common.MSSUU.MSSUUService;
import org.jfpc.common.sms.SMSBean;
import org.jfpc.common.sms.SMSService;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.helper.PKHelper;
import org.jfpc.framework.helper.PhoneHelper;
import org.jfpc.framework.helper.RandomHelper;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXDBO;
import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXService;
import com.aek56.atm.company.MYY0_JBXX.MYY0_JBXXDBO;
import com.aek56.atm.company.MYY0_JBXX.MYY0_JBXXService;
import com.aek56.constants.ISAek56SMSConstants;

/**
 * 个人帐号管理
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/5/30
 */
@Controller
public class WUDController extends MyControllerSupport implements ISAek56SMSConstants{
	private static final Logger logger = LoggerFactory.getLogger(WUDController.class);


	/**
	 * 默认首页
	 * 
	 * @return
	 */
	public MyModelAndViewSupport getModelAndView() {
		return new MyModelAndViewSupport("qt/WUD/WUD01");
	}
	/**
	 * 用户基本资料
	 */
	@Resource
	private MSSUUService MSSUUService_;
	/**
	 * 医院基本信息
	 */
	@Resource
	private MYY0_JBXXService myy0_JBXXService;
	/**
	 * 供应商基本信息
	 */
	@Resource
	private MGYS0_JBXXService mgys0_JBXXService;
	/**
	 * 短信服务
	 */
	@Resource
	SMSService smsService;

	/**
	 * 个人帐号信息及 企业信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/3140205", method = RequestMethod.POST)
	public MyModelAndViewSupport m3140205POST(String index) {
		MyModelAndViewSupport mv = getModelAndView();
		MSSUUDBO user = new MSSUUDBO();
		user.setPuk(super.getLoginerId());
		String isadmin = super.getLoginerBean().getIsAdmin();
		if (logger.isDebugEnabled())
			logger.error("/3140205//////////>>>>>>=====" + user.getPuk());
		//获取用户基本信息
		user = (MSSUUDBO) MSSUUService_.doRead(user);
		if (StringUtils.isNotEmpty(user.getFb3()) && user.getFb3().length() > 10)
			user.setFb3(PhoneHelper.getProtectedPhoneNumber(user.getFb3()));
		mv.addObject(USER_DATA, user);
		mv.addObject("isadmin",isadmin);
		mv.addObject("index", index);
		//如果是超级管理员 则获取所属企业信息
		if(ZERO.equals(isadmin)){
			String userType = user.getF02_yhlb();
			if(TWO.equals(userType)){
				MYY0_JBXXDBO pbean = new MYY0_JBXXDBO();
				pbean.setPuk(user.getK01_ssqyid());
				MYY0_JBXXDBO hospital = (MYY0_JBXXDBO)myy0_JBXXService.doRead(pbean);
				mv.addObject("company",hospital);
			}
			else if(ONE.equals(userType)){
				MGYS0_JBXXDBO mgys0 = new MGYS0_JBXXDBO();
				mgys0.setPuk(user.getK01_ssqyid());
				MGYS0_JBXXDBO supplier = (MGYS0_JBXXDBO)mgys0_JBXXService.doRead(mgys0);
				mv.addObject("company",supplier);
			}
		}
		return mv;
	}

	/**
	 * 修改企业资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "/31402050", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m31402050POST(MYY0_JBXXDBO formParamBean) {
		String userType = super.getLoginerBean().getUserType();
		RESTResultBean rt = new RESTResultBean();
		int flag = 0;
		formParamBean.setPuk(super.getCompanyId());
		//供应商用户
		if(ONE.equals(userType)){
			MGYS0_JBXXDBO mgys = new MGYS0_JBXXDBO();
			BeanUtils.copyProperties(formParamBean, mgys);
			flag = mgys0_JBXXService.doUpdate(mgys);
		}
		//医院用户
		else{
			flag = myy0_JBXXService.doUpdate(formParamBean);
		}
		if(flag==1){
			rt.setMessage(messageModel.getLocalMessage("314020501"));
		}else{
			rt.setMessage(messageModel.getLocalMessage("314020502"));
			rt.setCode(ONE);
		}
		return rt;
	}

	/**
	 * 验证手机码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/31402052", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m31402052POST(String mobile, String verCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("mobile==" + mobile);
			logger.debug("verCode==" + verCode);
		}
		RESTResultBean rb = new RESTResultBean();
		if (checkRandomCode(verCode) == false) {
			rb.setCode(ONE);
			rb.setMessage(messageModel.getLocalMessage("314020521"));
		} else {
			int rt = 0;// SMSHelper.validateSMSCode(mobile, "31402053", code);
			MSSUUDBO user = new MSSUUDBO();
			user.setPuk(super.getLoginerId());
			user = (MSSUUDBO) MSSUUService_.doRead(user);
			if (!user.getFb3().equals(mobile)) {
				rt = 1;
			}
			// 验证验证码
			if (rt != 0) {
				rb.setCode(ONE);
				rb.setMessage(messageModel.getLocalMessage("314020522"));
			}
		}
		return rb;
	}


	/**
	 * 手机号码变更
	 * 
	 * @return
	 */
	@RequestMapping(value = "/31402053", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m31402053POST(MSSUUDBO user,String verCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("user==" + user);
			logger.debug("verCode==" + verCode);
		}
		RESTResultBean rb = new RESTResultBean();
		user.setPuk(super.getLoginerId());
		int flag = MSSUUService_.doUpdate(user);
		if(flag==1){
			rb.setMessage(messageModel.getLocalMessage("314020533"));
		}else{
			rb.setCode(ONE);
			rb.setMessage(messageModel.getLocalMessage("314020534"));
		}
		return rb;
	}
	
	
	
	/**
	 * 密码修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/31402054", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m31402054POST(MSSUUDBO user, String oldpassword) {
		if (logger.isDebugEnabled()) {
			logger.debug("user==" + user);
			logger.debug("oldpassword==" + oldpassword);
		}
		RESTResultBean rb = new RESTResultBean();
		user.setPuk(super.getLoginerId());
		if (StringUtils.isNotEmpty(oldpassword)) {
			MSSUUDBO mb = (MSSUUDBO) MSSUUService_.doRead(user);
			oldpassword = new Md5PasswordEncoder().encodePassword(oldpassword, null);
			if (!oldpassword.equals(mb.getK04_dlmm())) {
				rb.setMessage(messageModel.getLocalMessage("314020541"));
				rb.setCode(ONE);
				return rb;
			}
		} else {
			rb.setMessage(messageModel.getLocalMessage("314020542"));
			rb.setCode(ONE);
			return rb;
		}
		user.setK04_dlmm(new Md5PasswordEncoder().encodePassword(user.getK04_dlmm(), null));
		int flag = MSSUUService_.doUpdate(user);
		if(flag==1){
			rb.setMessage(messageModel.getLocalMessage("314020543"));
		}else{
			rb.setCode(ONE);
			rb.setMessage(messageModel.getLocalMessage("314020544"));
		}
		return rb;
	}

	/**
	 * 验证邮件
	 * 
	 * @return
	 */
	@RequestMapping(value = "/31402055", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m31402055POST(MSSUUDBO user, String verCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("user==" + user);
			logger.debug("verCode==" + verCode);
		}
		RESTResultBean rb = new RESTResultBean();
		if (checkRandomCode(verCode) == false) {
			rb.setCode(ONE);
			rb.setMessage(messageModel.getLocalMessage("314020551"));
		} else {
			user.setPuk(super.getLoginerId());
			user.setEb1(StringUtils.EMPTY);
			int flag = MSSUUService_.doUpdate(user);
			if(flag==1){
				rb.setMessage(messageModel.getLocalMessage("314020552"));
			}else{
				rb.setCode(ONE);
				rb.setMessage(messageModel.getLocalMessage("314020553"));
			}
		}
		return rb;
	}
	
	/**
	 * 弹窗页面跳转
	 * @return
	 */
	@RequestMapping(value = "/31402056", method = RequestMethod.GET)
	public MyModelAndViewSupport gopage(String page){
		MyModelAndViewSupport mv = new MyModelAndViewSupport("qt/WUD/"+page);
		return mv;
	}

}
