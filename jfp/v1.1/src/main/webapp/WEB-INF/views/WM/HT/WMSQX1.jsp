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
	<table width="101%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td colspan="">
										<div class="content">
											<div class="main">
												<div class="main-one">
													<div class="main-one-box">
														<div class="main-one-title">
															<h5><b>系统信息</b></h5>
														</div>
														<div class="main-one-add">
															<div id="" class="step" style="display:block;">
																			
																	<div class="form-group control-group">
																		<span style=" float:left; padding-left:15px;"><label for="inputPassword3" class="control-label">系统分类</label></span>
																		
                                                                                     <span style="float:left;" class="col-lg-3 form-float">
																			${systeminfo.f09}
	</span>																
																	</div>
								  									<div style="clear:both;"></div>
																	<div class="form-group control-group" style="margin:0;">
																			<span style=" float:left; padding-left:15px;"><label for="inputEmail3" class="control-label">系统版本</label></span>
																		<span style="float:left;" class="col-lg-3 form-float">
																				${systeminfo.f10}
																		</span>
																	
																	</div>
																	<div style="clear:both;"></div><div class="form-group control-group">
																		<span style=" float:left; padding-left:15px;"><label for="inputEmail3" class=" control-label">绑定域名</label></span>
																		<span style="float:left;" class="col-lg-3 form-float">
																			${systeminfo.k02}
																		</span><div style="clear:both;"></div>
																		
																	</div>
																	
																	<div class="form-group control-group" style="margin:0;">
																		<span style=" float:left; padding-left:15px;"><label for="inputEmail3" class="control-label">联系电话</label></span>
																		<span style="float:left;" class="col-lg-3 form-float">
																			${systeminfo.f08}
																		</span>
																	
																	</div><div style="clear:both;"></div>																	
																	<div class="form-group control-group" style="margin:0;">
																		<span style=" float:left; padding-left:15px;"><label for="inputEmail3" class="control-label">授权有效期至</label></span>
																		<span style="float:left;" class="col-lg-3 form-float">
																			${systeminfo.f12}
																		</span>
																	
																	</div>
								                                   
							
                              
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
