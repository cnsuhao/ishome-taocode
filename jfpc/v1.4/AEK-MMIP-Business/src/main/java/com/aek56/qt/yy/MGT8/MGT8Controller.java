package com.aek56.qt.yy.MGT8;

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

import com.aek56.atm.credentials.MGT8_CJYYZZ.MGT8_CJYYZZDBO;
import com.aek56.atm.credentials.MGT8_CJYYZZ.MGT8_CJYYZZService;
import com.aek56.qt.yy.MGT1.MGT1PVO;
/**
 *  供应商提供医院厂家营业执照
 * @author tangmuming
 * 2014-12-5
 * 3210800
 */
@Controller
public class MGT8Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MGT8Controller.class);
	
	//供应商提供医院营业执照
	@Resource
	protected MGT8_CJYYZZService MGT8_CJYYZZService_;
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
	@RequestMapping(value = "/32108001", method = RequestMethod.POST)
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
	@RequestMapping(value = "/32108002", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean listPost(String page,String pageSize,String orderBy,String keywords){
		RESTResultBean rb = new RESTResultBean();
		pageModel.setPageCurrent(Integer.parseInt(page));
		pageModel.setPageLimit(Integer.parseInt(pageSize));
		MGT8_CJYYZZDBO param = new MGT8_CJYYZZDBO();
		param.setFb1(keywords);
		param.setP01_yyid(super.getCompanyId());
		if(ONE.equals(orderBy))
			param.setF05_yxzzrq(">0");
		else if(TWO.equals(orderBy))
			param.setF05_yxzzrq("<0");
		pageModel.setFormParamBean(param);
		PageVOSupport pvo = MGT8_CJYYZZService_.doSelectPage(pageModel, false);
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
	@RequestMapping(value = "/32108003", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean delete(String puk){
		RESTResultBean rb = new RESTResultBean();
		MGT8_CJYYZZDBO param = new MGT8_CJYYZZDBO();
		param.setPuk(puk);
		int flag = MGT8_CJYYZZService_.toDelete(param);
		if(flag == 1){
			rb.setMessage(messageModel.getLocalMessage("321080031"));
		}else{
			rb.setCode(ONE);
			rb.setMessage(messageModel.getLocalMessage("321080032"));
		}
		return rb;
	}
	
}
