<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<div class="container">

		<div class="row" id="pwd-container">
			<div class="col-md-4"></div>

			<div class="col-md-4">
				<section class="login-form">
				<form method="post" name="myform" action="LoginFilter" role="login" data-toggle="validator">
					<img src="http://i.imgur.com/RcmcLv4.png" class="img-responsive"
						alt="" /> 
						<div class="form-group has-feedback">
						<input id="inputemailid" type="email" name="email" placeholder="Email"
						required class="form-control input-lg"
						value=""  pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$" data-error="please enter valid email id format"  required/> 
						<span class="glyphicon form-control-feedback" aria-hidden="true" ></span>
						<div id="demo" class="help-block with-errors"></div>
						</div>
						<input type="password"
						class="form-control input-lg" id="password" placeholder="Password"
						required="" name="password" />


					<div class="pwstrength_viewport_progress"></div>
					<c:choose>
					<c:when test="${pageContext.request.servletPath eq '/Login.jsp'}">
						<button type="submit" name="go"
						class="btn btn-lg btn-primary btn-block" onclick='checkUserLogin();'>Sign in</button>
						<div>
						<a href="ShowRegServ" >Create account</a> or <a href="ForgotPassword.jsp">forgot password</a>
						</div>
					</c:when>
					<c:otherwise>
						<button type="submit" name="go"
						class="btn btn-lg btn-primary btn-block" onclick='document.myform.action="ForgotPassServ"'>Update</button>
					</c:otherwise>
					</c:choose>
					
					
					
					<c:out value="${requestScope.rspmsg}"></c:out>
				</form>
				</section>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/validate-email.js"></script>
<script>
$(document).ready(function (){
 	$(":password").hide();
});
function checkUserLogin(){
	checkUser(getFlag); 
var flag =	getFlag()
if(flag==false){
	$(":password").show();
}else{
	$(":password").hide();
}
}
</script>
</body>
</html>