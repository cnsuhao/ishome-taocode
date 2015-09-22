package com.aek56.qt.yy.MYY2;

import javax.annotation.Resource;

import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.page.PageVOSupport;
import org.jfpc.framework.support.MyControllerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aek56.atm.company.MYY1_YYTJGYSXX.MYY1_YYTJGYSXXDBO;
import com.aek56.atm.company.MYY1_YYTJGYSXX.MYY1_YYTJGYSXXService;
import com.aek56.qt.yy.WGD1.WGD1PVO;
/**
 * 待审核供应商
 * @author tangmuming
 * 2014-12-9
 * 
 */
@Controller
public class MYY2Controller extends MyControllerSupport{
	private static final Logger logger = LoggerFactory.getLogger(MYY2Controller.class);

	/**
	 * 医院供应商信息
	 */
	@Resource
	private MYY1_YYTJGYSXXService yytjgysxxService;
	
	
	/**
	 * 待审核供应商一览
	 * @param page
	 * @param pageSize
	 * @param orderBy
	 * @param keywords
	 * @return
	 */
	@RequestMapping(value = "/320110001", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m320110001post(String page,String pageSize) {
		RESTResultBean rs = new RESTResultBean();
		if (logger.isDebugEnabled()) {
			logger.debug("page:{},pageSize:{}", page, pageSize);
		}
		
		pageModel.setPageCurrent(Integer.parseInt(page));
		pageModel.setPageLimit(Integer.parseInt(pageSize));
		MYY1_YYTJGYSXXDBO param = new MYY1_YYTJGYSXXDBO();
		param.setP01_yyid(super.getCompanyId());
		param.setF40(TWO);
		pageModel.setFormParamBean(param);
		
		PageVOSupport pvo = yytjgysxxService.doSelectPage(pageModel, false);
		WGD1PVO mg = new WGD1PVO();
		if(pvo!=null){
			mg.setList(pvo.getPageListData());
			mg.setCount(pvo.getResultCount());
		}
		rs.setResult(mg);
		return rs;
	}
	
	/**
	 * 审核
	 * @param puk
	 * @return
	 */
	@RequestMapping(value = "/320110002", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m320110002post(String puk, String type) {
		RESTResultBean rb = new RESTResultBean();
		MYY1_YYTJGYSXXDBO param = new MYY1_YYTJGYSXXDBO();
		param.setPuk(puk);
		param.setF40(type);
		int flag = yytjgysxxService.doUpdate(param);
		rb.setMessage("3201100021");
		if(flag!=1){
			rb.setCode(ONE);
			rb.setMessage("3201100022");
		}
		return rb;
	}
	
}	
