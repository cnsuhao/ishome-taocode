package com.ttnn.business.wm.controller.ht;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.wm.biz.WMHTMXBusiness;
import com.ttnn.business.wm.biz.WMHTTotalBusiness;
import com.ttnn.business.wm.service.WMBM03Service;
import com.ttnn.business.wm.service.WMBMA1Service;
import com.ttnn.business.wm.service.WMBMA2Service;
import com.ttnn.business.wm.service.WMSZT2Service;
import com.ttnn.common.util.DateUtil;
import com.ttnn.common.util.ExcelUtil;
import com.ttnn.common.util.PKUtil;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;

@Controller
@RequestMapping("/WMSZT2")
public class WMSZT2Controller extends MyControllerSupportImpl {
	
	@Autowired
	private WMBMA1Service WMBMA1Service;
	
	@Autowired
	private WMSZT2Service WMSZT2Service;
	
	@Autowired
	private WMBM03Service WMBM03Service;
	
	@Resource
	private WMHTMXBusiness WMHTMXBusiness;
	
	/*@Autowired
	public WMQTCZTXBussiness testBusi;
	*/
	 /**
		 * 数据一览
		 */
	
	
		@RequestMapping(value = "/F.go",method = RequestMethod.GET)
		public String doFindList(CSPVOSupport paramBean,Model model) {
			
			
			pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
			pageVO.setPageData(paramBean);				   
			
			WMSZT2Service.doSelectPage(pageVO);
		    model.addAttribute(pageVO);

//			model.addAttribute("page", pageVO);
			
			return "WM/HT/WMSZT2";
		}
		@RequestMapping(value = "/{roadid}/U.go",method = RequestMethod.GET)
		public String doupdate(CSPVOSupport param,@PathVariable String roadid,Model model){
			param.setPuk(roadid);
			
			FrameworkDataBean data = WMBMA1Service.doRead(param);
			model.addAttribute("roadinfo",data);
			return "WM/HT/WMSZT2C1";
		}
		
		@RequestMapping(value = "/{roadid}/U.go",method = RequestMethod.POST)
		public String doupdate(CSPVOSupport param,@PathVariable String roadid,FrameworkDataBean data,Model model){
			 param.setPuk(data.getPuk());
			 param.setUu1(data.getUu1());
			 param.setF05(data.getF05());
			 param.setF19(data.getF19());
			 param.setF17(data.getF17());
			 WMBMA1Service.doUpdate(param);
			return "redirect:/WMSZT2/F.go";
		}
		
//		结算生成委托文件佣金
			@RequestMapping(value = "/{roadid}/S.go",method = RequestMethod.GET)
			public void dodataimpyj(CSPVOSupport param,@PathVariable String roadid,HttpServletRequest request, HttpServletResponse response){
				 param.setK02(roadid);
				 param.setF06("0");
				 pageVO.setPageCurrent(Integer.parseInt(param.getPageCurrent()));
				 pageVO.setPageData(param);				   
					
				 WMSZT2Service.doSelectPageYJ(pageVO);
				 List<FrameworkDataBean> dataList = (List)pageVO.getPageData();
//				 还要做其他的事？？
				 for(FrameworkDataBean data:dataList){
					 CSPVOSupport para = new CSPVOSupport();
					 para.setPuk(data.getPuk());
					 para.setF06("2");
					 WMSZT2Service.doUpdateYJ(para);
				 }
				 
				 response.setCharacterEncoding("utf-8");  
			     response.setContentType("multipart/form-data");  
			     String filename = DateUtil.currentTimeMillis0()+".xls";
			  
			     response.setHeader("Content-Disposition", "attachment;fileName="+filename); 
			     OutputStream os = null;
			     ByteArrayOutputStream baos = new ByteArrayOutputStream();
				try {
					os = response.getOutputStream();	
					if(roadid.equals("rbtd")){
						 ExcelUtil.createWorkBookRB(dataList).write(baos);
					}else{
						 ExcelUtil.createWorkBook(dataList).write(baos);
					}
				

//                   ExcelUtil.createWorkBook(dataList).write(baos);	
                   byte[] ba = baos.toByteArray();
			      
			       os.write(ba);
			   
		
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				finally{
					 try {
						 os.flush();
						os.close();
						baos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}  
			    
			    
				 
//				 return "WM/HT/WMTXG11";
			}
		
		
		
//		结算生成委托文件
			@RequestMapping(value = "/{roadid}/D.go",method = RequestMethod.GET)
			public void dodataimp(CSPVOSupport param,@PathVariable String roadid,HttpServletRequest request, HttpServletResponse response){
				 param.setK02(roadid);
				 param.setF06("0");
				 pageVO.setPageCurrent(Integer.parseInt(param.getPageCurrent()));
				 pageVO.setPageData(param);				   
					
				 WMSZT2Service.doSelectPageTX(pageVO);
				 List<FrameworkDataBean> dataList = (List)pageVO.getPageData();
//				 还要做其他的事？？、
				 
				 CSPVOSupport txjsjl = new CSPVOSupport();
				 txjsjl.setPuk(PKUtil.getPUKey());
				 
				 
				 for(FrameworkDataBean data:dataList){
					 CSPVOSupport para = new CSPVOSupport();
					 para.setPuk(data.getPuk());
					 para.setF06("2");
					 WMSZT2Service.doUpdateTX(para);
				 }
				 
				 response.setCharacterEncoding("utf-8");  
			     response.setContentType("multipart/form-data");  
			     String filename = DateUtil.currentTimeMillis0()+".xls";
			  
			     response.setHeader("Content-Disposition", "attachment;fileName="+filename); 
			     OutputStream os = null;
			     ByteArrayOutputStream baos = new ByteArrayOutputStream();
				try {
					os = response.getOutputStream();	
					if(roadid.equals("rbtd")){
						 ExcelUtil.createWorkBookRB(dataList).write(baos);
					}else{
						 ExcelUtil.createWorkBook(dataList).write(baos);
					}
				

//                   ExcelUtil.createWorkBook(dataList).write(baos);	
                   byte[] ba = baos.toByteArray();
			      
			       os.write(ba);
			   
		
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				finally{
					 try {
						 os.flush();
						os.close();
						baos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}  
			    
			    
				 
//				 return "WM/HT/WMTXG11";
			}
		
//			查看提现充值明细	
			@RequestMapping(value = "/{txid}/X.go",method = RequestMethod.GET)
			public String doFindczmx(CSPVOSupport param,@PathVariable String txid,Model model){
				pageVO.setPageCurrent(Integer.parseInt(param.getPageCurrent()));

				param.setF05("%Y/%m/%d");
				param.setEb5(super.getProductId());
				param.setF20(txid);
				pageVO.setPageData(param);				   
				
				WMHTMXBusiness.doSelectPageCZMX(pageVO);
				
			    model.addAttribute(pageVO);
				
				 
				 return "WM/HT/TXCZMX";
			}
			
			
//			查看佣金充值明细	
			@RequestMapping(value = "/{yjid}/M.go",method = RequestMethod.GET)
			public String doFindyjmx(CSPVOSupport paramBean,@PathVariable String yjid,Model model){
				paramBean.setF05("%Y/%m/%d");
				paramBean.setEb5(super.getProductId());
				paramBean.setF03(yjid);
				
				pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
				pageVO.setPageData(paramBean);	
				pageVO.setOrderby(" order by eb4 desc ");
				
				WMHTMXBusiness.doSelectPageYJMX(pageVO);
			    model.addAttribute(pageVO);

				model.addAttribute("param", paramBean);
				
				 
				 return "WM/HT/YJJSMX";
			}
			
		
//	待结算记录	
		@RequestMapping(value = "/{roadid}/E.go",method = RequestMethod.GET)
		public String dobalance(CSPVOSupport param,@PathVariable String roadid,Model model){
			 param.setK02(roadid);
			 param.setF06("0");
			 pageVO.setPageCurrent(Integer.parseInt(param.getPageCurrent()));
			 pageVO.setPageData(param);				   
				
			 WMSZT2Service.doSelectPageTX(pageVO);
			 model.addAttribute(pageVO);
			 return "WM/HT/WMTXG11";
		}
		
//		待结算佣金记录	
			@RequestMapping(value = "/{roadid}/J.go",method = RequestMethod.GET)
			public String dobalanceyj(CSPVOSupport param,@PathVariable String roadid,Model model){
				 param.setK02(roadid);
				 param.setF06("0");
				 pageVO.setPageCurrent(Integer.parseInt(param.getPageCurrent()));
				 pageVO.setPageData(param);				   
					
				 WMSZT2Service.doSelectPageYJ(pageVO);
				 model.addAttribute(pageVO);
				 return "WM/HT/YJTXJL";
			}
			
			@RequestMapping(value = "/{roadid}/N.go",method = RequestMethod.GET)
			public String dobalanceyjj(CSPVOSupport param,@PathVariable String roadid,Model model){
				 param.setK02(roadid);
				 param.setF06("2");
				 pageVO.setPageCurrent(Integer.parseInt(param.getPageCurrent()));
				 pageVO.setPageData(param);				   
					
				 WMSZT2Service.doSelectPageYJ(pageVO);
				 model.addAttribute(pageVO);
				 return "WM/HT/YJTXJLJ";
			}
		
			
		
		
		
//		已结算记录	
			@RequestMapping(value = "/{roadid}/L.go",method = RequestMethod.GET)
			public String dobalanceend(CSPVOSupport param,@PathVariable String roadid,Model model){
				 param.setK02(roadid);
				 param.setF06("2");
				 pageVO.setPageCurrent(Integer.parseInt(param.getPageCurrent()));
				 pageVO.setPageData(param);				   
					
				 WMSZT2Service.doSelectPageTX(pageVO);
				 model.addAttribute(pageVO);
				 return "WM/HT/WMTXG12";
			}
		
		
//		打开通道
		@RequestMapping(value = "/{roadid}/C.go",method = RequestMethod.GET)
		public String doopen(CSPVOSupport param,@PathVariable String roadid,Model model){
			param.setPuk(roadid);
			param.setF06("0");
			
			WMSZT2Service.doWMSZT2Update(param);
		
			return "redirect:/WMSZT2/F.go";
		}
		
//	关闭通道	
		@RequestMapping(value = "/{roadid}/O.go",method = RequestMethod.GET)
		public String doclose(CSPVOSupport param,@PathVariable String roadid,Model model){
			param.setPuk(roadid);
			
			FrameworkDataBean data = WMBMA1Service.doRead(param);
			model.addAttribute("roadinfo",data);
			return "WM/HT/WMSZT2D1";
		}
		
		@RequestMapping(value = "/{roadid}/O.go",method = RequestMethod.POST)
		public String doclose(CSPVOSupport param,@PathVariable String roadid,FrameworkDataBean data,Model model){
			 param.setPuk(data.getPuk());
			 param.setUu1(data.getUu1());
			 param.setF05(data.getF07());
			 param.setF06("1");
			 WMBMA1Service.doUpdate(param);
			return "redirect:/WMSZT2/F.go";
		}
		
		/*@RequestMapping(value = "/test",method = RequestMethod.GET)
		public String doTran(){
			testBusi.testTransaction();
			return "WM/HT/success";
		}*/
		
//	日统计	
		@RequestMapping(value = "/{roadid}/R.go",method = RequestMethod.GET)
		public String doRitj(CSPVOSupport param,@PathVariable String roadid,Model model){
			pageVO.setPageCurrent(Integer.parseInt(param.getPageCurrent()));
			pageVO.setPageData(param);	
			param.setPuk(roadid);
			WMSZT2Service.doSelectPageRitj(pageVO);
			
		    model.addAttribute(pageVO);
			
		
			return "/WM/HT/TDLRTJR";
		}
//		周统计
		@RequestMapping(value = "/{roadid}/Z.go",method = RequestMethod.GET)
		public String doZhtj(CSPVOSupport param,@PathVariable String roadid,Model model){
			pageVO.setPageCurrent(Integer.parseInt(param.getPageCurrent()));
			pageVO.setPageData(param);	
			param.setPuk(roadid);
			WMSZT2Service.doSelectPageZhtj(pageVO);
		    model.addAttribute(pageVO);
			
		
			return "/WM/HT/TDLRTJZ";
		}
//		月统计
		@RequestMapping(value = "/{roadid}/Y.go",method = RequestMethod.GET)
		public String doYutj(CSPVOSupport param,@PathVariable String roadid,Model model){
			pageVO.setPageCurrent(Integer.parseInt(param.getPageCurrent()));
			pageVO.setPageData(param);
			param.setPuk(roadid);
			WMSZT2Service.doSelectPageYutj(pageVO);
		    model.addAttribute(pageVO);
			
		
			return "/WM/HT/TDLRTJY";
		}
		@Resource
		protected WMBMA2Service WMBMA2Service_;
		/**
		 * 动态费率设定
		 * @param paramBean
		 * @param roadid
		 * @return
		 */
		@RequestMapping(value = "/{roadid}/DF.go",method = RequestMethod.GET)
		public ModelAndView doWMHTFLMX(@PathVariable String roadid){
			getLogger().debug("paramBean" + roadid);
			ModelAndView pageMAV = getModelAndView();
			
			CSPVOSupport paramBean = new CSPVOSupport();
			paramBean.setK01(roadid);
			pageVO.setPageData(paramBean);
			WMBMA2Service_.doSelectPage(pageVO);
			
			pageMAV.addObject("roadid",roadid);
			pageMAV.addObject("ddd","1");
		    pageMAV.addObject("list",pageVO.getPageData());
		    pageMAV.setViewName("WM/HT/WMFLMX");
			getLogger().debug("pageMAV" + pageMAV);
			return pageMAV;
		}

}
