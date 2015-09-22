package com.aek56.qt.yy.CGTZ2;

import javax.annotation.Resource;

import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aek56.atm.company.cmp.CompanyService;
import com.aek56.atm.credentials.CGTZ_TSJL.CGTZ_TSJLDBO;
import com.aek56.atm.credentials.CGTZ_TSJL.CGTZ_TSJLService;
/**
 * 变更审核
 * @author Administrator
 *
 */
@Controller
public class CGTZ2Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(CGTZ2Controller.class);
	
	@Resource
	private CompanyService companyService;
	
	@Resource
	private CGTZ_TSJLService cgtz_TSJLService;
	@Resource
	private CGTZ2Business cgtzBusiness;

	/**
	 *变更证件详情页
	 * @param puk
	 * @return
	 */						  
	@RequestMapping(value = "/32113210100", method = RequestMethod.GET)
	public MyModelAndViewSupport m321132104post(String aid,String type) {
		MyModelAndViewSupport mv = new MyModelAndViewSupport("qt/yy/CGTZ/CGTZ02");
		RESTResultBean rb =companyService.auditingCredentialsForView(aid,type);
		mv.addObject(USER_DATA, rb.getResult());
		mv.addObject("aid",aid);
		mv.addObject("type",type);
		return mv;
	}
	
	
	/**
	 * 换证审核
	 * @param aid
	 * @return
	 */
	@RequestMapping(value = "/32113210101", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m32113210101post(CGTZ_TSJLDBO param) {
		RESTResultBean rb = new RESTResultBean();
		try {
			//推送
			param.setGgg(ONE);
			cgtzBusiness.tsshenhe(param);
			rb.setMessage("321132101011");
		} catch (Exception e) {
			e.printStackTrace();
			rb.setCode(ONE);
			rb.setMessage("321132101012");
		}
		return rb;
	}
	
	/**
	 * 多条审核
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/32113210102", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m32113210102post(CGTZ_TSJLDBO param) {
		RESTResultBean rb = new RESTResultBean();
		param.setGgg(ONE);
		String[] puks = param.getPuk().split(COMMA);
		try {
			for (String puk : puks) {
				param.setPuk(puk);
				cgtzBusiness.tsshenhe(param);
			}
			rb.setMessage("321132101011");
		} catch (Exception e) {
			e.printStackTrace();
			rb.setCode(ONE);
			rb.setMessage("321132101012");
		}
		return rb;
	}
	
	
	/**
	 * 查看我的证件图片，   已经通过医院审核的图片
	 * @param falg 请求类型：0：查看我的证件图片，1：查看供应商图片
	 * @param cid  供应商ID
	 * @param zid  证件ID    营业执照、经营许可证、税务登记证、 注册证 传 推送表里的PUK也就是zid
	 * @param type 类型
	 * @return
	 */
	@RequestMapping(value = "/32113210103", method = RequestMethod.GET)
	public MyModelAndViewSupport showImage1(String flag, String cid,String zid,String type, String tsbId, String zczId){
		MyModelAndViewSupport mv = new MyModelAndViewSupport("qt/pic");
		if (ZERO.equals(flag)) {
		    int ctype = Integer.parseInt(type);
		    cgtzBusiness.showImage2(cid, ctype, tsbId, zczId, mv);
		} else {
		    int ctype = Integer.parseInt(type);
	        cgtzBusiness.showImage1(zid, ctype, mv);
		}
		
		return mv;
	}
}
