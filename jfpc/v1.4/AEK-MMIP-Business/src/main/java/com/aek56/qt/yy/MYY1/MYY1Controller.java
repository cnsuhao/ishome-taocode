package com.aek56.qt.yy.MYY1;

import javax.annotation.Resource;

import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aek56.atm.company.MYY1_YYTJGYSXX.MYY1_YYTJGYSXXDBO;
import com.aek56.atm.company.MYY1_YYTJGYSXX.MYY1_YYTJGYSXXService;
import com.aek56.qt.credentials.CredentialsBusiness;
/**
 * 医院查看供应商工作台
 * @ClassName: MYY1Controller 
 * @author: Gaoqin
 * @date: 2014-12-3 下午6:56:40
 */
@Controller
public class MYY1Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MYY1Controller.class);
	
	/**
	 * 医院供应商信息
	 */
	@Resource
	private MYY1_YYTJGYSXXService yytjgysxxService;

	@Resource
	private  MYY1Business  myy1Business;
	/**
	 * 提醒服务
	 */
	@Resource
	private CredentialsBusiness credentialsBusiness;
	/**
	 * 默认加载页
	 */
	public MyModelAndViewSupport getModelAndView(String strPath) {
		return new MyModelAndViewSupport(strPath);
	}
	
	
	/**
	 * 跳转到医院查看供应商页面  
	 * @param @param pvo
	 * @param @param param
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/32011001", method = RequestMethod.POST)
	public MyModelAndViewSupport m32011001post(String f01_qyqc) {
		
		MyModelAndViewSupport mv = getModelAndView("qt/yy/MYY1/MYY101");
		mv.addObject("name", f01_qyqc);
		
		return mv;
	}
	
	/**
	 * 医院查看供应商一览数据提供
	 * @param @param pvo
	 * @param @param param
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/32011001/1", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m320110011post(String page,String pageSize,String orderBy,String keywords) {
		RESTResultBean rs = new RESTResultBean();
		if (logger.isDebugEnabled()) {
			logger.debug("page:{},pageSize:{},orderBy:{},keywords{}", page, pageSize, orderBy,keywords);
		}
		
		pageModel.setPageCurrent(Integer.parseInt(page));
		pageModel.setPageLimit(Integer.parseInt(pageSize));
		MYY1_YYTJGYSXXDBO param = new MYY1_YYTJGYSXXDBO();
		param.setF01_qyqc(keywords);
		param.setP01_yyid(super.getCompanyId());
		param.setF40(ZERO);
		pageModel.setFormParamBean(param);
		rs.setResult(yytjgysxxService.doSelectPage(pageModel, false).getPageListData());
		//mv.addObject(SEARCH, param);
		
		if (logger.isDebugEnabled()) {
			logger.debug("result:{}", pageModel);
		}
		
		return rs;
	}

	/**
	 * 跳转到新增供应商页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/32011002", method = RequestMethod.GET)
	public MyModelAndViewSupport m32011002post() {
		MyModelAndViewSupport mv = getModelAndView("qt/yy/MYY1/MYY102");
		/*if (EmptyHelper.isEmpty(param.getPuk())) {
			mv.addObject(USER_DATA, new MYY1_YYTJGYSXXDBO());
		} else {
			FrameworkDataBean result = yytjgysxxService.doRead(param);
			mv.addObject(USER_DATA, result);
		}*/
		return mv;
	}

	/**
	 * 保存新增供应商
	 * @param @param param
	 * @param @return
	 * @return MyModelAndViewSupport
	 * @throws
	 */
	@RequestMapping(value = "/32011003", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m32011003post(MYY1_YYTJGYSXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}
		RESTResultBean rb = new RESTResultBean();
		try {
			myy1Business.addInfo(param);
			rb.setMessage("320110031");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rb.setCode(ONE);
			rb.setMessage("320110032");
		}
		return rb;
	}

	/**
	 * 医院解除供应商关系
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/32011004", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m32011004post(MYY1_YYTJGYSXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}
		RESTResultBean rb = new RESTResultBean();
		try {
			int flag = yytjgysxxService.toDelete(param);
			rb.setMessage("320110041");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rb.setCode(ONE);
			rb.setMessage("320110042");
		}
		return rb;
	}
	
	
	/**
	 * 提醒
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/32011005", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m32011005post(MYY1_YYTJGYSXXDBO param) {
		if (logger.isDebugEnabled()) {
			logger.debug("param:{}", param);
		}
		RESTResultBean rb = new RESTResultBean();
		try {
			credentialsBusiness.sendSmsByHispotalOnGYSCompany(param.getPuk());
			rb.setMessage("320110051");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rb.setCode(ONE);
			rb.setMessage("320110052");
		}
		return rb;
	}
	
	@RequestMapping(value = "/32011006", method = RequestMethod.POST)
	public MyModelAndViewSupport m32011006post(String index) {
		MyModelAndViewSupport mv = getModelAndView("qt/yy/MYY1/MYY104");
		
		return mv;
	}
	
}
