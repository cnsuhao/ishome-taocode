package com.ttnn.business.wm.controller.qt;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CheckUserMoneyService;
import com.ttnn.business.wm.biz.WMQTJLCXBussiness;
import com.ttnn.business.wm.service.WMBM02Service;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMBM02")
/** 充值交易记录*/
public class WMBM02Controller extends MyControllerSupportImpl {
	
    @Resource
    protected WMBM02Service WMBM02Service_;
    @Resource
    protected WMUI01Service WMUI01Service_;
    @Resource
    protected  WMQTJLCXBussiness WMJLCXBussiness_;
    @Resource
    protected CheckUserMoneyService CheckUserMoneyService_;
    @Override
    public MyServiceSupportImpl getService(){
        return WMBM02Service_;
    }
    public MyServiceSupportImpl getWMUI01Service(){
        return WMUI01Service_;
    }

    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMBM02Controller.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("WM/QT/WMJYC2");
    }
   
   
	@Override
	@RequestMapping(value = "/H.go", method = RequestMethod.POST)
	public ModelAndView home(CSPVOSupport paramBean){
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}

	

	  @Resource
	    protected WMQTJLCXBussiness WMQTJLCXBussiness_;
    /**
	 * 我的充值記錄
	 */
	
	@RequestMapping(value = "/F.go")
	public ModelAndView doSelectPage(CSPVOSupport ParamBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + ParamBean);
		ParamBean.setEb5(super.getProductId()); // eb5
		ParamBean.setF01(super.getLoginerId());
		pageVO.setPageCurrent(Integer.parseInt(ParamBean.getPageCurrent()));
		pageVO.setPageData(ParamBean);		
		pageMAV.addObject(pageVO);
		WMQTJLCXBussiness_.doSelectPageCZJYJL(pageVO);
		pageMAV.addObject("ParamBean", ParamBean);
		pageMAV.addObject("list", pageVO.getPageData());
		pageMAV.setViewName("WM/QT/WMJYC1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	
           /**
			 * 某用户的充值一览
			 */
			@RequestMapping(value = "/{userid}/X.go")
			public ModelAndView doSelectX(CSPVOSupport ParamBean,@PathVariable String userid,HttpServletRequest request) {
				ModelAndView pageMAV = getModelAndView();
				getLogger().debug("paramBean===>>>" + ParamBean);
				ParamBean.setK01(userid);
				pageVO.setPageCurrent(Integer.parseInt(ParamBean.getPageCurrent()));
				pageVO.setPageData(ParamBean);		
				pageMAV.addObject(pageVO);
				CSPVOSupport userInfo = new CSPVOSupport();
				userInfo.setPuk(userid);
				FrameworkDataBean db = WMUI01Service_.doRead(userInfo); //个人信息
				pageMAV.addObject("bbb",request.getParameter("pageid"));
		        pageMAV.addObject("userInfo", db);
				pageMAV.addObject("list", getService().doSelectPage(pageVO).getPageData());
				pageMAV.setViewName("WM/QT/WMJYC3");
				getLogger().debug("pageMAV===>>>" + pageMAV);
				return pageMAV;
			}
	
	
	  /**
		 * 我的下线充值记录
		 */
		
		@RequestMapping(value = "/T.go")
		public ModelAndView doSelectPage1(CSPVOSupport ParamBean) {
			ModelAndView pageMAV = getModelAndView();
			getLogger().debug("paramBean===>>>" + ParamBean);
			ParamBean.setK01(super.getLoginerId());
			ParamBean.setEb5(super.getProductId());
			pageVO.setOrderby(" wmbm02.cc1 ");
			pageVO.setPageCurrent(Integer.parseInt(ParamBean.getPageCurrent()));
			pageVO.setPageData(ParamBean);		
			pageMAV.addObject(pageVO);
			pageMAV.addObject("ParamBean",ParamBean);
			WMJLCXBussiness_.doSelectPagePayFor(pageVO);
			pageMAV.addObject("list",pageVO.getPageData());
			pageMAV.setViewName("WM/QT/WMXXCZJL");
			getLogger().debug("pageMAV===>>>" + pageMAV);
			return pageMAV;
		}
		
		/**
		 * 查询下线提现对应充值记录
		 * 
		 * @param paramBean
		 * @see ISSQLDaoSupport#doSelect(CSPVOSupport)
		 */
		@RequestMapping(value = "/{userid}/{tdid}/{txid}/Z.go",method = RequestMethod.POST)
		public ModelAndView showDetail(@PathVariable String userid,@PathVariable String tdid,CSPVOSupport paramBean,@PathVariable String txid ) {
			ModelAndView pageMAV = getModelAndView();
			paramBean.setK02(tdid);
			paramBean.setF06("2");
			paramBean.setEb1(txid);
			paramBean.setK01(userid);
			pageVO.setPageData(paramBean);
//			getService().doSelectPage(pageVO);
			WMJLCXBussiness_.doSelectPageTC(pageVO);
			pageMAV.addObject("list", pageVO.getPageData());
			pageMAV.setViewName("WM/QT/WMXXCZJL");
			getLogger().debug("pageMAV===>>>" + pageMAV);
			return pageMAV;
		}
		
		/**
		 * 查询提现对应充值记录
		 * 
		 * @param paramBean
		 * @see ISSQLDaoSupport#doSelect(CSPVOSupport)
		 */
		@RequestMapping(value = "/{userid}/{txid}/K.go",method = RequestMethod.POST)
		public ModelAndView showDetail1(@PathVariable String userid,@PathVariable String txid,CSPVOSupport paramBean ) {
			ModelAndView pageMAV = getModelAndView();
			paramBean.setK02(userid);
			paramBean.setEb1(txid);
			paramBean.setF06("2");
			pageVO.setPageData(paramBean);
			pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
			pageVO.setPageData(paramBean);		
			pageMAV.addObject(pageVO);
			getService().doSelectPage(pageVO);
			pageMAV.addObject("list", pageVO.getPageData());
			pageMAV.setViewName("WM/QT/WMJYC1");
			getLogger().debug("pageMAV===>>>" + pageMAV);
			return pageMAV;
		}
	
	/**
	 * 个人对账
	 * @return
	 */
	@RequestMapping(value = "/Check.go")
    public ModelAndView doCheck(CSPVOSupport ParamBean){
		ModelAndView pageMAV = getModelAndView();
		ParamBean.setK01(super.getLoginerId());
		pageVO.setPageData(ParamBean);
		CheckUserMoneyService_.findAllCheck(pageVO);
		pageMAV.addObject(pageVO);
		pageMAV.setViewName("WM/QT/WMDZD1"); 
    	return pageMAV;
    }
			
			
	/**
	 * 查询一条记录
	 * @param paramBean
	 * @see ISSQLDaoSupport#doSelect(CSPVOSupport)
	 */
	@Override
	@RequestMapping(value = "/R.go")
	public ModelAndView doRead(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);	
		pageMAV.addObject(paramBean);
		pageMAV.addObject("parambean1", getService().doRead(paramBean));
        pageMAV.setViewName("WM/QT/WMJYC2");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	}
