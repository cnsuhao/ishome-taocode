package org.jfpc.common.sms;

import javax.annotation.Resource;

import org.jfpc.constants.ISModelConstants;
import org.jfpc.framework.bean.RESTResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 短信中心接口
 * @author Spook
 * @since 0.0.10
 * @version 0.1.0 2014/9/4
 *
 */
@Controller
public class SMSApi {
private static final Logger logger = LoggerFactory.getLogger(SMSApi.class);
	@Resource
	SMSService SMSService_;
	
//	@Resource
//	SMSClientFactory smsf;		
//	
//	@Resource
//	CSB1_SEND_SMS_INFOService smsInfoService;
//	/**
//	 * 解析xml
//	 */
//	public String parserXML(String strXML){
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        try {
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            StringReader sr = new StringReader(strXML.trim());
//            InputSource is = new InputSource(sr);
//            Document doc = builder.parse(is);
//            Element rootElement = doc.getDocumentElement();
//            NodeList error = rootElement.getElementsByTagName("error");
//            for (int i = 0; i < error.getLength(); i++) {
//                Node type = error.item(0);
//                String status=type.getFirstChild().getNodeValue();
//                return status;
//               
//            }
//        } catch (ParserConfigurationException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }catch (SAXException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return "";
//    }
	
	/**
	 * 根据模版发送短信
	 */
	@RequestMapping(value = "/00055000/{phoneNum}/{modelId}", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public RESTResultBean m01091011(@PathVariable String phoneNum,String modelId) {
		logger.debug("======="+phoneNum);
		logger.debug("======="+modelId);
		RESTResultBean tb = new RESTResultBean();
		//接受数据后，保存到队列
		 try {
			  
			// SMSBean smsr = SMSService_.sendMessage(phoneNum, modelId,null);
			 
			    String resultcode = "0";
//			    String resultcode= parserXML(resultMessage); 
//
////				JSONObject jsonobject = JSONObject.fromObject(resultMessage);
////				//网关返回状态
////				String resultcode= (String)jsonobject.get("status");
//				//插入短信信息表
//				String[] phoneArr=phoneNum.split(",");
//				for(int i=0;i< phoneArr.length;i++){
//					CSB1_SEND_SMS_INFODBO smsInfo=new CSB1_SEND_SMS_INFODBO();
//					smsInfo.setK01_service_id("01091011");
//					smsInfo.setK02_send_tel_number(phoneArr[i]);
//					smsInfo.setK03_sms_network_id(sc.getConfig().getSmsId());
//					smsInfo.setF01_network_status(SMSConstants.NETWORK_STATUS_OPEN);//网关状态
//					smsInfo.setF02_send_status(SMSConstants.SEND_STATUS_ALREADY);//发送状态
//					smsInfo.setF03_back_status(resultcode);//网关返回状态
//					smsInfo.setBbb_meno(message);
//					//smsInfo.setCc1_creat_time(smsInfo.getUuu());
//					int res=smsInfoService.doInsert(smsInfo);
//				}
				tb.setCode(resultcode);
				if("0".equals(resultcode)){
					tb.setMessage(ISModelConstants.SMS_SEND_SUCCESS);
				}else{
					tb.setMessage(ISModelConstants.SMS_SEND_FAIL);
				}
				
		} catch (Exception e) {
			tb.setCode("9999");
			tb.setMessage("系统繁忙，请稍后再试！");
			// TODO: handle exception
			logger.error("m01091011",e);
		}
		 
		return tb;
	}

////	/**
////	 * 发送短信
////	 */
////	@RequestMapping(value = "/01091012/{phoneNum}/{messageCode}", method = RequestMethod.GET)
////	@ResponseBody
////	public RESTResultBean m01091012(@PathVariable String phoneNum,@PathVariable String messageCode,String a,a,a) {
////		logger.debug("======="+phoneNum);
////		logger.debug("======="+message);
////		RESTResultBean tb = new RESTResultBean();
////		//接受数据后，保存到队列
////		 try {
////			 
////			    SMSClient sc = smsf.build();
////			    //从短信模版里面获得信息
////			    smsf.buildMessage(messageCode, "123",123);
////			    
////			    String resultMessage=sc.sendMessage(phoneNum, message);
////			    
////				JSONObject jsonobject = JSONObject.fromObject(resultMessage);
////				//网关返回状态
////				String resultcode= (String)jsonobject.get("status");
////				//插入短信信息表
////				CSB1_SEND_SMS_INFODBO smsInfo=new CSB1_SEND_SMS_INFODBO();
////				smsInfo.setK01_service_id("01091011");
////				smsInfo.setK02_send_tel_number(phoneNum);
////				smsInfo.setK03_sms_network_id(sc.getConfig().getSmsId());
////				smsInfo.setF01_network_status("0");//网关状态
////				smsInfo.setF02_send_status("1");//发送状态
////				smsInfo.setF03_back_status(resultcode);//网关返回状态
////				smsInfo.setBbb_meno(message);
////				smsInfo.setCc1_creat_time(smsInfo.getUuu());
////				int res=smsInfoService.doInsert(smsInfo);
////				tb.setResultcode(resultcode);
////				tb.setResultmessage((String)jsonobject.get("status"));
////				
////				
////		} catch (Exception e) {
////			tb.setResultcode("1");
////			// TODO: handle exception
////			logger.error("m01091011",e);
////		}
////		 
////		return tb;
////	}
////	
//	/**
//	 * 发送验证码	 
//	 * @param phoneNum 目标手机号码
//	 * @param bizId 业务识别ID
//	 * @param codeLength 验证码长度
//	 */
//	@RequestMapping(value = "/01091020/{phoneNum}/{bizId}/{codeLength}", method = RequestMethod.GET)
//	@ResponseBody
//	public RESTResultBean m01091020(@PathVariable String phoneNum,
//			@PathVariable String bizId,
//			@PathVariable String codeLength) {
//		logger.debug(phoneNum);
//		RESTResultBean tb = new RESTResultBean();
//		 try {
//			    DefaultSMSClient sc = smsf.buildClient();
//			    
//			    String codeNumber=  RandomHelper.nextCode(Integer.parseInt(codeLength));
//			    
//			    String resultMessage=sc.sendMessage(phoneNum,codeNumber);
//			    
//				JSONObject jsonobject = JSONObject.fromObject(resultMessage);
//				//网关返回状态
//				String resultcode= (String)jsonobject.get("status");
//				//插入短信信息表
//				CSB1_SEND_SMS_INFODBO smsInfo=new CSB1_SEND_SMS_INFODBO();
//				smsInfo.setK01_service_id("01091020");
//				smsInfo.setK02_send_tel_number(phoneNum);
//				smsInfo.setK03_sms_network_id(sc.getConfig().getSmsId());
//				smsInfo.setF01_network_status(SMSConstants.NETWORK_STATUS_OPEN);//网关状态
//				smsInfo.setF02_send_status(SMSConstants.SEND_STATUS_ALREADY);//发送状态
//				smsInfo.setF03_back_status(resultcode);//网关返回状态
//				smsInfo.setBbb_meno(codeNumber);
//				smsInfo.setCc1_creat_time(smsInfo.getUuu());
//				int res=smsInfoService.doInsert(smsInfo);
//				tb.setResultcode(resultcode);
//				if("0".equals(resultcode)){
//					tb.setResultmessage(SMSConstants.SMS_SEND_SUCCESS);
//				}else{
//					
//					tb.setResultmessage(SMSConstants.SMS_SEND_FAIL);
//				}
//				
//		} catch (Exception e) {
//			tb.setResultcode("9999");
//			tb.setResultmessage("系统繁忙，请稍后再试！");
//			//e.printStackTrace();
//			// TODO: handle exception
//			logger.error("m01091020",e);
//		}
//		
//		return tb;
//	}
//	
//	/**
//	 * 网关控制
//	 */
//	@RequestMapping(value = "/01091032/{smsId}/{smsType}", method = RequestMethod.POST)
//	@ResponseBody
//	public RESTResultBean m01091032(@PathVariable String smsId,@PathVariable String smsType) {
//		logger.debug("======="+smsId);
//		logger.debug("======="+smsType);
//		RESTResultBean tb = new RESTResultBean();
//		try {
//			int res=smsf.colseSMSGateway(smsId, smsType);
//			if(res>0){
//				  tb.setResultcode("0");
//				  tb.setResultmessage("网关操作成功!");
//			}else{
//				  tb.setResultcode("1");
//				  tb.setResultmessage("网关操作失败!");
//			}
//		} catch (Exception e) {
//			tb.setResultcode("9999");
//			tb.setResultmessage("系统繁忙，请稍后再试！");
//			// TODO: handle exception
//			logger.error("m01091032",e);
//		}
//		return tb;
//	}
//	
}
