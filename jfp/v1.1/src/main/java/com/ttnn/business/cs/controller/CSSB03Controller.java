package com.ttnn.business.cs.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.dbo.CSSB03DBO;
import com.ttnn.business.cs.service.CSSB03Service;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSModelAndViewSupport;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("CSSB03")
/** 业务说明*/
public class CSSB03Controller extends MyControllerSupportImpl {
	@Resource
	protected CSSB03Service CSSB03Service_;
	
	@Resource
	protected CSSB03DBO cssb03dbo;
	
	
	@Override
	public MyServiceSupportImpl getService() {
		return CSSB03Service_;
	}
	
	@Override	
    public Logger getLogger(){
        return LoggerFactory.getLogger(CSSB03Controller.class);
    }
		
	/**
	 * 页面视图
	 */
    @Override
	public ModelAndView getModelAndView() {
		return new CSModelAndViewSupport("CS/sb03c1");
	}
    
	@Override
	@RequestMapping(value = "/H.go", method = RequestMethod.GET)
	public ModelAndView home(CSPVOSupport paramBean){
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean" + paramBean);
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}

	/**
	 * 插入一条记录
	 * @param paramBean
	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
	 */
	@Override
	@RequestMapping(value = "/C.go", method = RequestMethod.POST)
	public ModelAndView doInsert(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean" + paramBean);	
		int result=getService().doInsert(paramBean);	
		if(result==1){
			pageMAV.addObject("obj", paramBean);
			
		}else if(result==-1){
			pageMAV.addObject("obj", "failure");
		
		}else{
			pageMAV.addObject("obj", "failure");
			
		}
		pageMAV.setViewName("redirect:F.go");
		getLogger().debug("pageMAV" + pageMAV);		
		return pageMAV;
	}
	
	/**
	 * 数据一览
	 */
	@RequestMapping(value = "/F.go")
	public ModelAndView doFindList(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
	    pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		pageVO.setPageData(paramBean);		
		pageMAV.addObject(pageVO);
		pageMAV.addObject("list", getService().doSelectPage(pageVO));
		pageMAV.setViewName("cs/sb03r1");
		getLogger().debug("pageMAV===>>>" + pageMAV);	
		return pageMAV;
	}
	
	/**
	 * 查询一条记录
	 * @param paramBean
	 * @see ISSQLDaoSupport#doSelect(CSPVOSupport)
	 */
	@Override
	@RequestMapping(value = "/R.go", method = RequestMethod.POST)
	public ModelAndView doRead(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);			
		pageMAV.addObject("paramBean1", getService().doRead(paramBean));
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 删除一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doBatchDelete(java.util.List)
	 */
	@Override
	@RequestMapping(value = "/D.go", method = RequestMethod.POST)
	public ModelAndView doDelete(CSPVOSupport paramBean) {
		ModelAndView pageMAV =  getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);	
		getService().doRead(paramBean);
		int result=getService().doDelete(paramBean);
		if (result==0) {
			pageMAV.addObject("obj", "delete");
		} else if(result==-1){
			pageMAV.addObject("obj", "delete failure");
		}else{//返回1
			pageMAV.addObject("obj", "delete success");
		}
		
		pageMAV.setViewName("redirect:F.go");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 更新一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
	@Override
	@RequestMapping(value = "/U.go", method = RequestMethod.POST)
	public ModelAndView doUpdate(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		//查询得到该记录，加载至“cs/sb03c1”页面	
//		FrameworkDataBean paramBean1=getService().read(paramBean);
//		pageMAV.addObject("paramBean1", paramBean);
		int result=getService().doUpdate(paramBean);	
		System.out.println(result);
		
		pageMAV.setViewName("redirect:F.go");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 分页显示
	 * 
	 * @param paramPageModel
	 * @return
	 * @see ISSQLDaoSupport#doSelectList(PageVO)
	 */
	@RequestMapping(value = "/L.go", method = RequestMethod.GET)
	public ModelAndView page(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		getService().doSelectPage(paramPageModel);
		pageMAV.addObject(paramPageModel);	
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
/*	@Override
	@RequestMapping(value = "/C", method = RequestMethod.GET)
	public ModelAndView home(CSPVOSupport paramBean){
		ModelAndView pageMAV = getModelAndView();
		return pageMAV;
	}*/
	
	/**
	 * 默认显示
	 * @param pageMAV
	 */
/*	public void doHomeProcess(CSPVOSupport paramBean, ModelAndView pageMAV) {
		pageMAV.setViewName("cs/sb03r1");
	}
	
	/**
	 * 插入一条记录
	 */

/*	public void doCreateProcess(CSPVOSupport paramBean, ModelAndView pageMAV) {
		getService().insert(paramBean);
		pageMAV.addObject("sb03", paramBean);
		pageMAV.setViewName("cs/sb03r1");
	//	pageMAV = new ModelAndView(new RedirectView("cs/sb03r1");
	}*/
	
	/**
	 * 数据一览
	 */
/*	@Override
	public void doAllProcess(CSPVOSupport paramBean, ModelAndView pageMAV) {
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
//		getLogger().debug(""+getService().find(paramPageModel));
//		pageMAV.addObject(attributeValue)
		//Map<String,List<FrameworkDataBean>> map=new HashMap<String,List<FrameworkDataBean>>();
		pageMAV.addObject("list", getService().find(paramPageModel));
		pageMAV.addObject(paramPageModel);
		pageMAV.setViewName("cs/sb03r1");
	}*/

	/**
	 * 修改一条记录
	 */
	/*public void doReplaceProcess(CSPVOSupport paramBean, ModelAndView pageMAV) {
		doViewProcess(paramBean,pageMAV);	
		getService().update(paramBean);
		pageMAV.setViewName("cs/sb03r1");
	}*/
	/**
	 * 查询一条记录
	 */
	/*public void doViewProcess(CSPVOSupport paramBean, ModelAndView pageMAV) {
		getService().read(paramBean);
		pageMAV.addObject("paramBean", paramBean);
	}*/

/*	@RequestMapping(value = "/H", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {	
		return "cs/sb03c1";
	}
		
	@RequestMapping(value = "/R", method = RequestMethod.GET)
	public String home1(Locale locale, Model model) {	
		return "cs/sb03r1";
	}
	*//**
	 * 创建
	 * *//*
	@RequestMapping(value = "/C",method = RequestMethod.POST)
	public String create(CSPVOSupport paramBean,Model model) {
		getLogger().debug(""+paramBean);
		getService().insert(paramBean);
		getService().insert(paramBean);
		return "cs/sb03r1";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/F",method = RequestMethod.POST)
	public String all(CSPVOSupport paramBean,Model model){
//		getLogger().debug("/");
		getService().read(paramBean);
		Map map=new HashMap();
		map.put("111", "222");
//		map.put("all", getService().read(paramBean));
		model.addAttribute("map", map);
		return "cs/sb03r1";
	}

*//**	@RequestMapping(value = "/D",method = RequestMethod.POST)
	public String remove(CSPVOSupport paramBean){	
		getService().delete(paramBean);
		return null;
	}*//*
  
	@RequestMapping(value = "/U",method = RequestMethod.POST)
	public String update(CSPVOSupport paramBean){
		getService().update(paramBean);
		return "cs/sb03c1";
	}*/
	
}
