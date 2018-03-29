<%@page import="java.util.Base64"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.UserImages"%>
<%@page import="model.Address"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/fileupload.css">
</head>
<body>
	<div class="home-wrapper">
		<!-- Container -->
		<div class="container">
			<!-- Row -->
			<div class="row">
				<!-- Section header -->
				<div class="section-header text-center"></div>
				<div class="col-md-9 col-md-offset-1">
					<div class="contact-form">
						<c:choose>
<%-- 						<c:when test="${not empty param.iduser}"> --%>
						<c:when test="${requestScope.user ne null}">
						<c:set var="user" value="${requestScope.user}"></c:set>
						</c:when>
						<c:otherwise>
						<c:set var="user" value="${sessionScope.user}"></c:set>
						</c:otherwise>
						</c:choose>
<%-- 						<c:set var="user" value="${sessionScope.user}"></c:set> --%>
						<form id="myform" class="form-horizontal" enctype="multipart/form-data"
							action="RegUser" method="POST" data-toggle="validator" >
							<div class="form-group has-feedback">
							
								<label class="col-sm-2 control-label"><span class="text-danger">*</span> First
									Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputName"
										name="fname"
										placeholder="Enter your first name example 'john'"
										value="${user.firstname}" data-error="first name should not be blank"  required>
										<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
										<div class="help-block with-errors"></div>
								</div>
								
							</div>
							
							<div class="form-group has-feedback">
								<label class="col-sm-2 control-label"><span class="text-danger">*</span> Last
									Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputlname"
										name="lname" placeholder="Enter your last name example 'Doe'"
										value="${user.lastname}" data-error="last name should not be blank" required>
										<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
										<div class="help-block with-errors"></div>
								</div>
							</div>

							<div class="form-group has-feedback" id="emaildiv">
								<label  class="col-sm-2 control-label"><span class="text-danger">*</span> Email</label>
								<div class="col-sm-10">
									<input type="email" class="form-control" id="inputemailid"
										placeholder="johndoe@example.com" name="email"
										${(requestScope.addrslist ne null) ? 'readonly' : ''}
										value="${user.email}" pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$" data-error="please enter valid email id format" onfocusout="checkUser()" required>
										<span class="glyphicon form-control-feedback" aria-hidden="true" ></span>
										<div id="demo" class="help-block with-errors"></div>
								</div>
								
							</div>
							<div class="form-group has-feedback" id="pwddiv" >
								<label  class="col-sm-2 control-label"><span class="text-danger">*</span> Password</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="inputPassword"
										value="${user.password}" placeholder="1@Mypass" name="pass"
										${(requestScope.addrslist ne null) ? 'readonly' : ''} data-minlength="6" maxlength="15"  required>
										<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
										<div class="help-block with-errors"></div>
								</div>
							</div>
							<c:out value="${pageContext.request.servletPath}"></c:out>
							<div class="form-group has-feedback" id="confpassdiv">
								<label  class="col-sm-2 control-label"><span class="text-danger">*</span> Confirm Password</label>
								<div class="col-sm-10">
									<input type="password" class="form-control"
										value="${user.password}" placeholder="1@Mypass" 
										${(empty user) ? '' : 'hidden'} data-minlength="6" data-match="#inputPassword"
										 data-match-error="Whoops, these don't match" placeholder="Confirm password" required>
										<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
										<div class="help-block with-errors"></div>
								</div>
							</div>

							<div class="form-group has-feedback">
								<label  class="col-sm-2 control-label"><span class="text-danger">*</span> Moblie</label>
								<div class="col-sm-10">
									<input type="text" class="only-number form-control" id="inputmobile" min="1" max="10" 
										placeholder="8844662211" data-minlength="10" name="mobile" value="${user.mobile}" pattern="^[0-9]+$" maxlength="10" required>
										<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
										<div class="help-block with-errors"></div>
								</div>
							</div>
							<fmt:formatDate value="${user.dob}" pattern="yyyy-MM-dd"
								var="date" />

							<div class="form-group has-feedback">
								<label  class="col-sm-2 control-label"><span class="text-danger">*</span> Date
									of birth</label>
								<div class="col-sm-10">
								<div class="input-group date" >
    								<input type="text" class="datepicker form-control" id="inputdob"  placeholder="21/2/1997"  name="dob"  value="${date}" required>
   									 <div class="input-group-addon">
        								<span class="glyphicon glyphicon-th"></span>
    								</div>
										
								</div>
								<div class="help-block with-errors"></div>
								<!--  	<input type="date" class="form-control" id="inputdob"
										placeholder="21/2/1997" name="dob" value="${date}" required>-->
								</div>
							</div>

							<div class="form-group  has-feedback">
								<label class="col-sm-2 control-label">image</label>
								<div class="col-sm-10">
									<ul id="media-list" class="clearfix">
										<li class="myupload"><span><i class="fa fa-plus"
												aria-hidden="true"></i><input type="file" click-type="type2"
												id="picupload" class="picupload" name="img" onchange="checkImage()" multiple></span></li>
									</ul>		
									<div id="fluploadmsg" class="help-block with-errors"></div>						
								</div>
							</div>




							<div class="form-group has-feedback" >
								<label class="col-sm-2 control-label"><span class="text-danger">*</span> Technology</label>
								<div class="col-sm-10">
									<select class="form-control" name="tech" data-error="please select any one" required>

										  <option value="">None</option>
										<c:forEach items="${requestScope.tech}" var="technologies">

											<option value="${technologies.idtech}"
												${(user.tech eq technologies.idtech)?'selected':'' }>
												<c:out value="${technologies.tech}" />
											</option>

										</c:forEach>
									</select>
									
									<div class="help-block with-errors"></div>
								</div>
							</div>
							<div class="form-group has-feedback">
							<div class="radio">
								<div class="fix-label">
									<label  class="col-sm-2 control-label"><span class="text-danger">*</span> gender</label>
										<div class="col-sm-10">
											<label class="radio-inline"> <input type="radio"
												value="0" name="gender"
												${(user.gender eq '0')? 'checked':''} required> Male
											</label> <label class="radio-inline"> <input type="radio"
												value="1" name="gender"
												${(user.gender eq '1')? 'checked':''} required>
												Female
											</label>
										</div>
								</div>
							</div>
							</div>
							<c:forEach var="lang" items="${requestScope.languages}">

								<c:out value="${lang.idlangmaster}"></c:out>
							</c:forEach>
						
							<div class="form-group has-feedback">
							<div>
									<div class="checkbox">
										<div class="fix-label">
										
											<label for="inputPassword3" class="col-sm-2 control-label"><span class="text-danger">*</span> Language</label>
											
											<div class=" col-sm-10">
												<c:forEach items="${requestScope.lang}" var="languages">
		
													<label class="checkbox-inline"> <input
														type="checkbox" name="lang" data-error="please check on languages you know"
														value="<c:out value="${languages.idlang}"  ></c:out>"
										                 <c:forEach  var="lang" items="${requestScope.languages}">
		<%-- 								                 <c:out value="'${(languages.idlang eq lang.idlangmaster)? 'checked':''}'"></c:out> --%>
															
															<c:if test="${languages.idlang == lang.idlangmaster}">  
		   													<c:out value="checked"></c:out> 
															 </c:if> 
										                 </c:forEach> data-validate="false" onchange="validateCheck()" >
														<c:out value="${languages.lang}"></c:out>
													</label>
												</c:forEach>
												<div class="help-block with-errors"></div>
											</div>
										</div>
									</div>
								</div>
								</div>
							<div class="padding-30"></div>
							<div class="panel panel-default">
								<!-- Default panel contents -->
								<div class="panel-heading">Enter your Address details</div>
								<ul class="list-group" id="list">
									<li class="list-group-item">
										<div class="well clearfix">
											<div id="czContainer">
												<div id="first">
													<div class="recordset">
														<div class="fieldRow clearfix">
															<div class="panel-body" id="pbody">
															<c:if test="${requestScope.addrslist ne null}">
															<input type="hidden" name="idaddress" id="id_idaddress_1_" value="x">
															</c:if>
																
																<div class="form-group  has-feedback">
																	<label for="inputEmail3" class="col-sm-2 control-label">Address
																		line 1</label>
																	<div class="col-sm-10">
																		<textarea rows="3" class="dynmc-input form-control"
																			name="addressline1" id="id_address1_1_" maxlength="100"  required></textarea>
																			<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
																			<div class="help-block with-errors"></div>
																	</div>
																</div>
																<div class="form-group">
																	<label for="inputEmail3" class="col-sm-2 control-label">Address
																		line 2</label>
																	<div class="col-sm-10">
																		<textarea rows="3" class="form-control"
																			name="addressline2" id="id_address2_1_"></textarea>
																	</div>
																</div>

																<div class="form-group has-feedback">
																	<label for="inputPassword3"
																		class="col-sm-2 control-label">pin</label>
																	<div class="col-sm-10">
																		<input type="text" class="dynmc-input only-number form-control"
																			placeholder="382481" name="pin" id="id_pin_1_" data-minlength="6"  maxlength="6" data-error="first name should not be blank"  required>
																			<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
																			<div class="help-block with-errors"></div>
																	</div>
																</div>


																<div class="form-group has-feedback">
																	<label for="inputPassword3"
																		class="col-sm-2 control-label">city</label>
																	<div class="col-sm-10">
																		<select class="dynmc-input form-control" name="city"
																			id="id_city_1_" required>
																			<option value="">none</option>
																			<option>Ahmedabad</option>
																			<option>jaipur</option>
																		</select>
																		<div class="help-block with-errors"></div>
																	</div>
																</div>


																<div class="form-group has-feedback">
																	<label for="inputPassword3"
																		class="col-sm-2 control-label">state</label>
																	<div class="col-sm-10">
																		<select class="dynmc-input form-control" name="state"
																			id="id_state_1_" required>
																			<option value="">none</option>
																			<option>gujarat</option>
																			<option>rajasthan</option>
																		</select>
																		<div class="help-block with-errors"></div>
																	</div>
																</div>

																<div class="form-group has-feedback">
																	<label for="inputPassword3"
																		class="col-sm-2 control-label">country</label>
																	<div class="col-sm-10" >
																		<select class="dynmc-input form-control" name="country"
																			id="id_country_1_" required>
																			<option value="">none</option>
																			<option>india</option>
																		</select>
																		<div class="help-block with-errors"></div>
																	</div>
																</div>

															</div>
														</div>
													</div>
												</div>
											</div>
										</div>




									</li>
								</ul>


								<!-- List group
								<ul class="list-group" id="list">
									
								</ul>
									<li class="list-group-item">Dapibus ac facilisis in</li>''
									<li class="list-group-item">Morbi leo risus</li>
									<li class="list-group-item">Porta ac consectetur ac</li>
									<li class="list-group-item">Vestibulum at eros</li>
							-->

							</div>
							<div class="form-group">
								<div>
								<c:choose>
								<c:when test="${pageContext.request.servletPath eq '/Registration.jsp'}">
									<button type="submit" id="mybutton" class="btn btn-primary">Sign Up</button>
								</c:when>
								<c:otherwise>
									<button type="submit" id="mybutton" class="btn btn-primary" onclick='$("form").attr("action","UpdateServ?iduser=<c:out value="${user.iduser}"></c:out>")'>Update</button>
								</c:otherwise>
								</c:choose>
								</div>
							</div>
						</form>
					</div>

					<c:out value="${requestScope.rspmsg}"></c:out>
					<img id="ItemPreview" src="" />
				</div>
			</div>
		</div>
	</div>
		
<!-- 	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script> -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js" type="text/javascript"></script>
	<script src="js/jquery.czMore-latest.js"></script>
	<script src="js/jquery.czMore-1.5.3.2.js"></script>
	<script type="text/javascript">
		//One-to-many relationship plugin by Yasir O. Atabani. Copyrights Reserved.
		$("#czContainer").czMore();
		
	</script>
	
	<script src="js/validate-email.js"></script>
	<script src="js/myvalidation.js"></script>
	<script src="js/fileupload.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
	var plusbtn = document.getElementById("btnPlus");
	<%
	if(request.getAttribute("addrslist")!=null){
		ArrayList<Address> adrs = (ArrayList<Address>) request.getAttribute("addrslist"); 
 			for (int i = 0; i<adrs.size(); i++) {%> 
 	    plusbtn.click();
 	    var idaddress = "<%=adrs.get(i).getIdadress()%>";
		var address1 = "<%=adrs.get(i).getAddressline1()%>";
		var address2 = "<%=adrs.get(i).getAddressline2()%>";
		var pin = "<%=adrs.get(i).getPin()%>";
		var city = "<%=adrs.get(i).getCity()%>";
		var state = "<%=adrs.get(i).getState()%>";
		var country = "<%=adrs.get(i).getCountry()%>";
		var idaddrselement = document.getElementById("id_idaddress_"+ <%=i+1%> +"_");
		var addrselement1 = document.getElementById("id_address1_" + <%=i+1%> + "_");
		var addrselement2 = document.getElementById("id_address2_" +  <%=i+1%> + "_");
		var pinelement = document.getElementById("id_pin_" +  <%=i+1%> + "_");
		var cityelement = document.getElementById("id_city_" +  <%=i+1%> + "_");
		var stateselement = document.getElementById("id_state_" + <%=i+1%> + "_");
		var countryelement = document.getElementById("id_country_" +  <%=i+1%> + "_");
		idaddrselement.value = idaddress;
		addrselement1.innerHTML = address1;
		addrselement2.innerHTML = address2;
		pinelement.value = pin;
		cityelement.value = city;
		stateselement.value = state;
		countryelement.value = country;
	<%}if(request.getAttribute("imglist")!=null){
 			ArrayList<UserImages> ui = (ArrayList<UserImages>) request.getAttribute("imglist");
 			for(UserImages userImage : ui){
 				%>
 				var idimg = "<%=userImage.getIduser_images()%>";
 				var iduser = "<%=userImage.getIduser()%>";
 				var  img = [];
 				var i=0;
 				<%
 					InputStream is = userImage.getImage();
 				    byte[] bytes = IOUtils.toByteArray(is);
 				   	String encoded = Base64.getEncoder().encodeToString(bytes);
 			
 				%>
 				 var div = document.createElement("li");
                 div.innerHTML = "<img src='"+"data:image/jpg;base64,"+"<%=encoded%>"+"' />"
				+"<div  class='post-thumb'><div class='inner-post-thumb'><a href='javascript:void(0);' id='"+idimg+"'  class='remove-pic'><i class='fa fa-times' aria-hidden='true'></i></a><div></div>";
                 $("#media-list").prepend(div);

 				var up = document.getElementById("picupload");
 				
<%--  			//	document.getElementById("id").src = "data:image/jpg;base64,"+"<%=encoded%>"; --%>

 				<%
 			}
 			}
	}%>
	
	});
	<c:if test="${pageContext.request.servletPath eq '/UpdateProfile.jsp'}">
	var bool= false;
	doCheck(bool);
	</c:if>
	</script> 
    
</body>
</html>