package com.ttnn.business.aq.controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.aq.biz.AQSMSBusiness;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.common.util.PKUtil;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("AQSMS1")
/** 实名认证*/
public class AQSMS1Controller extends MyControllerSupportImpl   {
    @Resource
    protected WMUI01Service WMUI01Service_;
    @Resource
    protected AQSMSBusiness AQSMSBusiness_;
     
    @Override
    public MyServiceSupportImpl getService(){
        return WMUI01Service_;
    }

    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(AQSMS1Controller.class);
    }
    /**
	 * 页面视图
	 */
    @Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("AQ/AQSMS1");
	}
    
	@Override
	@RequestMapping(value = "/H.go")
	public ModelAndView home(CSPVOSupport paramBean){
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		
		    paramBean.setPuk(super.getLoginerId());
	
		    
		   pageMAV.addObject("paramBean",WMUI01Service_.doRead(paramBean));
		/*
		 paramBean.setF03(super.getLoginerId());
		 paramBean.setEb5(super.getProductId());   
		pageMAV.addObject("list", AQSMSBusiness_.doFindyh(paramBean));
		*/ 
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
	public ModelAndView doUpdate1(@RequestParam("file") MultipartFile file,@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2,CSPVOSupport ParamBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + ParamBean);
		String a1 = PKUtil.getNumber();
		String a2 = PKUtil.getNumber();
		String a3 = PKUtil.getNumber();
		//更新姓名，身份证等信息
		CSPVOSupport c1 = new CSPVOSupport();
		c1.setF04(ParamBean.getF04());
		c1.setF03(ParamBean.getF03());
		c1.setF06(ParamBean.getF06());
		c1.setF05(ParamBean.getF05());
		c1.setPuk(super.getLoginerId());
		AQSMSBusiness_.doUpdate1(c1);
		
		HttpServletRequest request = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
		   if(file!=null){
			 File dir=new File(request.getSession().getServletContext().getRealPath("/")+"/uploads/wm/"+super.getProductId()+"/"+new 
                                 SimpleDateFormat("yyyyMMdd").format(new Date())+"/"+a1+".jpg");
					dir.getParentFile().mkdirs();
			  try {
				file.transferTo(dir);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}  catch(Exception e){
				e.printStackTrace();
			}
		   }
		   if(file1!=null){
				 File dir=new File(request.getSession().getServletContext().getRealPath("/")+"/uploads/wm/"+super.getProductId()+"/"+new 
                                     SimpleDateFormat("yyyyMMdd").format(new Date())+"/"+a2+".jpg");
						dir.getParentFile().mkdirs();
				  try {
					file1.transferTo(dir);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}  catch(Exception e){
					e.printStackTrace();
				}
			   }
		   if(file2!=null){
				 File dir=new File(request.getSession().getServletContext().getRealPath("/")+"/uploads/wm/"+super.getProductId()+"/"+new 
                                   SimpleDateFormat("yyyyMMdd").format(new Date())+"/"+a3+".jpg");
						dir.getParentFile().mkdirs();
				  try {
					file2.transferTo(dir);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}  catch(Exception e){
					e.printStackTrace();
				}
				 
		   }
		  //插入QQ号，邮箱信息
		CSPVOSupport c2 = new CSPVOSupport();
		c2.setPuk(super.getLoginerId());
		c2.setF01(ParamBean.getF04());
		c2.setF12(ParamBean.getF03());
		c2.setF16(ParamBean.getF16());
		c2.setF17(ParamBean.getF17());
		c2.setFb1("/uploads/wm/"+super.getProductId()+"/"+new 
                      SimpleDateFormat("yyyyMMdd").format(new Date())+"/"+a1+".jpg");
		c2.setFb2("/uploads/wm/"+super.getProductId()+"/"+new 
                      SimpleDateFormat("yyyyMMdd").format(new Date())+"/"+a2+".jpg");
		c2.setFb3("/uploads/wm/"+super.getProductId()+"/"+new 
                      SimpleDateFormat("yyyyMMdd").format(new Date())+"/"+a3+".jpg");
		AQSMSBusiness_.doInsert1(c2);
		pageMAV.setViewName("AQ/AQSMS2");		
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	

	  @RequestMapping(value="P.go")  
	    public String upload(HttpServletRequest request,HttpServletResponse response ){  
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
	        return "AQ/AQSMS1";  
	    }  
	
	
	}
