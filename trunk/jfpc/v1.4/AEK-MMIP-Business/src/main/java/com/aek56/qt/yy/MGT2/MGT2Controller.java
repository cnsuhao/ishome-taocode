package com.aek56.qt.yy.MGT2;

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

import com.aek56.atm.credentials.MGT2_JYXKZ.MGT2_JYXKZDBO;
import com.aek56.atm.credentials.MGT2_JYXKZ.MGT2_JYXKZService;
import com.aek56.qt.yy.MGT1.MGT1PVO;
/**
 *  供应商提供医院经营许可证
 * @author tangmuming
 * 2014-12-5
 * 3210200
 */
@Controller
public class MGT2Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MGT2Controller.class);
	
	//供应商提供医院营业执照
	@Resource
	protected MGT2_JYXKZService MGT2_JYXKZService_;
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
	@RequestMapping(value = "/32102001", method = RequestMethod.POST)
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
	@RequestMapping(value = "/32102002", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean listPost(String page,String pageSize,String orderBy,String keywords){
		RESTResultBean rb = new RESTResultBean();
		pageModel.setPageCurrent(Integer.parseInt(page));
		pageModel.setPageLimit(Integer.parseInt(pageSize));
		MGT2_JYXKZDBO param = new MGT2_JYXKZDBO();
		param.setFb1(keywords);
		param.setP01_yyid(super.getCompanyId());
		if(ONE.equals(orderBy))
			param.setF05_yxzzrq(">0");
		else if(TWO.equals(orderBy))
			param.setF05_yxzzrq("<0");
		pageModel.setFormParamBean(param);
		PageVOSupport pvo = MGT2_JYXKZService_.doSelectPage(pageModel, false);
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
	@RequestMapping(value = "/32102003", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean delete(String puk){
		RESTResultBean rb = new RESTResultBean();
		MGT2_JYXKZDBO param = new MGT2_JYXKZDBO();
		param.setPuk(puk);
		int flag = MGT2_JYXKZService_.toDelete(param);
		if(flag == 1){
			rb.setMessage(messageModel.getLocalMessage("321020031"));
		}else{
			rb.setCode(ONE);
			rb.setMessage(messageModel.getLocalMessage("321020032"));
		}
		return rb;
	}
	
}
