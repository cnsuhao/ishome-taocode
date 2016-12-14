package com.ttnn.business.wm.controller.qt;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.service.PayService;
import com.ttnn.business.wm.service.PayServiceFactory;
import com.ttnn.business.wm.service.WMBM01Service;
import com.ttnn.business.wm.service.WMBM02Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;


@Controller
@RequestMapping("payForOnlineCall")
/** 在线支付回调*/
public class PayForCallBackController extends MyControllerSupportImpl  {
    
	 @Resource
	 protected WMBM02Service WMBM02Service_; //充值记录
     
	 @Resource PayServiceFactory payServiceFactory; //支付工厂
	 
	 @Resource 
	 protected WMBM01Service WMBM01Servic_;  //个人通道
	 
	@RequestMapping(value = "/BaoFu.go")
	public ModelAndView baofu(HttpServletRequest request,HttpServletResponse resp){
		String transID = request.getParameter("TransID"); //流水号
		CSPVOSupport bean = new CSPVOSupport();
		bean.setPuk(transID);// 流水号
		FrameworkDataBean db = WMBM02Service_.doRead(bean); //充值记录
		String puk_tid = db.getK02();
		CSPVOSupport bean2 = new CSPVOSupport();
		bean2.setPuk(puk_tid);
		FrameworkDataBean db2 = WMBM01Servic_.doRead(bean2); //系统通道信息
		
		
		PayService payService = payServiceFactory.getPayService("bftd");
		try {
			payService.callBack(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}
	
	@RequestMapping(value = "/RongBao.go")
	public ModelAndView rongbao(CSPVOSupport paramBean){
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}
	

}
