<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/login.css">
<script src="js/login.js"></script>
<script>
window.history.forward();
	function noBack() { window.history.forward(); }
	</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<jsp:include page="header-footer/header.jsp"></jsp:include>
	<div class="container">

		<div class="row" id="pwd-container">
			<div class="col-md-4"></div>

			<div class="col-md-4">
				<section class="login-form">
				<form method="post" action="LoginFilter" role="login">
					<img src="http://i.imgur.com/RcmcLv4.png" class="img-responsive"
						alt="" /> <input type="email" name="email" placeholder="Email"
						required class="form-control input-lg"
						value="" /> <input type="password"
						class="form-control input-lg" id="password" placeholder="Password"
						required="" name="password" />


					<div class="pwstrength_viewport_progress"></div>
					

					<button type="submit" name="go"
						class="btn btn-lg btn-primary btn-block">Sign in</button>
					<div>
						<a href="Registration.jsp">Create account</a> or <a href="#">reset password</a>
					</div>
					<c:out value="${requestScope.rspmsg}"></c:out>
				</form>

				
				</section>
			</div>

			<div class="col-md-4"></div>


		</div>



	</div>
	
</body>
</html>