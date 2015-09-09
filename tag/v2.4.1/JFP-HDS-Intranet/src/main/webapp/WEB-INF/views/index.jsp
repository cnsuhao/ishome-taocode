<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>
	
	<table width="100%" border="1">
	  <tr>
	    <td>
	    </td>
	  </tr>
	  <tr>
	    <td>
	The Time is ${now}<br><br>
	&nbsp;&nbsp;&nbsp;&nbsp;HundSun Hospital Docking Synchronization<br><br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;on&nbsp;&nbsp;[${ver}]<br>
	    </td>
	  </tr>
	  <tr>
	    <td>
	    <script type="text/javascript">
	    //     URL=     /APITest/{operationId}/{hosId}/{bizName}
function doSubmit(){
	if(document.all("jsonData").value==""){
		alert("请输入业务测试内容");
		return;
	}
	TestForm.submit();
}
function doChange(){
	document.all("actionURL").value="/"+document.all("bizName").value ;
}
</script>
		<form id="TestForm" action="/APITest" method="post">
		 actionURL:<br><input type="text" value="自动生成，请最后修改" id = "actionURL" name="actionURL" size="100"/><button type="button" onclick="doSubmit()">Test</button><br>
		 bizName:<br><select id="bizName" name="bizName"  onchange="doChange()">
		 <option value =""></option>
		  <option value ="Department:sync">数据同步...科室(自动)...Department</option>
		  <option value ="Doctor:sync">数据同步...医生(自动)...Doctor</option>
		  <option value="Scheduling:sync">数据同步...排班(自动)...Scheduling</option>
		  
		   <option value="">---------------------</option>
		  <option value ="Department">数据同步...科室(手动)...Department</option>
		  <option value ="Doctor">数据同步...医生(手动)...Doctor</option>
		  <option value="Scheduling">数据同步...排班(手动)...Scheduling</option>
		  
		  <option value="">-----------------------</option>
		   <option value="CreatPhoneUser">前置机...创建手机用户...CreatPhoneUser</option>
		   <option value="ReadPhoneUser">前置机...读取手机用户 ...ReadPhoneUser</option>
		   <option value="UpdatePhoneUser">前置机...更新用户  ...UpdatePhoneUser</option>
		  
		   <option value="CreatUserPatient">前置机...创建患者 ...CreatUserPatient</option>
		   <option value="DeleteUserPatient">前置机...删除患者...DeleteUserPatient</option>
		   <option value="UpdateUserPatient">前置机...更新患者 ...UpdateUserPatient</option>   
		   <option value="ListUserPatient">前置机...患者列表 ...ListUserPatient</option>
		   <option value="ReadUserPatient">前置机...患者详情 ...ReadUserPatient</option>
		   		  
		  <option value="">---------------------</option>
		  <option value="PatientCheck">日常业务...患者校验...PatientCheck</option>
		  <option value="CheckSheetList">日常业务...检查检验一览...CheckSheetList</option>
		  <option value="CheckSheetView">日常业务...检查检验详情...CheckSheetView</option>
		  <option value="ExamineSheetList">日常业务...检验检验一览...ExamineSheetList</option>
		  <option value="ExamineSheetView">日常业务...检验检验详情...ExamineSheetView</option>
		  <option value="ExamReportView">日常业务...体检详情...ExamReportView</option>
		  
		  <option value="PatientQueueList">日常业务...获取患者排队队列状态列表 ...PatientQueueList</option>
		  <option value="PatientQueueRecordList">日常业务...获取某个队列下排队状态列表...PatientQueueRecordList</option>
		  <option value="PatientQueueRecordView">日常业务...获取某个队列下某个排队号的状态详情...PatientQueueRecordView</option>
		  
		  <option value="HosFeesList">日常业务...费用列表信息...HosFeesList</option>
		  <option value="OutFeesList">日常业务...费用明细信息...OutFeesList</option>
		  <option value="FeesHistoryList">日常业务...在院历史查询...FeesHistoryList</option>
		  
		  <option value="">---------------------</option>
		  <option value="RemainQuery">日常业务...余号查询...RemainQuery</option>
		  <option value="TimeSlotRemainQuery">日常业务...余号查询...TimeSlotRemainQuery</option>
		  <option value="StopDiagnosis">日常业务...停诊...StopDiagnosis</option>
		  <option value="ChangeDiagnosis">日常业务...换诊...ChangeDiagnosis</option>
		  <option value="OrderRegister">日常业务...预约挂号...OrderRegister</option>
		  <option value="PayRegister">日常业务...挂号支付确认...PayRegister</option>
		  <option value="CancelRegister">日常业务...取消挂号...CancelRegister</option>
		  <option value="PatientQueue">日常业务...获取患者排队状态列表...PatientQueue</option>
		  <option value="PatientQueueView">日常业务...获取患者排队状态详细...PatientQueueView</option>
		  <option value="PhysicalList">日常业务...体检一览...PhysicalList</option>
		  <option value="PhysicalView">日常业务...体检详情...PhysicalView</option>
		  <option value="Health">日常业务...健康档案...Health</option>
		  <option value="PatientPayResult">日常业务...用户支付结果通知医院...PatientPayResult</option>
		  <option value="PatientPayList">日常业务...用户缴费内容查询（一览）...PatientPayList</option>
		  
		  
		  <option value="GoodList">日常业务...商品查询 ...GoodList</option>
		  <option value="GoodOrder">日常业务...订单创建...GoodOrder</option>
		  <option value="GoodOrderPay">日常业务...订单支付...GoodOrderPay</option>
		  <option value="GoodOrderList">日常业务...订单信息查询...GoodOrderList</option>
		  <option value="CreatPatientCard">日常业务...创建就诊卡...CreatPatientCard</option>
		  <option value="DiagnosisPriceList">日常业务...诊疗项目查询...DiagnosisPriceList</option>
		  <option value="DiagnosisPriceView">日常业务...诊疗项目明细...DiagnosisPriceView</option>
		  <option value="DrugGroupPriceList">日常业务...药品组项目查询...DrugGroupPriceList</option>
		  <option value="DrugGroupPriceView">日常业务...药品组项目明细...DrugGroupPriceView</option>
		  <option value="DrugPriceList">日常业务...药品价格...DrugPriceList</option>
		  
		  <option value="UpdatePhoneUser">日常业务...用户信息...UpdatePhoneUser</option>
		  
		  <option value="">---------------------</option>
		  <option value="">日常业务...药品价格...</option>
		  <option value="">日常业务...药品价格...</option>
		  <option value="">日常业务...药品价格...</option>
		  
		 </select>
		 
		 
		 <br>
		 Json Param:<br><textarea id="jsonData" name="jsonData" rows="20" cols="100" value="${jsonData}"></textarea>
		</form>
		
	    </td>
	  </tr>
	</table>
	

	</body>
</html>
