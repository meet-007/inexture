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
<link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
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
				<div class="col-md-8 col-md-offset-2">
					<div class="contact-form">
						<c:set var="user" value="${sessionScope.user}"></c:set>
						<form name="myform" class="form-horizontal" enctype="multipart/form-data"
							action="RegUser" method="POST">
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">First
									Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputEmail3"
										name="fname"
										placeholder="Enter your first name example 'john'"
										value="${user.firstname}">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">Last
									Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputPassword3"
										name="lname" placeholder="Enter your last name example 'Doe'"
										value="${user.lastname}">
								</div>
							</div>

							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">Email</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputPassword3"
										placeholder="johndoe@example.com" name="email"
										${(requestScope.addrslist ne null) ? 'readonly' : ''}
										value="${user.email}">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="inputPassword3"
										value="${user.password}" placeholder="1@Mypass" name="pass"
										${(requestScope.addrslist ne null) ? 'readonly' : ''}>
								</div>
							</div>

							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">Moblie</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputPassword3"
										placeholder="8844662211" name="mobile" value="${user.mobile}">
								</div>
							</div>
							<fmt:formatDate value="${user.dob}" pattern="yyyy-MM-dd"
								var="date" />

							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">Date
									of birth</label>
								<div class="col-sm-10">
									<input type="date" class="form-control" id="inputPassword3"
										placeholder="21/2/1997" name="dob" value="${date}">
								</div>
							</div>

							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">image</label>
								<div class="col-sm-10">
									<ul id="media-list" class="clearfix">
										<li class="myupload"><span><i class="fa fa-plus"
												aria-hidden="true"></i><input type="file" click-type="type2"
												id="picupload" class="picupload" name="img" multiple></span></li>
									</ul>								</div>
							</div>




							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">Technology</label>
								<div class="col-sm-10">
									<select class="form-control" name="tech">


										<c:forEach items="${requestScope.tech}" var="technologies">

											<option value="${technologies.idtech}"
												${(user.tech eq technologies.idtech)?'selected':'' }>
												<c:out value="${technologies.tech}" />
											</option>

										</c:forEach>
									</select>
								</div>
							</div>
							<div class="radio">
								<label for="inputPassword3" class="col-sm-2 control-label">gender</label>
								<div class="col-sm-10">
									<label class="radio-inline"> <input type="radio"
										value="0" name="gender"
										${(user.gender eq '0')? 'checked':''}> Male
									</label> <label class="radio-inline"> <input type="radio"
										value="1" name="gender"
										${(user.gender eq '1')? 'checked':''}>
										Female
									</label>
								</div>
							</div>
							<c:forEach var="lang" items="${requestScope.languages}">

								<c:out value="${lang.idlangmaster}"></c:out>
							</c:forEach>
							<div class="clearfix">
								<div class="checkbox">
									<label for="inputPassword3" class="col-sm-2 control-label">Language</label>
									<div class=" col-sm-10">
										<c:forEach items="${requestScope.lang}" var="languages">

											<label class="checkbox-inline"> <input
												type="checkbox" name="lang"
												value="<c:out value="${languages.idlang}"  ></c:out>"
								                 <c:forEach  var="lang" items="${requestScope.languages}">
<%-- 								                 <c:out value="'${(languages.idlang eq lang.idlangmaster)? 'checked':''}'"></c:out> --%>
													
													<c:if test="${languages.idlang == lang.idlangmaster}">  
   													<c:out value="checked"></c:out> 
													 </c:if> 
													
													
								                 </c:forEach>>
												<c:out value="${languages.lang}"></c:out>
											</label>
										</c:forEach>
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
															<input type="hidden" name="idaddress" id="id_idaddress_1_">
															</c:if>
															
																<div class="form-group">
																	<label for="inputEmail3" class="col-sm-2 control-label">Address
																		line 1</label>
																	<div class="col-sm-10">
																		<textarea rows="3" class="form-control"
																			name="addressline1" id="id_address1_1_"></textarea>
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

																<div class="form-group">
																	<label for="inputPassword3"
																		class="col-sm-2 control-label">pin</label>
																	<div class="col-sm-10">
																		<input type="text" class="form-control"
																			placeholder="382481" name="pin" id="id_pin_1_">
																	</div>
																</div>


																<div class="form-group">
																	<label for="inputPassword3"
																		class="col-sm-2 control-label">city</label>
																	<div class="col-sm-10">
																		<select class="form-control" name="city"
																			id="id_city_1_">
																			<option>Ahmedabad</option>
																			<option>jaipur</option>
																		</select>
																	</div>
																</div>


																<div class="form-group">
																	<label for="inputPassword3"
																		class="col-sm-2 control-label">state</label>
																	<div class="col-sm-10">
																		<select class="form-control" name="state"
																			id="id_state_1_">
																			<option>gujarat</option>
																			<option>rajasthan</option>
																		</select>
																	</div>
																</div>

																<div class="form-group">
																	<label for="inputPassword3"
																		class="col-sm-2 control-label">country</label>
																	<div class="col-sm-10">
																		<select class="form-control" name="country"
																			id="id_country_1_">
																			<option>--Select--</option>
																			<option>india</option>
																		</select>
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
								<c:when test="${empty requestScope.addrslist}">
									<button type="submit" class="btn btn-primary">Sign Up</button>
								</c:when>
								<c:otherwise>
									<button type="submit" class="btn btn-primary" onclick='document.myform.action="UpdateServ"'>Update</button>
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
	<script src="js/jquery.min.js" type="text/javascript"></script>
	<script src="js/jquery.czMore-latest.js"></script>
	<script src="js/jquery.czMore-1.5.3.2.js"></script>
	<script type="text/javascript">
		//One-to-many relationship plugin by Yasir O. Atabani. Copyrights Reserved.
		$("#czContainer").czMore();
	</script>
	<script src="js/fileupload.js"></script>
	<script type="text/javascript">
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
	<%}
 			ArrayList<UserImages> ui = (ArrayList<UserImages>) request.getAttribute("imglist");
 			for(UserImages userImage : ui){
 				%>
 				var idimg = "<%=userImage.getIduser_images()%>";
 				var iduser = "<%=userImage.getIduser()%>";
 				var  img = [];
 				var i=0;
 				<%
 				InputStream is = userImage.getImage();
 				int index = 0,i=-2;
 				
 					
 					%>
<%--  					img[i]=<%=i%>; --%>
//  					i=i+1;
 					<%
 					
 				    byte[] bytes = IOUtils.toByteArray(is);
 				   String encoded = Base64.getEncoder().encodeToString(bytes);
 					
 				
 				
 				System.out.println(index);
 				%>
 				 var div = document.createElement("li");
                 div.innerHTML = "<img src='"+"data:image/jpg;base64,"+"<%=encoded%>"+"' />"
				+"<div  class='post-thumb'><div class='inner-post-thumb'><a href='javascript:void(0);' data-id='"+" "+ "' class='remove-pic'><i class='fa fa-times' aria-hidden='true'></i></a><div></div>";
                 $("#media-list").prepend(div);

 				var up = document.getElementById("picupload");
 				
<%--  			//	document.getElementById("id").src = "data:image/jpg;base64,"+"<%=encoded%>"; --%>

 				<%
 			}
	}%>
		
	</script> 
</body>
</html>