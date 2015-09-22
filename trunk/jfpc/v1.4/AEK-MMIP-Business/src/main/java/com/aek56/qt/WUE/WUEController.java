package com.aek56.qt.WUE;

import java.util.List;

import javax.annotation.Resource;

import org.jfpc.beans.common.MS2A1.MS2A1DBO;
import org.jfpc.beans.common.MS2A1.MS2A1Service;
import org.jfpc.beans.common.MSSUU.MSSUUDBO;
import org.jfpc.beans.common.MSSUU.MSSUUService;
import org.jfpc.common.login.LoginService;
import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 子账号管理
 * 
 * @author tangmuming
 * @version 1.0
 * @since  2014/12/3
 */
@Controller
public class WUEController extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(WUEController.class);
	
	
	@Resource
	protected LoginService LoginService_;
	/**
	 * 用户基本信息
	 */
	@Resource
	protected MSSUUService mssuuService;
	/**
	 * 成员管理业务
	 */
	@Resource
	protected WUEBusiness WUEBusiness_;
	
	/**
	 * 角色服务
	 */
	@Resource
	protected MS2A1Service ms2a1Service;
	/**
	 * 一览
	 * 
	 * @return
	 */
	@RequestMapping(value = "/31402040", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean listPOST() {
		if (logger.isDebugEnabled())
			logger.debug(super.getCompanyId());
		RESTResultBean rb = new RESTResultBean();
		List<WUEPVO> relist = WUEBusiness_.listPOST();
		rb.setResult(relist);
		if (logger.isDebugEnabled())
			logger.debug("relist:",relist);
		return rb;
	}
	
	/**
	 * 增加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/31402041", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean addPOST(WUEPVO formParamBean) {
		if (logger.isDebugEnabled())
			logger.debug(formParamBean.toString());
		RESTResultBean rb = new RESTResultBean();
		//用户名唯一性检查
		RESTResultBean rs = LoginService_.accountCheck(formParamBean.getK03_dlyhm(),false);
		if("0".equals(rs.getCode())){
			
			formParamBean.setK03_dlyhm(formParamBean.getK03_dlyhm()+"01");
			rb.setMessage(messageModel.getLocalMessage("314020411"));
			rb.setCode(ONE);
			rb.setResult(formParamBean);
			return rb;
		}
		try{
			WUEBusiness_.addUser(formParamBean);
			rb.setMessage(messageModel.getLocalMessage("314020412"));
		}
		catch(Exception e){
			e.printStackTrace();
			rb.setCode(ONE);
			rb.setMessage(messageModel.getLocalMessage("314020413"));
		}
		return rb;
	}
	
	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/31402042", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean deletePOST(WUEPVO formParamBean) {
		if (logger.isDebugEnabled())
			logger.debug(formParamBean.toString());
		RESTResultBean rb = new RESTResultBean();
		try{
			WUEBusiness_.delUser(formParamBean);
			rb.setMessage(messageModel.getLocalMessage("314020421"));
		}
		catch(Exception e){
			e.printStackTrace();
			rb.setCode(ONE);
			rb.setMessage(messageModel.getLocalMessage("314020422"));
		}
		return rb;
	}
	/**
	 * 明细
	 * @param formParamBean
	 * @return
	 */
	@RequestMapping(value = "/31402043", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean detailPOST(WUEPVO formParamBean) {
		if (logger.isDebugEnabled())
			logger.debug(formParamBean.toString());
		RESTResultBean rb = new RESTResultBean();
		try{
			WUEPVO result = WUEBusiness_.detailUser(formParamBean);
			rb.setMessage(messageModel.getLocalMessage("314020431"));
			rb.setResult(result);
		}
		catch(Exception e){
			e.printStackTrace();
			rb.setCode(ONE);
			rb.setMessage(messageModel.getLocalMessage("314020432"));
		}
		return rb;
	}
	
	
	/**
	 * 重置密码
	 * @param formParamBean
	 * @return
	 */
	@RequestMapping(value = "/31402044", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean baseUpdatePOST(WUEPVO formParamBean) {
		if (logger.isDebugEnabled())
			logger.debug(formParamBean.toString());
		RESTResultBean rb = new RESTResultBean();
		String defaultpass = new Md5PasswordEncoder().encodePassword("123456", null);
		formParamBean.setK04_dlmm(defaultpass);
		int flag = mssuuService.doUpdate(formParamBean);
		if(flag==1){
			rb.setMessage(messageModel.getLocalMessage("314020441"));
		}else{
			rb.setCode(ONE);
			rb.setMessage(messageModel.getLocalMessage("314020442"));
		}
		return rb;
	}
	
	
	/**
	 * 修改权限
	 * @param formParamBean
	 * @return
	 */
	@RequestMapping(value = "/31402045", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean updateRolePOST(WUEPVO formParamBean) {
		if (logger.isDebugEnabled())
			logger.debug(formParamBean.toString());
		RESTResultBean rb = new RESTResultBean();
		try {
			WUEBusiness_.updateRole(formParamBean);
			rb.setMessage(messageModel.getLocalMessage("314020451"));
			
		} catch (Exception e) {
			e.printStackTrace();
			rb.setCode(ONE);
			rb.setMessage(messageModel.getLocalMessage("314020452"));
		}
		return rb;
	}
	/**
	 * 跳转到添加
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/31402046", method = RequestMethod.GET)
	public MyModelAndViewSupport goPage(String page){
		MyModelAndViewSupport mv = new MyModelAndViewSupport("qt/WUE/"+page);
		List<FrameworkDataBean> lt = ms2a1Service.doSelectPage(new MS2A1DBO(), false);
		mv.addObject("roles",lt);
		return mv;
	}
	
	
	/**
	 * 所有权限
	 * @param formParamBean
	 * @return
	 */
	@RequestMapping(value = "/31402047", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean allRoles() {
		RESTResultBean rb = new RESTResultBean();
		List<FrameworkDataBean> lt = ms2a1Service.doSelectPage(new MS2A1DBO(), false);
		rb.setResult(lt);
		if (logger.isDebugEnabled())
			logger.debug(lt.size()+"");
		return rb;
	}

}
