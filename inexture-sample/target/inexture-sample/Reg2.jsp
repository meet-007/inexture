<%@page import="model.Address"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
	<jsp:include page="header-footer/header.jsp"></jsp:include>
	<div class="home-wrapper">
		<!-- Container -->
		<div class="container">
			<!-- Row -->
			<div class="row">
				<!-- Section header -->
				<div class="section-header text-center">
					<h2 class="title">Register here</h2>
				</div>
				<div class="col-md-8 col-md-offset-2">
					<div class="contact-form">

						<form class="form-horizontal" enctype="multipart/form-data"
							action="RegUser" method="POST">
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">First
									Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputEmail3"
										name="fname"
										placeholder="Enter your first name example 'john'">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">Last
									Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputPassword3"
										name="lname" placeholder="Enter your last name example 'Doe'">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">Email</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputPassword3"
										placeholder="johndoe@example.com" name="email">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="inputPassword3"
										placeholder="1@Mypass" name="pass">
								</div>
							</div>

							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">Moblie</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputPassword3"
										placeholder="8844662211" name="mobile">
								</div>
							</div>

							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">Date
									of birth</label>
								<div class="col-sm-10">
									<input type="date" class="form-control" id="inputPassword3"
										placeholder="21/2/1997" name="dob">
								</div>
							</div>

							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">image</label>
								<div class="col-sm-10">
									<input type="file" id="inputPassword3" multiple name="img">
								</div>
							</div>




							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">Technology</label>
								<div class="col-sm-10">
									<select class="form-control" name="tech">


										<c:forEach items="${requestScope.tech}" var="technologies">

											<option value="${technologies.idtech}">
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
										value="0" name="gender"> Male
									</label> <label class="radio-inline"> <input type="radio"
										value="1" name="gender"> Female
									</label>
								</div>
							</div>
							<div class="clearfix">
								<div class="checkbox">
									<label for="inputPassword3" class="col-sm-2 control-label">Language</label>
									<div class=" col-sm-10">
										<c:forEach items="${requestScope.lang}" var="languages">
											<label class="checkbox-inline"> <input
												type="checkbox" name="lang"
												value="<c:out value="${languages.idlang}"></c:out>">
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
									<li class="list-group-item">Dapibus ac facilisis in</li>
									<li class="list-group-item">Morbi leo risus</li>
									<li class="list-group-item">Porta ac consectetur ac</li>
									<li class="list-group-item">Vestibulum at eros</li>
							-->

							</div>
							<div class="form-group">
								<div>
									<button type="submit" class="btn btn-primary">Sign Up</button>
								</div>
							</div>
						</form>
					</div>

					<c:out value="${requestScope.rspmsg}"></c:out>
				</div>
			</div>
		</div>
	</div>




	<script src="js/jquery.min.js" type="text/javascript"></script>
	<script src="js/jquery.min.js" type="text/javascript"></script>
	<script src="js/jquery.czMore-latest.js"></script>
	<script src="js/jquery.czMore-1.5.3.2.js"></script>
	<script type="text/javascript">
		//One-to-many relationship plugin by Yasir O. Atabani. Copyrights Reserved.
		$("#czContainer").czMore();
	</script>
	<script type="text/javascript">
	var plusbtn = document.getElementById("btnPlus");
	<%Address[] adrs = (Address[]) request.getAttribute("addresslist"); 
 			for (int i = 1; i<adrs.length; i++) {%> 
 	    plusbtn.click();
		var address1 = "<%=adrs[i].getAddressline1()%>";
		var address2 = "<%=adrs[i].getAddressline2()%>";
		var pin = "<%=adrs[i].getPin()%>";
		var city = "<%=adrs[i].getCity()%>";
		var state = "<%=adrs[i].getState()%>";
		var country = "<%=adrs[i].getCountry()%>";
		var addrselement1 = document.getElementById("id_address1_" + <%=i%> + "_");
		var addrselement2 = document.getElementById("id_address2_" +  <%=i%> + "_");
		var pinelement = document.getElementById("id_pin_" +  <%=i%> + "_");
		var cityelement = document.getElementById("id_city_" +  <%=i%> + "_");
		var stateselement = document.getElementById("id_state_" + <%=i%> + "_");
		var countryelement = document.getElementById("id_country_" +  <%=i%> + "_");
		addrselement1.innerHTML = address1;
		addrselement2.innerHTML = address2;
		pinelement.value = pin;
		cityelement.value = city;
		stateselement.value = state;
		countryelement.value = country;
	<%}%>
		
	</script> 

</body>
</html>