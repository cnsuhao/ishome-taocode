<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>add</title>

   <link href="/resources/css/wm/ht/bootstrap.min.css" rel="stylesheet"
	media="screen">
	<link href="/resources/css/wm/ht/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/ht/jquery.js"></script>
<script src="/resources/js/wm/ht/bootstrap.js"></script>

  </head>

  <body class="body">
	<table width="100%" border="0">
								<tr>
									<td colspan="">
										<div class="content">
											<div class="main">
												<div class="main-one">
													<div class="main-one-box">
														<div class="main-one-title">
															<h5><b>提成结算设置</b></h5>
														</div>
														<div class="main-one-add">
															<div id="" class="step" style="display:block;">
																<sf:form class="form-horizontal" role="form" modelAttribute="systeminfo">
																<sf:hidden path="puk"/>
								
																	<div class="form-group control-group" style="margin:0;">
																		<label for="inputPassword3" class="col-sm-2 control-label">绑定域名</label>
																		<div class="col-lg-3 form-float">
																			d.69dai.com
																		</div>
																		<span class="step-tip">提示信息</span>
																	</div>
								  
																	<div class="form-group control-group" style="margin:0;">
																		<label for="inputEmail3" class="col-sm-2 control-label">结算费用</label>
																		<div class="col-lg-3 form-float">
																			<sf:input path="fb1" value="15.00"  style="width:63px;"/><span style="color:red"> &nbsp;元/笔&nbsp;*</span>
																		</div>
																		<span class="step-tip">提示信息</span>
																	</div>
																	<div class="form-group control-group" style="margin:0;">
																		<label for="inputEmail3" class="col-sm-2 control-label">结算上限</label>
																		<div class="col-lg-3 form-float">
																			 <sf:input path="fb2" value="50000.00" style="width:63px;"/><span style="color:red"> &nbsp;元/笔&nbsp;*</span>
																		</div>
																		<span class="step-tip">提示信息</span>
																	</div>
								  
                                 
								  
                                  <div class="form-group control-group" style="margin:0;">
                                    <div class="col-sm-offset-2 col-sm-10">
									  <input type="submit" class="btn btn-primary"></button>
                                      
                                    </div>
                                  </div>
                                </sf:form>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
      					  </td>
      					</tr>
    				  </table>
    				</td>
  			  </tr>
  			</table>


  </body>
</html>
