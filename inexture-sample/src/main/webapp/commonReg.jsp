<%@page import="org.hibernate.Hibernate"%>
<%@page import="sample.models.User"%>
<%@page import="java.util.Base64"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sample.models.Address"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/fileupload.css">
</head>
<body>
	<div id="reg" class="section ">
		<!-- Container -->
		<div class="container">
			<!-- Row -->
			<div class="row">
				<!-- Section header -->
				<div class="section-header text-center"></div>
				<div class="col-md-9 col-md-offset-1">
					<div class="contact-form">


						
						<sf:errors path="user.*" element="div" cssClass="alert alert-danger"/>

						<c:choose>
							<%-- 						<c:when test="${not empty param.iduser}"> --%>
							<c:when test="${requestScope.user ne null}">
								<c:set var="user" value="${requestScope.user}"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="user" value="${sessionScope.userObject}"></c:set>
							</c:otherwise>
						</c:choose>
						<%-- 						<c:set var="user" value="${sessionScope.user}"></c:set> --%>
						<form id="myform" class="form-horizontal"
							enctype="multipart/form-data" method="POST"
							data-toggle="validator">
							<div class="form-group has-feedback">
								<label class="col-sm-2 control-label"><span
									class="text-danger">*</span> First Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputName"
										name="firstname"
										placeholder="Enter your first name example 'john'"
										value="${user.firstname}"
										data-error="first name should not be blank" required>
									<span class="glyphicon form-control-feedback"
										aria-hidden="true"></span>
									<div class="help-block with-errors"></div>
								</div>

							</div>

							<div class="form-group has-feedback">
								<label class="col-sm-2 control-label"><span
									class="text-danger">*</span> Last Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputlname"
										name="lastname"
										placeholder="Enter your last name example 'Doe'"
										value="${user.lastname}"
										data-error="last name should not be blank" required> <span
										class="glyphicon form-control-feedback" aria-hidden="true"></span>
									<div class="help-block with-errors"></div>
								</div>
							</div>

							<div class="form-group has-feedback" id="emaildiv">
								<label class="col-sm-2 control-label"><span
									class="text-danger">*</span> Email</label>
								<div class="col-sm-10">
									<input type="email" class="form-control" id="inputemailid"
										placeholder="johndoe@example.com" name="email"
										${(requestScope.addrslist ne null) ? 'readonly' : ''}
										value="${user.email}"
										pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
										data-error="please enter valid email id format"
										onfocusout="checkUser()" required> <span
										class="glyphicon form-control-feedback" aria-hidden="true"></span>
									<div id="demo" class="help-block with-errors"></div>
								</div>

							</div>
							<div class="form-group has-feedback" id="pwddiv">
								<label class="col-sm-2 control-label"><span
									class="text-danger">*</span> Password</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="inputPassword"
										value="${user.password}" placeholder="1@Mypass"
										name="password"
										${(requestScope.addrslist ne null) ? 'readonly' : ''}
										data-minlength="6" maxlength="15" required> <span
										class="glyphicon form-control-feedback" aria-hidden="true"></span>
									<div class="help-block with-errors"></div>
								</div>
							</div>
							<div class="form-group has-feedback" id="confpassdiv">
								<label class="col-sm-2 control-label"><span
									class="text-danger">*</span> Confirm Password</label>
								<div class="col-sm-10">
									<input type="password" class="form-control"
										value="${user.password}" placeholder="1@Mypass"
										${(empty user) ? '' : 'hidden'} data-minlength="6"
										data-match="#inputPassword"
										data-match-error="Whoops, these don't match"
										placeholder="Confirm password" required> <span
										class="glyphicon form-control-feedback" aria-hidden="true"></span>
									<div class="help-block with-errors"></div>
								</div>
							</div>

							<div class="form-group has-feedback">
								<label class="col-sm-2 control-label"><span
									class="text-danger">*</span> Moblie</label>
								<div class="col-sm-10">
									<input type="text" class="only-number form-control"
										id="inputmobile" min="1" max="10" placeholder="8844662211"
										data-minlength="10" name="mobile" value="${user.mobile}"
										pattern="^[0-9]+$" maxlength="10" required> <span
										class="glyphicon form-control-feedback" aria-hidden="true"></span>
									<div class="help-block with-errors"></div>
								</div>
							</div>
							<fmt:formatDate value="${user.dob}" pattern="MM/dd/yyyy"
								var="date" />

							<div class="form-group has-feedback">
								<label class="col-sm-2 control-label"><span
									class="text-danger">*</span> Date of birth</label>
								<div class="col-sm-10">
									<div class="input-group date">
										<input type="text" class="datepicker form-control"
											id="inputdob" placeholder="21/2/1997" name="dob"
											value="${date}" required>
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
												id="picupload" class="picupload" name="userImages"
												onchange="checkImage()" multiple></span></li>
									</ul>
									<div id="fluploadmsg" class="help-block with-errors"></div>
								</div>
							</div>




							<div class="form-group has-feedback">
								<label class="col-sm-2 control-label"><span
									class="text-danger">*</span> Technology</label>
								<div class="col-sm-10">
									<select class="form-control" name="tech"
										data-error="please select any one" required>

										<option value="">None</option>
										<c:forEach items="${requestScope.tech}" var="technologies">

											<option value="${technologies.idtech}"
												${(user.tech.idtech eq technologies.idtech)?'selected':'' }>
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
										<label class="col-sm-2 control-label"><span
											class="text-danger">*</span> gender</label>
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

											<label for="inputPassword3" class="col-sm-2 control-label"><span
												class="text-danger">*</span> Language</label>

											<div class=" col-sm-10">
												<c:forEach items="${requestScope.lang}" var="languages">

													<label class="checkbox-inline"> <input
														type="checkbox" name="languages"
														data-error="please check on languages you know"
														value="<c:out value="${languages.idlang}"  ></c:out>"
														<c:forEach  var="lang" items="${user.languages}">
		<%-- 								                 <c:out value="'${(languages.idlang eq lang.idlangmaster)? 'checked':''}'"></c:out> --%>
															
															<c:if test="${languages.idlang == lang.idlang}">  
		   													<c:out value="checked"></c:out> 
															 </c:if> 
										                 </c:forEach>
														data-validate="false" onchange="validateCheck()">
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
																${user.getAddressList().size()}
																<c:if test="${user.getAddressList() ne null}">
																	<input type="hidden" class="ad_id"
																		name="AddressList[1].idadress" id="id_idaddress_1_"
																		value="">
																</c:if>

																<div class="form-group  has-feedback">
																	<label for="inputEmail3" class="col-sm-2 control-label">Address
																		line 1</label>
																	<div class="col-sm-10">
																		<textarea rows="3" class="dynmc-input form-control"
																			name="AddressList[1].addressline1"
																			id="id_address1_1_" maxlength="100" required="true"></textarea>
																		<span class="glyphicon form-control-feedback"
																			aria-hidden="true"></span>
																		<div class="help-block with-errors"></div>
																	</div>
																</div>
																<div class="form-group has-feedback">
																	<label for="inputEmail3" class="col-sm-2 control-label">Address
																		line 2</label>
																	<div class="col-sm-10">
																		<textarea rows="3" class="form-control"
																			maxlength="100" name="AddressList[1].addressline2"
																			id="id_address2_1_"></textarea>
																		<span class="glyphicon form-control-feedback"
																			aria-hidden="true"></span>
																		<div class="help-block with-errors"></div>
																	</div>
																</div>

																<div class="form-group has-feedback">
																	<label for="inputPassword3"
																		class="col-sm-2 control-label">pin</label>
																	<div class="col-sm-10">
																		<input type="text"
																			class="dynmc-input only-number form-control"
																			placeholder="382481" name="AddressList[1].pin"
																			id="id_pin_1_" data-minlength="6" maxlength="6"
																			data-error="first name should not be blank"
																			required="true" /> <span
																			class="glyphicon form-control-feedback"
																			aria-hidden="true"></span>
																		<div class="help-block with-errors"></div>
																	</div>
																</div>


																<div class="form-group has-feedback">
																	<label for="inputPassword3"
																		class="col-sm-2 control-label">city</label>
																	<div class="col-sm-10">
																		<select class="dynmc-input form-control"
																			name="AddressList[1].city" id="id_city_1_"
																			required="true">
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
																		<select class="dynmc-input form-control"
																			name="AddressList[1].state" id="id_state_1_"
																			required="true">
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
																	<div class="col-sm-10">
																		<select class="dynmc-input form-control"
																			name="AddressList[1].country" id="id_country_1_"
																			required="true">
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
										<c:when
											test="${pageContext.request.servletPath eq '/Registration.jsp'}">
											<button type="submit" id="mybutton" class="btn btn-primary">Sign
												Up</button>
										</c:when>
										<c:otherwise>
											<button type="submit" id="mybutton" class="btn btn-primary"
												onclick='$("form").attr("action","?iduser=<c:out value="${user.iduser}"></c:out>")'>Update</button>
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
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.czMore-latest.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.czMore-1.5.3.2.js"></script>
	<script type="text/javascript">
		//One-to-many relationship plugin by Yasir O. Atabani. Copyrights Reserved.
		$("#czContainer").czMore();
		
	</script>


	<script src="${pageContext.request.contextPath}/js/validate-email.js"></script>
	<script src="${pageContext.request.contextPath}/js/myvalidation.js"></script>
	<script src="${pageContext.request.contextPath}/js/fileupload.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
	var plusbtn = document.getElementById("btnPlus");

   <c:if test="${user.getAddressList() ne null}">
   <c:if test="${user.getAddressList().size() ne 0}">
	<c:set var="i" value="0"></c:set>
	  <c:forEach  var="adrs" items="${user.getAddressList()}">
 	    plusbtn.click();
 	    var idaddress = "<c:out value='${adrs.idadress}'></c:out>";
		var address1 = "<c:out value='${adrs.addressline1}'></c:out>";
		var address2 = "<c:out value='${adrs.addressline2}'></c:out>";
		var pin = "<c:out value='${adrs.pin}'></c:out>";
		var city = "<c:out value='${adrs.city}'></c:out>";
		var state = "<c:out value='${adrs.state}'></c:out>";
		var country = "<c:out value='${adrs.country}'></c:out>";
		var idaddrselement = document.getElementById("id_idaddress_"+ <c:out value='${i}'></c:out>+"_");
		var addrselement1 = document.getElementById("id_address1_" + <c:out value='${i}'></c:out>+ "_");
		var addrselement2 = document.getElementById("id_address2_" +  <c:out value='${i}'></c:out> + "_");
		var pinelement = document.getElementById("id_pin_" +  <c:out value='${i}'></c:out>+ "_");
		var cityelement = document.getElementById("id_city_" +  <c:out value='${i}'></c:out> + "_");
		var stateselement = document.getElementById("id_state_" + <c:out value='${i}'></c:out>+ "_");
		var countryelement = document.getElementById("id_country_" +  <c:out value='${i}'></c:out> + "_");
		idaddrselement.value = idaddress;
		addrselement1.innerHTML = address1;
		addrselement2.innerHTML = address2;
		pinelement.value = pin;
		cityelement.value = city;
		stateselement.value = state;
		countryelement.value = country;
		<c:set var="i" value="${i+1}"></c:set>
		</c:forEach>
		</c:if>
		</c:if>
		
		//${Hibernate.initialize(user.getUserImages())}
		<c:if test="${user.userImages ne null}">
		  <c:forEach  var="userImage" items="${user.userImages}">
	
 				var idimg = "<c:out value='${userImage.iduserImages}'></c:out>";
 				var iduser = "<c:out value='${userImage.iduser}'></c:out>";
 				var  img = [];
 				var i=0;
 			
 			
 				<c:set var="is" value="${userImage.image}"></c:set>
 				//<c:set var="bytes" value="${bytes = IOUtils.toByteArray(is)}"></c:set>
 				<c:set var="encoded" value="${encoded = Base64.getEncoder().encodeToString(is)}"></c:set>
 				
 				
 			
 	
 				 var div = document.createElement("li");
                 div.innerHTML = "<img src='"+"data:image/jpg;base64,"+"<c:out value='${encoded}'></c:out>"+"' />"
				+"<div  class='post-thumb'><div class='inner-post-thumb'><a href='javascript:void(0);' id='"+idimg+"'  class='remove-pic'><i class='fa fa-times' aria-hidden='true'></i></a><div></div>";
                 $("#media-list").prepend(div);

 				var up = document.getElementById("picupload");
 				
<%--  			//	document.getElementById("id").src = "data:image/jpg;base64,"+"<%=encoded%>"; --%>

	</c:forEach>
	</c:if>

	$(document).on('click','.minusbtn',function(){
		//var hidden = $(this).next().find("input:hidden")[0];
		//$("#czContainer").append($(hidden).val("-1"))
		//console.log($(hidden));
	
		
	});
	});
	<c:if test="${pageContext.request.servletPath eq '/UpdateProfile.jsp'}">
	var bool= false;
	doCheck(bool);
	</c:if>
	</script>

</body>
</html>