package com.aek56.qt.yy.MGT1;

import javax.annotation.Resource;

import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.page.PageVOSupport;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aek56.atm.credentials.MGT1_YYZZ.MGT1_YYZZDBO;
import com.aek56.atm.credentials.MGT1_YYZZ.MGT1_YYZZService;
/**
 *  供应商提供医院营业执照   修改by高勤
 * @author tangmuming 
 * 2014-12-5
 * 3210100
 */
@Controller
public class MGT1Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MGT1Controller.class);
	
	//供应商提供医院营业执照
	@Resource
	protected MGT1_YYZZService mgt1_YYZZService;
	
	/**
	 * 默认加载页
	 */
	public MyModelAndViewSupport getModelAndView(String strPath) {
		return new MyModelAndViewSupport(strPath);
	}
	/**
	 * 跳转到我的证件页面 ,修改by高勤,因业务ID和数据表不匹配
	 * @return
	 */
	@RequestMapping(value = "/3210100", method = RequestMethod.POST)
	public MyModelAndViewSupport gopage(String f01_zczzwmc,String index){
		MyModelAndViewSupport mv = getModelAndView("qt/yy/MGT1/MGT101");
		mv.addObject("name", f01_zczzwmc);
		if(EmptyHelper.isEmpty(index))
			index = ZERO;
		mv.addObject("index", index);
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
	@RequestMapping(value = "/32101002", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean listPost(String page,String pageSize,String orderBy,String keywords){
		RESTResultBean rb = new RESTResultBean();
		pageModel.setPageCurrent(Integer.parseInt(page));
		pageModel.setPageLimit(Integer.parseInt(pageSize));
		MGT1_YYZZDBO param = new MGT1_YYZZDBO();
		param.setFb1(keywords);
		param.setP01_yyid(super.getCompanyId());
		if(ONE.equals(orderBy))
			param.setF05_yxzzrq(">0");
		else if(TWO.equals(orderBy))
			param.setF05_yxzzrq("<0");
		pageModel.setFormParamBean(param);
		PageVOSupport pvo = mgt1_YYZZService.doSelectPage(pageModel, false);
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
	@RequestMapping(value = "/32101003", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean delete(String puk){
		RESTResultBean rb = new RESTResultBean();
		MGT1_YYZZDBO param = new MGT1_YYZZDBO();
		param.setPuk(puk);
		int flag = mgt1_YYZZService.toDelete(param);
		if(flag == 1){
			rb.setMessage(messageModel.getLocalMessage("321010031"));
		}else{
			rb.setCode(ONE);
			rb.setMessage(messageModel.getLocalMessage("321010032"));
		}
		return rb;
	}	
}
