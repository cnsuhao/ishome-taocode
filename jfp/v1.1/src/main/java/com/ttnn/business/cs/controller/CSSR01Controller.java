package com.ttnn.business.cs.controller;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.biz.CSUserGroupBusiness;
import com.ttnn.business.cs.service.CSSR01Service;
import com.ttnn.business.cs.service.CSSR03Service;
import com.ttnn.business.cs.service.MenuService;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("CSSR01")
/**
 *  允许用户登录信息
 * @author duan.p
 *
 */
public class CSSR01Controller extends MyControllerSupportImpl {
	
    @Resource
    protected CSSR01Service CSSR01Service_; //允许用户登录
    
    @Resource 
    protected WMUI01Service WMUI01Service_; //用户信息
    
    @Resource
    protected CSSR03Service CSSR03Service_;  //用户组信息
    
    @Resource
    protected CSUserGroupBusiness CSUserGroupBusiness_;  //用户组业务信息
    
    @Resource
    protected MenuService menuService;  //菜单业务
    
	@Override
	public MyServiceSupportImpl getService() {
		return CSSR01Service_;
	}
	
	@Override	
    public Logger getLogger(){
        return LoggerFactory.getLogger(CSSR01Controller.class);
    }
    
   
    /**
   	 * 页面视图
   	 */
       @Override
   	public ModelAndView getModelAndView() {
   		return new ModelAndView("CS/CSSR0101");
   	}
       
       
    /**
     *   跳转至添加页面 
     */
   	@Override
   	@RequestMapping(value = "/H.go")  //f02=HT
   	public ModelAndView home(CSPVOSupport paramBean){
   		getLogger().debug("paramBean" + paramBean);
   		ModelAndView pageMAV = getModelAndView();
   		paramBean.setEb5(super.getProductId());
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
   	@RequestMapping(value = "/C.go") //f05='HT'
   	public ModelAndView doInsert(CSPVOSupport paramBean,HttpServletRequest request) {
   		//添加允许用户登录,并把用户赋权
   		ModelAndView pageMAV = getModelAndView();
   		getLogger().debug("paramBean" + paramBean);	
   		paramBean.setF05(super.getLoginType());
   		paramBean.setF04( new Md5PasswordEncoder().encodePassword(paramBean.getF04(), null));
		paramBean.setEb5(super.getProductId());

		//TODO
		paramBean.setF05(paramBean.getF05());
		paramBean.setF06("1");
		
		//用户组信息
        String[] groups = request.getParameterValues("groups");
        //把用户添加到用户组
   		int result=  CSUserGroupBusiness_.saveUserToGroup(paramBean, groups);
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
   	
   	
   	/**
   	 * 修改密码页面
   	 * @param paramBean
   	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
   	 */
   	@RequestMapping(value = "/{userid}/tu.go")
   	public ModelAndView doToUpdatePassowrd(@PathVariable String userid) {
   		//修改密码页面
   		ModelAndView pageMAV = getModelAndView();
   		CSPVOSupport bean = new CSPVOSupport();
   		bean.setPuk(userid);
   		FrameworkDataBean db = WMUI01Service_.doRead(bean); //读取个人信息
   		pageMAV.addObject("userInfo", db);
   		//如果确认为其上线，才可以修改密码
   		if(db.getK01().equals(super.getLoginerId())){
   			pageMAV.setViewName("WM/QT/WMYHXQ1");
   		}
   		getLogger().debug("pageMAV" + pageMAV);		
   		return pageMAV;
   	}
   	
   	
   	
   	/**
   	 * 后台修改密码页面2
   	 * @param paramBean
   	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
   	 */
   	@RequestMapping(value = "/{userid}/tu2.go")
   	public ModelAndView doToUpdatePassowrd2(@PathVariable String userid) {
   		//修改密码页面
   		ModelAndView pageMAV = getModelAndView();
   		CSPVOSupport bean = new CSPVOSupport();
   		bean.setPuk(userid);
   		FrameworkDataBean db = WMUI01Service_.doRead(bean); //读取个人信息
   		pageMAV.addObject("userInfo", db);
   		//如果确认为其上线，才可以修改密码
   		
   			pageMAV.setViewName("WM/QT/WMYHXQ1");	
   		getLogger().debug("pageMAV" + pageMAV);		
   		return pageMAV;
   	}
   	
   	
   	/**
   	 * 密码修改提交
   	 * @param paramBean
   	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
   	 */
   	@RequestMapping(value = "/{userid}/du.go")
   	public void doUpdatePassowrd(CSPVOSupport parambean,@PathVariable String userid,HttpServletResponse response) {
   		//修改密码页面
   		PrintWriter pw = null ;
   		try {
   		 pw = response.getWriter();

   		CSPVOSupport bean = new CSPVOSupport();
   		int result =0;
   		bean.setPuk(userid);
   		FrameworkDataBean db = WMUI01Service_.doRead(bean); 
   		//如果确认为其上线，才可以修改密码
   		//if(db.getK01().equals(super.getLoginerId())){
   			
   		    CSPVOSupport  support = new CSPVOSupport();
   		    support.setPuk(userid);
   		   
   		    FrameworkDataBean dab = (FrameworkDataBean)    CSSR01Service_.doRead(support);
   		    support.setUu1(dab.getUu1());
   		    support.setF04( new Md5PasswordEncoder().encodePassword(parambean.getF04(), null));
   		 result = CSSR01Service_.doUpdate(support);  //更新用户
   		//}
		 if(result==1){
			 pw.write("success");  

		 }else{
			 pw.write("fail");  
		 }
		
		 pw.flush();
		}  catch (Exception e){
			 pw.write("fail");
			 pw.flush();
			e.printStackTrace();
		}
   		
   	}
   	

   	@RequestMapping(value="/CheckUserName.go")
   	public void checkUserName(HttpServletRequest request,HttpServletResponse response)
   	{  
   		PrintWriter pw =null;
   		try{
   			pw = response.getWriter();
   			String username = request.getParameter("username");
   			CSPVOSupport parambean = new CSPVOSupport();
   			parambean.setF03(username);
   			parambean.setDdd("");
   			pageVO.setPageData(parambean);
   			CSSR01Service_.doSelectPage(pageVO);
   			List<FrameworkDataBean> fdb = (List<FrameworkDataBean>) pageVO.getPageData();	
	        if(fdb!=null&&fdb.size()>0){
	        	pw.write("fail");
   			}else{
   				pw.write("success");
   			}
   	   		pw.flush();
   		}catch(Exception e){
        	pw.write("fail");
        	pw.flush();
   		}
   
   	}
   	
   	/**
   	 * 数据一览 -后台
   	 */
   	@RequestMapping(value = "/F.go")  //f05='HT'
   	public ModelAndView doFindList(CSPVOSupport paramBean) {
   		//允许用户一览表
   		ModelAndView pageMAV = getModelAndView();
   		getLogger().debug("paramBean===>>>" + paramBean);
   		paramBean.setEb5(super.getProductId());
   		paramBean.setF05(super.getLoginType());
   		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		pageVO.setPageData(paramBean);
		pageMAV.addObject(pageVO);
		getService().doSelectPage(pageVO);
		//查询所有用户组
		paramBean.setF05("");
		paramBean.setF02(paramBean.getF05());
  		pageMAV.addObject("parentGroups",CSSR03Service_.findParentGroup(paramBean));
   		pageMAV.setViewName("CS/CSSR01");
   		getLogger().debug("pageMAV===>>>" + pageMAV);
   		return pageMAV;
   	}
   	
   	

   	
   	
   	
   	
   	/**
   	 * 查询一条记录
   	 * @param paramBean
   	 * @see ISSQLDaoSupport#doSelect(CSPVOSupport)
   	 */
   	@Override
   	@RequestMapping(value = "/R.go")  //f02='HT'
   	public ModelAndView doRead(CSPVOSupport paramBean) {
   		//读取用户信息
   		ModelAndView pageMAV = getModelAndView();
   		getLogger().debug("paramBean===>>>" + paramBean);
   		pageMAV.addObject("parambean1", getService().doRead(paramBean));//查出用户状况
   		paramBean.setF02(super.getLoginType());
   		paramBean.setEb5(super.getProductId());
   		pageMAV.addObject("parentGroups",CSSR03Service_.findParentGroup(paramBean)); //查询所有用户组
   		pageMAV.addObject("myGroups",menuService.selectUserInGroup(paramBean));//查出所属用户组
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
   		//删除用户信息
   		ModelAndView pageMAV = getModelAndView();
   		getLogger().debug("paramBean===>>>" + paramBean);
   		int result=getService().toDelete(paramBean);
   		if(result==-1){
   			pageMAV.addObject("obj", "success");
   			pageMAV.setViewName("CS/CSSR01");
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
   		//更新允许用户登录信息,并且更新其所属组
   		ModelAndView pageMAV = getModelAndView();
   		getLogger().debug("paramBean===>>>" + paramBean);
   		if(StringUtils.isNotEmpty(paramBean.getF04())){
   	   		paramBean.setF04( new Md5PasswordEncoder().encodePassword(paramBean.getF04(), null));	
   		}
   		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
   	    String[] groups = request.getParameterValues("groups");
   	    
   	    
   	    
   		CSUserGroupBusiness_.updateUserToGroup(paramBean,groups);  //修改用户信息及其所属组
   	    String url = "redirect:F.go";
 	    pageMAV = new ModelAndView(url);
   		getLogger().debug("pageMAV===>>>" + pageMAV);
   		return pageMAV;
  }
}
