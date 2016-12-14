package com.aek56.qt.yy.MGT3;

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

import com.aek56.atm.credentials.MGT3_GSSWDJZ.MGT3_GSSWDJZDBO;
import com.aek56.atm.credentials.MGT3_GSSWDJZ.MGT3_GSSWDJZService;
import com.aek56.qt.yy.MGT1.MGT1PVO;
/**
 *  供应商提供医院工商税务登记证
 * @author tangmuming
 * 2014-12-5
 * 3210300
 */
@Controller
public class MGT3Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MGT3Controller.class);
	
	//供应商提供医院营业执照
	@Resource
	protected MGT3_GSSWDJZService MGT3_GSSWDJZService_;
	/**
	 * 默认加载页
	 */
	public MyModelAndViewSupport getModelAndView(String strPath) {
		return new MyModelAndViewSupport(strPath);
	}
	/**
	 * 跳转到我的证件页面
	 * @return
	 */
	@RequestMapping(value = "/32103001", method = RequestMethod.POST)
	public MyModelAndViewSupport gopage(){
		MyModelAndViewSupport mv = getModelAndView("qt/yy/MGT1/MGT101");
		return mv;
	}
	/**
	 * 一览
	 * @return
	 * @param page当前页
	 * @param pageSize条数
	 * @param orderBy过期，未过期
	 * @param keywords供应商名称，关键字
	 */
	@RequestMapping(value = "/32103002", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean listPost(String page,String pageSize,String orderBy,String keywords){
		RESTResultBean rb = new RESTResultBean();
		pageModel.setPageCurrent(Integer.parseInt(page));
		pageModel.setPageLimit(Integer.parseInt(pageSize));
		MGT3_GSSWDJZDBO param = new MGT3_GSSWDJZDBO();
		param.setFb1(keywords);
		param.setP01_yyid(super.getCompanyId());
		if(ONE.equals(orderBy))
			param.setF05_yxzzrq(">0");
		else if(TWO.equals(orderBy))
			param.setF05_yxzzrq("<0");
		pageModel.setFormParamBean(param);
		PageVOSupport pvo = MGT3_GSSWDJZService_.doSelectPage(pageModel, false);
		MGT1PVO mg = new MGT1PVO();
		if(pvo!=null){
			mg.setList(pvo.getPageListData());
			mg.setCount(pvo.getResultCount());
		}
		rb.setResult(mg);
		return rb;
	}
	
	/**
	 * 删除证件
	 * @param puk
	 * @return
	 */
	@RequestMapping(value = "/32103003", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean delete(String puk){
		RESTResultBean rb = new RESTResultBean();
		MGT3_GSSWDJZDBO param = new MGT3_GSSWDJZDBO();
		param.setPuk(puk);
		int flag = MGT3_GSSWDJZService_.toDelete(param);
		if(flag == 1){
			rb.setMessage(messageModel.getLocalMessage("321030031"));
		}else{
			rb.setCode(ONE);
			rb.setMessage(messageModel.getLocalMessage("321030032"));
		}
		return rb;
	}
	
}
