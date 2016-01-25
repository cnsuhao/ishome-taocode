package com.ttnn.business.aq.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSR01Service;
import com.ttnn.business.cs.service.CSSR03Service;
import com.ttnn.business.cs.service.CSSS01Service;
import com.ttnn.business.wm.biz.WMFZGLService;
import com.ttnn.business.wm.biz.WMZKJGService;
import com.ttnn.common.util.DateUtil;
import com.ttnn.common.util.PKUtil;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("AQMM")
/** 安全中心密码修改*/
public class WMAQMMController extends MyControllerSupportImpl {
	
	@Resource
	protected CSSR01Service CSSR01Service_;//允许用户登录
	
	@Override
    public MyServiceSupportImpl getService(){
        return CSSR01Service_;
    }
	
    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMAQMMController.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("AQ/AQMMM2");
    }
    
    @Override
    @RequestMapping(value = "/H.go" )
    public ModelAndView home(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageMAV.setViewName("AQ/AQMMM2");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
       
	/**
	 * 更新一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
	@RequestMapping(value = "/U.go" )
	public ModelAndView doUpdate1(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);		
		paramBean.setPuk(super.getLoginerId());//获取登录者id		
		//判断原密码
		FrameworkDataBean paramBean1=  (FrameworkDataBean) CSSR01Service_.doRead(paramBean);
		String str1=new Md5PasswordEncoder().encodePassword(paramBean.getEb1(), null);
	//	String str=new Md5PasswordEncoder().encodePassword(paramBean1.getF04(), null);
		if(paramBean1.getF04().equals(str1)){
			//判断输入新密码
			if(paramBean.getF04().equals(paramBean.getEb2())){
				//修改密码加密
				CSPVOSupport uc = new CSPVOSupport();
				uc.setF04(new Md5PasswordEncoder().encodePassword(paramBean.getF04(), null));
				uc.setPuk(super.getLoginerId());
				uc.setUu1(paramBean1.getUu1());
				int result = CSSR01Service_.doUpdate(uc);
				if(result<1){
					pageMAV.addObject("message", "修改失败");
				}
				else{
					pageMAV.addObject("message", "修改成功");
				}
			}else{
				pageMAV.addObject("message", "两次输入密码不一致，请重新输入");
			}			
		}else{
			pageMAV.addObject("message", "原密码有误，请重新输入");
		}
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
}
