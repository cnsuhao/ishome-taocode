package com.aek56.qt.yy.CGTZ1;

import java.util.HashMap;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.page.PageVOSupport;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aek56.atm.company.cmp.CompanyService;
import com.aek56.atm.credentials.CGTZ_TSJL.CGTZ_TSJLDBO;
import com.aek56.atm.credentials.CGTZ_TSJL.CGTZ_TSJLService;
import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZDBO;
import com.aek56.qt.yy.WGD1.WGD1PVO;
/**
 * 审核推送证件
 * @author Administrator
 *
 */
@Controller
public class CGTZ1Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(CGTZ1Controller.class);
	
	@Resource
	private CompanyService companyService;
	
	@Resource
	private CGTZ_TSJLService cgtz_TSJLService;
	@Resource
	private CGTZ1Business cgtzBusiness;
	/**
	 * 推送证件列表、换证列表
	 * @param puk
	 * @return
	 */
	@RequestMapping(value = "/321132101", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m321132101post(String page,String pageSize,String type) {
		RESTResultBean rb = new RESTResultBean();
		CGTZ_TSJLDBO param = new CGTZ_TSJLDBO();
		param.setK02_yyid(super.getCompanyId());
		param.setF13_shzt(TWO);
		param.setGgg(type);
		pageModel.setPageCurrent(Integer.parseInt(page));
		pageModel.setPageLimit(Integer.parseInt(pageSize));
		pageModel.setFormParamBean(param);
		PageVOSupport pvo =  cgtz_TSJLService.doSelectPage(pageModel, false);
		WGD1PVO mg = new WGD1PVO();
		if(pvo!=null){
			mg.setList(pvo.getPageListData());
			mg.setCount(pvo.getResultCount());
		}
		rb.setResult(mg);
		return rb;
	}	

	/**
	 * 推送获取获取审核详情
	 * @param puk
	 * @return
	 */
	@RequestMapping(value = "/321132102", method = RequestMethod.GET)
	public MyModelAndViewSupport m321132102post(String aid,String type) {
		MyModelAndViewSupport mv = new MyModelAndViewSupport("qt/yy/CGTZ/CGTZ01");
		RESTResultBean rb =companyService.auditingCredentialsForView(aid,type);
		mv.addObject(USER_DATA, rb.getResult());
		mv.addObject("f10_cjylqxzcz", JSONArray.fromObject(((HashMap<String, Object>)rb.getResult()).get("f10_cjylqxzcz")).toString());
		mv.addObject("aid",aid);
		mv.addObject("type",type);
		return mv;
	}

	/**
	 * 推送审核
	 * @param aid
	 * @return
	 */
	@RequestMapping(value = "/321132103", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m321132103post(CGTZ_TSJLDBO param) {
		RESTResultBean rb = new RESTResultBean();
		try {
			//推送
			param.setGgg(TWO);
			cgtzBusiness.tsshenhe(param);
			rb.setMessage("3211321031");
		} catch (Exception e) {
			e.printStackTrace();
			rb.setCode(ONE);
			rb.setMessage("3211321032");
		}
		return rb;
	}
	
	/**
	 * 推送时，预览注册证详情注册证详情
	 * @return
	 */
	@RequestMapping(value = "/321132105", method = RequestMethod.GET)
	public MyModelAndViewSupport m321132105post(MGAAC_YLQXZCZDBO param){
		MyModelAndViewSupport mv = new MyModelAndViewSupport("qt/yy/CGTZ/CGTZ03");
		mv.addObject(USER_DATA, cgtzBusiness.m321132105post(param));
		return mv;
	}
	
	/**
	 * 推送时，预览注册证详情注册证详情
	 * @return
	 */
	@RequestMapping(value = "/321132106", method = RequestMethod.GET)
	public MyModelAndViewSupport m321132106post(MGAAC_YLQXZCZDBO param){
		MyModelAndViewSupport mv = new MyModelAndViewSupport("qt/yy/CGTZ/CGTZ04");
		
		return mv;
	}
	
	

	
	
	
	
}
