package com.ttnn.business.od.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.fvo.UserBean;
import com.ttnn.business.cs.service.CSSR01Service;
import com.ttnn.business.cs.service.CSSS01Service;
import com.ttnn.business.cs.service.PTCPService;
import com.ttnn.common.util.DateUtil;
import com.ttnn.common.util.PKUtil;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("HOMEORDER")
/**information*/
public class OrderController extends MyControllerSupportImpl {
    @Resource
    protected CSSS01Service CSSS01Service_;
    
    @Resource
    protected CSSR01Service CSSR01Service_;
    
    @Resource
    protected PTCPService PTCPService_;

    @Override
    public MyServiceSupportImpl getService(){
        return CSSS01Service_;
    }

    @Override
    public Logger getLogger(){
        return LoggerFactory.getLogger(OrderController.class);
    }

    @Override
    public ModelAndView getModelAndView(){
        return new ModelAndView("OD/information");
    }
    
    @Override
    @RequestMapping(value = "/H.go" )
    public ModelAndView home(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
//		pageMAV.setViewName("WM/ZK/information");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
    
    @RequestMapping(value = "/A.go" )
    public ModelAndView subSuccess(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);	
		System.out.println(paramBean.toString());
		pageMAV.setViewName("OD/Complete");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
    
    @RequestMapping(value = "/B.go" )
    public ModelAndView independentSelection(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);		
		pageMAV.setViewName("OD/Custom");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
    
    @RequestMapping(value = "/CheckAdmin.go" )
    public ModelAndView checkAdminName(CSPVOSupport paramBean,HttpServletRequest rq,
    		HttpServletResponse rs) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);	
		CSPVOSupport paramBean1=new CSPVOSupport();
		paramBean1.setF03(rq.getParameter("name"));
		pageVO.setPageData(paramBean1);
		List<FrameworkDataBean> list=(List<FrameworkDataBean>) CSSR01Service_.doSelectPage(pageVO).getPageData();
		try {
			PrintWriter pw=rs.getWriter();
			//判断
			if(!list.isEmpty()){
				pw.write("success");
			}else{
				pw.write("failure");
			}
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	//	pageMAV.setViewName("OD/Custom");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
    
    
    /**
     * 增加
     */
    @RequestMapping(value = "/C.go" )
    public ModelAndView doInsert(CSPVOSupport paramBean,@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);	
		//MultipartFile file1 = multipartRequest.getFile("file1");
		HttpServletRequest request = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();		
		if(file1!=null){
				 File dir=new File(request.getSession().getServletContext().getRealPath("/")+"/logo/od/"+new 
                                  SimpleDateFormat("yyyyMMdd").format(new Date())+"/"+paramBean.getPuk()+"前台.jpg");
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
				 File dir=new File(request.getSession().getServletContext().getRealPath("/")+"/logo/od/"+new 
                                SimpleDateFormat("yyyyMMdd").format(new Date())+"/"+paramBean.getPuk()+"后台.jpg");
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
		   String pwd=paramBean.getFb2();
		//添加客户
		paramBean.setPuk(PKUtil.getPUKey());
		paramBean.setK03(paramBean.getFb1());
	    paramBean.setFb1("");
	    paramBean.setFb2("");
	    paramBean.setEb5("TTNN_PT");	    
	    paramBean.setF14("/logo/od/"+new 
                SimpleDateFormat("yyyyMMdd").format(new Date())+"/"+paramBean.getPuk()+"前台.jpg");
	    paramBean.setF15("/logo/od/"+new 
                SimpleDateFormat("yyyyMMdd").format(new Date())+"/"+paramBean.getPuk()+"后台.jpg");
	  // CSSS01Service_.doInsert(paramBean);	
		UserBean user = new UserBean();
		user.setProductId("TTNN_PT");  
		user.setUserId("TTNN_SYSTEM"); 
		user.setLoginDateTime(DateUtil.currentTimestamp());
		CSSS01Service_.setLoginerBean(user);
	    CSSS01Service_.doInsert(paramBean);
	    //添加管理员
	    //判断管理员用户名是否重复
	    CSPVOSupport paramBean2=new CSPVOSupport();
	    //取得用户名读取记录
	    paramBean2.setF03(paramBean.getK03());
	   List<FrameworkDataBean> para= PTCPService_.doReadC(paramBean2);
	   if(!para.isEmpty()){
	    	pageMAV.addObject("message", "管理员用户名重复，请重新输入");
	    }else{	   
		    CSPVOSupport paramBean1=new CSPVOSupport();
		    paramBean1.setPuk(PKUtil.getPUKey());
		    paramBean1.setF02("Y");
		    paramBean1.setF03(paramBean.getK03());
	//	    System.out.println(pwd);
		    paramBean1.setF04(new Md5PasswordEncoder().encodePassword(pwd, null));		    		    
		    paramBean1.setEb5(paramBean.getPuk());
		    CSSR01Service_.setLoginerBean(user);
		    CSSR01Service_.doInsert(paramBean1); 
		 //   PTCPService_.do3InsertPt(paramBean);
		  //  CSSS01Service_.doInsert(paramBean1);
	//      CSSR01Service_.doInsert(paramBean1);
	//	    pageMAV=new ModelAndView("redirect:/HOMEORDER1/F.go?k01="+paramBean.getPuk());	    
	    	pageMAV=new ModelAndView("redirect:F.go?eb1="+paramBean.getPuk());
	//      pageMAV=new ModelAndView("redirect:F.go");
	    }
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
  /*  @Override
    @RequestMapping(value = "/F.go" )
    public ModelAndView doSelectPage(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
	//	paramBean.setEb5("TTNN_PT");
		//横向查询该产品的四个套餐
		paramBean.setK01("1384156198796.0");//测试数据
		List<FrameworkDataBean> list=PTCPService_.doSelectPT(paramBean);		
		//查询该产品套餐的所有功能
		List<FrameworkDataBean> list1=PTCPService_.doSelectGN(paramBean);	
		for (FrameworkDataBean frameworkDataBean : list1) {
			System.out.println(frameworkDataBean.toString());
		}
		//将查询结果存入map集合
		Map<String, List> map=new HashMap<String, List>();			
			for (FrameworkDataBean frameworkDataBean1 : list) {//套餐
				//存入功能结果
				List<String> newList=new ArrayList<String>();
				for (FrameworkDataBean frameworkDataBean : list1) {//功能
					//判断产品ID
					if(frameworkDataBean1.getK01().equals(frameworkDataBean.getK01())
						)
						//map.put(frameworkDataBean.getF01(), frameworkDataBean1.getF01());	
						//将查询功能作为v存入map
						newList.add(frameworkDataBean.getF01());
				}	
//				for (String string : newList) {
//					System.out.println(string.toString());
//				}
//					套餐数据为k //功能数据为v
				map.put(frameworkDataBean1.getF01(), newList);
			}	
			System.out.println(map.size());
		//查看map中的值
		Iterator keys = map.keySet().iterator();
		while(keys.hasNext()){
			String key = (String) keys.next();
			List value = map.get(key);
			System.out.println(key+"/t"+value);
		}			
		Set set=map.keySet();
		List<String> newnew=new ArrayList<String>();
		newnew.addAll(set);
		List<List> newnewnew=new ArrayList<List>();
		newnewnew.addAll(map.values());		
//		Object[] arr= set.toArray();		
//		pageVO.setPageData(map);
		//判断产品id相同		
	/*	Map<String,List> map=new HashMap<String,List>();
		map.put("1",list); 
		map.put("2",list1);
		pageVO.setPageData(map);*/
	/*	pageMAV.addObject("newnew", newnew);
		pageMAV.addObject("newnewnew", newnewnew);
		pageMAV.addObject("pagedata",pageVO.getPageData());
		pageMAV.addObject("map", map);
		pageMAV.addObject("list1", list1);
		pageMAV.setViewName("OD/Package");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}*/
    
    @RequestMapping(value = "/F.go" )
    public ModelAndView doSelectPageCP(CSPVOSupport paramBean) {
    	ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		paramBean.setEb1("1384156198796.0");
		List<FrameworkDataBean> list1=PTCPService_.do1SelectCP(paramBean);
		List<String> list=new ArrayList<String>();
		Set<String> setnew=new HashSet<String>();
		for (FrameworkDataBean fb : list1) {
			setnew.add(fb.getEb1());
			setnew.add(fb.getEb2());
			setnew.add(fb.getEb3());
			setnew.add(fb.getEb4());
			
		}
		list.addAll(setnew);
		//为空去掉
		for (int i = 0; i <list.size(); i++) {
			if("".equals(list.get(i))){
				list.remove(list.get(i));
			}
		}
		 Collections.sort(list);
		 List<FrameworkDataBean> list2=new ArrayList<FrameworkDataBean>();
		 for (int i = 0; i <list.size(); i++) {
			 CSPVOSupport paramBean1=new CSPVOSupport();
			 paramBean1.setPuk(list.get(i));
			 paramBean1.setK01(paramBean.getEb1());
			FrameworkDataBean fd=PTCPService_.doRead1(paramBean1);	
			list2.add(fd);
		}
		System.out.println(list2.size());		
		pageMAV.addObject("list1", list1);
		pageMAV.addObject("list2", list2);
	//	pageMAV.addObject(pageVO);
		pageMAV.setViewName("OD/Package");
    	return pageMAV;
    }

}
