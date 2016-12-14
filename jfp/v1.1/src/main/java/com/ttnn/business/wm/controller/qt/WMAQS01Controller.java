package com.ttnn.business.wm.controller.qt;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.common.util.PKUtil;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMAQS01")
/** 实名认证*/
public class WMAQS01Controller extends MyControllerSupportImpl   {
    @Resource
    protected WMUI01Service WMUI01Service;

    @Override
    public MyServiceSupportImpl getService(){
        return WMUI01Service;
    }

    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(WMAQS01Controller.class);
    }
    /**
	 * 页面视图
	 */
    @Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("WM/QT/WMAQS1");
	}
    
	@Override
	@RequestMapping(value = "/H.go")
	public ModelAndView home(CSPVOSupport paramBean){
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}
	
	
	
	
	/**
	 * 插入一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
	 */
	@RequestMapping(value = "/C.go")
	public ModelAndView doInsert(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		int result = getService().doInsert(paramBean);
		if (result == 1) {
			pageMAV.addObject("obj", paramBean);
		} else if (result == -1) {
			pageMAV.addObject("obj", "failure");
		} else {
			pageMAV.addObject("obj", "failure");
		}
		PageVO paramPageModel = new PageVO();
		paramPageModel.setPageData(paramBean);
		pageMAV.addObject(paramPageModel);
//		List<FrameworkDataBean> list = getService().doSelectPage(paramPageModel);
		pageMAV.addObject("list", getService().doSelectPage(paramPageModel).getPageData());
		pageMAV.setViewName("WM/QT/WMZHT3");		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
//  图片上传
	  @RequestMapping(value="/upload.go")  
	    public String upload(HttpServletRequest request,HttpServletResponse response ){  
		  
//		  String folderName=  request.getSession().getServletContext().getRealPath("/") + 
//					"resources/project/" + paramBean.getPuk() + "/logo/" ;
		  
		  
		  
		  
	    	MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());

	    	MultipartHttpServletRequest multipartHttpservletRequest = resolver.resolveMultipart(request);  
	        MultipartFile multipartFile = multipartHttpservletRequest.getFile("file");  
	        String originalFileName=multipartFile.getOriginalFilename();  
	        File file=new File("file");  
	        if(!file.exists()){  
	            file.mkdir();  
	        }  
	       String filename=PKUtil.getPUKey();
	        System.out.println(file.getAbsolutePath());  
	        try {  
	            FileOutputStream fileOutputStream=new FileOutputStream(file+"/filename"+originalFileName.substring(originalFileName.lastIndexOf('.'), originalFileName.length()));  
	            fileOutputStream.write(multipartFile.getBytes());  
	            fileOutputStream.flush();  
	            fileOutputStream.close();  
	        } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            return "error";  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            return "error";  
	        }  
	        return "WM/QT/WMAQS1";  
	    }  
	
	
	}
