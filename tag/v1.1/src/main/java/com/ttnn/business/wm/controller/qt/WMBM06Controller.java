package com.ttnn.business.wm.controller.qt;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.biz.WMCheckOrderBussiness;
import com.ttnn.business.wm.biz.WMQTCZTXBussiness;
import com.ttnn.business.wm.service.PayService;
import com.ttnn.business.wm.service.PayServiceFactory;
import com.ttnn.business.wm.service.WMBM01Service;
import com.ttnn.business.wm.service.WMBM02Service;
import com.ttnn.business.wm.service.WMBM04Service;
import com.ttnn.business.wm.service.WMBMA1Service;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.common.util.PKUtil;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSModelAndViewSupport;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;

@Controller
@RequestMapping("WMBM06")
/** 充值*/
public class WMBM06Controller extends MyControllerSupportImpl {
	@Resource
	protected WMQTCZTXBussiness WMQTCZTXBussiness_;
	/**
	 * 佣金记录
	 */
	@Resource
	protected WMBM04Service WMBM04Service_;

	/**
	 * 个人支付通道
	 */
	@Resource
	protected WMBM01Service WMBM01Service_;
    
	/**
	 * 充值记录
	 */
	 @Resource
	 protected WMBM02Service WMBM02Service_;
	
	/**
	 * 系统通道
	 */
	@Resource
	protected WMBMA1Service WMBMA1Service_;

	/**
	 * 个人信息
	 */
	@Resource
	protected WMUI01Service WMUI01Service_;
	
	@Resource
	PayServiceFactory payServiceFactory;
    
	/**
	 * 订单核对
	 */
	@Resource
	WMCheckOrderBussiness  WMCheckOrderBussiness_;
	
	public WMQTCZTXBussiness getService() {
		return WMQTCZTXBussiness_;
	}
   
	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMBM06Controller.class);
	}

	@Override
	public CSModelAndViewSupport getModelAndView() {
		return new CSModelAndViewSupport("WM/QT/WMZHC1");
	}

	/**
	 * 初期页面显示数据一览
	 */
	@Override
	@RequestMapping(value = "/F.go")
	public ModelAndView home(CSPVOSupport paramBean) {
		CSModelAndViewSupport pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageMAV.setViewName("WM/QT/WMZHC1FL");
		pageVO.setPageData(paramBean);
		pageMAV.addObject(paramBean);
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
			      .getRequestAttributes()).getRequest();
		String message = request.getParameter("message");
		if(org.apache.commons.lang.StringUtils.isNotEmpty(message)&&message.equals("-1")){
			pageMAV.addObject("message","充值失败");
		}
		
		// 获得个人数据通道
		FrameworkDataBean fdb = new FrameworkDataBean();
		fdb.setK01(super.getLoginerId());// 用户ID
		fdb.setEb5(super.getProductId());// 产品ID
		fdb.setTablename("wmbm01");
		pageMAV.addObject("listGrtd", getDictionaryOnTable(fdb));

		fdb = new FrameworkDataBean();
		fdb.setK01(super.getLoginerId());// 用户ID
		fdb.setEb5(super.getProductId());// 产品ID
		fdb.setTablename("wmbs01");
		pageMAV.addObject("listYhk", getDictionaryOnTable(fdb));

		// 个人信息
		CSPVOSupport c2 = new CSPVOSupport();
		c2.setPuk(super.getLoginerId());
		FrameworkDataBean grxxFrameworkDataBean = WMUI01Service_.doRead(c2); 

		pageMAV.addObject("f01", grxxFrameworkDataBean.getF01());

		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	
	/**
	 * 跳转到支付前页面
	 */
	@RequestMapping(value = "/{orderno}/t.go")
	public ModelAndView tgo(@PathVariable String orderno){
		
		CSModelAndViewSupport pageMAV = getModelAndView();
		CSPVOSupport c1 = new CSPVOSupport();
          c1.setPuk(orderno);
		  FrameworkDataBean parambean =  WMBM02Service_.doRead(c1); //拿到订单
		// 支付通道PUK
				CSPVOSupport c2 = new CSPVOSupport();
				c2.setPuk(parambean.getK02());
				FrameworkDataBean grzftdFrameworkDataBean = WMBM01Service_.doRead(c2); //拿到个人通道
				c2 = new CSPVOSupport();
				c2.setPuk(grzftdFrameworkDataBean.getK03());
				// 系统通道
				FrameworkDataBean xttdFrameworkDataBean = WMBMA1Service_.doRead(c2);

				// 跳转到支付页面
				PayService payService = payServiceFactory
						.getPayService(xttdFrameworkDataBean.getFb2());

				pageMAV.setViewName(payService.getView());
				pageMAV.addObject("payflag", "0");
				pageMAV.addObject("postMap",
						payService.readToPay(parambean, xttdFrameworkDataBean));
		         
		return pageMAV;
	}
	
	
	
	/**
	 * 支付通道中的充值
	 */

	@RequestMapping(value = "/{userid}/F.go")
	public ModelAndView home1(CSPVOSupport paramBean,@PathVariable String userid) {
		CSModelAndViewSupport pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
        pageMAV.setViewName("WM/QT/WMZHC1FL");
		pageVO.setPageData(paramBean);
		pageMAV.addObject(paramBean);
		// 获得个人数据通道
		FrameworkDataBean fdb = new FrameworkDataBean();
		fdb.setK01(super.getLoginerId());// 用户ID
		fdb.setEb5(super.getProductId());// 产品ID
		fdb.setPuk(userid);
		fdb.setTablename("wmbm01");
		pageMAV.addObject("listGrtd", getDictionaryOnTable(fdb));
		
		fdb = new FrameworkDataBean();
		fdb.setK01(super.getLoginerId());// 用户ID
		fdb.setEb5(super.getProductId());// 产品ID
		fdb.setTablename("wmbs01");
		pageMAV.addObject("listYhk", getDictionaryOnTable(fdb));		
		
		// 个人信息
		CSPVOSupport c2 = new CSPVOSupport();
		c2.setPuk(super.getLoginerId());
		FrameworkDataBean grxxFrameworkDataBean = WMUI01Service_.doRead(c2);
		
		pageMAV.addObject("f01", grxxFrameworkDataBean.getF01());
		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 插入一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
	 */
	@Override
	@RequestMapping(value = "/C.go")
	public ModelAndView doInsert(CSPVOSupport paramBean) {
		CSModelAndViewSupport pageMAV = getModelAndView();
		getLogger().debug("paramBean" + paramBean);

		paramBean.setPuk(PKUtil.getPUKey());

		try {
			getService().doCZInsert(paramBean);
		} catch (Exception e) {
			e.printStackTrace();
			String url = "redirect:F.go?message=-1";
			pageMAV = new CSModelAndViewSupport(url);
			return pageMAV;
			
		}
        pageMAV.addObject("orderno",paramBean.getPuk());
        pageMAV.addObject("flag","1");
		pageMAV.setViewName("/WM/QT/ReadyOpen");
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}

    /**
     * 手动对账
     * @return
     */
	@RequestMapping(value = "/CheckOrder.go")
	public void  doCheckOneRecord(HttpServletRequest request,HttpServletResponse response){
		PrintWriter pw  = null;
		try{
			pw =  response.getWriter();
			String recordid = request.getParameter("recordid");
			CSPVOSupport  record = new CSPVOSupport();
			record.setPuk(recordid);
			FrameworkDataBean db = WMBM02Service_.doRead(record); //获取充值记录
			WMCheckOrderBussiness_.doCheckOrder(db);
			pw.write("success");
			pw.flush();
		}catch(Exception e){
			e.printStackTrace();
			if(pw!=null){
				pw.write("fail");
				pw.flush();
			}
		}
	}
	

	/**
	 * 更新一条记录--回调
	 * 
	 * @param paramBean
	 * @throws Exception 
	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
	 */
	@RequestMapping(value = "/{channelid}/HOME/U.go")
	public ModelAndView doUpdate(@PathVariable String channelid,
			HttpServletRequest request)  {
		CSModelAndViewSupport pageMAV = getModelAndView();
		try {
		CSPVOSupport csp = new CSPVOSupport();
		csp.setPuk(channelid);
		FrameworkDataBean xttdFrameworkDataBean = WMBMA1Service_.doRead(csp); //系统通道
		PayService payService = payServiceFactory.getPayService(xttdFrameworkDataBean.getFb2());
		CSPVOSupport paramBean = payService.callBack(request);
		if(paramBean.getF06().equals("2")){
			//回调成功
		    paramBean.setDdd("9");//和老付的约定
			getService().doCZUpdate(paramBean, pageMAV);
			pageMAV.addObject("message", "充值成功");
		}else{
			pageMAV.addObject("message", "充值失败");
		}
		} catch (Exception e) {
			e.printStackTrace();
			getLogger().error("回调失败!通道:"+channelid+":"+e.getMessage());
			pageMAV.addObject("message", e.getMessage());
			getLogger().error(e.getMessage());
		}
		pageMAV.setViewName("WM/QT/WMZHC2");
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}
}
