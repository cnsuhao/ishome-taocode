package com.ttnn.business.wm.controller.qt;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;
import com.ttnn.business.wm.biz.WMQTCZTXBussiness;
import com.ttnn.business.wm.service.WMBS01Service;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.common.util.PKUtil;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSModelAndViewSupport;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;

@Controller
@RequestMapping("WMBM07")
/** 提现*/
public class WMBM07Controller extends MyControllerSupportImpl {
	@Resource
	protected WMQTCZTXBussiness WMQTCZTXBussiness_;
    @Resource
    protected WMBS01Service WMBS01Service_;
	@Override
	public WMQTCZTXBussiness getService() {
		return WMQTCZTXBussiness_;
	}

	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMBM07Controller.class);
	}

	@Override
	public CSModelAndViewSupport getModelAndView() {
		return new CSModelAndViewSupport("WM/QT/WMZHT1");
	}
	/**
	 * 个人信息
	 */
	@Resource
	protected WMUI01Service WMUI01Service_;

	/**
	 * 初期页面显示数据一览
	 */
	@Override
	@RequestMapping(value = "/F.go")
	public ModelAndView home(CSPVOSupport paramBean) {
		CSModelAndViewSupport pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		pageVO.setPageData(paramBean);
		pageMAV.addObject(paramBean);
		// 获得个人数据通道
		FrameworkDataBean fdb = new FrameworkDataBean();
		if(!StringUtils.isNullOrEmpty(paramBean.getPuk())){
			pageMAV.addObject("puk", paramBean.getPuk());
			fdb.setPuk(paramBean.getPuk());
		}
		fdb.setK01(super.getLoginerId());// 用户ID
		fdb.setEb5(super.getProductId());// 产品ID
		fdb.setTablename("wmbm01");
		
		List<FrameworkDataBean> tds = getDictionaryOnTable(fdb);
		List<FrameworkDataBean> ods = new ArrayList<FrameworkDataBean>();
		for(FrameworkDataBean td : tds){
			getService().changeFY(td);
			ods.add(td);
		}
		
		pageMAV.addObject("listGrtd", ods);
		
		
		//获得银行卡信息
		fdb = new FrameworkDataBean();
		fdb.setK01(super.getLoginerId());// 用户ID
		fdb.setEb5(super.getProductId());// 产品ID
		fdb.setTablename("wmbs01");
		pageMAV.addObject("listYhk", getDictionaryOnTable(fdb));
		
		// 个人信息
		CSPVOSupport c2 = new CSPVOSupport();
		c2.setPuk(super.getLoginerId());
		FrameworkDataBean grxxFrameworkDataBean = WMUI01Service_.doRead(c2);
		if(grxxFrameworkDataBean!=null){
			pageMAV.addObject("f01", grxxFrameworkDataBean.getF01());
		}
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
			getService().doTXInsert(paramBean,pageMAV);
		} catch (Exception e) {
			pageMAV.addObject("message", e.getMessage());
			getLogger().error(e.getMessage());
		}
		
		pageMAV.addObject("puk", paramBean.getPuk());
		
		pageMAV.setViewName("WM/QT/WMZHT2");
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}
}
		
