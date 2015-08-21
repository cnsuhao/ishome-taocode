package com.hundsun.med.hdp.service.util;

public class RequestXmlDataUtil {
	
	/*
	 * 请求的webservice的地址
	 */
	public static String WEBSERVICE_URL="http://125.125.125.48:8998/webservice/services/register?wsdl";
	
	public static String WEBSERVICE_URL_FEES="http://125.125.125.48:8998/webservice/services/patientCharges?wsdl";	
	
	public static String WEBSERVICE_URL_SHEETS="http://125.125.125.48:8998/webservice/services/report?wsdl";
	
	public static String WEBSERVICE_URL_EXAMINREPORT="http://125.125.125.48:8998/webservice/services/testReport?wsdl";
	
	
	/*
	 * GetDept：获取科室
	 * 通过Type=0获取所有科室
	 */
	public static String GET_DEPTS_XML_REQUEST="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.webservice.heletianxia.com/\">"
			+"<soapenv:Header/>"
			+"<soapenv:Body>"
			+"<int:GetDept>"
			+" <!--Optional:-->"
			+"<TransactionId></TransactionId>"
			+"<!--Optional:-->"
			+"<HospitalId></HospitalId>"
			+"<!--Optional:-->"
			+"<Type>0</Type>"
			+"</int:GetDept>"
			+"</soapenv:Body>"
			+"</soapenv:Envelope>";

	/*
	 * GetDoctor：获取特定科室下面的医生
	 * 通过DeptId获取医生信息
	 */
	public static String GET_DOCTORS_XML_REQUEST="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.webservice.heletianxia.com/\">"
			+"<soapenv:Header/>"
			+"<soapenv:Body>"
			+"<int:GetDoctor>"
			+" <!--Optional:-->"
			+"<TransactionId>0</TransactionId>"
			+"<!--Optional:-->"
			+"<DeptId>DEPTID_STRING</DeptId>"
			+"</int:GetDoctor>"
			+"</soapenv:Body>"
			+"</soapenv:Envelope>";
	
	
	/*
	 * GetArrangement：获取排班     专家、科室的排班同时返回
	 */
	public static String GET_ARRANGEMENT_XML_REQUEST = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.webservice.heletianxia.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+ "<int:GetArrangement>"
			+ " <!--Optional:-->"
			+ "<TransactionId>0</TransactionId>"
			+ "!--Optional:-->"
			+ "<RBASDate></RBASDate>"
			+ "<!--Optional:-->"
			+ "<RBASDay>7</RBASDay>"
			+ "<!--Optional:-->"
			+ "<DoctorId></DoctorId>"
			+ "<!--Optional:-->"
			+ "<DeptId></DeptId>"
			+ "</int:GetArrangement>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
	
	/*
	 * 获取某一排班下的余号
	 */
	public static String GET_ARRANGEMENT_AVAIABLECOUNT_REQUEST = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.webservice.heletianxia.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+ "<int:GetAvailableRegCount>"
			+ " <!--Optional:-->"
			+ "<RBASId>RBASID_STRING</RBASId>"
			+ "!--Optional:-->"
			+ "<TransactionId></TransactionId>"
			+ "<!--Optional:-->"
			+ "<RBASDate>RBASDATE_STRING</RBASDate>"
			+ "</int:GetAvailableRegCount>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
	
	/*
	 * 获取某一排班下的时间段及时间段内的余号和限号
	 */
	public static String GET_ARRANGEMENT_HBTIME_REQUEST = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.webservice.heletianxia.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+ "<int:GetHbTime>"
			+ " <!--Optional:-->"
			+ "<RBASId>RBASID_STRING</RBASId>"
			+ "</int:GetHbTime>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
	
	
	/*
	 * 获取余号
	 */
	public static String GET_AVAILABLEREGCOUNT_XML_REQUEST="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.webservice.heletianxia.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+ "<int:GetAvailableRegCount>"
			+ " <!--Optional:-->"
			+ "<RBASId>RBASID_STRING</RBASId>"
			+ "!--Optional:-->"
			+ "<TransactionId>0</TransactionId>"
			+ "<!--Optional:-->"
			+ "<RBASDate>RBASDATE_STRING</RBASDate>"
			+ "</int:GetAvailableRegCount>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
	
	/*
	 * 挂号
	 */
	public static String DOREGISTER_XML_REQUEST="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.webservice.heletianxia.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+ "<int:DoRegister>"
			+ " <!--Optional:-->"
			+ "<PatientID>PATIENT_STRING</PatientID>"
			+ "!--Optional:-->"
			+ "<RBASId>REASID_STRING</RBASId>"
			+ "<!--Optional:-->"
			+ "<TransactionId></TransactionId>"
			+"<!--Optional:-->"
			+"<BDate>BDATE_STRING</BDate>"
			+"<!--Optional:-->"
			+"<UserID></UserID>"
			+"<!--Optional:-->"
			+"<ClientAddress></ClientAddress>"
			+"<!--Optional:-->"
			+"<Method></Method>"
			+"<!--Optional:-->"
	        +"<Fee>FEE_STRING</Fee>"
	        +"<!--Optional:-->"
	        +"<HTime>HTIME_STRINHG</HTime>"
			+ "</int:DoRegister>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
	
	/*
	 * 取消挂号
	 */
	public static String CANCEL_REGISTER_XML_REQUEST="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.webservice.heletianxia.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+ "<int:DoCancelRegister>"
			+"<!--Optional:-->"
	        +"<TransactionId></TransactionId>"
	        +"!--Optional:-->"
	        +"<ApptId>PATIENTID_STRING</ApptId>"
	        +"<!--Optional:-->"
	        +"<UserId></UserId>"
	        +"<!--Optional:-->"
	        +"<ClientAddress></ClientAddress>"
	        +"<!--Optional:-->"
	        +"<Method></Method>"
	        +"<!--Optional:-->"
	        +"<BDate>BDATE_STRING</BDate>"
	        +"<!--Optional:-->"
	        +"<RBASId>RBASID_STRING</RBASId>"
	        +"<!--Optional:-->"
	        +"<HbTime>HBTIME_STRING</HbTime>"
			+ "</int:DoCancelRegister>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
	
	/*
	 *病人信息校验
	 */
    public static String PATIENT_CHECK_XML_REQUEST="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.webservice.heletianxia.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+ "<int:HisCheckPatientInfo>"
			+ " <!--Optional:-->"
			+ "<PatientID></PatientID>"
			+ "!--Optional:-->"
			+ "<IDCardNO></IDCardNO>"
			+ "<!--Optional:-->"
			+ "<CardNo>CARDNO_STRING</CardNo>"
			+ "<!--Optional:-->"
			+ "<CardType>0</CardType>"
			+ "<!--Optional:-->"
			+ "<Name>NAME_STRING</Name>"
			+ "<!--Optional:-->"
			+ "<Sex></Sex>"
			+ "<!--Optional:-->"
			+ "<Age></Age>"
			+ "<!--Optional:-->"
			+ "<PhoneNo>PHONENO_STRING</PhoneNo>"
			+ "<!--Optional:-->"
			+ "<MailingAddress>ADDRESS_STRING</MailingAddress>"
			+ "</int:HisCheckPatientInfo>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
    
    /*
     * 检查报告列表
     */
    public static String GET_JCSHEET_XML="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.webservice.heletianxia.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+ "<int:GetPatientJCSheet>"
			+ "<!--Optional:-->"
			+ "<TransactionId></TransactionId>"
			+ "<!--Optional:-->"
			+ "<HospitalId></HospitalId>"
			+ "<!--Optional:-->"
			+ "<IDCard></IDCard>"
			+ "<!--Optional:-->"
			+ "<CardNO>CARDNO_STRING</CardNO>"
			+ "<!--Optional:-->"
			+ "<PatName>PATNAME_STRING</PatName>"
			+ "<!--Optional:-->"
			+ "<StartDate>STARTTIMA_STRING</StartDate>"
			+ "<!--Optional:-->"
			+ "<EndDate>ENDDATE_STRING</EndDate>"
			+ "</int:GetPatientJCSheet>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
    
    /*
     * 检查报告详情
     */
    public static String GET_JCSHEET_DETAIL_XML="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.webservice.heletianxia.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+ "<int:GetPatientJCSheetDetail>"
			+ "<!--Optional:-->"
			+ " <TransactionId></TransactionId>"
			+ "!--Optional:-->"
			+ "<HospitalId></HospitalId>"
			+ "<!--Optional:-->"
			+ "<IDCard></IDCard>"
			+ "<!--Optional:-->"
			+ "<CardNO></CardNO>"
			+ "<!--Optional:-->"
			+ " <PatName></PatName>"
			+ "<!--Optional:-->"
			+ "<ReqNo>REQNO_STRING</ReqNo>"
			+ "<!--Optional:-->"
			+ " <ItemClass></ItemClass>"
			+ "<!--Optional:-->"
			+ "<PatientId>PATIENTID_STRING</PatientId>"
			+ "</int:GetPatientJCSheetDetail>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
    
    /*
     * 检验报告
     */
    public static String GET_JYSHEET_XML="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.webservice.heletianxia.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+ "<int:GetPatientJYSheet>"
			+ "<!--Optional:-->"
			+ "<TransactionId></TransactionId>"
			+ "!--Optional:-->"
			+ "<HospitalId></HospitalId>"
			+ "<!--Optional:-->"
			+ "<IDCard></IDCard>"
			+ "<!--Optional:-->"
			+ "<CardNO>CARDNO_STRING</CardNO>"
			+ "<!--Optional:-->"
			+ "<PatName>PATNAME_STRING</PatName>"
			+ "<!--Optional:-->"
			+ "<StartDate>START_STRING</StartDate>"
			+ "<!--Optional:-->"
			+ "<EndDate>END_STRING</EndDate>"
			+ "<!--Optional:-->"
			+ "</int:GetPatientJYSheet>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
    
    /*
     * 检验报告详情
     */
    public static String GET_JYSHEET_DETAIL_XML="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.webservice.heletianxia.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+ "<int:GetPatientJYSheetDetail>"
			+ "<!--Optional:-->"
			+ "<TransactionId></TransactionId>"
			+ "<!--Optional:-->"
			+ "<HospitalId></HospitalId>"
			+ "<!--Optional:-->"
			+ "<ReqNo>REGNO_STRING</ReqNo>"
			+ "<!--Optional:-->"
			+ "<ItemClass></ItemClass>"
			+ "<!--Optional:-->"
			+ "<sdate></sdate>"
			+ "<!--Optional:-->"
			+ "</int:GetPatientJYSheetDetail>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
   
    /*
     * 体检报告列表
     */
    public static String GET_REPORTEXAM_XML="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.webservice.heletianxia.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+ "<int:GetExamReportList>"
			+ " <!--Optional:-->"
			+ "<TransactionId></TransactionId>"
			+ "!--Optional:-->"
			+ "<HospitalId></HospitalId>"
			+ "<!--Optional:-->"
			+ " <PatName></PatName>"
			+ "<!--Optional:-->"
			+ "<ExamID>ExamIDString</ExamID>"
			+ "<!--Optional:-->"
			+ " <StartDate>STARTTIMA_STRING</StartDate>"
			+ "<!--Optional:-->"
			+ "<EndDate>ENDDATE_STRING</EndDate>"
			+ "</int:GetExamReportList>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
    
    /*
     * 体检报告列表
     */
    public static String GET_REPORTEXAM_DETAIL_XML="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.webservice.heletianxia.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+ "<int:GetExamReportDetail>"
			+ " <!--Optional:-->"
			+ "<TransactionId></TransactionId>"
			+ "!--Optional:-->"
			+ "<HospitalId></HospitalId>"
			+ "<!--Optional:-->"
			+ "<ExamID>ExamIDString</ExamID>"
			+ "<!--Optional:-->"
			+ "<ExamTimes></ExamTimes>"
			+ "<!--Optional:-->"
			+ " <Date></Date>"
			+ "</int:GetExamReportDetail>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
    
    
    /*
     * 体检报告详情
     */
    
    /*
     * 门诊费用
     */
    public static String GET_MZ_FEES="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.webservice.heletianxia.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+"<int:GetPatientMzCharges>"
	        +"<!--Optional:-->"
	        +"<HospitalId></HospitalId>"
	        +"<!--Optional:-->"
	        +"<TransactionId></TransactionId>"
	        +"<!--Optional:-->"
	        +"<CardNO>CARDNO_STRING</CardNO>"
	        +"<!--Optional:-->"
	        +"<Name>NAME_STRING</Name>"
	        +"<!--Optional:-->"
	        +"<IDCard></IDCard>"
	        +"<!--Optional:-->"
	        +"<State>1</State>"
	        +"</int:GetPatientMzCharges>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
    
    /*
     * 住院费用
     */
    public static String GET_ZY_FEES="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.webservice.heletianxia.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+"<int:GetPatientZyCharges>"
	        +"<!--Optional:-->"
	        +"<HospitalId></HospitalId>"
	        +"<!--Optional:-->"
	        +"<TransactionId></TransactionId>"
	        +"<!--Optional:-->"
	        +"<Inpno>IPNO_STRING</Inpno>"
	        +"<!--Optional:-->"
	        +"<PatientName></PatientName>"
	        +"<!--Optional:-->"
	        +"<IDCard></IDCard>"
	        +"<!--Optional:-->"
	        +"<State></State>"
	        +"</int:GetPatientZyCharges>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
}
