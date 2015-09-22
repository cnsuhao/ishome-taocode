<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="org.jfpc.framework.helper.*" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>回溯主页</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<link rel="stylesheet" type="text/css" href="/resources/css/jquery.mCustomScrollbar.css">
<link rel="stylesheet" type="text/css" href="/resources/css/hospital_cert.css">
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/function.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<%@ include file="/resources/jsp/yy/inc.jsp"%>
<script>
$(function(){
	$(".carousel").jCarouselLite({
        btnNext: ".jcnext",
        btnPrev: ".jcprev",
        visible: 3,
        circular: false,
        scroll: 2
    });
    $("#plastic").mCustomScrollbar({theme:"minimal"});
});
</script>
</head>
<body class="whiteBg">
<div class="formTitle mTop10">
	${ZCZLIST[0].f03_cpzwmc}&nbsp;&nbsp;&nbsp;&nbsp;<span class="gray">${ZCZLIST[0].f01_zczzwmc}</span>
</div>
<div class="boxPContent" id="plastic" style="height:400px; overflow:hidden; overflow-y:auto;">
	<!--生产厂家-->
	<div class="plasticBox">
		<span class="plasticLine"></span>
		<span class="yuan"></span>
		<div class="plasticContentBox">
			<div class="plasticName">生产厂家/总代理</div>
			<div class="rFloat contnet">
				<ul class="plasticCertList">
					<li>
						<div class="uploadBox uploadW80">
							<div class="uploadBtn"></div>
							<div class="uploadImg"><img src="<%=ImagePath%>/00003030/${CJ_YYZZ.bbb}/${loginer.token}"></div>
							<div class="uploadBoxText">营业执照</div>
						</div>
						<%-- <p>${CJ_YYZZ.f04_yxksrq.substring(0,7) }至${ CJ_YYZZ.f05_yxzzrq.substring(0,7)}</p> --%>
					</li>
					<li>
						<div class="uploadBox uploadW80">
							<div class="uploadBtn"></div>
							<div class="uploadImg"><img src="<%=ImagePath%>/00003030/${CJ_SCXKZ.bbb}/${loginer.token}"></div>
							<div class="uploadBoxText">生产许可证</div>
						</div>
						<%-- <p>${CJ_YYZZ.f04_yxksrq.substring(0,7) }至${ CJ_YYZZ.f05_yxzzrq.substring(0,7)}</p> --%>
					</li>
				</ul>
			</div>
			<div class="plasticContent">
				<h3>${CJDBO.f01_qyqc }</h3>
				<p class="mTop10 gray">
					<img src="/resources/images/icon/tb_m_1.png" align="absmiddle" /> ${CJDBO.f30_lxrxm } ${CJDBO.f32_lxrdh } ${CJDBO.f16_lxdh }<br />
					<img src="/resources/images/icon/tb_m_2.png" align="absmiddle" /> ${CJDBO.f11_szs }${CJDBO.f12_szds }${CJDBO.f13_szqx }${CJDBO.f14_szxxdz }
				</p>
			</div>
		</div>
	</div>

	<!--注册证-->
	<c:forEach items="${ZCZLIST }" var="item1">
			<div class="plasticBox">
				<span class="yuanLine"></span>
				<div class="plasticContentBox">
					<div class="plasticName">医疗器械注册证</div>
					<div class="rFloat contnet">
						<div class="custom-container fraction">
				        <a href="#" class="jcprev">&lsaquo;</a>
				        <a href="#" class="jcnext">&rsaquo;</a>
				        <div class="carousel">
							<ul>
							<c:forEach items="${item1.images }" var="item2">
								<li>
									<div class="uploadBox uploadW80">
										<div class="uploadBtn"></div>
										<div class="uploadImg"><img src="<%=ImagePath%>/00003030/${item2}/${loginer.token}"></div>
										<div class="uploadBoxText">查看大图</div>
									</div>
								</li>
							</c:forEach>
							</ul>
							<div class="clear"></div>
			    		</div>
					</div>
				
					</div>
					<div class="plasticContent">
						<h3>${item1.f03_cpzwmc}&nbsp;&nbsp;<span class="gray">${ZCZLIST[0].f01_zczzwmc}</span></h3>
						<p class="mTop10 gray">
							${item1.f09_yxksrq }至${ item1.f10_yxzzrq}
						</p>
					</div>
				</div>	
			</div>
	</c:forEach>
	<!--供应商-->
	<div class="plasticBox">
		<span class="yuanLine"></span>
		<div class="plasticContentBox">
			<div class="plasticName">供应商</div>
			<div class="rFloat contnet">
				<div class="custom-container fraction">
			        <a href="#" class="jcprev">&lsaquo;</a>
			        <a href="#" class="jcnext">&rsaquo;</a>
			        <div class="carousel">
						<ul>
							<li>
								<div class="uploadBox uploadW80">
									<div class="uploadBtn"></div>
									<div class="uploadImg"><img src="<%=ImagePath%>/00003030/${GYS_YYZZ.bbb}/${loginer.token}"></div>
									<div class="uploadBoxText">营业执照</div>
								</div>
								<%-- <p>${CJ_YYZZ.f04_yxksrq.substring(0,7) }至${ CJ_YYZZ.f05_yxzzrq.substring(0,7)}</p> --%>
							</li>
							<li>
								<div class="uploadBox uploadW80">
									<div class="uploadBtn"></div>
									<div class="uploadImg"><img src="<%=ImagePath%>/00003030/${GYS_JYXKZ.bbb}/${loginer.token}"></div>
									<div class="uploadBoxText">经营许可证</div>
								</div>
								<%-- <p>${CJ_YYZZ.f04_yxksrq.substring(0,7) }至${ CJ_YYZZ.f05_yxzzrq.substring(0,7)}</p> --%>
							</li>
							<li>
								<div class="uploadBox uploadW80">
									<div class="uploadBtn"></div>
									<div class="uploadImg"><img src="<%=ImagePath%>/00003030/${GYS_SWDJZ.bbb}/${loginer.token}"></div>
									<div class="uploadBoxText">税务登录证</div>
								</div>
								<%-- <p>${CJ_YYZZ.f04_yxksrq.substring(0,7) }至${ CJ_YYZZ.f05_yxzzrq.substring(0,7)}</p> --%>
							</li>
							<c:forEach items="${SQSLIST }" var="item">
								<li>
									<div class="uploadBox uploadW80">
										<div class="uploadBtn"></div>
										<div class="uploadImg"><img src="<%=ImagePath%>/00003030/${item.bbb}/${loginer.token}"></div>
										<div class="uploadBoxText">授权书</div>
									</div>
									<%-- <p>${CJ_YYZZ.f04_yxksrq.substring(0,7) }至${ CJ_YYZZ.f05_yxzzrq.substring(0,7)}</p> --%>
								</li>
							</c:forEach>
							<c:forEach items="${WTSLIST }" var="item">
								<li>
									<div class="uploadBox uploadW80">
										<div class="uploadBtn"></div>
										<div class="uploadImg"><img src="<%=ImagePath%>/00003030/${item.bbb}/${loginer.token}"></div>
										<div class="uploadBoxText">委托人</div>
									</div>
									<%-- <p>${CJ_YYZZ.f04_yxksrq.substring(0,7) }至${ CJ_YYZZ.f05_yxzzrq.substring(0,7)}</p> --%>
								</li>
							</c:forEach>
							<c:forEach items="${GYS_CNS }" var="item">
								<li>
									<div class="uploadBox uploadW80">
										<div class="uploadBtn"></div>
										<div class="uploadImg"><img src="<%=ImagePath%>/00003030/${item.bbb}/${loginer.token}"></div>
										<div class="uploadBoxText">承诺书</div>
									</div>
									<%-- <p>${CJ_YYZZ.f04_yxksrq.substring(0,7) }至${ CJ_YYZZ.f05_yxzzrq.substring(0,7)}</p> --%>
								</li>							
								
							</c:forEach>

						</ul>
		        	<div class="clear"></div>
		    		</div>
				</div>
			</div>
			<div class="plasticContent">
				<h3>${GYS.f01_qyqc }</h3>
				<p class="mTop10 gray">
					<img src="/resources/images/icon/tb_m_1.png" align="absmiddle" /> ${GYS.f30_lxrxm } ${GYS.f32_lxrdh } ${GYS.f16_lxdh }<br />
					<img src="/resources/images/icon/tb_m_2.png" align="absmiddle" /> ${GYS.f11_szs }${GYS.f12_szds }${GYS.f13_szqx }${GYS.f14_szxxdz }
				</p>
			</div>
		</div>
	</div>

	<!--医院-->
	<div class="plasticBox">
		<span class="sanJiao"></span>
		<span class="yuanOver"></span>
		<div class="plasticHosBox font14">${YYMC }</div>
	</div>
	<br />
</div>
</body>
</html>