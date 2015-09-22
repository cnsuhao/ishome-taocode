package com.aek56.qt;

import javax.annotation.Resource;

import org.jfpc.beans.common.MSSUU.MSSUUDBO;
import org.jfpc.beans.common.MSSUU.MSSUUService;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.MessagePageHelper;
import org.jfpc.framework.helper.PKHelper;
import org.jfpc.framework.support.MyBusinessSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXDBO;
import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXService;
import com.aek56.atm.company.MYY0_JBXX.MYY0_JBXXDBO;
import com.aek56.atm.company.MYY0_JBXX.MYY0_JBXXService;
import com.aek56.atm.master.MD2_GYSXX.MD2_GYSXXDBO;
import com.aek56.atm.master.MD2_GYSXX.MD2_GYSXXService;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXDBO;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXService;

/**
 * 用户登录后页面
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/5/30
 */
@Controller
public class REGController extends MyBusinessSupport {
	private static final Logger logger = LoggerFactory.getLogger(REGController.class);

	/**
	 * 用户注册
	 * 
	 * @param puk
	 * @return
	 */
	@RequestMapping(value = "/3001000", method = RequestMethod.GET)
	public MyModelAndViewSupport m3001000GET() {
		return new MyModelAndViewSupport("qt/gys/greg");
	}

	/**
	 * 用户注册
	 * 
	 * @param puk
	 * @return
	 */
	@RequestMapping(value = "/3002000", method = RequestMethod.GET)
	public MyModelAndViewSupport m3002000GET() {
		return new MyModelAndViewSupport("qt/yy/yreg");
	}

	/**
	 * 用户注册
	 * 
	 * @param puk
	 * @return
	 */
	@RequestMapping(value = "/3001001", method = RequestMethod.POST)
	@Transactional
	public MyModelAndViewSupport m3001001POST(MSSUUDBO user, String verCode) {
		MyModelAndViewSupport mv = new MyModelAndViewSupport();

		if (super.checkRandomCode(verCode) == false) {
			return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_WWW, "注册失败，验证码错误", "/", false);
		}

		// 用户名唯一性检查
		RESTResultBean rs = loginService.accountCheck(user.getK03_dlyhm(), false);
		if (ZERO.equals(rs.getCode())) {
			mv.addObject(PAGE_MESSAGE, "用户名已经存在，请输入新的用户名！【" + user.getK03_dlyhm() + "】");
			user.setK03_dlyhm(user.getK03_dlyhm() + "01");
			mv.addObject(USER_DATA, user);
			// 设定页面显示信息
			if (ONE.equals(user.getF02_yhlb())) {
				mv.setViewName("qt/gys/greg");
			} else if (TWO.equals(user.getF02_yhlb())) {
				mv.setViewName("qt/yy/yreg");
			}
			return mv;
		}

		// 手机号码检查唯一性
		rs = loginService.accountCheck(user.getFb3(), true);
		if (ZERO.equals(rs.getCode())) {
			mv.addObject(PAGE_MESSAGE, "手机号码已经绑定，请更换！【" + user.getFb3() + "】");
			mv.addObject(USER_DATA, user);
			// 设定页面显示信息
			if (ONE.equals(user.getF02_yhlb())) {
				mv.setViewName("qt/gys/greg");
			} else if (TWO.equals(user.getF02_yhlb())) {
				mv.setViewName("qt/yy/yreg");
			}
			return mv;
		}
		if (logger.isDebugEnabled())
			logger.debug(user.toString());
		// 用户ID
		String userid = PKHelper.creatPUKey();
		// 企业ID
		String cmpid = user.getK01_ssqyid();
		// 来源于主数据
		if (EmptyHelper.isEmpty(cmpid)) {
			cmpid = PKHelper.creatPUKey();
			user.setK01_ssqyid(cmpid);
		}

		// 来源于主数据
		if (EmptyHelper.isEmpty(user.getPuk())) {
			// 插入企业基本信息
			if (ONE.equals(user.getF02_yhlb())) {
				MGYS0_JBXXDBO gys = new MGYS0_JBXXDBO();
				gys.setPuk(cmpid);
				gys.setF01_qyqc(user.getEb1());
				gys.setF37(userid);
				gys.setPpp(cmpid);
				MGYS0_JBXXService_.doInsert(gys);
			} else if (TWO.equals(user.getF02_yhlb())) {
				MYY0_JBXXDBO yy = new MYY0_JBXXDBO();
				yy.setPuk(cmpid);
				yy.setF01_qyqc(user.getEb1());
				yy.setF42(userid);
				yy.setPpp(cmpid);
				MYY0_JBXXService_.doInsert(yy);
			}
		} else {
			// 插入企业基本信息
			if (ONE.equals(user.getF02_yhlb())) {
				MGYS0_JBXXDBO gys = new MGYS0_JBXXDBO();
				gys.setPuk(cmpid);
				gys = (MGYS0_JBXXDBO) MGYS0_JBXXService_.doRead(gys);	
				if(gys==null){			
					//主数据获得
					MD2_GYSXXDBO mgys  = new MD2_GYSXXDBO();
					mgys.setPuk(cmpid);
					mgys = (MD2_GYSXXDBO) MD2_GYSXXService_.doRead(mgys);
					///创建私有
					gys = new MGYS0_JBXXDBO();
					BeanUtils.copyProperties(mgys, gys);
					gys.setF37(userid);
					MGYS0_JBXXService_.doInsert(gys);
				}else{
					return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_WWW, "重复注册，该企业已经注册，请直接登录", "/", false);
				}
			} else if (TWO.equals(user.getF02_yhlb())) {
				MYY0_JBXXDBO yy = new MYY0_JBXXDBO();
				yy.setPuk(cmpid);
				yy = (MYY0_JBXXDBO) MYY0_JBXXService_.doRead(yy);	
				if(yy==null){				
					//主数据获得
					MD3_YYXXDBO myy  = new MD3_YYXXDBO();
					myy.setPuk(cmpid);
					myy = (MD3_YYXXDBO) MD2_GYSXXService_.doRead(myy);
					//创建私有
					yy = new MYY0_JBXXDBO();
					BeanUtils.copyProperties(myy, yy);
					yy.setF42(userid);
					MYY0_JBXXService_.doInsert(yy);	
				}else{
					return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_WWW, "重复注册，该企业已经注册，请直接登录", "/", false);
				}
			}
		}

		user.setEb1(EMPTY);
		// 插入用户基本信息
		{
			user.setF04_yhdj(ZERO);
			user.setF05_vipdj(ONE);
			// 联系人登记密码
			String passWord = new Md5PasswordEncoder().encodePassword(user.getK04_dlmm(), null);
			user.setK04_dlmm(passWord);
			user.setPpp(cmpid);
			MSSUUService_.doInsert(user);
		}
		return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_WWW, "注册成功，请登录系统", "/", true);
	}

	// 用户基本信息	
	@Resource MSSUUService MSSUUService_;
	@Resource MD2_GYSXXService MD2_GYSXXService_;
	@Resource MD3_YYXXService MD3_YYXXService_;
	@Resource MGYS0_JBXXService MGYS0_JBXXService_;
	@Resource MYY0_JBXXService MYY0_JBXXService_;
}
