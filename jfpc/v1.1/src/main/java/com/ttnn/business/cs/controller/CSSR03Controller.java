package com.ttnn.business.cs.controller;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.biz.CSUserGroupRightBusiness;
import com.ttnn.business.cs.service.CSSB03Service;
import com.ttnn.business.cs.service.CSSR03Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.util.MyMap;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("CSSR03")
/**
 * 用户组
 * @author duan.p
 *
 */
public class CSSR03Controller extends MyControllerSupportImpl {
    @Resource
    protected CSSR03Service CSSR03Service_;  //用户组信息
    
    @Resource 
    protected  CSSB03Service  cssb03Service; //画面信息
    
    @Resource
    protected CSUserGroupRightBusiness  CSUserGroupRightBusiness_;   //用户组画面
    
	@Override
	public MyServiceSupportImpl getService() {
		return CSSR03Service_;
	}
	
	@Override	
    public Logger getLogger(){
        return LoggerFactory.getLogger(CSSR03Controller.class);
    }
    
   
    /**
  	 * 页面视图
  	 */
      @Override
  	public ModelAndView getModelAndView() {
  		return new ModelAndView("CS/CSSR0301");
  	}
      
  	@Override
  	@RequestMapping(value = "/H.go") // f02="HT"
  	public ModelAndView home(CSPVOSupport paramBean){
  		getLogger().debug("paramBean" + paramBean);
  		ModelAndView pageMAV = getModelAndView();
  		paramBean.setF02(super.getLoginType());
  		pageMAV.addObject("parentGroups",CSSR03Service_.findParentGroup(paramBean));
  		getLogger().debug("pageMAV" + pageMAV);
  		return pageMAV;
  	}
  	
  	
  	
  	/**
  	 * 插入一条记录
  	 * @param paramBean
  	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
  	 */
  	@Override
  	@RequestMapping(value = "/C.go")
  	public ModelAndView doInsert(CSPVOSupport paramBean) {
  		ModelAndView pageMAV = getModelAndView();
  		//paramBean.setPuk(paramBean.getK01());
  		getLogger().debug("paramBean" + paramBean);	
  		paramBean.setF02(super.getLoginType());
  		int result=getService().doInsert(paramBean);
  		if(result==1){
  			pageMAV.addObject("obj", paramBean);
  		}else if(result==-1){
  			pageMAV.addObject("obj", "failure");
  		}else{
  			pageMAV.addObject("obj", "failure");
  		}
  		 String url = "redirect:F.go";
    	 pageMAV = new ModelAndView(url);
  		getLogger().debug("pageMAV" + pageMAV);		
  		return pageMAV;
  	}
  	
  	
  	@RequestMapping(value="/CheckUserGroup.go")
   	public void checkUserName(HttpServletRequest request,HttpServletResponse response)
   	{  
   		PrintWriter pw =null;
   		try{
   			String groupCode = request.getParameter("groupCode");
   			CSPVOSupport parambean = new CSPVOSupport();
   			parambean.setK01(groupCode);
   			pageVO.setPageData(parambean);
   			CSSR03Service_.doSelectPage(pageVO);
   			List<FrameworkDataBean> fdb = (List<FrameworkDataBean>) pageVO.getPageData();	
   			pw = response.getWriter();
	        if(fdb!=null&&fdb.size()>0){
	        	pw.write("fail");
   			}else{
   				pw.write("success");
   			}
   	   		pw.flush();
   		}catch(Exception e){
        	pw.write("fail");
   		}
   
   	}
  	
  	
  	
  	/**
  	 * 数据一览
  	 */
  	@RequestMapping(value = "/F.go") // f02="HT"
  	public ModelAndView doFindList(CSPVOSupport paramBean) {
  		//查询用户组,查询上级用户组
  		ModelAndView pageMAV = getModelAndView();
  		getLogger().debug("paramBean===>>>" + paramBean);
  		paramBean.setF02(super.getLoginType());
  		paramBean.setEb5(super.getProductId());
  		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		pageVO.setPageData(paramBean);
		pageMAV.addObject(pageVO);
		getService().doSelectPage(pageVO);
		//查询父用户组
  		pageMAV.addObject("parentGroups",CSSR03Service_.findParentGroup(paramBean));
		pageMAV.setViewName("CS/CSSR03");
  		getLogger().debug("pageMAV===>>>" + pageMAV);
  		return pageMAV;
  	}
  	
  /**
   * 准备赋权
   * @param paramBean
   * @return
   */
  @RequestMapping(value = "/TG.go")
  public ModelAndView doToGrant(CSPVOSupport paramBean){
	    //准备跳转到赋权页面
		ModelAndView pageMAV = getModelAndView();
		paramBean.setF05(super.getLoginType());
  		getLogger().debug("paramBean===>>>" + paramBean);
  		//查询出所有菜单
  		List<MyMap>  lstTree= cssb03Service.findTree(paramBean);
  		MyMap allChecked = new MyMap();
  		allChecked.put("id", "0");
  		allChecked.put("pid", "-1");
  		allChecked.put("name", "\"贷付宝\"");
  		allChecked.put("checked", "false");
  		lstTree.add(0,allChecked);
  		JSONArray.fromObject(lstTree).toString();
  		pageMAV.addObject("menus",lstTree);  
  		//菜单转为json构成树状
  		pageMAV.setViewName("CS/CSSR0401");
  		getLogger().debug("pageMAV===>>>" + pageMAV);
  		return pageMAV;
  }
  
  
  /**
   * 赋权于取消赋权
   * @param paramBean
   * @return
   */
  @RequestMapping(value = "/G.go")
  public ModelAndView doGrant(){
	  //赋权操作
		ModelAndView pageMAV = getModelAndView();
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();   
  		String[] k01=request.getParameterValues("k01");
  		StringBuilder sb = new StringBuilder();
  		for(int i=0;k01!=null&&i<k01.length;i++){
  		    if(i!=0){
  	  		     sb.append(",");
  		    } 
  			sb.append("").append(k01[i]).append("");
  		}
  		List<String> lt = Arrays.asList(k01);
  		String   puk = request.getParameter("puk");
  		CSPVOSupport csvoSupport = new CSPVOSupport();
  		csvoSupport.setK01(sb.toString());
  		csvoSupport.setF05(super.getLoginType());
  		csvoSupport.setPuk(puk);
  		csvoSupport.setList(lt);
  		//把权限添加进用户组
  		csvoSupport.setEb5(super.getProductId());
  		CSUserGroupRightBusiness_.saveGrant(csvoSupport);
  		
        pageMAV.addObject("msg", "赋权成功");
        pageMAV.addObject("retun","/CSSR03/F.go");
  		pageMAV.setViewName("CS/CSSR0402");
  		getLogger().debug("pageMAV===>>>" + pageMAV);
  		return pageMAV;
  }
  
  	
  	/**
  	 * 查询一条记录
  	 * @param paramBean
  	 * @see ISSQLDaoSupport#doSelect(CSPVOSupport)
  	 */
  	@Override
  	@RequestMapping(value = "/R.go") //f02='HT'
  	public ModelAndView doRead(CSPVOSupport paramBean) {
  		ModelAndView pageMAV = getModelAndView();
  		getLogger().debug("paramBean===>>>" + paramBean);	
  		paramBean.setF02(super.getLoginType());
  		pageMAV.addObject("parentGroups",CSSR03Service_.findParentGroup(paramBean));
  		paramBean.setF02("");
  		pageMAV.addObject("parambean1", getService().doRead(paramBean));
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
  	@RequestMapping(value = "/D.go")
  	public ModelAndView doDelete(CSPVOSupport paramBean) {
  		ModelAndView pageMAV = getModelAndView();
  		getLogger().debug("paramBean===>>>" + paramBean);
  		int result=getService().toDelete(paramBean);
  		if(result==-1){
  			pageMAV.addObject("obj", "success");
  			pageMAV.setViewName("cs/sb01");
  		}else if(result==1){
  			pageMAV.addObject("obj", "删除失败");
  		}else{
  			pageMAV.addObject("obj", "删除失败");
  		}
  	    String url = "redirect:F.go";
 	    pageMAV = new ModelAndView(url);
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
  	@RequestMapping(value = "/U.go")
  	public ModelAndView doUpdate(CSPVOSupport paramBean) {
  		ModelAndView pageMAV = getModelAndView();
  		getLogger().debug("paramBean===>>>" + paramBean);
  		//查询得到该记录，加载至“cs/sb03c1”页面	
  		 int result=getService().doUpdate(paramBean);	
  		 String url = "redirect:F.go";
     	 pageMAV = new ModelAndView(url);
  		getLogger().debug("pageMAV===>>>" + pageMAV);
  		return pageMAV;
  	}
}
