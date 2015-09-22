package com.aek56.qt.yy.WGD1;

import javax.annotation.Resource;

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

import com.aek56.atm.credentials.MGA1_YYZZ.MGA1_YYZZDBO;
import com.aek56.atm.credentials.MGT1_YYZZ.MGT1_YYZZDBO;
import com.aek56.qt.credentials.CredentialsBusiness;
/**
 * 已过期证件
 * @author tangmuming
 * 3220030
 */
@Controller
public class WGD1Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(WGD1Controller.class);
	/**
	 *已过期证件服务
	 */
	@Resource
	protected CredentialsBusiness credentialsBusiness;
	
	@RequestMapping(value = "/32200300", method = RequestMethod.POST)
	public MyModelAndViewSupport gopage(String index){
		MyModelAndViewSupport mv = new MyModelAndViewSupport("qt/yy/WGD1/WGD101");
		mv.addObject("index", index);
		return mv;
	}
	
	
	/**
	 * 过期证件一览
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/32200301", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m32200301post(String page,String pageSize){
		if(logger.isDebugEnabled()){
			logger.debug("page:{},pageSize:{},loginId{}",page,pageSize,super.getLoginerId());
		}
		pageModel.config();
		RESTResultBean rb = new RESTResultBean();
		MGT1_YYZZDBO dbo = new MGT1_YYZZDBO();
		dbo.setP01_yyid("'"+super.getCompanyId()+"'");
		
		dbo.setF06_shzt(ZERO);
		dbo.setDdd(ZERO);
		pageModel.setPageCurrent(Integer.parseInt(page));
		pageModel.setPageLimit(Integer.parseInt(pageSize));
		pageModel.setFormParamBean(dbo);
		PageVOSupport pvo = credentialsBusiness.doSelectPageHospitalCredentialsList(pageModel);
		WGD1PVO mg = new WGD1PVO();
		if(pvo!=null){
			mg.setList(pvo.getPageListData());
			mg.setCount(pvo.getResultCount());
		}
		rb.setResult(mg);
		return rb;
	}
	
	
	/**
	 * 忽略
	 * @param zid
	 * @return
	 */
	@RequestMapping(value = "/32200302", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m32200302post(String puk,char type){
		RESTResultBean rb = new RESTResultBean();
		
		int flag =credentialsBusiness.doUpdateCredentials(puk,type);
		rb.setMessage("322003021");
		if(flag!=1){
			rb.setCode(ONE);
			rb.setMessage("322003022");
		}
		return rb;
		
	}
	
	
	/**
	 * 提醒
	 * @param gysid  供应商ID  '200','300'
	 * @param puk    证件ID    '7874','564657'
	 * @return
	 */
	@RequestMapping(value = "/32200303", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m32200303post(String gysid,String puk){
		RESTResultBean rb = new RESTResultBean();
		MGA1_YYZZDBO dbo = new MGA1_YYZZDBO();
		dbo.setK01_gysid(gysid);
		dbo.setPuk(puk);
		pageModel.setFormParamBean(dbo);
		try {
			credentialsBusiness.sendSmsByHispotalOnDetail(pageModel);
			rb.setMessage("322003031");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rb.setCode(ONE);
			rb.setMessage("322003032");
		}
		return rb;
		
	}
	
	
	
}
